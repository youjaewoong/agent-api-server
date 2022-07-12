package com.api.server.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.api.server.model.agentstatus.AgentStatusCategoriesResponse;
import com.api.server.model.agentstatus.AgentStatusRedisCategories;
import com.api.server.model.agentstatus.CreateAgentStatus;
import com.api.server.model.agentstatus.CreateRedisAgentStatus;
import com.api.server.model.agentstatus.SearchAgentStatusCategories;
import com.api.server.service.AgentStatusService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
@RequestMapping("/advisor/agent/status")
@Validated
public class AgentStatusController {

	private final AgentStatusService agentStatusService;
	private final RestTemplate restTemplate;
	private static String WEBSOCKET_URL = "http://localhost:8089";
	
    @GetMapping("categories")
	@ApiOperation(value = "상담유형(카테고리) 조회")
    public List<AgentStatusCategoriesResponse> getAgentStatusCategories(
    											@RequestParam(value = "parent_cd", required = false) String parentCd,
    											@RequestParam(value = "cd_type", required = false) String cdType) {
		
		SearchAgentStatusCategories searchAgentStatusCategories = new SearchAgentStatusCategories();
		searchAgentStatusCategories.setParentCd(parentCd);
		searchAgentStatusCategories.setCdType(cdType);
		
        return agentStatusService.searchAgentStatusCategories(searchAgentStatusCategories);
    }
	
	
	@ApiOperation("추가")
    @PostMapping
	@ResponseStatus(HttpStatus.CREATED)
    public void addAgentStatus(@Valid @RequestBody CreateAgentStatus createAgentStatus) {
		agentStatusService.createAgentStatus(createAgentStatus);
	}
    
	
	@ApiOperation("레디스 추가")
    @PostMapping("/redis/add/{key}")
	@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> addRedisAgentStatus(@NotNull @RequestBody CreateRedisAgentStatus obj, @PathVariable String key) throws JsonProcessingException {
	    String url = WEBSOCKET_URL + "/redis/strings/set?key={key}&obj={obj}";
	    Map<String, Object> params = new HashMap<String, Object>();
	    params.put("key", key);
	    params.put("obj", new ObjectMapper().writeValueAsString(obj));
		
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class, params);
		return response;
	}
	
	
	@ApiOperation("레디스 상담유형 카테고리")
    @GetMapping(value = "/categories/{key}")
    public String getAgentCategory(@NotNull @PathVariable String key) throws NullPointerException {
		
		ResponseEntity<String> redisResponse = restTemplate.getForEntity(WEBSOCKET_URL+"/redis/strings/{id}", String.class, key);
		if (redisResponse.getBody() == null) {
			throw new NullPointerException("redis categories is null");
		}
		return redisResponse.getBody();
    }
	
	
	@ApiOperation("레디스 핵심문장 추출")
	@GetMapping(value = "/sentences", produces = "application/text; charset=utf8")
    public ResponseEntity<String> getAgentSentence(
    								@NotNull @RequestParam String key1, 
    							    @NotNull @RequestParam String key2) {
		
	    String url = WEBSOCKET_URL + "/redis/hash?key1={key1}&key2={key2}";
	    Map<String, String> params = new HashMap<String, String>();
	    params.put("key1", key1);
	    params.put("key2", key2);
		
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class, params);
		return response;
    }
	
	
	@ApiOperation("레디스 상담정보")
	@GetMapping(value = "/informations", produces = "application/text; charset=utf8")
    public void getAgentInformation() throws Exception {
		//TODO
    }
	
}
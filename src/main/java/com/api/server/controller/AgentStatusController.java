package com.api.server.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.api.server.model.agentstatus.CreateAgentStatus;
import com.api.server.model.agentstatus.SearchAgentStatusCategories;
import com.api.server.service.AgentStatusService;

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
	
	
	@ApiOperation("레디스 추가")
    @PostMapping("{key}")
	@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> addRedisAgentStatus(@NotNull @RequestBody CreateAgentStatus obj, 
    												  @PathVariable String key) {
		
		for(Map<String, Object> category : obj.getCategories() ) {
			for ( String mayKey : category.keySet() ) {
			    System.out.println(mayKey);
			    System.out.println(category.get(mayKey));
			}
		}
		
		
//		if (!RedisUtil.getHashAll(key).isEmpty()) {
//			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
//		}
//		
//		for(Map<String, Object> category : obj.getCategories() ) {
//			for (String mayKey : category.keySet() ) {
//			    System.out.println(mayKey.toString());
//			    ObjectUtil.ObjectToRedisHash(key, mayKey, category.get(mayKey).toString());
//			}
//		}
//		ObjectUtil.ObjectToRedisHash(key, "adviceSentence", obj.getSentences());
//		ObjectUtil.ObjectToRedisHash(key, "adviceMemo", obj.getMemo());
//		
//		return new ResponseEntity<>(HttpStatus.CREATED);
		return null;
	}
	
	
	@ApiOperation("레디스 상담유형 카테고리")
    @GetMapping(value = "/categories/{key}")
    public ResponseEntity<String> getAgentCategories(@NotNull @PathVariable String key) throws NullPointerException {
		
		ResponseEntity<String> categories = restTemplate.getForEntity(WEBSOCKET_URL+"/redis/strings/{id}", String.class, key);
		if (categories.getBody() == null) {
			new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(categories.getBody(), HttpStatus.OK);
    }
	
	
	@ApiOperation("레디스 핵심문장 추출")
	@GetMapping(value = "/sentences", produces = "application/text; charset=utf8")
    public ResponseEntity<String> getAgentSentences(
    								@NotNull @RequestParam String key, 
    							    @NotNull @RequestParam String hashKey) {
		
	    String url = WEBSOCKET_URL + "/redis/hash?key={key}&hashKey={hashKey}";
	    Map<String, String> params = new HashMap<String, String>();
	    params.put("key", key);
	    params.put("hashKey", hashKey);
		
		ResponseEntity<String> sentences = restTemplate.getForEntity(url, String.class, params);
		
		if (sentences == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(sentences.getBody(), HttpStatus.OK);
    }
	
	
	@ApiOperation("레디스 상담정보")
	@GetMapping(value = "/information/{key}", produces = "application/text; charset=utf8")
    public ResponseEntity<String> getAgentInformation(@NotNull @PathVariable String key) throws Exception {
		ResponseEntity<String> info = restTemplate.getForEntity(WEBSOCKET_URL+"/redis/strings/{id}", String.class, key);
		
		if (info.getBody() == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(info.getBody(), HttpStatus.OK);
    }
	
}
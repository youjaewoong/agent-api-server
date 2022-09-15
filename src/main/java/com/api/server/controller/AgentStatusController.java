package com.api.server.controller;

import java.net.URI;
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
import org.springframework.web.util.UriComponentsBuilder;

import com.api.server.model.agentstatus.AgentStatusCategoriesResponse;
import com.api.server.model.agentstatus.CreateAgentStatus;
import com.api.server.service.AgentStatusService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
@RequestMapping("/advisor/agent-status")
@Validated
public class AgentStatusController {

	private final AgentStatusService agentStatusService;
	private final RestTemplate restTemplate;
	private static String WEBSOCKET_URL = "http://localhost:8089";
	
    @GetMapping("categories")
	@ApiOperation(value = "상담유형(카테고리) 조회")
    public List<AgentStatusCategoriesResponse> getAgentStatusCategories(
    											@RequestParam(value = "cd_type", required = false) String cdType) {
		return agentStatusService.searchAgentStatusCategories(cdType);
    }
	
	
	@ApiOperation("레디스 추가")
    @PostMapping("{key}")
	@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> addRedisAgentStatus(@NotNull @RequestBody CreateAgentStatus obj, 
    												  @PathVariable String key) {
		
		
		URI pushUri = UriComponentsBuilder
				.fromUriString(WEBSOCKET_URL)
				.path("/redis/hash/push/{key}")
				.build()
				.expand(key)
				.toUri();
		
		
		URI keyUri = UriComponentsBuilder
				.fromUriString(WEBSOCKET_URL)
				.path("/redis/keys/has/{key}")
				.build()
				.expand(key)
				.toUri();
		
		//key가 존재하는지 확인
		ResponseEntity<String> iskey = restTemplate.getForEntity(keyUri, String.class);
		
		if (Boolean.parseBoolean(iskey.getBody())) {
			//key가 있으면 삭제
			URI deleteUri = UriComponentsBuilder
					.fromUriString(WEBSOCKET_URL)
					.path("/redis/keys/{key}")
					.build()
					.expand(key)
					.toUri();
			restTemplate.delete(deleteUri);
		}
	
		
		for(Map<String, Object> category : obj.getCategories() ) {
			for (String hashKey : category.keySet() ) {
				
			    Map<String, String> params = new HashMap<String, String>();
			    params.put("hashKey", hashKey);
			    params.put("obj", category.get(hashKey).toString());
			    
			    restTemplate.getForEntity(pushUri+"?hashKey={hashKey}&obj={obj}", String.class, params);
			}
		}
		
	    Map<String, String> params1 = new HashMap<String, String>();
	    if (obj.getSentences() != null) {
	    	params1.put("hashKey", "adviceSentence");
	    	params1.put("obj", obj.getSentences());
	    	restTemplate.getForEntity(pushUri+"?hashKey={hashKey}&obj={obj}", String.class, params1);
	    }
		
	    Map<String, String> params2 = new HashMap<String, String>();
	    if (obj.getMemo() != null) {
	    	params2.put("hashKey", "adviceMemo");
	    	params2.put("obj", obj.getMemo());
	    	restTemplate.getForEntity(pushUri+"?hashKey={hashKey}&obj={obj}", String.class, params2);
	    }
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	
	@ApiOperation("레디스 상담유형 카테고리")
    @GetMapping(value = "/categories/{key}")
    public ResponseEntity<String> getAgentCategories(@NotNull @PathVariable String key) throws NullPointerException {
		
		ResponseEntity<String> categories = restTemplate.getForEntity(WEBSOCKET_URL+"/redis/strings/{key}", String.class, key);

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
		
		return new ResponseEntity<>(sentences.getBody(), HttpStatus.OK);
    }
	
	
	@ApiOperation("레디스 상담정보")
	@GetMapping(value = "/information/{key}", produces = "application/text; charset=utf8")
    public ResponseEntity<String> getAgentInformation(@NotNull @PathVariable String key) throws Exception {
		ResponseEntity<String> info = restTemplate.getForEntity(WEBSOCKET_URL+"/redis/strings/{id}", String.class, key);
		
		
		return new ResponseEntity<>(info.getBody(), HttpStatus.OK);
    }
	
}
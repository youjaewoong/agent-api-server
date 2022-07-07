package com.api.server.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.server.model.agentstatus.AgentStatusCategoriesResponse;
import com.api.server.model.agentstatus.SearchAgentStatusCategories;
import com.api.server.service.AgentStatusService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
@RequestMapping("/advisor/agent-status")
@Validated
public class AgentStatusController {

	private final AgentStatusService agentStatusService;

	
    @GetMapping("categories")
	@ApiOperation(value = "조회")
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
    public void addBookmark() throws Exception {
    }
    
	
	@ApiOperation("레디스 상담유형")
    @GetMapping("/redids/categorys")
    public void getAgentCategory() throws Exception {
    }
	
	
	@ApiOperation("레디스 핵심문장 추출")
	@GetMapping("/redids/sentences")
    public void getAgentSentence() throws Exception {
    }
	
	
	@ApiOperation("레디스 상담정보")
	@GetMapping("/redids/informations")
    public void getAgentInformation() throws Exception {
    }
	
}
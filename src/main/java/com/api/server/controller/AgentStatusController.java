package com.api.server.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.server.model.agentstatus.AgentStatusCategoryResponse;
import com.api.server.model.agentstatus.SearchAgentStatusCategory;
import com.api.server.model.bookmark.CreateBookmark;
import com.api.server.service.AgentStatusService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/advisor/agent-status")
@Validated
public class AgentStatusController {

	private final AgentStatusService agentStatusService;

	
    @GetMapping("categorys")
	@ApiOperation(value = "조회")
    public List<AgentStatusCategoryResponse> getAgentStatusCategorys(
    											@RequestParam(value = "parent_cd", required = false) String parentCd,
    											@RequestParam(value = "cd_type", required = false) String cdType) {
		
		SearchAgentStatusCategory searchAgentStatusCategory = new SearchAgentStatusCategory();
		searchAgentStatusCategory.setParentCd(parentCd);
		searchAgentStatusCategory.setCdType(cdType);
		
        return agentStatusService.searchAgentStatusCategorys(searchAgentStatusCategory);
    }
    
	
	@ApiOperation("추가")
    @PostMapping
	@ResponseStatus(HttpStatus.CREATED)
    public void addBookmark(@Valid @RequestBody CreateBookmark createBookmark) throws Exception {
    	//bookMarkService.createBookmark(createBookmark);
    }
    
}
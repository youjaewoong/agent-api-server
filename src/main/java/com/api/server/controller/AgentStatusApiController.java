package com.api.server.controller;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.server.model.agentstatus.SearchAgentStatus;
import com.api.server.model.agentstatus.api.AgentStatusApiResponse;
import com.api.server.service.AgentStatusService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/agent/status")
@Validated
public class AgentStatusApiController {

	private final AgentStatusService agentStatusService;
	
	
    @GetMapping
	@ApiOperation(value = "조회")
    public AgentStatusApiResponse getAgentStatusCategories(@Valid SearchAgentStatus searchAgentStatus) throws Exception {
		
        return agentStatusService.searchAgentStatus(searchAgentStatus);
    }
	
}
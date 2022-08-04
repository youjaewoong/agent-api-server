package com.api.server.agent.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.server.agent.model.notice.AgentNoticeResponse;
import com.api.server.agent.model.notice.CreateAgentNotice;
import com.api.server.agent.model.notice.SearchAgentNoticeRequest;
import com.api.server.agent.model.notice.UpdateAgentNotice;
import com.api.server.agent.service.AgentNoticeService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/advisor")
public class AgentNoticeController {

	private final AgentNoticeService agentNoticeService;
	
	
	@ApiOperation("조회")
    @GetMapping("notices")
    public List<AgentNoticeResponse> selectAgentNotices(@NotBlank @RequestParam("agent_id") String agentId) {
		
		SearchAgentNoticeRequest searchAgentNoticeRequest = new SearchAgentNoticeRequest();
		searchAgentNoticeRequest.setAgentId(agentId);
        return agentNoticeService.selectAgentNotices(searchAgentNoticeRequest);
    }
	
	
	@ApiOperation("추가")
    @PostMapping("notice")
    public void createAgentNotice(@Valid @RequestBody CreateAgentNotice createAgentNotice) {
		agentNoticeService.createAgentNotice(createAgentNotice);
    }
	  
    
    @ApiOperation("단건 수정")
    @PutMapping("/notices/{id}")
    public void updateAgentNotice(@PathVariable String id, @Valid @RequestBody UpdateAgentNotice updateAgentNotice) {
    	updateAgentNotice.setId(id);
    	agentNoticeService.updateAgentNotice(updateAgentNotice);
    }
    
	
}
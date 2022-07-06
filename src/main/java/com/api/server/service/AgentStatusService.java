package com.api.server.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api.server.dao.AgentStatusMapper;
import com.api.server.model.agentstatus.AgentStatusCategoryResponse;
import com.api.server.model.agentstatus.SearchAgentStatusCategory;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AgentStatusService {
	
	private final AgentStatusMapper agentStatusMapper;

	
	public List<AgentStatusCategoryResponse> searchAgentStatusCategorys(SearchAgentStatusCategory searchAgentStatusCategory) {
		return agentStatusMapper.searchAgentStatusCategorys(searchAgentStatusCategory);
	}
	
}
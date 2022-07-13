package com.api.server.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api.server.dao.AgentStatusMapper;
import com.api.server.model.agentstatus.AgentStatusCategoriesResponse;
import com.api.server.model.agentstatus.SearchAgentStatusCategories;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AgentStatusService {
	
	private final AgentStatusMapper agentStatusMapper;

	public List<AgentStatusCategoriesResponse> searchAgentStatusCategories(SearchAgentStatusCategories searchAgentStatusCategory) {
		return agentStatusMapper.searchAgentStatusCategories(searchAgentStatusCategory);
	}

}
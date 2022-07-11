package com.api.server.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api.server.dao.AgentStatusMapper;
import com.api.server.model.agentstatus.AgentStatusCategoriesResponse;
import com.api.server.model.agentstatus.AgentStatusResponse;
import com.api.server.model.agentstatus.CreateAgentStatus;
import com.api.server.model.agentstatus.SearchAgentStatus;
import com.api.server.model.agentstatus.SearchAgentStatusCategories;
import com.api.server.model.agentstatus.api.AgentStatusApiResponse;
import com.api.server.model.agentstatus.api.AgentStatusCategories;
import com.api.server.model.agentstatus.api.AgentStatusDetail;
import com.api.server.model.agentstatus.api.AgentStatusSentences;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AgentStatusService {
	
	private final AgentStatusMapper agentStatusMapper;

	
	public List<AgentStatusCategoriesResponse> searchAgentStatusCategories(SearchAgentStatusCategories searchAgentStatusCategory) {
		return agentStatusMapper.searchAgentStatusCategories(searchAgentStatusCategory);
	}


	public AgentStatusCategoriesResponse searchAgentStatusCustomCategories(
			SearchAgentStatusCategories searchAgentStatusCategories) {
		return agentStatusMapper.searchAgentStatusCustomCategories(searchAgentStatusCategories);
	}
	
	
	public void createAgentStatus(CreateAgentStatus createAgentStatus) {
		agentStatusMapper.createAgentStatus(createAgentStatus);
	}
	
	
	public AgentStatusApiResponse searchAgentStatus(SearchAgentStatus searchAgentStatus) throws Exception {
		
		AgentStatusResponse response = agentStatusMapper.searchAgentStatus(searchAgentStatus).get(0);
		return new AgentStatusApiResponse(response);
	}
	
}
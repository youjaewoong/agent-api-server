package com.api.server.service;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.api.server.dao.AgentStatusMapper;
import com.api.server.model.agentstatus.AgentStatusCategoriesResponse;
import com.api.server.model.agentstatus.UpdateAgentStatus;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AgentStatusService {
	
	private final AgentStatusMapper agentStatusMapper;
	
	/**
	 * CATEGORY_ LIKE 값 기준 코드리스트 호출
	 */
	@Cacheable(cacheNames = "agentStatusCategories")
	public List<AgentStatusCategoriesResponse> searchAgentStatusCategories(String cdType) {
		return agentStatusMapper.searchAgentStatusCategories(cdType);
	}
	
	
	public void updateAgentStatus(UpdateAgentStatus updateAgentStatus) {
		agentStatusMapper.updateAgentStatus(updateAgentStatus);
	}

}
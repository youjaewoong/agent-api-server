package com.api.server.service;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.api.server.dao.AgentStatusMapper;
import com.api.server.model.agentstatus.AgentStatusCategoriesResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AgentStatusService {
	
	private final AgentStatusMapper agentStatusMapper;
	
	/**
	 * CATEGORY_ LIKE 값 기준 코드리스트 호출
	 */
	@Cacheable(cacheNames = "agentStatusCategories")
	public List<AgentStatusCategoriesResponse> searchAgentStatusCategories(String cdType) {
		return agentStatusMapper.searchAgentStatusCategories(cdType);
	}

}
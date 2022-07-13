package com.api.server.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.api.server.model.agentstatus.AgentStatusCategoriesResponse;
import com.api.server.model.agentstatus.SearchAgentStatusCategories;

@Repository
@Mapper
public interface AgentStatusMapper {

	List<AgentStatusCategoriesResponse> searchAgentStatusCategories(SearchAgentStatusCategories searchAgentStatusCategory);
	
}
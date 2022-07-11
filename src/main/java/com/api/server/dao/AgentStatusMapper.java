package com.api.server.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.api.server.model.agentstatus.AgentStatusCategoriesResponse;
import com.api.server.model.agentstatus.AgentStatusResponse;
import com.api.server.model.agentstatus.CreateAgentStatus;
import com.api.server.model.agentstatus.SearchAgentStatus;
import com.api.server.model.agentstatus.SearchAgentStatusCategories;

@Repository
@Mapper
public interface AgentStatusMapper {

	List<AgentStatusCategoriesResponse> searchAgentStatusCategories(SearchAgentStatusCategories searchAgentStatusCategory);
	
	AgentStatusCategoriesResponse searchAgentStatusCustomCategories(SearchAgentStatusCategories searchAgentStatusCategory);

	void createAgentStatus(CreateAgentStatus createAgentStatus);
	
	List<AgentStatusResponse> searchAgentStatus(SearchAgentStatus searchAgentStatus);

}
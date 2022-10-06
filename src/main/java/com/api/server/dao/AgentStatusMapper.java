package com.api.server.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.api.server.model.agentstatus.AgentStatusCategoriesResponse;
import com.api.server.model.agentstatus.UpdateAgentStatus;

@Repository
@Mapper
public interface AgentStatusMapper {

	List<AgentStatusCategoriesResponse> searchAgentStatusCategories(@Param("cdType") String cdType);
	
	int updateAgentStatus(UpdateAgentStatus updateAgentStatus);
	
}
package com.api.server.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.api.server.model.agentstatus.AgentStatusCategoryResponse;
import com.api.server.model.agentstatus.SearchAgentStatusCategory;

@Repository
@Mapper
public interface AgentStatusMapper {

	List<AgentStatusCategoryResponse> searchAgentStatusCategorys(SearchAgentStatusCategory searchAgentStatusCategory);

}
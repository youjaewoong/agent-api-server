package com.api.server.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AgentRecordMapper {

	List<Map<String, Object>> getAgentRecordRecent(HashMap<String, Object> param) throws Exception;
	void updateCustomerHighlight(HashMap<String, Object> param) throws Exception;
	HashMap<String, Object> getAgentInfo(HashMap<String, Object> param) throws Exception;
	List<Object> getDeptInfo(HashMap<String, Object> param) throws Exception;
	List<Object> getCustomerCallHistory(HashMap<String, Object> param) throws Exception;
	List<Object> getCallIdStt(HashMap<String, Object> param) throws Exception;
}

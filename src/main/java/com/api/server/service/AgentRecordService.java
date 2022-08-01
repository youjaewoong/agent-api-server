package com.api.server.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.server.dao.AgentRecordMapper;

@Service("AgentRecordService")
public class AgentRecordService{

	@Autowired
	AgentRecordService agentRecordService;

	@Autowired
	AgentRecordMapper agentRecordMyBatisMapper;

	//	당일 콜 이력 조회
	public HashMap<String, Object> getAgentRecordRecent(HashMap<String, Object> params) throws Exception {
		HashMap<String, Object> agentRecordRecentMap = new HashMap<String, Object>();
		List<Map<String, Object>> agentRecordRecentList = agentRecordMyBatisMapper.getAgentRecordRecent(params);
		agentRecordRecentMap.put("agentRecordRecentList", agentRecordRecentList);
		return agentRecordRecentMap;
	}

	//	관심 고객 등록/해제
	public void updateCustomerHighlight(HashMap<String, Object> params) throws Exception {
		agentRecordMyBatisMapper.updateCustomerHighlight(params);

	}

	// 상담사 이름, 직급 조회
	public HashMap<String, Object> getAgentInfo(HashMap<String, Object> params) throws Exception {
		HashMap<String, Object> agentRecordRecentMap = agentRecordMyBatisMapper.getAgentInfo(params);
		return agentRecordRecentMap;
	}

	// 부서 정보 조회
	public List<Object> getDeptInfo(HashMap<String, Object> params) throws Exception {
		List<Object> deptInfoList = agentRecordMyBatisMapper.getDeptInfo(params);
		return deptInfoList;
	}

	// 고객 최근 상담이력 조회
	public List<Object> getCustomerCallHistory(HashMap<String, Object> params) throws Exception {
		List<Object> customerCallHistoryList = agentRecordMyBatisMapper.getCustomerCallHistory(params);
		return customerCallHistoryList;
	}

	// 상담 내용 STT 조회
	public List<Object> getCallIdStt(HashMap<String, Object> params) throws Exception {
		List<Object> callIdSttList = agentRecordMyBatisMapper.getCallIdStt(params);
		return callIdSttList;
	}
}

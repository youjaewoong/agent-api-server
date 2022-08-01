package com.api.server.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.server.service.AgentRecordService;

@RestController
@RequestMapping("/advisor/history")
public class AgentRecordController {

	@Autowired
	AgentRecordService agentRecordService;

	// 최근 콜 이력 조회 (5)
	@GetMapping("/get/recent")
	public HashMap<String, Object> getAgentRecordRecent(@RequestParam HashMap<String, Object> params) throws Exception {
		HashMap<String, Object> agentRecordRecentMap = agentRecordService.getAgentRecordRecent(params);
		return agentRecordRecentMap;
	}
	
	// 관심 고객 등록/해제
	@GetMapping("/customer/highlight")
	public void updateCustomerHighlight(@RequestParam HashMap<String, Object> params) throws Exception {
		agentRecordService.updateCustomerHighlight(params);
	}
	
	// 상담사 이름/직급 조회
	@GetMapping("/agent/info")
	public HashMap<String, Object> getAgentInfo(@RequestParam HashMap<String, Object> params) throws Exception {
		HashMap<String, Object> agentInfoMap = agentRecordService.getAgentInfo(params);
		return agentInfoMap;
	}
	
	// 부서 테이블 조회
	@GetMapping("/getDeptInfo")
	public List<Object> getDeptInfo(@RequestParam HashMap<String, Object> params) throws Exception {
		List<Object> deptInfoList = agentRecordService.getDeptInfo(params);
		return deptInfoList;
	}
	
	// 고객 최근 상담이력 조회
	@GetMapping("/customerCallHistory")
	public List<Object> getCustomerCallHistory(@RequestParam HashMap<String, Object> params) throws Exception{
		List<Object> customerCallHistoryList = agentRecordService.getCustomerCallHistory(params);
		return customerCallHistoryList;
	}
	
	// 상담 내용 STT 조회
	@GetMapping("/callIdStt")
	public List<Object> getCallIdStt(@RequestParam HashMap<String, Object> params) throws Exception{
		List<Object> getCallIdSttList = agentRecordService.getCallIdStt(params);
		return getCallIdSttList;
	}
}

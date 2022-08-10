package com.api.server.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.server.admin.service.AdminService;
import com.api.server.util.ResultMap;

import io.swagger.annotations.ApiOperation;

@RequestMapping("/advisor/admin/agent")
@RestController
public class AdminController {

	@Autowired
	AdminService adminService;
	
	// 관리자 이름/직급 조회
	@ApiOperation("관리자 리스트 조회")
	@GetMapping("/info")
	public HashMap<String, Object> getAdminInfo(@RequestParam HashMap<String, Object> params) throws Exception {
		HashMap<String, Object> adminInfoMap = adminService.getAdminInfo(params);
		return adminInfoMap;
	}

	@ApiOperation("상담원 리스트 조회")
	@GetMapping("/list")
	public HashMap<String, Object> list(@RequestParam HashMap<String, Object> paramMap) {
		HashMap<String, Object> result = new HashMap<String, Object>();

		List<ResultMap> agentList = adminService.selectMngAdvList(paramMap);
		
		if(agentList.size()>0) {
			for(ResultMap agent : agentList) {
                try {
                	//Object callStatus = RedisUtil.getHash("callStatus", agent.get("ext").toString());
                	Object callStatus = null;
                	if (callStatus == null) {
                		agent.put("status", "0"); //대기코드 0
                	} else {
                		agent.put("status", callStatus);
                	}
                } catch (Exception e) {
                	agent.put("status", "0");
                }
			}			
		}else {
			return new HashMap<String, Object>();
		}
		
		result.put("agentList", agentList);
		return result;

	}

	@ApiOperation("관심 상담원 저장")
	@PostMapping("/insert_interest")
	public HashMap<String, Object> insertInterest(@RequestBody HashMap<String, Object> paramMap) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		int cnt = adminService.insertInterest(paramMap); 
		ResultMap interest = null;
		if(cnt > 0) {
			interest = adminService.selectMngAdvInterest(paramMap);
		}
		result.put("interest", interest);
		return result;
	}
	
	@ApiOperation("관심 상담원 삭제")
	@PostMapping("/delete_interest")
	public int deleteInterest(@RequestBody HashMap<String, Object> paramMap) {
		int result = adminService.deleteInterest(paramMap);
		return result;
	}
	
	@ApiOperation("조회")
	@GetMapping("/call_cnt")
	public HashMap<String, Object> callCnt(@RequestParam HashMap<String, Object> paramMap) {
		HashMap<String, Object> result = new HashMap<String, Object>();

		// 통화중인 민원콜, 종료된 민원콜
		// 통화중인 지연콜, 종료된 지연콜
		// 통화중인 금칙어 검출콜, 종료된 금칙어 검출콜
		// 통화중인 이슈어 검출콜, 종료된 이슈어 검출콜
		// 총 콜수

		return result;

	}
	
	@ApiOperation("callList")
	@GetMapping("/call")
	public HashMap<String, Object> call(@RequestParam HashMap<String, Object> paramMap) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		String callKey = (String) paramMap.get("callKey");
		String callInfo = (String) paramMap.get("callInfo");

		//String callList = "{\"STT\":"+RedisUtil.getListAll(callKey)+"}";
		String callList = "";
		
		
		//Map<Object, Object> callInfomation = RedisUtil.getHashAll(callInfo);
		Map<Object, Object> callInfomation = null;;
		
		try {
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(callList);
			JSONObject jsonObj = (JSONObject) obj;
				
			result.put("callList", jsonObj);
			//obj = parser.parse(callInfomation);
			//jsonObj = (JSONObject) obj;
			if(callInfomation != null ) {
				result.put("callInfomation", callInfomation);				
			}else {
				result.put("callInfomation", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("result : " + result);
		return result;

	}

}
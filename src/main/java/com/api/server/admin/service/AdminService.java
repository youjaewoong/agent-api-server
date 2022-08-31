package com.api.server.admin.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.server.admin.dao.AdminMapper;
import com.api.server.admin.model.admin.AdminDeptResponse;
import com.api.server.util.ResultMap;

@Service
@Transactional
public class AdminService {
  
	@Autowired
	AdminMapper mapper;
  
	public HashMap<String, Object> getAdminInfo(HashMap<String, Object> params) throws Exception {
		HashMap<String, Object> adminRecentMap = mapper.getAdminInfo(params);
		return adminRecentMap;
	}
	
	public List<ResultMap> selectMngAdvList(HashMap<String, Object> paramMap) {
		return mapper.selectMngAdvList(paramMap);
	}
	
	public int insertInterest(HashMap<String, Object> paramMap) {
		return mapper.insertInterest(paramMap);
	}
	
	public int deleteInterest(HashMap<String, Object> paramMap) {
		return mapper.deleteInterest(paramMap);
	}
	
	public ResultMap selectMngAdvInterest(HashMap<String, Object> paramMap) {
		return mapper.selectMngAdvInterest(paramMap);
	}

	public List<AdminDeptResponse> selectAdminDept(String companyCode) {
		return  mapper.selectAdminDept(companyCode);
	}
}
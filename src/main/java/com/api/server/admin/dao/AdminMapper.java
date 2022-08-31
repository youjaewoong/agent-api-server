package com.api.server.admin.dao;


import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.api.server.admin.model.admin.AdminDeptResponse;
import com.api.server.util.ResultMap;

@Repository
@Mapper
public interface AdminMapper {
  
	HashMap<String, Object> getAdminInfo(HashMap<String, Object> param) throws Exception;
	List<ResultMap> selectMngAdvList(HashMap<String, Object> paramMap);

	int insertInterest(HashMap<String, Object> paramMap);
	int deleteInterest(HashMap<String, Object> paramMap);
  
	ResultMap selectMngAdvInterest(HashMap<String, Object> paramMap);
	
	List<AdminDeptResponse> selectAdminDept(String companyCode);

}

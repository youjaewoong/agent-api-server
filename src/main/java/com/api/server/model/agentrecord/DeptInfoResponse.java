package com.api.server.model.agentrecord;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeptInfoResponse {

	private String deptCode; 
	private String deptName; 
	private String companyCode; 
	private String agentId; 
	private String agentName; 
	private String ext; 
	private String agentTitle; 
	private String isAdmin; 
}

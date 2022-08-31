package com.api.server.admin.model.admin;

import lombok.Getter;

@Getter
public class AdminDeptResponse {
	private String deptCode;
	private String deptName;
	private String pDeptCode;
	private int depth;
	private String companyCode;
}

package com.api.server.admin.model.notice;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchAdminNoticeRequest {
	
	private String adminId;
	
	private String deptCode;
	
	private String companyCode;
	
	private String category = "ALL";
	
	private String startDate;
	
	private String endDate;
	
	private int limit = 10;
	
	private int offset = 0;
	
}

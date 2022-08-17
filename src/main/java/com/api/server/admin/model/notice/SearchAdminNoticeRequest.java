package com.api.server.admin.model.notice;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchAdminNoticeRequest {
	
	@NotBlank
	@JsonProperty("admin_id")
	private String adminId;
	
	@JsonProperty("dept_code")
	private String deptCode;
	
	@JsonProperty("company_code")
	private String companyCode;
	
	private String category = "ALL";
	
	@JsonProperty("start_date")
	private String startDate;
	
	@JsonProperty("end_date")
	private String endDate;
	
	private String order;
	
	private String sort;
	
	private int limit = 10;
	
	private int offset = 0;
	
}

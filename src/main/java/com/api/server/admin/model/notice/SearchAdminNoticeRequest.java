package com.api.server.admin.model.notice;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchAdminNoticeRequest {
	
	@JsonProperty("admin_id")
	@NotBlank
	private String adminId;
	
	private String deptCode;
	
	private String companyCode;
}

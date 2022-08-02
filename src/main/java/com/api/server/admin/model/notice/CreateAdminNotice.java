package com.api.server.admin.model.notice;

import java.util.UUID;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAdminNotice {
	
	public CreateAdminNotice() {
		this.id = UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	@JsonIgnore
	private String id;
	
	@JsonProperty("company_code")
	private String companyCode;
	
	@NotBlank
	@JsonProperty("admin_id")
	private String adminId;
	
	@NotBlank
	private String title;
	
	@NotBlank
	private String content;
	
	private CategoryType category;
	
	@JsonProperty("dept_code")
	private String deptCode;
	
	private enum CategoryType {
        E,N,W;
    }
	
}
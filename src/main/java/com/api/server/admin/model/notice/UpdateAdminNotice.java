package com.api.server.admin.model.notice;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateAdminNotice {
	
	@JsonIgnore
	private String id;
	
	@NotBlank
	private String title;
	
	@NotBlank
	private String content;
	
	@NotBlank
	private CategoryType category;
	
	@NotBlank
	@JsonProperty("admin_id")
	private String adminId;
	
	private enum CategoryType {
        E,N,W;
    }
	
}
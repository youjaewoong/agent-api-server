package com.api.server.admin.model.notice;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteAdminNotice {
	
	private String id;
	
	@NotBlank
	@JsonProperty("admin_id")
	private String adminId;
}
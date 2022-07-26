package com.api.server.model.memo;

import java.util.UUID;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateMemo {
	
	public CreateMemo() {
		this.id = UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	@JsonIgnore
	private String id;
	
	@JsonProperty("company_code")
	private String companyCode;
	
	@NotBlank
	@JsonProperty("agent_id")
	private String agentId;
	
	@NotBlank
	@JsonProperty("group_id")
	private String groupId;
	
	@JsonIgnore
	private String title;
	
	@NotBlank
	private String content;
	
	@NotBlank
	@JsonProperty("original_content")
	private String originalContent;
	
	public void setTitle() {
		if (this.originalContent.length() > 10) {
			this.title = this.originalContent.substring(0, 7) + "...";
		} else {
			this.title = this.originalContent;
		}
	}
}
package com.api.server.model.memo;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateMemo {
	
	@JsonIgnore
	private String id;
	
	@JsonIgnore
	private String title;
	
	@NotBlank
	private String content;
	
	@NotBlank
	@JsonProperty("group_id")
	private String groupId;
	
	@NotBlank
	@JsonProperty("agent_id")
	private String agentId;
	
	@NotBlank
	@JsonProperty("original_content")
	private String originalContent;
	
	public void setTitle() {
		if (this.originalContent.length() > 10) {
			this.title = this.originalContent.substring(0, 10) + "...";
		} else {
			this.title = this.originalContent;
		}
	}
}
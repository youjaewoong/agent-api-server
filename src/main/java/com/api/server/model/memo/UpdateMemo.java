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
	private String contents;
	
	@NotBlank
	@JsonProperty("group_id")
	private String groupId;
	@NotBlank
	@JsonProperty("agent_id")
	private String agentId;
	
	public void setTitle() {
		if (this.contents.length() > 10) {
			this.title = this.contents.substring(0, 7) + "...";
		} else {
			this.title = this.contents;
		}
	}
}
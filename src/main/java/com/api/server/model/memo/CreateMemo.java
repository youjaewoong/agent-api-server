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
	@NotBlank
	@JsonProperty("agent_id")
	private String agentId;
	@NotBlank
	@JsonProperty("group_id")
	private String groupId;
	@JsonIgnore
	private String title;
	@NotBlank
	private String contents;
	
	public void setTitle() {
		if (this.contents.length() > 10) {
			this.title = this.contents.substring(0, 7) + "...";
		} else {
			this.title = this.contents;
		}
	}
}
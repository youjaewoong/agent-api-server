package com.api.server.model.memogroup;

import java.util.UUID;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateMemoGroup {
	
	public CreateMemoGroup() {
		this.id = UUID.randomUUID().toString().replaceAll("-", "");
		this.basicGroupYn = "N";
	}
	
	@JsonIgnore
	private String id;
	@NotBlank
	@JsonProperty("agent_id")
	private String agentId;
	@NotBlank
	private String title;
	@JsonProperty("basic_group_yn")
	private String basicGroupYn;
}
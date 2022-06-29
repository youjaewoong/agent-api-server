package com.api.server.model.memo;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteMemo {
	private String id;
	private String groupId;
	@NotBlank
	@JsonProperty("agent_id")
	private String agentId;
}
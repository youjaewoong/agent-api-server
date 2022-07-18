package com.api.server.model.bookmark;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteBookmark {
	
	private String id;
	
	@JsonProperty("target_id")
	private String targetId;
	
	@NotBlank
	@JsonProperty("agent_id")
	private String agentId;
}
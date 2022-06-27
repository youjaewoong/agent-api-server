package com.api.server.model.bookmarkgroup;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteBookmarkGroup {
	
	@NotBlank
	private String id;
	@NotBlank
	@JsonProperty("agent_id")
	private String agentId;
}
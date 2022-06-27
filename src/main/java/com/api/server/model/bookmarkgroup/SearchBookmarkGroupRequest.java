package com.api.server.model.bookmarkgroup;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchBookmarkGroupRequest {
	
	@NotBlank
	@JsonProperty("agent_id")
	private String agentId;
	
	@JsonIgnore
	private List<String> titles;
}
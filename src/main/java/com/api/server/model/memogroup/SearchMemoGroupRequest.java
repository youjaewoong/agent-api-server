package com.api.server.model.memogroup;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchMemoGroupRequest {
	
	@NotBlank
	private String agentId;
	
	private String title;
}
package com.api.server.model.bookmark;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchBookmarkRequest {
	
	@JsonProperty("adv_id")
	@NotBlank
	private String advId;
	@JsonProperty("group_id")
	private String groupId;
}

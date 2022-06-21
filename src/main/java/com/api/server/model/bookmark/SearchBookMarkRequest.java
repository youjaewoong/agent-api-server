package com.api.server.model.bookmark;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchBookmarkRequest {
	
	@NotBlank
	private String advId;
	private String groupId;
}

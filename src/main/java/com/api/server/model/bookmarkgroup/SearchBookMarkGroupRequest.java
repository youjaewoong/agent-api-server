package com.api.server.model.bookmarkgroup;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchBookmarkGroupRequest {
	
	@NotBlank
	private String advId;
}
package com.api.server.model.bookmarkgroup;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchBookMarkGroupRequest {
	
	@NotBlank
	private String advId;
}
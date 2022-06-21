package com.api.server.model.bookmarkgroup;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateBookmarkGroup {
	@NotBlank
	private String id;
	@NotBlank
	private String title;
}

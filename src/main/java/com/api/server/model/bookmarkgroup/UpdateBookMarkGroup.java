package com.api.server.model.bookmarkgroup;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateBookMarkGroup {
	
	private List<@Valid UpdateBookMarkGroups> editGroups;
	@NotBlank
	private String advId;
	
	@Getter
	@Setter
	public static class UpdateBookMarkGroups {
		@NotBlank
		private String id;
		@NotBlank
		private String title;
	}
}
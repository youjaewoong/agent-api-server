package com.api.server.model.bookmarkgroup;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateBookmarkGroup {
	
	@NotBlank
	private String id;
	@NotBlank
	private String title;
	@NotBlank
	@JsonProperty("adv_id")
	private String advId;
}

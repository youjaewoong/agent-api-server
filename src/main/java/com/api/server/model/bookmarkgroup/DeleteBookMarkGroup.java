package com.api.server.model.bookmarkgroup;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteBookmarkGroup {
	
	@NotEmpty
	private String id;
	@NotBlank
	@JsonProperty("adv_id")
	private String advId;
}
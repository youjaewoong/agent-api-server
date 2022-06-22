package com.api.server.model.bookmarkgroup;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteBookmarkGroups {
	
	private List<@NotEmpty String> ids;
	@NotBlank
	@JsonProperty("adv_id")
	private String advId;
}
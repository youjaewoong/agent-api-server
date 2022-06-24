package com.api.server.model.bookmarkgroup;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteBookmarkGroups {
	@NotNull
	private List<String> ids;
	@NotBlank
	@JsonProperty("adv_id")
	private String advId;
}
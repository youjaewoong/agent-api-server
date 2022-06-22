package com.api.server.model.bookmark;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteBookmark {
	private String id;
	@NotBlank
	@JsonProperty("adv_id")
	private String advId;
}
package com.api.server.model.bookmark;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteBookmark {
	@JsonIgnore
	private String id;
	@NotBlank
	private String advId;
}
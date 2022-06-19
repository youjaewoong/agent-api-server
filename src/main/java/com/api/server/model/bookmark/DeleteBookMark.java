package com.api.server.model.bookmark;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteBookMark {
	
	private List<@NotNull String> id;
	@NotBlank
	private String advId;
}
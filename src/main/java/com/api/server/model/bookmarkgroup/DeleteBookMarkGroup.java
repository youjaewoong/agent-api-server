package com.api.server.model.bookmarkgroup;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteBookMarkGroup {
	@Valid
	private List<@NotEmpty String> id;
	@NotBlank
	private String advId;
}
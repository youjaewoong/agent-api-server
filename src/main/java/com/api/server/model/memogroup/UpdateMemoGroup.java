package com.api.server.model.memogroup;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateMemoGroup {
	@JsonIgnore
	private String id;
	private String name;
}
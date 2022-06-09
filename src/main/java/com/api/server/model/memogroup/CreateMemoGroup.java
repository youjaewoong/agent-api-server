package com.api.server.model.memogroup;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateMemoGroup {
	@JsonIgnore
	private String id;
	private String userId;
	private String name;
	@JsonIgnore
 	private String createUserId;
	@JsonIgnore
	private String updateUserId;
}
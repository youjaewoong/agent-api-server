package com.api.server.model.memo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateMemo {
	@JsonIgnore
	private String id;
	@JsonIgnore
	private String title;
	private String contents;
	private String groupId;
}
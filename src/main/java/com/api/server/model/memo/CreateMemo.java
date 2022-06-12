package com.api.server.model.memo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateMemo {
	@JsonIgnore
	private String id;
	private String userId;
	private String title;
	private String contents;
	private String groupId;
}
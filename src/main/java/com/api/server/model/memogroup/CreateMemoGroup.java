package com.api.server.model.memogroup;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateMemoGroup {
	
	public CreateMemoGroup() {
		this.id = UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	@JsonIgnore
	private String id;
	private String userId;
	private String name;
}
package com.api.server.model.bookmarkgroup;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBookMarkGroup {
	
	public CreateBookMarkGroup() {
		this.id = UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	@JsonIgnore
	private String id;
	private String userId;
	private String title;
}
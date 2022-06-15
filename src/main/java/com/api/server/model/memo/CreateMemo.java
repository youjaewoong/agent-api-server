package com.api.server.model.memo;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateMemo {
	
	public CreateMemo() {
		this.id = UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	@JsonIgnore
	private String id;
	private String userId;
	private String title;
	private String contents;
	private String groupId;
	
	public void setTitle() {
		if (this.contents.length() > 10) {
			this.title = this.contents.substring(0, 7) + "...";
		} else {
			this.title = this.contents;
		}
	}
}
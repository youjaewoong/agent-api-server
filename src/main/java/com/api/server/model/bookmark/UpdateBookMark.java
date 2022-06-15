package com.api.server.model.bookmark;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateBookMark {
	@JsonIgnore
	private String id;
	@JsonIgnore
	private String title;
	private String contents;
	private String groupId;
	private String userId;
	
	public void setTitle() {
		if (this.contents.length() > 10) {
			this.title = this.contents.substring(0, 7) + "...";
		} else {
			this.title = this.contents;
		}
	}
}
package com.api.server.model.bookmark;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateBookmark {
	private String id;
	private String advId;
	@JsonIgnore
	@NotBlank
	private String title;
	private String contents;
	private updateType type;
	private String groupId;
	
	
    private enum updateType {
        K,S;
    }
    
	public void setTitle() {
		if (this.contents.length() > 10) {
			this.title = this.contents.substring(0, 7) + "...";
		} else {
			this.title = this.contents;
		}
	}
}
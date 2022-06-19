package com.api.server.model.bookmark;

import java.util.UUID;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBookMark {
	
	public CreateBookMark() {
		this.id = UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	@JsonIgnore
	private String id;
	private String advId;
	@JsonIgnore
	private String title;
	@NotBlank
	private String contents;
	private addType type;
	private String groupId;
	
    private enum addType {
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
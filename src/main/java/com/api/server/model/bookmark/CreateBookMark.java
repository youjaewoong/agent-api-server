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
	
	private enum addType {
        K,S;
    }
	
	@JsonIgnore
	private String id;
	@NotBlank
	private String advId;
	@NotBlank
	private String groupId;
	@NotBlank
	private String contents;
	@JsonIgnore
	private String title;
	@JsonIgnore
	private String subTitle;
	private addType type;
	
}
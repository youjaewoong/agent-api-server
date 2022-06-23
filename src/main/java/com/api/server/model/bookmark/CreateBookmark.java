package com.api.server.model.bookmark;

import java.util.UUID;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CreateBookmark {
	
	public CreateBookmark() {
		this.id = UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	private enum CategoryType {
        K,S;
    }
	
	@JsonIgnore
	private String id;
	@NotBlank
	private String advId;
	@NotBlank
	private String groupId;
	@NotBlank
	private String content;
	@JsonIgnore
	private String title;
	@JsonIgnore
	private String subTitle;
	private CategoryType category;
	
}
package com.api.server.model.bookmark;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
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
}
package com.api.server.model.bookmarkgroup;

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
public class CreateBookmarkGroup {
	
	public CreateBookmarkGroup() {
		this.id = UUID.randomUUID().toString().replaceAll("-", "");
		this.basicGroupYn = "N";
	}
	
	@JsonIgnore
	private String id;
	@NotBlank
	private String advId;
	@NotBlank
	private String title;
	private String basicGroupYn;
}
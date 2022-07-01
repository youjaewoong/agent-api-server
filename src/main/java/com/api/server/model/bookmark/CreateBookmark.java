package com.api.server.model.bookmark;

import java.util.UUID;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
	@JsonProperty("agent_id")
	private String agentId;
	@NotBlank
	@JsonProperty("group_id")
	private String groupId;
	@NotBlank
	private String content;
	@JsonIgnore
	private String title;
	@JsonIgnore
	@JsonProperty("sub_title")
	private String subTitle;
	private CategoryType category;
	
}
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
	@JsonProperty("target_id")
	private String targetId;
	
	@NotBlank
	@JsonProperty("group_id")
	private String groupId;
	
	private String content;
	
	@JsonProperty("sub_title")
	private String subTitle;
	
	@NotBlank
	private String title;
	
	private CategoryType category;
	
}
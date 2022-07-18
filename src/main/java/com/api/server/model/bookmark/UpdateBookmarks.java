package com.api.server.model.bookmark;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateBookmarks {
	
	@NotNull
	private List<UpdateBookmark> ids;
	
	@Getter
	@Setter
	public
	static class UpdateBookmark {
		private String id;
		
		@JsonProperty("group_id")
		private String groupId;
	}
}
package com.api.server.model.bookmark;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bookmark {
	
	private String title;
	private Content content;
	
	@Getter
	@Setter
	public static class Content{
		
		@JsonProperty("sub_title")
		private String subTitle;
		private String content;
	}
}
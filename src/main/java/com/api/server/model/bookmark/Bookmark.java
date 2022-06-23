package com.api.server.model.bookmark;

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
		private String subTitle;
		private String content;
	}
}
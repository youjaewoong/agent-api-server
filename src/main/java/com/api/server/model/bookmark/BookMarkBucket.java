package com.api.server.model.bookmark;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookmarkBucket {
	
	private String title;
	private Contents contents;
	
	@Getter
	@Setter
	public static class Contents {
		private String subTitle;
		private String contents;
	}
}
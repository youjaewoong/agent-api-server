package com.api.server.model.bookmark;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookmarkBasket {
	
	private String title;
	private Contents contents;
	
	@Getter
	@Setter
	public static class Contents {
		private String subTitle;
		private String contents;
	}
}
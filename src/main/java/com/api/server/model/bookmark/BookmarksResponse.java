package com.api.server.model.bookmark;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookmarksResponse {
	
	private String id;
	private String title;
	private String basicGroupYn;
	List<BookmarkResponse> bookmarks = new ArrayList<>();
}
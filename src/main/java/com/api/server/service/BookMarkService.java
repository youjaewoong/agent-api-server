package com.api.server.service;

import java.util.List;

import com.api.server.model.bookmark.BookmarksResponse;
import com.api.server.model.bookmark.CreateBookmark;
import com.api.server.model.bookmark.DeleteBookmark;
import com.api.server.model.bookmark.DeleteBookmarks;
import com.api.server.model.bookmark.SearchBookmarkRequest;
import com.api.server.model.bookmark.UpdateBookmark;

public interface BookmarkService {
	
	public List<BookmarksResponse> selectBookmarks(SearchBookmarkRequest searchBookmarkRequest);

	public void createBookmark(CreateBookmark createBookmark) throws Exception;

	public void updateBookmark(UpdateBookmark updateBookmark);
	
	public void deleteBookmark(DeleteBookmark deleteBookmark);

	public void deleteBookmarkByGroup(DeleteBookmark deleteBookmark);

	public void deleteBookmarkByGroups(DeleteBookmarks deleteBookmarks);

}
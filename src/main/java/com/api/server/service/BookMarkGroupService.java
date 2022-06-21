package com.api.server.service;

import java.util.List;

import com.api.server.model.bookmarkgroup.BookmarkGroupResponse;
import com.api.server.model.bookmarkgroup.CreateBookmarkGroup;
import com.api.server.model.bookmarkgroup.DeleteBookmarkGroup;
import com.api.server.model.bookmarkgroup.SearchBookmarkGroupRequest;
import com.api.server.model.bookmarkgroup.UpdateBookmarkGroup;
import com.api.server.model.bookmarkgroup.UpdateBookmarkGroups;

public interface BookMarkGroupService {
	
	public List<BookmarkGroupResponse> selectBookmarkGroups(SearchBookmarkGroupRequest searchBookmarkGroupRequest);

	public BookmarkGroupResponse createBookmarkGroup(CreateBookmarkGroup createBookmarkGroup);

	public void updateBookmarkGroups(UpdateBookmarkGroups updateBookmarkGroups) throws Exception;
	
	public void updateBookmarkGroup(UpdateBookmarkGroup updateBookmarkGroup);
	
	public void deleteBookmarkGroup(DeleteBookmarkGroup deleteBookmarkGroup);

}
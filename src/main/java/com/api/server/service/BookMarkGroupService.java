package com.api.server.service;

import java.util.List;

import com.api.server.model.bookmarkgroup.BookMarkGroupResponse;
import com.api.server.model.bookmarkgroup.CreateBookMarkGroup;
import com.api.server.model.bookmarkgroup.DeleteBookMarkGroup;
import com.api.server.model.bookmarkgroup.SearchBookMarkGroupRequest;
import com.api.server.model.bookmarkgroup.UpdateBookMarkGroup;

public interface BookMarkGroupService {
	
	public List<BookMarkGroupResponse> selectBookMarkGroups(SearchBookMarkGroupRequest searchBookMarkGroupRequest);

	public void updateBookMarkGroup(List<UpdateBookMarkGroup> updateBookMarkGroup);
	
	public void createBookMarkGroup(CreateBookMarkGroup createBookMarkGroup);
	
	<T> String createBasicBookMarkGroup(T obj);

	public void deleteBookMarkGroup(DeleteBookMarkGroup deleteBookMarkGroup);

	public void deleteBookMarkGroups();

	public int checkBookMarkGroupByName(String name);

}
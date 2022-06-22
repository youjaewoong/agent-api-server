package com.api.server.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.api.server.model.bookmarkgroup.BookmarkGroupResponse;
import com.api.server.model.bookmarkgroup.CreateBookmarkGroup;
import com.api.server.model.bookmarkgroup.DeleteBookmarkGroup;
import com.api.server.model.bookmarkgroup.DeleteBookmarkGroups;
import com.api.server.model.bookmarkgroup.SearchBookmarkGroupRequest;
import com.api.server.model.bookmarkgroup.UpdateBookmarkGroup;

@Repository
@Mapper
public interface BookmarkGroupMapper {

	List<BookmarkGroupResponse> selectBookmarkGroups(SearchBookmarkGroupRequest searchBookmarkGroupRequest);

	int createBookmarkGroup(CreateBookmarkGroup createBookmarkGroup);
    
	int updateBookmarkGroup(UpdateBookmarkGroup updateBookmarkGroup);

	int deleteBookmarkGroups(DeleteBookmarkGroups deleteBookmarkGroup);
	
	int deleteBookmarkGroup(DeleteBookmarkGroup deleteBookmarkGroup);

	int checkBookmarkGroupTitle(SearchBookmarkGroupRequest searchBookmarkGroupRequest);

}
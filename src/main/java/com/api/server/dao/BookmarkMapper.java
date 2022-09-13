package com.api.server.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.api.server.model.bookmark.BookmarkResponse;
import com.api.server.model.bookmark.CreateBookmark;
import com.api.server.model.bookmark.DeleteBookmark;
import com.api.server.model.bookmark.DeleteBookmarks;
import com.api.server.model.bookmark.SearchBookmarkRequest;
import com.api.server.model.bookmark.UpdateBookmarks.UpdateBookmark;

@Repository
@Mapper
public interface BookmarkMapper {

	public List<BookmarkResponse> selectBookmarks(SearchBookmarkRequest searchBookmarkRequest);

	public int checkBookmarkTargetId(SearchBookmarkRequest searchBookmarkRequest);

	public int createBookmark(CreateBookmark createBookmark);
    
	public int updateBookmark(UpdateBookmark updateBookmark);

	public int deleteBookmark(DeleteBookmark deleteBookmark);
	
	public int deleteBookmarkByGroup(DeleteBookmark deleteBookmark);

	public void deleteBookmarkByGroups(DeleteBookmarks deleteBookmarks);

	public void updateBookmarkToMove(HashMap<String, Object> updateBookmark);
}
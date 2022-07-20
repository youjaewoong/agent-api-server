package com.api.server.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.server.dao.BookmarkMapper;
import com.api.server.model.bookmark.BookmarkResponse;
import com.api.server.model.bookmark.BookmarksResponse;
import com.api.server.model.bookmark.CreateBookmark;
import com.api.server.model.bookmark.DeleteBookmark;
import com.api.server.model.bookmark.DeleteBookmarks;
import com.api.server.model.bookmark.SearchBookmarkRequest;
import com.api.server.model.bookmark.UpdateBookmarks;
import com.api.server.model.bookmark.UpdateBookmarks.UpdateBookmark;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class BookmarkService {
	
	private final BookmarkMapper bookMarkMapper;


	public List<BookmarksResponse> selectBookmarks(SearchBookmarkRequest searchBookmarkRequest) {
		
		List<BookmarkResponse> bookmarks = bookMarkMapper.selectBookmarks(searchBookmarkRequest);
		
		//중복그룹제거
		Set<String> checkdIds = new HashSet<String>();
		List<BookmarkResponse> groups = new ArrayList<>();
		for (BookmarkResponse bookmark : bookmarks) {
			if (!checkdIds.contains(bookmark.getGroupId())) {
				groups.add(bookmark);
			}
			checkdIds.add(bookmark.getGroupId());
		}
		
		//그룹별처리
		List<BookmarksResponse> BookMarkByGroups = new ArrayList<BookmarksResponse>();
		for (BookmarkResponse group : groups) {
			BookmarksResponse bookMarkByGroup = new BookmarksResponse();
			bookMarkByGroup.setId(group.getGroupId());
			bookMarkByGroup.setTitle(group.getGroupTitle());
			bookMarkByGroup.setBasicGroupYn(group.getBasicGroupYn());
			
			for (BookmarkResponse bookmark : bookmarks) {
				if (group.getGroupId().equals(bookmark.getGroupId())) {
					bookMarkByGroup.getBookmarks().add(bookmark);
				}
			}
			BookMarkByGroups.add(bookMarkByGroup);
		}
		return BookMarkByGroups;
	}

	
	public boolean checkBookmarkTargetId(SearchBookmarkRequest searchBookmarkRequest) {
		if (bookMarkMapper.checkBookmarkTargetId(searchBookmarkRequest) > 0) return true;
		return false;
	}
	

	public void updateBookmark(UpdateBookmarks updateBookmarks) {
		for (UpdateBookmark updateBookmark : updateBookmarks.getIds()) {
			bookMarkMapper.updateBookmark(updateBookmark);
		}
	}
	

	public void createBookmark(CreateBookmark createBookmark) {
		
		bookMarkMapper.createBookmark(createBookmark);
	}
	

	public boolean deleteBookmark(DeleteBookmark deleteBookmark) {
		if (bookMarkMapper.deleteBookmark(deleteBookmark) > 0) return true;
		
		return false;
	}
	
	
	public void deleteBookmarkByGroups(DeleteBookmarks deleteBookmarks) {
		bookMarkMapper.deleteBookmarkByGroups(deleteBookmarks);
	}
	

	public void deleteBookmarkByGroup(DeleteBookmark deleteBookmark) {
		bookMarkMapper.deleteBookmarkByGroup(deleteBookmark);
	}

	
}
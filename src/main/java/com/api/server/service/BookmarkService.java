package com.api.server.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.server.dao.BookmarkMapper;
import com.api.server.model.bookmark.Bookmark;
import com.api.server.model.bookmark.BookmarkResponse;
import com.api.server.model.bookmark.BookmarksResponse;
import com.api.server.model.bookmark.CreateBookmark;
import com.api.server.model.bookmark.DeleteBookmark;
import com.api.server.model.bookmark.DeleteBookmarks;
import com.api.server.model.bookmark.SearchBookmarkRequest;
import com.api.server.model.bookmark.UpdateBookmark;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

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


	public void updateBookmark(UpdateBookmark updateBookmark) {
		bookMarkMapper.updateBookmark(updateBookmark);
	}
	

	public void createBookmark(CreateBookmark createBookmark) throws Exception {
		ObjectMapper objectMapper =  new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		Bookmark bookMarkBucket = objectMapper.readValue(createBookmark.getContent(), Bookmark.class);
		
		if (bookMarkBucket.getContent().getSubTitle() != null) {
			createBookmark.setSubTitle(bookMarkBucket.getContent().getSubTitle());
		}
		createBookmark.setTitle(bookMarkBucket.getTitle());
		createBookmark.setContent(bookMarkBucket.getContent().getContent());
		bookMarkMapper.createBookmark(createBookmark);
	}
	

	public void deleteBookmark(DeleteBookmark deleteBookmark) {
		bookMarkMapper.deleteBookmark(deleteBookmark);
	}
	
	
	public void deleteBookmarkByGroups(DeleteBookmarks deleteBookmarks) {
		bookMarkMapper.deleteBookmarkByGroups(deleteBookmarks);
	}
	

	public void deleteBookmarkByGroup(DeleteBookmark deleteBookmark) {
		bookMarkMapper.deleteBookmarkByGroup(deleteBookmark);
	}
	
}
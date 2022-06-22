package com.api.server.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.server.dao.BookmarkMapper;
import com.api.server.model.bookmark.BookmarkBasket;
import com.api.server.model.bookmark.BookmarksResponse;
import com.api.server.model.bookmark.BookmarkResponse;
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
public class BookmarkServiceImpl implements BookmarkService {
	
	private final BookmarkMapper bookMarkMapper;

	@Override
	public List<BookmarksResponse> selectBookmarks(SearchBookmarkRequest searchBookmarkRequest) {
		
		List<BookmarkResponse> bookmarks = bookMarkMapper.selectBookmarks(searchBookmarkRequest);
		List<BookmarkResponse> groupIds = bookmarks.stream()
				.filter(distinctByKey(v -> v.getGroupId()))
				.collect(Collectors.toList());
		
		List<BookmarksResponse> BookMarkByGroups = new ArrayList<BookmarksResponse>();
		groupIds.forEach(group -> {
			BookmarksResponse bookMarkByGroup = new BookmarksResponse();
			bookMarkByGroup.setId(group.getGroupId());
			bookMarkByGroup.setTitle(group.getGroupTitle());
			bookMarkByGroup.setBasicGroupYn(group.getBasicGroupYn());
			
			bookmarks.forEach(bookmark -> {
				if (group.getGroupId().equals(bookmark.getGroupId())) {
					bookMarkByGroup.getBookmarks().add(bookmark);
				}
			});
			BookMarkByGroups.add(bookMarkByGroup);
		});
		return BookMarkByGroups;
	}


	@Override
	public void updateBookmark(UpdateBookmark updateBookmark) {
		bookMarkMapper.updateBookmark(updateBookmark);
	}
	

	@Override
	public void createBookmark(CreateBookmark createBookmark) throws Exception {
		ObjectMapper objectMapper =  new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		BookmarkBasket bookMarkBucket = objectMapper.readValue(createBookmark.getContents(), BookmarkBasket.class);
		
		if (bookMarkBucket.getContents().getSubTitle() != null) {
			createBookmark.setSubTitle(bookMarkBucket.getContents().getSubTitle());
		}
		createBookmark.setTitle(bookMarkBucket.getTitle());
		createBookmark.setContents(bookMarkBucket.getContents().getContents());
		bookMarkMapper.createBookmark(createBookmark);
	}
	

	@Override
	public void deleteBookmark(DeleteBookmark deleteBookmark) {
		bookMarkMapper.deleteBookmark(deleteBookmark);
	}
	
	
	@Override
	public void deleteBookmarkByGroups(DeleteBookmarks deleteBookmarks) {
		bookMarkMapper.deleteBookmarkByGroups(deleteBookmarks);
	}
	

	@Override
	public void deleteBookmarkByGroup(DeleteBookmark deleteBookmark) {
		bookMarkMapper.deleteBookmarkByGroup(deleteBookmark);
	}
	

	/**
	 * 특정 키로 중복제거
	 *
	 * @param keyExtractor
	 * @param <T>
	 * @return
	 */
	private static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
		Map<Object, Boolean> map = new HashMap<>();
		return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}

}
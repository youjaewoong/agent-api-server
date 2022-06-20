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

import com.api.server.dao.BookMarkMapper;
import com.api.server.model.bookmark.BookMarkBucket;
import com.api.server.model.bookmark.BookMarkByGroupResponse;
import com.api.server.model.bookmark.BookMarkResponse;
import com.api.server.model.bookmark.CreateBookMark;
import com.api.server.model.bookmark.DeleteBookMark;
import com.api.server.model.bookmark.SearchBookMarkRequest;
import com.api.server.model.bookmark.UpdateBookMark;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class BookMarkServiceImpl implements BookMarkService {
	
	private final BookMarkMapper bookMarkMapper;

	@Override
	public List<BookMarkByGroupResponse> selectBookMarks(SearchBookMarkRequest searchBookMarkRequest) {
		
		List<BookMarkResponse> bookMarks = bookMarkMapper.selectBookMarks(searchBookMarkRequest);
		List<BookMarkResponse> groups = bookMarks.stream()
				.filter(distinctByKey(m -> m.getGroupId()))
				.collect(Collectors.toList());

		
		List<BookMarkByGroupResponse> BookMarkByGroups = new ArrayList<BookMarkByGroupResponse>();
		groups.forEach(group -> {
			BookMarkByGroupResponse bookMarkByGroup = new BookMarkByGroupResponse();
			bookMarkByGroup.setId(group.getGroupId());
			bookMarkByGroup.setTitle(group.getGroupTitle());
			
			bookMarks.forEach(bookmark -> {
				if (group.getGroupId().equals(bookmark.getGroupId())) {
					bookMarkByGroup.getBookMarkByGroups().add(bookmark);
				}
			});
			BookMarkByGroups.add(bookMarkByGroup);
		});
		return BookMarkByGroups;
	}


	@Override
	public void updateBookMark(UpdateBookMark updateBookMark) {
		updateBookMark.setTitle();
		bookMarkMapper.updateBookMark(updateBookMark);
	}
	

	@Override
	public void createBookMark(CreateBookMark createBookMark) throws Exception {
		ObjectMapper objectMapper =  new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		BookMarkBucket bookMarkBucket = objectMapper.readValue(createBookMark.getContents(), BookMarkBucket.class);
		
		if (bookMarkBucket.getContents().getSubTitle() != null) {
			createBookMark.setSubTitle(bookMarkBucket.getContents().getSubTitle());
		}
		createBookMark.setTitle(bookMarkBucket.getTitle());
		createBookMark.setContents(bookMarkBucket.getContents().getContents());
		bookMarkMapper.createBookMark(createBookMark);
	}
	

	@Override
	public void deleteBookMark(String id) {
		bookMarkMapper.deleteBookMark(id);
	}
	
	
	@Override
	public void deleteBookMarks(DeleteBookMark deleteBookMark) {
		bookMarkMapper.deleteBookMarks(deleteBookMark);
	}
	

	@Override
	public void deleteBookMarkByGroups(DeleteBookMark deleteBookMark) {
		bookMarkMapper.deleteBookMarkByGroups(deleteBookMark);
		
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
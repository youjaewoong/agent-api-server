package com.api.server.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.api.server.dao.BookMarkMapper;
import com.api.server.model.bookmark.BookMarkByGroupResponse;
import com.api.server.model.bookmark.BookMarkResponse;
import com.api.server.model.bookmark.CreateBookMark;
import com.api.server.model.bookmark.DeleteBookMark;
import com.api.server.model.bookmark.SearchBookMarkRequest;
import com.api.server.model.bookmark.UpdateBookMark;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookMarkServiceImpl implements BookMarkService {
	
	private final BookMarkMapper bookMarkMapper;
	private final BookMarkGroupService bookMarkGroupService;


	@Override
	public List<BookMarkResponse> selectBookMarks(SearchBookMarkRequest searchBookMarkRequest) {
		return bookMarkMapper.selectBookMarks(searchBookMarkRequest);
	}
	
	
	@Override
	public List<BookMarkByGroupResponse> selectGroupBookMarks(SearchBookMarkRequest searchBookMarkRequest) {
		
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
	public void createBookMark(CreateBookMark createBookMark) {
		
		//매뉴그룹이 없을 경우 신규 생성 및 고유ID 셋팅
		 String createId = bookMarkGroupService.createBasicBookMarkGroup(createBookMark);
		if (createId != null) {
			createBookMark.setGroupId(createId);
		}
		createBookMark.setTitle();
		bookMarkMapper.createBookMark(createBookMark);
	}
	

	@Override
	public void deleteBookMark(DeleteBookMark deleteBookMark) {
		bookMarkMapper.deleteBookMark(deleteBookMark);
	}
	
	
	@Override
	public void deleteBookMarks() {
		bookMarkMapper.deleteBookMarks();
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
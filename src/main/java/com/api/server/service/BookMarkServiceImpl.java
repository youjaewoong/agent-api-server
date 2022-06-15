package com.api.server.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api.server.dao.BookMarkMapper;
import com.api.server.model.bookmark.BookMarkResponse;
import com.api.server.model.bookmark.CreateBookMark;
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
	public void deleteBookMark(String id) {
		bookMarkMapper.deleteBookMark(id);
	}
	
	@Override
	public void deleteBookMarks() {
		bookMarkMapper.deleteBookMarks();
	}
}
package com.api.server.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.server.dao.BookMarkGroupMapper;
import com.api.server.model.bookmarkgroup.BookMarkGroupResponse;
import com.api.server.model.bookmarkgroup.CreateBookMarkGroup;
import com.api.server.model.bookmarkgroup.DeleteBookMarkGroup;
import com.api.server.model.bookmarkgroup.SearchBookMarkGroupRequest;
import com.api.server.model.bookmarkgroup.UpdateBookMarkGroup;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class BookMarkGroupServiceImpl implements BookMarkGroupService {
	
	private final BookMarkGroupMapper bookMarkGroupMapper;
	
	@Override
	public List<BookMarkGroupResponse> selectBookMarkGroups(SearchBookMarkGroupRequest searchBookMarkGroupRequest) {
		return bookMarkGroupMapper.selectBookMarkGroups(searchBookMarkGroupRequest);
	}

	
	@Override
	public void updateBookMarkGroup(UpdateBookMarkGroup updateBookMarkGroup) {
		updateBookMarkGroup.getEditGroups().forEach(group -> {
			bookMarkGroupMapper.updateBookMarkGroup(group);
		});
	}

	
	@Override
	public BookMarkGroupResponse createBookMarkGroup(CreateBookMarkGroup createBookMarkGroup) {
		bookMarkGroupMapper.createBookMarkGroup(createBookMarkGroup);
		BookMarkGroupResponse bookMarkGroupResponse = new BookMarkGroupResponse();
		BeanUtils.copyProperties(createBookMarkGroup, bookMarkGroupResponse);
		
		return bookMarkGroupResponse;
	}


	@Override
	public void deleteBookMarkGroup(DeleteBookMarkGroup deleteBookMarkGroup) {
		bookMarkGroupMapper.deleteBookMarkGroup(deleteBookMarkGroup);
	}

	
	@Override
	public void deleteBookMarkGroups() {
		bookMarkGroupMapper.deleteBookMarkGroups();
	}

}
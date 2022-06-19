package com.api.server.service;

import java.util.List;

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
	public void createBookMarkGroup(CreateBookMarkGroup createBookMarkGroup) {
		bookMarkGroupMapper.createBookMarkGroup(createBookMarkGroup);
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
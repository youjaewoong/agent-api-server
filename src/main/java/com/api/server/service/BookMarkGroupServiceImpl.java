package com.api.server.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api.server.dao.BookMarkGroupMapper;
import com.api.server.model.bookmarkgroup.BookMarkGroupResponse;
import com.api.server.model.bookmarkgroup.CreateBookMarkGroup;
import com.api.server.model.bookmarkgroup.DeleteBookMarkGroup;
import com.api.server.model.bookmarkgroup.SearchBookMarkGroupRequest;
import com.api.server.model.bookmarkgroup.UpdateBookMarkGroup;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookMarkGroupServiceImpl implements BookMarkGroupService {
	
	private final BookMarkGroupMapper bookMarkGroupMapper;

	
	@Override
	public List<BookMarkGroupResponse> selectBookMarkGroups(SearchBookMarkGroupRequest searchBookMarkGroupRequest) {
		return bookMarkGroupMapper.selectBookMarkGroups(searchBookMarkGroupRequest);
	}

	
	@Override
	public void updateBookMarkGroup(UpdateBookMarkGroup updateBookMarkGroup) {
		bookMarkGroupMapper.updateBookMarkGroup(updateBookMarkGroup);
	}

	
	@Override
	public void createBookMarkGroup(CreateBookMarkGroup createBookMarkGroup) {
		
		//매뉴그룹이 없을 경우 기본 생성
		this.createBasicBookMarkGroup(createBookMarkGroup);
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
	

	@Override
	public int checkBookMarkGroupByName(String name) {
		return bookMarkGroupMapper.checkBookMarkGroupByName(name);
	}

	
	@Override
	public <T> String createBasicBookMarkGroup(T obj) {
		
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		SearchBookMarkGroupRequest searchBookMarkGroupRequest = 
				objectMapper.convertValue(obj, SearchBookMarkGroupRequest.class);
		
		if (bookMarkGroupMapper.selectBookMarkGroups(searchBookMarkGroupRequest).size() == 0) {
			CreateBookMarkGroup basicBookMarkGroup = new CreateBookMarkGroup();
			basicBookMarkGroup.setUserId(searchBookMarkGroupRequest.getUserId());
			basicBookMarkGroup.setTitle("기본");
			bookMarkGroupMapper.createBookMarkGroup(basicBookMarkGroup);
			return basicBookMarkGroup.getId();
		}
		return null;
	}
}
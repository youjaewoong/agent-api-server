package com.api.server.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.server.dao.BookmarkGroupMapper;
import com.api.server.dao.BookmarkMapper;
import com.api.server.model.bookmark.DeleteBookmark;
import com.api.server.model.bookmark.DeleteBookmarks;
import com.api.server.model.bookmarkgroup.BookmarkGroupResponse;
import com.api.server.model.bookmarkgroup.CreateBookmarkGroup;
import com.api.server.model.bookmarkgroup.DeleteBookmarkGroup;
import com.api.server.model.bookmarkgroup.DeleteBookmarkGroups;
import com.api.server.model.bookmarkgroup.SearchBookmarkGroupRequest;
import com.api.server.model.bookmarkgroup.UpdateBookmarkGroup;
import com.api.server.model.bookmarkgroup.UpdateBookmarkGroups;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookmarkGroupService {
	
	
	private final BookmarkGroupMapper bookMarkGroupMapper;
	private final BookmarkMapper bookmarkMapper;
	
	
	public List<BookmarkGroupResponse> selectBookmarkGroups(SearchBookmarkGroupRequest searchBookmarkGroupRequest) {
		return bookMarkGroupMapper.selectBookmarkGroups(searchBookmarkGroupRequest);
	}

	
	public void updateBookmarkGroups(UpdateBookmarkGroups updateBookmarkGroups) throws Exception {
		
		//중복타이틀제거
		int groupsSize = updateBookmarkGroups.getGroups().size();
		ArrayList<String> titls = new ArrayList<>();
		for (UpdateBookmarkGroup groups : updateBookmarkGroups.getGroups()) {
			if (!titls.contains(groups.getTitle())) {
				titls.add(groups.getTitle());
			}
		}
		
        if (groupsSize != titls.size()) {
            throw new Exception();
        }
		
		for (UpdateBookmarkGroup groups : updateBookmarkGroups.getGroups()) {
			bookMarkGroupMapper.updateBookmarkGroup(groups);
		}
	}
	
	
	public void updateBookmarkGroup(UpdateBookmarkGroup updateBookmarkGroup) throws Exception {
		
		this.checkBookmarkGroupTitle(Arrays.asList(updateBookmarkGroup.getTitle()), updateBookmarkGroup.getAgentId());
		
		bookMarkGroupMapper.updateBookmarkGroup(updateBookmarkGroup);
	}
	
	
	public BookmarkGroupResponse createBookmarkGroup(CreateBookmarkGroup createBookmarkGroup) throws Exception {
		
		this.checkBookmarkGroupTitle(Arrays.asList(createBookmarkGroup.getTitle()), createBookmarkGroup.getAgentId());
		
		bookMarkGroupMapper.createBookmarkGroup(createBookmarkGroup);
		BookmarkGroupResponse bookMarkGroupResponse = new BookmarkGroupResponse();
		BeanUtils.copyProperties(createBookmarkGroup, bookMarkGroupResponse);
		return bookMarkGroupResponse;
	}


	@Transactional
	public void deleteBookmarkGroups(DeleteBookmarkGroups deleteBookmarkGroups) {
		
		bookMarkGroupMapper.deleteBookmarkGroups(deleteBookmarkGroups);
		DeleteBookmarks deleteBookmarks = 
				new ObjectMapper().convertValue(deleteBookmarkGroups, DeleteBookmarks.class);
		bookmarkMapper.deleteBookmarkByGroups(deleteBookmarks);
		
	}
	
	
	@Transactional
	public void deleteBookmarkGroup(DeleteBookmarkGroup deleteBookmarkGroup) {
		
		bookMarkGroupMapper.deleteBookmarkGroup(deleteBookmarkGroup);
		
		DeleteBookmark deleteBookmark = new DeleteBookmark();
		deleteBookmark.setAgentId(deleteBookmarkGroup.getAgentId());
		deleteBookmark.setId(deleteBookmarkGroup.getId());
		bookmarkMapper.deleteBookmarkByGroup(deleteBookmark);
	}
	

	/**
	 * 타이틀 중복 체크
	 * @param titls
	 * @throws Exception
	 */
	private void checkBookmarkGroupTitle(List<String> titls, String agentId) throws Exception {
        
		SearchBookmarkGroupRequest searchBookmarkGroupRequest = new SearchBookmarkGroupRequest();
		searchBookmarkGroupRequest.setAgentId(agentId);
		searchBookmarkGroupRequest.setTitles(titls);
		
		int foundCnt = bookMarkGroupMapper.checkBookmarkGroupTitle(searchBookmarkGroupRequest);
		if (foundCnt > 0) {
			throw new Exception();
		}
	}

}
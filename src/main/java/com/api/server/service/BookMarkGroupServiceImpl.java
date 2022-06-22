package com.api.server.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.server.dao.BookmarkGroupMapper;
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
@Transactional
@RequiredArgsConstructor
public class BookMarkGroupServiceImpl implements BookMarkGroupService {
	
	private final BookmarkGroupMapper bookMarkGroupMapper;
	private final BookMarkService bookMarkService;
	
	@Override
	public List<BookmarkGroupResponse> selectBookmarkGroups(SearchBookmarkGroupRequest searchBookmarkGroupRequest) {
		return bookMarkGroupMapper.selectBookmarkGroups(searchBookmarkGroupRequest);
	}

	
	@Override
	public void updateBookmarkGroups(UpdateBookmarkGroups updateBookmarkGroups) throws Exception {
		
		int groupsSize = updateBookmarkGroups.getEditGroups().size();
		long distinctSize = updateBookmarkGroups.getEditGroups().stream().map(group -> {
			return group.getTitle();
		}).distinct().count();
		
		if (groupsSize != distinctSize) {
			throw new Exception();
		}
		
		updateBookmarkGroups.getEditGroups().forEach(group -> {
			bookMarkGroupMapper.updateBookmarkGroup(group);
		});
	}
	
	
	@Override
	public void updateBookmarkGroup(UpdateBookmarkGroup updateBookmarkGroup) {
		bookMarkGroupMapper.updateBookmarkGroup(updateBookmarkGroup);
	}
	
	
	@Override
	public BookmarkGroupResponse createBookmarkGroup(CreateBookmarkGroup createBookmarkGroup) {
		bookMarkGroupMapper.createBookmarkGroup(createBookmarkGroup);
		BookmarkGroupResponse bookMarkGroupResponse = new BookmarkGroupResponse();
		BeanUtils.copyProperties(createBookmarkGroup, bookMarkGroupResponse);
		
		return bookMarkGroupResponse;
	}


	@Override
	@Transactional
	public void deleteBookmarkGroups(DeleteBookmarkGroups deleteBookmarkGroups) {
		
		bookMarkGroupMapper.deleteBookmarkGroups(deleteBookmarkGroups);
		DeleteBookmarks deleteBookmarks = 
				new ObjectMapper().convertValue(deleteBookmarkGroups, DeleteBookmarks.class);
		bookMarkService.deleteBookmarkByGroups(deleteBookmarks);
		
	}
	
	
	@Override
	@Transactional
	public void deleteBookmarkGroup(DeleteBookmarkGroup deleteBookmarkGroup) {
		
		bookMarkGroupMapper.deleteBookmarkGroup(deleteBookmarkGroup);
		
		DeleteBookmark deleteBookmark = new DeleteBookmark();
		deleteBookmark.setAdvId(deleteBookmarkGroup.getAdvId());
		deleteBookmark.setId(deleteBookmarkGroup.getId());
		bookMarkService.deleteBookmarkByGroup(deleteBookmark);
	}

}
package com.api.server.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.server.model.bookmarkgroup.BookmarkGroupResponse;
import com.api.server.model.bookmarkgroup.CreateBookmarkGroup;
import com.api.server.model.bookmarkgroup.DeleteBookmarkGroup;
import com.api.server.model.bookmarkgroup.DeleteBookmarkGroups;
import com.api.server.model.bookmarkgroup.SearchBookmarkGroupRequest;
import com.api.server.model.bookmarkgroup.UpdateBookmarkGroup;
import com.api.server.model.bookmarkgroup.UpdateBookmarkGroups;
import com.api.server.service.BookMarkGroupService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class BookmarkGroupController {

	private final BookMarkGroupService bookMarkGroupService;
	
	
	@ApiOperation("조회")
    @GetMapping("/bookmark-groups")
    public List<BookmarkGroupResponse> getBookmarkGroups(@Valid SearchBookmarkGroupRequest searchBookmarkGroupRequest) {
        return bookMarkGroupService.selectBookmarkGroups(searchBookmarkGroupRequest);
    }
    
    
	@ApiOperation("추가")
    @PostMapping("/bookmark-groups")
    public BookmarkGroupResponse addBookmarkGroup(@Valid @RequestBody CreateBookmarkGroup createBookmarkGroup) {
		return bookMarkGroupService.createBookmarkGroup(createBookmarkGroup);
    }
    
	
	@ApiOperation("건별수정")
    @PutMapping("/bookmark-groups")
    public void editBookmarkGroups(@Valid @RequestBody UpdateBookmarkGroups updateBookmarkGroups) throws Exception {
    	bookMarkGroupService.updateBookmarkGroups(updateBookmarkGroups);
    }
	
	
	@ApiOperation("단건수정")
	@PutMapping("/bookmark-groups/{id}")
	public void editBookmarkGroups(@Valid @RequestBody UpdateBookmarkGroup updateBookmarkGroup) {
		bookMarkGroupService.updateBookmarkGroup(updateBookmarkGroup);
	}
	
    
	@ApiOperation("건별삭제")
	@DeleteMapping("/bookmark-groups")
    public void removeBookmarkGroup(@Valid @RequestBody DeleteBookmarkGroups deleteBookmarkGroups) {
		bookMarkGroupService.deleteBookmarkGroups(deleteBookmarkGroups);
    }
	
	
	@ApiOperation("단건삭제")
	@DeleteMapping("/bookmark-groups/{id}")
    public void removeBookmarkGroup(@Valid @RequestBody DeleteBookmarkGroup deleteBookmarkGroup) {
		bookMarkGroupService.deleteBookmarkGroup(deleteBookmarkGroup);
    }
    
}
package com.api.server.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.server.model.bookmarkgroup.BookmarkGroupResponse;
import com.api.server.model.bookmarkgroup.CreateBookmarkGroup;
import com.api.server.model.bookmarkgroup.DeleteBookmarkGroup;
import com.api.server.model.bookmarkgroup.DeleteBookmarkGroups;
import com.api.server.model.bookmarkgroup.SearchBookmarkGroupRequest;
import com.api.server.model.bookmarkgroup.UpdateBookmarkGroup;
import com.api.server.model.bookmarkgroup.UpdateBookmarkGroups;
import com.api.server.service.BookmarkGroupService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/advisor/bookmark-groups")
public class BookmarkGroupController {

	private final BookmarkGroupService bookMarkGroupService;
	
	
	@ApiOperation("조회")
    @GetMapping
    public List<BookmarkGroupResponse> getBookmarkGroups(@NotBlank @RequestParam("agent_id") String agentId) {
		SearchBookmarkGroupRequest searchBookmarkGroupRequest = new SearchBookmarkGroupRequest();
		searchBookmarkGroupRequest.setAgentId(agentId);
		return bookMarkGroupService.selectBookmarkGroups(searchBookmarkGroupRequest);
    }
    
    
	@ApiOperation("추가")
    @PostMapping
	@ResponseStatus(HttpStatus.CREATED)
    public BookmarkGroupResponse addBookmarkGroup(@Valid @RequestBody CreateBookmarkGroup createBookmarkGroup) throws Exception {
		return bookMarkGroupService.createBookmarkGroup(createBookmarkGroup);
    }
    
	
	@ApiOperation("단건수정")
	@PutMapping("{id}")
	public void editBookmarkGroups(@Valid @RequestBody UpdateBookmarkGroup updateBookmarkGroup) throws Exception {
		bookMarkGroupService.updateBookmarkGroup(updateBookmarkGroup);
	}
	
	
	@ApiOperation("건별수정")
    @PutMapping
    public void editBookmarkGroups(@Valid @RequestBody UpdateBookmarkGroups updateBookmarkGroups) throws Exception {
    	bookMarkGroupService.updateBookmarkGroups(updateBookmarkGroups);
    }
	
    
	@ApiOperation("단건삭제")
	@DeleteMapping("{id}")
    public void removeBookmarkGroup(@Valid @RequestBody DeleteBookmarkGroup deleteBookmarkGroup) {
		bookMarkGroupService.deleteBookmarkGroup(deleteBookmarkGroup);
    }
	
	
	@ApiOperation("건별삭제")
	@DeleteMapping
    public void removeBookmarkGroup(@Valid @RequestBody DeleteBookmarkGroups deleteBookmarkGroups) {
		bookMarkGroupService.deleteBookmarkGroups(deleteBookmarkGroups);
    }
	
}
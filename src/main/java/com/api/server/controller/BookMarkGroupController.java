package com.api.server.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.server.model.bookmarkgroup.BookMarkGroupResponse;
import com.api.server.model.bookmarkgroup.CreateBookMarkGroup;
import com.api.server.model.bookmarkgroup.DeleteBookMarkGroup;
import com.api.server.model.bookmarkgroup.SearchBookMarkGroupRequest;
import com.api.server.model.bookmarkgroup.UpdateBookMarkGroup;
import com.api.server.service.BookMarkGroupService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class BookMarkGroupController {

	private final BookMarkGroupService bookMarkGroupService;
	
	
	@ApiOperation("조회")
    @GetMapping("/bookmark-groups")
    public List<BookMarkGroupResponse> getBookMarkGroups(@Valid SearchBookMarkGroupRequest searchBookMarkGroupRequest) {
        return bookMarkGroupService.selectBookMarkGroups(searchBookMarkGroupRequest);
    }
    
    
	@ApiOperation("추가")
    @PostMapping("/bookmark-groups")
	@ResponseStatus(HttpStatus.CREATED)
    public BookMarkGroupResponse addBookMarkGroup(@Valid @RequestBody CreateBookMarkGroup createBookMarkGroup) {
		return bookMarkGroupService.createBookMarkGroup(createBookMarkGroup);
    }
    
	
	@ApiOperation("건별수정")
    @PutMapping("/bookmark-groups")
    public void editBookMarkGroups(@Valid @RequestBody UpdateBookMarkGroup updateBookMarkGroup) {
    	bookMarkGroupService.updateBookMarkGroup(updateBookMarkGroup);
    }
	
    
	@ApiOperation("건별삭제")
	@DeleteMapping("/bookmark-groups")
    public void removeBookMarkGroup(@Valid @RequestBody DeleteBookMarkGroup deleteBookMarkGroup) {
		bookMarkGroupService.deleteBookMarkGroup(deleteBookMarkGroup);
    }
	
    
	@ApiOperation("전체삭제")
    @DeleteMapping("/bookmark-groups/all")
    public void removeBookMarkGroups() {
		bookMarkGroupService.deleteBookMarkGroups();
    }
	
}
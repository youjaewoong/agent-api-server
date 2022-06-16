package com.api.server.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public List<BookMarkGroupResponse> getBookMarkGroups(SearchBookMarkGroupRequest searchBookMarkGroupRequest) {
        return bookMarkGroupService.selectBookMarkGroups(searchBookMarkGroupRequest);
    }
    
    
	@ApiOperation("추가")
    @PostMapping("/bookmark-groups")
    public void addMemoGroup(@RequestBody CreateBookMarkGroup createBookMarkGroup) {
		bookMarkGroupService.createBookMarkGroup(createBookMarkGroup);
    }
    
	
	@ApiOperation("기본추가")
    @PostMapping("/bookmark-groups/add/basic")
    public boolean addBasicMemoGroup(@RequestBody SearchBookMarkGroupRequest searchBookMarkGroupRequest) {
    	return bookMarkGroupService.createBasicBookMarkGroup(searchBookMarkGroupRequest) == null ? false : true;
    }
    
    
	@ApiOperation("건별삭제")
	@DeleteMapping("/bookmark-groups")
    public void removeBookMarkGroup(@RequestBody DeleteBookMarkGroup deleteBookMarkGroup) {
		bookMarkGroupService.deleteBookMarkGroup(deleteBookMarkGroup);
    }
	
    
	@ApiOperation("전체삭제")
    @DeleteMapping("/bookmark-groups/all")
    public void removeBookMarkGroups() {
		bookMarkGroupService.deleteBookMarkGroups();
    }
	
    
	@ApiOperation("수정")
    @PutMapping("/bookmark-groups")
    public void modifyBookMarkGroup(@RequestBody UpdateBookMarkGroup updateBookMarkGroup) {
    	bookMarkGroupService.updateBookMarkGroup(updateBookMarkGroup);
    }
    
	
	@ApiOperation("그룹명 중복체크")
    @GetMapping("/bookmark-groups/check/name/{name}")
    public boolean checkBookMarkGroupByName(@PathVariable String name) {
    	return bookMarkGroupService.checkBookMarkGroupByName(name) == 0 ? false : true;
    }
}
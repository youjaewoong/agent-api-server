package com.api.server.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.server.model.bookmark.BookMarkByGroupResponse;
import com.api.server.model.bookmark.CreateBookMark;
import com.api.server.model.bookmark.DeleteBookMark;
import com.api.server.model.bookmark.SearchBookMarkRequest;
import com.api.server.model.bookmark.UpdateBookMark;
import com.api.server.service.BookMarkService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class BookMarkController {

	private final BookMarkService bookMarkService;
	

	@ApiOperation("조회")
    @GetMapping("/bookmarks")
    public List<BookMarkByGroupResponse> getBookMarks(@Valid SearchBookMarkRequest searchBookMarkRequest) {
        return bookMarkService.selectBookMarks(searchBookMarkRequest);
    }
    
	
	@ApiOperation("추가")
    @PostMapping("/bookmarks")
    public void addBookMark(@Valid @RequestBody CreateBookMark createBookMark) throws Exception {
    	bookMarkService.createBookMark(createBookMark);
    }
    
	
	@ApiOperation("수정")
    @PutMapping("/bookmarks")
    public void editBookMark(@Valid @RequestBody UpdateBookMark updateBookMark) {
    	bookMarkService.updateBookMark(updateBookMark);
    }
	
	
	@ApiOperation("단건삭제")
    @DeleteMapping("/bookmarks/{id}")
    public void removeBookMark(@Valid @NotEmpty @PathVariable String id) {
    	bookMarkService.deleteBookMark(id);
    }
	
	
	@ApiOperation("건별삭제")
    @DeleteMapping("/bookmarks")
    public void removeBookMarks(@Valid @RequestBody DeleteBookMark deleteBookMark) {
    	bookMarkService.deleteBookMarks(deleteBookMark);
    }
	
	
	@ApiOperation("그룹별삭제")
    @DeleteMapping("/bookmarks/groups")
    public void removeBookMarkByGroups(@Valid @RequestBody DeleteBookMark deleteBookMark) {
    	bookMarkService.deleteBookMarkByGroups(deleteBookMark);
    }

}
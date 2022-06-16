package com.api.server.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.server.model.bookmark.BookMarkByGroupResponse;
import com.api.server.model.bookmark.BookMarkResponse;
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
	
	@ApiOperation("전체조회")
    @GetMapping("/bookmarks")
    public List<BookMarkResponse> getBookMarks(SearchBookMarkRequest searchBookMarkRequest) {
        return bookMarkService.selectBookMarks(searchBookMarkRequest);
    }
	
	@ApiOperation("그룹별 전체조회")
    @GetMapping("/bookmarks/groupby")
    public List<BookMarkByGroupResponse> getGroupBookMarks(SearchBookMarkRequest searchBookMarkRequest) {
        return bookMarkService.selectGroupBookMarks(searchBookMarkRequest);
    }
    
	
	@ApiOperation("추가")
    @PostMapping("/bookmarks")
    public void createBookMark(@RequestBody CreateBookMark createBookMark) {
    	bookMarkService.createBookMark(createBookMark);
    }
    
	
	@ApiOperation("건별삭제")
    @DeleteMapping("/bookmarks")
    public void removeBookMark(@RequestBody DeleteBookMark deleteBookMark) {
    	bookMarkService.deleteBookMark(deleteBookMark);
    }
    
	@ApiOperation("전체삭제")
    @DeleteMapping("/bookmarks/all")
    public void removeBookMarks() {
    	bookMarkService.deleteBookMarks();
    }
    
	@ApiOperation("수정")
    @PutMapping("/bookmarks")
    public void modifyBookMark(@RequestBody UpdateBookMark updateBookMark) {
    	bookMarkService.updateBookMark(updateBookMark);
    }
}
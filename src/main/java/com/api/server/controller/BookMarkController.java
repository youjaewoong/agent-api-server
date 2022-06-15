package com.api.server.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.server.model.bookmark.BookMarkByGroupResponse;
import com.api.server.model.bookmark.BookMarkResponse;
import com.api.server.model.bookmark.CreateBookMark;
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
    
	
	@ApiOperation("단건삭제")
    @DeleteMapping("/bookmarks/{id}")
    public void removeBookMark(@PathVariable String id) {
    	bookMarkService.deleteBookMark(id);
    }
    
	@ApiOperation("전체삭제")
    @DeleteMapping("/bookmarks")
    public void removeBookMarks() {
    	bookMarkService.deleteBookMarks();
    }
    
	@ApiOperation("수정")
    @PutMapping("/bookmarks/{id}")
    public void modifyBookMark(@PathVariable String id, @RequestBody UpdateBookMark updateBookMark) {
    	updateBookMark.setId(id);
    	bookMarkService.updateBookMark(updateBookMark);
    }
}
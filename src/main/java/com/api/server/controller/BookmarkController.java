package com.api.server.controller;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.server.model.bookmark.BookmarksResponse;
import com.api.server.model.bookmark.CreateBookmark;
import com.api.server.model.bookmark.DeleteBookmark;
import com.api.server.model.bookmark.DeleteBookmarks;
import com.api.server.model.bookmark.SearchBookmarkRequest;
import com.api.server.model.bookmark.UpdateBookmarks;
import com.api.server.service.BookmarkService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/advisor/bookmarks")
@Validated
public class BookmarkController {

	private final BookmarkService bookMarkService;
	

    @GetMapping
	@ApiOperation(value = "조회")
    public List<BookmarksResponse> getBookmarks(@NotBlank @RequestParam("agent_id") String agentId,
    											@Nullable @RequestParam("group_id") String groupId) {
		
		SearchBookmarkRequest searchBookmarkRequest = new SearchBookmarkRequest();
		searchBookmarkRequest.setAgentId(agentId);
		searchBookmarkRequest.setGroupId(groupId);
		
        return bookMarkService.selectBookmarks(searchBookmarkRequest);
    }
    
    
	@ApiOperation("대상ID 확인")
	@GetMapping("check/{targetId}")
    public boolean checkBookmarkTargetId(@NotBlank @RequestParam("agent_id") String agentId,
										 @PathVariable String targetId) {
		
		SearchBookmarkRequest searchBookmarkRequest = new SearchBookmarkRequest();
		searchBookmarkRequest.setAgentId(agentId);
		searchBookmarkRequest.setTargetId(targetId);
		
    	return bookMarkService.checkBookmarkTargetId(searchBookmarkRequest);
    }
	
    
	
	@ApiOperation("추가")
    @PostMapping
	@ResponseStatus(HttpStatus.CREATED)
    public void addBookmark(@Valid @RequestBody CreateBookmark createBookmark) throws Exception {
    	bookMarkService.createBookmark(createBookmark);
    }
    
	
	@ApiOperation("수정")
    @PutMapping
    public void editBookmark(@Valid @RequestBody UpdateBookmarks updateBookmark) {
    	bookMarkService.updateBookmark(updateBookmark);
    }
	
	
	@ApiOperation("단건삭제")
    @DeleteMapping
    public boolean removeBookmark(@Valid @RequestBody DeleteBookmark deleteBookmark) {
    	return bookMarkService.deleteBookmark(deleteBookmark);
    }
	
	
	@ApiOperation("그룹별삭제")
	@DeleteMapping("groups")
	public void removeBookmarkByGroups(@Valid @RequestBody DeleteBookmarks deleteBookmark) {
		bookMarkService.deleteBookmarkByGroups(deleteBookmark);
	}
	
	
	@ApiOperation("그룹삭제")
    @DeleteMapping("groups/{id}")
    public void removeBookmarkByGroup(@Valid @NotEmpty @PathVariable String id, 
    								  @Valid DeleteBookmark deleteBookmark) {
		deleteBookmark.setId(id);
    	bookMarkService.deleteBookmarkByGroup(deleteBookmark);
    }
	
	
    @ApiOperation("이동그룹 수정")
    @PutMapping("move")
    public void putMemoMove(@RequestBody HashMap<String, Object> updateBookmark) {
    	bookMarkService.updateBookmarkToMove(updateBookmark);
    }
}
package com.api.server.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.server.model.bookmark.BookmarksResponse;
import com.api.server.model.bookmark.CreateBookmark;
import com.api.server.model.bookmark.DeleteBookmark;
import com.api.server.model.bookmark.DeleteBookmarks;
import com.api.server.model.bookmark.SearchBookmarkRequest;
import com.api.server.model.bookmark.UpdateBookmark;
import com.api.server.service.BookmarkService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class BookmarkController {

	private final BookmarkService bookMarkService;
	

    @GetMapping("/bookmarks")
	@ApiOperation(value = "조회")
    public List<BookmarksResponse> getBookmarks(@NotBlank @RequestParam("adv_id") String advId,
    											@Nullable @RequestParam("group_id") String groupId) {
		
		SearchBookmarkRequest searchBookmarkRequest = new SearchBookmarkRequest();
		searchBookmarkRequest.setAdvId(advId);
		searchBookmarkRequest.setGroupId(groupId);
		
        return bookMarkService.selectBookmarks(searchBookmarkRequest);
    }
    
	
	@ApiOperation("추가")
    @PostMapping("/bookmarks")
    public void addBookmark(@Valid @RequestBody CreateBookmark createBookmark) throws Exception {
    	bookMarkService.createBookmark(createBookmark);
    }
    
	
	@ApiOperation("수정")
    @PutMapping("/bookmarks")
    public void editBookmark(@Valid @RequestBody UpdateBookmark updateBookmark) {
    	bookMarkService.updateBookmark(updateBookmark);
    }
	
	
	@ApiOperation("단건삭제")
    @DeleteMapping("/bookmarks/{id}")
    public void removeBookmark(@Valid @RequestBody DeleteBookmark deleteBookmark) {
    	bookMarkService.deleteBookmark(deleteBookmark);
    }
	
	
	@ApiOperation("그룹별삭제")
	@DeleteMapping("/bookmarks/groups")
	public void removeBookmarkByGroups(@Valid @RequestBody DeleteBookmarks deleteBookmark) {
		bookMarkService.deleteBookmarkByGroups(deleteBookmark);
	}
	
	
	@ApiOperation("그룹삭제")
    @DeleteMapping("/bookmarks/groups/{id}")
    public void removeBookmarkByGroup(@Valid @NotEmpty @PathVariable String id, 
    								  @Valid DeleteBookmark deleteBookmark) {
		deleteBookmark.setId(id);
    	bookMarkService.deleteBookmarkByGroup(deleteBookmark);
    }
}
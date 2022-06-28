package com.api.server.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.server.model.bookmarkgroup.UpdateBookmarkGroups;
import com.api.server.model.memogroup.CreateMemoGroup;
import com.api.server.model.memogroup.MemoGroupResponse;
import com.api.server.model.memogroup.SearchMemoGroupRequest;
import com.api.server.model.memogroup.UpdateMemoGroup;
import com.api.server.model.memogroup.UpdateMemoGroups;
import com.api.server.service.MemoGroupService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class MemoGroupController {

	private final MemoGroupService memoGroupService;
	
	@ApiOperation("전체조회")
    @GetMapping("/memo-groups")
    public List<MemoGroupResponse> getMemoGroups() {
        return memoGroupService.selectMemoGroups();
    }
    
	
	@ApiOperation("유저ID로 조회")
    @GetMapping("/memo-groups/{userId}")
    public List<MemoGroupResponse> getMemoGroup(@PathVariable @NotBlank String userId) {
    	
    	SearchMemoGroupRequest searchMemoGroupRequest = new SearchMemoGroupRequest();
    	searchMemoGroupRequest.setUserId(userId);
        return memoGroupService.selectMemoGroups(searchMemoGroupRequest);
    }
    
    
	@ApiOperation("추가")
    @PostMapping("/memo-groups")
    public MemoGroupResponse addMemoGroup(@RequestBody CreateMemoGroup createMemoGroup) throws Exception {
    	return memoGroupService.createMemoGroup(createMemoGroup);
    }
    
    
	@ApiOperation("단건삭제")
    @DeleteMapping("/memo-groups/{id}")
    public void removeMemoGroup(@PathVariable String id) {
    	memoGroupService.deleteMemoGroup(id);
    }
    
	
	@ApiOperation("전체삭제")
    @DeleteMapping("/memos-groups")
    public void removeMemoGroups() {
    	memoGroupService.deleteMemoGroups();
    }
    
	
	
	@ApiOperation("건별수정")
    @PutMapping("/memo-groups")
    public void modifyMemoGroups(@Valid @RequestBody UpdateMemoGroups updateMemoGroups) throws Exception {
		memoGroupService.updateMemoGroups(updateMemoGroups);
    }
	
	
	@ApiOperation("단건수정")
    @PutMapping("/memo-groups/{id}")
    public void modifyMemoGroup(@PathVariable String id, @RequestBody UpdateMemoGroup updateMemoGroup) {
    	updateMemoGroup.setId(id);
    	memoGroupService.updateMemoGroup(updateMemoGroup);
    }

}
package com.api.server.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.server.model.memogroup.CreateMemoGroup;
import com.api.server.model.memogroup.MemoGroupResponse;
import com.api.server.model.memogroup.UpdateMemoGroup;
import com.api.server.service.MemoGroupService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class MemoGroupController {

	private final MemoGroupService memoGroupService;
	
    @GetMapping("/memo-groups")
    public List<MemoGroupResponse> selectMemoGroups() {
        return memoGroupService.selectMemoGroups();
    }
    
    @PostMapping("/memo-groups")
    public void createMemoGroup(@RequestBody CreateMemoGroup createMemoGroup) {
    	memoGroupService.createMemoGroup(createMemoGroup);
    }
    
    @DeleteMapping("/memo-groups/{id}")
    public void deleteMemoGroup(@PathVariable String id) {
    	memoGroupService.deleteMemoGroup(id);
    }
    
    @DeleteMapping("/memos-groups")
    public void deleteMemoGroups() {
    	memoGroupService.deleteMemoGroups();
    }
    
    @PutMapping("/memo-groups/{id}")
    public void putMemoGroup(@PathVariable String id, @RequestBody UpdateMemoGroup updateMemoGroup) {
    	updateMemoGroup.setId(id);
    	memoGroupService.updateMemoGroup(updateMemoGroup);
    }
    
    @GetMapping("/memo-groups/check/name/{name}")
    public int checkMemoGroupByName(@PathVariable String name) {
    	return memoGroupService.checkMemoGroupByName(name);
    }
}
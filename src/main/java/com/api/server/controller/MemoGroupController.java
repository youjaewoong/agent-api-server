package com.api.server.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.server.model.memogroup.CreateMemoGroup;
import com.api.server.model.memogroup.DeleteMemoGroups;
import com.api.server.model.memogroup.MemoGroupResponse;
import com.api.server.model.memogroup.SearchMemoGroupRequest;
import com.api.server.model.memogroup.UpdateMemoGroup;
import com.api.server.model.memogroup.UpdateMemoGroups;
import com.api.server.service.MemoGroupService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/advisor")
public class MemoGroupController {

	private final MemoGroupService memoGroupService;
	

	@ApiOperation("조회")
    @GetMapping("memo-groups")
    public List<MemoGroupResponse> getMemoGroups(@NotBlank @RequestParam("agent_id") String agentId) {
    	SearchMemoGroupRequest searchMemoGroupRequest = new SearchMemoGroupRequest();
    	searchMemoGroupRequest.setAgentId(agentId);
        return memoGroupService.selectMemoGroups(searchMemoGroupRequest);
    }
	
    
	@ApiOperation("추가")
    @PostMapping("memo-groups")
    public MemoGroupResponse addMemoGroup(@RequestBody CreateMemoGroup createMemoGroup) throws Exception {
    	return memoGroupService.createMemoGroup(createMemoGroup);
    }
    
	
	@ApiOperation("단건수정")
    @PutMapping("memo-groups/{id}")
    public void editMemoGroup(@PathVariable String id, @RequestBody UpdateMemoGroup updateMemoGroup) {
    	updateMemoGroup.setId(id);
    	memoGroupService.updateMemoGroup(updateMemoGroup);
    }
	
	
	@ApiOperation("건별수정")
    @PutMapping("memo-groups")
    public void editMemoGroups(@Valid @RequestBody UpdateMemoGroups updateMemoGroups) throws Exception {
		memoGroupService.updateMemoGroups(updateMemoGroups);
    }
	
    
	
	@ApiOperation("건별삭제")
	@DeleteMapping("memo-groups")
    public void removeMemoGroups(@Valid @RequestBody DeleteMemoGroups deleteMemoGroups) {
		memoGroupService.deleteMemoGroups(deleteMemoGroups);
    }

}
package com.api.server.controller;

import java.util.HashMap;
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

import com.api.server.model.memo.CreateMemo;
import com.api.server.model.memo.DeleteMemo;
import com.api.server.model.memo.MemosResponse;
import com.api.server.model.memo.SearchMemoRequest;
import com.api.server.model.memo.UpdateMemo;
import com.api.server.service.MemoService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/advisor")
public class MemoController {

	private final MemoService memoService;
	
	@ApiOperation("조회")
    @GetMapping("memos")
    public List<MemosResponse> selectMemos(@NotBlank @RequestParam("agent_id") String agentId
    		                              ,@RequestParam(value ="group_id", required = false) String groupId) {
		
		SearchMemoRequest searchMemoRequest = new SearchMemoRequest();
		searchMemoRequest.setAgentId(agentId);
		searchMemoRequest.setGroupId(groupId);
        return memoService.selectMemos(searchMemoRequest);
        
    }
	
	
	@ApiOperation("추가")
    @PostMapping("memos")
    public void createMemo(@RequestBody CreateMemo createMemo) {
    	memoService.createMemo(createMemo);
    }
	
    
	@ApiOperation("삭제")
    @DeleteMapping("memos/{id}")
    public void deleteMemo(@Valid @RequestBody DeleteMemo deleteMemo) {
    	memoService.deleteMemo(deleteMemo);
    }
    
    
    @ApiOperation("단건 수정")
    @PutMapping("/memos/{id}")
    public void putMemo(@PathVariable String id, @RequestBody UpdateMemo updateMemo) {
    	updateMemo.setId(id);
    	memoService.updateMemo(updateMemo);
    }
    
    
    @ApiOperation("이동그룹 수정")
    @PutMapping("/memos/move")
    public void putMemoMove(@RequestBody HashMap<String, Object> updateMemo) {
    	memoService.updateMemoToMove(updateMemo);
    }
}
package com.api.server.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.server.model.memo.CreateMemo;
import com.api.server.model.memo.MemoResponse;
import com.api.server.model.memo.SearchMemoRequest;
import com.api.server.model.memo.UpdateMemo;
import com.api.server.service.MemoService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class MemoController {

	private final MemoService memoService;
	
	@ApiOperation("조회")
    @GetMapping("/memos")
    public List<MemoResponse> selectMemos(SearchMemoRequest searchMemoRequest) {
        return memoService.selectMemos(searchMemoRequest);
    }
	
	@ApiOperation("생성")
    @PostMapping("/memos")
    public void createMemo(@RequestBody CreateMemo createMemo) {
    	memoService.createMemo(createMemo);
    }
    
	@ApiOperation("삭제")
    @DeleteMapping("/memos/{id}")
    public void deleteMemo(@PathVariable String id) {
    	memoService.deleteMemo(id);
    }
    
    @DeleteMapping("/memos")
    public void deleteMemos() {
    	memoService.deleteMemos();
    }
    
    @ApiOperation("단건 수정")
    @PutMapping("/memos/{id}")
    public void putMemo(@PathVariable String id, @RequestBody UpdateMemo updateMemo) {
    	updateMemo.setId(id);
    	memoService.updateMemo(updateMemo);
    }
}
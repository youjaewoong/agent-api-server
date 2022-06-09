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
import com.api.server.model.memo.UpdateMemo;
import com.api.server.service.MemoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class MemoController {

	private final MemoService memoService;
	
    @GetMapping("/memos")
    public List<MemoResponse> selectMemos() {
        return memoService.selectMemos();
    }
    
    @PostMapping("/memos")
    public void createMemo(@RequestBody CreateMemo createMemo) {
    	memoService.createMemo(createMemo);
    }
    
    @DeleteMapping("/memos/{id}")
    public void deleteMemo(@PathVariable String id) {
    	memoService.deleteMemo(id);
    }
    
    @DeleteMapping("/memos")
    public void deleteMemos() {
    	memoService.deleteMemos();
    }
    
    @PutMapping("/memos/{id}")
    public void putMemo(@PathVariable String id, @RequestBody UpdateMemo updateMemo) {
    	updateMemo.setId(id);
    	memoService.updateMemo(updateMemo);
    }
}
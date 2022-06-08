package com.api.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.server.model.memo.MemoResponse;
import com.api.server.model.memo.SearchMemoRequest;
import com.api.server.service.MemoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class MemoController {

	private final MemoService memoService;
	
    @GetMapping("/memos")
    public List<MemoResponse> test() {
    	//List<MemoResponse> memoResponse = memoService.selectMemos();
    	try {
    		memoService.selectMemos();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
        return memoService.selectMemos();
    }
}

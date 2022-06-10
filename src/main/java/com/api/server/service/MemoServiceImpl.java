package com.api.server.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.api.server.dao.MemoMapper;
import com.api.server.model.memo.CreateMemo;
import com.api.server.model.memo.MemoResponse;
import com.api.server.model.memo.SearchMemoRequest;
import com.api.server.model.memo.UpdateMemo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemoServiceImpl implements MemoService {
	
	private final MemoMapper memoMapper;

	@Override
	public List<MemoResponse> selectMemos(SearchMemoRequest searchMemoRequest) {
		return memoMapper.selectMemos(searchMemoRequest);
	}

	@Override
	public void updateMemo(UpdateMemo updateMemo) {
		if (updateMemo.getContents().length() > 10) {
			updateMemo.setTitle(updateMemo.getContents().substring(0, 7) + "...");
		} else {
			updateMemo.setTitle(updateMemo.getContents());
		}
		memoMapper.updateMemo(updateMemo);
	}

	@Override
	public void createMemo(CreateMemo createMemo) {
		createMemo.setCreateUserId(createMemo.getUserId());
		createMemo.setUpdateUserId(createMemo.getUserId());
		
		if (createMemo.getContents().length() > 10) {
			createMemo.setTitle(createMemo.getContents().substring(0, 7) + "...");
		} else {
			createMemo.setTitle(createMemo.getContents());
		}
		
		createMemo.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		memoMapper.createMemo(createMemo);
	}

	@Override
	public void deleteMemo(String id) {
		memoMapper.deleteMemo(id);
	}
	
	@Override
	public void deleteMemos() {
		memoMapper.deleteMemos();
	}
}
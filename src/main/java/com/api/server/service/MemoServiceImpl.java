package com.api.server.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.api.server.dao.MemoMapper;
import com.api.server.model.memo.CreateMemo;
import com.api.server.model.memo.MemoResponse;
import com.api.server.model.memo.UpdateMemo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemoServiceImpl implements MemoService {
	
	private final MemoMapper memoMapper;

	@Override
	public List<MemoResponse> selectMemos() {
		return memoMapper.selectMemos();
	}

	@Override
	public void updateMemo(UpdateMemo updateMemo) {
		updateMemo.setTitle(updateMemo.getContents().substring(0, 7) + "...");
		memoMapper.updateMemo(updateMemo);
	}

	@Override
	public void createMemo(CreateMemo createMemo) {
		createMemo.setTitle(createMemo.getContents().substring(0, 7) + "...");
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
package com.api.server.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api.server.dao.MemoMapper;
import com.api.server.model.memo.CreateMemo;
import com.api.server.model.memo.MemoResponse;
import com.api.server.model.memo.SearchMemoRequest;
import com.api.server.model.memo.UpdateMemo;
import com.api.server.model.memogroup.MemoGroupResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemoServiceImpl implements MemoService {
	
	private final MemoMapper memoMapper;
	private final MemoGroupService memoGroupService;


	@Override
	public List<MemoResponse> selectMemos(SearchMemoRequest searchMemoRequest) {
		
		List<MemoResponse> memos = memoMapper.selectMemos(searchMemoRequest);
		List<MemoGroupResponse> memoGroups = memoGroupService.selectMemoGroups();
		if (memoGroups != null) {
			memos.forEach(memo -> {
				memo.setMemoGroups(memoGroups);
			});
		}
		return memos;
	}

	@Override
	public void updateMemo(UpdateMemo updateMemo) {
		updateMemo.setTitle();
		memoMapper.updateMemo(updateMemo);
	}

	@Override
	public void createMemo(CreateMemo createMemo) {
		createMemo.setTitle();
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
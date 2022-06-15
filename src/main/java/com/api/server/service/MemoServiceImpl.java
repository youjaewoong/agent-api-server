package com.api.server.service;

import java.util.List;

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
	private final MemoGroupService memoGroupService;


	@Override
	public List<MemoResponse> selectMemos(SearchMemoRequest searchMemoRequest) {
		return memoMapper.selectMemos(searchMemoRequest);
	}

	@Override
	public void updateMemo(UpdateMemo updateMemo) {
		updateMemo.setTitle();
		memoMapper.updateMemo(updateMemo);
	}

	@Override
	public void createMemo(CreateMemo createMemo) {
		
		//매뉴그룹이 없을 경우 신규 생성 및 고유ID 셋팅
		 String createId = memoGroupService.createBasicMemoGroup(createMemo);
		if (createId != null) {
			createMemo.setGroupId(createId);
		}

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
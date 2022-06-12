package com.api.server.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.api.server.dao.MemoGroupMapper;
import com.api.server.dao.MemoMapper;
import com.api.server.model.memo.CreateMemo;
import com.api.server.model.memo.MemoResponse;
import com.api.server.model.memo.SearchMemoRequest;
import com.api.server.model.memo.UpdateMemo;
import com.api.server.model.memogroup.CreateMemoGroup;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemoServiceImpl implements MemoService {
	
	private final MemoMapper memoMapper;
	private final MemoGroupMapper memoGroupMapper;


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
		
		if (createMemo.getContents().length() > 10) {
			createMemo.setTitle(createMemo.getContents().substring(0, 7) + "...");
		} else {
			createMemo.setTitle(createMemo.getContents());
		}
		
		//매뉴그룹이 없을 경우 신규 생성
		if (memoGroupMapper.selectMemoGroups().size() == 0) {
			CreateMemoGroup createMemoGroup = new CreateMemoGroup();
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");
			createMemoGroup.setUserId(createMemo.getUserId());
			createMemoGroup.setName("기본");
			createMemoGroup.setId(uuid);
			memoGroupMapper.createMemoGroup(createMemoGroup);
			
			createMemo.setGroupId(uuid);
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
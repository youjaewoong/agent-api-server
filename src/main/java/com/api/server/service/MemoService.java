package com.api.server.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.api.server.dao.MemoGroupMapper;
import com.api.server.dao.MemoMapper;
import com.api.server.model.memo.CreateMemo;
import com.api.server.model.memo.DeleteMemo;
import com.api.server.model.memo.MemoResponse;
import com.api.server.model.memo.MemosResponse;
import com.api.server.model.memo.SearchMemoRequest;
import com.api.server.model.memo.UpdateMemo;
import com.api.server.model.memogroup.MemoGroupResponse;
import com.api.server.model.memogroup.SearchMemoGroupRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemoService {
	
	private final MemoMapper memoMapper;
	private final MemoGroupMapper memoGroupMapper;


	public List<MemosResponse> selectMemos(SearchMemoRequest searchMemoRequest) {
		
		List<MemoResponse> memos = memoMapper.selectMemos(searchMemoRequest);
		
		//그룹 셋팅
		SearchMemoGroupRequest searchMemoGroupRequest = new SearchMemoGroupRequest();
		searchMemoGroupRequest.setAgentId(searchMemoRequest.getAgentId());
		List<MemoGroupResponse> memoGroups = memoGroupMapper.selectMemoGroups(searchMemoGroupRequest);
		for (MemoResponse memo : memos) {
			memo.setMemoGroups(memoGroups);
		}
		
		//중복그룹제거
		Set<String> checkdIds = new HashSet<String>();
		List<MemoResponse> groups = new ArrayList<>();
		for (MemoResponse memo : memos) {
			if (!checkdIds.contains(memo.getGroupId())) {
				groups.add(memo);
			}
			checkdIds.add(memo.getGroupId());
		}

		//그룹핑처리
		List<MemosResponse> memoByGroups = new ArrayList<MemosResponse>();
		for (MemoResponse group : groups) {
			MemosResponse memoByGroup = new MemosResponse();
			memoByGroup.setId(group.getGroupId());
			memoByGroup.setTitle(group.getGroupTitle());
			
			for (MemoResponse memo : memos) {
				if (group.getGroupId().equals(memo.getGroupId())) {
					memoByGroup.getMemos().add(memo);
				}
			}
			memoByGroups.add(memoByGroup);
		}
		
		return memoByGroups;
	}

	
	public List<MemoResponse> selectViewMoreMemos(SearchMemoRequest searchMemoRequest) {
		List<MemoResponse> memos = memoMapper.selectViewMoreMemos(searchMemoRequest);
		
		//그룹 셋팅
		SearchMemoGroupRequest searchMemoGroupRequest = new SearchMemoGroupRequest();
		searchMemoGroupRequest.setAgentId(searchMemoRequest.getAgentId());
		List<MemoGroupResponse> memoGroups = memoGroupMapper.selectMemoGroups(searchMemoGroupRequest);
		if (memoGroups != null) {
			for (MemoResponse memo : memos) {
				memo.setMemoGroups(memoGroups);
			}
		}
		return memos;
	}
	
	
	public void updateMemo(UpdateMemo updateMemo) {
		updateMemo.setTitle();
		memoMapper.updateMemo(updateMemo);
	}
	
	
	public void createMemo(CreateMemo createMemo) {
		createMemo.setTitle();
		memoMapper.createMemo(createMemo);
	}
	

	public void deleteMemo(DeleteMemo deleteMemo) {
		memoMapper.deleteMemo(deleteMemo);
	}

	
}
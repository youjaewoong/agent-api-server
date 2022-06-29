package com.api.server.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api.server.dao.MemoMapper;
import com.api.server.model.memo.CreateMemo;
import com.api.server.model.memo.DeleteMemo;
import com.api.server.model.memo.MemoResponse;
import com.api.server.model.memo.SearchMemoRequest;
import com.api.server.model.memo.UpdateMemo;
import com.api.server.model.memogroup.MemoGroupResponse;
import com.api.server.model.memogroup.SearchMemoGroupRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemoServiceImpl implements MemoService {
	
	private final MemoMapper memoMapper;
	private final MemoGroupService memoGroupMapper;


	@Override
	public List<MemoResponse> selectMemos(SearchMemoRequest searchMemoRequest) {
		
		List<MemoResponse> memos = memoMapper.selectMemos(searchMemoRequest);
		
		SearchMemoGroupRequest searchMemoGroupRequest = new SearchMemoGroupRequest();
		searchMemoGroupRequest.setAgentId(searchMemoRequest.getAgentId());
		List<MemoGroupResponse> memoGroups = memoGroupMapper.selectMemoGroups(searchMemoGroupRequest);
		
		if (memoGroups != null) {
			
			//기본그룹ID추출
			String basicGroupId = memoGroups.stream()
					.filter(group -> group.getBasicGroupYn().equals("Y"))
					.map(group -> {
						return group.getId();
					})
					.findAny().get();
			
			memos.forEach(memo -> {
				
				if (memo.getGroupId() == null) {
					memo.setGroupId(basicGroupId);
				}
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
	public void deleteMemo(DeleteMemo deleteMemo) {
		memoMapper.deleteMemo(deleteMemo);
	}
	
}
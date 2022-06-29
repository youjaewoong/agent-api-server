package com.api.server.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.api.server.dao.MemoGroupMapper;
import com.api.server.dao.MemoMapper;
import com.api.server.model.memo.DeleteMemos;
import com.api.server.model.memogroup.CreateMemoGroup;
import com.api.server.model.memogroup.DeleteMemoGroups;
import com.api.server.model.memogroup.MemoGroupResponse;
import com.api.server.model.memogroup.SearchMemoGroupRequest;
import com.api.server.model.memogroup.UpdateMemoGroup;
import com.api.server.model.memogroup.UpdateMemoGroups;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemoGroupServiceImpl implements MemoGroupService {
	
	private final MemoGroupMapper memoGroupMapper;
	private final MemoMapper memoMapper;
	
	
	@Override
	public List<MemoGroupResponse> selectMemoGroups(SearchMemoGroupRequest searchMemoGroupRequest) {
		return memoGroupMapper.selectMemoGroups(searchMemoGroupRequest);
	}
	

	@Override
	public void updateMemoGroup(UpdateMemoGroup updateMemoGroup) {
		memoGroupMapper.updateMemoGroup(updateMemoGroup);
	}
	
	
	@Override
	public void updateMemoGroups(UpdateMemoGroups updateMemoGroups) throws Exception {
        int groupsSize = updateMemoGroups.getGroups().size();
        long deduplicationSize = updateMemoGroups.getGroups().stream()
        		.map(group -> {
        				return group.getTitle();
        			})
        		.distinct().count();
        if (groupsSize != deduplicationSize) {
            throw new Exception();
        }
		
        updateMemoGroups.getGroups().forEach(group -> {
        	memoGroupMapper.updateMemoGroup(group);
		});
		
	}

	
	@Override
	public MemoGroupResponse createMemoGroup(CreateMemoGroup createMemoGroup) throws Exception {
		
		this.checkMemoGroupTitle(createMemoGroup.getTitle());
		
		memoGroupMapper.createMemoGroup(createMemoGroup);
		MemoGroupResponse memoGroupResponse = new MemoGroupResponse();
		BeanUtils.copyProperties(createMemoGroup, memoGroupResponse);
		return memoGroupResponse;
	}

	
	@Override
	public void deleteMemoGroups(DeleteMemoGroups deleteMemoGroups) {
		memoGroupMapper.deleteMemoGroups(deleteMemoGroups);
		
		DeleteMemos deleteMemos = 
				new ObjectMapper().convertValue(deleteMemoGroups, DeleteMemos.class);
		memoMapper.deleteMemoByGroups(deleteMemos);
	}

	
	@Override
	public void checkMemoGroupTitle(String title) throws Exception {
		int foundCnt = memoGroupMapper.checkMemoGroupTitle(title);
		if (foundCnt > 0) {
			throw new Exception();
		}
	}
	
}
package com.api.server.service;

import java.util.ArrayList;
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
public class MemoGroupService {
	
	private final MemoGroupMapper memoGroupMapper;
	private final MemoMapper memoMapper;
	
	
	public List<MemoGroupResponse> selectMemoGroups(SearchMemoGroupRequest searchMemoGroupRequest) {
		return memoGroupMapper.selectMemoGroups(searchMemoGroupRequest);
	}
	

	public void updateMemoGroup(UpdateMemoGroup updateMemoGroup) {
		memoGroupMapper.updateMemoGroup(updateMemoGroup);
	}
	
	
	public void updateMemoGroups(UpdateMemoGroups updateMemoGroups) throws Exception {
        
		//중복체크
		int groupsSize = updateMemoGroups.getGroups().size();
		ArrayList<String> titls = new ArrayList<>();
		for (UpdateMemoGroup groups : updateMemoGroups.getGroups()) {
			if (!titls.contains(groups.getTitle())) {
				titls.add(groups.getTitle());
			}
		}
        if (groupsSize != titls.size()) {
            throw new Exception();
        }
        
        for (UpdateMemoGroup group: updateMemoGroups.getGroups()) {
        	memoGroupMapper.updateMemoGroup(group);
        }
	}

	
	public MemoGroupResponse createMemoGroup(CreateMemoGroup createMemoGroup) throws Exception {
		
		this.checkMemoGroupTitle(createMemoGroup.getTitle());
		
		memoGroupMapper.createMemoGroup(createMemoGroup);
		MemoGroupResponse memoGroupResponse = new MemoGroupResponse();
		BeanUtils.copyProperties(createMemoGroup, memoGroupResponse);
		return memoGroupResponse;
	}

	
	public void deleteMemoGroups(DeleteMemoGroups deleteMemoGroups) {
		memoGroupMapper.deleteMemoGroups(deleteMemoGroups);
		
		DeleteMemos deleteMemos = 
				new ObjectMapper().convertValue(deleteMemoGroups, DeleteMemos.class);
		memoMapper.deleteMemoByGroups(deleteMemos);
	}

	
	public void checkMemoGroupTitle(String title) throws Exception {
		int foundCnt = memoGroupMapper.checkMemoGroupTitle(title);
		if (foundCnt > 0) {
			throw new Exception();
		}
	}
	
}
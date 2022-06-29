package com.api.server.service;

import java.util.List;

import com.api.server.model.memogroup.CreateMemoGroup;
import com.api.server.model.memogroup.DeleteMemoGroups;
import com.api.server.model.memogroup.MemoGroupResponse;
import com.api.server.model.memogroup.SearchMemoGroupRequest;
import com.api.server.model.memogroup.UpdateMemoGroup;
import com.api.server.model.memogroup.UpdateMemoGroups;

public interface MemoGroupService {
	
	public List<MemoGroupResponse> selectMemoGroups(SearchMemoGroupRequest searchMemoGroupRequest);

	public void updateMemoGroup(UpdateMemoGroup updateMemoGroup);
	
	public MemoGroupResponse createMemoGroup(CreateMemoGroup createMemoGroup) throws Exception;
	
	public void deleteMemoGroups(DeleteMemoGroups deleteMemoGroups);

	public void checkMemoGroupTitle(String title) throws Exception;

	public void updateMemoGroups(UpdateMemoGroups updateMemoGroups) throws Exception;


}
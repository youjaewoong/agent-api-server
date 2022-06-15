package com.api.server.service;

import java.util.List;

import com.api.server.model.memogroup.CreateMemoGroup;
import com.api.server.model.memogroup.MemoGroupResponse;
import com.api.server.model.memogroup.SearchMemoGroupRequest;
import com.api.server.model.memogroup.UpdateMemoGroup;

public interface MemoGroupService {
	
	public List<MemoGroupResponse> selectMemoGroups();

	public List<MemoGroupResponse> selectMemoGroups(SearchMemoGroupRequest searchMemoGroupRequest);

	public void updateMemoGroup(UpdateMemoGroup updateMemoGroup);
	
	public void createMemoGroup(CreateMemoGroup createMemoGroup);
	
	<T> String createBasicMemoGroup(T obj);

	public void deleteMemoGroup(String id);

	public void deleteMemoGroups();

	public int checkMemoGroupByName(String name);

}
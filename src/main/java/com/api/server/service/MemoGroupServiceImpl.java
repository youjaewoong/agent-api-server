package com.api.server.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api.server.dao.MemoGroupMapper;
import com.api.server.model.memogroup.CreateMemoGroup;
import com.api.server.model.memogroup.MemoGroupResponse;
import com.api.server.model.memogroup.SearchMemoGroupRequest;
import com.api.server.model.memogroup.UpdateMemoGroup;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemoGroupServiceImpl implements MemoGroupService {
	
	private final MemoGroupMapper memoGroupMapper;

	@Override
	public List<MemoGroupResponse> selectMemoGroups() {
		return memoGroupMapper.selectMemoGroups();
	}
	
	@Override
	public List<MemoGroupResponse> selectMemoGroups(SearchMemoGroupRequest searchMemoGroupRequest) {
		return memoGroupMapper.selectMemoGroups(searchMemoGroupRequest);
	}

	@Override
	public void updateMemoGroup(UpdateMemoGroup updateMemoGroup) {
		memoGroupMapper.updateMemoGroup(updateMemoGroup);
	}

	@Override
	public void createMemoGroup(CreateMemoGroup createMemoGroup) {
		
		//매뉴그룹이 없을 경우 기본 생성
		this.createBasicMemoGroup(createMemoGroup.getUserId());
		memoGroupMapper.createMemoGroup(createMemoGroup);
	}


	@Override
	public void deleteMemoGroup(String id) {
		memoGroupMapper.deleteMemoGroup(id);
	}

	@Override
	public void deleteMemoGroups() {
		memoGroupMapper.deleteMemoGroups();
	}

	@Override
	public int checkMemoGroupByName(String name) {
		return memoGroupMapper.checkMemoGroupByName(name);
	}

	@Override
	public <T> String createBasicMemoGroup(T obj) {
		
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		SearchMemoGroupRequest searchMemoGroupRequest = 
				objectMapper.convertValue(obj, SearchMemoGroupRequest.class);
		
		if (memoGroupMapper.selectMemoGroups(searchMemoGroupRequest).size() == 0) {
			CreateMemoGroup basicMemoGroup = new CreateMemoGroup();
			basicMemoGroup.setUserId(searchMemoGroupRequest.getUserId());
			basicMemoGroup.setName("기본메모");
			memoGroupMapper.createMemoGroup(basicMemoGroup);
			return basicMemoGroup.getId();
		}
		return null;
	}
}
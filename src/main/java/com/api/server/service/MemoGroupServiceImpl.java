package com.api.server.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.api.server.dao.MemoGroupMapper;
import com.api.server.model.memogroup.CreateMemoGroup;
import com.api.server.model.memogroup.MemoGroupResponse;
import com.api.server.model.memogroup.UpdateMemoGroup;

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
	public void updateMemoGroup(UpdateMemoGroup updateMemoGroup) {
		memoGroupMapper.updateMemoGroup(updateMemoGroup);
	}

	@Override
	public void createMemoGroup(CreateMemoGroup createMemoGroup) {
		
		//매뉴그룹이 없을 경우 신규 생성
		if (memoGroupMapper.selectMemoGroups().size() == 0) {
			CreateMemoGroup basicMemoGroup = new CreateMemoGroup();
			basicMemoGroup.setUserId(createMemoGroup.getUserId());
			basicMemoGroup.setName("기본");
			basicMemoGroup.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			memoGroupMapper.createMemoGroup(basicMemoGroup);
		}
		
		createMemoGroup.setId(UUID.randomUUID().toString().replaceAll("-", ""));
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
}
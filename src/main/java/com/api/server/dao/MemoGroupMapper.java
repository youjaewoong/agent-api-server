package com.api.server.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.api.server.model.memogroup.CreateMemoGroup;
import com.api.server.model.memogroup.DeleteMemoGroups;
import com.api.server.model.memogroup.MemoGroupResponse;
import com.api.server.model.memogroup.SearchMemoGroupRequest;
import com.api.server.model.memogroup.UpdateMemoGroup;

@Repository
@Mapper
public interface MemoGroupMapper {

	List<MemoGroupResponse> selectMemoGroups();

	List<MemoGroupResponse> selectMemoGroups(SearchMemoGroupRequest searchMemoGroupRequest);

	int createMemoGroup(CreateMemoGroup createMemo);
    
    int updateMemoGroup(UpdateMemoGroup updateMemo);
	
	int checkMemoGroupTitle(String name);

	int deleteMemoGroup(String id);

	int deleteMemoGroups(DeleteMemoGroups deleteMemoGroups);

}
package com.api.server.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.api.server.model.memo.MemoResponse;

@Repository
@Mapper
public interface MemoDAO {

	List<MemoResponse> testMemos();

	/*
	List<MemoResponse> selectByMemoId(SearchMemoRequest searchMemoReq);
    
    int insertMemos(CreateMemo createMemo);
    
    int deleteMemos(String id);
    
    int updateMemos(UpdateMemo updateMemo);
    */
}
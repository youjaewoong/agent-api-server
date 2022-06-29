package com.api.server.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.api.server.model.memo.CreateMemo;
import com.api.server.model.memo.DeleteMemo;
import com.api.server.model.memo.DeleteMemos;
import com.api.server.model.memo.MemoResponse;
import com.api.server.model.memo.SearchMemoRequest;
import com.api.server.model.memo.UpdateMemo;

@Repository
@Mapper
public interface MemoMapper {

	List<MemoResponse> selectMemos(SearchMemoRequest searchMemoRequest);

    int createMemo(CreateMemo createMemo);
    
    int updateMemo(UpdateMemo updateMemo);
    
    int deleteMemo(DeleteMemo deleteMemo);

	int deleteMemoByGroups(DeleteMemos deleteMemos);

}
package com.api.server.service;

import java.util.List;

import com.api.server.model.memo.CreateMemo;
import com.api.server.model.memo.DeleteMemo;
import com.api.server.model.memo.MemoResponse;
import com.api.server.model.memo.MemosResponse;
import com.api.server.model.memo.SearchMemoRequest;
import com.api.server.model.memo.UpdateMemo;

public interface MemoService {
	
	public List<MemosResponse> selectMemos(SearchMemoRequest searchMemoRequest);

	public void updateMemo(UpdateMemo updateMemo);
	
	public void createMemo(CreateMemo createMemo);
	
	public void deleteMemo(DeleteMemo deleteMemo);

	public List<MemoResponse> selectViewMoreMemos(SearchMemoRequest searchMemoRequest);

}
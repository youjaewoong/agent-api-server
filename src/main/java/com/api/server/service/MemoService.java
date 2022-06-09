package com.api.server.service;

import java.util.List;

import com.api.server.model.memo.CreateMemo;
import com.api.server.model.memo.MemoResponse;
import com.api.server.model.memo.UpdateMemo;

public interface MemoService {
	
	public List<MemoResponse> selectMemos();

	public void updateMemo(UpdateMemo updateMemo);
	
	public void createMemo(CreateMemo createMemo);
	
	public void deleteMemo(String id);

	public void deleteMemos();

}
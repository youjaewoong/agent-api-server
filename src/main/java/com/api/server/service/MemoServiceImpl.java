package com.api.server.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api.server.dao.MemoDAO;
import com.api.server.model.memo.CreateMemo;
import com.api.server.model.memo.DeleteMemo;
import com.api.server.model.memo.MemoResponse;
import com.api.server.model.memo.UpdateMemo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemoServiceImpl implements MemoService {
	
	private final MemoDAO memoMapper;

	@Override
	public List<MemoResponse> selectMemos() {
		return memoMapper.testMemos();
	}

	@Override
	public void updateMemo(UpdateMemo updateMemo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createMemo(CreateMemo createMemo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteMemo(DeleteMemo deleteMemo) {
		// TODO Auto-generated method stub
		
	}


}
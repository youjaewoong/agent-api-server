package com.api.server.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.api.server.dao.MemoMapper;
import com.api.server.model.memo.CreateMemo;
import com.api.server.model.memo.DeleteMemo;
import com.api.server.model.memo.MemoResponse;
import com.api.server.model.memo.MemosResponse;
import com.api.server.model.memo.SearchMemoRequest;
import com.api.server.model.memo.UpdateMemo;
import com.api.server.model.memogroup.MemoGroupResponse;
import com.api.server.model.memogroup.SearchMemoGroupRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemoServiceImpl implements MemoService {
	
	private final MemoMapper memoMapper;
	private final MemoGroupService memoGroupMapper;


	@Override
	public List<MemosResponse> selectMemos(SearchMemoRequest searchMemoRequest) {
		
		List<MemoResponse> memos = memoMapper.selectMemos(searchMemoRequest);
		
		//그룹 셋팅
		SearchMemoGroupRequest searchMemoGroupRequest = new SearchMemoGroupRequest();
		searchMemoGroupRequest.setAgentId(searchMemoRequest.getAgentId());
		List<MemoGroupResponse> memoGroups = memoGroupMapper.selectMemoGroups(searchMemoGroupRequest);
		if (memoGroups != null) {
			memos.forEach(memo -> {
				memo.setMemoGroups(memoGroups);
			});
		}
		
		//중복그룹처리
		List<MemoResponse> groupIds = memos.stream()
				.filter(distinctByKey(v -> v.getGroupId()))
				.collect(Collectors.toList());
		
		//그룹핑처리
		List<MemosResponse> memoByGroups = new ArrayList<MemosResponse>();
		groupIds.forEach(group -> {
			MemosResponse memoByGroup = new MemosResponse();
			memoByGroup.setId(group.getGroupId());
			memoByGroup.setTitle(group.getGroupTitle());
			
			memos.forEach(memo -> {
				if (group.getGroupId().equals(memo.getGroupId())) {
					memoByGroup.getMemos().add(memo);
				}
			});
			memoByGroups.add(memoByGroup);
		});
	
		//더보기처리
		memoByGroups.forEach(group -> {
			int total = group.getMemos().size();
			int limit = searchMemoRequest.getLimit();
			if (total > limit) {
				group.getMore().setIsmore(true);
			}
			group.getMore().setTotal(total);
			group.getMore().setLimit(limit);
			
			group.setMemos(group.getMemos().stream()
					.limit(limit)
					.toList());
			
		});
		
		return memoByGroups;
	}

	
	@Override
	public List<MemoResponse> selectViewMoreMemos(SearchMemoRequest searchMemoRequest) {
		List<MemoResponse> memos = memoMapper.selectViewMoreMemos(searchMemoRequest);
		
		//그룹 셋팅
		SearchMemoGroupRequest searchMemoGroupRequest = new SearchMemoGroupRequest();
		searchMemoGroupRequest.setAgentId(searchMemoRequest.getAgentId());
		List<MemoGroupResponse> memoGroups = memoGroupMapper.selectMemoGroups(searchMemoGroupRequest);
		if (memoGroups != null) {
			memos.forEach(memo -> {
				memo.setMemoGroups(memoGroups);
			});
		}
		return memos;
	}
	
	
	@Override
	public void updateMemo(UpdateMemo updateMemo) {
		updateMemo.setTitle();
		memoMapper.updateMemo(updateMemo);
	}
	

	@Override
	public void createMemo(CreateMemo createMemo) {
		createMemo.setTitle();
		memoMapper.createMemo(createMemo);
	}
	

	@Override
	public void deleteMemo(DeleteMemo deleteMemo) {
		memoMapper.deleteMemo(deleteMemo);
	}
	
	
	/**
	 * 특정 키로 중복제거
	 *
	 * @param keyExtractor
	 * @param <T>
	 * @return
	 */
	private static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
		Map<Object, Boolean> map = new HashMap<>();
		return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}
	
}
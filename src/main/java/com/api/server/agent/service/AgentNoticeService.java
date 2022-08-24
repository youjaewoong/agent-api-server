package com.api.server.agent.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.server.agent.dao.AgentNoticeMapper;
import com.api.server.agent.model.notice.AgentNoticeCategory;
import com.api.server.agent.model.notice.AgentNoticeCategory.Type;
import com.api.server.agent.model.notice.AgentNoticeCategoryResponse;
import com.api.server.agent.model.notice.AgentNoticeResponse;
import com.api.server.agent.model.notice.CreateAgentNotice;
import com.api.server.agent.model.notice.SearchAgentNoticeRequest;
import com.api.server.agent.model.notice.UpdateAgentNotice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AgentNoticeService {
	
	@Autowired
	private final AgentNoticeMapper agentNoticeMapper;
	
	
	public List<AgentNoticeCategoryResponse> selectAgentNoticesAll(SearchAgentNoticeRequest searchAgentNoticeRequest) {
		
		List<AgentNoticeCategory> categories = agentNoticeMapper.countAgentNoticeByCategories(searchAgentNoticeRequest);
		List<AgentNoticeCategoryResponse> response = new ArrayList<>();
		
		List<String> empryCategories = new ArrayList<>(); 
		for (Type type : AgentNoticeCategory.Type.values()) {
			empryCategories.add(type.toString());
		}
		
		for (AgentNoticeCategory category : categories) {
			
			//카테고리 별 검색조건
			searchAgentNoticeRequest.setCategory(category.getCategory());
			List<AgentNoticeResponse> notices = agentNoticeMapper.selectAgentNotices(searchAgentNoticeRequest);
			
			//total 사이즈가 크면 무한스크롤 기능 활성화
			if (category.getTotal() > notices.size()) {
				category.setLoading(true);
			}
			
			AgentNoticeCategoryResponse categoryResponse = new AgentNoticeCategoryResponse();
			categoryResponse.setCategory(category);
			categoryResponse.setNotices(notices);
			
			response.add(categoryResponse);
			
			empryCategories.remove(category.getCategory());
		}
		
		//비어있는 카테고리 처리
		for (String empryCategory : empryCategories) {
			
			AgentNoticeCategory category = new AgentNoticeCategory();
			category.setCategory(empryCategory);

			AgentNoticeCategoryResponse categoryResponse = new AgentNoticeCategoryResponse();
			categoryResponse.setCategory(category);
			response.add(categoryResponse);
		}
		
		return response;
	}
	
	public AgentNoticeCategoryResponse selectAgentPageNotices(SearchAgentNoticeRequest searchAgentNoticeRequest) {
		
		List<AgentNoticeResponse> notices = agentNoticeMapper.selectAgentNotices(searchAgentNoticeRequest);
		int total = agentNoticeMapper.countAgentNotice(searchAgentNoticeRequest);
		
		AgentNoticeCategory category = new AgentNoticeCategory();
		category.setTotal(total);
		category.setCategory(searchAgentNoticeRequest.getCategory());
		
		//total 사이즈가 크면 무한스크롤 기능 활성화
		if (category.getTotal() > notices.size()) {
			category.setLoading(true);
		}
		
		AgentNoticeCategoryResponse categoryResponse = new AgentNoticeCategoryResponse();
		categoryResponse.setCategory(category);
		categoryResponse.setNotices(notices);
		
		return categoryResponse;
	}
	
	
	/**
	 * 관리자화면에서 재공지 시 상담사ID 리스트 
	 * @param id
	 * @return
	 */
	public List<String> selectReNoticeTargetAgentIds(String id) {
		return agentNoticeMapper.selectReNoticeTargetAgentIds(id);
	}

	
	/**
	 * 상담사 공지데이터 하루전 데이터가 없을수 있기떄문에 리마인드 처리를 위한 데이터 삽입
	 * @param updateAgentNotice
	 * @return 
	 */
	@Transactional
	public String updateAgentNotice(UpdateAgentNotice updateAgentNotice) {
		
		if ("null".equals(updateAgentNotice.getId()) || updateAgentNotice.getId() == null) {
			CreateAgentNotice createAgentNotice = new CreateAgentNotice();
			String id = createAgentNotice.getId();
			
			BeanUtils.copyProperties(updateAgentNotice, createAgentNotice);
			
			createAgentNotice.setId(id);
			
			this.createAgentNotice(createAgentNotice);
			return createAgentNotice.getId();
		} 
		agentNoticeMapper.updateAgentNotice(updateAgentNotice);
		return updateAgentNotice.getId();
	}
	
	
	public void createAgentNotice(CreateAgentNotice createAgentNotice) {
		agentNoticeMapper.createAgentNotice(createAgentNotice);
	}
	
	
	/**
	 * 상담사별 읽지않은 공지 갯수
	 * @param searchAgentNoticeRequest
	 * @return
	 */
	public int countAgentNoticeUnConfirmed(SearchAgentNoticeRequest searchAgentNoticeRequest) {
		return agentNoticeMapper.countAgentNoticeUnConfirmed(searchAgentNoticeRequest);
	}
	
	
	/**
	 * 하루전 데이터 일괄삭제
	 */
	public void deleteAgentNoticeByDay(int day) {
		int deleteCnt = agentNoticeMapper.deleteAgentNoticeByDay(day);
		log.info("하루전 데이터 일괄삭제 {} 건", deleteCnt);
	}
	
	
	/**
	 * 리마인드 설정 Y 데이터  
	 * @param id
	 * @return
	 */
	public List<AgentNoticeResponse> selectAgentNoticeByAlarm(SearchAgentNoticeRequest searchAgentNoticeRequest) {
		return agentNoticeMapper.selectAgentNoticeByAlarm(searchAgentNoticeRequest);
	}
}
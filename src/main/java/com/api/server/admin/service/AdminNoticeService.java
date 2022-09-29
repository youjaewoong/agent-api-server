package com.api.server.admin.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.api.server.admin.dao.AdminNoticeMapper;
import com.api.server.admin.model.admin.AdminDeptResponse;
import com.api.server.admin.model.notice.AdminNoticeCategory;
import com.api.server.admin.model.notice.AdminNoticeCategory.Type;
import com.api.server.admin.model.notice.AdminNoticeCategoryResponse;
import com.api.server.admin.model.notice.AdminNoticeResponse;
import com.api.server.admin.model.notice.CreateAdminNotice;
import com.api.server.admin.model.notice.DeleteAdminNotice;
import com.api.server.admin.model.notice.SearchAdminNoticeRequest;
import com.api.server.admin.model.notice.UpdateAdminNotice;
import com.api.server.agent.dao.AgentNoticeMapper;
import com.api.server.agent.model.notice.CreateAgentNotice;
import com.api.server.agent.model.notice.SearchAgentNoticeRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminNoticeService {
	
	private final AdminNoticeMapper adminNoticeMapper;
	private final AgentNoticeMapper agentNoticeMapper;
	private final RestTemplate restTemplate;
	private static String WEBSOCKET_URL = "http://localhost:8089";

	
	public List<AdminNoticeCategoryResponse> selectAdminNoticesAll(SearchAdminNoticeRequest searchAdminNoticeRequest) {
		
		List<AdminNoticeCategory> categories = adminNoticeMapper.countAdminNoticeByCategories(searchAdminNoticeRequest);
		List<AdminNoticeCategoryResponse> response = new ArrayList<>();
		
		List<String> empryCategories = new ArrayList<>(); 
		for (Type type : AdminNoticeCategory.Type.values()) {
			empryCategories.add(type.toString());
		}
		
		for (AdminNoticeCategory category : categories) {
			
			//카테고리 별 검색조건
			searchAdminNoticeRequest.setCategory(category.getCategory());
			List<AdminNoticeResponse> notices = adminNoticeMapper.selectAdminNotices(searchAdminNoticeRequest);
			
			//total 사이즈가 크면 무한스크롤 기능 활성화
			if (category.getTotal() > notices.size()) {
				category.setLoading(true);
			}
			
			AdminNoticeCategoryResponse categoryResponse = new AdminNoticeCategoryResponse();
			categoryResponse.setCategory(category);
			categoryResponse.setNotices(notices);
			
			response.add(categoryResponse);
			
			empryCategories.remove(category.getCategory());
		}
		
		//비어있는 카테고리 처리
		for (String empryCategory : empryCategories) {
			
			AdminNoticeCategory category = new AdminNoticeCategory();
			category.setCategory(empryCategory);

			AdminNoticeCategoryResponse categoryResponse = new AdminNoticeCategoryResponse();
			categoryResponse.setCategory(category);
			response.add(categoryResponse);
		}
		
		return response;
	}
	
	public AdminNoticeCategoryResponse selectAdminPageNotices(SearchAdminNoticeRequest searchAdminNoticeRequest) {
		
		List<AdminNoticeResponse> notices = adminNoticeMapper.selectAdminNotices(searchAdminNoticeRequest);
		int total = adminNoticeMapper.countAdminNotice(searchAdminNoticeRequest);
		
		AdminNoticeCategory category = new AdminNoticeCategory();
		category.setTotal(total);
		category.setCategory(searchAdminNoticeRequest.getCategory());
		
		//total 사이즈가 크면 무한스크롤 기능 활성화
		if (category.getTotal() > notices.size()) {
			category.setLoading(true);
		}
		
		AdminNoticeCategoryResponse categoryResponse = new AdminNoticeCategoryResponse();
		categoryResponse.setCategory(category);
		categoryResponse.setNotices(notices);
		
		return categoryResponse;
	}

	
	public void updateAdminNotice(UpdateAdminNotice updateAdminNotice) {
		adminNoticeMapper.updateAdminNotice(updateAdminNotice);
		
		//수정을 했을 경우 상담사 정보 삭제
		agentNoticeMapper.deleteAgentNoticeByAdminId(updateAdminNotice.getId());
		
		//대상 상담사 리스트 다시 저장
		SearchAgentNoticeRequest searchAgentNoticeRequest = new SearchAgentNoticeRequest();
		searchAgentNoticeRequest.setCompanyCode(updateAdminNotice.getCompanyCode());
		searchAgentNoticeRequest.setDeptCode(updateAdminNotice.getDeptCode());
		List<String> ids = agentNoticeMapper.selectNoticeTargetAgentIds(searchAgentNoticeRequest);
		
		if (ids.size() > 0) {
			for (String agentId : ids) {
				CreateAgentNotice createAgentNotice = new CreateAgentNotice();
				createAgentNotice.setCompanyCode(updateAdminNotice.getCompanyCode());
				createAgentNotice.setAdminNoticeId(updateAdminNotice.getId());
				createAgentNotice.setAdminId(updateAdminNotice.getAdminId());
				createAgentNotice.setAgentId(agentId);
				agentNoticeMapper.createAgentNotice(createAgentNotice);
			}
		}
	}
	
	
	public String createAdminNotice(CreateAdminNotice createAdminNotice) {
		
		SearchAdminNoticeRequest searchAdminNoticeRequest = new SearchAdminNoticeRequest();
		searchAdminNoticeRequest.setCompanyCode(createAdminNotice.getCompanyCode());
		searchAdminNoticeRequest.setDeptCode(createAdminNotice.getDeptCode());

		//부서 상담사 총합
		int deptTotal = adminNoticeMapper.countAdminNoticeDeptTotal(searchAdminNoticeRequest);
		if (deptTotal > 0) {
			createAdminNotice.setDeptTotal(deptTotal);
		}
		
		adminNoticeMapper.createAdminNotice(createAdminNotice);
		
		//부서에 속한 상담사 데이터 추가
		SearchAgentNoticeRequest searchAgentNoticeRequest = new SearchAgentNoticeRequest();
		searchAgentNoticeRequest.setCompanyCode(createAdminNotice.getCompanyCode());
		searchAgentNoticeRequest.setDeptCode(createAdminNotice.getDeptCode());
		List<String> ids = agentNoticeMapper.selectNoticeTargetAgentIds(searchAgentNoticeRequest);
		
		if (ids.size() > 0) {
			for (String agentId : ids) {
				CreateAgentNotice createAgentNotice = new CreateAgentNotice();
				createAgentNotice.setCompanyCode(createAdminNotice.getCompanyCode());
				createAgentNotice.setAdminNoticeId(createAdminNotice.getId());
				createAgentNotice.setAdminId(createAdminNotice.getAdminId());
				createAgentNotice.setAgentId(agentId);
				agentNoticeMapper.createAgentNotice(createAgentNotice);
			}
		}
		return createAdminNotice.getId();
	}
	

	public void deleteAdminNotice(DeleteAdminNotice deleteAdminNotice) {
		int deleteCnt = adminNoticeMapper.deleteAdminNotice(deleteAdminNotice);
		if (deleteCnt > 0) {
			agentNoticeMapper.deleteAgentNoticeByAdminId(deleteAdminNotice.getId());
		}
	}

	
	/**
	 * api를 통한 공지사항 추가
	 * @param createAdminNotice
	 * @throws JsonProcessingException 
	 */
	public void createAdminApiNotice(CreateAdminNotice createAdminNotice) throws Exception {
		List<Map<String, Object>> targetUsers = adminNoticeMapper.selectAdminNoticeTargetUser();
		if (targetUsers != null) {
			for (Map<String, Object> user : targetUsers) {
				
				String deptCode = user.get("DEPT_CODE").toString();
				createAdminNotice.setId(UUID.randomUUID().toString().replaceAll("-", ""));
				createAdminNotice.setDeptCode(deptCode);
				createAdminNotice.setAdminId(user.get("AGENT_ID").toString());

				List<Map<String, Object>> deptDetails = 
						adminNoticeMapper.selectAdminNoticeDeptDetail(deptCode);
				
				String deptName = null;
				List<String> deptNames = new ArrayList<>();
				deptDetails.forEach(deptDetail -> {
					deptNames.add(deptDetail.get("DEPT_NAME").toString());
					createAdminNotice.setCompanyCode(deptDetail.get("COMPANY_CODE").toString());
				});
				deptName = String.join(" > ", deptNames);
				createAdminNotice.setDeptName(deptName);
				
				// 공지사항 추가 메소드 호출
				String id = this.createAdminNotice(createAdminNotice);
				if (id != null) {
					// 관리자별 구독 전달
					Map<String, Object> message = new HashMap<>();
					message.put("agentType", "SYSTEM");
					
					Map<String, Object> noticeMessage = new HashMap<>();
					noticeMessage.put("topic", "/sub/redis/notice/" + createAdminNotice.getAdminId());
					noticeMessage.put("message", message);
					
					ObjectMapper mapper = new ObjectMapper();
					String jsonMessage = mapper.writeValueAsString(noticeMessage);
					
					HttpHeaders headers = new HttpHeaders();
					headers.setContentType(MediaType.APPLICATION_JSON);
					HttpEntity<String> entity = new HttpEntity<String>(jsonMessage, headers);
					
					restTemplate.postForEntity(WEBSOCKET_URL+"/pubsub/redis/notice", entity, String.class);
				}
				
			};
		} else {
			throw new NullPointerException();
		}
	}

	
}
package com.api.server.admin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.server.admin.dao.AdminNoticeMapper;
import com.api.server.admin.model.notice.AdminNoticeCategory;
import com.api.server.admin.model.notice.AdminNoticeCategory.Type;
import com.api.server.admin.model.notice.AdminNoticeCategoryResponse;
import com.api.server.admin.model.notice.AdminNoticeDeptResponse;
import com.api.server.admin.model.notice.AdminNoticeResponse;
import com.api.server.admin.model.notice.CreateAdminNotice;
import com.api.server.admin.model.notice.DeleteAdminNotice;
import com.api.server.admin.model.notice.SearchAdminNoticeRequest;
import com.api.server.admin.model.notice.UpdateAdminNotice;
import com.api.server.agent.dao.AgentNoticeMapper;
import com.api.server.agent.model.notice.CreateAgentNotice;
import com.api.server.agent.model.notice.SearchAgentNoticeRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminNoticeService {
	
	private final AdminNoticeMapper adminNoticeMapper;
	private final AgentNoticeMapper agentNoticeMapper;

	
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
	}
	
	
	@Transactional
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
	

	public void deleteAdminNotice(DeleteAdminNotice deleteMemo) {
		adminNoticeMapper.deleteAdminNotice(deleteMemo);
	}
	
	
	public List<AdminNoticeDeptResponse> selectAdminNoticeDept(String companyCode) {
		return adminNoticeMapper.selectAdminNoticeDept(companyCode);
	}

	
}
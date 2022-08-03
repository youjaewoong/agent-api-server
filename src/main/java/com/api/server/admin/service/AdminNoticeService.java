package com.api.server.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api.server.admin.dao.AdminNoticeMapper;
import com.api.server.admin.model.notice.AdminNoticeDeptResponse;
import com.api.server.admin.model.notice.AdminNoticeResponse;
import com.api.server.admin.model.notice.CreateAdminNotice;
import com.api.server.admin.model.notice.DeleteAdminNotice;
import com.api.server.admin.model.notice.SearchAdminNoticeRequest;
import com.api.server.admin.model.notice.UpdateAdminNotice;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminNoticeService {
	
	private final AdminNoticeMapper adminNoticeMapper;

	public List<AdminNoticeResponse> selectAdminNotices(SearchAdminNoticeRequest searchAdminNoticeRequest) {
		
		return adminNoticeMapper.selectAdminNotices(searchAdminNoticeRequest);
	}

	
	public void updateAdminNotice(UpdateAdminNotice updateAdminNotice) {
		adminNoticeMapper.updateAdminNotice(updateAdminNotice);
	}
	
	
	public void createAdminNotice(CreateAdminNotice createAdminNotice) {
		
		SearchAdminNoticeRequest searchAdminNoticeRequest = new SearchAdminNoticeRequest();
		searchAdminNoticeRequest.setCompanyCode(createAdminNotice.getCompanyCode());
		searchAdminNoticeRequest.setDeptCode(createAdminNotice.getDeptCode());

		//부서 상담사 총합
		int deptCount = adminNoticeMapper.countAdminNoticeDeptCount(searchAdminNoticeRequest);
		if (deptCount > 0) {
			createAdminNotice.setDeptCount(deptCount);
		}
		
		adminNoticeMapper.createAdminNotice(createAdminNotice);
	}
	

	public void deleteAdminNotice(DeleteAdminNotice deleteMemo) {
		adminNoticeMapper.deleteAdminNotice(deleteMemo);
	}
	
	
	public List<AdminNoticeDeptResponse> selectAdminNoticeDept(String companyCode) {
		return adminNoticeMapper.selectAdminNoticeDept(companyCode);
	}

	
}
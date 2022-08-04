package com.api.server.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.server.admin.dao.AdminNoticeMapper;
import com.api.server.admin.model.notice.AdminNoticeDeptResponse;
import com.api.server.admin.model.notice.AdminNoticeGroupResponse;
import com.api.server.admin.model.notice.CreateAdminNotice;
import com.api.server.admin.model.notice.DeleteAdminNotice;
import com.api.server.admin.model.notice.SearchAdminNoticeRequest;
import com.api.server.admin.model.notice.UpdateAdminNotice;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminNoticeService {
	
	private final AdminNoticeMapper adminNoticeMapper;

	
	public AdminNoticeGroupResponse selectAdminNotices(SearchAdminNoticeRequest searchAdminNoticeRequest) {
		return  new AdminNoticeGroupResponse(adminNoticeMapper.selectAdminNotices(searchAdminNoticeRequest));
	}

	
	public void updateAdminNotice(UpdateAdminNotice updateAdminNotice) {
		adminNoticeMapper.updateAdminNotice(updateAdminNotice);
	}
	
	
	@Transactional
	public void createAdminNotice(CreateAdminNotice createAdminNotice) {
		
		SearchAdminNoticeRequest searchAdminNoticeRequest = new SearchAdminNoticeRequest();
		searchAdminNoticeRequest.setCompanyCode(createAdminNotice.getCompanyCode());
		searchAdminNoticeRequest.setDeptCode(createAdminNotice.getDeptCode());

		//부서 상담사 총합
		int deptTotal = adminNoticeMapper.countAdminNoticeDeptTotal(searchAdminNoticeRequest);
		if (deptTotal > 0) {
			createAdminNotice.setDeptTotal(deptTotal);
		}
		
		adminNoticeMapper.createAdminNotice(createAdminNotice);
		
		
		//해당 상담사들에게 공지 데이터 추가
		//TODO
	}
	

	public void deleteAdminNotice(DeleteAdminNotice deleteMemo) {
		adminNoticeMapper.deleteAdminNotice(deleteMemo);
	}
	
	
	public List<AdminNoticeDeptResponse> selectAdminNoticeDept(String companyCode) {
		return adminNoticeMapper.selectAdminNoticeDept(companyCode);
	}

	
}
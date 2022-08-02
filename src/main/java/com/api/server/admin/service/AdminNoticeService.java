package com.api.server.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api.server.admin.dao.AdminNoticeMapper;
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
		adminNoticeMapper.createAdminNotice(createAdminNotice);
	}
	

	public void deleteAdminNotice(DeleteAdminNotice deleteMemo) {
		adminNoticeMapper.deleteAdminNotice(deleteMemo);
	}

	
}
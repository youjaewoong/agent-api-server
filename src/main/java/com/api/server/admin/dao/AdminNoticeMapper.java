package com.api.server.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.api.server.admin.model.notice.AdminNoticeCategory;
import com.api.server.admin.model.notice.AdminNoticeDeptResponse;
import com.api.server.admin.model.notice.AdminNoticeResponse;
import com.api.server.admin.model.notice.CreateAdminNotice;
import com.api.server.admin.model.notice.DeleteAdminNotice;
import com.api.server.admin.model.notice.SearchAdminNoticeRequest;
import com.api.server.admin.model.notice.UpdateAdminNotice;


@Repository
@Mapper
public interface AdminNoticeMapper {

	public List<AdminNoticeResponse> selectAdminNotices(SearchAdminNoticeRequest searchAdminNoticeRequest);

	public int createAdminNotice(CreateAdminNotice createAdminNotice);
    
	public int updateAdminNotice(UpdateAdminNotice updateAdminNotice);

	public int deleteAdminNotice(DeleteAdminNotice deleteAdminNotice);
	
	public int countAdminNoticeDeptTotal(SearchAdminNoticeRequest searchAdminNoticeRequest);
	
	public List<AdminNoticeDeptResponse> selectAdminNoticeDept(String companyCode);
	
	public List<AdminNoticeCategory> countAdminNoticeByCategories();
	
	public int countAdminNotice(SearchAdminNoticeRequest searchAdminNoticeRequest);
	
}
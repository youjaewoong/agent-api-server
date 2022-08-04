package com.api.server.admin.model.notice;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminNoticeGroupResponse {
	
	public AdminNoticeGroupResponse(List<AdminNoticeResponse> adminNoticeResponse) {
		
		//전체
		this.all.setContents(adminNoticeResponse);
		this.all.setTotal(adminNoticeResponse.size());
		
		List<AdminNoticeResponse> emergency = new ArrayList<>();
		List<AdminNoticeResponse> normal = new ArrayList<>();
		List<AdminNoticeResponse> warning = new ArrayList<>();
		
		//긴급 일반 워닝 등 처리
		for (AdminNoticeResponse notice : adminNoticeResponse) {
			if ("E".equals(notice.getCategory())) {
				emergency.add(notice);
			} else if ("W".equals(notice.getCategory())) {
				warning.add(notice);
			} else {
				normal.add(notice);
			}
		}
		
		//긴급
		this.emergency.setContents(emergency);
		this.emergency.setTotal(emergency.size());
		//일반
		this.normal.setContents(normal);
		this.normal.setTotal(normal.size());
		//워닝
		this.warning.setContents(warning);
		this.warning.setTotal(warning.size());
		
	}
	
	private AdminNoticeGroup all;
	private AdminNoticeGroup emergency;
	private AdminNoticeGroup normal;
	private AdminNoticeGroup warning;
	
	@Getter
	@Setter
	private static class AdminNoticeGroup {
		private int total;
		private List<AdminNoticeResponse> contents;
	}
}

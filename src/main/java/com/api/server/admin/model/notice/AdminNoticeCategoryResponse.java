package com.api.server.admin.model.notice;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminNoticeCategoryResponse {
	private AdminNoticeCategory category;
	private List<AdminNoticeResponse> notices = new ArrayList<>();
}
package com.api.server.admin.model.notice;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminNoticeResponse {
	private String id;
	private String companyCode;
	private String adminId;
	private String title;
	private String content;
	private char category;
	private String categoryName;
	private String deptCode;
	private int confirmCount; //부서에속한 상담사별 읽음 카운트
	private char confirmYn; //전체읽음: Y, 진행중: N 
	private String createdDatetime;
	private String conversionUpdatedDatetime;
}

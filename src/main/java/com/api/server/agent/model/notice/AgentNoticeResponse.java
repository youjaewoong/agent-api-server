package com.api.server.agent.model.notice;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgentNoticeResponse {
	private String id;
	private String agentNoticeId; //DP_ADV_AGENT_NOTICE 테이블의 상담사 고유ID
	private String companyCode;
	private String adminId;
	private String title;
	private String content;
	private String category;
	private String categoryName;
	private char newYn; //신규건 Y, 기존: N 
	private char noticeCofirmYn; //읽음처리 Y, 미처리: N
	private String noticeRemind; //알람시간
	private String noticeRemindTime; //설정시간
	private String noticeRemindYn; //알람확인처리 Y, 미처리: N
	private String createdDatetime;
	private String updatedDatetime;
	private String conversionUpdatedDatetime;
}

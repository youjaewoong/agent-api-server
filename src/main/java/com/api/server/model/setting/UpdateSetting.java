package com.api.server.model.setting;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateSetting {

	@NotBlank
	private String companyCode;
	
	@NotBlank
	private String agentId;
	
	/* 화면설정  */
	private Screen screen;
	
	/* 알림 */
	private Notice notice;
	
	/* 키워드 */
	private Keyword keyword;
	
	/* 외부채널 */
	private Channer channer;
	
	/* 알람 */
	private String alarmSetting;
	
	
	@Getter
	@Setter
	private static class Screen {
		private String callScreenToggle; //콜 대화창
		private String knowledgeToggle; //지식저장소
		private String historyToggle; //상담 이력
		private String fontSize; //글자크기
	}
	
	
	@Getter
	@Setter
	private static class Notice {
		private String noticeToggle; //공지사항
		private String coachingToggle; //코칭
		private String complainToggle = "false"; //민원
	}
	
	
	@Getter
	@Setter
	private static class Keyword {
		private String forbiddenKeyword; //금칙어 키워드
		private String iussueKeyword; //이슈어 키워드
	}
	
	
	@Getter
	@Setter
	private static class Channer {
		private String chatbotToggle; //챗봇
		private String myStartToggle; //마이스타
		private String callTerminalToggle; //콜단말
		private String adminToggle; //통합어드민
		private String callbackToggle; //프리미엄 콜백
	}
	
}
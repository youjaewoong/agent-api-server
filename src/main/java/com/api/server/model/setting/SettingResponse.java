package com.api.server.model.setting;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SettingResponse {
	
	private String id;
	private String companyCode;
	private String agentId;
	private boolean callScreenToggle; //콜대화창 토글
	private boolean knowledgeToggle; //지식저장소 토글
	private boolean historyToggle; //상담이력 토글
	private String fontSize; //글자크기
	private boolean noticeToggle; //공지사항 토글
	private boolean coachingToggle; //코칭 토글
	private boolean chatbotToggle; //챗봇 토글
	private boolean myStartToggle; //마이스타 토글
	private boolean callTerminalToggle; //콜 단말 토글
	private boolean adminToggle; //통합어드민 토글
	private boolean callbackToggle; //프리미엄콜백 토글
	private boolean complainToggle; //민원 토글
	private int alarmSetting; //알람설정
	private String forbiddenKeyword; //금칙어키워드
	private String iussueKeyword; //이슈어키워드
}
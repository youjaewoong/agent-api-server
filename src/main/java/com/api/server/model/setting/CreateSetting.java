package com.api.server.model.setting;

import java.util.UUID;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateSetting {
	
	public CreateSetting() {
		this.id = UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	@JsonIgnore
	private String id;
	
	private String companyCode;
	
	@NotBlank
	private String agentId;
	
	private String callScreenToggle = "true"; //콜 대화창
	private String knowledgeToggle = "true"; //지식저장소
	private String historyToggle = "true"; //상담 이력
	private String fontSize = "default"; //글자크기
	private String noticeToggle = "true"; //공지사항
	private String coachingToggle = "true"; //코칭
	private String complainToggle = "true"; //민원
	private String chatbotToggle = "true"; //챗봇
	private String myStartToggle = "true"; //마이스타
	private String callTerminalToggle = "true"; //콜단말
	private String adminToggle = "true"; //통합어드민
	private String callbackToggle = "true"; //프리미엄 콜백
	
}
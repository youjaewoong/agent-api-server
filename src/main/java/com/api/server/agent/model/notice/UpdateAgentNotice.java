package com.api.server.agent.model.notice;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateAgentNotice {
	
	@JsonIgnore
	private String id;
	
	@JsonProperty("company_code")
	private String companyCode;

	@JsonProperty("admin_notice_id")
	private String adminNoticeId;
	
	@NotBlank
	@JsonProperty("agent_id")
	private String agentId;
	
	@JsonProperty("admin_id")
	private String adminId;
	
	@JsonProperty("notice_remind")
	private String noticeRemind;
	
	@JsonProperty("notice_remind_yn")
	private String noticeRemindYn;

	@JsonProperty("notice_cofirm_yn")
	private String noticeCofirmYn = "N";
	
	@JsonProperty("notice_remind_time")
	private String noticeRemindTime = "0";
	
}
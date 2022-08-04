package com.api.server.agent.model.notice;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateAgentNotice {
	
	@JsonIgnore
	private String id;
	
	@NotBlank
	private String agentId;
	
	@NotBlank
	private char noticeCofirmYn;
	
	private String noticeRemind;
	
}
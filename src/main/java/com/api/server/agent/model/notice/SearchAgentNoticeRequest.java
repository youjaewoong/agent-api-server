package com.api.server.agent.model.notice;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchAgentNoticeRequest {
	
	@JsonProperty("agent_id")
	@NotBlank
	private String agentId;
	
	private String deptCode;
	
	private String companyCode;
}

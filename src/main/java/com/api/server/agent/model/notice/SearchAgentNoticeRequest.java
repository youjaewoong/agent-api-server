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
	
	@JsonProperty("dept_code")
	private String deptCode;
	
	@JsonProperty("company_code")
	private String companyCode;
}

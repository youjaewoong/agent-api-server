package com.api.server.agent.model.notice;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchAgentNoticeRequest {
	
	@NotBlank
	@JsonProperty("agent_id")
	private String agentId;

	@NotBlank
	@JsonProperty("company_code")
	private String companyCode;
	
	@JsonProperty("dept_code")
	private String deptCode;
	
	private String category = "ALL";
	
	private String order;
	
	private String sort;
	
	private int limit = 15;
	
	private int offset = 0;
}

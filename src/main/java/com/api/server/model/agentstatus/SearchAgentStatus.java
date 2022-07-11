package com.api.server.model.agentstatus;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchAgentStatus {
	
	@NotEmpty
	private String agentId;
	@NotEmpty
	private String callId;
}

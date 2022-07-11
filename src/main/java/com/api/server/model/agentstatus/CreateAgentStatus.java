package com.api.server.model.agentstatus;

import java.util.UUID;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAgentStatus {
	
	public CreateAgentStatus() {
		this.id = UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	@JsonIgnore
	private String id;
	@NotEmpty
	@JsonProperty("agent_id")
	private String agentId;
	@NotEmpty
	@JsonProperty("call_id")
	private String callId;
	@NotEmpty
	private String categories;
	@NotEmpty
	private String sentences;
	private String etc;
	private String detail;
}
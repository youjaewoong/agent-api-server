package com.api.server.agent.model.notice;

import java.util.UUID;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAgentNotice {
	
	public CreateAgentNotice() {
		this.id = UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	@JsonIgnore
	private String id;
	
	private String agentId;
	
	@NotBlank
	private String targetId;
		
}
package com.api.server.agent.model.notice;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAgentNotice extends UpdateAgentNotice {
	
	public CreateAgentNotice() {
		this.id = UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	@JsonIgnore
	private String id;
	
	@JsonProperty("notice_cofirm_yn")
	private String noticeCofirmYn = "N";
	
}
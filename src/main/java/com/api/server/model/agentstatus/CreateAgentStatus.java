package com.api.server.model.agentstatus;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAgentStatus {
	
	@NotEmpty
	@JsonProperty("agent_id")
	private String agentId;
	@NotEmpty
	@JsonProperty("rec_key")
	private String recKey;
	
	@JsonProperty("ap_id")
	private String apId;
	
	private List<Map<String, Object>> categories; //상담유형
	private String sentences; //핵심문장추출
	private String ext; //내선번호
	private String memo; //상담특이사항
}
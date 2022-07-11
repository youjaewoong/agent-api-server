package com.api.server.model.agentstatus;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.api.server.model.agentstatus.api.AgentStatusDetail;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRedisAgentStatus {
	
	@NotEmpty
	@JsonProperty("agent_id")
	private String agentId;
	@NotEmpty
	@JsonProperty("call_id")
	private String callId;
	@NotEmpty
	private List<List<CategoryDetail>> categories;
	@NotEmpty
	private List<SentencesDetail> sentences;
	private String ext;
	private String etc;
	private AgentStatusDetail detail;
	
	@Getter
	@Setter
	private static class CategoryDetail {
		private String code;
		private String name;
		private String level;
	}
	
	@Getter
	@Setter
	public static class SentencesDetail {
		private String letter;
		private String rank;
		private String value;
	}
}
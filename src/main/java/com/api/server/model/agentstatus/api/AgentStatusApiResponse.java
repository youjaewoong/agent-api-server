package com.api.server.model.agentstatus.api;

import java.util.Date;
import java.util.List;

import com.api.server.model.agentstatus.AgentStatusResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgentStatusApiResponse {
	
	@SuppressWarnings("unchecked")
	public AgentStatusApiResponse(AgentStatusResponse response) throws JsonMappingException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		List<AgentStatusCategories> categories = objectMapper.readValue(response.getCategories(), List.class);
		List<AgentStatusSentences> sentences = objectMapper.readValue(response.getSentences(), List.class);
		AgentStatusDetail details = objectMapper.readValue(response.getDetail(), AgentStatusDetail.class);
		
		this.id = response.getId();
		this.agentId = response.getAgentId();
		this.callId = response.getCallId();
		this.etc = response.getEtc();
		this.details = details;
		this.categories = categories;
		this.sentences = sentences;
		this.createdIlsi = response.getCreatedIlsi();
		this.updatedIlsi = response.getUpdatedIlsi();
	}
	
	private String id;
	private String agentId;
	private String callId;
	private String etc;
	private AgentStatusDetail details;
	private List<AgentStatusCategories> categories;
	private List<AgentStatusSentences> sentences;
	private Date createdIlsi;
	private Date updatedIlsi;
	
}
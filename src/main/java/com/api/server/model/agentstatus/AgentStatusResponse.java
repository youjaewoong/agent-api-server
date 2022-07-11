package com.api.server.model.agentstatus;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgentStatusResponse {
	
	private String id;
	private String agentId;
	private String callId;
	private String categories;
	private String sentences;
	private String etc;
	private String detail;
	private Date createdIlsi;
	private Date updatedIlsi;
}
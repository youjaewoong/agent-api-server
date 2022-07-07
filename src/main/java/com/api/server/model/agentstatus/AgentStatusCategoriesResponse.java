package com.api.server.model.agentstatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgentStatusCategoriesResponse {
	
	private String cdType;
	private String cd;
	private String parentCd;
	private String cdName;
}
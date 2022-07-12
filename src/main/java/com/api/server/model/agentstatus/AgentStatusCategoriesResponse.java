package com.api.server.model.agentstatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgentStatusCategoriesResponse {
	
	private String cdType;
	private String code;
	private String parentCd;
	private String name;
}
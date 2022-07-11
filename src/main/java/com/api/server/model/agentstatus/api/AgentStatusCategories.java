package com.api.server.model.agentstatus.api;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgentStatusCategories {

	private List<CategoryDetail> categories; //상담유형
	
	@Getter
	@Setter
	public static class CategoryDetail {
		private String code;
		private String name;
		private String level;
	}
}
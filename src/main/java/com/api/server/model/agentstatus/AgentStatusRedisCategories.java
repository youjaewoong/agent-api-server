package com.api.server.model.agentstatus;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgentStatusRedisCategories {
	
	@JsonProperty("agent_status")
	private List<Cetegories> agentStatus;
	
	@Getter
	@Setter
	public static class Cetegories {
		public List<Category> categories;
	}
	
	@Getter
	@Setter
	public static class Category {
		public String level;
		public String name;
	}
}

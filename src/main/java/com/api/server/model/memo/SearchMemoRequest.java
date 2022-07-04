package com.api.server.model.memo;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchMemoRequest {
	
    @NotBlank
    @JsonProperty("agent_id")
	private String agentId;
    private int limit = 5;
    
	@NotBlank
	@JsonProperty("group_id")
    private String groupId;
    @JsonProperty("updated_ilsi")
    private String updatedIlsi;
}

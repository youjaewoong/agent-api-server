package com.api.server.model.memo;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchMemoRequest {
	
    public enum sortType {
        desc,
        asc;
    }
    @NotBlank
    @JsonProperty("agent_id")
	private String agentId;
	private String sortItem;
    private sortType sortType;
    private int limit = 10;
    
	@NotBlank
	@JsonProperty("group_id")
    private String groupId;
    @JsonProperty("updated_ilsi")
    private String updatedIlsi;
}

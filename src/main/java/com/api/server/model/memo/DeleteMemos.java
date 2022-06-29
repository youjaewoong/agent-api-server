package com.api.server.model.memo;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteMemos {
	@NotNull
	private List<String> ids;
	@NotBlank
	@JsonProperty("agent_id")
	private String agentId;
}
package com.api.server.model.bookmark;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteBookmarks {
	
    private List<@NotNull String> ids;
	@NotBlank
	@JsonProperty("adv_id")
	private String advId;
}
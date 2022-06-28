package com.api.server.model.memogroup;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateMemoGroups {
	
	@NotNull
	private List<UpdateMemoGroup> groups;

}
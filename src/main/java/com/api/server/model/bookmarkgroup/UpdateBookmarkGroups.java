package com.api.server.model.bookmarkgroup;

import java.util.List;

import javax.validation.Valid;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateBookmarkGroups {
	
	private List<@Valid UpdateBookmarkGroup> editGroups;

}
package com.api.server.model.bookmarkgroup;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateBookMarkGroup {
	private List<String> id;
	private String title;
	private String userId;
}
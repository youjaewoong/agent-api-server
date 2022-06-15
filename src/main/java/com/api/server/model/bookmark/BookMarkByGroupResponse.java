package com.api.server.model.bookmark;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookMarkByGroupResponse {
	
	private String id;
	private String title;
	List<BookMarkResponse> bookMarkByGroups = new ArrayList<>();
}
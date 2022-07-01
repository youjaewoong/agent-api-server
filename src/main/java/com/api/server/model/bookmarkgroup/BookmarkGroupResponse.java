package com.api.server.model.bookmarkgroup;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookmarkGroupResponse {
	
	private String id;
	private String title;
	private String basicGroupYn;
	private Date createdIlsi;
}
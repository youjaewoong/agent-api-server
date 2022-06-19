package com.api.server.model.bookmarkgroup;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookMarkGroupResponse {
	private String id;
	private String title;
	private char basicGroupYn;
	private Date regDt;
}
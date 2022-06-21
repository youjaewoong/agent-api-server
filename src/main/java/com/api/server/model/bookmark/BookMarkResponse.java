package com.api.server.model.bookmark;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookmarkResponse {
	private String id;
	private String advId;
	private String title;
	private String subTitle;
	private String contents;
	private char type;
	private String groupId;
	private String groupTitle;
	private String basicGroupYn;
	private Date regDt;
}

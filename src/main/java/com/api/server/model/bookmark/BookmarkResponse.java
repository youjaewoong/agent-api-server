package com.api.server.model.bookmark;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookmarkResponse {
	private String id;
	private String agentId;
	private String title;
	private String subTitle;
	private String content;
	private char category;
	private String categoryName;
	private String groupId;
	private String groupTitle;
	private String basicGroupYn;
	private String createdIlsi;
	private String conversionUpdatedIlsi;
}

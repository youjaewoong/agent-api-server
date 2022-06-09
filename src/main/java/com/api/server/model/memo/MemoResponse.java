package com.api.server.model.memo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemoResponse {
	private String id;
	private String userId;
	private String title;
	private String contents;
	private String groupId;
	private String groupName;
	private Date createDt;
}

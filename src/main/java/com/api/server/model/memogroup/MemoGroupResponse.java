package com.api.server.model.memogroup;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemoGroupResponse {
	private String id;
	private String title;
	private String basicGroupYn;
	private Date createdDatetime;
}
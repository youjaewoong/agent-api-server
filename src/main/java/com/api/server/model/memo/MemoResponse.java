package com.api.server.model.memo;

import java.util.Date;
import java.util.List;

import com.api.server.model.memogroup.MemoGroupResponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemoResponse {
	private String id;
	private String agentId;
	private String title;
	private String content;
	private String groupId;
	private String groupTitle;
	private String basicGroupYn;
	private Date createdDatetime;
	private String updatedDatetime;
	private String conversionUpdatedDatetime;
	private List<MemoGroupResponse> memoGroups;
}

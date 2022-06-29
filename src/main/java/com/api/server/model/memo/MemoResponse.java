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
	private String contents;
	private List<MemoGroupResponse> memoGroups;
	private String groupId;
	private Date createdIlsi;
	private Date createdConversionIlsi;
}

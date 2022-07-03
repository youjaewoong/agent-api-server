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
	private String groupId;
	private String groupTitle;
	private String basicGroupYn;
	private Date createdIlsi;
	private String updatedIlsi;
	private String conversionUpdatedIlsi;
	private List<MemoGroupResponse> memoGroups;
}

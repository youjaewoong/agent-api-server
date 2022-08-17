package com.api.server.agent.model.notice;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgentNoticeCategoryResponse {
	private AgentNoticeCategory category;
	private List<AgentNoticeResponse> notices = new ArrayList<>();
}
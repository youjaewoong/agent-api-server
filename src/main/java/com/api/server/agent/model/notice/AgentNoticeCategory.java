package com.api.server.agent.model.notice;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgentNoticeCategory {
	private String category;
	private int total;
	private boolean loading = false;

	/**
	 * ALL : 전체
	 * E: 긴급
	 * N: 기본
	 * W: 워닝
	 */
	public enum Type {
		ALL,E,N,W;
	}

}
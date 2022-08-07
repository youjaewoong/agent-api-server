package com.api.server.admin.model.notice;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminNoticeCategory {
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
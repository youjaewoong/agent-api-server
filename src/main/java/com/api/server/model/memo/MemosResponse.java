package com.api.server.model.memo;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemosResponse {
	private String id;
	private String title;
	private More more = new More();
	private List<MemoResponse> memos  = new ArrayList<>();
	
	@Getter
	@Setter
	public static class More {
		private boolean ismore = false;
		private int total;
		private int limit;
	}
}
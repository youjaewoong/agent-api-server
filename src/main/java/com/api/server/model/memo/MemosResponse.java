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
	private ViewMore viewMore = new ViewMore();
	private List<MemoResponse> memos  = new ArrayList<>();
	
	@Getter
	@Setter
	public static class ViewMore {
		private boolean viewMore = false;
		private int total;
		private int limit;
		private int viewMoreCount;
	}
}
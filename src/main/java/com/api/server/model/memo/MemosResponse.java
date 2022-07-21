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
	private List<MemoResponse> memos  = new ArrayList<>();
}
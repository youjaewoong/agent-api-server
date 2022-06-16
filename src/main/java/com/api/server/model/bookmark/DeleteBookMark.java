package com.api.server.model.bookmark;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteBookMark {
	private List<String> id;
	private String userId;
}
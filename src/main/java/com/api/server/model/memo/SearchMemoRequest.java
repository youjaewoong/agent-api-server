package com.api.server.model.memo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchMemoRequest {
	
    public enum sortType {
        desc,
        asc;
    }
	
	private String userId;
	
	private String sortItem;
	
    private sortType sortType;
}

package com.api.server.model.bookmark;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchBookMarkRequest {
	
    public enum sortType {
        desc,
        asc;
    }
    
	private String userId;
	private String sortItem;
	private sortType sortType;
}

package com.api.server.model.memo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchMemoRequest {
	
	@ApiModelProperty(name="이름", value="벨류", example = "zzz")
	private String userId;
	
	private String sortItem;
	
	private String sortType;
}

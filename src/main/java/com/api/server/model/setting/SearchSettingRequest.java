package com.api.server.model.setting;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchSettingRequest {
	
	@NotBlank
	private String agentId;
	
	private String companyCode;
	
}

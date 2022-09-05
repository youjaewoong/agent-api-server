package com.api.server.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.server.dao.SettingMapper;
import com.api.server.model.setting.CreateSetting;
import com.api.server.model.setting.SearchSettingRequest;
import com.api.server.model.setting.SettingResponse;
import com.api.server.model.setting.UpdateSetting;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class SettingService {
	
	private final SettingMapper settingMapper;

	
	public SettingResponse selectAdvisorSetting(SearchSettingRequest searchSettingRequest) {
		return settingMapper.selectAdvisorSetting(searchSettingRequest);
	}

	
	public void createAdvisorSetting(CreateSetting createSetting) {
		
		SearchSettingRequest searchSettingRequest = new SearchSettingRequest();
		searchSettingRequest.setAgentId(createSetting.getAgentId());
		searchSettingRequest.setCompanyCode(createSetting.getCompanyCode());
		SettingResponse setting = this.selectAdvisorSetting(searchSettingRequest);
		if (setting == null) {
			settingMapper.createAdvisorSetting(createSetting);
		}
	}
	
	public void updateAdvisorSetting(UpdateSetting updateSetting) {
		settingMapper.updateAdvisorSetting(updateSetting);
	}

}
package com.api.server.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.api.server.model.setting.CreateSetting;
import com.api.server.model.setting.SearchSettingRequest;
import com.api.server.model.setting.SettingResponse;
import com.api.server.model.setting.UpdateSetting;

@Repository
@Mapper
public interface SettingMapper {

	SettingResponse selectAdvisorSetting(SearchSettingRequest searchSettingRequest);

    int updateAdvisorSetting(UpdateSetting updateSetting);
	
	int createAdvisorSetting(CreateSetting createSetting);

}
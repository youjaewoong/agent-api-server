package com.api.server.controller;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.server.model.setting.CreateSetting;
import com.api.server.model.setting.SearchSettingRequest;
import com.api.server.model.setting.SettingResponse;
import com.api.server.model.setting.UpdateSetting;
import com.api.server.service.SettingService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping({"/advisor","/advisor/admin"})
public class SettingController {

	private final SettingService settingService;
	
	@ApiOperation("조회")
    @GetMapping("settings")
    public SettingResponse selectMemos(@Valid SearchSettingRequest searchSettingRequest) {
        return settingService.selectAdvisorSetting(searchSettingRequest);
    }
	
	
	@ApiOperation("추가")
    @PostMapping("settings")
    public void createAdvisorSetting(@Valid @RequestBody CreateSetting createSetting) {
		settingService.createAdvisorSetting(createSetting);
    }

    
    @ApiOperation("수정")
    @PutMapping("settings")
    public void updateAdvisorSetting(@Valid @RequestBody UpdateSetting updateSetting) {
    	settingService.updateAdvisorSetting(updateSetting);
    }
    
}
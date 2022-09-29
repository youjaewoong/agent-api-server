package com.api.server.admin.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.server.admin.model.notice.CreateAdminNotice;
import com.api.server.admin.service.AdminNoticeService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/api/advisor")
public class AdminNoticeApiController {

	
	private final AdminNoticeService adminNoticeService;

	
    @ApiOperation("공지사항 추가")
    @PostMapping("notices")
    public ResponseEntity<String> createAdminNoticeWarning(@RequestBody CreateAdminNotice createAdminNotice) throws Exception {
        adminNoticeService.createAdminApiNotice(createAdminNotice);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
	
}
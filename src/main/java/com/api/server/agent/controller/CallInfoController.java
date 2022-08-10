package com.api.server.agent.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.api.server.agent.service.CallInfoService;

import java.util.Map;

@Api("현재 상담중 콜 정보")
@RequestMapping(value = "/advisor/sttCallInfo", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class CallInfoController {

    @Autowired
    CallInfoService callInfoService;

    @GetMapping("/nowCall")
    public Map<Object, Object> getCallInfo(@RequestParam Map<String, Object> jsonData) {
        return callInfoService.getCallInfo(jsonData);
    }

    @PostMapping("/nowCall/{ext}")
    public void saveCallInfo(@PathVariable String ext, @RequestBody Map<String, Object> jsonData) {
        callInfoService.saveCallInfo(ext, jsonData);
    }
}

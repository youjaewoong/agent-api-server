package com.api.server.agent.service;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CallInfoService {

    public Map<Object, Object> getCallInfo(Map<String, Object> jsonData) {
        try{
            //return RedisUtil.getHashAll(String.valueOf(jsonData.get("key")));
        	return null;
        } catch (Exception e) {
            return null;
        }
    }

    public String saveCallInfo(String ext, Map<String, Object> jsonData) {
        try {
            //ObjectUtil.ObjectToRedisHash("nowCallList:" + ext, new JSONObject(jsonData).toString());
        } catch (Exception e) {
            return "N";
        }
        return "Y";
    }
}

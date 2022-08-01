package com.api.server.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.api.server.service.CoachingService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("코칭 컨트롤러")
@RequestMapping(value = "/advisor", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class CoachingController {

    @Autowired
    CoachingService coachingService;

    @GetMapping("/coaching/requestList")
    public HashMap<String, Object> lookupCoachingRequestList(@RequestParam HashMap<String, Object> params) throws Exception {
        return coachingService.lookupCoachingRequestList(params);
    }

    @GetMapping("/coaching/requestData")
    public HashMap<String, Object> getCoachingRequestList(@RequestParam HashMap<String, Object> params) throws Exception {
        return coachingService.getCoachingRequestList(params);
    }

    @GetMapping("/coaching/one")
    public HashMap<String, Object> getCoachingRequest(@RequestParam HashMap<String, Object> params) throws Exception {
        return coachingService.getCoachingRequest(params);
    }

    @GetMapping("/coaching/adminList")
    public HashMap<String, Object> getAdminList(@RequestParam HashMap<String, Object> params) throws Exception {
        return coachingService.getAdminList(params);
    }

    @ApiOperation("코칭 종합 리스트 조회")
    @GetMapping("/coaching/lookup")
    public HashMap<String, Object> lookupCoachingMessageList(@RequestParam HashMap<String, Object> params) throws Exception {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  CoachingController  26");

        return coachingService.lookupCoachingMessageList(params);
    }

    @ApiOperation("코칭 조건별 건수 조회")
    @GetMapping("/coaching/lookup_count")
    public Integer lookupCount(@RequestParam HashMap<String, Object> params) throws Exception {

        return coachingService.lookupCount(params);
    }

    @ApiOperation("코칭메시지 단건 등록")
    @PostMapping("/coaching/insert")
    public HashMap<String, Object> insertOneCoachingMessage(@RequestBody HashMap<String, Object> params) throws Exception {
        return coachingService.insertOneCoachingMessage(params);
    }

    @ApiOperation("코칭메시지 단건 수정")
    @PostMapping("/coaching/feedback")
    public HashMap<String, Object> updateOneCoachingMessage(@RequestBody HashMap<String, Object> params) throws Exception {
        return coachingService.updateOneCoachingMessage(params);
    }

    @ApiOperation("코칭메시지 읽음여부 수정")
    @PostMapping("/coaching/update_read_yn")
    public String updateOneReadYn(@RequestBody HashMap<String, Object> params) throws Exception {

        String saveYn;

//    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  CoachingController  67");
//    Set<String> keys = params.keySet();
//    for (String key : keys) {
//        System.out.println("key: " + key);
//    }
//    
//    Collection<Object> values = params.values();
//    for (Object value : values) {
//        System.out.println("value: " + value);
//    }

        if (params.containsKey("chatting_no") && params.get("chatting_no") != null) {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  CoachingController  85");
            saveYn = coachingService.updateOneReadYn(params);
        } else {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  CoachingController  88");
            throw new Exception();
        }
        return saveYn;
    }

    @ApiOperation("관리자 미답변 건수 조회")
    @GetMapping("/coaching/noAnswerCount")
    public Integer lookupCountNoAnswer(@RequestParam HashMap<String, Object> params) throws Exception {

        return coachingService.lookupCountNoAnswer(params);
    }

    @ApiOperation("상담사 읽지않음 건수 조회")
    @GetMapping("/coaching/lookup_count_no_read")
    public Integer lookupCountNoRead(@RequestParam HashMap<String, Object> params) throws Exception {

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  CoachingController  101");
        System.out.println(params);

//	  ObjectMapper objectMapper = new ObjectMapper();
//	  
//	  Map<String, Object> map = objectMapper.convertValue(params, Map.class);
//	
//	  for(String key : params.keySet()) {
//	    CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, key);
//	  }
//	  
//    Set<String> keys = params.keySet();
//    for (String key : keys) {
//        System.out.println("key: " + key);
//    }
//    
//    Collection<Object> values = params.values();
//    for (Object value : values) {
//        System.out.println("value: " + value);
//    }

        return coachingService.lookupCountNoRead(params);
    }

    @ApiOperation("코칭해준 상담원리스트 조회")
    @GetMapping("/coaching/lookup_coaching_agent_list")
    public HashMap<String, Object> lookupCoachingAgentList(@RequestParam HashMap<String, Object> params) throws Exception {

        return coachingService.lookupCoachingAgentList(params);

//	  System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  CoachingController  138");
//	  System.out.println(params);
//	  
//	  List<HashMap<String, Integer>> rrrr = new ArrayList<HashMap<String, Integer>>();
//		
//		HashMap<String, Integer> aaaa = new HashMap<String, Integer>();
//		aaaa.put("sumResult", 144);
//		aaaa.put("goodHowmany", 799);
//		rrrr.add(aaaa);
//		
//		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  CoachingController  148");
//		  System.out.println(rrrr);
//		
//	return null;

    }

    @PostMapping("/createSubscriber/{ext}")
    public void createSubscriber(@PathVariable String ext, @RequestParam(value = "depth", required = false) String depth, @RequestBody String jsonData) {
        int cntComma = 0;
        Matcher m = Pattern.compile(",").matcher(depth);
        while (m.find()) {
            cntComma++;
        }
        if (cntComma > 0) {
            String[] depthArr = depth.split(",");
            for (String de : depthArr) {
                coachingService.createSubscriber(ext, de, jsonData);
            }
        } else {
            coachingService.createSubscriber(ext, depth, jsonData);
        }
    }

    @GetMapping("/coaching/redis/data")
    public List<HashMap<String, Object>> getRedisCoaching(@RequestParam HashMap<String, Object> params) {
        return coachingService.getRedisCoaching(params);
    }

    @GetMapping("coaching/redis/list")
    public List<HashMap<String, Object>> getRedisCoachingList(@RequestParam HashMap<String, Object> params) {
        return coachingService.getRedisCoachingList(params);
    }

    @GetMapping("/coaching/redis/cnt")
    public Integer getRedisCoachingCount(@RequestParam HashMap<String, Object> params) {
        return coachingService.getRedisCoachingCount(params);
    }

    @PostMapping("/publish/{ext}")
    public void publish(@PathVariable String ext, @RequestBody Map<String, Object> jsonData) {
        coachingService.publish(ext, jsonData);
    }

}

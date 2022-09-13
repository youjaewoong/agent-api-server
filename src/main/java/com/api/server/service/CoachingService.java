package com.api.server.service;


import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.server.dao.CoachingMapper;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CoachingService {

    private final CoachingMapper coachingMapper;
    
    //채팅방(topic)에 발행되는 메시지를 처리할 Listner
    private RedisMessageListenerContainer redisMessageListener;

    private Map<String, ChannelTopic> subscribers = new HashMap<>();

    public HashMap<String, Object> lookupCoachingRequestList(HashMap<String, Object> params) throws Exception {
        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("data", coachingMapper.lookupCoachingRequestList(params));
        return resultMap;
    }

    public HashMap<String, Object> getCoachingRequestList(HashMap<String, Object> params) throws Exception {
        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("data", coachingMapper.getCoachingRequestList(params));
        return resultMap;
    }

    public HashMap<String, Object> getCoachingRequest(HashMap<String, Object> params) throws Exception {
        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("data", coachingMapper.getCoachingRequest(params));
        return resultMap;
    }

    public HashMap<String, Object> getAdminList(HashMap<String, Object> params) throws Exception {
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("data", coachingMapper.getAdminList(params));
        return resultMap;
    }

    public HashMap<String, Object> lookupCoachingMessageList(HashMap<String, Object> params) throws Exception {

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  CoachingService  26");

        //	page_size파라미터로 들어온 값이 있을 때에만 아래와 같이 값을 가공한다. (2022.06.24)
        if (params.containsKey("page_size") && params.get("page_size") != null) {
            Integer pageSize = Integer.parseInt(String.valueOf(params.get("page_size")));
            params.put("page_size", pageSize);
        }

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  CoachingService  33");

        // start_date파라미터로 들어온 값이 있을 때에만 아래와 같이 값을 가공한다. (2022.06.24)
        if (params.containsKey("start_date") && params.get("start_date") != null) {
            String fromDate = (String) params.get("start_date");
            if (fromDate != null && !fromDate.isEmpty()) {
                fromDate = params.get("start_date") + " 00:00:00.000000";
                params.put("start_date", fromDate);
            }
        }

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  CoachingService  44");

        // end_date파라미터로 들어온 값이 있을 때에만 아래와 같이 값을 가공한다. (2022.06.24)
        if (params.containsKey("end_date") && params.get("end_date") != null) {
            String toDate = (String) params.get("end_date");
            if (toDate != null && !toDate.isEmpty()) {
                toDate = params.get("end_date") + " 23:59:59.999999";
                params.put("end_date", toDate);
            }
        }

        // chatting_no파라미터로 들어온 값이 있을 때에만 아래와 같이 값을 가공한다. (2022.06.24)
        if (params.containsKey("chatting_no") && params.get("chatting_no") != null) {
            Integer chattingNo = Integer.parseInt(String.valueOf(params.get("chatting_no")));
            params.put("chatting_no", chattingNo);
        }

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  CoachingService  55");

        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("RESULT33", coachingMapper.lookupCoachingMessageList(params));

        return resultMap;
    }

    public Integer lookupCount(HashMap<String, Object> params) throws Exception {

        return coachingMapper.lookupCount(params);
    }

    /**
     * 코칭 단건 Redis, DB 저장
     * */
    public HashMap<String, Object> insertOneCoachingMessage(HashMap<String, Object> params) throws Exception {
        HashMap<String, Object> resultMap = new HashMap<>();
        String insertYn = "Y";
        String redisYn = "N";
        try {
            // 현재 시간(서버 시간 기준)
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            if(params.containsKey("coachingContent")){
                params.put("coachingDate", dateFormat.format(date));
            } else {
                params.put("requestDate", dateFormat.format(date));
            }


            // 1. Redis
            redisYn = publish(params.get("adminExt").toString(), params);
            // 2. DB 저장
            coachingMapper.insertOneCoachingMessage(params);
        } catch (Exception e) {
            insertYn = "N";
        }
        resultMap.put("insertYn", insertYn);
        resultMap.put("redisYn", redisYn);
        return resultMap;
    }

    /**
     * 코칭 단건 Redis, DB 업데이트
     * */
    public HashMap<String, Object> updateOneCoachingMessage(HashMap<String, Object> params) throws Exception {
        HashMap<String, Object> resultMap = new HashMap<>();
        String updateYn = "Y";
        String redisYn = "N";
        try {
            // 현재 시간(서버 시간 기준)
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            if(params.containsKey("read") && String.valueOf(params.get("read")).equals("Y")) {
                params.put("readDateAgent", dateFormat.format(date));
            } else {
                params.put("coachingDate", dateFormat.format(date));
            }
            // 1. Redis
            redisYn = publish(params.get("adminExt").toString(), params);
            if(params.containsKey("read") && String.valueOf(params.get("read")).equals("Y")) {
                params.remove("coachingDate");
            }
            // 2. DB 저장
            coachingMapper.updateOneCoachingMessage(params);
        } catch (Exception e) {
            updateYn = "N";
        }
        resultMap.put("updateYn", updateYn);
        resultMap.put("redisYn", redisYn);
        return resultMap;
    }

    public String updateOneReadYn(HashMap<String, Object> params) throws Exception {

        String updateYn = "Y";

        try {
            coachingMapper.updateOneReadYn(params);
        } catch (Exception e) {
            updateYn = "N";
        }
        return updateYn;
    }

    public Integer lookupCountNoAnswer(HashMap<String, Object> params) throws Exception {

        return coachingMapper.lookupCount(params);
    }

    public Integer lookupCountNoRead(HashMap<String, Object> params) throws Exception {

        return coachingMapper.lookupCountNoRead(params);
    }

    public HashMap<String, Object> lookupCoachingAgentList(HashMap<String, Object> params) throws Exception {

        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("RESULT33", coachingMapper.lookupCoachingAgentList(params));

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  CochService  98");
        System.out.println(coachingMapper.lookupCoachingAgentList(params));

        return resultMap;
    }

    // REDIS 구독
    public void createSubscriber(String ext, String path, String jsonData) {
        log.info(">>># createSubscriber ext :: {}", ext);

        // 채널 생성
        String key = asURL("/sub/redis", path, ext);

        ChannelTopic channel = new ChannelTopic(key);
        //리스너
        MessageListener messageListener = new MessageListener() {
            @Override
            public void onMessage(Message message, byte[] pattern) {
                subscriber(channel, message);
            }
        };

        //구독한다는 정보를 레디스메시지리스너에 등록
        if (subscribers.get(key) == null) {
            redisMessageListener.addMessageListener(messageListener, channel);
            subscribers.put(key, channel);    //구독 list에 추가
        }
//        ObjectUtil.ObjectToRedisHash("coaching:" + ext + ":" + new Date().toString(), subscribers.toString() );
    }

    public String publish(String ext, Map<String, Object> jsonData) {
        try {
            String path = String.valueOf(jsonData.get("path"));
            // 채널 생성
            String key = asURL("/sub/redis", path, ext);

            ChannelTopic channel = new ChannelTopic(key);
            //redisTemplate.convertAndSend(channel.getTopic(), new JSONObject(jsonData).toString());

            // 경로 -> coaching:{관리자 내선번호} | key -> {상담사 내선번호}{요청일자(yyyyMMddHHmmdd)}
            //HashMap<String, Object> dataMap = new HashMap<>();
            //dataMap.put(jsonData.get("agentExt").toString() + jsonData.get("requestDate").toString().replaceAll("\\s", "").replaceAll("-", "").replaceAll(":", ""), new JSONObject(jsonData).toString());
            //ObjectUtil.ObjectToRedisHash("coaching:" + ext, new JSONObject(dataMap).toString());
        } catch (Exception e) {
            return "N";
        }
        return "Y";
    }

    private void subscriber(ChannelTopic channel, Message message) {
        log.info(">>># subscriber");
        // 채널 생성
        //template.convertAndSend(channel.getTopic(), message.toString());
    }

    //TODO
    public List<HashMap<String, Object>> getRedisCoaching(HashMap<String, Object> params){
        //Map<Object, Object> dataMap = RedisUtil.getHashAll(String.valueOf(params.get("key")));
    	Map<Object, Object> dataMap = null;
        List<Map.Entry<Object, Object>> entries = new LinkedList<>(dataMap.entrySet());
        ObjectMapper mapper = new ObjectMapper();
        String agentExt = "";
        Boolean isAgent = false;
        if(params.containsKey("agentExt")){
            isAgent = true;
            agentExt = String.valueOf(params.get("agentExt"));
        }

        // 현재 시간(서버 시간 기준)
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        String todayDate = dateFormat.format(date) + "000000";

        List<Map.Entry<Object, Object>> list = new ArrayList<>();
        for (Map.Entry<Object, Object> entry : entries) {
            try {
                String redisKey = String.valueOf(entry.getKey());
                String redisDate = redisKey.substring(redisKey.length() - 14, redisKey.length());
                if(redisDate.compareTo(todayDate) >= 0) {
                    if(isAgent){
                        if(redisKey.startsWith(agentExt)
                                && ((mapper.readValue(String.valueOf(entry.getValue()), Map.class)).containsKey("coachingContent"))){
                            list.add(entry);
                        }
                    } else {
                        list.add(entry);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        Collections.sort(list, (o1, o2) -> {
            try {
                return ((mapper.readValue(String.valueOf(o2.getValue()), Map.class)).get("requestDate").toString().compareTo((mapper.readValue(String.valueOf(o1.getValue()), Map.class)).get("requestDate").toString()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        List<HashMap<String, Object>> result = new ArrayList<>();
        for (Map.Entry<Object, Object> entry : list) {
            try {
                result.add(mapper.readValue(String.valueOf(entry.getValue()), HashMap.class));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    //TODO
    public List<HashMap<String, Object>> getRedisCoachingList(HashMap<String, Object> params) {
//        String key = String.valueOf(params.get("key"));
//        String agentExt = String.valueOf(params.get("agentExt"));
//        String clientId = String.valueOf(params.get("clientId"));
//
//        //Map<Object, Object> dataMap = RedisUtil.getHashAll(key);
//        Map<Object, Object> dataMap = null;
//        List<Map.Entry<Object, Object>> entries = new LinkedList<>(dataMap.entrySet());
//        ObjectMapper mapper = new ObjectMapper();
//
//        // 현재 시간(서버 시간 기준)
//        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmdd");
//        Date date = new Date();
//        String nowDate = dateFormat.format(date);
//        String todayDate = dateFormat.format(date);
//        todayDate = todayDate.substring(0, 8) + "000000";
//
//        List<Map.Entry<Object, Object>> list = new ArrayList<>();
//        for (Map.Entry<Object, Object> entry : entries) {
//            String redisKey = String.valueOf(entry.getKey());
//            if(redisKey.contains(agentExt)) {
//                String redisDate = redisKey.substring(redisKey.length() - 14, redisKey.length());
//                if(redisDate.compareTo(todayDate) >= 0 && redisDate.compareTo(nowDate) <= 0) {
//                    try {
//                        if(((mapper.readValue(String.valueOf(entry.getValue()), Map.class)).get("clientId").toString()).equals(clientId)){
//                            list.add(entry);
//                        }
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//            }
//        }
//
//        Collections.sort(list, (o1, o2) -> {
//            try {
//                return ((mapper.readValue(String.valueOf(o2.getValue()), Map.class)).get("requestDate").toString().compareTo((mapper.readValue(String.valueOf(o1.getValue()), Map.class)).get("requestDate").toString()));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        });
//
//        List<HashMap<String, Object>> result = new ArrayList<>();
//        for (Map.Entry<Object, Object> entry : list) {
//            try {
//                result.add(mapper.readValue(String.valueOf(entry.getValue()), HashMap.class));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
        return null;
    }

    //TODO
    public Integer getRedisCoachingCount(HashMap<String, Object> params){
        //Map<Object, Object> dataMap = RedisUtil.getHashAll(String.valueOf(params.get("key")));
    	Map<Object, Object> dataMap = null;
        List<Map.Entry<Object, Object>> entries = new LinkedList<>(dataMap.entrySet());
        ObjectMapper mapper = new ObjectMapper();
        Boolean isAgent = false;
        if(params.containsKey("agentExt")){
            isAgent = true;
        }

        // 현재 시간(서버 시간 기준)
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String nowData = dateFormat.format(date) + " 00:00:00";

        List<Map.Entry<Object, Object>> list = new ArrayList<>();
        for (Map.Entry<Object, Object> entry : entries) {
            try {
                if(((mapper.readValue(String.valueOf(entry.getValue()), Map.class)).get("requestDate").toString()).compareTo(nowData) >= 0) {
                    if(!(mapper.readValue(String.valueOf(entry.getValue()), Map.class)).containsKey("callEnd")) {
                        if(isAgent){
                            if((mapper.readValue(String.valueOf(entry.getValue()), Map.class)).containsKey("coachingContent")){
                                if(!(mapper.readValue(String.valueOf(entry.getValue()), Map.class)).containsKey("read")) {
                                    list.add(entry);
                                }
                            }
                        } else if(!(mapper.readValue(String.valueOf(entry.getValue()), Map.class)).containsKey("coachingContent")) {

                                list.add(entry);
                        }
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return list.size();
    }

    private String asURL(String... keys) {
        return String.join("/", keys);
    }
}

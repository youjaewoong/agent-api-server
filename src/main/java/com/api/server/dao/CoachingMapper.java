package com.api.server.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.api.server.model.coaching.Coaching;
import com.api.server.model.coaching.CoachingAgent;
import com.api.server.model.coaching.CoachingAgentList;

@Repository
@Mapper
public interface CoachingMapper {

  List<Coaching> lookupCoachingRequestList(HashMap<String, Object> params);
  List<Coaching> getCoachingRequestList(HashMap<String, Object> params);
  List<Coaching> getCoachingRequest(HashMap<String, Object> params);
  List<CoachingAgent> getAdminList(HashMap<String, Object> params);
  List<Coaching> lookupCoachingMessageList(HashMap<String, Object> params);
  Integer lookupCount(HashMap<String, Object> params);
  void insertOneCoachingMessage(Map<String, Object> params);
  void updateOneCoachingMessage(Map<String, Object> params);
  void updateOneReadYn(Map<String, Object> params);
  Integer lookupCountNoAnswer(HashMap<String, Object> params);
  Integer lookupCountNoRead(HashMap<String, Object> params);
  List<CoachingAgentList> lookupCoachingAgentList(HashMap<String, Object> params);

}

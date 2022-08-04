package com.api.server.agent.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.api.server.agent.model.notice.AgentNoticeResponse;
import com.api.server.agent.model.notice.CreateAgentNotice;
import com.api.server.agent.model.notice.SearchAgentNoticeRequest;
import com.api.server.agent.model.notice.UpdateAgentNotice;


@Repository
@Mapper
public interface AgentNoticeMapper {

	public List<AgentNoticeResponse> selectAgentNotices(SearchAgentNoticeRequest searchAgentNoticeRequest);

	public int createAgentNotice(CreateAgentNotice createAgentNotice);
    
	public int updateAgentNotice(UpdateAgentNotice updateAgentNotice);
	
}
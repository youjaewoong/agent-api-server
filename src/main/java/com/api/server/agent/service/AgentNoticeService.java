package com.api.server.agent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.server.agent.dao.AgentNoticeMapper;
import com.api.server.agent.model.notice.AgentNoticeResponse;
import com.api.server.agent.model.notice.CreateAgentNotice;
import com.api.server.agent.model.notice.SearchAgentNoticeRequest;
import com.api.server.agent.model.notice.UpdateAgentNotice;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AgentNoticeService {
	
	@Autowired
	private final AgentNoticeMapper agentNoticeMapper;

	
	public List<AgentNoticeResponse> selectAgentNotices(SearchAgentNoticeRequest searchAgentNoticeRequest) {
		return agentNoticeMapper.selectAgentNotices(searchAgentNoticeRequest);
	}
	
	
	/**
	 * 제공지 대상 상담사 ID LIST
	 * @param id
	 * @return
	 */
	public List<String> selectReNoticeTargetAgentIds(String id) {
		return agentNoticeMapper.selectReNoticeTargetAgentIds(id);
	}

	
	public void updateAgentNotice(UpdateAgentNotice updateAgentNotice) {
		agentNoticeMapper.updateAgentNotice(updateAgentNotice);
	}
	
	
	public void createAgentNotice(CreateAgentNotice createAgentNotice) {
		agentNoticeMapper.createAgentNotice(createAgentNotice);
	}
}
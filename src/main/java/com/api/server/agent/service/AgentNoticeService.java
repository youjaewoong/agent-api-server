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
	 * 관리자화면에서 재공지 시 상담사ID 리스트 
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
	
	
	/**
	 * 상담사별 읽지않은 공지 갯수
	 * @param searchAgentNoticeRequest
	 * @return
	 */
	public int countAgentNoticeUnConfirmed(SearchAgentNoticeRequest searchAgentNoticeRequest) {
		return agentNoticeMapper.countAgentNoticeUnConfirmed(searchAgentNoticeRequest);
	}
}
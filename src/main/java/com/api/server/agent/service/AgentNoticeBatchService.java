package com.api.server.agent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.api.server.agent.dao.AgentNoticeMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Component
@Slf4j
public class AgentNoticeBatchService {
	
	@Autowired
	private final AgentNoticeMapper agentNoticeMapper;
	
	/**
	 * 하루전 데이터 일괄삭제
	 */
	@Scheduled(cron = "0 0 01 * * *")
	@Async
	public void deleteAgentNoticeByDay() {
		int deleteCnt = agentNoticeMapper.deleteAgentNoticeByDay(-1);
		log.info("하루전 데이터 일괄삭제 {} 건", deleteCnt);
	}
}
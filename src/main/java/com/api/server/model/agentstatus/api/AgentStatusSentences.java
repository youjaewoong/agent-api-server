package com.api.server.model.agentstatus.api;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgentStatusSentences {
	private List<SentencesDetail> sentences; //핵심문장
	
	@Getter
	@Setter
	private static class SentencesDetail {
		private String letter;
		private String rank;
		private String value;
	}
}
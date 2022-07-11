package com.api.server.model.agentstatus.api;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgentStatusDetail {
	@JsonProperty("high_frequency_keywords")
	private List<KeywordDetail> highFrequencyKeywords; //고빈도키워드
	
	@JsonProperty("interest_keywords")
	private List<KeywordDetail> interestKeywords; //관심키워드
	
	@JsonProperty("positive_keywords")
	private List<KeywordDetail> positiveKeywords;  //긍정키워드
	
	@JsonProperty("denial_keywords")
	private List<KeywordDetail> denialKeywords; //부정키워드
	
	@JsonProperty("main_keywords")
	private List<KeywordDetail> mainKeywords; //핵심키워드
	
	@JsonProperty("emotion_information")
	private String emotionInformation;  //감성정보
	
	@JsonProperty("breakaway_prediction")
	private PredictionDetail breakawayPrediction;  //이탈 예측
	
	@JsonProperty("complaints_prediction")
	private PredictionDetail complaintsPrediction; //민원 예측
	
	@JsonProperty("new_prediction")
	private PredictionDetail newPrediction; //신규 예측
	
	
	@Getter
	@Setter
	private static class KeywordDetail {
		private String id;
		private String rank;
		private String keyword;
		private String sentence;
	}
	
	
	@Getter
	@Setter
	private static class PredictionDetail {
		private String percent;
		private List<KeywordDetail> keywords;
		
		
		@Getter
		@Setter
		private static class KeywordDetail {
			private String id;
			private String sentence;
		}
	}
}
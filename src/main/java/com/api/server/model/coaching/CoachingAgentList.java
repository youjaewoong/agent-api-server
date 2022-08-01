package com.api.server.model.coaching;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CoachingAgentList {

  // 상담사번호
  private String agentNo;
  // 코칭회수
  private String coachingCount;

}

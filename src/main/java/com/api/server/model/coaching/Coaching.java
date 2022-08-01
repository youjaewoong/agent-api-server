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
public class Coaching {
  // 채팅일련번호
  private Long chattingNo;
  // 코칭요청일시
  private String requestIlsi;
  // 코칭일시
  private String coachingIlsi;
  // 상담사번호
  private String agentNo;
  // 관리자번호
  private String adminNo;
  // 고객번호
  private String clientNo;
  // 코칭요청내용
  private String requestContent;
  // 코칭내용
  private String coachingContent;
  // 긴급구분
  private String emergencyGubun;
  // 채팅구분
  private String chattingGubun;
  // 읽음확인일시_상담사기준
  private String readIlsiAgent;
  // 메시지업데이트일시
  private String updatedIlsi;

}

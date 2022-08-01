package com.api.server.model.coaching;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CoachingHist {
    // 채팅일련번호
    private Long chattingNo;
    // 관리자이름
    private String adminName;
    // 상담사 이름
    private String agentName;
    // 상담사 내선번호
    private String ext;
    // 코칭요청일시
    private String requestDate;
    // 코칭일시
    private String coachingDate;
    // 상담사ID
    private String agentId;
    // 관리자ID
    private String adminId;
    // 고객ID
    private String clientId;
    // 고객이름
    private String clientName;
    // 고객번호
    private String clientTel;
    // 코칭요청내용
    private String requestContent;
    // 코칭내용
    private String coachingContent;
    // 긴급구분
    private String emergencyDivision;
    // 채팅구분
    private String chattingDivision;
    // 읽음확인일시_상담사기준
    private String readDateAgent;
    // 메시지업데이트일시
    private String updatedDate;
}

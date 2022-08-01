package com.api.server.model.coaching;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CoachingAgent {

    // 상담사번호
    private String agentId;
    // 상담사명
    private String agentName;
    // 상담사 부서코드
    private String deptCode;
    // 내선번호
    private String ext;
    // 직급
    private String agentTitle;
}

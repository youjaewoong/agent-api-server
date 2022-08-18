package com.api.server.agent.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.server.admin.model.notice.AdminNoticeCategoryResponse;
import com.api.server.admin.model.notice.SearchAdminNoticeRequest;
import com.api.server.admin.service.AdminNoticeService;
import com.api.server.agent.model.notice.AgentNoticeCategoryResponse;
import com.api.server.agent.model.notice.CreateAgentNotice;
import com.api.server.agent.model.notice.SearchAgentNoticeRequest;
import com.api.server.agent.model.notice.UpdateAgentNotice;
import com.api.server.agent.service.AgentNoticeService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/advisor")
public class AgentNoticeController {

	private final AgentNoticeService agentNoticeService;
	
	
	
	@ApiOperation("공지사항 카테고리별 전체 조회")
    @GetMapping("notices/all")
    public List<AgentNoticeCategoryResponse> selectAgentNotices(@NotBlank @RequestParam("agent_id") String agentId
    														   ,@NotBlank @RequestParam("company_code") String companyCode) {
		
		SearchAgentNoticeRequest searchAgentNoticeRequest = new SearchAgentNoticeRequest();
		searchAgentNoticeRequest.setAgentId(agentId);
		searchAgentNoticeRequest.setCompanyCode(companyCode);
        return agentNoticeService.selectAgentNoticesAll(searchAgentNoticeRequest);
    }
	
	
	@ApiOperation("공지사항 카테고리별 개별 조회")
    @GetMapping("notices")
    public AgentNoticeCategoryResponse selectAdminNotices(@NotBlank @RequestParam("agent_id") String agentId
    													 ,@NotBlank @RequestParam("company_code") String companyCode
    												     ,SearchAgentNoticeRequest searchAgentNoticeRequest) {
		searchAgentNoticeRequest.setAgentId(agentId);
		searchAgentNoticeRequest.setCompanyCode(companyCode);
        return agentNoticeService.selectAgentPageNotices(searchAgentNoticeRequest);
    }
	
	
	@ApiOperation("공지 대상 상담사ID 조회")
	@GetMapping("notices/target-agents")
	public List<String> selectReNoticeTargetAgentIds(@NotBlank @RequestParam("id") String id) {
		
		return agentNoticeService.selectReNoticeTargetAgentIds(id);
	}
	
	
	@ApiOperation("추가")
    @PostMapping("notice")
    public void createAgentNotice(@Valid @RequestBody CreateAgentNotice createAgentNotice) {
		agentNoticeService.createAgentNotice(createAgentNotice);
    }
	  
    
    @ApiOperation("단건 수정")
    @PutMapping("/notices/{id}")
    public String updateAgentNotice(@PathVariable String id, @Valid @RequestBody UpdateAgentNotice updateAgentNotice) {
    	updateAgentNotice.setId(id);
    	return agentNoticeService.updateAgentNotice(updateAgentNotice);
    }
    
    
    @ApiOperation("확인되지 않은 공지 갯수")
    @GetMapping("/notices/{agentId}")
    public int countAgentNoticeUnConfirmed(@PathVariable String agentId 
    									  ,@NotBlank @RequestParam("company_code") String companyCode) {
    	SearchAgentNoticeRequest searchAgentNoticeRequest = new SearchAgentNoticeRequest();
    	searchAgentNoticeRequest.setAgentId(agentId);
    	searchAgentNoticeRequest.setCompanyCode(companyCode);
    	return agentNoticeService.countAgentNoticeUnConfirmed(searchAgentNoticeRequest);
    }
    
    
    /**
     * -1 하루전 default
     * @param dayNumber
     * @return
     */
    @ApiOperation("일별 데이터 일괄삭제")
    @DeleteMapping("/notices/day")
    public int countAgentNoticeUnConfirmed(@RequestParam(value="day_count",defaultValue = "-1") int dayCount) {
    	return agentNoticeService.deleteAgentNoticeByDay(dayCount);
    }
    
}
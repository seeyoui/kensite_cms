package com.seeyoui.kensite.framework.oa.leave.service;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seeyoui.kensite.common.base.service.BaseService;
import com.seeyoui.kensite.common.exception.CRUDException;
import com.seeyoui.kensite.common.util.Collections3;
import com.seeyoui.kensite.common.util.StringUtils;
import com.seeyoui.kensite.framework.oa.leave.domain.Leave;
import com.seeyoui.kensite.framework.oa.leave.persistence.LeaveMapper;

@Service
public class LeaveService extends BaseService {

	@Autowired
	private LeaveMapper leaveMapper;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	protected TaskService taskService;
	@Autowired
	protected HistoryService historyService;
	@Autowired
	private IdentityService identityService;

	/**
	 * 获取流程详细及工作流参数
	 * @param id
	 */
	@SuppressWarnings("unchecked")
	public Leave findLeaveById(String id) {
		Leave leave = leaveMapper.findLeaveById(id);
		Map<String,Object> variables=null;
		HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(leave.getBindId()).singleResult();
		if(historicProcessInstance!=null) {
			variables = Collections3.extractToMap(historyService.createHistoricVariableInstanceQuery().processInstanceId(historicProcessInstance.getId()).list(), "variableName", "value");
		} else {
			variables = runtimeService.getVariables(runtimeService.createProcessInstanceQuery().processInstanceId(leave.getBindId()).active().singleResult().getId());
		}
		leave.setVariables(variables);
		Task task = taskService.createTaskQuery().processInstanceId(leave.getBindId()).active().singleResult();
		if(task!=null) {
			leave.setTask(task);
			leave.getAct().setTask(task);
		}
		return leave;
	}
	
	/**
	 * 启动流程
	 * @param leave
	 * @param variables
	 */
	public void start(Leave leave, Map<String, Object> variables) {
		// 保存业务数据
		if (StringUtils.isBlank(leave.getId())){
			leave.preInsert();
			leaveMapper.saveLeave(leave);
		}else{
			leave.preUpdate();
			leaveMapper.updateLeave(leave);
		}
		// 用来设置启动流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
		identityService.setAuthenticatedUserId(leave.getCurrentUser().getUserName());
		// 启动流程
		String businessKey = leave.getId();
		variables.put("type", "leave");
		variables.put("busId", businessKey);
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("leave", businessKey, variables);
		// 更新流程实例ID
		leave.setBindId(processInstance.getId());
		leaveMapper.updateBindid(leave);
	}
	
	/**
	 * 数据新增或修改
	 * @param leave
	 * @throws CRUDException
	 */
	public void saveOrUpdate(Leave leave) throws CRUDException{
		// 保存业务数据
		if (StringUtils.isBlank(leave.getId())){
			leave.preInsert();
			leaveMapper.saveLeave(leave);
		}else{
			leave.preUpdate();
			leaveMapper.updateLeave(leave);
		}
	}
}

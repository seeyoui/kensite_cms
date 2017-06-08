package com.seeyoui.kensite.framework.oa.leave.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	protected RepositoryService repositoryService;
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
		return leave;
	}
	
	/**
	 * 启动流程
	 * @param entity
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
		logger.debug("save entity: {}", leave);
		
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
		
		logger.debug("start process of {key={}, bkey={}, pid={}, variables={}}", new Object[] { 
				"leave", businessKey, processInstance.getId(), variables });
	}
	
	/**
	 * 数据新增
	 * @param leave
	 * @throws CRUDException
	 */
	public void save(Leave leave) throws CRUDException{
		// 保存业务数据
		if (StringUtils.isBlank(leave.getId())){
			leave.preInsert();
			leaveMapper.saveLeave(leave);
		}else{
			leave.preUpdate();
			leaveMapper.updateLeave(leave);
		}
	}

	/**
	 * 查询待办任务
	 * @param userId 用户ID
	 * @return
	 */
	public List<Leave> findTodoTasks(String userId) {
		
		List<Leave> results = new ArrayList<Leave>();
		List<Task> tasks = new ArrayList<Task>();
		// 根据当前人的ID查询
		List<Task> todoList = taskService.createTaskQuery().processDefinitionKey("leave").taskAssignee(userId).active().orderByTaskPriority().desc().orderByTaskCreateTime().desc().list();
		// 根据当前人未签收的任务
		List<Task> unsignedTasks = taskService.createTaskQuery().processDefinitionKey("leave").taskCandidateUser(userId).active().orderByTaskPriority().desc().orderByTaskCreateTime().desc().list();
		// 合并
		tasks.addAll(todoList);
		tasks.addAll(unsignedTasks);
		
		//下面的方法不能使用自定义的用户和用户组
//		List<Task> tasks = taskService.createTaskQuery().processDefinitionKey("leave").taskCandidateOrAssigned(userId).active().orderByTaskPriority().desc().orderByTaskCreateTime().desc().list();
		// 根据流程的业务ID查询实体并关联
		for (Task task : tasks) {
			String processInstanceId = task.getProcessInstanceId();
			ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).active().singleResult();
			String businessKey = processInstance.getBusinessKey();
			Leave leave = leaveMapper.findLeaveById(businessKey);
			leave.setTask(task);
			leave.setProcessInstance(processInstance);
			leave.setProcessDefinition(repositoryService.createProcessDefinitionQuery().processDefinitionId((processInstance.getProcessDefinitionId())).singleResult());
			results.add(leave);
		}
		return results;
	}
	
	public List<Leave> find(Leave leave) {
		List<Leave> leaveList = leaveMapper.findLeaveList(leave);
		for(Leave item : leaveList) {
			String processInstanceId = item.getBindId();
			Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).active().singleResult();
			item.setTask(task);
			HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
			if(historicProcessInstance!=null) {
				item.setHistoricProcessInstance(historicProcessInstance);
				item.setProcessDefinition(repositoryService.createProcessDefinitionQuery().processDefinitionId(historicProcessInstance.getProcessDefinitionId()).singleResult());
			} else {
				ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).active().singleResult();
				if (processInstance != null){
					item.setProcessInstance(processInstance);
					item.setProcessDefinition(repositoryService.createProcessDefinitionQuery().processDefinitionId(processInstance.getProcessDefinitionId()).singleResult());
				}
			}
		}
		return leaveList;
	}
}

package com.seeyoui.kensite.framework.act.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.bpmn.behavior.UserTaskActivityBehavior;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.delegate.ActivityBehavior;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.task.TaskDefinition;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.seeyoui.kensite.common.base.domain.Pager;
import com.seeyoui.kensite.common.base.service.BaseService;
import com.seeyoui.kensite.common.util.DateUtils;
import com.seeyoui.kensite.common.util.StringUtils;
import com.seeyoui.kensite.framework.act.domain.Act;
import com.seeyoui.kensite.framework.act.util.ActUtils;
import com.seeyoui.kensite.framework.act.util.ProcessDefCache;
import com.seeyoui.kensite.framework.system.domain.SysUser;
import com.seeyoui.kensite.framework.system.util.UserUtils;

@Service
public class ActTaskService extends BaseService {

//	@Autowired
//	private ActDao actDao;
	
	@Autowired
	private ProcessEngineFactoryBean processEngine;
	@Autowired
    ProcessEngineConfiguration processEngineConfiguration;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private FormService formService;
	@Autowired
	private HistoryService historyService;
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private IdentityService identityService;
	
	/**
	 * 获取待办列表
	 * @param procDefKey 流程定义标识
	 * @return
	 */
	public List<HashMap<String,String>> todoList(Act act){
		String userId = UserUtils.getUser().getUserName();//ObjectUtils.toString(UserUtils.getUser().getId());
		
		List<HashMap<String,String>> result = new ArrayList<HashMap<String,String>>();
		
		// =============== 已经签收的任务  ===============
		TaskQuery todoTaskQuery = taskService.createTaskQuery().taskAssignee(userId).active()
				.includeProcessVariables().orderByTaskCreateTime().desc();
		
		// 设置查询条件
		if (StringUtils.isNotBlank(act.getProcDefKey())){
			todoTaskQuery.processDefinitionKey(act.getProcDefKey());
		}
		if (act.getBeginDate() != null){
			todoTaskQuery.taskCreatedAfter(act.getBeginDate());
		}
		if (act.getEndDate() != null){
			todoTaskQuery.taskCreatedBefore(act.getEndDate());
		}
		
		// 查询列表
		List<Task> todoList = todoTaskQuery.list();
		for (Task task : todoList) {
			HashMap map = new HashMap();
			map.put("task.assignee",task.getAssignee());
			map.put("task.id", task.getId());
			map.put("task.createTime", DateUtils.formatDateTime(task.getCreateTime()));
			map.put("task.name", task.getName());
			map.put("task.executionId",task.getExecutionId());
			map.put("task.processDefinitionId", task.getProcessDefinitionId());
			map.put("task.processInstanceId", task.getProcessInstanceId());
			map.put("task.taskDefinitionKey", task.getTaskDefinitionKey());
			String applyUserId = "";
			if(task.getProcessVariables()!=null && task.getProcessVariables().get("applyUserId")!=null && StringUtils.isNotBlank(task.getProcessVariables().get("applyUserId").toString())) {
				applyUserId = task.getProcessVariables().get("applyUserId").toString();
				applyUserId = UserUtils.getByLoginName(applyUserId).getName();
			} else {
				applyUserId = "";
			}
			map.put("vars.applyUserId", applyUserId);
			String title = "";
			String procName = ProcessDefCache.get(task.getProcessDefinitionId()).getName();
			if(task.getProcessVariables()!=null && task.getProcessVariables().get("title")!=null && StringUtils.isNotBlank(task.getProcessVariables().get("title").toString())) {
				title = task.getProcessVariables().get("title").toString();
			} else {
				if(StringUtils.isNoneBlank(applyUserId)) {
					title = applyUserId+"的"+procName;
				} else {
					title = procName;
				}
			}
			map.put("vars.title", title);
			map.put("procDef.name", ProcessDefCache.get(task.getProcessDefinitionId()).getName());
			map.put("procDef.version", ProcessDefCache.get(task.getProcessDefinitionId()).getVersion());
			map.put("status","todo");
			result.add(map);
		}
		
		// =============== 等待签收的任务  ===============
		TaskQuery toClaimQuery = taskService.createTaskQuery().taskCandidateUser(userId)
				.includeProcessVariables().active().orderByTaskCreateTime().desc();
		
		// 设置查询条件
		if (StringUtils.isNotBlank(act.getProcDefKey())){
			toClaimQuery.processDefinitionKey(act.getProcDefKey());
		}
		if (act.getBeginDate() != null){
			toClaimQuery.taskCreatedAfter(act.getBeginDate());
		}
		if (act.getEndDate() != null){
			toClaimQuery.taskCreatedBefore(act.getEndDate());
		}
		
		// 查询列表
		List<Task> toClaimList = toClaimQuery.list();
		for (Task task : toClaimList) {
			HashMap map = new HashMap();
			map.put("task.assignee",task.getAssignee());
			map.put("task.id", task.getId());
			map.put("task.name", task.getName());
			map.put("task.createTime", DateUtils.formatDateTime(task.getCreateTime()));
			map.put("task.executionId",task.getExecutionId());
			map.put("task.processInstanceId", task.getProcessInstanceId());
			map.put("task.processDefinitionId", task.getProcessDefinitionId());
			map.put("task.taskDefinitionKey", task.getTaskDefinitionKey());
			String applyUserId = "";
			if(task.getProcessVariables()!=null && task.getProcessVariables().get("applyUserId")!=null && StringUtils.isNotBlank(task.getProcessVariables().get("applyUserId").toString())) {
				applyUserId = task.getProcessVariables().get("applyUserId").toString();
				applyUserId = UserUtils.getByLoginName(applyUserId).getName();
			} else {
				applyUserId = "";
			}
			map.put("vars.applyUserId", applyUserId);
			String title = "";
			String procName = ProcessDefCache.get(task.getProcessDefinitionId()).getName();
			if(task.getProcessVariables()!=null && task.getProcessVariables().get("title")!=null && StringUtils.isNotBlank(task.getProcessVariables().get("title").toString())) {
				title = task.getProcessVariables().get("title").toString();
			} else {
				if(StringUtils.isNoneBlank(applyUserId)) {
					title = applyUserId+"的"+procName;
				} else {
					title = procName;
				}
			}
			map.put("vars.title", title);
			map.put("procDef.name", ProcessDefCache.get(task.getProcessDefinitionId()).getName());
			map.put("procDef.version", ProcessDefCache.get(task.getProcessDefinitionId()).getVersion());
			map.put("status", "claim");
			result.add(map);
		}
		return result;
	}
	
	/**
	 * 获取已办任务
	 * @param page
	 * @param procDefKey 流程定义标识
	 * @return
	 */
	public List<HashMap<String,String>> historicList(Pager pager, Act act){
		String userId = UserUtils.getUser().getUserName();//ObjectUtils.toString(UserUtils.getUser().getId());
		List<HashMap<String,String>> result = new ArrayList<HashMap<String,String>>();
		HistoricTaskInstanceQuery histTaskQuery = historyService.createHistoricTaskInstanceQuery().taskAssignee(userId).finished()
				.includeProcessVariables().orderByHistoricTaskInstanceEndTime().desc();
		
		// 设置查询条件
		if (StringUtils.isNotBlank(act.getProcDefKey())){
			histTaskQuery.processDefinitionKey(act.getProcDefKey());
		}
		if (act.getBeginDate() != null){
			histTaskQuery.taskCompletedAfter(act.getBeginDate());
		}
		if (act.getEndDate() != null){
			histTaskQuery.taskCompletedBefore(act.getEndDate());
		}
		
		// 查询总数
		pager.setTotal(histTaskQuery.count());
		// 查询列表
		List<HistoricTaskInstance> histList = histTaskQuery.listPage(pager.getFirstResult(), pager.getMaxResults());
		for (HistoricTaskInstance histTask : histList) {
			Act e = new Act();
			e.setHistTask(histTask);
			e.setVars(histTask.getProcessVariables());
//			e.setTaskVars(histTask.getTaskLocalVariables());
//			System.out.println(histTask.getId()+"  =  "+histTask.getProcessVariables() + "  ========== " + histTask.getTaskLocalVariables());
			e.setProcDef(ProcessDefCache.get(histTask.getProcessDefinitionId()));
//			e.setProcIns(runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult());
//			e.setProcExecUrl(ActUtils.getProcExeUrl(task.getProcessDefinitionId()));
			e.setStatus("finish");
			HashMap map = new HashMap();
			map.put("task.assignee",histTask.getAssignee());
			map.put("task.id", histTask.getId());
			map.put("task.name", histTask.getName());
			map.put("task.createTime", DateUtils.formatDateTime(histTask.getCreateTime()));
			map.put("task.executionId",histTask.getExecutionId());
			map.put("task.processInstanceId", histTask.getProcessInstanceId());
			map.put("task.processDefinitionId", histTask.getProcessDefinitionId());
			map.put("task.taskDefinitionKey", histTask.getTaskDefinitionKey());
			String applyUserId = "";
			if(histTask.getProcessVariables()!=null && histTask.getProcessVariables().get("applyUserId")!=null && StringUtils.isNotBlank(histTask.getProcessVariables().get("applyUserId").toString())) {
				applyUserId = histTask.getProcessVariables().get("applyUserId").toString();
				applyUserId = UserUtils.getByLoginName(applyUserId).getName();
			} else {
				applyUserId = "";
			}
			map.put("vars.applyUserId", applyUserId);
			String title = "";
			String procName = ProcessDefCache.get(histTask.getProcessDefinitionId()).getName();
			if(histTask.getProcessVariables()!=null && histTask.getProcessVariables().get("title")!=null && StringUtils.isNotBlank(histTask.getProcessVariables().get("title").toString())) {
				title = histTask.getProcessVariables().get("title").toString();
			} else {
				if(StringUtils.isNoneBlank(applyUserId)) {
					title = applyUserId+"的"+procName;
				} else {
					title = procName;
				}
			}
			map.put("vars.title", title);
			map.put("procDef.name", ProcessDefCache.get(histTask.getProcessDefinitionId()).getName());
			map.put("procDef.version", ProcessDefCache.get(histTask.getProcessDefinitionId()).getVersion());
			map.put("status", "finish");
			result.add(map);
		}
		return result;
	}
	
	/**
	 * 获取已完成流程
	 * @param page
	 * @param procDefKey 流程定义标识
	 * @return
	 */
	public List<HashMap<String,String>> finishedList(Pager pager, String finished){
		String userId = UserUtils.getUser().getUserName();//ObjectUtils.toString(UserUtils.getUser().getId());
		List<HashMap<String,String>> result = new ArrayList<HashMap<String,String>>();
		HistoricProcessInstanceQuery historicProcessInstanceQuery = historyService.createHistoricProcessInstanceQuery().variableValueEquals("applyUserId", userId).orderByProcessInstanceStartTime().desc();
		if(StringUtils.isNoneBlank(finished) && "finished".equals(finished)) {
			historicProcessInstanceQuery = historicProcessInstanceQuery.finished();
		}
		if(StringUtils.isNoneBlank(finished) && "unfinished".equals(finished)) {
			historicProcessInstanceQuery = historicProcessInstanceQuery.unfinished();
		}
		pager.setTotal(historicProcessInstanceQuery.count());
		// 查询列表
		List<HistoricProcessInstance> hpiList = historicProcessInstanceQuery.listPage(pager.getFirstResult(), pager.getMaxResults());
		for(HistoricProcessInstance hpi : hpiList) {
			HashMap map = new HashMap();
			map.put("processName",hpi.getProcessDefinitionName());
			map.put("startTime",DateUtils.formatDateTime(hpi.getStartTime()));
			map.put("endTime",hpi.getEndTime()==null?"":DateUtils.formatDateTime(hpi.getEndTime()));
			map.put("processDefinitionId",hpi.getProcessDefinitionId());
			List<Task> taskList = taskService.createTaskQuery().processInstanceBusinessKey(hpi.getBusinessKey()).active().list();//.taskAssignee(userId)
			List<HistoricTaskInstance> hisTaskList = historyService.createHistoricTaskInstanceQuery().processInstanceBusinessKey(hpi.getBusinessKey()).list();
			if(taskList.size() > 0) {
				for(Task task : taskList) {
					map.put("task.id",task.getId());
					map.put("task.name",task.getName());
					map.put("task.assignee",task.getAssignee());
					map.put("task.taskDefinitionKey", task.getTaskDefinitionKey());
					map.put("task.processInstanceId", task.getProcessInstanceId());
					map.put("task.processDefinitionId", task.getProcessDefinitionId());
				}
			} else {
				for(HistoricTaskInstance task : hisTaskList) {
					map.put("task.id",task.getId());
					map.put("task.name",task.getName());
					map.put("task.assignee",task.getAssignee());
					map.put("task.taskDefinitionKey", task.getTaskDefinitionKey());
					map.put("task.processInstanceId", task.getProcessInstanceId());
					map.put("task.processDefinitionId", task.getProcessDefinitionId());
				}
			}
			result.add(map);
		}
		return result;
	}
	
	/**
	 * 获取流转历史列表
	 * @param procInsId 流程实例
	 * @param startAct 开始活动节点名称
	 * @param endAct 结束活动节点名称
	 */
	public List<Act> histoicFlowList(String procInsId, String startAct, String endAct){
		List<Act> actList = Lists.newArrayList();
		List<HistoricActivityInstance> list = historyService.createHistoricActivityInstanceQuery().processInstanceId(procInsId)
				.orderByHistoricActivityInstanceStartTime().asc().orderByHistoricActivityInstanceEndTime().asc().list();
		
		boolean start = false;
		Map<String, Integer> actMap = Maps.newHashMap();
		
		for (int i=0; i<list.size(); i++){
			
			HistoricActivityInstance histIns = list.get(i);
			
			// 过滤开始节点前的节点
			if (StringUtils.isNotBlank(startAct) && startAct.equals(histIns.getActivityId())){
				start = true;
			}
			if (StringUtils.isNotBlank(startAct) && !start){
				continue;
			}
			
			// 只显示开始节点和结束节点，并且执行人不为空的任务
			if (StringUtils.isNotBlank(histIns.getAssignee())
					 || "startEvent".equals(histIns.getActivityType())
					 || "endEvent".equals(histIns.getActivityType())){
				
				// 给节点增加一个序号
				Integer actNum = actMap.get(histIns.getActivityId());
				if (actNum == null){
					actMap.put(histIns.getActivityId(), actMap.size());
				}
				
				Act e = new Act();
				e.setHistIns(histIns);
				// 获取流程发起人名称
				if ("startEvent".equals(histIns.getActivityType())){
					List<HistoricProcessInstance> il = historyService.createHistoricProcessInstanceQuery().processInstanceId(procInsId).orderByProcessInstanceStartTime().asc().list();
//					List<HistoricIdentityLink> il = historyService.getHistoricIdentityLinksForProcessInstance(procInsId);
					if (il.size() > 0){
						if (StringUtils.isNotBlank(il.get(0).getStartUserId())){
							SysUser sysUser = UserUtils.getByLoginName(il.get(0).getStartUserId());
							if (sysUser != null){
								e.setAssignee(histIns.getAssignee());
								e.setAssigneeName(sysUser.getName());
							}
						}
					}
				}
				// 获取任务执行人名称
				if (StringUtils.isNotEmpty(histIns.getAssignee())){
					SysUser sysUser = UserUtils.getByLoginName(histIns.getAssignee());
					if (sysUser != null){
						e.setAssignee(histIns.getAssignee());
						e.setAssigneeName(sysUser.getName());
					}
				}
				// 获取意见评论内容
				if (StringUtils.isNotBlank(histIns.getTaskId())){
					List<Comment> commentList = taskService.getTaskComments(histIns.getTaskId());
					if (commentList.size()>0){
						e.setComment(commentList.get(0).getFullMessage());
					}
				}
				actList.add(e);
			}
			
			// 过滤结束节点后的节点
			if (StringUtils.isNotBlank(endAct) && endAct.equals(histIns.getActivityId())){
				boolean bl = false;
				Integer actNum = actMap.get(histIns.getActivityId());
				// 该活动节点，后续节点是否在结束节点之前，在后续节点中是否存在
				for (int j=i+1; j<list.size(); j++){
					HistoricActivityInstance hi = list.get(j);
					Integer actNumA = actMap.get(hi.getActivityId());
					if ((actNumA != null && actNumA < actNum) || StringUtils.equals(hi.getActivityId(), histIns.getActivityId())){
						bl = true;
					}
				}
				if (!bl){
					break;
				}
			}
		}
		return actList;
	}

	/**
	 * 获取流程列表
	 * @param category 流程分类
	 */
	public List<Object[]> processList(Pager pager, String category) {
		/*
		 * 保存两个对象，一个是ProcessDefinition（流程定义），一个是Deployment（流程部署）
		 */
	    ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery()
	    		.latestVersion().active().orderByProcessDefinitionKey().asc();
	    
	    if (StringUtils.isNotEmpty(category)){
	    	processDefinitionQuery.processDefinitionCategory(category);
		}
	    
	    pager.setTotal(processDefinitionQuery.count());
	    List<Object[]> list = new ArrayList<Object[]>();
	    List<ProcessDefinition> processDefinitionList = processDefinitionQuery.listPage(pager.getFirstResult(), pager.getMaxResults());
	    for (ProcessDefinition processDefinition : processDefinitionList) {
			String deploymentId = processDefinition.getDeploymentId();
			Deployment deployment = repositoryService.createDeploymentQuery().deploymentId(deploymentId).singleResult();
			list.add(new Object[]{processDefinition, deployment});
	    }
		return list;
	}
	
	/**
	 * 获取流程表单（首先获取任务节点表单KEY，如果没有则取流程开始节点表单KEY）
	 * @return
	 */
	public String getFormKey(String procDefId, String taskDefKey){
		String formKey = "";
		if (StringUtils.isNotBlank(procDefId)){
			if (StringUtils.isNotBlank(taskDefKey)){
				try{
					formKey = formService.getTaskFormKey(procDefId, taskDefKey);
				}catch (Exception e) {
					formKey = "";
				}
			}
			if (StringUtils.isBlank(formKey)){
				formKey = formService.getStartFormKey(procDefId);
			}
			if (StringUtils.isBlank(formKey)){
				formKey = "/404";
			}
		}
		logger.debug("getFormKey: {}", formKey);
		return formKey;
	}
	
	/**
	 * 获取流程实例对象
	 * @param procInsId
	 * @return
	 */
	public ProcessInstance getProcIns(String procInsId) {
		return runtimeService.createProcessInstanceQuery().processInstanceId(procInsId).singleResult();
	}
	/**
	 * 获取已经结束流程实例对象
	 * @param procInsId
	 * @return
	 */
	@Transactional(readOnly = false)
	public HistoricProcessInstance getFinishedProcIns(String procInsId) {
		return historyService.createHistoricProcessInstanceQuery().processInstanceId(procInsId).singleResult();
	}
	
	
	/**
	 * 获取正在运行的流程实例对象
	 * @param procInsId
	 * @return
	 */
	@Transactional(readOnly = false)
	public List<ProcessInstance> getRunngingProcIns(String procDefKey,  SysUser user, int[] pageParams) {
		ProcessInstanceQuery query = runtimeService.createProcessInstanceQuery().processDefinitionKey(procDefKey).active().orderByProcessInstanceId().desc();
		List<ProcessInstance> list = new ArrayList<ProcessInstance>();
		if (UserUtils.getSubject().hasRole("sys")){
			list = query.listPage(pageParams[0], pageParams[1]);
        } else {
        	list = query.involvedUser(user.getUserName()).listPage(pageParams[0], pageParams[1]);
        }
		return list;
	}
	
	/**
	 * 获取已经结束的流程实例对象
	 * @param procInsId
	 * @return
	 */
	@Transactional(readOnly = false)
	public List<HistoricProcessInstance> getFinishedProcIns(String procDefKey, SysUser user, int[] pageParams) {
		HistoricProcessInstanceQuery query = historyService.createHistoricProcessInstanceQuery().processDefinitionKey(procDefKey).finished().orderByProcessInstanceEndTime().desc();
		List<HistoricProcessInstance> list = new ArrayList<HistoricProcessInstance>();
		if (UserUtils.getSubject().hasRole("sys")){
			list = query.listPage(pageParams[0], pageParams[1]);
        } else {
        	list = query.involvedUser(user.getUserName()).listPage(pageParams[0], pageParams[1]);
        }
		return list;
	}

	/**
	 * 启动流程
	 * @param procDefKey 流程定义KEY
	 * @param businessTable 业务表表名
	 * @param businessId	业务表编号
	 * @return 流程实例ID
	 */
	public String startProcess(String procDefKey, String businessTable, String businessId) {
		return startProcess(procDefKey, businessTable, businessId, "");
	}
	
	/**
	 * 启动流程
	 * @param procDefKey 流程定义KEY
	 * @param businessTable 业务表表名
	 * @param businessId	业务表编号
	 * @param title			流程标题，显示在待办任务标题
	 * @return 流程实例ID
	 */
	public String startProcess(String procDefKey, String businessTable, String businessId, String title) {
		Map<String, Object> vars = Maps.newHashMap();
		return startProcess(procDefKey, businessTable, businessId, title, vars);
	}
	
	/**
	 * 启动流程
	 * @param procDefKey 流程定义KEY
	 * @param businessTable 业务表表名
	 * @param businessId	业务表编号
	 * @param title			流程标题，显示在待办任务标题
	 * @param vars			流程变量
	 * @return 流程实例ID
	 */
	public String startProcess(String procDefKey, String businessTable, String businessId, String title, Map<String, Object> vars) {
		String userId = UserUtils.getUser().getUserName();//ObjectUtils.toString(UserUtils.getUser().getId())
		
		// 用来设置启动流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
		identityService.setAuthenticatedUserId(userId);
		
		// 设置流程变量
		if (vars == null){
			vars = Maps.newHashMap();
		}
		
		// 设置流程标题
		if (StringUtils.isNotBlank(title)){
			vars.put("title", title);
		}
		
		// 启动流程
		ProcessInstance procIns = runtimeService.startProcessInstanceByKey(procDefKey, businessTable+":"+businessId, vars);
		
		// 更新业务表流程实例ID
		Act act = new Act();
		act.setBusinessTable(businessTable);// 业务表名
		act.setBusinessId(businessId);	// 业务表ID
		act.setProcInsId(procIns.getId());
//		actDao.updateProcInsIdByBusinessId(act);
		return act.getProcInsId();
	}

	/**
	 * 获取任务
	 * @param taskId 任务ID
	 */
	public Task getTask(String taskId){
		return taskService.createTaskQuery().taskId(taskId).singleResult();
	}
	
	/**
	 * 删除任务
	 * @param taskId 任务ID
	 * @param deleteReason 删除原因
	 */
	public void deleteTask(String taskId, String deleteReason){
		taskService.deleteTask(taskId, deleteReason);
	}
	
	/**
	 * 签收任务
	 * @param taskId 任务ID
	 * @param userId 签收用户ID（用户登录名）
	 */
	public void claim(String taskId, String userId){
		taskService.claim(taskId, userId);
	}
	
	/**
	 * 提交任务, 并保存意见
	 * @param taskId 任务ID
	 * @param procInsId 流程实例ID，如果为空，则不保存任务提交意见
	 * @param comment 任务提交意见的内容
	 * @param vars 任务变量
	 */
	public void complete(String taskId, String procInsId, String comment, Map<String, Object> vars){
		complete(taskId, procInsId, comment, "", vars);
	}
	
	/**
	 * 提交任务, 并保存意见
	 * @param taskId 任务ID
	 * @param procInsId 流程实例ID，如果为空，则不保存任务提交意见
	 * @param comment 任务提交意见的内容
	 * @param title			流程标题，显示在待办任务标题
	 * @param vars 任务变量
	 */
	public void complete(String taskId, String procInsId, String comment, String title, Map<String, Object> vars){
		// 添加意见
		if (StringUtils.isNotBlank(procInsId) && StringUtils.isNotBlank(comment)){
			taskService.addComment(taskId, procInsId, comment);
		}
		
		// 设置流程变量
		if (vars == null){
			vars = Maps.newHashMap();
		}
		
		// 设置流程标题
		if (StringUtils.isNotBlank(title)){
			vars.put("title", title);
		}
		
		// 提交任务
//		taskService.setAssignee(taskId, "userId");//指定完成人ID
		taskService.complete(taskId, vars);
	}

	/**
	 * 完成第一个任务
	 * @param procInsId
	 */
	public void completeFirstTask(String procInsId){
		completeFirstTask(procInsId, null, null, null);
	}
	
	/**
	 * 完成第一个任务
	 * @param procInsId
	 * @param comment
	 * @param title
	 * @param vars
	 */
	public void completeFirstTask(String procInsId, String comment, String title, Map<String, Object> vars){
		String userId = UserUtils.getUser().getUserName();
		Task task = taskService.createTaskQuery().taskAssignee(userId).processInstanceId(procInsId).active().singleResult();
		if (task != null){
			complete(task.getId(), procInsId, comment, title, vars);
		}
	}

//	/**
//	 * 委派任务
//	 * @param taskId 任务ID
//	 * @param userId 被委派人
//	 */
//	public void delegateTask(String taskId, String userId){
//		taskService.delegateTask(taskId, userId);
//	}
//	
//	/**
//	 * 被委派人完成任务
//	 * @param taskId 任务ID
//	 */
//	public void resolveTask(String taskId){
//		taskService.resolveTask(taskId);
//	}
//	
//	/**
//	 * 回退任务
//	 * @param taskId
//	 */
//	public void backTask(String taskId){
//		taskService.
//	}
	
	////////////////////////////////////////////////////////////////////
	
	/**
	 * 读取带跟踪的图片
	 * @param executionId	环节ID
	 * @return	封装了各种节点信息
	 */
	public InputStream tracePhoto(String processDefinitionId, String executionId) {
		BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
		
		List<String> activeActivityIds = Lists.newArrayList();
		if (runtimeService.createExecutionQuery().executionId(executionId).count() > 0){
			activeActivityIds = runtimeService.getActiveActivityIds(executionId);
		}
		
		// 不使用spring请使用下面的两行代码
		// ProcessEngineImpl defaultProcessEngine = (ProcessEngineImpl)ProcessEngines.getDefaultProcessEngine();
		// Context.setProcessEngineConfiguration(defaultProcessEngine.getProcessEngineConfiguration());

		// 使用spring注入引擎请使用下面的这行代码
		processEngineConfiguration = processEngine.getProcessEngineConfiguration();
		Context.setProcessEngineConfiguration((ProcessEngineConfigurationImpl) processEngineConfiguration);
//		ProcessDiagramGenerator diagramGenerator = processEngineConfiguration.getProcessDiagramGenerator();
//        InputStream imageStream = diagramGenerator.generateDiagram(bpmnModel, "png", activeActivityIds);
		InputStream imageStream = processEngine.getProcessEngineConfiguration().getProcessDiagramGenerator()
			    .generateDiagram(bpmnModel, "png", activeActivityIds, activeActivityIds, 
			    		processEngine.getProcessEngineConfiguration().getActivityFontName(), 
			    		processEngine.getProcessEngineConfiguration().getLabelFontName(), 
			    		processEngine.getProcessEngineConfiguration().getAnnotationFontName(), 
			    		processEngine.getProcessEngineConfiguration().getClassLoader(), 1.0);
        return imageStream;
	}
	
	/**
	 * 流程跟踪图信息
	 * @param processInstanceId		流程实例ID
	 * @return	封装了各种节点信息
	 */
	public List<Map<String, Object>> traceProcess(String processInstanceId) throws Exception {
		Execution execution = runtimeService.createExecutionQuery().executionId(processInstanceId).singleResult();//执行实例
		Object property = PropertyUtils.getProperty(execution, "activityId");
		String activityId = "";
		if (property != null) {
			activityId = property.toString();
		}
		ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId)
				.singleResult();
		ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
				.getDeployedProcessDefinition(processInstance.getProcessDefinitionId());
		List<ActivityImpl> activitiList = processDefinition.getActivities();//获得当前任务的所有节点

		List<Map<String, Object>> activityInfos = new ArrayList<Map<String, Object>>();
		for (ActivityImpl activity : activitiList) {

			boolean currentActiviti = false;
			String id = activity.getId();

			// 当前节点
			if (id.equals(activityId)) {
				currentActiviti = true;
			}

			Map<String, Object> activityImageInfo = packageSingleActivitiInfo(activity, processInstance, currentActiviti);

			activityInfos.add(activityImageInfo);
		}

		return activityInfos;
	}

	/**
	 * 封装输出信息，包括：当前节点的X、Y坐标、变量信息、任务类型、任务描述
	 * @param activity
	 * @param processInstance
	 * @param currentActiviti
	 * @return
	 */
	private Map<String, Object> packageSingleActivitiInfo(ActivityImpl activity, ProcessInstance processInstance,
			boolean currentActiviti) throws Exception {
		Map<String, Object> vars = new HashMap<String, Object>();
		Map<String, Object> activityInfo = new HashMap<String, Object>();
		activityInfo.put("currentActiviti", currentActiviti);
		setPosition(activity, activityInfo);
		setWidthAndHeight(activity, activityInfo);

		Map<String, Object> properties = activity.getProperties();
		vars.put("节点名称", properties.get("name"));
		vars.put("任务类型", ActUtils.parseToZhType(properties.get("type").toString()));

		ActivityBehavior activityBehavior = activity.getActivityBehavior();
		logger.debug("activityBehavior={}", activityBehavior);
		if (activityBehavior instanceof UserTaskActivityBehavior) {

			Task currentTask = null;

			// 当前节点的task
			if (currentActiviti) {
				currentTask = getCurrentTaskInfo(processInstance);
			}

			// 当前任务的分配角色
			UserTaskActivityBehavior userTaskActivityBehavior = (UserTaskActivityBehavior) activityBehavior;
			TaskDefinition taskDefinition = userTaskActivityBehavior.getTaskDefinition();
			Set<Expression> candidateGroupIdExpressions = taskDefinition.getCandidateGroupIdExpressions();
			if (!candidateGroupIdExpressions.isEmpty()) {

				// 任务的处理角色
				setTaskGroup(vars, candidateGroupIdExpressions);

				// 当前处理人
				if (currentTask != null) {
					setCurrentTaskAssignee(vars, currentTask);
				}
			}
		}

		vars.put("节点说明", properties.get("documentation"));

		String description = activity.getProcessDefinition().getDescription();
		vars.put("描述", description);

		logger.debug("trace variables: {}", vars);
		activityInfo.put("vars", vars);
		return activityInfo;
	}

	/**
	 * 设置任务组
	 * @param vars
	 * @param candidateGroupIdExpressions
	 */
	private void setTaskGroup(Map<String, Object> vars, Set<Expression> candidateGroupIdExpressions) {
		String roles = "";
		for (Expression expression : candidateGroupIdExpressions) {
			String expressionText = expression.getExpressionText();
			String roleName = identityService.createGroupQuery().groupId(expressionText).singleResult().getName();
			roles += roleName;
		}
		vars.put("任务所属角色", roles);
	}

	/**
	 * 设置当前处理人信息
	 * @param vars
	 * @param currentTask
	 */
	private void setCurrentTaskAssignee(Map<String, Object> vars, Task currentTask) {
		String assignee = currentTask.getAssignee();
		if (assignee != null) {
			org.activiti.engine.identity.User assigneeUser = identityService.createUserQuery().userId(assignee).singleResult();
			String userInfo = assigneeUser.getFirstName() + " " + assigneeUser.getLastName();
			vars.put("当前处理人", userInfo);
		}
	}

	/**
	 * 获取当前节点信息
	 * @param processInstance
	 * @return
	 */
	private Task getCurrentTaskInfo(ProcessInstance processInstance) {
		Task currentTask = null;
		try {
			String activitiId = (String) PropertyUtils.getProperty(processInstance, "activityId");
			logger.debug("current activity id: {}", activitiId);

			currentTask = taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskDefinitionKey(activitiId)
					.singleResult();
			logger.debug("current task for processInstance: {}", ToStringBuilder.reflectionToString(currentTask));

		} catch (Exception e) {
			logger.error("can not get property activityId from processInstance: {}", processInstance);
		}
		return currentTask;
	}

	/**
	 * 设置宽度、高度属性
	 * @param activity
	 * @param activityInfo
	 */
	private void setWidthAndHeight(ActivityImpl activity, Map<String, Object> activityInfo) {
		activityInfo.put("width", activity.getWidth());
		activityInfo.put("height", activity.getHeight());
	}

	/**
	 * 设置坐标位置
	 * @param activity
	 * @param activityInfo
	 */
	private void setPosition(ActivityImpl activity, Map<String, Object> activityInfo) {
		activityInfo.put("x", activity.getX());
		activityInfo.put("y", activity.getY());
	}
	
}

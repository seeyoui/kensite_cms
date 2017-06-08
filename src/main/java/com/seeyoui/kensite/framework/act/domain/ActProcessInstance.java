package com.seeyoui.kensite.framework.act.domain;

import java.util.Map;

public class ActProcessInstance {
	private String id;
	private String processInstanceId;
	private String processDefinitionId;
	private String activityId;
	private String businessKey;
	private String deploymentId;
	private String name;
	private String parentId;
	private Map<String, Object> processVariables;
	private boolean suspended;
	private boolean ended;
	private ActProcessDefinition processDefinition;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	public String getProcessDefinitionId() {
		return processDefinitionId;
	}
	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	public String getBusinessKey() {
		return businessKey;
	}
	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}
	public String getDeploymentId() {
		return deploymentId;
	}
	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public Map<String, Object> getProcessVariables() {
		return processVariables;
	}
	public void setProcessVariables(Map<String, Object> processVariables) {
		this.processVariables = processVariables;
	}
	public ActProcessDefinition getProcessDefinition() {
		return processDefinition;
	}
	public void setProcessDefinition(ActProcessDefinition processDefinition) {
		this.processDefinition = processDefinition;
	}
	public boolean isSuspended() {
		return suspended;
	}
	public void setSuspended(boolean suspended) {
		this.suspended = suspended;
	}
	public boolean isEnded() {
		return ended;
	}
	public void setEnded(boolean ended) {
		this.ended = ended;
	}
	
	
}

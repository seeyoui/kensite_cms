package com.seeyoui.kensite.framework.act.domain;

import java.util.Date;
import java.util.Map;

public class ActHistoricProcessInstance {
	  private String getId;
	  private String getBusinessKey;
	  private String getProcessDefinitionId;
	  private Date getStartTime;
	  private Date getEndTime;
	  private Long getDurationInMillis;
	  private String getEndActivityId;
	  private String getStartUserId;
	  private String getStartActivityId;
	  private String getDeleteReason;
	  private String getSuperProcessInstanceId;
	  private String getTenantId;
	  private String getName;
	  private Map<String, Object> getProcessVariables;
	public String getGetId() {
		return getId;
	}
	public void setGetId(String getId) {
		this.getId = getId;
	}
	public String getGetBusinessKey() {
		return getBusinessKey;
	}
	public void setGetBusinessKey(String getBusinessKey) {
		this.getBusinessKey = getBusinessKey;
	}
	public String getGetProcessDefinitionId() {
		return getProcessDefinitionId;
	}
	public void setGetProcessDefinitionId(String getProcessDefinitionId) {
		this.getProcessDefinitionId = getProcessDefinitionId;
	}
	public Date getGetStartTime() {
		return getStartTime;
	}
	public void setGetStartTime(Date getStartTime) {
		this.getStartTime = getStartTime;
	}
	public Date getGetEndTime() {
		return getEndTime;
	}
	public void setGetEndTime(Date getEndTime) {
		this.getEndTime = getEndTime;
	}
	public Long getGetDurationInMillis() {
		return getDurationInMillis;
	}
	public void setGetDurationInMillis(Long getDurationInMillis) {
		this.getDurationInMillis = getDurationInMillis;
	}
	public String getGetEndActivityId() {
		return getEndActivityId;
	}
	public void setGetEndActivityId(String getEndActivityId) {
		this.getEndActivityId = getEndActivityId;
	}
	public String getGetStartUserId() {
		return getStartUserId;
	}
	public void setGetStartUserId(String getStartUserId) {
		this.getStartUserId = getStartUserId;
	}
	public String getGetStartActivityId() {
		return getStartActivityId;
	}
	public void setGetStartActivityId(String getStartActivityId) {
		this.getStartActivityId = getStartActivityId;
	}
	public String getGetDeleteReason() {
		return getDeleteReason;
	}
	public void setGetDeleteReason(String getDeleteReason) {
		this.getDeleteReason = getDeleteReason;
	}
	public String getGetSuperProcessInstanceId() {
		return getSuperProcessInstanceId;
	}
	public void setGetSuperProcessInstanceId(String getSuperProcessInstanceId) {
		this.getSuperProcessInstanceId = getSuperProcessInstanceId;
	}
	public String getGetTenantId() {
		return getTenantId;
	}
	public void setGetTenantId(String getTenantId) {
		this.getTenantId = getTenantId;
	}
	public String getGetName() {
		return getName;
	}
	public void setGetName(String getName) {
		this.getName = getName;
	}
	public Map<String, Object> getGetProcessVariables() {
		return getProcessVariables;
	}
	public void setGetProcessVariables(Map<String, Object> getProcessVariables) {
		this.getProcessVariables = getProcessVariables;
	}
	  
}

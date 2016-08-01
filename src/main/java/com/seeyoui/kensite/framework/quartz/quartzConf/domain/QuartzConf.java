/*
 * Powered By cuichen
 * Since 2014 - 2016
 */
package com.seeyoui.kensite.framework.quartz.quartzConf.domain;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.seeyoui.kensite.common.base.domain.DataEntity;
import com.seeyoui.kensite.common.util.excel.annotation.ExcelField;

/**
 * 定时任务计划配置
 * @author cuichen
 * @version 1.0
 * @since 1.0
 * @date 2016-04-08
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class QuartzConf extends DataEntity<QuartzConf> {
	private static final long serialVersionUID = 1L;

	@NotNull
	@ExcelField(title="任务名", type=1, align=2, sort=7, mod="JOB_QUARTZ_CONF|JOB_NAME")
	private String jobName;//任务名
	@NotNull
	@ExcelField(title="任务别名", type=1, align=2, sort=8, mod="JOB_QUARTZ_CONF|ALIAS_NAME")
	private String aliasName;//任务别名
	@NotNull
	@ExcelField(title="任务分组", type=1, align=2, sort=9, mod="JOB_QUARTZ_CONF|JOB_GROUP")
	private String jobGroup;//任务分组
	@ExcelField(title="任务触发器", type=1, align=2, sort=10, mod="JOB_QUARTZ_CONF|JOB_TRIGGER")
	private String jobTrigger;//任务触发器
//	@NotNull
	@ExcelField(title="cron表达式", type=1, align=2, sort=11, mod="JOB_QUARTZ_CONF|CRON_EXPRESSION")
	private String cronExpression;//cron表达式
	@ExcelField(title="是否异步", type=1, align=2, sort=12, mod="JOB_QUARTZ_CONF|IS_SYNC")
	private String isSync;//是否异步
	@ExcelField(title="任务描述", type=1, align=2, sort=13, mod="JOB_QUARTZ_CONF|DESCRIPTION")
	private String description;//任务描述
	@ExcelField(title="任务状态", type=1, align=2, sort=14, mod="JOB_QUARTZ_CONF|STATUS")
	private String status;//任务状态
	@NotNull
	@ExcelField(title="任务主体", type=1, align=2, sort=15, mod="JOB_QUARTZ_CONF|JOB_CLASS")
	private String jobClass;//任务主体
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@ExcelField(title="任务开始时间", type=1, align=2, sort=16, mod="JOB_QUARTZ_CONF|START_AT")
	private java.util.Date startAt;//任务开始时间
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@ExcelField(title="任务结束时间", type=1, align=2, sort=17, mod="JOB_QUARTZ_CONF|END_AT")
	private java.util.Date endAt;//任务结束时间

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobName() {
		return this.jobName;
	}
	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

	public String getAliasName() {
		return this.aliasName;
	}
	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	public String getJobGroup() {
		return this.jobGroup;
	}
	public void setJobTrigger(String jobTrigger) {
		this.jobTrigger = jobTrigger;
	}

	public String getJobTrigger() {
		return this.jobTrigger;
	}
	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public String getCronExpression() {
		return this.cronExpression;
	}
	public void setIsSync(String isSync) {
		this.isSync = isSync;
	}

	public String getIsSync() {
		return this.isSync;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return this.status;
	}
	public void setJobClass(String jobClass) {
		this.jobClass = jobClass;
	}

	public String getJobClass() {
		return this.jobClass;
	}
	public void setStartAt(java.util.Date startAt) {
		this.startAt = startAt;
	}

	public java.util.Date getStartAt() {
		return this.startAt;
	}
	public void setEndAt(java.util.Date endAt) {
		this.endAt = endAt;
	}

	public java.util.Date getEndAt() {
		return this.endAt;
	}
}
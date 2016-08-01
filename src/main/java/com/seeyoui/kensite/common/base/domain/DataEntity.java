package com.seeyoui.kensite.common.base.domain;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.seeyoui.kensite.common.util.GeneratorUUID;
import com.seeyoui.kensite.common.util.StringUtils;
import com.seeyoui.kensite.framework.system.domain.SysUser;
import com.seeyoui.kensite.framework.system.util.UserUtils;

/**
 * 数据Entity类
 * @author SeeYoui
 * @version 2014-05-16
 */
public abstract class DataEntity<T> extends BaseEntity<T> {

	private static final long serialVersionUID = 1L;
	
	protected String remarks; // 备注
	@JsonIgnore
	protected SysUser createUser; // 创建者
	protected Date createDate; // 创建日期
	@JsonIgnore
	protected SysUser updateUser; // 更新者
	protected Date updateDate; // 更新日期
	protected String delFlag; // 删除标记（0：正常；1：删除；2：审核）
	protected String bindId; // 流程相关绑定id
	protected String sort; //排序字段
	protected String order; //排序方式
	
	/**
	 * 插入之前执行方法，需要手动调用
	 */
	@Override
	public void preInsert(){
		if(StringUtils.isBlank(this.id)) {
			this.id = GeneratorUUID.getId();
		}
		SysUser sysUser = UserUtils.getUser();
		if (StringUtils.isNotBlank(sysUser.getUserName())){
			this.updateUser = sysUser;
			this.createUser = sysUser;
		}
		this.updateDate = new Date();
		this.createDate = this.updateDate;
	}
	
	/**
	 * 更新之前执行方法，需要手动调用
	 */
	@Override
	public void preUpdate(){
		SysUser SysUser = UserUtils.getUser();
		if (StringUtils.isNotBlank(SysUser.getUserName())){
			this.updateUser = SysUser;
		}
		this.updateDate = new Date();
	}
	
	@Length(min=0, max=255)
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	@JsonIgnore
	public SysUser getCreateUser() {
		return createUser;
	}

	public void setCreateUser(SysUser createUser) {
		this.createUser = createUser;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@JsonIgnore
	public SysUser getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(SysUser updateUser) {
		this.updateUser = updateUser;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@JsonIgnore
	@Length(min=1, max=1)
	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getBindId() {
		return bindId;
	}

	public void setBindId(String bindId) {
		this.bindId = bindId;
	}

	@JsonIgnore
	public String getSort() {
		return StringUtils.toUnderScoreCase(sort);
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	@JsonIgnore
	public String getOrder() {
		return this.order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

}

/*
 * Powered By cuichen
 * Since 2014 - 2015
 */
package com.seeyoui.kensite.framework.system.domain;

import com.seeyoui.kensite.common.base.domain.DataEntity;

/**
 * @author cuichen
 * @version 1.0
 * @since 1.0
 */
public class SysUserRole extends DataEntity<SysUserRole> {
	private static final long serialVersionUID = 5454155825314635342L;

	private String userId;
	private String roleId;

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleId() {
		return this.roleId;
	}
}
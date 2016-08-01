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
public class SysRoleModule extends DataEntity<SysRoleModule> {
	private static final long serialVersionUID = 5454155825314635342L;

	private String roleId;
	private String moduleId;

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleId() {
		return this.roleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getModuleId() {
		return this.moduleId;
	}
}
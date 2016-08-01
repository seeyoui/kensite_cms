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
public class SysModulePermission extends DataEntity<SysModulePermission> {
	private static final long serialVersionUID = 5454155825314635342L;

	private String moduleId;
	private String permissionId;

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getModuleId() {
		return this.moduleId;
	}

	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
	}

	public String getPermissionId() {
		return this.permissionId;
	}
}
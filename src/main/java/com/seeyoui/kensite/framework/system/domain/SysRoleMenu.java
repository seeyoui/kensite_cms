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
public class SysRoleMenu extends DataEntity<SysRoleMenu> {
	private static final long serialVersionUID = 5454155825314635342L;

	private String roleId;
	private String menuId;

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleId() {
		return this.roleId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getMenuId() {
		return this.menuId;
	}
}
/*
 * Powered By cuichen
 * Since 2014 - 2015
 */
package com.seeyoui.kensite.framework.system.persistence;  

import java.util.List;

import com.seeyoui.kensite.common.exception.CRUDException;
import com.seeyoui.kensite.framework.system.domain.SysMenu;
import com.seeyoui.kensite.framework.system.domain.SysRoleMenu;

/**
 * @author cuichen
 * @version 1.0
 * @since 1.0
 */
public interface SysRoleMenuMapper {

	/**
	 * 查询数据TREE
	 * @param map
	 * @return
	 * @throws CRUDException
	 */
	public List<SysMenu> tree(SysRoleMenu sysRoleMenu);
	
	/**
	 * 数据新增
	 * @param sysRoleMenu
	 */
	public void save(SysRoleMenu sysRoleMenu);
	
	/**
	 * 数据删除
	 * @param listId
	 */
	public void delete(String roleid);
}
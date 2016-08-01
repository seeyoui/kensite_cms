/*
 * Powered By cuichen
 * Since 2014 - 2015
 */
package com.seeyoui.kensite.framework.system.persistence;  

import java.util.List;

import com.seeyoui.kensite.common.exception.CRUDException;
import com.seeyoui.kensite.framework.system.domain.SysModulePermission;
import com.seeyoui.kensite.framework.system.domain.SysPermission;

/**
 * @author cuichen
 * @version 1.0
 * @since 1.0
 */
public interface SysModulePermissionMapper {

	/**
	 * 查询数据TREE
	 * @param map
	 * @return
	 * @throws CRUDException
	 */
	public List<SysPermission> tree(SysModulePermission sysModulePermission);
	
	/**
	 * 数据新增
	 * @param sysModulePermission
	 */
	public void save(SysModulePermission sysModulePermission);
	
	/**
	 * 数据删除
	 * @param listId
	 */
	public void delete(String roleid);
}
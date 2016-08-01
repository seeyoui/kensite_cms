/*
 * Powered By cuichen
 * Since 2014 - 2015
 */
package com.seeyoui.kensite.framework.system.persistence;  

import java.util.List;

import com.seeyoui.kensite.common.exception.CRUDException;
import com.seeyoui.kensite.framework.system.domain.SysModule;
import com.seeyoui.kensite.framework.system.domain.SysRoleModule;

/**
 * @author cuichen
 * @version 1.0
 * @since 1.0
 */
public interface SysRoleModuleMapper {

	/**
	 * 查询数据TREE
	 * @param map
	 * @return
	 * @throws CRUDException
	 */
	public List<SysModule> tree(SysRoleModule sysRoleModule);
	
	/**
	 * 数据新增
	 * @param sysRoleModule
	 */
	public void save(SysRoleModule sysRoleModule);
	
	/**
	 * 数据删除
	 * @param listId
	 */
	public void delete(String roleid);
}
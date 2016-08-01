/*
 * Powered By cuichen
 * Since 2014 - 2015
 */
package com.seeyoui.kensite.framework.system.persistence;  

import java.util.List;

import com.seeyoui.kensite.common.exception.CRUDException;
import com.seeyoui.kensite.framework.system.domain.SysRole;
import com.seeyoui.kensite.framework.system.domain.SysUserRole;

/**
 * @author cuichen
 * @version 1.0
 * @since 1.0
 */
public interface SysUserRoleMapper {

	/**
	 * 查询数据TREE
	 * @param map
	 * @return
	 * @throws CRUDException
	 */
	public List<SysRole> tree(SysUserRole sysUserRole);
	
	/**
	 * 数据新增
	 * @param sysUserRole
	 */
	public void save(SysUserRole sysUserRole);
	
	/**
	 * 数据删除
	 * @param listId
	 */
	public void delete(String userId);
}
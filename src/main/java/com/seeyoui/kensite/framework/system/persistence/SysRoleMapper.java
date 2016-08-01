/*
 * Powered By cuichen
 * Since 2014 - 2015
 */
package com.seeyoui.kensite.framework.system.persistence;  

import java.util.List;

import com.seeyoui.kensite.common.exception.CRUDException;
import com.seeyoui.kensite.framework.system.domain.SysRole;
import com.seeyoui.kensite.framework.system.domain.SysUser;

/**
 * @author cuichen
 * @version 1.0
 * @since 1.0
 */
public interface SysRoleMapper {

	/**
	 * 根据ID查询单条数据
	 * @param id
	 * @return
	 */
	public SysRole findOne(String id);
	
	/**
	 * 查询数据集合
	 * @param sysRole
	 * @return
	 */
	public List<SysRole> findList(SysRole sysRole);
	
	/**
	 * 查询数据集合
	 * @param sysRole
	 * @return
	 */
	public List<SysRole> findAll(SysRole sysRole);
	
	/**
	 * 查询用户权限集合
	 * @param map
	 * @return
	 * @throws CRUDException
	 */
	public List<SysRole> findSysUserList(SysUser sysUser);
	
	/**
	 * 查询数据总数
	 * @param userinfo
	 * @return
	 */
	public int findTotal(SysRole sysRole);
	
	/**
	 * 数据新增
	 * @param sysRole
	 */
	public void save(SysRole sysRole);
	
	/**
	 * 数据修改
	 * @param sysRole
	 */
	public void update(SysRole sysRole);
	
	/**
	 * 数据删除
	 * @param listId
	 */
	public void delete(List<String> listId);
}
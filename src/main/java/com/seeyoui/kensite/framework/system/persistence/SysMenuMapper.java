/*
 * Powered By cuichen
 * Since 2014 - 2015
 */
package com.seeyoui.kensite.framework.system.persistence;  

import java.util.List;

import com.seeyoui.kensite.common.exception.CRUDException;
import com.seeyoui.kensite.framework.system.domain.SysMenu;
import com.seeyoui.kensite.framework.system.domain.SysUser;

/**
 * @author cuichen
 * @version 1.0
 * @since 1.0
 */
public interface SysMenuMapper {

	/**
	 * 根据ID查询单条数据
	 * @param id
	 * @return
	 */
	public SysMenu findOne(String id);
	
	/**
	 * 查询数据集合
	 * @param sysMenu
	 * @return
	 */
	public List<SysMenu> findList(SysMenu sysMenu);
	
	/**
	 * 查询数据TREE
	 * @param sysUser
	 * @return
	 * @throws CRUDException
	 */
	public List<SysMenu> findTree(SysUser sysUser);
	
	/**
	 * 查询数据TREE
	 * @param sysMenu
	 * @return
	 * @throws CRUDException
	 */
	public List<SysMenu> findAll(SysMenu sysMenu);
	
	/**
	 * 查询数据总数
	 * @param sysMenu
	 * @return
	 */
	public Integer findTotal(SysMenu sysMenu);
	
	/**
	 * 数据新增
	 * @param sysMenu
	 */
	public void save(SysMenu sysMenu);
	
	/**
	 * 数据修改
	 * @param sysMenu
	 */
	public void update(SysMenu sysMenu);
	
	/**
	 * 数据删除
	 * @param listId
	 */
	public void delete(List<String> listId);
}
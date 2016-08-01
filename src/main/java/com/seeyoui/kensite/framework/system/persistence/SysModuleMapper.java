/*
 * Powered By cuichen
 * Since 2014 - 2015
 */
package com.seeyoui.kensite.framework.system.persistence;  

import java.util.List;

import com.seeyoui.kensite.framework.system.domain.SysModule;

/**
 * @author cuichen
 * @version 1.0
 * @since 1.0
 */
public interface SysModuleMapper {

	/**
	 * 根据ID查询单条数据
	 * @param id
	 * @return
	 */
	public SysModule findOne(String id);
	
	/**
	 * 查询数据集合
	 * @param sysModule
	 * @return
	 */
	public List<SysModule> findList(SysModule sysModule);
	
	/**
	 * 查询数据集合
	 * @param sysModule
	 * @return
	 */
	public List<SysModule> findAll(SysModule sysModule);
	
	/**
	 * 查询数据总数
	 * @param userinfo
	 * @return
	 */
	public int findTotal(SysModule sysModule);
	
	/**
	 * 数据新增
	 * @param sysModule
	 */
	public void save(SysModule sysModule);
	
	/**
	 * 数据修改
	 * @param sysModule
	 */
	public void update(SysModule sysModule);
	
	/**
	 * 数据删除
	 * @param listId
	 */
	public void delete(List<String> listId);
}
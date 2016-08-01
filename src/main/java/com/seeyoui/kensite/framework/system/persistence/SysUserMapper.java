/*
 * Powered By cuichen
 * Since 2014 - 2015
 */
package com.seeyoui.kensite.framework.system.persistence;  

import java.util.List;

import com.seeyoui.kensite.framework.system.domain.SysUser;

/**
 * @author cuichen
 * @version 1.0
 * @since 1.0
 */
public interface SysUserMapper {

	/**
	 * 根据ID查询单条数据
	 * @param id
	 * @return
	 */
	public SysUser findOne(String id);
	
	/**
	 * 根据账号查询单条数据
	 * @param userName
	 * @return
	 */
	public SysUser findByUserName(String userName);
	
	/**
	 * 查询某个角色的所有用户
	 * @param roleId
	 * @return
	 */
	public List<SysUser> findByRole(String roleId);
	
	/**
	 * 查询数据集合
	 * @param sysUser
	 * @return
	 */
	public List<SysUser> findList(SysUser sysUser);
	
	/**
	 * 查询所有数据集合
	 * @param sysUser
	 * @return
	 */
	public List<SysUser> findAll(SysUser sysUser);
	
	/**
	 * 查询数据总数
	 * @param userinfo
	 * @return
	 */
	public int findTotal(SysUser sysUser);
	
	/**
	 * 数据新增
	 * @param sysUser
	 */
	public void save(SysUser sysUser);
	
	/**
	 * 数据修改
	 * @param sysUser
	 */
	public void update(SysUser sysUser);
	
	/**
	 * 修改用户密码
	 * @param sysUser
	 */
	public void updatePassword(SysUser sysUser);
	
	/**
	 * 修改账号状态
	 * @param sysUser
	 */
	public void updateState(SysUser sysUser);
	
	/**
	 * 数据删除
	 * @param listId
	 */
	public void delete(List<String> listId);
}
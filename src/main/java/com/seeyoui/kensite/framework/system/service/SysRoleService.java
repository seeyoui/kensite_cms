/*
 * Powered By cuichen
 * Since 2014 - 2015
 */package com.seeyoui.kensite.framework.system.service;  
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seeyoui.kensite.common.base.service.BaseService;
import com.seeyoui.kensite.common.exception.CRUDException;
import com.seeyoui.kensite.framework.system.domain.SysRole;
import com.seeyoui.kensite.framework.system.domain.SysUser;
import com.seeyoui.kensite.framework.system.persistence.SysRoleMapper;

/**
 * @author cuichen
 * @version 1.0
 * @since 1.0
 */
@Service
public class SysRoleService extends BaseService {
	
	@Autowired
	private SysRoleMapper sysRoleMapper;

	/**
	 * 根据ID查询单条数据
	 * @param id
	 * @return
	 * @throws CRUDException
	 */
	public SysRole findOne(String id) throws CRUDException{
		return sysRoleMapper.findOne(id);
	}
	
	/**
	 * 查询数据集合
	 * @param sysRole
	 * @return
	 * @throws CRUDException
	 */
	public List<SysRole> findList(SysRole sysRole) throws CRUDException {
		return sysRoleMapper.findList(sysRole);
	}
	
	/**
	 * 查询数据集合
	 * @param sysRole
	 * @return
	 * @throws CRUDException
	 */
	public List<SysRole> findAll(SysRole sysRole) throws CRUDException {
		return sysRoleMapper.findAll(sysRole);
	}
	
	/**
	 * 查询用户权限集合
	 * @param sysRole
	 * @return
	 * @throws CRUDException
	 */
	public List<SysRole> findSysUserList(SysUser sysUser) throws CRUDException {
		return sysRoleMapper.findSysUserList(sysUser);
	}
	
	/**
	 * 查询数据总数
	 * @param userinfo
	 * @return
	 * @throws CRUDException
	 */
	public int findTotal(SysRole sysRole) throws CRUDException {
		return sysRoleMapper.findTotal(sysRole);
	}
	
	/**
	 * 数据新增
	 * @param sysRole
	 * @throws CRUDException
	 */
	public void save(SysRole sysRole) throws CRUDException{
		sysRole.preInsert();
		sysRoleMapper.save(sysRole);
	}
	
	/**
	 * 数据修改
	 * @param sysRole
	 * @throws CRUDException
	 */
	public void update(SysRole sysRole) throws CRUDException{
		sysRoleMapper.update(sysRole);			
	}
	
	/**
	 * 数据删除
	 * @param listId
	 * @throws CRUDException
	 */
	public void delete(List<String> listId) throws CRUDException {
		sysRoleMapper.delete(listId);
	}
	
}
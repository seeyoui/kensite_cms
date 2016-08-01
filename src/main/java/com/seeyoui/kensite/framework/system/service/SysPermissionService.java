/*
 * Powered By cuichen
 * Since 2014 - 2015
 */package com.seeyoui.kensite.framework.system.service;  
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seeyoui.kensite.common.base.domain.EasyUIDataGrid;
import com.seeyoui.kensite.common.base.service.BaseService;
import com.seeyoui.kensite.common.exception.CRUDException;
import com.seeyoui.kensite.framework.system.domain.SysPermission;
import com.seeyoui.kensite.framework.system.domain.SysUser;
import com.seeyoui.kensite.framework.system.persistence.SysPermissionMapper;

/**
 * @author cuichen
 * @version 1.0
 * @since 1.0
 */
@Service
public class SysPermissionService extends BaseService {
	
	@Autowired
	private SysPermissionMapper sysPermissionMapper;

	/**
	 * 根据ID查询单条数据
	 * @param id
	 * @return
	 * @throws CRUDException
	 */
	public SysPermission findOne(String id) throws CRUDException{
		return sysPermissionMapper.findOne(id);
	}
	
	/**
	 * 查询数据集合
	 * @param sysPermission
	 * @return
	 * @throws CRUDException
	 */
	public List<SysPermission> findList(SysPermission sysPermission) throws CRUDException {
		return sysPermissionMapper.findList(sysPermission);
	}
	
	/**
	 * 查询数据集合
	 * @param sysPermission
	 * @return
	 * @throws CRUDException
	 */
	public List<SysPermission> findAll(SysPermission sysPermission) throws CRUDException {
		return sysPermissionMapper.findAll(sysPermission);
	}
	
	/**
	 * 查询用户权限集合
	 * @param sysPermission
	 * @return
	 */
	public List<SysPermission> findSysUserList(SysUser sysUser) throws CRUDException {
		return sysPermissionMapper.findSysUserList(sysUser);
	}
	
	/**
	 * 查询数据总数
	 * @param userinfo
	 * @return
	 * @throws CRUDException
	 */
	public int findTotal(SysPermission sysPermission) throws CRUDException {
		return sysPermissionMapper.findTotal(sysPermission);
	}
	
	/**
	 * 数据新增
	 * @param sysPermission
	 * @throws CRUDException
	 */
	public void save(SysPermission sysPermission) throws CRUDException{
		sysPermissionMapper.save(sysPermission);
	}
	
	/**
	 * 数据修改
	 * @param sysPermission
	 * @throws CRUDException
	 */
	public void update(SysPermission sysPermission) throws CRUDException{
		sysPermissionMapper.update(sysPermission);			
	}
	
	/**
	 * 数据删除
	 * @param listId
	 * @throws CRUDException
	 */
	public void delete(List<String> listId) throws CRUDException {
		sysPermissionMapper.delete(listId);
	}
	
}
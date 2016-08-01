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
import com.seeyoui.kensite.common.util.GeneratorUUID;
import com.seeyoui.kensite.framework.system.domain.SysModule;
import com.seeyoui.kensite.framework.system.persistence.SysModuleMapper;
import com.seeyoui.kensite.framework.system.persistence.SysModulePermissionMapper;
import com.seeyoui.kensite.framework.system.persistence.SysRoleModuleMapper;

/**
 * @author cuichen
 * @version 1.0
 * @since 1.0
 */
@Service
public class SysModuleService extends BaseService {
	
	@Autowired
	private SysModuleMapper sysModuleMapper;
	@Autowired
	private SysModulePermissionMapper sysModulePermissionMapper;
	@Autowired
	private SysRoleModuleMapper sysRoleModuleMapper;

	/**
	 * 根据ID查询单条数据
	 * @param id
	 * @return
	 * @throws CRUDException
	 */
	public SysModule findOne(String id) throws CRUDException{
		return sysModuleMapper.findOne(id);
	}
	
	/**
	 * 查询数据集合
	 * @param sysModule
	 * @return
	 * @throws CRUDException
	 */
	public List<SysModule> findList(SysModule sysModule) throws CRUDException {
		return sysModuleMapper.findList(sysModule);
	}
	
	/**
	 * 查询数据集合
	 * @param sysModule
	 * @return
	 * @throws CRUDException
	 */
	public List<SysModule> findAll(SysModule sysModule) throws CRUDException {
		return sysModuleMapper.findAll(sysModule);
	}
	
	/**
	 * 查询数据总数
	 * @param userinfo
	 * @return
	 * @throws CRUDException
	 */
	public int findTotal(SysModule sysModule) throws CRUDException {
		return sysModuleMapper.findTotal(sysModule);
	}
	
	/**
	 * 数据新增
	 * @param sysModule
	 * @throws CRUDException
	 */
	public void save(SysModule sysModule) throws CRUDException{
		sysModule.preInsert();
		sysModuleMapper.save(sysModule);
	}
	
	/**
	 * 数据修改
	 * @param sysModule
	 * @throws CRUDException
	 */
	public void update(SysModule sysModule) throws CRUDException{
		sysModule.preUpdate();
		sysModuleMapper.update(sysModule);			
	}
	
	/**
	 * 数据删除
	 * @param listId
	 * @throws CRUDException
	 */
	public void delete(List<String> listId) throws CRUDException {
		for(int i=0; i<listId.size(); i++) {
			sysModulePermissionMapper.delete(listId.get(i));
			sysRoleModuleMapper.delete(listId.get(i));
		}
		sysModuleMapper.delete(listId);
	}
	
}
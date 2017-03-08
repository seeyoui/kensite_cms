/*
 * Powered By cuichen
 * Since 2014 - 2017
 */package com.seeyoui.kensite.framework.eshop.shop.service;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seeyoui.kensite.common.base.service.BaseService;

import java.util.*;

import com.seeyoui.kensite.common.base.domain.EasyUIDataGrid;
import com.seeyoui.kensite.common.base.service.BaseService;
import com.seeyoui.kensite.common.exception.CRUDException;
import com.seeyoui.kensite.common.util.*;
import com.seeyoui.kensite.common.constants.StringConstant;
import com.seeyoui.kensite.framework.eshop.shop.domain.Shop;
import com.seeyoui.kensite.framework.eshop.shop.persistence.ShopMapper;
import com.seeyoui.kensite.framework.system.constants.SysUserConstants;
import com.seeyoui.kensite.framework.system.domain.SysDepartment;
import com.seeyoui.kensite.framework.system.domain.SysUser;
import com.seeyoui.kensite.framework.system.domain.SysUserRole;
import com.seeyoui.kensite.framework.system.persistence.SysDepartmentMapper;
import com.seeyoui.kensite.framework.system.persistence.SysUserMapper;
import com.seeyoui.kensite.framework.system.persistence.SysUserRoleMapper;
import com.seeyoui.kensite.framework.system.util.UserUtils;

/**
 * 商铺
 * @author cuichen
 * @version 2.0
 * @since 1.0
 * @date 2017-03-07
 */

@Service
public class ShopService extends BaseService {
	
	@Autowired
	private ShopMapper shopMapper;
	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	@Autowired
	private SysDepartmentMapper sysDepartmentMapper;

	/**
	 * 根据ID查询单条数据
	 * @param id
	 * @return
	 * @throws CRUDException
	 */
	public Shop findOne(String id) throws CRUDException{
		return shopMapper.findOne(id);
	}
	
	/**
	 * 查询数据集合
	 * @param shop
	 * @return
	 * @throws CRUDException
	 */
	public List<Shop> findList(Shop shop) throws CRUDException {
		return shopMapper.findList(shop);
	}
	
	/**
	 * 查询所有数据集合
	 * @param shop
	 * @return
	 * @throws CRUDException
	 */
	public List<Shop> findAll(Shop shop) throws CRUDException {
		return shopMapper.findAll(shop);
	}
	
	/**
	 * 查询数据总数
	 * @param shop
	 * @return
	 * @throws CRUDException
	 */
	public int findTotal(Shop shop) throws CRUDException {
		return shopMapper.findTotal(shop);
	}
	
	/**
	 * 查询数据总数排除当前数据
	 * @param shop
	 * @return
	 * @throws CRUDException
	 */
	public int findExTotal(Shop shop) throws CRUDException {
		return shopMapper.findExTotal(shop);
	}
	
	/**
	 * 数据新增
	 * @param shop
	 * @throws CRUDException
	 */
	public String save(Shop shop) throws CRUDException{
		if(StringUtils.isBlank(shop.getUserName())) {
			return SysUserConstants.MESSAGE_ACCOUNT_ISNULL;
		}
		SysUser sysUserResult = sysUserMapper.findByUserName(shop.getUserName());
		if(sysUserResult != null && StringUtils.isNoneBlank(sysUserResult.getId())){
			return SysUserConstants.MESSAGE_ACCOUNT_EXIST;
		}
		shop.preInsert();
		shopMapper.save(shop);
		SysUser sysUser = new SysUser();
		sysUser.setId(shop.getId());
		sysUser.setDepartmentId(shop.getId());
		sysUser.setName(shop.getName());
		sysUser.setUserName(shop.getUserName());
		sysUser.setPassword(MD5.md5(sysUser.getUserName()+StringConstant.INIT_PASSWORD));
		sysUser.setState("1");
		sysUserMapper.save(sysUser);
		SysUserRole sysUserRole = new SysUserRole();
		sysUserRole.setUserId(shop.getId());
		sysUserRole.setRoleId("7c034fc80e8f4ab9b895523804326603");
		sysUserRoleMapper.save(sysUserRole);
		SysDepartment sysDepartment = new SysDepartment();
		sysDepartment.setId(shop.getId());
		sysDepartment.setName(shop.getName());
		sysDepartment.setParentId("08b8f74199a34dc898c01894f12d7abf");
		sysDepartment.setSequence(100L);
		sysDepartment.setCode("000000");
		sysDepartmentMapper.save(sysDepartment);
		return SysUserConstants.MESSAGE_SUCCESS;
	}
	
	/**
	 * 数据修改
	 * @param shop
	 * @throws CRUDException
	 */
	public void update(Shop shop) throws CRUDException{
		shop.preUpdate();
		shopMapper.update(shop);	
		SysUser sysUser = new SysUser();
		sysUser.setId(shop.getId());
		sysUser.setName(shop.getName());
		sysUserMapper.update(sysUser);
		SysDepartment sysDepartment = new SysDepartment();
		sysDepartment.setId(shop.getId());
		sysDepartment.setName(shop.getName());
		sysDepartmentMapper.update(sysDepartment);
		UserUtils.clearCache(sysUser);
	}
	
	/**
	 * 数据删除
	 * @param listId
	 * @throws CRUDException
	 */
	public void delete(List<String> listId) throws CRUDException {
		shopMapper.delete(listId);
	}
	
	/**
	 * 数据假删除
	 * @param shop
	 * @throws CRUDException
	 */
	public void remove(Shop shop) throws CRUDException{
		shop.preUpdate();
		shopMapper.remove(shop);			
	}
	
}
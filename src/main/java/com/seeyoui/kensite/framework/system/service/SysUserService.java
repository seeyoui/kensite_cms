/*
 * Powered By cuichen
 * Since 2014 - 2015
 */package com.seeyoui.kensite.framework.system.service;  
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seeyoui.kensite.common.base.domain.EasyUIDataGrid;
import com.seeyoui.kensite.common.base.service.BaseService;
import com.seeyoui.kensite.common.constants.StringConstant;
import com.seeyoui.kensite.common.exception.CRUDException;
import com.seeyoui.kensite.common.util.GeneratorUUID;
import com.seeyoui.kensite.common.util.MD5;
import com.seeyoui.kensite.framework.system.constants.SysUserConstants;
import com.seeyoui.kensite.framework.system.domain.SysUser;
import com.seeyoui.kensite.framework.system.persistence.SysUserMapper;
import com.seeyoui.kensite.framework.system.util.UserUtils;

/**
 * @author cuichen
 * @version 1.0
 * @since 1.0
 */
@Service
public class SysUserService extends BaseService {
	
	@Autowired
	private SysUserMapper sysUserMapper;

	/**
	 * 根据ID查询单条数据
	 * @param id
	 * @return
	 * @throws CRUDException
	 */
	public SysUser findOne(String id) throws CRUDException{
		return sysUserMapper.findOne(id);
	}
	
	/**
	 * 根据账号查询单条数据
	 * @param username
	 * @return
	 * @throws CRUDException
	 */
	public SysUser findByUserName(String username) throws CRUDException{
		return sysUserMapper.findByUserName(username);
	}
	
	/**
	 * 查询某个角色的所有用户
	 * @param roleid
	 * @return
	 * @throws CRUDException
	 */
	public List<SysUser> findByRole(String roleid) throws CRUDException{
		return sysUserMapper.findByRole(roleid);
	}
	
	/**
	 * 查询数据集合
	 * @param sysUser
	 * @return
	 * @throws CRUDException
	 */
	public List<SysUser> findList(SysUser sysUser) throws CRUDException {
		return sysUserMapper.findList(sysUser);
	}
	
	/**
	 * 查询所有数据集合
	 * @param sysUser
	 * @return
	 * @throws CRUDException
	 */
	public List<SysUser> findAll(SysUser sysUser) throws CRUDException {
		return sysUserMapper.findAll(sysUser);
	}
	
	/**
	 * 查询数据总数
	 * @param userinfo
	 * @return
	 * @throws CRUDException
	 */
	public int findTotal(SysUser sysUser) throws CRUDException {
		return sysUserMapper.findTotal(sysUser);
	}
	
	/**
	 * 数据新增
	 * @param sysUser
	 * @throws CRUDException
	 */
	public String save(SysUser sysUser) throws CRUDException{
		/*
		 *	判断用户名称是否为空。
		 *	(1)为空则直接返回提示信息；
		 *	(2)不为空，则根据名称查询用户，如果得到返回结果，证明系统中存在此用户，
		 *		直接返回提示信息，需要用户重新输入用户名称
		 * 
		 */
		if(sysUser.getUserName() != null && !sysUser.getUserName().equals("")){
			SysUser sysUserResult = sysUserMapper.findByUserName(sysUser.getUserName());
			if(sysUserResult != null 
				&& (sysUserResult.getId() != null && !sysUserResult.getId().equals(""))){
				return SysUserConstants.MESSAGE_ACCOUNT_EXIST;
			}
		}else{
			return SysUserConstants.MESSAGE_ACCOUNT_ISNULL;
		}
		sysUser.setId(GeneratorUUID.getId());
		sysUser.setState(StringConstant.STATE_ENABLE);
		if(sysUser.getPassword() != null && !"".equals(sysUser.getPassword()) && !"null".equals(sysUser.getPassword())) {
			sysUser.setPassword(MD5.md5(sysUser.getUserName()+sysUser.getPassword()));
		} else {
			sysUser.setPassword(MD5.md5(sysUser.getUserName()+StringConstant.INIT_PASSWORD));
		}
		sysUserMapper.save(sysUser);
		return SysUserConstants.MESSAGE_SUCCESS;
	}
	
	/**
	 * 数据修改
	 * @param sysUser
	 * @throws CRUDException
	 */
	public void update(SysUser sysUser) throws CRUDException{
		UserUtils.clearCache(sysUser);
		sysUserMapper.update(sysUser);			
	}
	
	/**
	 * 修改用户密码
	 * @param sysUser
	 * @throws CRUDException
	 */
	public void updatePassword(SysUser sysUser) throws CRUDException{
		UserUtils.clearCache(sysUser);
		sysUserMapper.updatePassword(sysUser);			
	}
	
	/**
	 * 修改账号状态
	 * @param sysUser
	 * @throws CRUDException
	 */
	public void updateState(SysUser sysUser) throws CRUDException{
		UserUtils.clearCache(sysUser);
		sysUserMapper.updateState(sysUser);			
	}
	
	/**
	 * 数据删除
	 * @param listId
	 * @throws CRUDException
	 */
	public void delete(List<String> listId) throws CRUDException {
		sysUserMapper.delete(listId);
	}
	
}
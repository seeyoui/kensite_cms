/*
 * Powered By cuichen
 * Since 2014 - 2015
 */package com.seeyoui.kensite.framework.system.service;  
 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seeyoui.kensite.common.base.domain.Attributes;
import com.seeyoui.kensite.common.base.domain.TreeJson;
import com.seeyoui.kensite.common.base.service.BaseService;
import com.seeyoui.kensite.common.constants.StringConstant;
import com.seeyoui.kensite.common.exception.CRUDException;
import com.seeyoui.kensite.common.util.StringUtils;
import com.seeyoui.kensite.framework.system.domain.SysRole;
import com.seeyoui.kensite.framework.system.domain.SysUserRole;
import com.seeyoui.kensite.framework.system.persistence.SysUserRoleMapper;

/**
 * @author cuichen
 * @version 1.0
 * @since 1.0
 */
@Service
public class SysUserRoleService extends BaseService {
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;

	/**
	 * 查询数据TREE
	 * @param username
	 * @return
	 * @throws CRUDException
	 */
	public List<TreeJson> tree(SysUserRole sysUserRole) throws CRUDException {
		List<SysRole> mList = sysUserRoleMapper.tree(sysUserRole);
		List<TreeJson> tList = new ArrayList<TreeJson>();
		for(int i=0; i<mList.size(); i++) {
			TreeJson tj = new TreeJson();
			tj.setId(mList.get(i).getId());
			tj.setText(mList.get(i).getName());
			tj.setPid(StringConstant.ROOT_ID_32);
			tj.setChecked(mList.get(i).getShiro());
			Attributes attributes = new Attributes();
			tj.setAttributes(attributes);
			tList.add(tj);
		}
		TreeJson root = new TreeJson();
		root.setId(StringConstant.ROOT_ID_32);
		TreeJson.getTree(tList, root);
		return root.getChildren();
	}
	
	/**
	 * 数据新增
	 * @param sysUserRole
	 * @throws CRUDException
	 */
	public void save(SysUserRole sysUserRole) throws CRUDException {
		sysUserRoleMapper.delete(sysUserRole.getUserId());
		if(sysUserRole.getRoleId() == null || StringUtils.isBlank(sysUserRole.getRoleId())) {
			return;
		}
		List<String> listId = Arrays.asList(sysUserRole.getRoleId().split(","));
		for(int i=0; i<listId.size(); i++) {
			sysUserRole.setRoleId(listId.get(i));
			sysUserRoleMapper.save(sysUserRole);
		}
	}
}
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
import com.seeyoui.kensite.framework.system.domain.SysMenu;
import com.seeyoui.kensite.framework.system.domain.SysRoleMenu;
import com.seeyoui.kensite.framework.system.persistence.SysRoleMenuMapper;

/**
 * @author cuichen
 * @version 1.0
 * @since 1.0
 */
@Service
public class SysRoleMenuService extends BaseService {
	
	@Autowired
	private SysRoleMenuMapper sysRoleMenuMapper;

	/**
	 * 查询数据TREE
	 * @param username
	 * @return
	 * @throws CRUDException
	 */
	public List<TreeJson> tree(SysRoleMenu sysRoleMenu) throws CRUDException {
		List<SysMenu> mList = sysRoleMenuMapper.tree(sysRoleMenu);
		List<TreeJson> tList = new ArrayList<TreeJson>();
		for(int i=0; i<mList.size(); i++) {
			TreeJson tj = new TreeJson();
			tj.setId(mList.get(i).getId());
			tj.setPid(mList.get(i).getParentId());
			tj.setText(mList.get(i).getName());
			tj.setChecked(mList.get(i).getTarget());
			Attributes attributes = new Attributes();
			attributes.setUrl(mList.get(i).getUrl());
			attributes.setIcon(mList.get(i).getIcon());
			tj.setAttributes(attributes);
			tList.add(tj);
		}
		TreeJson root = new TreeJson();
		root.setId(StringConstant.ROOT_ID_32);
		root.setPid("");
		TreeJson.getTree(tList, root);
		return root.getChildren();
	}
	
	/**
	 * 数据新增
	 * @param sysRoleMenu
	 * @throws CRUDException
	 */
	public void save(SysRoleMenu sysRoleMenu) throws CRUDException {
		sysRoleMenuMapper.delete(sysRoleMenu.getRoleId());
		if(sysRoleMenu.getMenuId() == null || StringUtils.isBlank(sysRoleMenu.getMenuId())) {
			return;
		}
		List<String> listId = Arrays.asList(sysRoleMenu.getMenuId().split(","));
		for(int i=0; i<listId.size(); i++) {
			sysRoleMenu.setMenuId(listId.get(i));
			sysRoleMenuMapper.save(sysRoleMenu);
		}
	}
	
}
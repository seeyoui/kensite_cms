/*
 * Powered By cuichen
 * Since 2014 - 2015
 */package com.seeyoui.kensite.framework.system.service;  
 
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seeyoui.kensite.common.base.domain.Attributes;
import com.seeyoui.kensite.common.base.domain.TreeJson;
import com.seeyoui.kensite.common.base.service.BaseService;
import com.seeyoui.kensite.common.constants.StringConstant;
import com.seeyoui.kensite.common.exception.CRUDException;
import com.seeyoui.kensite.framework.system.domain.SysMenu;
import com.seeyoui.kensite.framework.system.domain.SysUser;
import com.seeyoui.kensite.framework.system.persistence.SysMenuMapper;
import com.seeyoui.kensite.framework.system.util.UserUtils;

/**
 * @author cuichen
 * @version 1.0
 * @since 1.0
 */
@Service
public class SysMenuService extends BaseService {
	
	@Autowired
	private SysMenuMapper sysMenuMapper;

	/**
	 * 根据ID查询单条数据
	 * @param id
	 * @return
	 * @throws CRUDException
	 */
	public SysMenu findOne(String id) throws CRUDException{
		return sysMenuMapper.findOne(id);
	}
	
	/**
	 * 查询数据集合
	 * @param sysMenu
	 * @return
	 * @throws CRUDException
	 */
	public List<SysMenu> findList(SysMenu sysMenu) throws CRUDException {
		return sysMenuMapper.findList(sysMenu);
	}
	
	/**
	 * 查询数据集合
	 * @param sysMenu
	 * @return
	 * @throws CRUDException
	 */
	public List<SysMenu> findAll(SysMenu sysMenu) throws CRUDException {
		return sysMenuMapper.findAll(sysMenu);
	}
	
	/**
	 * 查询数据集合
	 * @param sysMenu
	 * @return
	 * @throws CRUDException
	 */
	public Integer findTotal(SysMenu sysMenu) throws CRUDException {
		return sysMenuMapper.findTotal(sysMenu);
	}
	
	/**
	 * 查询数据TREE
	 * @param username
	 * @return
	 * @throws CRUDException
	 */
	public List<TreeJson> findTree(SysUser sysUser) throws CRUDException {
		TreeJson root = UserUtils.getMenuTree();
		return root.getChildren();
	}
	
	/**
	 * 查询数据TREE
	 * @param username
	 * @return
	 * @throws CRUDException
	 */
	public List<TreeJson> tree() throws CRUDException {
		List<SysMenu> mList = sysMenuMapper.findAll(null);
		List<TreeJson> tList = new ArrayList<TreeJson>();
		for(int i=0; i<mList.size(); i++) {
			TreeJson tj = new TreeJson();
			tj.setId(mList.get(i).getId());
			tj.setPid(mList.get(i).getParentId());
			tj.setText(mList.get(i).getName());
			Attributes attributes = new Attributes();
			attributes.setUrl(mList.get(i).getUrl());
			attributes.setIcon(mList.get(i).getIcon());
			tj.setAttributes(attributes);
			tList.add(tj);
		}
		TreeJson root = new TreeJson();
		root.setText("导航菜单");
		root.setId(StringConstant.ROOT_ID_32);
		TreeJson.getTree(tList, root);
		tList.clear();
		tList.add(root);
		return tList;
	}
	
	/**
	 * 数据新增
	 * @param sysMenu
	 * @throws CRUDException
	 */
	public void save(SysMenu sysMenu) throws CRUDException{
		sysMenu.preInsert();
		sysMenuMapper.save(sysMenu);
	}
	
	/**
	 * 数据修改
	 * @param sysMenu
	 * @throws CRUDException
	 */
	public void update(SysMenu sysMenu) throws CRUDException{
		sysMenu.preUpdate();
		sysMenuMapper.update(sysMenu);			
	}
	
	/**
	 * 数据删除
	 * @param listId
	 * @throws CRUDException
	 */
	public void delete(List<String> listId) throws CRUDException {
		sysMenuMapper.delete(listId);
	}
	
}
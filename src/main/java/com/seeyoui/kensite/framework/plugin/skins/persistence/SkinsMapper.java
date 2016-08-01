/*
 * Powered By cuichen
 * Since 2014 - 2015
 */
package com.seeyoui.kensite.framework.plugin.skins.persistence;  

import com.seeyoui.kensite.common.base.domain.EasyUIDataGrid;
import com.seeyoui.kensite.framework.plugin.skins.domain.Skins;
import java.util.*;

/**
 * @author cuichen
 * @version 1.0
 * @since 1.0
 */
public interface SkinsMapper {

	/**
	 * 根据ID查询单条数据
	 * @param id
	 * @return
	 */
	public Skins findOne(String id);
	
	/**
	 * 查询数据集合
	 * @param skins
	 * @return
	 */
	public List<Skins> findList(Skins skins);
	
	/**
	 * 查询所有数据集合
	 * @param skins
	 * @return
	 */
	public List<Skins> findAll(Skins skins);
	
	/**
	 * 查询数据总数
	 * @param skins
	 * @return
	 */
	public int findTotal(Skins skins);
	
	/**
	 * 数据新增
	 * @param skins
	 */
	public void save(Skins skins);
	
	/**
	 * 数据修改
	 * @param skins
	 */
	public void update(Skins skins);
	
	/**
	 * 数据删除
	 * @param listId
	 */
	public void delete(List<String> listId);
	
	/**
	 * 查询当前系统皮肤
	 * @return
	 */
	public Skins findCurrent();
	
	/**
	 * 选中
	 * @param skins
	 */
	public void chose(Skins skins);
	
	/**
	 * 清空
	 * @param skins
	 */
	public void unchose(Skins skins);
}
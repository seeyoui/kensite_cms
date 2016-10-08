/*
 * Powered By cuichen
 * Since 2014 - 2016
 */
package com.seeyoui.kensite.framework.plugin.connCenter.persistence;

import com.seeyoui.kensite.common.base.domain.EasyUIDataGrid;
import com.seeyoui.kensite.framework.plugin.connCenter.domain.ConnCenter;

import java.util.*;

import com.seeyoui.kensite.common.exception.CRUDException;

/**
 * 数据库连接中心
 * @author cuichen
 * @version 2.0
 * @since 1.0
 * @date 2016-09-27
 */

public interface ConnCenterMapper {

	/**
	 * 根据ID查询单条数据
	 * @param id
	 * @return
	 */
	public ConnCenter findOne(String id);
	
	/**
	 * 查询数据集合
	 * @param connCenter
	 * @return
	 */
	public List<ConnCenter> findList(ConnCenter connCenter);
	
	/**
	 * 查询所有数据集合
	 * @param connCenter
	 * @return
	 */
	public List<ConnCenter> findAll(ConnCenter connCenter);
	
	/**
	 * 查询数据总数
	 * @param connCenter
	 * @return
	 */
	public int findTotal(ConnCenter connCenter);

	/**
	 * 查询数据总数排除当前数据
	 * @param connCenter
	 * @return
	 */
	public int findExTotal(ConnCenter connCenter);
	
	/**
	 * 数据新增
	 * @param connCenter
	 */
	public void save(ConnCenter connCenter);
	
	/**
	 * 数据修改
	 * @param connCenter
	 */
	public void update(ConnCenter connCenter);
	
	/**
	 * 数据删除
	 * @param listId
	 */
	public void delete(List<String> listId);
}
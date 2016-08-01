/*
 * Powered By cuichen
 * Since 2014 - 2015
 */
package com.seeyoui.kensite.framework.mod.table.persistence;  

import com.seeyoui.kensite.common.base.domain.EasyUIDataGrid;
import com.seeyoui.kensite.framework.mod.table.domain.Table;
import java.util.*;

/**
 * @author cuichen
 * @version 1.0
 * @since 1.0
 * @date 2015-09-22
 */
public interface TableMapper {

	/**
	 * 根据ID查询单条数据
	 * @param id
	 * @return
	 */
	public Table findOne(String id);
	
	/**
	 * 查询数据集合
	 * @param table
	 * @return
	 */
	public List<Table> findList(Table table);
	
	/**
	 * 查询所有数据集合
	 * @param table
	 * @return
	 */
	public List<Table> findAll(Table table);
	
	/**
	 * 查询数据总数
	 * @param table
	 * @return
	 */
	public int findTotal(Table table);
	
	/**
	 * 数据新增
	 * @param table
	 */
	public void save(Table table);
	
	/**
	 * 数据修改
	 * @param table
	 */
	public void update(Table table);
	
	/**
	 * 数据外键关联修改
	 * @param table
	 */
	public void updateFk(Table table);
	
	/**
	 * 数据删除
	 * @param listId
	 */
	public void delete(List<String> listId);
}
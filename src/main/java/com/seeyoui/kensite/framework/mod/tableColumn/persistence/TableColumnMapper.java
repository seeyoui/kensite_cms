/*
 * Powered By cuichen
 * Since 2014 - 2015
 */
package com.seeyoui.kensite.framework.mod.tableColumn.persistence;  

import com.seeyoui.kensite.common.base.domain.EasyUIDataGrid;
import com.seeyoui.kensite.framework.mod.tableColumn.domain.TableColumn;
import java.util.*;

/**
 * 业务表字段
 * @author cuichen
 * @version 1.0
 * @since 1.0
 * @date 2015-10-24
 */
public interface TableColumnMapper {
	
	/**
	 * 查询单条数据
	 * @param id
	 * @return
	 */
	public TableColumn findById(String id);

	/**
	 * 查询单条数据
	 * @param tableColumn
	 * @return
	 */
	public TableColumn findOne(TableColumn tableColumn);
	
	/**
	 * 查询数据集合
	 * @param tableColumn
	 * @return
	 */
	public List<TableColumn> findList(TableColumn tableColumn);
	
	/**
	 * 查询所有数据集合
	 * @param tableColumn
	 * @return
	 */
	public List<TableColumn> findAll(TableColumn tableColumn);
	
	/**
	 * 查询数据总数
	 * @param tableColumn
	 * @return
	 */
	public int findTotal(TableColumn tableColumn);
	
	/**
	 * 数据新增
	 * @param tableColumn
	 */
	public void save(TableColumn tableColumn);
	
	/**
	 * 数据修改
	 * @param tableColumn
	 */
	public void update(TableColumn tableColumn);
	
	/**
	 * 表名重命名
	 * @param tableColumn
	 */
	public void rename(TableColumn tableColumn);
	
	/**
	 * 数据删除
	 * @param listId
	 */
	public void delete(List<String> listId);
}
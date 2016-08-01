/*
 * Powered By cuichen
 * Since 2014 - 2015
 */
package com.seeyoui.kensite.framework.mod.db.persistence;  

import com.seeyoui.kensite.framework.mod.table.domain.Table;
import com.seeyoui.kensite.framework.mod.tableColumn.domain.TableColumn;

/**
 * @author cuichen
 * @version 1.0
 * @since 1.0
 * @date 2015-09-22
 */
public interface DBMapper {

	/**
	 * 创建表
	 * @param table
	 */
	public void createTable(Table table);
	
	/**
	 * 表重命名
	 * @param table
	 */
	public void renameTable(Table table);
	
	/**
	 * 给表添加注释
	 * @param table
	 */
	public void commentTable(Table table);
	
	/**
	 * 删除表
	 * @param table
	 */
	public void dropTable(Table table);
	
	/**
	 * 增加字段
	 * @param tableColumn
	 */
	public void addColumn(TableColumn tableColumn);

	/**
	 * 修改字段
	 * @param tableColumn
	 */
	public void modifyColumn(TableColumn tableColumn);

	/**
	 * 字段重命名
	 * @param tableColumn
	 */
	public void renameColumn(TableColumn tableColumn);
	
	/**
	 * 删除字段
	 * @param tableColumn
	 */
	public void dropColumn(TableColumn tableColumn);

	/**
	 * 给表添加注释
	 * @param tableColumn
	 */
	public void commentColumn(TableColumn tableColumn);

	/**
	 * 给表添加主键
	 * @param tableColumn
	 */
	public void addPrimaryKey(TableColumn tableColumn);

	/**
	 * 给表删除主键
	 * @param tableColumn
	 */
	public void dropPrimaryKey(TableColumn tableColumn);
}
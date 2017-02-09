/*
 * Powered By cuichen
 * Since 2014 - 2017
 */
package com.seeyoui.kensite.framework.report.dataSource.persistence;

import com.seeyoui.kensite.common.base.domain.EasyUIDataGrid;
import com.seeyoui.kensite.framework.report.dataSource.domain.DataSource;

import java.util.*;

import com.seeyoui.kensite.common.exception.CRUDException;

/**
 * 数据源
 * @author cuichen
 * @version 2.0
 * @since 1.0
 * @date 2017-02-08
 */

public interface DataSourceMapper {

	/**
	 * 根据ID查询单条数据
	 * @param id
	 * @return
	 */
	public DataSource findOne(String id);
	
	/**
	 * 查询数据集合
	 * @param dataSource
	 * @return
	 */
	public List<DataSource> findList(DataSource dataSource);
	
	/**
	 * 查询所有数据集合
	 * @param dataSource
	 * @return
	 */
	public List<DataSource> findAll(DataSource dataSource);
	
	/**
	 * 查询数据总数
	 * @param dataSource
	 * @return
	 */
	public int findTotal(DataSource dataSource);

	/**
	 * 查询数据总数排除当前数据
	 * @param dataSource
	 * @return
	 */
	public int findExTotal(DataSource dataSource);
	
	/**
	 * 数据新增
	 * @param dataSource
	 */
	public void save(DataSource dataSource);
	
	/**
	 * 数据修改
	 * @param dataSource
	 */
	public void update(DataSource dataSource);
	
	/**
	 * 数据删除
	 * @param listId
	 */
	public void delete(List<String> listId);
}
/*
 * Powered By cuichen
 * Since 2014 - 2017
 */
package com.seeyoui.kensite.framework.report.chartEngine.persistence;

import com.seeyoui.kensite.common.base.domain.EasyUIDataGrid;
import com.seeyoui.kensite.framework.report.chartEngine.domain.ChartEngine;

import java.util.*;

import com.seeyoui.kensite.common.exception.CRUDException;

/**
 * 统计图表
 * @author cuichen
 * @version 2.0
 * @since 1.0
 * @date 2017-01-20
 */

public interface ChartEngineMapper {

	/**
	 * 根据ID查询单条数据
	 * @param id
	 * @return
	 */
	public ChartEngine findOne(String id);
	
	/**
	 * 查询数据集合
	 * @param chartEngine
	 * @return
	 */
	public List<ChartEngine> findList(ChartEngine chartEngine);
	
	/**
	 * 查询所有数据集合
	 * @param chartEngine
	 * @return
	 */
	public List<ChartEngine> findAll(ChartEngine chartEngine);
	
	/**
	 * 查询数据总数
	 * @param chartEngine
	 * @return
	 */
	public int findTotal(ChartEngine chartEngine);

	/**
	 * 查询数据总数排除当前数据
	 * @param chartEngine
	 * @return
	 */
	public int findExTotal(ChartEngine chartEngine);
	
	/**
	 * 数据新增
	 * @param chartEngine
	 */
	public void save(ChartEngine chartEngine);
	
	/**
	 * 数据修改
	 * @param chartEngine
	 */
	public void update(ChartEngine chartEngine);
	
	/**
	 * 数据删除
	 * @param listId
	 */
	public void delete(List<String> listId);
}
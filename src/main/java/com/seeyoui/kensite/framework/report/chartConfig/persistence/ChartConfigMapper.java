/*
 * Powered By cuichen
 * Since 2014 - 2016
 */
package com.seeyoui.kensite.framework.report.chartConfig.persistence;

import com.seeyoui.kensite.common.base.domain.EasyUIDataGrid;
import com.seeyoui.kensite.framework.report.chartConfig.domain.ChartConfig;

import java.util.*;

import com.seeyoui.kensite.common.exception.CRUDException;

/**
 * 统计图表
 * @author cuichen
 * @version 2.0
 * @since 1.0
 * @date 2016-09-05
 */

public interface ChartConfigMapper {

	/**
	 * 根据ID查询单条数据
	 * @param id
	 * @return
	 */
	public ChartConfig findOne(String id);
	
	/**
	 * 查询数据集合
	 * @param chartConfig
	 * @return
	 */
	public List<ChartConfig> findList(ChartConfig chartConfig);
	
	/**
	 * 查询所有数据集合
	 * @param chartConfig
	 * @return
	 */
	public List<ChartConfig> findAll(ChartConfig chartConfig);
	
	/**
	 * 查询数据总数
	 * @param chartConfig
	 * @return
	 */
	public int findTotal(ChartConfig chartConfig);

	/**
	 * 查询数据总数排除当前数据
	 * @param chartConfig
	 * @return
	 */
	public int findExTotal(ChartConfig chartConfig);
	
	/**
	 * 数据新增
	 * @param chartConfig
	 */
	public void save(ChartConfig chartConfig);
	
	/**
	 * 数据修改
	 * @param chartConfig
	 */
	public void update(ChartConfig chartConfig);
	
	/**
	 * 数据删除
	 * @param listId
	 */
	public void delete(List<String> listId);
}
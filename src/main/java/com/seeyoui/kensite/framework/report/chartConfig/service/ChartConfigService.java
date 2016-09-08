/*
 * Powered By cuichen
 * Since 2014 - 2016
 */package com.seeyoui.kensite.framework.report.chartConfig.service;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seeyoui.kensite.common.base.service.BaseService;

import java.util.*;

import com.seeyoui.kensite.common.base.domain.EasyUIDataGrid;
import com.seeyoui.kensite.common.base.service.BaseService;
import com.seeyoui.kensite.common.exception.CRUDException;
import com.seeyoui.kensite.common.util.*;
import com.seeyoui.kensite.common.constants.StringConstant;
import com.seeyoui.kensite.framework.report.chartConfig.domain.ChartConfig;
import com.seeyoui.kensite.framework.report.chartConfig.persistence.ChartConfigMapper;

/**
 * 统计图表
 * @author cuichen
 * @version 2.0
 * @since 1.0
 * @date 2016-09-05
 */

@Service
public class ChartConfigService extends BaseService {
	
	@Autowired
	private ChartConfigMapper chartConfigMapper;

	/**
	 * 根据ID查询单条数据
	 * @param id
	 * @return
	 * @throws CRUDException
	 */
	public ChartConfig findOne(String id) throws CRUDException{
		return chartConfigMapper.findOne(id);
	}
	
	/**
	 * 查询数据集合
	 * @param chartConfig
	 * @return
	 * @throws CRUDException
	 */
	public List<ChartConfig> findList(ChartConfig chartConfig) throws CRUDException {
		return chartConfigMapper.findList(chartConfig);
	}
	
	/**
	 * 查询所有数据集合
	 * @param chartConfig
	 * @return
	 * @throws CRUDException
	 */
	public List<ChartConfig> findAll(ChartConfig chartConfig) throws CRUDException {
		return chartConfigMapper.findAll(chartConfig);
	}
	
	/**
	 * 查询数据总数
	 * @param chartConfig
	 * @return
	 * @throws CRUDException
	 */
	public int findTotal(ChartConfig chartConfig) throws CRUDException {
		return chartConfigMapper.findTotal(chartConfig);
	}
	
	/**
	 * 查询数据总数排除当前数据
	 * @param chartConfig
	 * @return
	 * @throws CRUDException
	 */
	public int findExTotal(ChartConfig chartConfig) throws CRUDException {
		return chartConfigMapper.findExTotal(chartConfig);
	}
	
	/**
	 * 数据新增
	 * @param chartConfig
	 * @throws CRUDException
	 */
	public void save(ChartConfig chartConfig) throws CRUDException{
		chartConfig.preInsert();
		chartConfigMapper.save(chartConfig);
	}
	
	/**
	 * 数据修改
	 * @param chartConfig
	 * @throws CRUDException
	 */
	public void update(ChartConfig chartConfig) throws CRUDException{
		chartConfig.preUpdate();
		chartConfigMapper.update(chartConfig);			
	}
	
	/**
	 * 数据删除
	 * @param listId
	 * @throws CRUDException
	 */
	public void delete(List<String> listId) throws CRUDException {
		chartConfigMapper.delete(listId);
	}
}
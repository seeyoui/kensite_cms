/*
 * Powered By cuichen
 * Since 2014 - 2017
 */package com.seeyoui.kensite.framework.report.chartEngine.service;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seeyoui.kensite.common.base.service.BaseService;

import java.util.*;

import com.seeyoui.kensite.common.base.domain.EasyUIDataGrid;
import com.seeyoui.kensite.common.base.service.BaseService;
import com.seeyoui.kensite.common.exception.CRUDException;
import com.seeyoui.kensite.common.util.*;
import com.seeyoui.kensite.common.constants.StringConstant;
import com.seeyoui.kensite.framework.report.chartEngine.domain.ChartEngine;
import com.seeyoui.kensite.framework.report.chartEngine.persistence.ChartEngineMapper;

/**
 * 统计图表
 * @author cuichen
 * @version 2.0
 * @since 1.0
 * @date 2017-01-20
 */

@Service
public class ChartEngineService extends BaseService {
	
	@Autowired
	private ChartEngineMapper chartEngineMapper;

	/**
	 * 根据ID查询单条数据
	 * @param id
	 * @return
	 * @throws CRUDException
	 */
	public ChartEngine findOne(String id) throws CRUDException{
		return chartEngineMapper.findOne(id);
	}
	
	/**
	 * 查询数据集合
	 * @param chartEngine
	 * @return
	 * @throws CRUDException
	 */
	public List<ChartEngine> findList(ChartEngine chartEngine) throws CRUDException {
		return chartEngineMapper.findList(chartEngine);
	}
	
	/**
	 * 查询所有数据集合
	 * @param chartEngine
	 * @return
	 * @throws CRUDException
	 */
	public List<ChartEngine> findAll(ChartEngine chartEngine) throws CRUDException {
		return chartEngineMapper.findAll(chartEngine);
	}
	
	/**
	 * 查询数据总数
	 * @param chartEngine
	 * @return
	 * @throws CRUDException
	 */
	public int findTotal(ChartEngine chartEngine) throws CRUDException {
		return chartEngineMapper.findTotal(chartEngine);
	}
	
	/**
	 * 查询数据总数排除当前数据
	 * @param chartEngine
	 * @return
	 * @throws CRUDException
	 */
	public int findExTotal(ChartEngine chartEngine) throws CRUDException {
		return chartEngineMapper.findExTotal(chartEngine);
	}
	
	/**
	 * 数据新增
	 * @param chartEngine
	 * @throws CRUDException
	 */
	public void save(ChartEngine chartEngine) throws CRUDException{
		chartEngine.preInsert();
		chartEngineMapper.save(chartEngine);
	}
	
	/**
	 * 数据修改
	 * @param chartEngine
	 * @throws CRUDException
	 */
	public void update(ChartEngine chartEngine) throws CRUDException{
		chartEngine.preUpdate();
		chartEngineMapper.update(chartEngine);			
	}
	
	/**
	 * 数据删除
	 * @param listId
	 * @throws CRUDException
	 */
	public void delete(List<String> listId) throws CRUDException {
		chartEngineMapper.delete(listId);
	}
	
	public Map<String, Object> pie(ChartEngine chartEngine) throws CRUDException, Exception {
		Map<String, Object> chart = new HashMap<String, Object>();
		Map<String, Object> legend = new HashMap<String, Object>();
		List<Map<String, Object>> series = new ArrayList<Map<String,Object>>();
		String xsource = chartEngine.getXsource();
		String xkey = chartEngine.getXkey();
		String xvalue = chartEngine.getXvalue();
		String xzkey = chartEngine.getXzkey();
		
		String ysource = chartEngine.getYsource();
		String yvalue = chartEngine.getYvalue();
		String yxkey = chartEngine.getYxkey();
		String yzkey = chartEngine.getYzkey();
		String operation = chartEngine.getOperation();
		
		String zsource = chartEngine.getZsource();
		String zkey = chartEngine.getZkey();
		String zvalue = chartEngine.getZvalue();
		
		String xsql = "select "+xkey+","+xvalue+","+xzkey+" from "+xsource+" where 1=1 ";
		if(StringUtils.isNoneBlank(chartEngine.getXwhere())) {
			xsql += chartEngine.getXwhere();
		}
		String ysql = "select "+operation+"("+yvalue+") "+yvalue+","+yxkey+","+yzkey+" from "+ysource+" where 1=1 ";
		if(StringUtils.isNoneBlank(chartEngine.getYwhere())) {
			ysql += chartEngine.getYwhere();
		}
		ysql += " group by "+yxkey+","+yzkey;
		String zsql = "select "+zkey+","+zvalue+" from "+zsource+" where 1=1 ";
		if(StringUtils.isNoneBlank(chartEngine.getZwhere())) {
			zsql += chartEngine.getZwhere();
		}
		
		List<Map<Object, Object>> xList = DBUtils.executeQuery(xsql, false);
		List<Map<Object, Object>> yList = DBUtils.executeQuery(ysql, false);
		List<Map<Object, Object>> zList = DBUtils.executeQuery(zsql, false);
		
		List<String> legendData = new ArrayList<String>();
		for(Map<Object, Object> x : xList) {
			legendData.add(String.valueOf(x.get(xvalue)));
		}
		for(Map<Object, Object> z : zList) {
			Map<String, Object> seriesMap = new HashMap<String, Object>();
			seriesMap.put("type", "pie");
			seriesMap.put("name", String.valueOf(z.get(zvalue)));
			List<Map<String, Object>> seriesData = new ArrayList<Map<String, Object>>();
			String zk = String.valueOf(z.get(zkey));
			seriesMap.put("zkey", zk);
			for(Map<Object, Object> y : yList) {
				String yzk = String.valueOf(y.get(yzkey));
				if(zk.equals(yzk)) {
					Map<String, Object> seriesDataMap = new HashMap<String, Object>();
					seriesDataMap.put("value", Long.parseLong(String.valueOf(y.get(yvalue))));
					for(Map<Object, Object> x : xList) {
						if(String.valueOf(x.get(xkey)).equals(String.valueOf(y.get(yxkey)))) {
							seriesDataMap.put("name", String.valueOf(x.get(xvalue)));
							break;
						}
					}
					seriesData.add(seriesDataMap);
				}
			}
			seriesMap.put("data", seriesData);
			series.add(seriesMap);
		}
		
		legend.put("data", legendData);
		chart.put("legend", legend);
		chart.put("series", series);
		return chart;
	}
}
/*
 * Powered By cuichen
 * Since 2014 - 2017
 */package com.seeyoui.kensite.framework.report.dataSource.service;
 
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seeyoui.kensite.common.base.service.BaseService;
import com.seeyoui.kensite.common.exception.CRUDException;
import com.seeyoui.kensite.common.util.DBUtils;
import com.seeyoui.kensite.common.util.StringUtils;
import com.seeyoui.kensite.framework.report.dataSource.domain.DataSource;
import com.seeyoui.kensite.framework.report.dataSource.persistence.DataSourceMapper;

/**
 * 数据源
 * @author cuichen
 * @version 2.0
 * @since 1.0
 * @date 2017-02-08
 */

@Service
public class DataSourceService extends BaseService {
	
	@Autowired
	private DataSourceMapper dataSourceMapper;

	/**
	 * 根据ID查询单条数据
	 * @param id
	 * @return
	 * @throws CRUDException
	 */
	public DataSource findOne(String id) throws CRUDException{
		return dataSourceMapper.findOne(id);
	}
	
	/**
	 * 查询数据集合
	 * @param dataSource
	 * @return
	 * @throws CRUDException
	 */
	public List<DataSource> findList(DataSource dataSource) throws CRUDException {
		return dataSourceMapper.findList(dataSource);
	}
	
	/**
	 * 查询所有数据集合
	 * @param dataSource
	 * @return
	 * @throws CRUDException
	 */
	public List<DataSource> findAll(DataSource dataSource) throws CRUDException {
		return dataSourceMapper.findAll(dataSource);
	}
	
	/**
	 * 查询数据总数
	 * @param dataSource
	 * @return
	 * @throws CRUDException
	 */
	public int findTotal(DataSource dataSource) throws CRUDException {
		return dataSourceMapper.findTotal(dataSource);
	}
	
	/**
	 * 查询数据总数排除当前数据
	 * @param dataSource
	 * @return
	 * @throws CRUDException
	 */
	public int findExTotal(DataSource dataSource) throws CRUDException {
		return dataSourceMapper.findExTotal(dataSource);
	}
	
	/**
	 * 数据新增
	 * @param dataSource
	 * @throws CRUDException
	 */
	public void save(DataSource dataSource) throws CRUDException{
		String codeNum = dataSource.getCodeNum();
		String type = dataSource.getType();
		String content = dataSource.getContent();
		StringBuffer viewSql = new StringBuffer();
		viewSql.append("create or replace view report_"+codeNum+" as ");
		if(StringUtils.isNoneBlank(type) && "st".equals(type)) {
			JSONObject jsonObject = JSONObject.fromObject(content);
			JSONArray jsonArray = jsonObject.getJSONArray("rows");
			for(int i=0; i<jsonArray.size(); i++) {
				JSONObject keyValue = JSONObject.fromObject(jsonArray.get(i));
				String key = keyValue.get("key").toString();
				String value = keyValue.get("value").toString();
				viewSql.append("select '"+key+"' key,'"+value+"' value from dual ");
				viewSql.append("union all ");
			}
			if(viewSql.toString().endsWith("union all ")) {
				viewSql.delete(viewSql.lastIndexOf("union all"), viewSql.length());
			}
		}
		if(StringUtils.isNoneBlank(type) && "sql".equals(type)) {
			viewSql.append(content);
		}
		dataSource.setViewSql(viewSql.toString());
		try {
			DBUtils.executeSql(viewSql.toString());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		dataSource.preInsert();
		dataSourceMapper.save(dataSource);
	}
	
	/**
	 * 数据修改
	 * @param dataSource
	 * @throws CRUDException
	 */
	public void update(DataSource dataSource) throws CRUDException{
		String codeNum = dataSource.getCodeNum();
		String type = dataSource.getType();
		String content = dataSource.getContent();
		StringBuffer viewSql = new StringBuffer();
		viewSql.append("create or replace view report_"+codeNum+" as ");
		if(StringUtils.isNoneBlank(type) && "st".equals(type)) {
			JSONObject jsonObject = JSONObject.fromObject(content);
			JSONArray jsonArray = jsonObject.getJSONArray("rows");
			for(int i=0; i<jsonArray.size(); i++) {
				JSONObject keyValue = JSONObject.fromObject(jsonArray.get(i));
				String key = keyValue.get("key").toString();
				String value = keyValue.get("value").toString();
				viewSql.append("select '"+key+"' key,'"+value+"' value from dual ");
				viewSql.append("union all ");
			}
			if(viewSql.toString().endsWith("union all ")) {
				viewSql.delete(viewSql.lastIndexOf("union all"), viewSql.length());
			}
		}
		if(StringUtils.isNoneBlank(type) && "sql".equals(type)) {
			viewSql.append(content);
		}
		dataSource.setViewSql(viewSql.toString());
		try {
			DataSource ds = dataSourceMapper.findOne(dataSource.getId());
			if(StringUtils.isNoneBlank(ds.getCodeNum()) && 
					StringUtils.isNoneBlank(dataSource.getCodeNum()) && 
					!ds.getCodeNum().equals(dataSource.getCodeNum())) {
				DBUtils.executeSql("drop view report_"+ds.getCodeNum());
			}
			DBUtils.executeSql(viewSql.toString());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		dataSource.preUpdate();
		dataSourceMapper.update(dataSource);			
	}
	
	/**
	 * 数据删除
	 * @param listId
	 * @throws CRUDException
	 */
	public void delete(List<String> listId) throws CRUDException {
		try {
			for(String id : listId) {
				DataSource ds = dataSourceMapper.findOne(id);
				if(StringUtils.isNoneBlank(ds.getCodeNum())) {
					DBUtils.executeSql("drop view report_"+ds.getCodeNum());
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		dataSourceMapper.delete(listId);
	}
}
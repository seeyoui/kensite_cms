/*
 * Powered By cuichen
 * Since 2014 - 2016
 */package com.seeyoui.kensite.bussiness.demo.service;
 
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seeyoui.kensite.bussiness.demo.domain.Demo;
import com.seeyoui.kensite.bussiness.demo.persistence.DemoMapper;
import com.seeyoui.kensite.common.base.service.BaseService;
import com.seeyoui.kensite.common.dao.Condition;
import com.seeyoui.kensite.common.dao.SqlFieldMetaData;
import com.seeyoui.kensite.common.exception.CRUDException;
import com.seeyoui.kensite.common.util.GeneratorUUID;

/**
 * 演示
 * @author cuichen
 * @version 1.0
 * @since 1.0
 * @date 2016-06-12
 */
@Service
public class DemoService extends BaseService {
	
	@Autowired
	private DemoMapper demoMapper;

	/**
	 * 根据ID查询单条数据
	 * @param id
	 * @return
	 * @throws CRUDException
	 */
	public Demo findOne(String id) throws CRUDException{
		return demoMapper.findOne(id);
	}
	
	/**
	 * 查询数据集合
	 * @param demo
	 * @return
	 * @throws CRUDException
	 */
	public List<Demo> findList(Demo demo) throws CRUDException {
		return demoMapper.findList(demo);
	}
	
	/**
	 * 查询所有数据集合
	 * @param demo
	 * @return
	 * @throws CRUDException
	 */
	public List<Demo> findAll(Demo demo) throws CRUDException {
		return demoMapper.findAll(demo);
	}
	
	/**
	 * 查询数据总数
	 * @param demo
	 * @return
	 * @throws CRUDException
	 */
	public int findTotal(Demo demo) throws CRUDException {
		return demoMapper.findTotal(demo);
	}
	
	/**
	 * 查询数据总数排除当前数据
	 * @param demo
	 * @return
	 * @throws CRUDException
	 */
	public int findExTotal(Demo demo) throws CRUDException {
		return demoMapper.findExTotal(demo);
	}
	
	/**
	 * 数据新增
	 * @param demo
	 * @throws CRUDException
	 */
	public void save(Demo demo) throws CRUDException{
		demo.preInsert();
		demoMapper.save(demo);
	}
	
	/**
	 * 数据修改
	 * @param demo
	 * @throws CRUDException
	 */
	public void update(Demo demo) throws CRUDException{
		demo.preUpdate();
		demoMapper.update(demo);			
	}
	
	/**
	 * 数据删除
	 * @param listId
	 * @throws CRUDException
	 */
	public void delete(List<String> listId) throws CRUDException {
		demoMapper.delete(listId);
	}
	
	public List<SqlFieldMetaData> test() {
		System.out.println(dao);
		String sql = "select * from sys_user";
		long count = 0;
		try {
			count = dao.sqlCount(sql);
			sql = "select * from sys_user";
			Condition condition = new Condition();
			condition.like("user_name", "sys");
			List<Map<String, Object>>  list = dao.sqlQuery(sql, condition);
			for(Map<String, Object> map : list) {
				System.out.println(map.get("user_name").toString());
			}
			sql = "insert into bo_demo(id) values(:id)";
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("id", GeneratorUUID.getId());
			dao.sqlExecute(sql, params);
	
			sql = "select * from bo_demo";
			List<SqlFieldMetaData> metaList = dao.sqlQueryMetaData(sql, condition);
			return metaList;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
}
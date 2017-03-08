/*
 * Powered By cuichen
 * Since 2014 - 2017
 */
package com.seeyoui.kensite.framework.eshop.category.persistence;

import com.seeyoui.kensite.common.base.domain.EasyUIDataGrid;
import com.seeyoui.kensite.framework.eshop.category.domain.Cate;

import java.util.*;

import com.seeyoui.kensite.common.exception.CRUDException;

/**
 * 分类
 * @author cuichen
 * @version 2.0
 * @since 1.0
 * @date 2017-03-07
 */

public interface CateMapper {

	/**
	 * 根据ID查询单条数据
	 * @param id
	 * @return
	 */
	public Cate findOne(String id);
	
	/**
	 * 查询数据集合
	 * @param category
	 * @return
	 */
	public List<Cate> findList(Cate category);
	
	/**
	 * 查询所有数据集合
	 * @param category
	 * @return
	 */
	public List<Cate> findAll(Cate category);
	
	/**
	 * 查询数据总数
	 * @param category
	 * @return
	 */
	public int findTotal(Cate category);

	/**
	 * 查询数据总数排除当前数据
	 * @param category
	 * @return
	 */
	public int findExTotal(Cate category);
	
	/**
	 * 数据新增
	 * @param category
	 */
	public void save(Cate category);
	
	/**
	 * 数据修改
	 * @param category
	 */
	public void update(Cate category);
	
	/**
	 * 数据删除
	 * @param listId
	 */
	public void delete(List<String> listId);
	
	/**
	 * 数据假删除
	 * @param category
	 */
	public void remove(Cate category);
	
}
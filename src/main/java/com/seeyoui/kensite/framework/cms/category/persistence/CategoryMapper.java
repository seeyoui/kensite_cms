/*
 * Powered By cuichen
 * Since 2014 - 2016
 */
package com.seeyoui.kensite.framework.cms.category.persistence;

import com.seeyoui.kensite.common.base.domain.EasyUIDataGrid;
import com.seeyoui.kensite.framework.cms.category.domain.Category;

import java.util.*;

import com.seeyoui.kensite.common.exception.CRUDException;

/**
 * 内容发布栏目
 * @author cuichen
 * @version 2.0
 * @since 1.0
 * @date 2016-07-15
 */

public interface CategoryMapper {

	/**
	 * 根据ID查询单条数据
	 * @param id
	 * @return
	 */
	public Category findOne(String id);
	
	/**
	 * 查询数据集合
	 * @param category
	 * @return
	 */
	public List<Category> findList(Category category);
	
	/**
	 * 查询所有数据集合
	 * @param category
	 * @return
	 */
	public List<Category> findAll(Category category);
	
	/**
	 * 查询数据总数
	 * @param category
	 * @return
	 */
	public int findTotal(Category category);

	/**
	 * 查询数据总数排除当前数据
	 * @param category
	 * @return
	 */
	public int findExTotal(Category category);
	
	/**
	 * 数据新增
	 * @param category
	 */
	public void save(Category category);
	
	/**
	 * 数据修改
	 * @param category
	 */
	public void update(Category category);
	
	/**
	 * 数据删除
	 * @param listId
	 */
	public void delete(List<String> listId);
}
/*
 * Powered By cuichen
 * Since 2014 - 2017
 */
package com.seeyoui.kensite.framework.eshop.attrSet.persistence;

import com.seeyoui.kensite.common.base.domain.EasyUIDataGrid;
import com.seeyoui.kensite.framework.eshop.attrSet.domain.AttrSet;

import java.util.*;

import com.seeyoui.kensite.common.exception.CRUDException;

/**
 * 属性设置
 * @author cuichen
 * @version 2.0
 * @since 1.0
 * @date 2017-03-08
 */

public interface AttrSetMapper {

	/**
	 * 根据ID查询单条数据
	 * @param id
	 * @return
	 */
	public AttrSet findOne(String id);
	
	/**
	 * 查询数据集合
	 * @param attrSet
	 * @return
	 */
	public List<AttrSet> findList(AttrSet attrSet);
	
	/**
	 * 查询所有数据集合
	 * @param attrSet
	 * @return
	 */
	public List<AttrSet> findAll(AttrSet attrSet);
	
	/**
	 * 查询数据总数
	 * @param attrSet
	 * @return
	 */
	public int findTotal(AttrSet attrSet);

	/**
	 * 查询数据总数排除当前数据
	 * @param attrSet
	 * @return
	 */
	public int findExTotal(AttrSet attrSet);
	
	/**
	 * 数据新增
	 * @param attrSet
	 */
	public void save(AttrSet attrSet);
	
	/**
	 * 数据修改
	 * @param attrSet
	 */
	public void update(AttrSet attrSet);
	
	/**
	 * 数据删除
	 * @param listId
	 */
	public void delete(List<String> listId);
	
	/**
	 * 数据假删除
	 * @param attrSet
	 */
	public void remove(AttrSet attrSet);
	
}
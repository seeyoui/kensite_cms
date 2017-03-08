/*
 * Powered By cuichen
 * Since 2014 - 2017
 */
package com.seeyoui.kensite.framework.eshop.specSet.persistence;

import com.seeyoui.kensite.common.base.domain.EasyUIDataGrid;
import com.seeyoui.kensite.framework.eshop.specSet.domain.SpecSet;

import java.util.*;

import com.seeyoui.kensite.common.exception.CRUDException;

/**
 * 规格设置
 * @author cuichen
 * @version 2.0
 * @since 1.0
 * @date 2017-03-08
 */

public interface SpecSetMapper {

	/**
	 * 根据ID查询单条数据
	 * @param id
	 * @return
	 */
	public SpecSet findOne(String id);
	
	/**
	 * 查询数据集合
	 * @param specSet
	 * @return
	 */
	public List<SpecSet> findList(SpecSet specSet);
	
	/**
	 * 查询所有数据集合
	 * @param specSet
	 * @return
	 */
	public List<SpecSet> findAll(SpecSet specSet);
	
	/**
	 * 查询数据总数
	 * @param specSet
	 * @return
	 */
	public int findTotal(SpecSet specSet);

	/**
	 * 查询数据总数排除当前数据
	 * @param specSet
	 * @return
	 */
	public int findExTotal(SpecSet specSet);
	
	/**
	 * 数据新增
	 * @param specSet
	 */
	public void save(SpecSet specSet);
	
	/**
	 * 数据修改
	 * @param specSet
	 */
	public void update(SpecSet specSet);
	
	/**
	 * 数据删除
	 * @param listId
	 */
	public void delete(List<String> listId);
	
	/**
	 * 数据假删除
	 * @param specSet
	 */
	public void remove(SpecSet specSet);
	
}
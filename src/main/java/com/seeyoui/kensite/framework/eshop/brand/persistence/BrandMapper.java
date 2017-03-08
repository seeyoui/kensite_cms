/*
 * Powered By cuichen
 * Since 2014 - 2017
 */
package com.seeyoui.kensite.framework.eshop.brand.persistence;

import com.seeyoui.kensite.common.base.domain.EasyUIDataGrid;
import com.seeyoui.kensite.framework.eshop.brand.domain.Brand;

import java.util.*;

import com.seeyoui.kensite.common.exception.CRUDException;

/**
 * 品牌
 * @author cuichen
 * @version 2.0
 * @since 1.0
 * @date 2017-03-07
 */

public interface BrandMapper {

	/**
	 * 根据ID查询单条数据
	 * @param id
	 * @return
	 */
	public Brand findOne(String id);
	
	/**
	 * 查询数据集合
	 * @param brand
	 * @return
	 */
	public List<Brand> findList(Brand brand);
	
	/**
	 * 查询所有数据集合
	 * @param brand
	 * @return
	 */
	public List<Brand> findAll(Brand brand);
	
	/**
	 * 查询数据总数
	 * @param brand
	 * @return
	 */
	public int findTotal(Brand brand);

	/**
	 * 查询数据总数排除当前数据
	 * @param brand
	 * @return
	 */
	public int findExTotal(Brand brand);
	
	/**
	 * 数据新增
	 * @param brand
	 */
	public void save(Brand brand);
	
	/**
	 * 数据修改
	 * @param brand
	 */
	public void update(Brand brand);
	
	/**
	 * 数据删除
	 * @param listId
	 */
	public void delete(List<String> listId);
	
	/**
	 * 数据假删除
	 * @param brand
	 */
	public void remove(Brand brand);
	
}
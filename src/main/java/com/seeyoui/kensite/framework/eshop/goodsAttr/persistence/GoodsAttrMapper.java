/*
 * Powered By cuichen
 * Since 2014 - 2017
 */
package com.seeyoui.kensite.framework.eshop.goodsAttr.persistence;

import com.seeyoui.kensite.common.base.domain.EasyUIDataGrid;
import com.seeyoui.kensite.framework.eshop.goodsAttr.domain.GoodsAttr;

import java.util.*;

import com.seeyoui.kensite.common.exception.CRUDException;

/**
 * 商品属性
 * @author cuichen
 * @version 2.0
 * @since 1.0
 * @date 2017-03-09
 */

public interface GoodsAttrMapper {

	/**
	 * 根据ID查询单条数据
	 * @param id
	 * @return
	 */
	public GoodsAttr findOne(String id);
	
	/**
	 * 查询数据集合
	 * @param goodsAttr
	 * @return
	 */
	public List<GoodsAttr> findList(GoodsAttr goodsAttr);
	
	/**
	 * 查询所有数据集合
	 * @param goodsAttr
	 * @return
	 */
	public List<GoodsAttr> findAll(GoodsAttr goodsAttr);
	
	/**
	 * 查询数据总数
	 * @param goodsAttr
	 * @return
	 */
	public int findTotal(GoodsAttr goodsAttr);

	/**
	 * 查询数据总数排除当前数据
	 * @param goodsAttr
	 * @return
	 */
	public int findExTotal(GoodsAttr goodsAttr);
	
	/**
	 * 数据新增
	 * @param goodsAttr
	 */
	public void save(GoodsAttr goodsAttr);
	
	/**
	 * 数据修改
	 * @param goodsAttr
	 */
	public void update(GoodsAttr goodsAttr);
	
	/**
	 * 数据删除
	 * @param listId
	 */
	public void delete(List<String> listId);
	
	/**
	 * 数据假删除
	 * @param goodsAttr
	 */
	public void remove(GoodsAttr goodsAttr);
	
}
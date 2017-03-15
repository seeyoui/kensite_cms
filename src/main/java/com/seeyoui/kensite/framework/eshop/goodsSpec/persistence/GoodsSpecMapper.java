/*
 * Powered By cuichen
 * Since 2014 - 2017
 */
package com.seeyoui.kensite.framework.eshop.goodsSpec.persistence;

import com.seeyoui.kensite.common.base.domain.EasyUIDataGrid;
import com.seeyoui.kensite.framework.eshop.goodsSpec.domain.GoodsSpec;

import java.util.*;

import com.seeyoui.kensite.common.exception.CRUDException;

/**
 * 商品规格
 * @author cuichen
 * @version 2.0
 * @since 1.0
 * @date 2017-03-09
 */

public interface GoodsSpecMapper {

	/**
	 * 根据ID查询单条数据
	 * @param id
	 * @return
	 */
	public GoodsSpec findOne(String id);
	
	/**
	 * 查询数据集合
	 * @param goodsSpec
	 * @return
	 */
	public List<GoodsSpec> findList(GoodsSpec goodsSpec);
	
	/**
	 * 查询所有数据集合
	 * @param goodsSpec
	 * @return
	 */
	public List<GoodsSpec> findAll(GoodsSpec goodsSpec);
	
	/**
	 * 查询数据总数
	 * @param goodsSpec
	 * @return
	 */
	public int findTotal(GoodsSpec goodsSpec);

	/**
	 * 查询数据总数排除当前数据
	 * @param goodsSpec
	 * @return
	 */
	public int findExTotal(GoodsSpec goodsSpec);
	
	/**
	 * 数据新增
	 * @param goodsSpec
	 */
	public void save(GoodsSpec goodsSpec);
	
	/**
	 * 数据修改
	 * @param goodsSpec
	 */
	public void update(GoodsSpec goodsSpec);
	
	/**
	 * 数据删除
	 * @param listId
	 */
	public void delete(List<String> listId);
	
	/**
	 * 数据假删除
	 * @param goodsSpec
	 */
	public void remove(GoodsSpec goodsSpec);
	
}
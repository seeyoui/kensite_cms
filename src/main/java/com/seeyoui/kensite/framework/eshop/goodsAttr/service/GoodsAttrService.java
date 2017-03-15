/*
 * Powered By cuichen
 * Since 2014 - 2017
 */package com.seeyoui.kensite.framework.eshop.goodsAttr.service;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seeyoui.kensite.common.base.service.BaseService;

import java.util.*;

import com.seeyoui.kensite.common.base.domain.EasyUIDataGrid;
import com.seeyoui.kensite.common.base.service.BaseService;
import com.seeyoui.kensite.common.exception.CRUDException;
import com.seeyoui.kensite.common.util.*;
import com.seeyoui.kensite.common.constants.StringConstant;
import com.seeyoui.kensite.framework.eshop.goodsAttr.domain.GoodsAttr;
import com.seeyoui.kensite.framework.eshop.goodsAttr.persistence.GoodsAttrMapper;

/**
 * 商品属性
 * @author cuichen
 * @version 2.0
 * @since 1.0
 * @date 2017-03-09
 */

@Service
public class GoodsAttrService extends BaseService {
	
	@Autowired
	private GoodsAttrMapper goodsAttrMapper;

	/**
	 * 根据ID查询单条数据
	 * @param id
	 * @return
	 * @throws CRUDException
	 */
	public GoodsAttr findOne(String id) throws CRUDException{
		return goodsAttrMapper.findOne(id);
	}
	
	/**
	 * 查询数据集合
	 * @param goodsAttr
	 * @return
	 * @throws CRUDException
	 */
	public List<GoodsAttr> findList(GoodsAttr goodsAttr) throws CRUDException {
		return goodsAttrMapper.findList(goodsAttr);
	}
	
	/**
	 * 查询所有数据集合
	 * @param goodsAttr
	 * @return
	 * @throws CRUDException
	 */
	public List<GoodsAttr> findAll(GoodsAttr goodsAttr) throws CRUDException {
		return goodsAttrMapper.findAll(goodsAttr);
	}
	
	/**
	 * 查询数据总数
	 * @param goodsAttr
	 * @return
	 * @throws CRUDException
	 */
	public int findTotal(GoodsAttr goodsAttr) throws CRUDException {
		return goodsAttrMapper.findTotal(goodsAttr);
	}
	
	/**
	 * 查询数据总数排除当前数据
	 * @param goodsAttr
	 * @return
	 * @throws CRUDException
	 */
	public int findExTotal(GoodsAttr goodsAttr) throws CRUDException {
		return goodsAttrMapper.findExTotal(goodsAttr);
	}
	
	/**
	 * 数据新增
	 * @param goodsAttr
	 * @throws CRUDException
	 */
	public void save(GoodsAttr goodsAttr) throws CRUDException{
		goodsAttr.preInsert();
		goodsAttrMapper.save(goodsAttr);
	}
	
	/**
	 * 数据修改
	 * @param goodsAttr
	 * @throws CRUDException
	 */
	public void update(GoodsAttr goodsAttr) throws CRUDException{
		goodsAttr.preUpdate();
		goodsAttrMapper.update(goodsAttr);			
	}
	
	/**
	 * 数据删除
	 * @param listId
	 * @throws CRUDException
	 */
	public void delete(List<String> listId) throws CRUDException {
		goodsAttrMapper.delete(listId);
	}
	
	/**
	 * 数据假删除
	 * @param goodsAttr
	 * @throws CRUDException
	 */
	public void remove(GoodsAttr goodsAttr) throws CRUDException{
		goodsAttr.preUpdate();
		goodsAttrMapper.remove(goodsAttr);			
	}
	
}
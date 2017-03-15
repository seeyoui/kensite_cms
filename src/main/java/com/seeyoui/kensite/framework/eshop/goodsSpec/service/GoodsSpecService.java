/*
 * Powered By cuichen
 * Since 2014 - 2017
 */package com.seeyoui.kensite.framework.eshop.goodsSpec.service;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seeyoui.kensite.common.base.service.BaseService;

import java.util.*;

import com.seeyoui.kensite.common.base.domain.EasyUIDataGrid;
import com.seeyoui.kensite.common.base.service.BaseService;
import com.seeyoui.kensite.common.exception.CRUDException;
import com.seeyoui.kensite.common.util.*;
import com.seeyoui.kensite.common.constants.StringConstant;
import com.seeyoui.kensite.framework.eshop.goodsSpec.domain.GoodsSpec;
import com.seeyoui.kensite.framework.eshop.goodsSpec.persistence.GoodsSpecMapper;

/**
 * 商品规格
 * @author cuichen
 * @version 2.0
 * @since 1.0
 * @date 2017-03-09
 */

@Service
public class GoodsSpecService extends BaseService {
	
	@Autowired
	private GoodsSpecMapper goodsSpecMapper;

	/**
	 * 根据ID查询单条数据
	 * @param id
	 * @return
	 * @throws CRUDException
	 */
	public GoodsSpec findOne(String id) throws CRUDException{
		return goodsSpecMapper.findOne(id);
	}
	
	/**
	 * 查询数据集合
	 * @param goodsSpec
	 * @return
	 * @throws CRUDException
	 */
	public List<GoodsSpec> findList(GoodsSpec goodsSpec) throws CRUDException {
		return goodsSpecMapper.findList(goodsSpec);
	}
	
	/**
	 * 查询所有数据集合
	 * @param goodsSpec
	 * @return
	 * @throws CRUDException
	 */
	public List<GoodsSpec> findAll(GoodsSpec goodsSpec) throws CRUDException {
		return goodsSpecMapper.findAll(goodsSpec);
	}
	
	/**
	 * 查询数据总数
	 * @param goodsSpec
	 * @return
	 * @throws CRUDException
	 */
	public int findTotal(GoodsSpec goodsSpec) throws CRUDException {
		return goodsSpecMapper.findTotal(goodsSpec);
	}
	
	/**
	 * 查询数据总数排除当前数据
	 * @param goodsSpec
	 * @return
	 * @throws CRUDException
	 */
	public int findExTotal(GoodsSpec goodsSpec) throws CRUDException {
		return goodsSpecMapper.findExTotal(goodsSpec);
	}
	
	/**
	 * 数据新增
	 * @param goodsSpec
	 * @throws CRUDException
	 */
	public void save(GoodsSpec goodsSpec) throws CRUDException{
		goodsSpec.preInsert();
		goodsSpecMapper.save(goodsSpec);
	}
	
	/**
	 * 数据修改
	 * @param goodsSpec
	 * @throws CRUDException
	 */
	public void update(GoodsSpec goodsSpec) throws CRUDException{
		goodsSpec.preUpdate();
		goodsSpecMapper.update(goodsSpec);			
	}
	
	/**
	 * 数据删除
	 * @param listId
	 * @throws CRUDException
	 */
	public void delete(List<String> listId) throws CRUDException {
		goodsSpecMapper.delete(listId);
	}
	
	/**
	 * 数据假删除
	 * @param goodsSpec
	 * @throws CRUDException
	 */
	public void remove(GoodsSpec goodsSpec) throws CRUDException{
		goodsSpec.preUpdate();
		goodsSpecMapper.remove(goodsSpec);			
	}
	
}
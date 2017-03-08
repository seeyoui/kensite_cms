/*
 * Powered By cuichen
 * Since 2014 - 2017
 */package com.seeyoui.kensite.framework.eshop.brand.service;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seeyoui.kensite.common.base.service.BaseService;

import java.util.*;

import com.seeyoui.kensite.common.base.domain.EasyUIDataGrid;
import com.seeyoui.kensite.common.base.service.BaseService;
import com.seeyoui.kensite.common.exception.CRUDException;
import com.seeyoui.kensite.common.util.*;
import com.seeyoui.kensite.common.constants.StringConstant;
import com.seeyoui.kensite.framework.eshop.brand.domain.Brand;
import com.seeyoui.kensite.framework.eshop.brand.persistence.BrandMapper;

/**
 * 品牌
 * @author cuichen
 * @version 2.0
 * @since 1.0
 * @date 2017-03-07
 */

@Service
public class BrandService extends BaseService {
	
	@Autowired
	private BrandMapper brandMapper;

	/**
	 * 根据ID查询单条数据
	 * @param id
	 * @return
	 * @throws CRUDException
	 */
	public Brand findOne(String id) throws CRUDException{
		return brandMapper.findOne(id);
	}
	
	/**
	 * 查询数据集合
	 * @param brand
	 * @return
	 * @throws CRUDException
	 */
	public List<Brand> findList(Brand brand) throws CRUDException {
		return brandMapper.findList(brand);
	}
	
	/**
	 * 查询所有数据集合
	 * @param brand
	 * @return
	 * @throws CRUDException
	 */
	public List<Brand> findAll(Brand brand) throws CRUDException {
		return brandMapper.findAll(brand);
	}
	
	/**
	 * 查询数据总数
	 * @param brand
	 * @return
	 * @throws CRUDException
	 */
	public int findTotal(Brand brand) throws CRUDException {
		return brandMapper.findTotal(brand);
	}
	
	/**
	 * 查询数据总数排除当前数据
	 * @param brand
	 * @return
	 * @throws CRUDException
	 */
	public int findExTotal(Brand brand) throws CRUDException {
		return brandMapper.findExTotal(brand);
	}
	
	/**
	 * 数据新增
	 * @param brand
	 * @throws CRUDException
	 */
	public void save(Brand brand) throws CRUDException{
		brand.preInsert();
		brandMapper.save(brand);
	}
	
	/**
	 * 数据修改
	 * @param brand
	 * @throws CRUDException
	 */
	public void update(Brand brand) throws CRUDException{
		brand.preUpdate();
		brandMapper.update(brand);			
	}
	
	/**
	 * 数据删除
	 * @param listId
	 * @throws CRUDException
	 */
	public void delete(List<String> listId) throws CRUDException {
		brandMapper.delete(listId);
	}
	
	/**
	 * 数据假删除
	 * @param brand
	 * @throws CRUDException
	 */
	public void remove(Brand brand) throws CRUDException{
		brand.preUpdate();
		brandMapper.remove(brand);			
	}
	
}
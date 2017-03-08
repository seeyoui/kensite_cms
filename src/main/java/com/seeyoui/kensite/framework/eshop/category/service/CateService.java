/*
 * Powered By cuichen
 * Since 2014 - 2017
 */package com.seeyoui.kensite.framework.eshop.category.service;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seeyoui.kensite.common.base.service.BaseService;

import java.util.*;

import com.seeyoui.kensite.common.base.domain.EasyUIDataGrid;
import com.seeyoui.kensite.common.base.service.BaseService;
import com.seeyoui.kensite.common.exception.CRUDException;
import com.seeyoui.kensite.common.util.*;
import com.seeyoui.kensite.common.constants.StringConstant;
import com.seeyoui.kensite.framework.eshop.category.domain.Cate;
import com.seeyoui.kensite.framework.eshop.category.persistence.CateMapper;

/**
 * 分类
 * @author cuichen
 * @version 2.0
 * @since 1.0
 * @date 2017-03-07
 */

@Service
public class CateService extends BaseService {
	
	@Autowired
	private CateMapper categoryMapper;

	/**
	 * 根据ID查询单条数据
	 * @param id
	 * @return
	 * @throws CRUDException
	 */
	public Cate findOne(String id) throws CRUDException{
		return categoryMapper.findOne(id);
	}
	
	/**
	 * 查询数据集合
	 * @param category
	 * @return
	 * @throws CRUDException
	 */
	public List<Cate> findList(Cate category) throws CRUDException {
		return categoryMapper.findList(category);
	}
	
	/**
	 * 查询所有数据集合
	 * @param category
	 * @return
	 * @throws CRUDException
	 */
	public List<Cate> findAll(Cate category) throws CRUDException {
		return categoryMapper.findAll(category);
	}
	
	/**
	 * 查询数据总数
	 * @param category
	 * @return
	 * @throws CRUDException
	 */
	public int findTotal(Cate category) throws CRUDException {
		return categoryMapper.findTotal(category);
	}
	
	/**
	 * 查询数据总数排除当前数据
	 * @param category
	 * @return
	 * @throws CRUDException
	 */
	public int findExTotal(Cate category) throws CRUDException {
		return categoryMapper.findExTotal(category);
	}
	
	/**
	 * 数据新增
	 * @param category
	 * @throws CRUDException
	 */
	public void save(Cate category) throws CRUDException{
		category.preInsert();
		categoryMapper.save(category);
	}
	
	/**
	 * 数据修改
	 * @param category
	 * @throws CRUDException
	 */
	public void update(Cate category) throws CRUDException{
		category.preUpdate();
		categoryMapper.update(category);			
	}
	
	/**
	 * 数据删除
	 * @param listId
	 * @throws CRUDException
	 */
	public void delete(List<String> listId) throws CRUDException {
		categoryMapper.delete(listId);
	}
	
	/**
	 * 数据假删除
	 * @param category
	 * @throws CRUDException
	 */
	public void remove(Cate category) throws CRUDException{
		category.preUpdate();
		categoryMapper.remove(category);			
	}
	
}
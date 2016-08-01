/*
 * Powered By cuichen
 * Since 2014 - 2016
 */package com.seeyoui.kensite.framework.cms.category.service;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seeyoui.kensite.common.base.service.BaseService;

import java.util.*;

import com.seeyoui.kensite.common.base.domain.EasyUIDataGrid;
import com.seeyoui.kensite.common.base.service.BaseService;
import com.seeyoui.kensite.common.exception.CRUDException;
import com.seeyoui.kensite.common.util.*;
import com.seeyoui.kensite.common.constants.StringConstant;
import com.seeyoui.kensite.framework.cms.category.domain.Category;
import com.seeyoui.kensite.framework.cms.category.persistence.CategoryMapper;

/**
 * 内容发布栏目
 * @author cuichen
 * @version 2.0
 * @since 1.0
 * @date 2016-07-15
 */

@Service
public class CategoryService extends BaseService {
	
	@Autowired
	private CategoryMapper categoryMapper;

	/**
	 * 根据ID查询单条数据
	 * @param id
	 * @return
	 * @throws CRUDException
	 */
	public Category findOne(String id) throws CRUDException{
		return categoryMapper.findOne(id);
	}
	
	/**
	 * 查询数据集合
	 * @param category
	 * @return
	 * @throws CRUDException
	 */
	public List<Category> findList(Category category) throws CRUDException {
		return categoryMapper.findList(category);
	}
	
	/**
	 * 查询所有数据集合
	 * @param category
	 * @return
	 * @throws CRUDException
	 */
	public List<Category> findAll(Category category) throws CRUDException {
		return categoryMapper.findAll(category);
	}
	
	/**
	 * 查询数据总数
	 * @param category
	 * @return
	 * @throws CRUDException
	 */
	public int findTotal(Category category) throws CRUDException {
		return categoryMapper.findTotal(category);
	}
	
	/**
	 * 查询数据总数排除当前数据
	 * @param category
	 * @return
	 * @throws CRUDException
	 */
	public int findExTotal(Category category) throws CRUDException {
		return categoryMapper.findExTotal(category);
	}
	
	/**
	 * 数据新增
	 * @param category
	 * @throws CRUDException
	 */
	public void save(Category category) throws CRUDException{
		category.preInsert();
		categoryMapper.save(category);
	}
	
	/**
	 * 数据修改
	 * @param category
	 * @throws CRUDException
	 */
	public void update(Category category) throws CRUDException{
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
}
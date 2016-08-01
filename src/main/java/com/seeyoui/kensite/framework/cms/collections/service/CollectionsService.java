/*
 * Powered By cuichen
 * Since 2014 - 2016
 */package com.seeyoui.kensite.framework.cms.collections.service;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seeyoui.kensite.common.base.service.BaseService;

import java.util.*;

import com.seeyoui.kensite.common.base.domain.EasyUIDataGrid;
import com.seeyoui.kensite.common.base.service.BaseService;
import com.seeyoui.kensite.common.exception.CRUDException;
import com.seeyoui.kensite.common.util.*;
import com.seeyoui.kensite.common.constants.StringConstant;
import com.seeyoui.kensite.framework.cms.collections.domain.Collections;
import com.seeyoui.kensite.framework.cms.collections.persistence.CollectionsMapper;

/**
 * 内容发布收藏
 * @author cuichen
 * @version 2.0
 * @since 1.0
 * @date 2016-07-15
 */

@Service
public class CollectionsService extends BaseService {
	
	@Autowired
	private CollectionsMapper collectionsMapper;

	/**
	 * 根据ID查询单条数据
	 * @param id
	 * @return
	 * @throws CRUDException
	 */
	public Collections findOne(String id) throws CRUDException{
		return collectionsMapper.findOne(id);
	}
	
	/**
	 * 查询数据集合
	 * @param collections
	 * @return
	 * @throws CRUDException
	 */
	public List<Collections> findList(Collections collections) throws CRUDException {
		return collectionsMapper.findList(collections);
	}
	
	/**
	 * 查询所有数据集合
	 * @param collections
	 * @return
	 * @throws CRUDException
	 */
	public List<Collections> findAll(Collections collections) throws CRUDException {
		return collectionsMapper.findAll(collections);
	}
	
	/**
	 * 查询数据总数
	 * @param collections
	 * @return
	 * @throws CRUDException
	 */
	public int findTotal(Collections collections) throws CRUDException {
		return collectionsMapper.findTotal(collections);
	}
	
	/**
	 * 查询数据总数排除当前数据
	 * @param collections
	 * @return
	 * @throws CRUDException
	 */
	public int findExTotal(Collections collections) throws CRUDException {
		return collectionsMapper.findExTotal(collections);
	}
	
	/**
	 * 数据新增
	 * @param collections
	 * @throws CRUDException
	 */
	public void save(Collections collections) throws CRUDException{
		collections.preInsert();
		collectionsMapper.save(collections);
	}
	
	/**
	 * 数据修改
	 * @param collections
	 * @throws CRUDException
	 */
	public void update(Collections collections) throws CRUDException{
		collections.preUpdate();
		collectionsMapper.update(collections);			
	}
	
	/**
	 * 数据删除
	 * @param listId
	 * @throws CRUDException
	 */
	public void delete(List<String> listId) throws CRUDException {
		collectionsMapper.delete(listId);
	}
}
/*
 * Powered By cuichen
 * Since 2014 - 2016
 */
package com.seeyoui.kensite.framework.cms.collections.persistence;

import com.seeyoui.kensite.common.base.domain.EasyUIDataGrid;
import com.seeyoui.kensite.framework.cms.collections.domain.Collections;

import java.util.*;

import com.seeyoui.kensite.common.exception.CRUDException;

/**
 * 内容发布收藏
 * @author cuichen
 * @version 2.0
 * @since 1.0
 * @date 2016-07-15
 */

public interface CollectionsMapper {

	/**
	 * 根据ID查询单条数据
	 * @param id
	 * @return
	 */
	public Collections findOne(String id);
	
	/**
	 * 查询数据集合
	 * @param collections
	 * @return
	 */
	public List<Collections> findList(Collections collections);
	
	/**
	 * 查询所有数据集合
	 * @param collections
	 * @return
	 */
	public List<Collections> findAll(Collections collections);
	
	/**
	 * 查询数据总数
	 * @param collections
	 * @return
	 */
	public int findTotal(Collections collections);

	/**
	 * 查询数据总数排除当前数据
	 * @param collections
	 * @return
	 */
	public int findExTotal(Collections collections);
	
	/**
	 * 数据新增
	 * @param collections
	 */
	public void save(Collections collections);
	
	/**
	 * 数据修改
	 * @param collections
	 */
	public void update(Collections collections);
	
	/**
	 * 数据删除
	 * @param listId
	 */
	public void delete(List<String> listId);
}
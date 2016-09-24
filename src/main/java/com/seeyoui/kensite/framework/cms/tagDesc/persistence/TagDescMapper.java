/*
 * Powered By cuichen
 * Since 2014 - 2016
 */
package com.seeyoui.kensite.framework.cms.tagDesc.persistence;

import com.seeyoui.kensite.common.base.domain.EasyUIDataGrid;
import com.seeyoui.kensite.framework.cms.tagDesc.domain.TagDesc;

import java.util.*;

import com.seeyoui.kensite.common.exception.CRUDException;

/**
 * 标签描述
 * @author cuichen
 * @version 2.0
 * @since 1.0
 * @date 2016-09-24
 */

public interface TagDescMapper {

	/**
	 * 根据ID查询单条数据
	 * @param id
	 * @return
	 */
	public TagDesc findOne(String id);
	
	/**
	 * 查询数据集合
	 * @param tagDesc
	 * @return
	 */
	public List<TagDesc> findList(TagDesc tagDesc);
	
	/**
	 * 查询所有数据集合
	 * @param tagDesc
	 * @return
	 */
	public List<TagDesc> findAll(TagDesc tagDesc);
	
	/**
	 * 查询数据总数
	 * @param tagDesc
	 * @return
	 */
	public int findTotal(TagDesc tagDesc);

	/**
	 * 查询数据总数排除当前数据
	 * @param tagDesc
	 * @return
	 */
	public int findExTotal(TagDesc tagDesc);
	
	/**
	 * 数据新增
	 * @param tagDesc
	 */
	public void save(TagDesc tagDesc);
	
	/**
	 * 数据修改
	 * @param tagDesc
	 */
	public void update(TagDesc tagDesc);
	
	/**
	 * 数据删除
	 * @param listId
	 */
	public void delete(List<String> listId);
}
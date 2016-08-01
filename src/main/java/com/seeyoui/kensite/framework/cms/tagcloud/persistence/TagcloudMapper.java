/*
 * Powered By cuichen
 * Since 2014 - 2016
 */
package com.seeyoui.kensite.framework.cms.tagcloud.persistence;

import com.seeyoui.kensite.common.base.domain.EasyUIDataGrid;
import com.seeyoui.kensite.framework.cms.tagcloud.domain.Tagcloud;

import java.util.*;

import com.seeyoui.kensite.common.exception.CRUDException;

/**
 * 标签云
 * @author cuichen
 * @version 2.0
 * @since 1.0
 * @date 2016-07-27
 */

public interface TagcloudMapper {

	/**
	 * 根据ID查询单条数据
	 * @param id
	 * @return
	 */
	public Tagcloud findOne(String id);
	
	/**
	 * 查询数据集合
	 * @param tagcloud
	 * @return
	 */
	public List<Tagcloud> findList(Tagcloud tagcloud);
	
	/**
	 * 查询所有数据集合
	 * @param tagcloud
	 * @return
	 */
	public List<Tagcloud> findAll(Tagcloud tagcloud);
	
	/**
	 * 查询数据总数
	 * @param tagcloud
	 * @return
	 */
	public int findTotal(Tagcloud tagcloud);

	/**
	 * 查询数据总数排除当前数据
	 * @param tagcloud
	 * @return
	 */
	public int findExTotal(Tagcloud tagcloud);
	
	/**
	 * 数据新增
	 * @param tagcloud
	 */
	public void save(Tagcloud tagcloud);
	
	/**
	 * 数据修改
	 * @param tagcloud
	 */
	public void update(Tagcloud tagcloud);
	
	/**
	 * 数据删除
	 * @param listId
	 */
	public void delete(List<String> listId);
}
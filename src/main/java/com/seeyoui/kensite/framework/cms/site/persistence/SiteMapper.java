/*
 * Powered By cuichen
 * Since 2014 - 2016
 */
package com.seeyoui.kensite.framework.cms.site.persistence;

import com.seeyoui.kensite.common.base.domain.EasyUIDataGrid;
import com.seeyoui.kensite.framework.cms.site.domain.Site;

import java.util.*;

import com.seeyoui.kensite.common.exception.CRUDException;

/**
 * 内容发布站点
 * @author cuichen
 * @version 2.0
 * @since 1.0
 * @date 2016-07-15
 */

public interface SiteMapper {

	/**
	 * 根据ID查询单条数据
	 * @param id
	 * @return
	 */
	public Site findOne(String id);
	
	/**
	 * 查询数据集合
	 * @param site
	 * @return
	 */
	public List<Site> findList(Site site);
	
	/**
	 * 查询所有数据集合
	 * @param site
	 * @return
	 */
	public List<Site> findAll(Site site);
	
	/**
	 * 查询数据总数
	 * @param site
	 * @return
	 */
	public int findTotal(Site site);

	/**
	 * 查询数据总数排除当前数据
	 * @param site
	 * @return
	 */
	public int findExTotal(Site site);
	
	/**
	 * 数据新增
	 * @param site
	 */
	public void save(Site site);
	
	/**
	 * 数据修改
	 * @param site
	 */
	public void update(Site site);
	
	/**
	 * 数据删除
	 * @param listId
	 */
	public void delete(List<String> listId);
}
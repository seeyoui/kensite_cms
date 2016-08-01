/*
 * Powered By cuichen
 * Since 2014 - 2016
 */
package com.seeyoui.kensite.framework.cms.article.persistence;

import com.seeyoui.kensite.common.base.domain.EasyUIDataGrid;
import com.seeyoui.kensite.framework.cms.article.domain.Article;

import java.util.*;

import com.seeyoui.kensite.common.exception.CRUDException;

/**
 * 内容发布文章
 * @author cuichen
 * @version 2.0
 * @since 1.0
 * @date 2016-07-15
 */

public interface ArticleMapper {

	/**
	 * 根据ID查询单条数据
	 * @param id
	 * @return
	 */
	public Article findOne(String id);
	
	/**
	 * 查询数据集合
	 * @param article
	 * @return
	 */
	public List<Article> findList(Article article);
	
	/**
	 * 查询所有数据集合
	 * @param article
	 * @return
	 */
	public List<Article> findAll(Article article);
	
	/**
	 * 查询数据总数
	 * @param article
	 * @return
	 */
	public int findTotal(Article article);

	/**
	 * 查询数据总数排除当前数据
	 * @param article
	 * @return
	 */
	public int findExTotal(Article article);
	
	/**
	 * 数据新增
	 * @param article
	 */
	public void save(Article article);
	
	/**
	 * 数据修改
	 * @param article
	 */
	public void update(Article article);
	
	/**
	 * 点击数据修改
	 * @param id
	 */
	public void hits(String id);
	
	/**
	 * 数据删除
	 * @param listId
	 */
	public void delete(List<String> listId);
}
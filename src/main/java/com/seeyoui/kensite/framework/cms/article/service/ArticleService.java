/*
 * Powered By cuichen
 * Since 2014 - 2016
 */package com.seeyoui.kensite.framework.cms.article.service;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seeyoui.kensite.common.base.service.BaseService;

import java.util.*;

import com.seeyoui.kensite.common.base.domain.EasyUIDataGrid;
import com.seeyoui.kensite.common.base.service.BaseService;
import com.seeyoui.kensite.common.exception.CRUDException;
import com.seeyoui.kensite.common.util.*;
import com.seeyoui.kensite.common.constants.StringConstant;
import com.seeyoui.kensite.framework.cms.article.domain.Article;
import com.seeyoui.kensite.framework.cms.article.persistence.ArticleMapper;

/**
 * 内容发布文章
 * @author cuichen
 * @version 2.0
 * @since 1.0
 * @date 2016-07-15
 */

@Service
public class ArticleService extends BaseService {
	
	@Autowired
	private ArticleMapper articleMapper;

	/**
	 * 根据ID查询单条数据
	 * @param id
	 * @return
	 * @throws CRUDException
	 */
	public Article findOne(String id) throws CRUDException{
		return articleMapper.findOne(id);
	}
	
	/**
	 * 查询数据集合
	 * @param article
	 * @return
	 * @throws CRUDException
	 */
	public List<Article> findList(Article article) throws CRUDException {
		return articleMapper.findList(article);
	}
	
	/**
	 * 查询所有数据集合
	 * @param article
	 * @return
	 * @throws CRUDException
	 */
	public List<Article> findAll(Article article) throws CRUDException {
		return articleMapper.findAll(article);
	}
	
	/**
	 * 查询数据总数
	 * @param article
	 * @return
	 * @throws CRUDException
	 */
	public int findTotal(Article article) throws CRUDException {
		return articleMapper.findTotal(article);
	}
	
	/**
	 * 查询数据总数排除当前数据
	 * @param article
	 * @return
	 * @throws CRUDException
	 */
	public int findExTotal(Article article) throws CRUDException {
		return articleMapper.findExTotal(article);
	}
	
	/**
	 * 数据新增
	 * @param article
	 * @throws CRUDException
	 */
	public void save(Article article) throws CRUDException{
		article.preInsert();
		articleMapper.save(article);
	}
	
	/**
	 * 数据修改
	 * @param article
	 * @throws CRUDException
	 */
	public void update(Article article) throws CRUDException{
		article.preUpdate();
		articleMapper.update(article);			
	}
	
	/**
	 * 数据修改
	 * @param article
	 * @throws CRUDException
	 */
	public void updateContent(Article article) throws CRUDException{
		article.preUpdate();
		articleMapper.updateContent(article);			
	}
	
	/**
	 * 点击数据修改
	 * @param id
	 * @throws CRUDException
	 */
	public void hits(String id) throws CRUDException{
		articleMapper.hits(id);			
	}
	
	/**
	 * 数据删除
	 * @param listId
	 * @throws CRUDException
	 */
	public void delete(List<String> listId) throws CRUDException {
		articleMapper.delete(listId);
	}
}
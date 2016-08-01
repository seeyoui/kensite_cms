/*
 * Powered By cuichen
 * Since 2014 - 2016
 */
package com.seeyoui.kensite.framework.cms.comment.persistence;

import com.seeyoui.kensite.common.base.domain.EasyUIDataGrid;
import com.seeyoui.kensite.framework.cms.comment.domain.Comment;

import java.util.*;

import com.seeyoui.kensite.common.exception.CRUDException;

/**
 * 内容发布评价
 * @author cuichen
 * @version 2.0
 * @since 1.0
 * @date 2016-07-15
 */

public interface CommentMapper {

	/**
	 * 根据ID查询单条数据
	 * @param id
	 * @return
	 */
	public Comment findOne(String id);
	
	/**
	 * 查询数据集合
	 * @param comment
	 * @return
	 */
	public List<Comment> findList(Comment comment);
	
	/**
	 * 查询所有数据集合
	 * @param comment
	 * @return
	 */
	public List<Comment> findAll(Comment comment);
	
	/**
	 * 查询数据总数
	 * @param comment
	 * @return
	 */
	public int findTotal(Comment comment);

	/**
	 * 查询数据总数排除当前数据
	 * @param comment
	 * @return
	 */
	public int findExTotal(Comment comment);
	
	/**
	 * 数据新增
	 * @param comment
	 */
	public void save(Comment comment);
	
	/**
	 * 数据修改
	 * @param comment
	 */
	public void update(Comment comment);
	
	/**
	 * 数据删除
	 * @param listId
	 */
	public void delete(List<String> listId);
}
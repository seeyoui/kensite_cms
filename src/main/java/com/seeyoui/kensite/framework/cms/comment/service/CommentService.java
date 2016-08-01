/*
 * Powered By cuichen
 * Since 2014 - 2016
 */package com.seeyoui.kensite.framework.cms.comment.service;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seeyoui.kensite.common.base.service.BaseService;

import java.util.*;

import com.seeyoui.kensite.common.base.domain.EasyUIDataGrid;
import com.seeyoui.kensite.common.base.service.BaseService;
import com.seeyoui.kensite.common.exception.CRUDException;
import com.seeyoui.kensite.common.util.*;
import com.seeyoui.kensite.common.constants.StringConstant;
import com.seeyoui.kensite.framework.cms.comment.domain.Comment;
import com.seeyoui.kensite.framework.cms.comment.persistence.CommentMapper;

/**
 * 内容发布评价
 * @author cuichen
 * @version 2.0
 * @since 1.0
 * @date 2016-07-15
 */

@Service
public class CommentService extends BaseService {
	
	@Autowired
	private CommentMapper commentMapper;

	/**
	 * 根据ID查询单条数据
	 * @param id
	 * @return
	 * @throws CRUDException
	 */
	public Comment findOne(String id) throws CRUDException{
		return commentMapper.findOne(id);
	}
	
	/**
	 * 查询数据集合
	 * @param comment
	 * @return
	 * @throws CRUDException
	 */
	public List<Comment> findList(Comment comment) throws CRUDException {
		return commentMapper.findList(comment);
	}
	
	/**
	 * 查询所有数据集合
	 * @param comment
	 * @return
	 * @throws CRUDException
	 */
	public List<Comment> findAll(Comment comment) throws CRUDException {
		return commentMapper.findAll(comment);
	}
	
	/**
	 * 查询数据总数
	 * @param comment
	 * @return
	 * @throws CRUDException
	 */
	public int findTotal(Comment comment) throws CRUDException {
		return commentMapper.findTotal(comment);
	}
	
	/**
	 * 查询数据总数排除当前数据
	 * @param comment
	 * @return
	 * @throws CRUDException
	 */
	public int findExTotal(Comment comment) throws CRUDException {
		return commentMapper.findExTotal(comment);
	}
	
	/**
	 * 数据新增
	 * @param comment
	 * @throws CRUDException
	 */
	public void save(Comment comment) throws CRUDException{
		comment.preInsert();
		commentMapper.save(comment);
	}
	
	/**
	 * 数据修改
	 * @param comment
	 * @throws CRUDException
	 */
	public void update(Comment comment) throws CRUDException{
		comment.preUpdate();
		commentMapper.update(comment);			
	}
	
	/**
	 * 数据删除
	 * @param listId
	 * @throws CRUDException
	 */
	public void delete(List<String> listId) throws CRUDException {
		commentMapper.delete(listId);
	}
}
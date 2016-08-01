/*
 * Powered By cuichen
 * Since 2014 - 2016
 */package com.seeyoui.kensite.framework.cms.tagcloud.service;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seeyoui.kensite.common.base.service.BaseService;

import java.util.*;

import com.seeyoui.kensite.common.base.domain.EasyUIDataGrid;
import com.seeyoui.kensite.common.base.service.BaseService;
import com.seeyoui.kensite.common.exception.CRUDException;
import com.seeyoui.kensite.common.util.*;
import com.seeyoui.kensite.common.constants.StringConstant;
import com.seeyoui.kensite.framework.cms.tagcloud.domain.Tagcloud;
import com.seeyoui.kensite.framework.cms.tagcloud.persistence.TagcloudMapper;

/**
 * 标签云
 * @author cuichen
 * @version 2.0
 * @since 1.0
 * @date 2016-07-27
 */

@Service
public class TagcloudService extends BaseService {
	
	@Autowired
	private TagcloudMapper tagcloudMapper;

	/**
	 * 根据ID查询单条数据
	 * @param id
	 * @return
	 * @throws CRUDException
	 */
	public Tagcloud findOne(String id) throws CRUDException{
		return tagcloudMapper.findOne(id);
	}
	
	/**
	 * 查询数据集合
	 * @param tagcloud
	 * @return
	 * @throws CRUDException
	 */
	public List<Tagcloud> findList(Tagcloud tagcloud) throws CRUDException {
		return tagcloudMapper.findList(tagcloud);
	}
	
	/**
	 * 查询所有数据集合
	 * @param tagcloud
	 * @return
	 * @throws CRUDException
	 */
	public List<Tagcloud> findAll(Tagcloud tagcloud) throws CRUDException {
		return tagcloudMapper.findAll(tagcloud);
	}
	
	/**
	 * 查询数据总数
	 * @param tagcloud
	 * @return
	 * @throws CRUDException
	 */
	public int findTotal(Tagcloud tagcloud) throws CRUDException {
		return tagcloudMapper.findTotal(tagcloud);
	}
	
	/**
	 * 查询数据总数排除当前数据
	 * @param tagcloud
	 * @return
	 * @throws CRUDException
	 */
	public int findExTotal(Tagcloud tagcloud) throws CRUDException {
		return tagcloudMapper.findExTotal(tagcloud);
	}
	
	/**
	 * 数据新增
	 * @param tagcloud
	 * @throws CRUDException
	 */
	public void save(Tagcloud tagcloud) throws CRUDException{
		tagcloud.preInsert();
		tagcloudMapper.save(tagcloud);
	}
	
	/**
	 * 数据修改
	 * @param tagcloud
	 * @throws CRUDException
	 */
	public void update(Tagcloud tagcloud) throws CRUDException{
		tagcloud.preUpdate();
		tagcloudMapper.update(tagcloud);			
	}
	
	/**
	 * 数据删除
	 * @param listId
	 * @throws CRUDException
	 */
	public void delete(List<String> listId) throws CRUDException {
		tagcloudMapper.delete(listId);
	}
}
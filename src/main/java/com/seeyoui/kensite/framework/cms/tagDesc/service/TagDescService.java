/*
 * Powered By cuichen
 * Since 2014 - 2016
 */package com.seeyoui.kensite.framework.cms.tagDesc.service;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seeyoui.kensite.common.base.service.BaseService;

import java.util.*;

import com.seeyoui.kensite.common.base.domain.EasyUIDataGrid;
import com.seeyoui.kensite.common.base.service.BaseService;
import com.seeyoui.kensite.common.exception.CRUDException;
import com.seeyoui.kensite.common.util.*;
import com.seeyoui.kensite.common.constants.StringConstant;
import com.seeyoui.kensite.framework.cms.tagDesc.domain.TagDesc;
import com.seeyoui.kensite.framework.cms.tagDesc.persistence.TagDescMapper;

/**
 * 标签描述
 * @author cuichen
 * @version 2.0
 * @since 1.0
 * @date 2016-09-24
 */

@Service
public class TagDescService extends BaseService {
	
	@Autowired
	private TagDescMapper tagDescMapper;

	/**
	 * 根据ID查询单条数据
	 * @param id
	 * @return
	 * @throws CRUDException
	 */
	public TagDesc findOne(String id) throws CRUDException{
		return tagDescMapper.findOne(id);
	}
	
	/**
	 * 查询数据集合
	 * @param tagDesc
	 * @return
	 * @throws CRUDException
	 */
	public List<TagDesc> findList(TagDesc tagDesc) throws CRUDException {
		return tagDescMapper.findList(tagDesc);
	}
	
	/**
	 * 查询所有数据集合
	 * @param tagDesc
	 * @return
	 * @throws CRUDException
	 */
	public List<TagDesc> findAll(TagDesc tagDesc) throws CRUDException {
		return tagDescMapper.findAll(tagDesc);
	}
	
	/**
	 * 查询数据总数
	 * @param tagDesc
	 * @return
	 * @throws CRUDException
	 */
	public int findTotal(TagDesc tagDesc) throws CRUDException {
		return tagDescMapper.findTotal(tagDesc);
	}
	
	/**
	 * 查询数据总数排除当前数据
	 * @param tagDesc
	 * @return
	 * @throws CRUDException
	 */
	public int findExTotal(TagDesc tagDesc) throws CRUDException {
		return tagDescMapper.findExTotal(tagDesc);
	}
	
	/**
	 * 数据新增
	 * @param tagDesc
	 * @throws CRUDException
	 */
	public void save(TagDesc tagDesc) throws CRUDException{
		tagDesc.preInsert();
		tagDescMapper.save(tagDesc);
	}
	
	/**
	 * 数据修改
	 * @param tagDesc
	 * @throws CRUDException
	 */
	public void update(TagDesc tagDesc) throws CRUDException{
		tagDesc.preUpdate();
		tagDescMapper.update(tagDesc);			
	}
	
	/**
	 * 数据删除
	 * @param listId
	 * @throws CRUDException
	 */
	public void delete(List<String> listId) throws CRUDException {
		tagDescMapper.delete(listId);
	}
}
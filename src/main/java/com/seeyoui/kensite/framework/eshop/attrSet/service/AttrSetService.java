/*
 * Powered By cuichen
 * Since 2014 - 2017
 */package com.seeyoui.kensite.framework.eshop.attrSet.service;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seeyoui.kensite.common.base.service.BaseService;

import java.util.*;

import com.seeyoui.kensite.common.base.domain.EasyUIDataGrid;
import com.seeyoui.kensite.common.base.service.BaseService;
import com.seeyoui.kensite.common.exception.CRUDException;
import com.seeyoui.kensite.common.util.*;
import com.seeyoui.kensite.common.constants.StringConstant;
import com.seeyoui.kensite.framework.eshop.attrSet.domain.AttrSet;
import com.seeyoui.kensite.framework.eshop.attrSet.persistence.AttrSetMapper;

/**
 * 属性设置
 * @author cuichen
 * @version 2.0
 * @since 1.0
 * @date 2017-03-08
 */

@Service
public class AttrSetService extends BaseService {
	
	@Autowired
	private AttrSetMapper attrSetMapper;

	/**
	 * 根据ID查询单条数据
	 * @param id
	 * @return
	 * @throws CRUDException
	 */
	public AttrSet findOne(String id) throws CRUDException{
		return attrSetMapper.findOne(id);
	}
	
	/**
	 * 查询数据集合
	 * @param attrSet
	 * @return
	 * @throws CRUDException
	 */
	public List<AttrSet> findList(AttrSet attrSet) throws CRUDException {
		return attrSetMapper.findList(attrSet);
	}
	
	/**
	 * 查询所有数据集合
	 * @param attrSet
	 * @return
	 * @throws CRUDException
	 */
	public List<AttrSet> findAll(AttrSet attrSet) throws CRUDException {
		return attrSetMapper.findAll(attrSet);
	}
	
	/**
	 * 查询数据总数
	 * @param attrSet
	 * @return
	 * @throws CRUDException
	 */
	public int findTotal(AttrSet attrSet) throws CRUDException {
		return attrSetMapper.findTotal(attrSet);
	}
	
	/**
	 * 查询数据总数排除当前数据
	 * @param attrSet
	 * @return
	 * @throws CRUDException
	 */
	public int findExTotal(AttrSet attrSet) throws CRUDException {
		return attrSetMapper.findExTotal(attrSet);
	}
	
	/**
	 * 数据新增
	 * @param attrSet
	 * @throws CRUDException
	 */
	public void save(AttrSet attrSet) throws CRUDException{
		attrSet.preInsert();
		attrSetMapper.save(attrSet);
	}
	
	/**
	 * 数据修改
	 * @param attrSet
	 * @throws CRUDException
	 */
	public void update(AttrSet attrSet) throws CRUDException{
		attrSet.preUpdate();
		attrSetMapper.update(attrSet);			
	}
	
	/**
	 * 数据删除
	 * @param listId
	 * @throws CRUDException
	 */
	public void delete(List<String> listId) throws CRUDException {
		attrSetMapper.delete(listId);
	}
	
	/**
	 * 数据假删除
	 * @param attrSet
	 * @throws CRUDException
	 */
	public void remove(AttrSet attrSet) throws CRUDException{
		attrSet.preUpdate();
		attrSetMapper.remove(attrSet);			
	}
	
}
/*
 * Powered By cuichen
 * Since 2014 - 2017
 */package com.seeyoui.kensite.framework.eshop.specSet.service;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seeyoui.kensite.common.base.service.BaseService;

import java.util.*;

import com.seeyoui.kensite.common.base.domain.EasyUIDataGrid;
import com.seeyoui.kensite.common.base.service.BaseService;
import com.seeyoui.kensite.common.exception.CRUDException;
import com.seeyoui.kensite.common.util.*;
import com.seeyoui.kensite.common.constants.StringConstant;
import com.seeyoui.kensite.framework.eshop.specSet.domain.SpecSet;
import com.seeyoui.kensite.framework.eshop.specSet.persistence.SpecSetMapper;

/**
 * 规格设置
 * @author cuichen
 * @version 2.0
 * @since 1.0
 * @date 2017-03-08
 */

@Service
public class SpecSetService extends BaseService {
	
	@Autowired
	private SpecSetMapper specSetMapper;

	/**
	 * 根据ID查询单条数据
	 * @param id
	 * @return
	 * @throws CRUDException
	 */
	public SpecSet findOne(String id) throws CRUDException{
		return specSetMapper.findOne(id);
	}
	
	/**
	 * 查询数据集合
	 * @param specSet
	 * @return
	 * @throws CRUDException
	 */
	public List<SpecSet> findList(SpecSet specSet) throws CRUDException {
		return specSetMapper.findList(specSet);
	}
	
	/**
	 * 查询所有数据集合
	 * @param specSet
	 * @return
	 * @throws CRUDException
	 */
	public List<SpecSet> findAll(SpecSet specSet) throws CRUDException {
		return specSetMapper.findAll(specSet);
	}
	
	/**
	 * 查询数据总数
	 * @param specSet
	 * @return
	 * @throws CRUDException
	 */
	public int findTotal(SpecSet specSet) throws CRUDException {
		return specSetMapper.findTotal(specSet);
	}
	
	/**
	 * 查询数据总数排除当前数据
	 * @param specSet
	 * @return
	 * @throws CRUDException
	 */
	public int findExTotal(SpecSet specSet) throws CRUDException {
		return specSetMapper.findExTotal(specSet);
	}
	
	/**
	 * 数据新增
	 * @param specSet
	 * @throws CRUDException
	 */
	public void save(SpecSet specSet) throws CRUDException{
		specSet.preInsert();
		specSetMapper.save(specSet);
	}
	
	/**
	 * 数据修改
	 * @param specSet
	 * @throws CRUDException
	 */
	public void update(SpecSet specSet) throws CRUDException{
		specSet.preUpdate();
		specSetMapper.update(specSet);			
	}
	
	/**
	 * 数据删除
	 * @param listId
	 * @throws CRUDException
	 */
	public void delete(List<String> listId) throws CRUDException {
		specSetMapper.delete(listId);
	}
	
	/**
	 * 数据假删除
	 * @param specSet
	 * @throws CRUDException
	 */
	public void remove(SpecSet specSet) throws CRUDException{
		specSet.preUpdate();
		specSetMapper.remove(specSet);			
	}
	
}
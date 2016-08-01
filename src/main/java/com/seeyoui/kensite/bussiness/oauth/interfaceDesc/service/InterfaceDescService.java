/*
 * Powered By cuichen
 * Since 2014 - 2015
 */package com.seeyoui.kensite.bussiness.oauth.interfaceDesc.service;  
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seeyoui.kensite.bussiness.oauth.interfaceDesc.domain.InterfaceDesc;
import com.seeyoui.kensite.bussiness.oauth.interfaceDesc.persistence.InterfaceDescMapper;
import com.seeyoui.kensite.common.base.service.BaseService;
import com.seeyoui.kensite.common.exception.CRUDException;

/**
 * 接口描述
 * @author cuichen
 * @version 1.0
 * @since 1.0
 * @date 2015-12-09
 */
@Service
public class InterfaceDescService extends BaseService {
	
	@Autowired
	private InterfaceDescMapper interfaceDescMapper;

	/**
	 * 根据ID查询单条数据
	 * @param id
	 * @return
	 * @throws CRUDException
	 */
	public InterfaceDesc findOne(String id) throws CRUDException{
		return interfaceDescMapper.findOne(id);
	}
	
	/**
	 * 查询数据集合
	 * @param interfaceDesc
	 * @return
	 * @throws CRUDException
	 */
	public List<InterfaceDesc> findList(InterfaceDesc interfaceDesc) throws CRUDException {
		return interfaceDescMapper.findList(interfaceDesc);
	}
	
	/**
	 * 查询所有数据集合
	 * @param interfaceDesc
	 * @return
	 * @throws CRUDException
	 */
	public List<InterfaceDesc> findAll(InterfaceDesc interfaceDesc) throws CRUDException {
		return interfaceDescMapper.findAll(interfaceDesc);
	}
	
	/**
	 * 查询数据总数
	 * @param interfaceDesc
	 * @return
	 * @throws CRUDException
	 */
	public int findTotal(InterfaceDesc interfaceDesc) throws CRUDException {
		return interfaceDescMapper.findTotal(interfaceDesc);
	}
	
	/**
	 * 数据新增
	 * @param interfaceDesc
	 * @throws CRUDException
	 */
	public void save(InterfaceDesc interfaceDesc) throws CRUDException{
		interfaceDesc.preInsert();
		interfaceDescMapper.save(interfaceDesc);
	}
	
	/**
	 * 数据修改
	 * @param interfaceDesc
	 * @throws CRUDException
	 */
	public void update(InterfaceDesc interfaceDesc) throws CRUDException{
		interfaceDesc.preUpdate();
		interfaceDescMapper.update(interfaceDesc);			
	}
	
	/**
	 * 数据删除
	 * @param listId
	 * @throws CRUDException
	 */
	public void delete(List<String> listId) throws CRUDException {
		interfaceDescMapper.delete(listId);
	}
	
}
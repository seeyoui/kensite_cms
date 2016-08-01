/*
 * Powered By cuichen
 * Since 2014 - 2015
 */package com.seeyoui.kensite.bussiness.oauth.interfaceCatalog.service;  
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seeyoui.kensite.bussiness.oauth.interfaceCatalog.domain.InterfaceCatalog;
import com.seeyoui.kensite.bussiness.oauth.interfaceCatalog.persistence.InterfaceCatalogMapper;
import com.seeyoui.kensite.common.base.service.BaseService;
import com.seeyoui.kensite.common.exception.CRUDException;

/**
 * 接口目录
 * @author cuichen
 * @version 1.0
 * @since 1.0
 * @date 2015-12-09
 */
@Service
public class InterfaceCatalogService extends BaseService {
	
	@Autowired
	private InterfaceCatalogMapper interfaceCatalogMapper;

	/**
	 * 根据ID查询单条数据
	 * @param id
	 * @return
	 * @throws CRUDException
	 */
	public InterfaceCatalog findOne(String id) throws CRUDException{
		return interfaceCatalogMapper.findOne(id);
	}
	
	/**
	 * 查询数据集合
	 * @param interfaceCatalog
	 * @return
	 * @throws CRUDException
	 */
	public List<InterfaceCatalog> findList(InterfaceCatalog interfaceCatalog) throws CRUDException {
		return interfaceCatalogMapper.findList(interfaceCatalog);
	}
	
	/**
	 * 查询所有数据集合
	 * @param interfaceCatalog
	 * @return
	 * @throws CRUDException
	 */
	public List<InterfaceCatalog> findAll(InterfaceCatalog interfaceCatalog) throws CRUDException {
		return interfaceCatalogMapper.findAll(interfaceCatalog);
	}
	
	/**
	 * 查询数据总数
	 * @param interfaceCatalog
	 * @return
	 * @throws CRUDException
	 */
	public int findTotal(InterfaceCatalog interfaceCatalog) throws CRUDException {
		return interfaceCatalogMapper.findTotal(interfaceCatalog);
	}
	
	/**
	 * 数据新增
	 * @param interfaceCatalog
	 * @throws CRUDException
	 */
	public void save(InterfaceCatalog interfaceCatalog) throws CRUDException{
		interfaceCatalog.preInsert();
		interfaceCatalogMapper.save(interfaceCatalog);
	}
	
	/**
	 * 数据修改
	 * @param interfaceCatalog
	 * @throws CRUDException
	 */
	public void update(InterfaceCatalog interfaceCatalog) throws CRUDException{
		interfaceCatalog.preUpdate();
		interfaceCatalogMapper.update(interfaceCatalog);			
	}
	
	/**
	 * 数据删除
	 * @param listId
	 * @throws CRUDException
	 */
	public void delete(List<String> listId) throws CRUDException {
		interfaceCatalogMapper.delete(listId);
	}
	
}
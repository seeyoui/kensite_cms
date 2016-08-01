/*
 * Powered By cuichen
 * Since 2014 - 2015
 */
package com.seeyoui.kensite.bussiness.oauth.interfaceCatalog.persistence;  

import java.util.List;

import com.seeyoui.kensite.bussiness.oauth.interfaceCatalog.domain.InterfaceCatalog;

/**
 * 接口目录
 * @author cuichen
 * @version 1.0
 * @since 1.0
 * @date 2015-12-09
 */
public interface InterfaceCatalogMapper {

	/**
	 * 根据ID查询单条数据
	 * @param id
	 * @return
	 */
	public InterfaceCatalog findOne(String id);
	
	/**
	 * 查询数据集合
	 * @param interfaceCatalog
	 * @return
	 */
	public List<InterfaceCatalog> findList(InterfaceCatalog interfaceCatalog);
	
	/**
	 * 查询所有数据集合
	 * @param interfaceCatalog
	 * @return
	 */
	public List<InterfaceCatalog> findAll(InterfaceCatalog interfaceCatalog);
	
	/**
	 * 查询数据总数
	 * @param interfaceCatalog
	 * @return
	 */
	public int findTotal(InterfaceCatalog interfaceCatalog);
	
	/**
	 * 数据新增
	 * @param interfaceCatalog
	 */
	public void save(InterfaceCatalog interfaceCatalog);
	
	/**
	 * 数据修改
	 * @param interfaceCatalog
	 */
	public void update(InterfaceCatalog interfaceCatalog);
	
	/**
	 * 数据删除
	 * @param listId
	 */
	public void delete(List<String> listId);
}
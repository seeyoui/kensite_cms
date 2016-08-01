/*
 * Powered By cuichen
 * Since 2014 - 2015
 */
package com.seeyoui.kensite.bussiness.oauth.interfaceDesc.persistence;  

import java.util.List;

import com.seeyoui.kensite.bussiness.oauth.interfaceDesc.domain.InterfaceDesc;

/**
 * 接口描述
 * @author cuichen
 * @version 1.0
 * @since 1.0
 * @date 2015-12-09
 */
public interface InterfaceDescMapper {

	/**
	 * 根据ID查询单条数据
	 * @param id
	 * @return
	 */
	public InterfaceDesc findOne(String id);
	
	/**
	 * 查询数据集合
	 * @param interfaceDesc
	 * @return
	 */
	public List<InterfaceDesc> findList(InterfaceDesc interfaceDesc);
	
	/**
	 * 查询所有数据集合
	 * @param interfaceDesc
	 * @return
	 */
	public List<InterfaceDesc> findAll(InterfaceDesc interfaceDesc);
	
	/**
	 * 查询数据总数
	 * @param interfaceDesc
	 * @return
	 */
	public int findTotal(InterfaceDesc interfaceDesc);
	
	/**
	 * 数据新增
	 * @param interfaceDesc
	 */
	public void save(InterfaceDesc interfaceDesc);
	
	/**
	 * 数据修改
	 * @param interfaceDesc
	 */
	public void update(InterfaceDesc interfaceDesc);
	
	/**
	 * 数据删除
	 * @param listId
	 */
	public void delete(List<String> listId);
}
/*
 * Powered By cuichen
 * Since 2014 - 2015
 */
package com.seeyoui.kensite.framework.system.persistence;  

import java.util.List;

import com.seeyoui.kensite.framework.system.domain.SysDepartment;

/**
 * @author cuichen
 * @version 1.0
 * @since 1.0
 */
public interface SysDepartmentMapper {

	/**
	 * 根据ID查询单条数据
	 * @param id
	 * @return
	 */
	public SysDepartment findOne(String id);
	
	/**
	 * 查询数据集合
	 * @param sysDepartment
	 * @return
	 */
	public List<SysDepartment> findList(SysDepartment sysDepartment);
	
	/**
	 * 查询数据集合
	 * @param sysDepartment
	 * @return
	 */
	public List<SysDepartment> findAll(SysDepartment sysDepartment);
	
	/**
	 * 查询数据总数
	 * @param sysDepartment
	 * @return
	 */
	public Integer findTotal(SysDepartment sysDepartment);
	
	/**
	 * 数据新增
	 * @param sysDepartment
	 */
	public void save(SysDepartment sysDepartment);
	
	/**
	 * 数据修改
	 * @param sysDepartment
	 */
	public void update(SysDepartment sysDepartment);
	
	/**
	 * 数据删除
	 * @param listId
	 */
	public void delete(List<String> listId);
}
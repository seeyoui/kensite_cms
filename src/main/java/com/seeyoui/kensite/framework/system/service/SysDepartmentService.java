/*
 * Powered By cuichen
 * Since 2014 - 2015
 */package com.seeyoui.kensite.framework.system.service;  
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seeyoui.kensite.common.base.service.BaseService;
import com.seeyoui.kensite.common.exception.CRUDException;
import com.seeyoui.kensite.framework.system.domain.SysDepartment;
import com.seeyoui.kensite.framework.system.persistence.SysDepartmentMapper;

/**
 * @author cuichen
 * @version 1.0
 * @since 1.0
 */
@Service
public class SysDepartmentService extends BaseService {
	
	@Autowired
	private SysDepartmentMapper sysDepartmentMapper;

	/**
	 * 根据ID查询单条数据
	 * @param id
	 * @return
	 * @throws CRUDException
	 */
	public SysDepartment findOne(String id) throws CRUDException{
		return sysDepartmentMapper.findOne(id);
	}
	
	/**
	 * 查询数据集合
	 * @param sysDepartment
	 * @return
	 * @throws CRUDException
	 */
	public List<SysDepartment> findList(SysDepartment sysDepartment) throws CRUDException {
		return sysDepartmentMapper.findList(sysDepartment);
	}
	
	/**
	 * 查询数据集合
	 * @param sysDepartment
	 * @return
	 * @throws CRUDException
	 */
	public List<SysDepartment> findAll(SysDepartment sysDepartment) throws CRUDException {
		return sysDepartmentMapper.findAll(sysDepartment);
	}
	
	/**
	 * 查询数据总数
	 * @param sysDepartment
	 * @return
	 * @throws CRUDException
	 */
	public Integer findTotal(SysDepartment sysDepartment) throws CRUDException {
		return sysDepartmentMapper.findTotal(sysDepartment);
	}
	
	/**
	 * 数据新增
	 * @param sysDepartment
	 * @throws CRUDException
	 */
	public void save(SysDepartment sysDepartment) throws CRUDException{
		sysDepartment.preInsert();
		sysDepartmentMapper.save(sysDepartment);
	}
	
	/**
	 * 数据修改
	 * @param sysDepartment
	 * @throws CRUDException
	 */
	public void update(SysDepartment sysDepartment) throws CRUDException{
		sysDepartment.preUpdate();
		sysDepartmentMapper.update(sysDepartment);			
	}
	
	/**
	 * 数据删除
	 * @param listId
	 * @throws CRUDException
	 */
	public void delete(List<String> listId) throws CRUDException {
		sysDepartmentMapper.delete(listId);
	}
	
}
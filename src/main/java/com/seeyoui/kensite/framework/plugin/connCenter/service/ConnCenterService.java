/*
 * Powered By cuichen
 * Since 2014 - 2016
 */package com.seeyoui.kensite.framework.plugin.connCenter.service;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seeyoui.kensite.common.base.service.BaseService;

import java.util.*;

import com.seeyoui.kensite.common.base.domain.EasyUIDataGrid;
import com.seeyoui.kensite.common.base.service.BaseService;
import com.seeyoui.kensite.common.exception.CRUDException;
import com.seeyoui.kensite.common.util.*;
import com.seeyoui.kensite.common.constants.StringConstant;
import com.seeyoui.kensite.framework.plugin.connCenter.domain.ConnCenter;
import com.seeyoui.kensite.framework.plugin.connCenter.persistence.ConnCenterMapper;

/**
 * 数据库连接中心
 * @author cuichen
 * @version 2.0
 * @since 1.0
 * @date 2016-09-27
 */

@Service
public class ConnCenterService extends BaseService {
	
	@Autowired
	private ConnCenterMapper connCenterMapper;

	/**
	 * 根据ID查询单条数据
	 * @param id
	 * @return
	 * @throws CRUDException
	 */
	public ConnCenter findOne(String id) throws CRUDException{
		return connCenterMapper.findOne(id);
	}
	
	/**
	 * 查询数据集合
	 * @param connCenter
	 * @return
	 * @throws CRUDException
	 */
	public List<ConnCenter> findList(ConnCenter connCenter) throws CRUDException {
		return connCenterMapper.findList(connCenter);
	}
	
	/**
	 * 查询所有数据集合
	 * @param connCenter
	 * @return
	 * @throws CRUDException
	 */
	public List<ConnCenter> findAll(ConnCenter connCenter) throws CRUDException {
		return connCenterMapper.findAll(connCenter);
	}
	
	/**
	 * 查询数据总数
	 * @param connCenter
	 * @return
	 * @throws CRUDException
	 */
	public int findTotal(ConnCenter connCenter) throws CRUDException {
		return connCenterMapper.findTotal(connCenter);
	}
	
	/**
	 * 查询数据总数排除当前数据
	 * @param connCenter
	 * @return
	 * @throws CRUDException
	 */
	public int findExTotal(ConnCenter connCenter) throws CRUDException {
		return connCenterMapper.findExTotal(connCenter);
	}
	
	/**
	 * 数据新增
	 * @param connCenter
	 * @throws CRUDException
	 */
	public void save(ConnCenter connCenter) throws CRUDException{
		connCenter.preInsert();
		connCenterMapper.save(connCenter);
	}
	
	/**
	 * 数据修改
	 * @param connCenter
	 * @throws CRUDException
	 */
	public void update(ConnCenter connCenter) throws CRUDException{
		connCenter.preUpdate();
		connCenterMapper.update(connCenter);			
	}
	
	/**
	 * 数据删除
	 * @param listId
	 * @throws CRUDException
	 */
	public void delete(List<String> listId) throws CRUDException {
		connCenterMapper.delete(listId);
	}
}
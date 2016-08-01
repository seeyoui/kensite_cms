/*
 * Powered By cuichen
 * Since 2014 - 2015
 */package com.seeyoui.kensite.framework.plugin.log.service;  
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seeyoui.kensite.common.base.service.BaseService;
import com.seeyoui.kensite.common.exception.CRUDException;
import com.seeyoui.kensite.framework.plugin.log.domain.Log;
import com.seeyoui.kensite.framework.plugin.log.persistence.LogMapper;

/**
 * @author cuichen
 * @version 1.0
 * @since 1.0
 */
@Service
public class LogService extends BaseService {
	
	@Autowired
	private LogMapper logMapper;

	/**
	 * 根据ID查询单条数据
	 * @param id
	 * @return
	 * @throws CRUDException
	 */
	public Log findOne(String id) throws CRUDException{
		return logMapper.findOne(id);
	}
	
	/**
	 * 查询数据集合
	 * @param log
	 * @return
	 * @throws CRUDException
	 */
	public List<Log> findList(Log log) throws CRUDException {
		return logMapper.findList(log);
	}
	
	/**
	 * 查询数据总数
	 * @param userinfo
	 * @return
	 * @throws CRUDException
	 */
	public int findTotal(Log log) throws CRUDException {
		return logMapper.findTotal(log);
	}
	
	/**
	 * 数据新增
	 * @param log
	 * @throws CRUDException
	 */
	public void save(Log log) throws CRUDException{
		log.preInsert();
		logMapper.save(log);
	}
	
}
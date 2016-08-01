/*
 * Powered By cuichen
 * Since 2014 - 2016
 */package com.seeyoui.kensite.framework.cms.site.service;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seeyoui.kensite.common.base.service.BaseService;

import java.util.*;

import com.seeyoui.kensite.common.base.domain.EasyUIDataGrid;
import com.seeyoui.kensite.common.base.service.BaseService;
import com.seeyoui.kensite.common.exception.CRUDException;
import com.seeyoui.kensite.common.util.*;
import com.seeyoui.kensite.common.constants.StringConstant;
import com.seeyoui.kensite.framework.cms.site.domain.Site;
import com.seeyoui.kensite.framework.cms.site.persistence.SiteMapper;

/**
 * 内容发布站点
 * @author cuichen
 * @version 2.0
 * @since 1.0
 * @date 2016-07-15
 */

@Service
public class SiteService extends BaseService {
	
	@Autowired
	private SiteMapper siteMapper;

	/**
	 * 根据ID查询单条数据
	 * @param id
	 * @return
	 * @throws CRUDException
	 */
	public Site findOne(String id) throws CRUDException{
		return siteMapper.findOne(id);
	}
	
	/**
	 * 查询数据集合
	 * @param site
	 * @return
	 * @throws CRUDException
	 */
	public List<Site> findList(Site site) throws CRUDException {
		return siteMapper.findList(site);
	}
	
	/**
	 * 查询所有数据集合
	 * @param site
	 * @return
	 * @throws CRUDException
	 */
	public List<Site> findAll(Site site) throws CRUDException {
		return siteMapper.findAll(site);
	}
	
	/**
	 * 查询数据总数
	 * @param site
	 * @return
	 * @throws CRUDException
	 */
	public int findTotal(Site site) throws CRUDException {
		return siteMapper.findTotal(site);
	}
	
	/**
	 * 查询数据总数排除当前数据
	 * @param site
	 * @return
	 * @throws CRUDException
	 */
	public int findExTotal(Site site) throws CRUDException {
		return siteMapper.findExTotal(site);
	}
	
	/**
	 * 数据新增
	 * @param site
	 * @throws CRUDException
	 */
	public void save(Site site) throws CRUDException{
		site.preInsert();
		siteMapper.save(site);
	}
	
	/**
	 * 数据修改
	 * @param site
	 * @throws CRUDException
	 */
	public void update(Site site) throws CRUDException{
		site.preUpdate();
		siteMapper.update(site);			
	}
	
	/**
	 * 数据删除
	 * @param listId
	 * @throws CRUDException
	 */
	public void delete(List<String> listId) throws CRUDException {
		siteMapper.delete(listId);
	}
}
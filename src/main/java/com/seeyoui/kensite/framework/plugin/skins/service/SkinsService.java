/*
 * Powered By cuichen
 * Since 2014 - 2015
 */package com.seeyoui.kensite.framework.plugin.skins.service;  
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seeyoui.kensite.common.base.service.BaseService;
import com.seeyoui.kensite.common.exception.CRUDException;
import com.seeyoui.kensite.framework.plugin.skins.domain.Skins;
import com.seeyoui.kensite.framework.plugin.skins.persistence.SkinsMapper;
import com.seeyoui.kensite.framework.system.util.SkinsUtils;

/**
 * @author cuichen
 * @version 1.0
 * @since 1.0
 */
@Service
public class SkinsService extends BaseService {
	
	@Autowired
	private SkinsMapper skinsMapper;

	/**
	 * 根据ID查询单条数据
	 * @param id
	 * @return
	 * @throws CRUDException
	 */
	public Skins findOne(String id) throws CRUDException{
		return skinsMapper.findOne(id);
	}
	
	/**
	 * 查询数据集合
	 * @param skins
	 * @return
	 * @throws CRUDException
	 */
	public List<Skins> findList(Skins skins) throws CRUDException {
		return skinsMapper.findList(skins);
	}
	
	/**
	 * 查询所有数据集合
	 * @param skins
	 * @return
	 * @throws CRUDException
	 */
	public List<Skins> findAll(Skins skins) throws CRUDException {
		return skinsMapper.findAll(skins);
	}
	
	/**
	 * 查询数据总数
	 * @param skins
	 * @return
	 * @throws CRUDException
	 */
	public int findTotal(Skins skins) throws CRUDException {
		return skinsMapper.findTotal(skins);
	}
	
	/**
	 * 数据新增
	 * @param skins
	 * @throws CRUDException
	 */
	public void save(Skins skins) throws CRUDException{
		skins.preInsert();
		skinsMapper.save(skins);
	}
	
	/**
	 * 数据修改
	 * @param skins
	 * @throws CRUDException
	 */
	public void update(Skins skins) throws CRUDException{
		skins.preUpdate();
		skinsMapper.update(skins);			
	}
	
	/**
	 * 数据删除
	 * @param listId
	 * @throws CRUDException
	 */
	public void delete(List<String> listId) throws CRUDException {
		skinsMapper.delete(listId);
	}
	
	/**
	 * 查询当前系统皮肤
	 * @return
	 * @throws CRUDException
	 */
	public Skins findCurrent() throws CRUDException{
		return skinsMapper.findCurrent();
	}
	
	/**
	 * 选中
	 * @param skins
	 * @throws CRUDException
	 */
	public void chose(Skins skins) throws CRUDException{
		skins.preUpdate();
		skinsMapper.unchose(skins);
		skinsMapper.chose(skins);
		SkinsUtils.cleanCache();
	}
	
}
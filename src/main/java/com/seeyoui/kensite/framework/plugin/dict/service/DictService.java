/*
 * Powered By cuichen
 * Since 2014 - 2015
 */package com.seeyoui.kensite.framework.plugin.dict.service;  
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seeyoui.kensite.common.base.service.BaseService;
import com.seeyoui.kensite.common.exception.CRUDException;
import com.seeyoui.kensite.common.util.CacheUtils;
import com.seeyoui.kensite.framework.plugin.dict.domain.Dict;
import com.seeyoui.kensite.framework.plugin.dict.persistence.DictMapper;
import com.seeyoui.kensite.framework.system.util.DictUtils;

/**
 * @author cuichen
 * @version 1.0
 * @since 1.0
 */
@Service
public class DictService extends BaseService {
	
	@Autowired
	private DictMapper dictMapper;

	/**
	 * 根据ID查询单条数据
	 * @param id
	 * @return
	 * @throws CRUDException
	 */
	public Dict findOne(String id) throws CRUDException{
		return dictMapper.findOne(id);
	}
	
	/**
	 * 查询数据集合
	 * @param dict
	 * @return
	 * @throws CRUDException
	 */
	public List<Dict> findList(Dict dict) throws CRUDException {
		return dictMapper.findList(dict);
	}
	
	/**
	 * 查询所有数据集合
	 * @param dict
	 * @return
	 * @throws CRUDException
	 */
	public List<Dict> findAll(Dict dict) throws CRUDException {
		return dictMapper.findAll(dict);
	}
	
	/**
	 * 查询数据总数
	 * @param dict
	 * @return
	 * @throws CRUDException
	 */
	public int findTotal(Dict dict) throws CRUDException {
		return dictMapper.findTotal(dict);
	}
	
	/**
	 * 获取生成TREE Json的
	 * @return
	 * @throws CRUDException
	 */
	public List<Dict> findTree(Dict dict) throws CRUDException {
		return dictMapper.findTree(dict);
	}
	
	/**
	 * 数据新增
	 * @param dict
	 * @throws CRUDException
	 */
	public void save(Dict dict) throws CRUDException{
		dict.preInsert();
		dictMapper.save(dict);
		CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
		CacheUtils.remove(DictUtils.CACHE_DICT_LIST);
	}
	
	/**
	 * 数据修改
	 * @param dict
	 * @throws CRUDException
	 */
	public void update(Dict dict) throws CRUDException{
		dict.preUpdate();
		dictMapper.update(dict);
		CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
		CacheUtils.remove(DictUtils.CACHE_DICT_LIST);
	}
	
	/**
	 * 数据删除
	 * @param listId
	 * @throws CRUDException
	 */
	public void delete(List<String> listId) throws CRUDException {
		dictMapper.delete(listId);
		CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
		CacheUtils.remove(DictUtils.CACHE_DICT_LIST);
	}
	
}
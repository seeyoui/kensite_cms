/*
 * Powered By cuichen
 * Since 2014 - 2016
 */package com.seeyoui.kensite.framework.cms.guestbook.service;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seeyoui.kensite.common.base.service.BaseService;

import java.util.*;

import com.seeyoui.kensite.common.base.domain.EasyUIDataGrid;
import com.seeyoui.kensite.common.base.service.BaseService;
import com.seeyoui.kensite.common.exception.CRUDException;
import com.seeyoui.kensite.common.util.*;
import com.seeyoui.kensite.common.constants.StringConstant;
import com.seeyoui.kensite.framework.cms.guestbook.domain.Guestbook;
import com.seeyoui.kensite.framework.cms.guestbook.persistence.GuestbookMapper;

/**
 * 内容发布留言板
 * @author cuichen
 * @version 2.0
 * @since 1.0
 * @date 2016-07-30
 */

@Service
public class GuestbookService extends BaseService {
	
	@Autowired
	private GuestbookMapper guestbookMapper;

	/**
	 * 根据ID查询单条数据
	 * @param id
	 * @return
	 * @throws CRUDException
	 */
	public Guestbook findOne(String id) throws CRUDException{
		return guestbookMapper.findOne(id);
	}
	
	/**
	 * 查询数据集合
	 * @param guestbook
	 * @return
	 * @throws CRUDException
	 */
	public List<Guestbook> findList(Guestbook guestbook) throws CRUDException {
		return guestbookMapper.findList(guestbook);
	}
	
	/**
	 * 查询所有数据集合
	 * @param guestbook
	 * @return
	 * @throws CRUDException
	 */
	public List<Guestbook> findAll(Guestbook guestbook) throws CRUDException {
		return guestbookMapper.findAll(guestbook);
	}
	
	/**
	 * 查询数据总数
	 * @param guestbook
	 * @return
	 * @throws CRUDException
	 */
	public int findTotal(Guestbook guestbook) throws CRUDException {
		return guestbookMapper.findTotal(guestbook);
	}
	
	/**
	 * 查询数据总数排除当前数据
	 * @param guestbook
	 * @return
	 * @throws CRUDException
	 */
	public int findExTotal(Guestbook guestbook) throws CRUDException {
		return guestbookMapper.findExTotal(guestbook);
	}
	
	/**
	 * 数据新增
	 * @param guestbook
	 * @throws CRUDException
	 */
	public void save(Guestbook guestbook) throws CRUDException{
		guestbook.preInsert();
		guestbookMapper.save(guestbook);
	}
	
	/**
	 * 数据修改
	 * @param guestbook
	 * @throws CRUDException
	 */
	public void update(Guestbook guestbook) throws CRUDException{
		guestbook.preUpdate();
		guestbookMapper.update(guestbook);			
	}
	
	/**
	 * 数据删除
	 * @param listId
	 * @throws CRUDException
	 */
	public void delete(List<String> listId) throws CRUDException {
		guestbookMapper.delete(listId);
	}
}
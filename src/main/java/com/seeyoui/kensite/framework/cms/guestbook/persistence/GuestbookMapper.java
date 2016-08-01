/*
 * Powered By cuichen
 * Since 2014 - 2016
 */
package com.seeyoui.kensite.framework.cms.guestbook.persistence;

import com.seeyoui.kensite.common.base.domain.EasyUIDataGrid;
import com.seeyoui.kensite.framework.cms.guestbook.domain.Guestbook;

import java.util.*;

import com.seeyoui.kensite.common.exception.CRUDException;

/**
 * 内容发布留言板
 * @author cuichen
 * @version 2.0
 * @since 1.0
 * @date 2016-07-30
 */

public interface GuestbookMapper {

	/**
	 * 根据ID查询单条数据
	 * @param id
	 * @return
	 */
	public Guestbook findOne(String id);
	
	/**
	 * 查询数据集合
	 * @param guestbook
	 * @return
	 */
	public List<Guestbook> findList(Guestbook guestbook);
	
	/**
	 * 查询所有数据集合
	 * @param guestbook
	 * @return
	 */
	public List<Guestbook> findAll(Guestbook guestbook);
	
	/**
	 * 查询数据总数
	 * @param guestbook
	 * @return
	 */
	public int findTotal(Guestbook guestbook);

	/**
	 * 查询数据总数排除当前数据
	 * @param guestbook
	 * @return
	 */
	public int findExTotal(Guestbook guestbook);
	
	/**
	 * 数据新增
	 * @param guestbook
	 */
	public void save(Guestbook guestbook);
	
	/**
	 * 数据修改
	 * @param guestbook
	 */
	public void update(Guestbook guestbook);
	
	/**
	 * 数据删除
	 * @param listId
	 */
	public void delete(List<String> listId);
}
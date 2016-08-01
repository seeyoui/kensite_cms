/*
 * Powered By cuichen
 * Since 2014 - 2015
 */
package com.seeyoui.kensite.framework.plugin.upload.persistence;  

import java.util.List;

import com.seeyoui.kensite.common.base.domain.EasyUIDataGrid;
import com.seeyoui.kensite.framework.plugin.upload.domain.Uploadfile;

/**
 * @author cuichen
 * @version 1.0
 * @since 1.0
 */
public interface UploadfileMapper {

	/**
	 * 根据ID查询单条数据
	 * @param id
	 * @return
	 */
	public Uploadfile findUploadfileById(String id);
	
	/**
	 * 查询数据集合
	 * @param uploadfile
	 * @return
	 */
	public List<Uploadfile> findUploadfileList(Uploadfile uploadfile);
	
	/**
	 * 查询数据总数
	 * @param userinfo
	 * @return
	 */
	public EasyUIDataGrid findUploadfileListTotal(Uploadfile uploadfile);
	
	/**
	 * 数据新增
	 * @param uploadfile
	 */
	public void saveUploadfile(Uploadfile uploadfile);
	
	/**
	 * 数据删除
	 * @param listId
	 */
	public void deleteUploadfile(List<String> listId);
}
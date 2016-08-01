/*
 * Powered By cuichen
 * Since 2014 - 2015
 */package com.seeyoui.kensite.framework.mod.tableColumn.service;  
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seeyoui.kensite.common.base.domain.EasyUIDataGrid;
import com.seeyoui.kensite.common.base.service.BaseService;
import com.seeyoui.kensite.common.exception.CRUDException;
import com.seeyoui.kensite.common.taglib.util.TagCacheUtils;
import com.seeyoui.kensite.common.util.Global;
import com.seeyoui.kensite.common.util.StringUtils;
import com.seeyoui.kensite.framework.mod.db.persistence.DBMapper;
import com.seeyoui.kensite.framework.mod.tableColumn.domain.TableColumn;
import com.seeyoui.kensite.framework.mod.tableColumn.persistence.TableColumnMapper;

/**
 * 业务表字段
 * @author cuichen
 * @version 1.0
 * @since 1.0
 * @date 2015-10-24
 */
@Service
public class TableColumnService extends BaseService {
	
	@Autowired
	private TableColumnMapper tableColumnMapper;
	@Autowired
	private DBMapper dbMapper;

	/**
	 * 查询单条数据
	 * @param tableColumn
	 * @return
	 * @throws CRUDException
	 */
	public TableColumn findOne(TableColumn tableColumn) throws CRUDException{
		return tableColumnMapper.findOne(tableColumn);
	}
	
	/**
	 * 查询数据集合
	 * @param tableColumn
	 * @return
	 * @throws CRUDException
	 */
	public List<TableColumn> findList(TableColumn tableColumn) throws CRUDException {
		return tableColumnMapper.findList(tableColumn);
	}
	
	/**
	 * 查询所有数据集合
	 * @param tableColumn
	 * @return
	 * @throws CRUDException
	 */
	public List<TableColumn> findAll(TableColumn tableColumn) throws CRUDException {
		return tableColumnMapper.findAll(tableColumn);
	}
	
	/**
	 * 查询数据总数
	 * @param tableColumn
	 * @return
	 * @throws CRUDException
	 */
	public int findTotal(TableColumn tableColumn) throws CRUDException {
		return tableColumnMapper.findTotal(tableColumn);
	}
	
	/**
	 * 数据新增
	 * @param tableColumn
	 * @throws CRUDException
	 */
	public void save(TableColumn tableColumn) throws CRUDException{
		tableColumn.preInsert();
		tableColumnMapper.save(tableColumn);
		String modifyStr = "";
		if(StringUtils.isNoneBlank(tableColumn.getJdbcLength())) {
			modifyStr += tableColumn.getJdbcType()+"("+tableColumn.getJdbcLength()+")";
		} else {
			modifyStr += tableColumn.getJdbcType();
		}
//		if(tableColumn.getJdbcType().equals("NUMBER")) {
//			if(StringUtils.isNoneBlank(tableColumn.getDefaultValue())) {
//				modifyStr += " default "+tableColumn.getDefaultValue();
//			} else {
//				modifyStr += " default 0";
//			}
//		} else {
//			if(StringUtils.isNoneBlank(tableColumn.getDefaultValue())) {
//				modifyStr += " default '"+tableColumn.getDefaultValue()+"'";
//			}
//		}
		if(tableColumn.getIsNull()!=null) {
			if("Y".equals(tableColumn.getIsNull())) {
				modifyStr += " null";
			}
			if("N".equals(tableColumn.getIsNull())) {
				modifyStr += " not null";
			}
		}
		tableColumn.setModifyStr(modifyStr);
		dbMapper.addColumn(tableColumn);
		dbMapper.commentColumn(tableColumn);
	}
	
	/**
	 * 数据修改
	 * @param tableColumn
	 * @throws CRUDException
	 */
	public void update(TableColumn tableColumn) throws CRUDException{
		TableColumn tableColumnOld = tableColumnMapper.findById(tableColumn.getId());
		tableColumn.preUpdate();
		TagCacheUtils.removeCache(tableColumn);
		tableColumnMapper.update(tableColumn);

		String dbType = Global.getConfig("jdbc.type");
		if("oracle".equals(dbType)) {
			if(StringUtils.isNotBlank(tableColumn.getName()) && !tableColumn.getName().equals(tableColumnOld.getName())) {
				tableColumn.setOldName(tableColumnOld.getName());
				dbMapper.renameColumn(tableColumn);
			}
			if(StringUtils.isNotBlank(tableColumn.getComments()) && !tableColumn.getComments().equals(tableColumnOld.getComments())) {
				dbMapper.commentColumn(tableColumn);
			}
			String modifyStr = "";
			if(StringUtils.isNotBlank(tableColumn.getJdbcType()) && !tableColumn.getJdbcType().equals(tableColumnOld.getJdbcType())) {
				if(StringUtils.isNoneBlank(tableColumn.getJdbcLength())) {
					modifyStr += tableColumn.getJdbcType();
					modifyStr += "("+tableColumn.getJdbcLength()+")";
				} else {
					modifyStr += tableColumn.getJdbcType();
				}
			}
			/*if(StringUtils.isNotBlank(tableColumn.getDefaultValue()) && !tableColumn.getDefaultValue().equals(tableColumnOld.getDefaultValue())) {
				if(tableColumn.getJdbcType().equals("NUMBER")) {
					modifyStr += " default "+tableColumn.getDefaultValue();
				} else {
					modifyStr += " default '"+tableColumn.getDefaultValue()+"'";
				}
			}*/
			if(StringUtils.isNotBlank(tableColumn.getIsNull()) && !tableColumn.getIsNull().equals(tableColumnOld.getIsNull())) {
				if("Y".equals(tableColumn.getIsNull())) {
					modifyStr += " null";
				}
				if("N".equals(tableColumn.getIsNull())) {
					modifyStr += " not null";
				}
			}
			if(StringUtils.isNotBlank(modifyStr)) {
				tableColumn.setModifyStr(modifyStr);
				dbMapper.modifyColumn(tableColumn);
			}
		} else if("mysql".equals(dbType)) {
			if(StringUtils.isNotBlank(tableColumn.getName()) && !tableColumn.getName().equals(tableColumnOld.getName())) {
				tableColumn.setOldName(tableColumnOld.getName());
				dbMapper.renameColumn(tableColumn);
			}
			String modifyStr = "";
			if(StringUtils.isNoneBlank(tableColumn.getJdbcLength())) {
				modifyStr += tableColumn.getJdbcType();
				modifyStr += "("+tableColumn.getJdbcLength()+")";
			} else {
				modifyStr += tableColumn.getJdbcType();
			}
			/*if(StringUtils.isNotBlank(tableColumn.getDefaultValue()) && !tableColumn.getDefaultValue().equals(tableColumnOld.getDefaultValue())) {
				if(tableColumn.getJdbcType().equals("NUMBER")) {
					modifyStr += " default "+tableColumn.getDefaultValue();
				} else {
					modifyStr += " default '"+tableColumn.getDefaultValue()+"'";
				}
			}*/
			if(StringUtils.isNotBlank(tableColumn.getIsNull()) && !tableColumn.getIsNull().equals(tableColumnOld.getIsNull())) {
				if("Y".equals(tableColumn.getIsNull())) {
					modifyStr += " null";
				}
				if("N".equals(tableColumn.getIsNull())) {
					modifyStr += " not null";
				}
			}
			if(StringUtils.isNotBlank(tableColumn.getComments()) && !tableColumn.getComments().equals(tableColumnOld.getComments())) {
				modifyStr += " comment '"+tableColumn.getComments()+"'";
			}
			if(StringUtils.isNotBlank(modifyStr)) {
				tableColumn.setModifyStr(modifyStr);
				dbMapper.modifyColumn(tableColumn);
			}
		}
	}
	
	/**
	 * 数据修改
	 * @param tableColumn
	 * @throws CRUDException
	 */
	public void changeState(TableColumn tableColumn) throws CRUDException{
		tableColumn.preUpdate();
		TagCacheUtils.removeCache(tableColumn);
		tableColumnMapper.update(tableColumn);
	}
	
	/**
	 * 数据修改
	 * @param tableColumn
	 * @throws CRUDException
	 */
	public void rename(TableColumn tableColumn) throws CRUDException{
		tableColumn.preUpdate();
		TagCacheUtils.removeCache(tableColumn);
		tableColumnMapper.update(tableColumn);
	}
	
	/**
	 * 数据删除
	 * @param listId
	 * @throws CRUDException
	 */
	public void delete(List<String> listId) throws CRUDException {
		for(String id : listId) {
			TableColumn tableColumn = tableColumnMapper.findById(id);
			dbMapper.dropColumn(tableColumn);
			TagCacheUtils.removeCache(tableColumn);
		}
		tableColumnMapper.delete(listId);
	}
	
}
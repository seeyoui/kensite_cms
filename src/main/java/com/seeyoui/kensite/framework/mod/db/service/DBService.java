/*
 * Powered By cuichen
 * Since 2014 - 2015
 */package com.seeyoui.kensite.framework.mod.db.service;  
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seeyoui.kensite.common.base.domain.EasyUIDataGrid;
import com.seeyoui.kensite.common.base.service.BaseService;
import com.seeyoui.kensite.common.exception.CRUDException;
import com.seeyoui.kensite.framework.mod.db.persistence.DBMapper;
import com.seeyoui.kensite.framework.mod.table.domain.Table;
import com.seeyoui.kensite.framework.mod.tableColumn.domain.TableColumn;

/**
 * @author cuichen
 * @version 1.0
 * @since 1.0
 * @date 2015-09-22
 */
@Service
public class DBService extends BaseService {
	
	@Autowired
	private DBMapper dbMapper;

	/**
	 * 创建表
	 * @param table
	 * @throws CRUDException
	 */
	public void createTable(Table table) throws CRUDException{
		dbMapper.createTable(table);
		TableColumn column = new TableColumn();
		column.setTableName(table.getName());
		column.setName("ID");
		column.setComments("主键");
		dbMapper.commentColumn(column);
		dbMapper.addPrimaryKey(column);

		column.setName("CREATE_DATE");
		column.setComments("创建日期");
		dbMapper.commentColumn(column);

		column.setName("CREATE_USER");
		column.setComments("创建用户");
		dbMapper.commentColumn(column);

		column.setName("UPDATE_DATE");
		column.setComments("修改日期");
		dbMapper.commentColumn(column);

		column.setName("UPDATE_USER");
		column.setComments("修改用户");
		dbMapper.commentColumn(column);

		column.setName("REMARKS");
		column.setComments("备注信息");
		dbMapper.commentColumn(column);

		column.setName("DEL_FLAG");
		column.setComments("删除标记");
		dbMapper.commentColumn(column);
	}
	
	/**
	 * 表重命名
	 * @param table
	 * @throws CRUDException
	 */
	public void renameTable(Table table) throws CRUDException{
		dbMapper.renameTable(table);
	}
	
	/**
	 * 给表添加注释
	 * @param table
	 * @throws CRUDException
	 */
	public void commentTable(Table table) throws CRUDException{
		dbMapper.commentTable(table);
	}
	
	/**
	 * 删除表
	 * @param table
	 * @throws CRUDException
	 */
	public void dropTable(Table table) throws CRUDException{
		dbMapper.dropTable(table);
	}
	
	/**
	 * 增加字段
	 * @param tableColumn
	 * @throws CRUDException
	 */
	public void addColumn(TableColumn tableColumn) throws CRUDException{
		dbMapper.addColumn(tableColumn);
	}

	/**
	 * 修改字段
	 * @param tableColumn
	 * @throws CRUDException
	 */
	public void modifyColumn(TableColumn tableColumn) throws CRUDException{
		dbMapper.modifyColumn(tableColumn);
	}

	/**
	 * 删除字段
	 * @param tableColumn
	 * @throws CRUDException
	 */
	public void dropColumn(TableColumn tableColumn) throws CRUDException{
		dbMapper.dropColumn(tableColumn);
	}

	/**
	 * 给表添加注释
	 * @param tableColumn
	 * @throws CRUDException
	 */
	public void commentColumn(TableColumn tableColumn) throws CRUDException{
		dbMapper.commentColumn(tableColumn);
	}

	/**
	 * 给表添加主键
	 * @param tableColumn
	 * @throws CRUDException
	 */
	public void addPrimaryKey(TableColumn tableColumn) throws CRUDException{
		dbMapper.addPrimaryKey(tableColumn);
	}

	/**
	 * 给表删除主键
	 * @param tableColumn
	 * @throws CRUDException
	 */
	public void dropPrimaryKey(TableColumn tableColumn) throws CRUDException{
		dbMapper.dropPrimaryKey(tableColumn);
	}
}
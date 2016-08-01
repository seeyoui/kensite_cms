/*
 * Powered By cuichen
 * Since 2014 - 2015
 */
package com.seeyoui.kensite.framework.mod.tableColumn.domain;  

import org.springframework.web.util.HtmlUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.seeyoui.kensite.common.base.domain.DataEntity;

/**
 * 业务表字段
 * @author cuichen
 * @version 1.0
 * @since 1.0
 * @date 2015-10-24
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TableColumn extends DataEntity<TableColumn> {
	private static final long serialVersionUID = 1L;
    
	private String tableName;//业务表
	private String name;//列名
	private String comments;//注释
	private String jdbcType;//数据库类型及长度
	private String jdbcLength;//是否主键
	private String isNull;//是否可为空
	private String isEdit;//是否编辑字段
	private String isList;//是否列表字段
	private String isQuery;//是否查询字段
	private String isSort;//是否列表排序
	private String listWidth;//列表显示宽度
	private String queryWidth;//查询框宽度
	private String category;//字段生成方案
	private String defaultValue;//默认值
	private String validType;//校验类型
	private String settings;//扩展设置
	private String htmlInner;//扩展HTML代码
	private String modifyStr;//修改字符串
	private String oldName;//修改之前的名字
	private String oldTableName;//修改之前的表名字

	public void setTableName(String tableName) {
		this.tableName = tableName.toUpperCase();
	}
    
	public String getTableName() {
		return this.tableName;
	}
	public void setName(String name) {
		this.name = name.toUpperCase();
	}
    
	public String getName() {
		return this.name;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
    
	public String getComments() {
		return this.comments;
	}
	public void setJdbcType(String jdbcType) {
		this.jdbcType = jdbcType;
	}
    
	public String getJdbcType() {
		return this.jdbcType;
	}
	
	public String getJdbcLength() {
		return jdbcLength;
	}

	public void setJdbcLength(String jdbcLength) {
		this.jdbcLength = jdbcLength;
	}

	public void setIsNull(String isNull) {
		this.isNull = isNull;
	}
    
	public String getIsNull() {
		return this.isNull;
	}
	public void setIsEdit(String isEdit) {
		this.isEdit = isEdit;
	}
    
	public String getIsEdit() {
		return this.isEdit;
	}
	public String getIsList() {
		return isList;
	}

	public void setIsList(String isList) {
		this.isList = isList;
	}

	public String getIsQuery() {
		return isQuery;
	}

	public void setIsQuery(String isQuery) {
		this.isQuery = isQuery;
	}

	public String getIsSort() {
		return isSort;
	}

	public void setIsSort(String isSort) {
		this.isSort = isSort;
	}

	public String getListWidth() {
		return listWidth;
	}

	public void setListWidth(String listWidth) {
		this.listWidth = listWidth;
	}

	public String getQueryWidth() {
		return queryWidth;
	}

	public void setQueryWidth(String queryWidth) {
		this.queryWidth = queryWidth;
	}

	public void setCategory(String category) {
		this.category = category;
	}
    
	public String getCategory() {
		return this.category;
	}
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
    
	public String getDefaultValue() {
		return this.defaultValue;
	}
	public void setValidType(String validType) {
		this.validType = validType;
	}
    
	public String getValidType() {
		return this.validType;
	}
	public void setSettings(String settings) {
		this.settings = settings;
	}
    
	public String getSettings() {
		return HtmlUtils.htmlUnescape(this.settings);
	}

	public String getHtmlInner() {
		return HtmlUtils.htmlUnescape(htmlInner);
	}

	public void setHtmlInner(String htmlInner) {
		this.htmlInner = htmlInner;
	}

	public String getModifyStr() {
		return modifyStr;
	}

	public void setModifyStr(String modifyStr) {
		this.modifyStr = modifyStr;
	}

	public String getOldName() {
		return oldName;
	}

	public void setOldName(String oldName) {
		this.oldName = oldName;
	}

	public String getOldTableName() {
		return oldTableName;
	}

	public void setOldTableName(String oldTableName) {
		this.oldTableName = oldTableName;
	}

}
/*
 * Powered By cuichen
 * Since 2014 - 2015
 */
package com.seeyoui.kensite.bussiness.oauth.interfaceCatalog.domain;  

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.seeyoui.kensite.common.base.domain.DataEntity;
import com.seeyoui.kensite.common.util.excel.annotation.ExcelField;

/**
 * 接口目录
 * @author cuichen
 * @version 1.0
 * @since 1.0
 * @date 2015-12-09
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class InterfaceCatalog extends DataEntity<InterfaceCatalog> {
	private static final long serialVersionUID = 1L;
    
    @ExcelField(title="名称", type=1, align=2, sort=6, mod="BO_INTERFACE_CATALOG|NAME")
    private String name;//名称
    @ExcelField(title="模块", type=1, align=2, sort=7, mod="BO_INTERFACE_CATALOG|PARENT_ID")
    private String parentId;//模块
    @ExcelField(title="排序", type=1, align=2, sort=8, mod="BO_INTERFACE_CATALOG|SEQUENCE")
    private String sequence;//排序

	public void setName(String name) {
		this.name = name;
	}
    
	public String getName() {
		return this.name;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
    
	public String getParentId() {
		return this.parentId;
	}

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
}
/*
 * Powered By cuichen
 * Since 2014 - 2015
 */
package com.seeyoui.kensite.bussiness.oauth.interfaceDesc.domain;  

import org.springframework.web.util.HtmlUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.seeyoui.kensite.common.base.domain.DataEntity;
import com.seeyoui.kensite.common.util.excel.annotation.ExcelField;

/**
 * 接口描述
 * @author cuichen
 * @version 1.0
 * @since 1.0
 * @date 2015-12-09
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class InterfaceDesc extends DataEntity<InterfaceDesc> {
	private static final long serialVersionUID = 1L;
    
    @ExcelField(title="名称", type=1, align=2, sort=6, mod="BO_INTERFACE_DESC|NAME")
    private String name;//名称
    @ExcelField(title="接口地址", type=1, align=2, sort=7, mod="BO_INTERFACE_DESC|URL")
    private String url;//接口地址
    @ExcelField(title="提交方式", type=1, align=2, sort=8, mod="BO_INTERFACE_DESC|METHOD")
    private String method;//提交方式
    @ExcelField(title="参数", type=1, align=2, sort=9, mod="BO_INTERFACE_DESC|PARAMETER")
    private String parameter;//参数
    @ExcelField(title="返回值", type=1, align=2, sort=10, mod="BO_INTERFACE_DESC|RETURN_VALUE")
    private String returnValue;//返回值
    @ExcelField(title="不返回字段", type=1, align=2, sort=11, mod="BO_INTERFACE_DESC|EXCLUDES")
    private String excludes;//不返回字段
    @ExcelField(title="接口目录", type=1, align=2, sort=12, mod="BO_INTERFACE_DESC|CATALOG_ID")
    private String catalogId;//接口目录
    @ExcelField(title="排序", type=1, align=2, sort=13, mod="BO_INTERFACE_DESC|SEQUENCE")
    private String sequence;//排序
    @ExcelField(title="类型", type=1, align=2, sort=14, mod="BO_INTERFACE_DESC|SEQUENCE")
    private String type;//类型

	public void setName(String name) {
		this.name = name;
	}
    
	public String getName() {
		return this.name;
	}
	public void setUrl(String url) {
		this.url = url;
	}
    
	public String getUrl() {
		return this.url;
	}
	public void setMethod(String method) {
		this.method = method;
	}
    
	public String getMethod() {
		return this.method;
	}
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}
    
	public String getParameter() {
		return HtmlUtils.htmlUnescape(this.parameter);
	}
	public void setReturnValue(String returnValue) {
		this.returnValue = returnValue;
	}
    
	public String getReturnValue() {
		return HtmlUtils.htmlUnescape(this.returnValue);
	}
	public void setExcludes(String excludes) {
		this.excludes = excludes;
	}
    
	public String getExcludes() {
		return HtmlUtils.htmlUnescape(this.excludes);
	}

	public String getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
	}

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
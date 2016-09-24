/*
 * Powered By cuichen
 * Since 2014 - 2016
 */
package com.seeyoui.kensite.framework.cms.tagDesc.domain;

import org.springframework.web.util.HtmlUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.seeyoui.kensite.common.base.domain.DataEntity;
import com.seeyoui.kensite.common.util.excel.annotation.ExcelField;

/**
 * 标签描述
 * @author cuichen
 * @version 2.0
 * @since 1.0
 * @date 2016-09-24
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class TagDesc extends DataEntity<TagDesc> {
	private static final long serialVersionUID = 1L;

	@ExcelField(title="名称", type=1, align=2, sort=7, mod="CMS_TAG_DESC|NAME")
	private String name;//名称
	@ExcelField(title="标签名称", type=1, align=2, sort=8, mod="CMS_TAG_DESC|TAG_NAME")
	private String tagName;//标签名称
	@ExcelField(title="分类", type=1, align=2, sort=9, mod="CMS_TAG_DESC|CATEGORY")
	private String category;//分类
	@ExcelField(title="描述", type=1, align=2, sort=10, mod="CMS_TAG_DESC|DESCRIBE")
	private String describe;//描述
	@ExcelField(title="属性", type=1, align=2, sort=11, mod="CMS_TAG_DESC|ATTRIBUTE")
	private String attribute;//属性

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getTagName() {
//		return this.tagName;
		return HtmlUtils.htmlUnescape(this.tagName);
	}
	public void setCategory(String category) {
		this.category = category;
	}

	public String getCategory() {
		return this.category;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getDescribe() {
//		return this.describe;
		return HtmlUtils.htmlUnescape(this.describe);
	}
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public String getAttribute() {
//		return this.attribute;
		return HtmlUtils.htmlUnescape(this.attribute);
	}
}
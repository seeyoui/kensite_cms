/*
 * Powered By cuichen
 * Since 2014 - 2016
 */
package com.seeyoui.kensite.framework.cms.collections.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.seeyoui.kensite.common.base.domain.DataEntity;
import com.seeyoui.kensite.common.util.excel.annotation.ExcelField;

/**
 * 内容发布收藏
 * @author cuichen
 * @version 2.0
 * @since 1.0
 * @date 2016-07-15
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Collections extends DataEntity<Collections> {
	private static final long serialVersionUID = 1L;

	@ExcelField(title="所属站点", type=1, align=2, sort=7, mod="CMS_COLLECTIONS|SITE_ID")
	private String siteId;//所属站点
	@ExcelField(title="所属栏目", type=1, align=2, sort=8, mod="CMS_COLLECTIONS|CATEGORY_ID")
	private String categoryId;//所属栏目
	@ExcelField(title="评论主体", type=1, align=2, sort=9, mod="CMS_COLLECTIONS|BASIC_ID")
	private String basicId;//评论主体
	@ExcelField(title="类型", type=1, align=2, sort=10, mod="CMS_COLLECTIONS|TYPE")
	private String type;//类型
	@ExcelField(title="分类", type=1, align=2, sort=11, mod="CMS_COLLECTIONS|CATEGORY")
	private String category;//分类

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public String getSiteId() {
		return this.siteId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryId() {
		return this.categoryId;
	}
	public void setBasicId(String basicId) {
		this.basicId = basicId;
	}

	public String getBasicId() {
		return this.basicId;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return this.type;
	}
	public void setCategory(String category) {
		this.category = category;
	}

	public String getCategory() {
		return this.category;
	}
}
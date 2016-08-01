/*
 * Powered By cuichen
 * Since 2014 - 2016
 */
package com.seeyoui.kensite.framework.cms.tagcloud.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.seeyoui.kensite.common.base.domain.DataEntity;
import com.seeyoui.kensite.common.util.excel.annotation.ExcelField;

/**
 * 标签云
 * @author cuichen
 * @version 2.0
 * @since 1.0
 * @date 2016-07-27
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Tagcloud extends DataEntity<Tagcloud> {
	private static final long serialVersionUID = 1L;

	@ExcelField(title="站点", type=1, align=2, sort=7, mod="CMS_TAGCLOUD|SITE_ID")
	private String siteId;//站点
	@ExcelField(title="标签", type=1, align=2, sort=8, mod="CMS_TAGCLOUD|NAME")
	private String name;//标签
	
	private int articleCount;//该标签下文章数

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public String getSiteId() {
		return this.siteId;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public int getArticleCount() {
		return articleCount;
	}

	public void setArticleCount(int articleCount) {
		this.articleCount = articleCount;
	}
}
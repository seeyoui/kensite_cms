/*
 * Powered By cuichen
 * Since 2014 - 2016
 */
package com.seeyoui.kensite.framework.cms.site.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.seeyoui.kensite.common.base.domain.DataEntity;
import com.seeyoui.kensite.common.util.excel.annotation.ExcelField;

/**
 * 内容发布站点
 * @author cuichen
 * @version 2.0
 * @since 1.0
 * @date 2016-07-15
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Site extends DataEntity<Site> {
	private static final long serialVersionUID = 1L;

	@ExcelField(title="站点名称", type=1, align=2, sort=7, mod="CMS_SITE|NAME")
	private String name;//站点名称
	@ExcelField(title="站点标题", type=1, align=2, sort=8, mod="CMS_SITE|TITLE")
	private String title;//站点标题
	@ExcelField(title="站点LOGO", type=1, align=2, sort=9, mod="CMS_SITE|LOGO")
	private String logo;//站点LOGO
	@ExcelField(title="站点域名", type=1, align=2, sort=10, mod="CMS_SITE|DOMAIN")
	private String domain;//站点域名
	@ExcelField(title="描述", type=1, align=2, sort=11, mod="CMS_SITE|DESCRIPTION")
	private String description;//描述
	@ExcelField(title="关键字", type=1, align=2, sort=12, mod="CMS_SITE|KEYWORDS")
	private String keywords;//关键字
	@ExcelField(title="主题", type=1, align=2, sort=13, mod="CMS_SITE|THEME")
	private String theme;//主题
	@ExcelField(title="版权信息", type=1, align=2, sort=14, mod="CMS_SITE|COPYRIGHT")
	private String copyright;//版权信息
	@ExcelField(title="自定义首页视图", type=1, align=2, sort=15, mod="CMS_SITE|CUSTOM_VIEW")
	private String customView;//自定义首页视图

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return this.title;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getLogo() {
		return this.logo;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getDomain() {
		return this.domain;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getKeywords() {
		return this.keywords;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getTheme() {
		return this.theme;
	}
	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}

	public String getCopyright() {
		return this.copyright;
	}
	public void setCustomView(String customView) {
		this.customView = customView;
	}

	public String getCustomView() {
		return this.customView;
	}
}
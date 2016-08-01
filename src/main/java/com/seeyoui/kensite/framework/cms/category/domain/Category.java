/*
 * Powered By cuichen
 * Since 2014 - 2016
 */
package com.seeyoui.kensite.framework.cms.category.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.seeyoui.kensite.common.base.domain.DataEntity;
import com.seeyoui.kensite.common.util.excel.annotation.ExcelField;

/**
 * 内容发布栏目
 * @author cuichen
 * @version 2.0
 * @since 1.0
 * @date 2016-07-15
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Category extends DataEntity<Category> {
	private static final long serialVersionUID = 1L;

	@ExcelField(title="站点", type=1, align=2, sort=7, mod="CMS_CATEGORY|SITE_ID")
	private String siteId;//站点
	@ExcelField(title="栏目模块", type=1, align=2, sort=8, mod="CMS_CATEGORY|MODULE")
	private String module;//栏目模块
	@ExcelField(title="栏目名称", type=1, align=2, sort=9, mod="CMS_CATEGORY|NAME")
	private String name;//栏目名称
	@ExcelField(title="父级栏目", type=1, align=2, sort=10, mod="CMS_CATEGORY|PARENT_ID")
	private String parentId;//父级栏目
	@ExcelField(title="链接", type=1, align=2, sort=11, mod="CMS_CATEGORY|HREF")
	private String href;//链接
	@ExcelField(title="目标", type=1, align=2, sort=12, mod="CMS_CATEGORY|TARGET")
	private String target;//目标
	@ExcelField(title="描述", type=1, align=2, sort=13, mod="CMS_CATEGORY|DESCRIPTION")
	private String description;//描述
	@ExcelField(title="关键字", type=1, align=2, sort=14, mod="CMS_CATEGORY|KEYWORDS")
	private String keywords;//关键字
	@ExcelField(title="权重", type=1, align=2, sort=15, mod="CMS_CATEGORY|SEQ")
	private String seq;//权重
	@ExcelField(title="是否导航显示", type=1, align=2, sort=16, mod="CMS_CATEGORY|IN_MENU")
	private String inMenu;//是否导航显示
	@ExcelField(title="是否分类列表", type=1, align=2, sort=17, mod="CMS_CATEGORY|IN_LIST")
	private String inList;//是否分类列表
	@ExcelField(title="是否允许评论", type=1, align=2, sort=18, mod="CMS_CATEGORY|IS_COMMENT")
	private String isComment;//是否允许评论
	@ExcelField(title="是否需要审核", type=1, align=2, sort=19, mod="CMS_CATEGORY|IS_AUDIT")
	private String isAudit;//是否需要审核
	@ExcelField(title="自定义列表视图", type=1, align=2, sort=20, mod="CMS_CATEGORY|CUSTOM_LIST_VIEW")
	private String customListView;//自定义列表视图
	@ExcelField(title="自定义内容视图", type=1, align=2, sort=21, mod="CMS_CATEGORY|CUSTOM_CONTENT_VIEW")
	private String customContentView;//自定义内容视图
	@ExcelField(title="视图配置", type=1, align=2, sort=22, mod="CMS_CATEGORY|VIEW_CONFIG")
	private String viewConfig;//视图配置
	@ExcelField(title="扩展配置", type=1, align=2, sort=23, mod="CMS_CATEGORY|EXTENDS_INFO")
	private String extendsInfo;//扩展配置

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public String getSiteId() {
		return this.siteId;
	}
	public void setModule(String module) {
		this.module = module;
	}

	public String getModule() {
		return this.module;
	}
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
	public void setHref(String href) {
		this.href = href;
	}

	public String getHref() {
		return this.href;
	}
	public void setTarget(String target) {
		this.target = target;
	}

	public String getTarget() {
		return this.target;
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
	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getSeq() {
		return this.seq;
	}
	public void setInMenu(String inMenu) {
		this.inMenu = inMenu;
	}

	public String getInMenu() {
		return this.inMenu;
	}
	public void setInList(String inList) {
		this.inList = inList;
	}

	public String getInList() {
		return this.inList;
	}
	public void setIsComment(String isComment) {
		this.isComment = isComment;
	}

	public String getIsComment() {
		return this.isComment;
	}
	public void setIsAudit(String isAudit) {
		this.isAudit = isAudit;
	}

	public String getIsAudit() {
		return this.isAudit;
	}
	public void setCustomListView(String customListView) {
		this.customListView = customListView;
	}

	public String getCustomListView() {
		return this.customListView;
	}
	public void setCustomContentView(String customContentView) {
		this.customContentView = customContentView;
	}

	public String getCustomContentView() {
		return this.customContentView;
	}
	public void setViewConfig(String viewConfig) {
		this.viewConfig = viewConfig;
	}

	public String getViewConfig() {
		return this.viewConfig;
	}

	public String getExtendsInfo() {
		return extendsInfo;
	}

	public void setExtendsInfo(String extendsInfo) {
		this.extendsInfo = extendsInfo;
	}
}
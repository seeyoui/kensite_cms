/*
 * Powered By cuichen
 * Since 2014 - 2016
 */
package com.seeyoui.kensite.framework.cms.article.domain;

import org.springframework.web.util.HtmlUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.seeyoui.kensite.common.base.domain.DataEntity;
import com.seeyoui.kensite.common.util.excel.annotation.ExcelField;
import com.seeyoui.kensite.framework.system.domain.SysUser;

/**
 * 内容发布文章
 * @author cuichen
 * @version 2.0
 * @since 1.0
 * @date 2016-07-15
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Article extends DataEntity<Article> {
	private static final long serialVersionUID = 1L;

	@ExcelField(title="所属站点", type=1, align=2, sort=7, mod="CMS_ARTICLE|SITE_ID")
	private String siteId;//所属站点
	@ExcelField(title="所属栏目", type=1, align=2, sort=8, mod="CMS_ARTICLE|CATEGORY_ID")
	private String categoryId;//所属栏目
	@ExcelField(title="标题", type=1, align=2, sort=9, mod="CMS_ARTICLE|TITLE")
	private String title;//标题
	@ExcelField(title="副标题", type=1, align=2, sort=10, mod="CMS_ARTICLE|SUB_TITLE")
	private String subTitle;//副标题
	@ExcelField(title="关键字", type=1, align=2, sort=11, mod="CMS_ARTICLE|KEYWORDS")
	private String keywords;//关键字
	@ExcelField(title="描述", type=1, align=2, sort=12, mod="CMS_ARTICLE|DESCRIPTION")
	private String description;//描述
	@ExcelField(title="权重", type=1, align=2, sort=13, mod="CMS_ARTICLE|SEQ")
	private String seq;//权重
	@ExcelField(title="点击数", type=1, align=2, sort=14, mod="CMS_ARTICLE|HITS")
	private String hits;//点击数
	@ExcelField(title="文章内容", type=1, align=2, sort=15, mod="CMS_ARTICLE|CONTENT")
	private String content;//文章内容
	@ExcelField(title="文章来源", type=1, align=2, sort=16, mod="CMS_ARTICLE|COPYFROM")
	private String copyfrom;//文章来源
	@ExcelField(title="自定义内容视图", type=1, align=2, sort=17, mod="CMS_ARTICLE|CUSTOM_CONTENT_VIEW")
	private String customContentView;//自定义内容视图
	@ExcelField(title="视图配置", type=1, align=2, sort=18, mod="CMS_ARTICLE|VIEW_CONFIG")
	private String viewConfig;//视图配置
	@ExcelField(title="海报", type=1, align=2, sort=19, mod="CMS_ARTICLE|POSTER")
	private String poster;//海报
	@ExcelField(title="标签", type=1, align=2, sort=20, mod="CMS_ARTICLE|TAG_ID")
	private String tagId;//标签
	
	/*****************************************/
	private int praiseNum;//点赞数
	private int collectNum;//收藏数
	private int commentNum;//评价数
	private double commentScore;//评价等级
	private SysUser author;//作者
	private String tag;//标签
	/*****************************************/

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryId() {
		return this.categoryId;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return this.title;
	}
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public String getSubTitle() {
		return this.subTitle;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getKeywords() {
		return this.keywords;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getSeq() {
		return this.seq;
	}
	public void setHits(String hits) {
		this.hits = hits;
	}

	public String getHits() {
		return this.hits;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
//		return this.content;
		return HtmlUtils.htmlUnescape(this.content);
	}
	public void setCopyfrom(String copyfrom) {
		this.copyfrom = copyfrom;
	}

	public String getCopyfrom() {
		return this.copyfrom;
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

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}
	
	public String getTagId() {
		return tagId;
	}

	public void setTagId(String tagId) {
		this.tagId = tagId;
	}

	/*****************************************/

	public int getPraiseNum() {
		return praiseNum;
	}

	public void setPraiseNum(int praiseNum) {
		this.praiseNum = praiseNum;
	}

	public int getCollectNum() {
		return collectNum;
	}

	public void setCollectNum(int collectNum) {
		this.collectNum = collectNum;
	}

	public int getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}

	public double getCommentScore() {
		return commentScore;
	}

	public void setCommentScore(double commentScore) {
		this.commentScore = commentScore;
	}

	public SysUser getAuthor() {
		return author;
	}

	public void setAuthor(SysUser author) {
		this.author = author;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	/*****************************************/
}
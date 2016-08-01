package com.seeyoui.kensite.framework.cms.taglib;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.seeyoui.kensite.common.base.domain.Page;
import com.seeyoui.kensite.common.constants.StringConstant;
import com.seeyoui.kensite.common.util.SpringContextHolder;
import com.seeyoui.kensite.common.util.StringUtils;
import com.seeyoui.kensite.framework.cms.article.domain.Article;
import com.seeyoui.kensite.framework.cms.article.persistence.ArticleMapper;
import com.seeyoui.kensite.framework.cms.comment.domain.Comment;
import com.seeyoui.kensite.framework.cms.site.domain.Site;
import com.seeyoui.kensite.framework.cms.site.persistence.SiteMapper;

public class ArticleTag extends SimpleTagSupport  {
	
	//站点
	private static SiteMapper siteMapper = SpringContextHolder.getBean(SiteMapper.class);
	//文章
	private static ArticleMapper articleMapper = SpringContextHolder.getBean(ArticleMapper.class);
	private String var;
	private String id;
	private String siteId;
	private String site;
	private String categoryId;
	private String tagId;
	private String title;
	private String subTitle;
	private String keywords;
	private String description;
	private String isPage;
	private int page;
	private int rows;
	private String sort;
	private String order;
	
	@Override
	public void  doTag() throws JspException, IOException {
		Article article = new Article();
		if(StringUtils.isNoneBlank(id)) {
			articleMapper.hits(id);
			article = articleMapper.findOne(id);
			getJspContext().setAttribute(var, article);
	        //输出标签体
	        getJspBody().invoke(null);
			return;
		}
		if(StringUtils.isNoneBlank(siteId)) {
			article.setSiteId(siteId);
		} else if(StringUtils.isNotBlank(site)) {
			Site s = siteMapper.findOne(site);
			article.setSiteId(s.getId());
		}
		article.setCategoryId(categoryId);
		article.setTagId(tagId);
		article.setTitle(title);
		article.setSubTitle(subTitle);
		article.setKeywords(keywords);
		article.setDescription(description);
		article.setSort(sort);
		article.setOrder(order);
		List<Article> articleList = new ArrayList<Article>();
		if(!StringConstant.YES.equals(isPage)) {
			articleList = articleMapper.findAll(article);
		} else {
			article.setPage(page);
			article.setRows(rows);
			int total = articleMapper.findTotal(article);
			getJspContext().setAttribute("articlePage", new Page<Comment>(page, rows, total));
			articleList = articleMapper.findList(article);
		}
		for(Article art : articleList) {
			getJspContext().setAttribute(var, art);
	        //输出标签体
	        getJspBody().invoke(null);
		}
	}

	public void setVar(String var) {
		this.var = var;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public void setTagId(String tagId) {
		this.tagId = tagId;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setIsPage(String isPage) {
		this.isPage = isPage;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public void setOrder(String order) {
		this.order = order;
	}
	
}
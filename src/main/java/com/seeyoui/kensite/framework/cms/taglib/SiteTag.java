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
import com.seeyoui.kensite.framework.cms.comment.domain.Comment;
import com.seeyoui.kensite.framework.cms.site.domain.Site;
import com.seeyoui.kensite.framework.cms.site.persistence.SiteMapper;

public class SiteTag extends SimpleTagSupport  {
	//站点
	private static SiteMapper siteMapper = SpringContextHolder.getBean(SiteMapper.class);
	private String var;
	private String id;
	private String site;
	private String name;
	private String title;
	private String keywords;
	private String description;
	private String isPage;
	private int page;
	private int rows;
	private String sort;
	private String order;
	
	@Override
	public void  doTag() throws JspException, IOException {
		Site s = new Site();
		if(StringUtils.isNoneBlank(id)) {
			s = siteMapper.findOne(id);
			getJspContext().setAttribute(var, s);
	        //输出标签体
	        getJspBody().invoke(null);
	        return;
		} else if(StringUtils.isNotBlank(site)) {
			s = siteMapper.findOne(site);
			getJspContext().setAttribute(var, s);
	        //输出标签体
	        getJspBody().invoke(null);
	        return;
		}
		s.setName(name);
		s.setTitle(title);
		s.setDescription(description);
		s.setKeywords(keywords);
		s.setOrder(order);
		s.setSort(sort);
		List<Site> siteList = new ArrayList<Site>();
		if(!StringConstant.YES.equals(isPage)) {
			siteList = siteMapper.findAll(s);
		} else {
			s.setPage(page);
			s.setRows(rows);
			int total = siteMapper.findTotal(s);
			getJspContext().setAttribute("sitePage", new Page<Comment>(page, rows, total));
			siteList = siteMapper.findList(s);
		}
		for(Site st : siteList) {
			getJspContext().setAttribute(var, st);
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

	public void setSite(String site) {
		this.site = site;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTitle(String title) {
		this.title = title;
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
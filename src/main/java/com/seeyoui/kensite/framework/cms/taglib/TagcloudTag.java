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
import com.seeyoui.kensite.framework.cms.tagcloud.domain.Tagcloud;
import com.seeyoui.kensite.framework.cms.tagcloud.persistence.TagcloudMapper;
import com.seeyoui.kensite.framework.cms.site.domain.Site;
import com.seeyoui.kensite.framework.cms.site.persistence.SiteMapper;

public class TagcloudTag extends SimpleTagSupport  {
	
	//站点
	private static SiteMapper siteMapper = SpringContextHolder.getBean(SiteMapper.class);
	//标签云
	private static TagcloudMapper tagcloudMapper = SpringContextHolder.getBean(TagcloudMapper.class);
	private String var;
	private String id;
	private String siteId;
	private String site;
	private String isPage;
	private int page;
	private int rows;
	private String sort;
	private String order;
	
	@Override
	public void  doTag() throws JspException, IOException {
		Tagcloud tagcloud = new Tagcloud();
		if(StringUtils.isNoneBlank(id)) {
			tagcloud = tagcloudMapper.findOne(id);
			getJspContext().setAttribute(var, tagcloud);
	        //输出标签体
	        getJspBody().invoke(null);
			return;
		}
		if(StringUtils.isNoneBlank(siteId)) {
			tagcloud.setSiteId(siteId);
		} else if(StringUtils.isNotBlank(site)) {
			Site s = siteMapper.findOne(site);
			tagcloud.setSiteId(s.getId());
		}
		tagcloud.setSort(sort);
		tagcloud.setOrder(order);
		List<Tagcloud> tagcloudList = new ArrayList<Tagcloud>();
		if(!StringConstant.YES.equals(isPage)) {
			tagcloudList = tagcloudMapper.findAll(tagcloud);
		} else {
			tagcloud.setPage(page);
			tagcloud.setRows(rows);
			int total = tagcloudMapper.findTotal(tagcloud);
			getJspContext().setAttribute("tagcloudPage", new Page<Article>(page, rows, total));
			tagcloudList = tagcloudMapper.findList(tagcloud);
		}
		for(Tagcloud c : tagcloudList) {
			getJspContext().setAttribute(var, c);
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
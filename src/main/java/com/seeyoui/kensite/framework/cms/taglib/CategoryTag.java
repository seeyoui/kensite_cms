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
import com.seeyoui.kensite.framework.cms.category.domain.Category;
import com.seeyoui.kensite.framework.cms.category.persistence.CategoryMapper;
import com.seeyoui.kensite.framework.cms.comment.domain.Comment;
import com.seeyoui.kensite.framework.cms.site.domain.Site;
import com.seeyoui.kensite.framework.cms.site.persistence.SiteMapper;

public class CategoryTag extends SimpleTagSupport  {
	//站点
	private static SiteMapper siteMapper = SpringContextHolder.getBean(SiteMapper.class);
	//栏目
	private static CategoryMapper categoryMapper = SpringContextHolder.getBean(CategoryMapper.class);
	private String var;
	private String id;
	private String siteId;
	private String site;
	private String parentId;
	private String module;
	private String keywords;
	private String description;
	private String inMenu;
	private String inList;
	private String isComment;
	private String isPage;
	private int page;
	private int rows;
	private String sort;
	private String order;
	
	@Override
	public void  doTag() throws JspException, IOException {
		Category category = new Category();
		if(StringUtils.isNoneBlank(id)) {
			category = categoryMapper.findOne(id);
			getJspContext().setAttribute(var, category);
	        //输出标签体
	        getJspBody().invoke(null);
	        return;
		}
		if(StringUtils.isNoneBlank(siteId)) {
			category.setSiteId(siteId);
		} else if(StringUtils.isNotBlank(site)) {
			Site s = siteMapper.findOne(site);
			category.setSiteId(s.getId());
		}
		category.setParentId(parentId);
		category.setModule(module);
		category.setSort(sort);
		category.setOrder(order);
		category.setKeywords(keywords);
		category.setDescription(description);
		category.setInMenu(inMenu);
		category.setInList(inList);
		category.setIsComment(isComment);
		List<Category> categoryList = new ArrayList<Category>();
		if(!StringConstant.YES.equals(isPage)) {
			categoryList = categoryMapper.findAll(category);
		} else {
			category.setPage(page);
			category.setRows(rows);
			int total = categoryMapper.findTotal(category);
			getJspContext().setAttribute("categoryPage", new Page<Comment>(page, rows, total));
			categoryList = categoryMapper.findList(category);
		}
		for(Category cate : categoryList) {
			getJspContext().setAttribute(var, cate);
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

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setInMenu(String inMenu) {
		this.inMenu = inMenu;
	}

	public void setInList(String inList) {
		this.inList = inList;
	}

	public void setIsComment(String isComment) {
		this.isComment = isComment;
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
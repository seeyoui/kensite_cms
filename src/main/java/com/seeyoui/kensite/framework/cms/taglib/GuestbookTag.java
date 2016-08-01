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
import com.seeyoui.kensite.framework.cms.guestbook.domain.Guestbook;
import com.seeyoui.kensite.framework.cms.guestbook.persistence.GuestbookMapper;
import com.seeyoui.kensite.framework.cms.site.domain.Site;
import com.seeyoui.kensite.framework.cms.site.persistence.SiteMapper;

public class GuestbookTag extends SimpleTagSupport  {
	
	//站点
	private static SiteMapper siteMapper = SpringContextHolder.getBean(SiteMapper.class);
	//文章
	private static GuestbookMapper guestbookMapper = SpringContextHolder.getBean(GuestbookMapper.class);
	private String var;
	private String id;
	private String siteId;
	private String site;
	private String type;
	private String isPage;
	private int page;
	private int rows;
	private String sort;
	private String order;
	
	@Override
	public void  doTag() throws JspException, IOException {
		Guestbook guestbook = new Guestbook();
		if(StringUtils.isNoneBlank(id)) {
			guestbook = guestbookMapper.findOne(id);
			getJspContext().setAttribute(var, guestbook);
	        //输出标签体
	        getJspBody().invoke(null);
			return;
		}
		if(StringUtils.isNoneBlank(siteId)) {
			guestbook.setSiteId(siteId);
		} else if(StringUtils.isNotBlank(site)) {
			Site s = siteMapper.findOne(site);
			guestbook.setSiteId(s.getId());
		}
		guestbook.setType(type);
		guestbook.setSort(sort);
		guestbook.setOrder(order);
		List<Guestbook> guestbookList = new ArrayList<Guestbook>();
		if(!StringConstant.YES.equals(isPage)) {
			guestbookList = guestbookMapper.findAll(guestbook);
		} else {
			guestbook.setPage(page);
			guestbook.setRows(rows);
			int total = guestbookMapper.findTotal(guestbook);
			getJspContext().setAttribute("guestbookPage", new Page<Comment>(page, rows, total));
			guestbookList = guestbookMapper.findList(guestbook);
		}
		for(Guestbook c : guestbookList) {
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

	public void setType(String type) {
		this.type = type;
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
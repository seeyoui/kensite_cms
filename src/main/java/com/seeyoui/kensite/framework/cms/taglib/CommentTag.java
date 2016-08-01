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
import com.seeyoui.kensite.framework.cms.comment.persistence.CommentMapper;
import com.seeyoui.kensite.framework.cms.site.domain.Site;
import com.seeyoui.kensite.framework.cms.site.persistence.SiteMapper;

public class CommentTag extends SimpleTagSupport  {
	
	//站点
	private static SiteMapper siteMapper = SpringContextHolder.getBean(SiteMapper.class);
	//文章
	private static CommentMapper commentMapper = SpringContextHolder.getBean(CommentMapper.class);
	private String var;
	private String id;
	private String siteId;
	private String site;
	private String basicId;
	private String userId;
	private String auditState;
	private String isPage;
	private int page;
	private int rows;
	private String sort;
	private String order;
	
	@Override
	public void  doTag() throws JspException, IOException {
		Comment comment = new Comment();
		if(StringUtils.isNoneBlank(id)) {
			comment = commentMapper.findOne(id);
			getJspContext().setAttribute(var, comment);
	        //输出标签体
	        getJspBody().invoke(null);
			return;
		}
		if(StringUtils.isNoneBlank(siteId)) {
			comment.setSiteId(siteId);
		} else if(StringUtils.isNotBlank(site)) {
			Site s = siteMapper.findOne(site);
			comment.setSiteId(s.getId());
		}
		comment.setBasicId(basicId);
		comment.setUserId(userId);
		comment.setAuditState(auditState);
		comment.setSort(sort);
		comment.setOrder(order);
		List<Comment> commentList = new ArrayList<Comment>();
		if(!StringConstant.YES.equals(isPage)) {
			commentList = commentMapper.findAll(comment);
		} else {
			comment.setPage(page);
			comment.setRows(rows);
			int total = commentMapper.findTotal(comment);
			getJspContext().setAttribute("commentPage", new Page<Comment>(page, rows, total));
			commentList = commentMapper.findList(comment);
		}
		for(Comment c : commentList) {
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

	public void setBasicId(String basicId) {
		this.basicId = basicId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setAuditState(String auditState) {
		this.auditState = auditState;
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
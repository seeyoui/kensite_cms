/*
 * Powered By cuichen
 * Since 2014 - 2016
 */
package com.seeyoui.kensite.framework.cms.comment.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.seeyoui.kensite.common.base.domain.DataEntity;
import com.seeyoui.kensite.common.util.excel.annotation.ExcelField;
import com.seeyoui.kensite.framework.system.domain.SysUser;

/**
 * 内容发布评价
 * @author cuichen
 * @version 2.0
 * @since 1.0
 * @date 2016-07-15
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Comment extends DataEntity<Comment> {
	private static final long serialVersionUID = 1L;

	@ExcelField(title="所属站点", type=1, align=2, sort=7, mod="CMS_COMMENT|SITE_ID")
	private String siteId;//所属站点
	@ExcelField(title="所属栏目", type=1, align=2, sort=8, mod="CMS_COMMENT|CATEGORY_ID")
	private String categoryId;//所属栏目
	@ExcelField(title="所属主体", type=1, align=2, sort=9, mod="CMS_COMMENT|BASIC_ID")
	private String basicId;//所属主体
	@ExcelField(title="等级", type=1, align=2, sort=10, mod="CMS_COMMENT|SCORE")
	private String score;//等级
	@ExcelField(title="评价内容", type=1, align=2, sort=11, mod="CMS_COMMENT|CONTENT")
	private String content;//评价内容
	@ExcelField(title="用户ID", type=1, align=2, sort=12, mod="CMS_COMMENT|USER_ID")
	private String userId;//用户ID
	@ExcelField(title="评价人", type=1, align=2, sort=13, mod="CMS_COMMENT|USER_NAME")
	private String userName;//评价人
	@ExcelField(title="IP", type=1, align=2, sort=14, mod="CMS_COMMENT|IP")
	private String ip;//IP
	@ExcelField(title="审核用户ID", type=1, align=2, sort=15, mod="CMS_COMMENT|AUDIT_USER_ID")
	private String auditUserId;//审核用户ID
	@ExcelField(title="审核人", type=1, align=2, sort=16, mod="CMS_COMMENT|AUDIT_USER_NAME")
	private String auditUserName;//审核人
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@ExcelField(title="审核时间", type=1, align=2, sort=17, mod="CMS_COMMENT|AUDIT_DATE")
	private java.util.Date auditDate;//审核时间
	@ExcelField(title="审核状态", type=1, align=2, sort=18, mod="CMS_COMMENT|AUDIT_STATE")
	private String auditState;//审核状态
	
	private SysUser commentator;//评价人
	
	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public String getSiteId() {
		return this.siteId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryId() {
		return this.categoryId;
	}
	public void setBasicId(String basicId) {
		this.basicId = basicId;
	}

	public String getBasicId() {
		return this.basicId;
	}
	public void setScore(String score) {
		this.score = score;
	}

	public String getScore() {
		return this.score;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
		return this.content;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return this.userId;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return this.userName;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getIp() {
		return this.ip;
	}
	public void setAuditUserId(String auditUserId) {
		this.auditUserId = auditUserId;
	}

	public String getAuditUserId() {
		return this.auditUserId;
	}
	public void setAuditUserName(String auditUserName) {
		this.auditUserName = auditUserName;
	}

	public String getAuditUserName() {
		return this.auditUserName;
	}
	public void setAuditDate(java.util.Date auditDate) {
		this.auditDate = auditDate;
	}

	public java.util.Date getAuditDate() {
		return this.auditDate;
	}
	public void setAuditState(String auditState) {
		this.auditState = auditState;
	}

	public String getAuditState() {
		return this.auditState;
	}

	public SysUser getCommentator() {
		return commentator;
	}

	public void setCommentator(SysUser commentator) {
		this.commentator = commentator;
	}
}
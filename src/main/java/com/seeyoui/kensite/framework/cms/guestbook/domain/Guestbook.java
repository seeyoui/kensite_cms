/*
 * Powered By cuichen
 * Since 2014 - 2016
 */
package com.seeyoui.kensite.framework.cms.guestbook.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.seeyoui.kensite.common.base.domain.DataEntity;
import com.seeyoui.kensite.common.util.excel.annotation.ExcelField;
import com.seeyoui.kensite.framework.system.domain.SysUser;

/**
 * 内容发布留言板
 * @author cuichen
 * @version 2.0
 * @since 1.0
 * @date 2016-07-30
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Guestbook extends DataEntity<Guestbook> {
	private static final long serialVersionUID = 1L;

	@ExcelField(title="留言分类", type=1, align=2, sort=7, mod="CMS_GUESTBOOK|TYPE")
	private String type;//留言分类
	@ExcelField(title="留言内容", type=1, align=2, sort=8, mod="CMS_GUESTBOOK|CONTENT")
	private String content;//留言内容
	@ExcelField(title="姓名", type=1, align=2, sort=9, mod="CMS_GUESTBOOK|NAME")
	private String name;//姓名
	@ExcelField(title="邮箱", type=1, align=2, sort=10, mod="CMS_GUESTBOOK|EMAIL")
	private String email;//邮箱
	@ExcelField(title="电话", type=1, align=2, sort=11, mod="CMS_GUESTBOOK|PHONE")
	private String phone;//电话
	@ExcelField(title="单位", type=1, align=2, sort=12, mod="CMS_GUESTBOOK|WORKUNIT")
	private String workunit;//单位
	@ExcelField(title="IP", type=1, align=2, sort=13, mod="CMS_GUESTBOOK|IP")
	private String ip;//IP
	@ExcelField(title="回复人", type=1, align=2, sort=14, mod="CMS_GUESTBOOK|REUSERID")
	private String reuserid;//回复人
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@ExcelField(title="回复时间", type=1, align=2, sort=15, mod="CMS_GUESTBOOK|REDATE")
	private java.util.Date redate;//回复时间
	@ExcelField(title="回复内容", type=1, align=2, sort=16, mod="CMS_GUESTBOOK|RECONTENT")
	private String recontent;//回复内容
	@ExcelField(title="站点", type=1, align=2, sort=17, mod="CMS_GUESTBOOK|SITE_ID")
	private String siteId;//站点
	
	private SysUser reuser;//回复人

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return this.type;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
		return this.content;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return this.email;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return this.phone;
	}
	public void setWorkunit(String workunit) {
		this.workunit = workunit;
	}

	public String getWorkunit() {
		return this.workunit;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getIp() {
		return this.ip;
	}
	public void setReuserid(String reuserid) {
		this.reuserid = reuserid;
	}

	public String getReuserid() {
		return this.reuserid;
	}
	public void setRedate(java.util.Date redate) {
		this.redate = redate;
	}

	public java.util.Date getRedate() {
		return this.redate;
	}
	public void setRecontent(String recontent) {
		this.recontent = recontent;
	}

	public String getRecontent() {
		return this.recontent;
	}
	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public String getSiteId() {
		return this.siteId;
	}

	public SysUser getReuser() {
		return reuser;
	}

	public void setReuser(SysUser reuser) {
		this.reuser = reuser;
	}
}
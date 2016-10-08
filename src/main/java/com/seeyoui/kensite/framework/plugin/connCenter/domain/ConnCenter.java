/*
 * Powered By cuichen
 * Since 2014 - 2016
 */
package com.seeyoui.kensite.framework.plugin.connCenter.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.seeyoui.kensite.common.base.domain.DataEntity;
import com.seeyoui.kensite.common.util.excel.annotation.ExcelField;

/**
 * 数据库连接中心
 * @author cuichen
 * @version 2.0
 * @since 1.0
 * @date 2016-09-27
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConnCenter extends DataEntity<ConnCenter> {
	private static final long serialVersionUID = 1L;

	@ExcelField(title="名称", type=1, align=2, sort=7, mod="KS_CONN_CENTER|NAME")
	private String name;//名称
	@ExcelField(title="驱动", type=1, align=2, sort=8, mod="KS_CONN_CENTER|DRIVER")
	private String driver;//驱动
	@ExcelField(title="URL", type=1, align=2, sort=9, mod="KS_CONN_CENTER|URL")
	private String url;//URL
	@ExcelField(title="用户名", type=1, align=2, sort=10, mod="KS_CONN_CENTER|USERNAME")
	private String username;//用户名
	@ExcelField(title="密码", type=1, align=2, sort=11, mod="KS_CONN_CENTER|PASSWORD")
	private String password;//密码

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getDriver() {
		return this.driver;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return this.url;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return this.username;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return this.password;
	}
}
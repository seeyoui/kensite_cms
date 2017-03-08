/*
 * Powered By cuichen
 * Since 2014 - 2017
 */
package com.seeyoui.kensite.framework.eshop.shop.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.seeyoui.kensite.common.base.domain.DataEntity;
import com.seeyoui.kensite.common.util.excel.annotation.ExcelField;

/**
 * 商铺
 * @author cuichen
 * @version 2.0
 * @since 1.0
 * @date 2017-03-07
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Shop extends DataEntity<Shop> {
	private static final long serialVersionUID = 1L;

	@ExcelField(title="帐号", type=1, align=2, sort=7, mod="ES_SHOP|USER_NAME")
	private String userName;//帐号
	@ExcelField(title="密码", type=1, align=2, sort=8, mod="ES_SHOP|PASS_WORD")
	private String passWord;//密码
	@ExcelField(title="店铺名称", type=1, align=2, sort=9, mod="ES_SHOP|NAME")
	private String name;//店铺名称
	@ExcelField(title="徽标", type=1, align=2, sort=10, mod="ES_SHOP|LOGO")
	private String logo;//徽标
	@ExcelField(title="描述", type=1, align=2, sort=11, mod="ES_SHOP|DESCRIBE")
	private String describe;//描述
	@ExcelField(title="地址", type=1, align=2, sort=12, mod="ES_SHOP|ADDRESS")
	private String address;//地址
	@ExcelField(title="配置项", type=1, align=2, sort=13, mod="ES_SHOP|CONFIG")
	private String config;//配置项

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return this.userName;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getPassWord() {
		return this.passWord;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getLogo() {
		return this.logo;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getDescribe() {
		return this.describe;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return this.address;
	}
	public void setConfig(String config) {
		this.config = config;
	}

	public String getConfig() {
		return this.config;
	}
}
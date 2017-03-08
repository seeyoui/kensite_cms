/*
 * Powered By cuichen
 * Since 2014 - 2017
 */
package com.seeyoui.kensite.framework.eshop.brand.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.seeyoui.kensite.common.base.domain.DataEntity;
import com.seeyoui.kensite.common.util.excel.annotation.ExcelField;

/**
 * 品牌
 * @author cuichen
 * @version 2.0
 * @since 1.0
 * @date 2017-03-07
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Brand extends DataEntity<Brand> {
	private static final long serialVersionUID = 1L;

	@ExcelField(title="名称", type=1, align=2, sort=7, mod="ES_BRAND|NAME")
	private String name;//名称
	@ExcelField(title="徽标", type=1, align=2, sort=8, mod="ES_BRAND|LOGO")
	private String logo;//徽标
	@ExcelField(title="描述", type=1, align=2, sort=9, mod="ES_BRAND|DESCRBE")
	private String descrbe;//描述
	@ExcelField(title="网址", type=1, align=2, sort=10, mod="ES_BRAND|SITE_URL")
	private String siteUrl;//网址
	@ExcelField(title="权重", type=1, align=2, sort=11, mod="ES_BRAND|SEQ")
	private String seq;//权重
	@ExcelField(title="是否显示", type=1, align=2, sort=12, mod="ES_BRAND|IS_SHOW")
	private String isShow;//是否显示
	@ExcelField(title="所属店铺", type=1, align=2, sort=13, mod="ES_BRAND|SHOP_ID")
	private String shopId;//所属店铺

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
	public void setDescrbe(String descrbe) {
		this.descrbe = descrbe;
	}

	public String getDescrbe() {
		return this.descrbe;
	}
	public void setSiteUrl(String siteUrl) {
		this.siteUrl = siteUrl;
	}

	public String getSiteUrl() {
		return this.siteUrl;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getSeq() {
		return this.seq;
	}
	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}

	public String getIsShow() {
		return this.isShow;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getShopId() {
		return this.shopId;
	}
}
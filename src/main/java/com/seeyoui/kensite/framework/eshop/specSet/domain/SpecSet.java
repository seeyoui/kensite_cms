/*
 * Powered By cuichen
 * Since 2014 - 2017
 */
package com.seeyoui.kensite.framework.eshop.specSet.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.seeyoui.kensite.common.base.domain.DataEntity;
import com.seeyoui.kensite.common.util.excel.annotation.ExcelField;

/**
 * 规格设置
 * @author cuichen
 * @version 2.0
 * @since 1.0
 * @date 2017-03-08
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class SpecSet extends DataEntity<SpecSet> {
	private static final long serialVersionUID = 1L;

	@ExcelField(title="名称", type=1, align=2, sort=7, mod="ES_SPEC_SET|NAME")
	private String name;//名称
	@ExcelField(title="分类", type=1, align=2, sort=8, mod="ES_SPEC_SET|CATE_ID")
	private String cateId;//分类
	@ExcelField(title="规格项", type=1, align=2, sort=9, mod="ES_SPEC_SET|OPTION_VALUE")
	private String optionValue;//规格项
	@ExcelField(title="权重", type=1, align=2, sort=10, mod="ES_SPEC_SET|SEQ")
	private String seq;//权重
	@ExcelField(title="所属店铺", type=1, align=2, sort=11, mod="ES_SPEC_SET|SHOP_ID")
	private String shopId;//所属店铺

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
	public void setCateId(String cateId) {
		this.cateId = cateId;
	}

	public String getCateId() {
		return this.cateId;
	}
	public void setOptionValue(String optionValue) {
		this.optionValue = optionValue;
	}

	public String getOptionValue() {
		return this.optionValue;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getSeq() {
		return this.seq;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getShopId() {
		return this.shopId;
	}
}
/*
 * Powered By cuichen
 * Since 2014 - 2017
 */
package com.seeyoui.kensite.framework.eshop.attrSet.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.seeyoui.kensite.common.base.domain.DataEntity;
import com.seeyoui.kensite.common.util.excel.annotation.ExcelField;

/**
 * 属性设置
 * @author cuichen
 * @version 2.0
 * @since 1.0
 * @date 2017-03-08
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class AttrSet extends DataEntity<AttrSet> {
	private static final long serialVersionUID = 1L;

	@ExcelField(title="名称", type=1, align=2, sort=7, mod="ES_ATTR_SET|NAME")
	private String name;//名称
	@ExcelField(title="分类", type=1, align=2, sort=8, mod="ES_ATTR_SET|CATE_ID")
	private String cateId;//分类
	@ExcelField(title="类型", type=1, align=2, sort=9, mod="ES_ATTR_SET|TYPE")
	private String type;//类型
	@ExcelField(title="选项", type=1, align=2, sort=10, mod="ES_ATTR_SET|OPTON_VALUE")
	private String optonValue;//选项
	@ExcelField(title="默认值", type=1, align=2, sort=11, mod="ES_ATTR_SET|DEFAULT_VALUE")
	private String defaultValue;//默认值
	@ExcelField(title="所属店铺", type=1, align=2, sort=12, mod="ES_ATTR_SET|SHOP_ID")
	private String shopId;//所属店铺
	@ExcelField(title="权重", type=1, align=2, sort=13, mod="ES_ATTR_SET|SEQ")
	private String seq;//权重

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
	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return this.type;
	}
	public void setOptonValue(String optonValue) {
		this.optonValue = optonValue;
	}

	public String getOptonValue() {
		return this.optonValue;
	}
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getDefaultValue() {
		return this.defaultValue;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getShopId() {
		return this.shopId;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getSeq() {
		return this.seq;
	}
}
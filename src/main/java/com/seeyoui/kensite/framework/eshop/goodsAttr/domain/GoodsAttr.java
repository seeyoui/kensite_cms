/*
 * Powered By cuichen
 * Since 2014 - 2017
 */
package com.seeyoui.kensite.framework.eshop.goodsAttr.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.seeyoui.kensite.common.base.domain.DataEntity;
import com.seeyoui.kensite.common.util.excel.annotation.ExcelField;

/**
 * 商品属性
 * @author cuichen
 * @version 2.0
 * @since 1.0
 * @date 2017-03-09
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class GoodsAttr extends DataEntity<GoodsAttr> {
	private static final long serialVersionUID = 1L;

	@ExcelField(title="属性", type=1, align=2, sort=7, mod="ES_GOODS_ATTR|ATTR_ID")
	private String attrId;//属性
	@ExcelField(title="属性名称", type=1, align=2, sort=8, mod="ES_GOODS_ATTR|ATTR_NAME")
	private String attrName;//属性名称
	@ExcelField(title="属性值", type=1, align=2, sort=9, mod="ES_GOODS_ATTR|ATTR_VALUE")
	private String attrValue;//属性值
	@ExcelField(title="商品", type=1, align=2, sort=10, mod="ES_GOODS_ATTR|GOODS_ID")
	private String goodsId;//商品
	@ExcelField(title="权重", type=1, align=2, sort=11, mod="ES_GOODS_ATTR|SEQ")
	private String seq;//权重

	public void setAttrId(String attrId) {
		this.attrId = attrId;
	}

	public String getAttrId() {
		return this.attrId;
	}
	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	public String getAttrName() {
		return this.attrName;
	}
	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue;
	}

	public String getAttrValue() {
		return this.attrValue;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsId() {
		return this.goodsId;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getSeq() {
		return this.seq;
	}
}
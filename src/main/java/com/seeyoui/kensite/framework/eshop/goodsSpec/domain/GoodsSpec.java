/*
 * Powered By cuichen
 * Since 2014 - 2017
 */
package com.seeyoui.kensite.framework.eshop.goodsSpec.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.seeyoui.kensite.common.base.domain.DataEntity;
import com.seeyoui.kensite.common.util.excel.annotation.ExcelField;

/**
 * 商品规格
 * @author cuichen
 * @version 2.0
 * @since 1.0
 * @date 2017-03-09
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class GoodsSpec extends DataEntity<GoodsSpec> {
	private static final long serialVersionUID = 1L;

	@ExcelField(title="图片", type=1, align=2, sort=7, mod="ES_GOODS_SPEC|SPEC_IMG")
	private String specImg;//图片
	@ExcelField(title="规格项", type=1, align=2, sort=8, mod="ES_GOODS_SPEC|SPEC_OPTION")
	private String specOption;//规格项
	@ExcelField(title="价格", type=1, align=2, sort=9, mod="ES_GOODS_SPEC|PRICE")
	private String price;//价格
	@ExcelField(title="商品", type=1, align=2, sort=10, mod="ES_GOODS_SPEC|GOODS_ID")
	private String goodsId;//商品
	@ExcelField(title="权重", type=1, align=2, sort=11, mod="ES_GOODS_SPEC|SEQ")
	private String seq;//权重

	public void setSpecImg(String specImg) {
		this.specImg = specImg;
	}

	public String getSpecImg() {
		return this.specImg;
	}
	public void setSpecOption(String specOption) {
		this.specOption = specOption;
	}

	public String getSpecOption() {
		return this.specOption;
	}
	public void setPrice(String price) {
		this.price = price;
	}

	public String getPrice() {
		return this.price;
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
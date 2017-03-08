/*
 * Powered By cuichen
 * Since 2014 - 2017
 */
package com.seeyoui.kensite.framework.eshop.category.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.seeyoui.kensite.common.base.domain.DataEntity;
import com.seeyoui.kensite.common.util.excel.annotation.ExcelField;

/**
 * 分类
 * @author cuichen
 * @version 2.0
 * @since 1.0
 * @date 2017-03-07
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Cate extends DataEntity<Cate> {
	private static final long serialVersionUID = 1L;

	@ExcelField(title="名称", type=1, align=2, sort=7, mod="ES_CATEGORY|NAME")
	private String name;//名称
	@ExcelField(title="关键字", type=1, align=2, sort=8, mod="ES_CATEGORY|KEY_WORDS")
	private String keyWords;//关键字
	@ExcelField(title="描述", type=1, align=2, sort=9, mod="ES_CATEGORY|DESCRIBE")
	private String describe;//描述
	@ExcelField(title="权重", type=1, align=2, sort=10, mod="ES_CATEGORY|SEQ")
	private String seq;//权重
	@ExcelField(title="父级", type=1, align=2, sort=11, mod="ES_CATEGORY|PARENT_ID")
	private String parentId;//父级
	@ExcelField(title="计量单位", type=1, align=2, sort=12, mod="ES_CATEGORY|MEASURE_UNIT")
	private String measureUnit;//计量单位
	@ExcelField(title="价格分级", type=1, align=2, sort=13, mod="ES_CATEGORY|GRADE")
	private String grade;//价格分级
	@ExcelField(title="是否显示", type=1, align=2, sort=14, mod="ES_CATEGORY|IS_SHOW")
	private String isShow;//是否显示
	@ExcelField(title="所属店铺", type=1, align=2, sort=15, mod="ES_CATEGORY|SHOP_ID")
	private String shopId;//所属店铺

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public String getKeyWords() {
		return this.keyWords;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getDescribe() {
		return this.describe;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getSeq() {
		return this.seq;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getParentId() {
		return this.parentId;
	}
	public void setMeasureUnit(String measureUnit) {
		this.measureUnit = measureUnit;
	}

	public String getMeasureUnit() {
		return this.measureUnit;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getGrade() {
		return this.grade;
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
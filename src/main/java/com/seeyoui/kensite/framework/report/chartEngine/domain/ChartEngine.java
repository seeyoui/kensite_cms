/*
 * Powered By cuichen
 * Since 2014 - 2017
 */
package com.seeyoui.kensite.framework.report.chartEngine.domain;

import org.springframework.web.util.HtmlUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.seeyoui.kensite.common.base.domain.DataEntity;
import com.seeyoui.kensite.common.util.excel.annotation.ExcelField;

/**
 * 统计图表
 * @author cuichen
 * @version 2.0
 * @since 1.0
 * @date 2017-01-20
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class ChartEngine extends DataEntity<ChartEngine> {
	private static final long serialVersionUID = 1L;

	@ExcelField(title="描述", type=1, align=2, sort=7, mod="KS_CHART_ENGINE|DESCRIBE")
	private String describe;//描述
	@ExcelField(title="类型", type=1, align=2, sort=8, mod="KS_CHART_ENGINE|TYPE")
	private String type;//类型
	@ExcelField(title="配置项", type=1, align=2, sort=9, mod="KS_CHART_ENGINE|SET_OPTION")
	private String setOption;//配置项
	@ExcelField(title="系列源", type=1, align=2, sort=10, mod="KS_CHART_ENGINE|Z_SOURCE")
	private String zsource;//系列源
	@ExcelField(title="系列键", type=1, align=2, sort=11, mod="KS_CHART_ENGINE|Z_KEY")
	private String zkey;//系列键
	@ExcelField(title="系列值", type=1, align=2, sort=12, mod="KS_CHART_ENGINE|Z_VALUE")
	private String zvalue;//系列值
	@ExcelField(title="X轴源", type=1, align=2, sort=13, mod="KS_CHART_ENGINE|X_SOURCE")
	private String xsource;//X轴源
	@ExcelField(title="X轴键", type=1, align=2, sort=14, mod="KS_CHART_ENGINE|X_KEY")
	private String xkey;//X轴键
	@ExcelField(title="X轴系列键", type=1, align=2, sort=15, mod="KS_CHART_ENGINE|X_Z_KEY")
	private String xzkey;//X轴系列键
	@ExcelField(title="X轴值", type=1, align=2, sort=16, mod="KS_CHART_ENGINE|X_VALUE")
	private String xvalue;//X轴值
	@ExcelField(title="Y轴源", type=1, align=2, sort=17, mod="KS_CHART_ENGINE|Y_SOURCE")
	private String ysource;//Y轴源
	@ExcelField(title="Y轴系列键", type=1, align=2, sort=18, mod="KS_CHART_ENGINE|Y_Z_KEY")
	private String yzkey;//Y轴系列键
	@ExcelField(title="Y轴X轴键", type=1, align=2, sort=19, mod="KS_CHART_ENGINE|Y_X_KEY")
	private String yxkey;//Y轴X轴键
	@ExcelField(title="Y轴值", type=1, align=2, sort=20, mod="KS_CHART_ENGINE|Y_VALUE")
	private String yvalue;//Y轴值
	@ExcelField(title="扩展函数", type=1, align=2, sort=21, mod="KS_CHART_ENGINE|FUNC")
	private String func;//扩展函数
	@ExcelField(title="运算", type=1, align=2, sort=21, mod="KS_CHART_ENGINE|OPERATION")
	private String operation;
	
	private String xwhere;
	private String ywhere;
	private String zwhere;

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getDescribe() {
		return this.describe;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return this.type;
	}
	public void setSetOption(String setOption) {
		this.setOption = setOption;
	}

	public String getSetOption() {
//		return this.setOption;
		return HtmlUtils.htmlUnescape(this.setOption);
	}
	public void setZsource(String zsource) {
		this.zsource = zsource;
	}

	public String getZsource() {
		return this.zsource;
	}
	public void setZkey(String zkey) {
		this.zkey = zkey;
	}

	public String getZkey() {
		return this.zkey;
	}
	public void setZvalue(String zvalue) {
		this.zvalue = zvalue;
	}

	public String getZvalue() {
		return this.zvalue;
	}
	public void setXsource(String xsource) {
		this.xsource = xsource;
	}

	public String getXsource() {
		return this.xsource;
	}
	public void setXkey(String xkey) {
		this.xkey = xkey;
	}

	public String getXkey() {
		return this.xkey;
	}
	public void setXzkey(String xzkey) {
		this.xzkey = xzkey;
	}

	public String getXzkey() {
		return this.xzkey;
	}
	public void setXvalue(String xvalue) {
		this.xvalue = xvalue;
	}

	public String getXvalue() {
		return this.xvalue;
	}
	public void setYsource(String ysource) {
		this.ysource = ysource;
	}

	public String getYsource() {
		return this.ysource;
	}
	public void setYzkey(String yzkey) {
		this.yzkey = yzkey;
	}

	public String getYzkey() {
		return this.yzkey;
	}
	public void setYxkey(String yxkey) {
		this.yxkey = yxkey;
	}

	public String getYxkey() {
		return this.yxkey;
	}
	public void setYvalue(String yvalue) {
		this.yvalue = yvalue;
	}

	public String getYvalue() {
		return this.yvalue;
	}
	public void setFunc(String func) {
		this.func = func;
	}

	public String getFunc() {
//		return this.func;
		return HtmlUtils.htmlUnescape(this.func);
	}

	public String getXwhere() {
		return HtmlUtils.htmlUnescape(this.xwhere);
	}

	public void setXwhere(String xwhere) {
		this.xwhere = xwhere;
	}

	public String getYwhere() {
		return HtmlUtils.htmlUnescape(this.ywhere);
	}

	public void setYwhere(String ywhere) {
		this.ywhere = ywhere;
	}

	public String getZwhere() {
		return HtmlUtils.htmlUnescape(this.zwhere);
	}

	public void setZwhere(String zwhere) {
		this.zwhere = zwhere;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

}
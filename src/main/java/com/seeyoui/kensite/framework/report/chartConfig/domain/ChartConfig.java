/*
 * Powered By cuichen
 * Since 2014 - 2016
 */
package com.seeyoui.kensite.framework.report.chartConfig.domain;

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
 * @date 2016-09-05
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class ChartConfig extends DataEntity<ChartConfig> {
	private static final long serialVersionUID = 1L;

	@ExcelField(title="标题", type=1, align=2, sort=7, mod="KS_CHART_CONFIG|TITLE")
	private String title;//标题
	@ExcelField(title="标识", type=1, align=2, sort=8, mod="KS_CHART_CONFIG|CODE_NUM")
	private String codeNum;//标识
	@ExcelField(title="类型", type=1, align=2, sort=9, mod="KS_CHART_CONFIG|TYPE")
	private String type;//类型
	@ExcelField(title="描述", type=1, align=2, sort=10, mod="KS_CHART_CONFIG|DESCRIBE")
	private String describe;//描述
	@ExcelField(title="图表选项", type=1, align=2, sort=11, mod="KS_CHART_CONFIG|CHART_OPTION")
	private String chartOption;//图表选项
	@ExcelField(title="系列选项", type=1, align=2, sort=12, mod="KS_CHART_CONFIG|SERIES_OPTION")
	private String seriesOption;//系列选项
	@ExcelField(title="横轴", type=1, align=2, sort=13, mod="KS_CHART_CONFIG|SQLX")
	private String sqlx;//横轴
	@ExcelField(title="纵轴", type=1, align=2, sort=14, mod="KS_CHART_CONFIG|SQLY")
	private String sqly;//纵轴
	@ExcelField(title="Z轴", type=1, align=2, sort=15, mod="KS_CHART_CONFIG|SQLZ")
	private String sqlz;//Z轴

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return this.title;
	}
	public void setCodeNum(String codeNum) {
		this.codeNum = codeNum;
	}

	public String getCodeNum() {
		return this.codeNum;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return this.type;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getDescribe() {
		return this.describe;
	}
	public void setChartOption(String chartOption) {
		this.chartOption = chartOption;
	}

	public String getChartOption() {
		//return this.chartOption;
		return HtmlUtils.htmlUnescape(this.chartOption);
	}
	public void setSeriesOption(String seriesOption) {
		this.seriesOption = seriesOption;
	}

	public String getSeriesOption() {
//		return this.seriesOption;
		return HtmlUtils.htmlUnescape(this.seriesOption);
	}
	public void setSqlx(String sqlx) {
		this.sqlx = sqlx;
	}

	public String getSqlx() {
//		return this.sqlx;
		return HtmlUtils.htmlUnescape(this.sqlx);
	}
	public void setSqly(String sqly) {
		this.sqly = sqly;
	}

	public String getSqly() {
//		return this.sqly;
		return HtmlUtils.htmlUnescape(this.sqly);
	}
	public void setSqlz(String sqlz) {
		this.sqlz = sqlz;
	}

	public String getSqlz() {
//		return this.sqlz;
		return HtmlUtils.htmlUnescape(this.sqlz);
	}
}
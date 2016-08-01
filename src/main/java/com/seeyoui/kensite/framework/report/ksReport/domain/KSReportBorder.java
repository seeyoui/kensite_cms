package com.seeyoui.kensite.framework.report.ksReport.domain;

/**
 * KS报表单元格边框
 * @author cuichen
 *
 */
public class KSReportBorder {
	private String color;//颜色
	private int style;//边框类型
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getStyle() {
		return style;
	}
	public void setStyle(int style) {
		this.style = style;
	}
}

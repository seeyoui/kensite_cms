package com.seeyoui.kensite.framework.report.ksReport.domain;

import java.util.List;
import java.util.Map;

/**
 * KS报表单元格
 * @author cuichen
 *
 */
public class KSReportCell {
	private int row;//所在行
	private int col;//所在列
	private double width;//宽度
	private double height;//高度
	private int direction;//方向
	private String value;//单元格值
	private KSReportStyle style;//单元格样式
	List<Map<Object, Object>> cellValue;//单元格数据
	
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public KSReportStyle getStyle() {
		return style;
	}
	public void setStyle(KSReportStyle style) {
		this.style = style;
	}
	public List<Map<Object, Object>> getCellValue() {
		return cellValue;
	}
	public void setCellValue(List<Map<Object, Object>> cellValue) {
		this.cellValue = cellValue;
	}
	public int getCellValueSize() {
		if(this.cellValue == null) {
			return 0;
		} else {
			return this.cellValue.size();
		}
	}
}

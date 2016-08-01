package com.seeyoui.kensite.framework.report.ksReport.domain;

/**
 * KS报表单元格样式
 * @author cuichen
 *
 */
public class KSReportStyle {
	private String backColor;//背景色
	private String font;//字体
	private String foreColor;//字颜色
	private String backgroundImage;//背景图片
	private int backgroundImageLayout;//背景图片布局
	private int hAlign;//水平布局
	private int vAlign;//垂直布局
	private boolean wordWrap;//自动换行
	private KSReportBorder borderTop;//上边框
	private KSReportBorder borderBottom;//下边框
	private KSReportBorder borderLeft;//左边框
	private KSReportBorder borderRight;//右边框
	public String getBackColor() {
		return backColor;
	}
	public void setBackColor(String backColor) {
		this.backColor = backColor;
	}
	public String getFont() {
		return font;
	}
	public void setFont(String font) {
		this.font = font;
	}
	public String getForeColor() {
		return foreColor;
	}
	public void setForeColor(String foreColor) {
		this.foreColor = foreColor;
	}
	public String getBackgroundImage() {
		return backgroundImage;
	}
	public void setBackgroundImage(String backgroundImage) {
		this.backgroundImage = backgroundImage;
	}
	public int getBackgroundImageLayout() {
		return backgroundImageLayout;
	}
	public void setBackgroundImageLayout(int backgroundImageLayout) {
		this.backgroundImageLayout = backgroundImageLayout;
	}
	public int gethAlign() {
		return hAlign;
	}
	public void sethAlign(int hAlign) {
		this.hAlign = hAlign;
	}
	public int getvAlign() {
		return vAlign;
	}
	public void setvAlign(int vAlign) {
		this.vAlign = vAlign;
	}
	public boolean isWordWrap() {
		return wordWrap;
	}
	public void setWordWrap(boolean wordWrap) {
		this.wordWrap = wordWrap;
	}
	public KSReportBorder getBorderTop() {
		return borderTop;
	}
	public void setBorderTop(KSReportBorder borderTop) {
		this.borderTop = borderTop;
	}
	public KSReportBorder getBorderBottom() {
		return borderBottom;
	}
	public void setBorderBottom(KSReportBorder borderBottom) {
		this.borderBottom = borderBottom;
	}
	public KSReportBorder getBorderLeft() {
		return borderLeft;
	}
	public void setBorderLeft(KSReportBorder borderLeft) {
		this.borderLeft = borderLeft;
	}
	public KSReportBorder getBorderRight() {
		return borderRight;
	}
	public void setBorderRight(KSReportBorder borderRight) {
		this.borderRight = borderRight;
	}
	
}

package com.seeyoui.kensite.framework.plugin.upload.domain;

public class ImageCrop {
	private int x;//左上角切片起点坐标X
	private int y;//左上角切片起点坐标Y
	private int w;//切片宽度
	private int h;//切片高度
	private int tw;//目标切片缩放宽度
	private int th;//目标切片缩放宽度
	private String s;//切片原路径
	private String t;//切片后图片生成路径
	private boolean c;//是否清除原图片
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getW() {
		return w;
	}
	public void setW(int w) {
		this.w = w;
	}
	public int getH() {
		return h;
	}
	public void setH(int h) {
		this.h = h;
	}
	public int getTw() {
		return tw;
	}
	public void setTw(int tw) {
		this.tw = tw;
	}
	public int getTh() {
		return th;
	}
	public void setTh(int th) {
		this.th = th;
	}
	public String getS() {
		return s;
	}
	public void setS(String s) {
		this.s = s;
	}
	public String getT() {
		return t;
	}
	public void setT(String t) {
		this.t = t;
	}
	public boolean isC() {
		return c;
	}
	public void setC(boolean c) {
		this.c = c;
	}
	
}

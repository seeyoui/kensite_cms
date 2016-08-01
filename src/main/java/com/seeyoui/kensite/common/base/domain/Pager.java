package com.seeyoui.kensite.common.base.domain;

import java.io.Serializable;

public class Pager implements Serializable {
	private static final long serialVersionUID = 2906682093124263902L;
	private int page;
	private int rows;
	private int row;
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getRow() {
		return (this.page-1)*this.rows;
	}
	
}

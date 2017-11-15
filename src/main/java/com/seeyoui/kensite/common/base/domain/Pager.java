package com.seeyoui.kensite.common.base.domain;

import java.io.Serializable;

public class Pager implements Serializable {
	private static final long serialVersionUID = 2906682093124263902L;
	private int page;
	private int rows;
	private int row;
	private long total;
	public int getPage() {
		return this.page <= 0 ? 1 : this.page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return this.rows <= 0 ? 20 : this.rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getRow() {
		return (this.page-1)*this.rows;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public int getFirstResult(){
		int firstResult = (getPage() - 1) * getRows();
		if (firstResult >= getTotal() || firstResult<0) {
			firstResult = 0;
		}
		return firstResult;
	}
	public int getMaxResults(){
		return getFirstResult()+getRows();
	}
}

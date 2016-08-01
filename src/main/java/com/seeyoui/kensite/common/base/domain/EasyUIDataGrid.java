package com.seeyoui.kensite.common.base.domain;

import java.io.Serializable;

public class EasyUIDataGrid implements Serializable {
	private static final long serialVersionUID = 1L;
	private String total;
	Object rows;
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public Object getRows() {
		return rows;
	}
	public void setRows(Object rows) {
		this.rows = rows;
	}
	
}

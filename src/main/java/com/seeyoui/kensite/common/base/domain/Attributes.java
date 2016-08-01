package com.seeyoui.kensite.common.base.domain;

import java.io.Serializable;

public class Attributes implements Serializable {
	private static final long serialVersionUID = 1L;
	private String url;
	private String icon;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
}

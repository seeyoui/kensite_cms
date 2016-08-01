package com.seeyoui.kensite.framework.luence.domain;

import com.seeyoui.kensite.common.util.StringUtils;

/**
 * luence document
 * @author cuichen
 * 2016-04-19
 */
public class LuceneDocument {
	private String id;//主键
	private String content;//内容
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		if(StringUtils.isBlank(content)) {
			return "";
		}
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}

/*
 * Powered By cuichen
 * Since 2014 - 2017
 */
package com.seeyoui.kensite.framework.report.dataSource.domain;

import org.springframework.web.util.HtmlUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.seeyoui.kensite.common.base.domain.DataEntity;
import com.seeyoui.kensite.common.util.StringUtils;
import com.seeyoui.kensite.common.util.excel.annotation.ExcelField;

/**
 * 数据源
 * @author cuichen
 * @version 2.0
 * @since 1.0
 * @date 2017-02-08
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class DataSource extends DataEntity<DataSource> {
	private static final long serialVersionUID = 1L;

	@ExcelField(title="名称", type=1, align=2, sort=7, mod="KS_DATA_SOURCE|NAME")
	private String name;//名称
	@ExcelField(title="编码", type=1, align=2, sort=8, mod="KS_DATA_SOURCE|CODE_NUM")
	private String codeNum;//编码
	@ExcelField(title="类型", type=1, align=2, sort=9, mod="KS_DATA_SOURCE|TYPE")
	private String type;//类型
	@ExcelField(title="描述", type=1, align=2, sort=10, mod="KS_DATA_SOURCE|DESCRIBE")
	private String describe;//描述
	@ExcelField(title="内容", type=1, align=2, sort=11, mod="KS_DATA_SOURCE|CONTENT")
	private String content;//内容
	@ExcelField(title="视图SQL", type=1, align=2, sort=12, mod="KS_DATA_SOURCE|VIEW_SQL")
	private String viewSql;//视图SQL

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
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
//		return this.describe;
		return HtmlUtils.htmlUnescape(this.describe);
	}
	public void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
//		return this.content;
		return HtmlUtils.htmlUnescape(this.content);
	}
	public void setViewSql(String viewSql) {
		this.viewSql = viewSql;
	}

	public String getViewSql() {
//		return this.viewSql;
		return HtmlUtils.htmlUnescape(this.viewSql);
	}
}
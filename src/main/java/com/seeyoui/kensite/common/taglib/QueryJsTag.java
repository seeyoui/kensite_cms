package com.seeyoui.kensite.common.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.seeyoui.kensite.common.taglib.util.QueryJsUtils;
import com.seeyoui.kensite.common.util.StringUtils;
import com.seeyoui.kensite.framework.mod.tableColumn.domain.TableColumn;

public class QueryJsTag extends TagSupport {
	private static final long serialVersionUID = 1L;
	
	private String table;
	private String column;
	
	@Override
	public int doStartTag() throws JspException {
		//如果返回SKIP_BODY则忽略标签之中的内容，如果返回EVAL_BODY_INCLUDE则将标签体的内容进行输出
		try {
			TableColumn tableColumn = new TableColumn();
			tableColumn.setTableName(table);
			tableColumn.setName(column);
			StringBuffer result = QueryJsUtils.getTableColumnStr(tableColumn);
			if (StringUtils.isBlank(result)) {
				return SKIP_BODY;
			}
			JspWriter out = this.pageContext.getOut();
			out.println(result.toString());
		} catch (Exception e) {
			throw new JspException(e.getMessage());
		}
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {
		//返回SKIP_PAGE跳过整个jsp页面后面的输出，返回EVAL_PAGE执行页面余下部分
		//返回EVAL_BODY_AGAIN那么将重新执行此方法
		return EVAL_PAGE;
	}

	@Override
	public void release() {
		super.release();
		this.table = null;
		this.column = null;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public void setColumn(String column) {
		this.column = column;
	}
	
}
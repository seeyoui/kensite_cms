package com.seeyoui.kensite.common.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.seeyoui.kensite.common.util.SpringContextHolder;
import com.seeyoui.kensite.common.util.StringUtils;
import com.seeyoui.kensite.common.util.TemplateUtils;
import com.seeyoui.kensite.framework.report.chartEngine.domain.ChartEngine;
import com.seeyoui.kensite.framework.report.chartEngine.persistence.ChartEngineMapper;

public class ChartTag extends TagSupport {
	private static final long serialVersionUID = 1L;
	
	private static ChartEngineMapper chartEngineMapper = SpringContextHolder.getBean(ChartEngineMapper.class);
	
	private String id;
	private String uuid;
	
	@Override
	public int doStartTag() throws JspException {
		//如果返回SKIP_BODY则忽略标签之中的内容，如果返回EVAL_BODY_INCLUDE则将标签体的内容进行输出
		try {
			ChartEngine chartEngine = null;
			if(StringUtils.isNoneBlank(id)) {
				chartEngine = chartEngineMapper.findOne(id);
			}
			if(StringUtils.isNoneBlank(uuid)) {
				chartEngine = chartEngineMapper.findOne(uuid);
			}
			if(chartEngine == null) {
				return SKIP_BODY;
			}
			String result = TemplateUtils.getStringFromTemplate(chartEngine, TemplateUtils.getFilePath("chart"), "chartTemplate.ks");
			if (StringUtils.isBlank(result)) {
				return SKIP_BODY;
			}
			JspWriter out = this.pageContext.getOut();
			out.println(result);
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
		this.id = null;
		this.uuid = null;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

}
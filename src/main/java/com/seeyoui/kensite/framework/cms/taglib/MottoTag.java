package com.seeyoui.kensite.framework.cms.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import net.sf.json.JSONObject;

import com.seeyoui.kensite.common.util.HttpRequestUtil;

public class MottoTag extends SimpleTagSupport  {
	
	private String var;
	
	@Override
	public void  doTag() throws JspException, IOException {
		String url = "http://open.iciba.com/dsapi/";
		String param = "";
		String source = HttpRequestUtil.sendGet(url, param);
		getJspContext().setAttribute(var, JSONObject.fromObject(source));
        //输出标签体
        getJspBody().invoke(null);
		return;
	}

	public void setVar(String var) {
		this.var = var;
	}
	
}
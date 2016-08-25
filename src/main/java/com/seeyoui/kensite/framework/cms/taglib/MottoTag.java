package com.seeyoui.kensite.framework.cms.taglib;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import net.sf.json.JSONObject;

import com.seeyoui.kensite.common.util.CacheUtils;
import com.seeyoui.kensite.common.util.HttpRequestUtil;

public class MottoTag extends SimpleTagSupport  {
	
	private String var;
	
	@Override
	public void  doTag() throws JspException, IOException {
		Calendar cal = Calendar.getInstance();
		String today = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
		cal.add(Calendar.DATE, -1);
		String yesterday = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
		JSONObject motto = (JSONObject)CacheUtils.get("ICIBA_MOTTO_"+today);
		if(motto != null) {
			getJspContext().setAttribute(var, motto);
	        //输出标签体
	        getJspBody().invoke(null);
			return;
		} else {
			String url = "http://open.iciba.com/dsapi/";
			String param = "";
			String source = HttpRequestUtil.sendGet(url, param);
			motto = JSONObject.fromObject(source);
			CacheUtils.remove("ICIBA_MOTTO_"+yesterday);
			CacheUtils.put("ICIBA_MOTTO_"+today, motto);
			getJspContext().setAttribute(var, motto);
	        //输出标签体
	        getJspBody().invoke(null);
			return;
		}
	}

	public void setVar(String var) {
		this.var = var;
	}
	
}
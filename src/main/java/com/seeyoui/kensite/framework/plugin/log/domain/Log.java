/*
 * Powered By cuichen
 * Since 2014 - 2015
 */
package com.seeyoui.kensite.framework.plugin.log.domain;  

import java.util.Map;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.seeyoui.kensite.common.base.domain.DataEntity;
import com.seeyoui.kensite.common.util.StringUtils;

/**
 * @author cuichen
 * @version 1.0
 * @since 1.0
 */
public class Log extends DataEntity<Log> {
    private static final long serialVersionUID = 5454155825314635342L;
      
    //日志类型
    private String type;
    //日志标题
    private String title;
    //操作用户的IP地址
    private String remoteAddr;
    //操作用户代理信息
    private String userAgent;
    //操作的URI
    private String requestUri;
    //操作的方式
    private String method;
    //操作提交的数据
    private String params;
    //异常信息
    private String exception;
    //耗费时间
    private String spendTime;
 
    public void setType(String type) {
        this.type = type;
    }
    
    public String getType() {
        return this.type;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getTitle() {
        return this.title;
    }
    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }
    
    public String getRemoteAddr() {
        return this.remoteAddr;
    }
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
    
    public String getUserAgent() {
        return this.userAgent;
    }
    public void setRequestUri(String requestUri) {
        this.requestUri = requestUri;
    }
    
    public String getRequestUri() {
        return this.requestUri;
    }
    public void setMethod(String method) {
        this.method = method;
    }
    
    public String getMethod() {
        return this.method;
    }
    public void setParams(String params) {
        this.params = params;
    }
    
    public String getParams() {
        return this.params;
    }
    public void setException(String exception) {
        this.exception = exception;
    }
    
    public String getException() {
        return this.exception;
    }
    
    public String getSpendTime() {
		return spendTime;
	}

	public void setSpendTime(String spendTime) {
		this.spendTime = spendTime;
	}

	/**
	 * 设置请求参数
	 * @param paramMap
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setParams(Map paramMap){
		if (paramMap == null){
			return;
		}
		StringBuilder params = new StringBuilder();
		for (Map.Entry<String, String[]> param : ((Map<String, String[]>)paramMap).entrySet()){
			params.append(("".equals(params.toString()) ? "" : "&") + param.getKey() + "=");
			String paramValue = (param.getValue() != null && param.getValue().length > 0 ? param.getValue()[0] : "");
			params.append(StringUtils.abbr(StringUtils.endsWithIgnoreCase(param.getKey(), "password") ? "" : paramValue, 100));
		}
		this.params = params.toString();
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
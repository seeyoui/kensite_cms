package com.seeyoui.kensite.common.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.seeyoui.kensite.common.util.SessionUtil;

/**
 * Session拦截器
 * @author SeeYoui
 * @version 2015-09-25
 */
public class AuthorityInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
			Object handler) throws Exception {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        /**
         * httpRequest.getContextPath() + "/"			/kensite/
         * httpRequest.getRequestURI()					/kensite/static/kensite.info.js
         * httpRequest.getServletPath()					/static/kensite.info.js
         * url.substring(url.lastIndexOf("/"))				/kensite.info.js
         */
        String loginUrl = httpRequest.getContextPath() + "/";
        // 超时处理，ajax请求超时设置超时状态，页面请求超时则返回提示并重定向
        String currentUserName = String.valueOf(SessionUtil.getSession("currentUserName"));
        if ((currentUserName == null || "".equals(currentUserName) || "null".equals(currentUserName))) {
            // 判断是否为ajax请求
            if (httpRequest.getHeader("x-requested-with") != null
                    && httpRequest.getHeader("x-requested-with")
                            .equalsIgnoreCase("XMLHttpRequest")) {
                httpResponse.addHeader("sessionstatus", "timeOut");
                httpResponse.addHeader("loginPath", loginUrl);
                return false;
            } else {
                String str = "<script language='javascript'>alert('会话过期,请重新登录');"
                        + "window.top.location.href='"
                        + loginUrl
                        + "';</script>";
                response.setContentType("text/html;charset=UTF-8");// 解决中文乱码
                try {
                    PrintWriter writer = response.getWriter();
                    writer.write(str);
                    writer.flush();
                    writer.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            }
        }
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, 
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, 
			Object handler, Exception ex) throws Exception {
	}

}

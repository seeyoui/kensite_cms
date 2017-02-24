package com.seeyoui.kensite.common.interceptor;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.seeyoui.kensite.common.base.service.BaseService;
import com.seeyoui.kensite.common.license.License;
import com.seeyoui.kensite.common.util.MD5;
import com.seeyoui.kensite.common.util.MacUtils;
import com.seeyoui.kensite.common.util.SessionUtil;

/**
 * License拦截器
 * @author SeeYoui
 * @version 2015-09-25
 */
public class LicenseInterceptor implements HandlerInterceptor {

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
        //判断来自PMS还是管理平台
      	String path = request.getServletPath();
      	String url = "";
		if(path.indexOf("pms") != -1){
			url += "/pms.jsp";
		}else{
			url += "/";
		}
        
        String loginUrl = httpRequest.getContextPath() + url;
		String mac = MacUtils.getMac();
		String license = MD5.md5(mac); // 客户标识
		String absUrl = request.getSession().getServletContext().getRealPath("/");
        String licensefilepath = absUrl+"/"+license+".license";
		if(License.ckLicense(licensefilepath)) {
			return true;
		} else {
			String str = "<script language='javascript'>alert('授权证书已失效');"
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

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, 
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, 
			Object handler, Exception ex) throws Exception {
	}

}

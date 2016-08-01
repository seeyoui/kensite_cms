package com.seeyoui.kensite.common.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

public class SessionUtil {

	/**
	 * 向session中设置信息
	 * @param key
	 * @param value
	 */
	public static void setSession(Object key, Object value){
		Subject currentUser = SecurityUtils.getSubject();
		if(null != currentUser){
			Session session = currentUser.getSession();
			if(null != session){
				session.setAttribute(key, value);
			}
		}
	}
	
	/**
	 * 从session中查询信息
	 * @param key
	 * @return
	 */
	public static Object getSession(Object key) {
		Subject currentUser = SecurityUtils.getSubject();
		if(null != currentUser){
			Session session = currentUser.getSession();
			if(null != session){
				return session.getAttribute(key);
			}
		}
		return null;
	}
	
}

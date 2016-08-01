package com.seeyoui.kensite.framework.plugin.log.util;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.method.HandlerMethod;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.seeyoui.kensite.common.exception.Exceptions;
import com.seeyoui.kensite.common.util.CacheUtils;
import com.seeyoui.kensite.common.util.Global;
import com.seeyoui.kensite.common.util.SpringContextHolder;
import com.seeyoui.kensite.common.util.StringUtils;
import com.seeyoui.kensite.framework.plugin.log.domain.Log;
import com.seeyoui.kensite.framework.plugin.log.persistence.LogMapper;
import com.seeyoui.kensite.framework.system.domain.SysMenu;
import com.seeyoui.kensite.framework.system.domain.SysPermission;
import com.seeyoui.kensite.framework.system.domain.SysUser;
import com.seeyoui.kensite.framework.system.persistence.SysMenuMapper;
import com.seeyoui.kensite.framework.system.util.UserUtils;

/**
 * 日志工具类
 * @author SeeYoui
 * @version 2015-6-23
 */
public class LogUtils {
	
	public static final String CACHE_MENU_NAME_PATH_MAP = "menuNamePathMap";
	
	private static LogMapper logMapper = SpringContextHolder.getBean(LogMapper.class);
	
	/**
	 * 保存日志
	 */
	public static void saveLog(HttpServletRequest request, String title, long spendTime){
		saveLog(request, null, null, title, spendTime);
	}
	
	/**
	 * 保存日志
	 */
	public static void saveLog(HttpServletRequest request, Object handler, Exception ex, String title, long spendTime){
		SysUser user = UserUtils.getUser();
		if (user != null && user.getId() != null){
			Log log = new Log();
			log.setTitle(title);
			log.setCreateUser(user);
			log.setType(ex == null ? "s" : "e");
			log.setRemoteAddr(StringUtils.getRemoteAddr(request));
			log.setUserAgent(request.getHeader("user-agent"));
			log.setRequestUri(request.getRequestURI());
			log.setParams(request.getParameterMap());
			log.setMethod(request.getMethod());
			log.setSpendTime(String.valueOf(spendTime));
			// 异步保存日志
			new SaveLogThread(log, handler, ex).start();
		}
	}

	/**
	 * 保存日志线程
	 */
	public static class SaveLogThread extends Thread{
		
		private Log log;
		private Object handler;
		private Exception ex;
		
		public SaveLogThread(Log log, Object handler, Exception ex){
			super(SaveLogThread.class.getSimpleName());
			this.log = log;
			this.handler = handler;
			this.ex = ex;
		}
		
		@Override
		public void run() {
			// 获取日志标题
			if (StringUtils.isBlank(log.getTitle())){
				String permission = "";
				if (handler instanceof HandlerMethod){
					Method m = ((HandlerMethod)handler).getMethod();
					RequiresPermissions rp = m.getAnnotation(RequiresPermissions.class);
					permission = (rp != null ? StringUtils.join(rp.value(), ",") : "");
				}
				log.setTitle(getNameByPermission(permission));
			}
			// 如果有异常，设置异常信息
			log.setException(Exceptions.getStackTraceAsString(ex));
			// 如果无标题并无异常日志，则不保存信息
			if (StringUtils.isBlank(log.getTitle()) && StringUtils.isBlank(log.getException())){
				return;
			}
			// 保存日志信息
			log.preInsert();
			logMapper.save(log);
		}
	}

	public static String getNameByPermission(String permission) {
		List<SysPermission> sysPermissionList = UserUtils.getPermissionList();
		StringBuffer sb = new StringBuffer();
		for (String p : StringUtils.split(permission)){
			for(SysPermission sp : sysPermissionList) {
				if(p.equals(sp.getId())) {
					sb.append(sp.getName());
				}
			}
		}
		return sb.toString();
	}
}

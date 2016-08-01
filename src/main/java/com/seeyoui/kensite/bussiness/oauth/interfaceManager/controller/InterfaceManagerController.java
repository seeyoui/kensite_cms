/*
 * Powered By cuichen
 * Since 2014 - 2015
 */
package com.seeyoui.kensite.bussiness.oauth.interfaceManager.controller;  
 
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.seeyoui.kensite.common.base.controller.BaseController;
/**
 * 接口目录
 * @author cuichen
 * @version 1.0
 * @since 1.0
 * @date 2015-12-09
 */
@Controller
@RequestMapping(value = "oauth/interfaceManager")
public class InterfaceManagerController extends BaseController {
	
	/**
	 * 展示列表页面
	 * @param modelMap
	 * @param module
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/{page}")
	public ModelAndView view(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, @PathVariable String page) throws Exception {
		return new ModelAndView("bussiness/oauth/interfaceManager/"+page, modelMap);
	}
	
	/**
	 * 获取当前毫秒数
	 * @param modelMap
	 * @param module
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getSystemTime")
	@ResponseBody
	public Object getSystemTime(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap) throws Exception {
		Map<String, Long> result = new HashMap<String, Long>();
		result.put("currentSystemTime", new Date().getTime());
		return result;
	}
	
}
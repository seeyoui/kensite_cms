/*
 * Powered By cuichen
 * Since 2014 - 2015
 */
package com.seeyoui.kensite.framework.plugin.log.controller;  
 
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.seeyoui.kensite.common.base.controller.BaseController;
import com.seeyoui.kensite.common.base.domain.EasyUIDataGrid;
import com.seeyoui.kensite.common.util.RequestResponseUtil;
import com.seeyoui.kensite.framework.plugin.log.domain.Log;
import com.seeyoui.kensite.framework.plugin.log.service.LogService;
/**
 * @author cuichen
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping(value = "sys/log")
public class LogController extends BaseController {
	
	@Autowired
	private LogService siteService;
	
	/**
	 * 展示列表页面
	 * @param modelMap
	 * @param module
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("sys:log:view")
	@RequestMapping(value = "/{page}")
	public ModelAndView view(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, @PathVariable String page) throws Exception {
		return new ModelAndView("framework/plugin/"+page, modelMap);
	}
	
	/**
	 * 获取列表展示数据
	 * @param modelMap
	 * @param log
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("sys:log:select")
	@RequestMapping(value = "/list/data", method=RequestMethod.POST)
	@ResponseBody
	public Object listData(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, Log log) throws Exception{
		List<Log> logList = siteService.findList(log);
		int total = siteService.findTotal(log);
		EasyUIDataGrid eudg = new EasyUIDataGrid();
		eudg.setRows(logList);
		eudg.setTotal(String.valueOf(total));
		return eudg;
	}
	
}
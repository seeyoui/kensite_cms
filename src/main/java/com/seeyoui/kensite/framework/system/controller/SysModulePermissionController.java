/*
 * Powered By cuichen
 * Since 2014 - 2015
 */
package com.seeyoui.kensite.framework.system.controller;  
 
import java.sql.*;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.seeyoui.kensite.common.base.controller.BaseController;
import com.seeyoui.kensite.common.constants.StringConstant;
import com.seeyoui.kensite.common.base.domain.EasyUIDataGrid;
import com.seeyoui.kensite.common.base.domain.TreeJson;
import com.seeyoui.kensite.common.base.controller.BaseController;
import com.seeyoui.kensite.common.util.RequestResponseUtil;
import com.seeyoui.kensite.framework.system.domain.SysModulePermission;
import com.seeyoui.kensite.framework.system.domain.SysModulePermission;
import com.seeyoui.kensite.framework.system.service.SysModulePermissionService;
/**
 * @author cuichen
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping(value = "sysModulePermission")
public class SysModulePermissionController extends BaseController {
	
	@Autowired
	private SysModulePermissionService sysModulePermissionService;
	
	/**
	 * 获取列表展示数据
	 * @param modelMap
	 * @param sysModulePermission
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("sysModulePermission:select")
	@RequestMapping(value = "/tree", method=RequestMethod.POST)
	@ResponseBody
	public String tree(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, SysModulePermission sysModulePermission) throws Exception{
		List<TreeJson> tList = sysModulePermissionService.tree(sysModulePermission);
		JSONArray jsonObj = JSONArray.fromObject(tList);
		return jsonObj.toString();
	}
	
	/**
	 * 保存新增的数据
	 * @param modelMap
	 * @param sysModulePermission
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("sysModulePermission:insert")
	@RequestMapping(value = "/save", method=RequestMethod.POST)
	@ResponseBody
	public String save(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, SysModulePermission sysModulePermission) throws Exception{
		if (!beanValidator(modelMap, sysModulePermission)){
			RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.FALSE);
			return null;
		}
		sysModulePermissionService.save(sysModulePermission);
		RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.TRUE);
		return null;
	}
}
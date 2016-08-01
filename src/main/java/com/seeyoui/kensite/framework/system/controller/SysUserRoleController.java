/*
 * Powered By cuichen
 * Since 2014 - 2015
 */
package com.seeyoui.kensite.framework.system.controller;  
 
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.seeyoui.kensite.common.base.controller.BaseController;
import com.seeyoui.kensite.common.base.domain.TreeJson;
import com.seeyoui.kensite.common.constants.StringConstant;
import com.seeyoui.kensite.common.util.RequestResponseUtil;
import com.seeyoui.kensite.framework.system.domain.SysUserRole;
import com.seeyoui.kensite.framework.system.service.SysUserRoleService;

/**
 * @author cuichen
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping(value = "sysUserRole")
public class SysUserRoleController extends BaseController {
	
	@Autowired
	private SysUserRoleService sysUserRoleService;
	
	/**
	 * 获取列表展示数据
	 * @param modelMap
	 * @param sysUserRole
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("sysUserRole:select")
	@RequestMapping(value = "/tree", method=RequestMethod.POST)
	@ResponseBody
	public Object getTreeData(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, SysUserRole sysUserRole) throws Exception{
		List<TreeJson> tList = sysUserRoleService.tree(sysUserRole);
		return tList;
	}
	
	/**
	 * 保存新增的数据
	 * @param modelMap
	 * @param sysUserRole
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("sysUserRole:insert")
	@RequestMapping(value = "/save", method=RequestMethod.POST)
	@ResponseBody
	public String saveUserRole(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, SysUserRole sysUserRole) throws Exception{
		if (!beanValidator(modelMap, sysUserRole)){
			RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.FALSE);
			return null;
		}
		sysUserRoleService.save(sysUserRole);
		RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.TRUE);
		return null;
	}
}
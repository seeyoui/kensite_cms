/*
 * Powered By cuichen
 * Since 2014 - 2015
 */
package com.seeyoui.kensite.framework.system.controller;  
 
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.seeyoui.kensite.common.constants.StringConstant;
import com.seeyoui.kensite.common.util.RequestResponseUtil;
import com.seeyoui.kensite.framework.system.domain.SysRole;
import com.seeyoui.kensite.framework.system.service.SysRoleService;
/**
 * @author cuichen
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping(value = "sysRole")
public class SysRoleController extends BaseController {
	
	@Autowired
	private SysRoleService sysRoleService;
	
	/**
	 * 展示列表页面
	 * @param modelMap
	 * @param module
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("sysRole:view")
	@RequestMapping(value = "/{page}")
	public ModelAndView showSysDepartmentPageList(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, @PathVariable String page) throws Exception {
		return new ModelAndView("framework/system/role/"+page, modelMap);
	}
	
	/**
	 * 根据ID查询单条数据
	 * @param modelMap
	 * @param module
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("sysRole:select")
	@RequestMapping(value = "/data/{id}")
	@ResponseBody
	public Object data(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, @PathVariable String id) throws Exception {
		SysRole sysRole = sysRoleService.findOne(id);
		return sysRole;
	}
	
	/**
	 * 获取列表展示数据
	 * @param modelMap
	 * @param sysRole
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("sysRole:select")
	@RequestMapping(value = "/list/data", method=RequestMethod.POST)
	@ResponseBody
	public Object listData(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, SysRole sysRole) throws Exception{
		List<SysRole> sysRoleList = sysRoleService.findList(sysRole);
		int total = sysRoleService.findTotal(sysRole);
		EasyUIDataGrid eudg = new EasyUIDataGrid();
		eudg.setRows(sysRoleList);
		eudg.setTotal(String.valueOf(total));
		return eudg;
	}
	
	/**
	 * 获取列表展示数据
	 * @param modelMap
	 * @param sysRole
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("sysRole:select")
	@RequestMapping(value = "/list/all", method=RequestMethod.POST)
	@ResponseBody
	public Object listAll(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, SysRole sysRole) throws Exception{
		List<SysRole> sysRoleList = sysRoleService.findAll(sysRole);
		return sysRoleList;
	}
	
	/**
	 * 保存新增的数据
	 * @param modelMap
	 * @param sysRole
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("sysRole:insert")
	@RequestMapping(value = "/save", method=RequestMethod.POST)
	@ResponseBody
	public String save(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, SysRole sysRole) throws Exception{
		if (!beanValidator(modelMap, sysRole)){
			RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.FALSE);
			return null;
		}
		sysRoleService.save(sysRole);
		RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.TRUE);
		return null;
	}
	
	/**
	 * 保存修改的数据
	 * @param modelMap
	 * @param sysRole
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("sysRole:update")
	@RequestMapping(value = "/update", method=RequestMethod.POST)
	@ResponseBody
	public String update(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, SysRole sysRole) throws Exception{
		if (!beanValidator(modelMap, sysRole)){
			RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.FALSE);
			return null;
		}
		sysRoleService.update(sysRole);
		RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.TRUE);
		return null;
	}
	
	/**
	 * 删除数据库
	 * @param modelMap
	 * @param sysRoleId
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("sysRole:delete")
	@RequestMapping(value = "/delete", method=RequestMethod.POST)
	@ResponseBody
	public String delete(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, String id) throws Exception {
		List<String> listId = Arrays.asList(id.split(","));
		sysRoleService.delete(listId);
		RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.TRUE);
		return null;
	}
}
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
import com.seeyoui.kensite.framework.system.domain.SysModule;
import com.seeyoui.kensite.framework.system.service.SysModuleService;
/**
 * @author cuichen
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping(value = "sysModule")
public class SysModuleController extends BaseController {
	
	@Autowired
	private SysModuleService sysModuleService;
	
	/**
	 * 展示列表页面
	 * @param modelMap
	 * @param module
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("sysModule:view")
	@RequestMapping(value = "/{page}")
	public ModelAndView showSysDepartmentPageList(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, @PathVariable String page) throws Exception {
		return new ModelAndView("framework/system/module/"+page, modelMap);
	}
	
	/**
	 * 根据ID查询单条数据
	 * @param modelMap
	 * @param module
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("sysModule:select")
	@RequestMapping(value = "/data/{id}")
	@ResponseBody
	public Object data(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, @PathVariable String id) throws Exception {
		SysModule sysModule = sysModuleService.findOne(id);
		return sysModule;
	}
	
	/**
	 * 获取列表展示数据
	 * @param modelMap
	 * @param sysModule
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("sysModule:select")
	@RequestMapping(value = "/list/data", method=RequestMethod.POST)
	@ResponseBody
	public Object listData(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, SysModule sysModule) throws Exception{
		List<SysModule> sysModuleList = sysModuleService.findList(sysModule);
		int total = sysModuleService.findTotal(sysModule);
		EasyUIDataGrid eudg = new EasyUIDataGrid();
		eudg.setRows(sysModuleList);
		eudg.setTotal(String.valueOf(total));
		return eudg;
	}
	
	/**
	 * 获取所有数据
	 * @param modelMap
	 * @param demo
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("sysModule:select")
	@RequestMapping(value = "/list/all", method=RequestMethod.POST)
	@ResponseBody
	public Object listAll(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, SysModule sysModule) throws Exception {
		List<SysModule> sysModuleList = sysModuleService.findAll(sysModule);
		return sysModuleList;
	}
	
	/**
	 * 保存新增的数据
	 * @param modelMap
	 * @param sysModule
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("sysModule:insert")
	@RequestMapping(value = "/save", method=RequestMethod.POST)
	@ResponseBody
	public String save(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, SysModule sysModule) throws Exception{
		if (!beanValidator(modelMap, sysModule)){
			RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.FALSE);
			return null;
		}
		sysModuleService.save(sysModule);
		RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.TRUE);
		return null;
	}
	
	/**
	 * 保存修改的数据
	 * @param modelMap
	 * @param sysModule
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("sysModule:update")
	@RequestMapping(value = "/update", method=RequestMethod.POST)
	@ResponseBody
	public String update(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, SysModule sysModule) throws Exception{
		if (!beanValidator(modelMap, sysModule)){
			RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.FALSE);
			return null;
		}
		sysModuleService.update(sysModule);
		RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.TRUE);
		return null;
	}
	
	/**
	 * 删除数据库
	 * @param modelMap
	 * @param sysModuleId
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("sysModule:delete")
	@RequestMapping(value = "/delete", method=RequestMethod.POST)
	@ResponseBody
	public String delete(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, String id) throws Exception {
		List<String> listId = Arrays.asList(id.split(","));
		sysModuleService.delete(listId);
		RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.TRUE);
		return null;
	}
}
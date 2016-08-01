/*
 * Powered By cuichen
 * Since 2014 - 2015
 */
package com.seeyoui.kensite.framework.plugin.skins.controller;  
 
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.seeyoui.kensite.common.base.controller.BaseController;
import com.seeyoui.kensite.common.constants.StringConstant;
import com.seeyoui.kensite.common.util.RequestResponseUtil;
import com.seeyoui.kensite.common.constants.StringConstant;
import com.seeyoui.kensite.common.base.domain.EasyUIDataGrid;
import com.seeyoui.kensite.common.base.controller.BaseController;
import com.seeyoui.kensite.common.util.RequestResponseUtil;
import com.seeyoui.kensite.framework.plugin.skins.domain.Skins;
import com.seeyoui.kensite.framework.plugin.skins.service.SkinsService;
/**
 * @author cuichen
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping(value = "sys/skins")
public class SkinsController extends BaseController {
	
	@Autowired
	private SkinsService skinsService;
	
	/**
	 * 展示列表页面
	 * @param modelMap
	 * @param module
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("skins:view")
	@RequestMapping(value = "/{page}")
	public ModelAndView view(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, @PathVariable String page) throws Exception {
		return new ModelAndView("framework/plugin/skins/"+page, modelMap);
	}
	
	/**
	 * 获取列表展示数据
	 * @param modelMap
	 * @param skins
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("skins:select")
	@RequestMapping(value = "/list/data", method=RequestMethod.POST)
	@ResponseBody
	public Object listData(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, Skins skins) throws Exception{
		List<Skins> skinsList = skinsService.findList(skins);
		int total = skinsService.findTotal(skins);
		EasyUIDataGrid eudg = new EasyUIDataGrid();
		eudg.setRows(skinsList);
		eudg.setTotal(String.valueOf(total));
		return eudg;
	}
	
	/**
	 * 获取所有数据
	 * @param modelMap
	 * @param skins
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("skins:select")
	@RequestMapping(value = "/list/all", method=RequestMethod.POST)
	@ResponseBody
	public Object listAll(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, Skins skins) throws Exception{
		List<Skins> skinsList = skinsService.findAll(skins);
		return skinsList;
	}
	
	/**
	 * 保存新增的数据
	 * @param modelMap
	 * @param skins
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("skins:insert")
	@RequestMapping(value = "/save", method=RequestMethod.POST)
	@ResponseBody
	public String save(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, Skins skins) throws Exception{
		if (!beanValidator(modelMap, skins)){
			RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.FALSE);
			return null;
		}
		skinsService.save(skins);
		RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.TRUE);
		return null;
	}
	
	/**
	 * 保存修改的数据
	 * @param modelMap
	 * @param skins
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("skins:update")
	@RequestMapping(value = "/update", method=RequestMethod.POST)
	@ResponseBody
	public String update(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, Skins skins) throws Exception{
		if (!beanValidator(modelMap, skins)){
			RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.FALSE);
			return null;
		}
		skinsService.update(skins);
		RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.TRUE);
		return null;
	}
	
	/**
	 * 删除数据库
	 * @param modelMap
	 * @param skinsId
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("skins:delete")
	@RequestMapping(value = "/delete", method=RequestMethod.POST)
	@ResponseBody
	public String delete(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, String id) throws Exception {
		List<String> listId = Arrays.asList(id.split(","));
		skinsService.delete(listId);
		RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.TRUE);
		return null;
	}
	
	/**
	 * 选中
	 * @param modelMap
	 * @param skins
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("skins:update")
	@RequestMapping(value = "/chose", method=RequestMethod.POST)
	@ResponseBody
	public String chose(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, Skins skins) throws Exception{
		skinsService.chose(skins);
		RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.TRUE);
		return null;
	}
	
}
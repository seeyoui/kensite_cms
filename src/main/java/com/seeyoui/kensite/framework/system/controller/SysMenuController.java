/*
 * Powered By cuichen
 * Since 2014 - 2015
 */
package com.seeyoui.kensite.framework.system.controller;  
 
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.seeyoui.kensite.common.base.controller.BaseController;
import com.seeyoui.kensite.common.base.domain.EasyUIDataGrid;
import com.seeyoui.kensite.common.base.domain.TreeJson;
import com.seeyoui.kensite.common.constants.StringConstant;
import com.seeyoui.kensite.common.util.FileUtils;
import com.seeyoui.kensite.common.util.RequestResponseUtil;
import com.seeyoui.kensite.framework.plugin.skins.domain.Skins;
import com.seeyoui.kensite.framework.plugin.skins.service.SkinsService;
import com.seeyoui.kensite.framework.system.domain.SysMenu;
import com.seeyoui.kensite.framework.system.service.SysMenuService;
import com.seeyoui.kensite.framework.system.util.SkinsUtils;
/**
 * @author cuichen
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping(value = "sysMenu")
public class SysMenuController extends BaseController {
	
	@Autowired
	private SysMenuService sysMenuService;
	@Autowired
	private SkinsService skinsService;
	
	/**
	 * 展示列表页面
	 * @param modelMap
	 * @param module
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("sysMenu:view")
	@RequestMapping(value = "/{page}")
	public ModelAndView view(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, @PathVariable String page) throws Exception {
		Skins skin = SkinsUtils.getCurSysSkins();
		String skinsUrl = "";
    	if(skin!=null && !"".equals(skin.getUrl())) {
    		skinsUrl = skin.getUrl();
    	}
		modelMap.put("skinsUrl", skinsUrl);
		return new ModelAndView("framework/system/menu/"+page, modelMap);
	}
	
	/**
	 * 获取列表展示数据
	 * @param modelMap
	 * @param sysMenu
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("sysMenu:select")
	@RequestMapping(value = "/list/data", method=RequestMethod.POST)
	@ResponseBody
	public Object listData(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, SysMenu sysMenu) throws Exception{
		List<SysMenu> sysMenuList = sysMenuService.findList(sysMenu);
		int total = sysMenuService.findTotal(sysMenu);
		EasyUIDataGrid eudg = new EasyUIDataGrid();
		eudg.setRows(sysMenuList);
		eudg.setTotal(String.valueOf(total));
		return eudg;
	}
	
	/**
	 * 获取列表展示数据
	 * @param modelMap
	 * @param sysMenu
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("sysMenu:select")
	@RequestMapping(value = "/list/all", method=RequestMethod.POST)
	@ResponseBody
	public Object listAll(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, SysMenu sysMenu) throws Exception{
		List<SysMenu> sysMenuList = sysMenuService.findAll(sysMenu);
		return sysMenuList;
	}
	
	/**
	 * 获取模块TREE页面JSON数据
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("sysMenu:select")
	@RequestMapping(value = "/tree", method=RequestMethod.POST)
	@ResponseBody
	public Object tree(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, String username) throws Exception {
		List<TreeJson> tList = sysMenuService.tree();
		return tList;
	}
	
	/**
	 * 保存新增的数据
	 * @param modelMap
	 * @param sysMenu
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("sysMenu:insert")
	@RequestMapping(value = "/save", method=RequestMethod.POST)
	@ResponseBody
	public String save(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, SysMenu sysMenu) throws Exception{
		if (!beanValidator(modelMap, sysMenu)){
			RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.FALSE);
			return null;
		}
		sysMenuService.save(sysMenu);
		RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.TRUE);
		return null;
	}
	
	/**
	 * 保存修改的数据
	 * @param modelMap
	 * @param sysMenu
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("sysMenu:update")
	@RequestMapping(value = "/update", method=RequestMethod.POST)
	@ResponseBody
	public String update(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, SysMenu sysMenu) throws Exception{
		if (!beanValidator(modelMap, sysMenu)){
			RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.FALSE);
			return null;
		}
		sysMenuService.update(sysMenu);
		RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.TRUE);
		return null;
	}
	
	/**
	 * 删除数据库
	 * @param modelMap
	 * @param sysMenuId
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("sysMenu:delete")
	@RequestMapping(value = "/delete", method=RequestMethod.POST)
	@ResponseBody
	public String delete(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, String id) throws Exception {
		List<String> listId = Arrays.asList(id.split(","));
		sysMenuService.delete(listId);
		RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.TRUE);
		return null;
	}
	
	/**
	 * 展示页签页面
	 * @param modelMap
	 * @param module
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/tabs")
	public ModelAndView tabs(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, String url) throws Exception {
		modelMap.put("url", url);
		return new ModelAndView("framework/system/menu/tabs", modelMap);
	}
}
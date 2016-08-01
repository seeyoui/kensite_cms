/*
 * Powered By cuichen
 * Since 2014 - 2015
 */
package com.seeyoui.kensite.bussiness.oauth.interfaceCatalog.controller;  
 
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.seeyoui.kensite.bussiness.oauth.interfaceCatalog.domain.InterfaceCatalog;
import com.seeyoui.kensite.bussiness.oauth.interfaceCatalog.service.InterfaceCatalogService;
import com.seeyoui.kensite.common.base.controller.BaseController;
import com.seeyoui.kensite.common.base.domain.EasyUIDataGrid;
import com.seeyoui.kensite.common.constants.StringConstant;
import com.seeyoui.kensite.common.util.DateUtils;
import com.seeyoui.kensite.common.util.RequestResponseUtil;
import com.seeyoui.kensite.common.util.excel.ExportExcel;
/**
 * 接口目录
 * @author cuichen
 * @version 1.0
 * @since 1.0
 * @date 2015-12-09
 */
@Controller
@RequestMapping(value = "oauth/interfaceCatalog")
public class InterfaceCatalogController extends BaseController {
	
	@Autowired
	private InterfaceCatalogService interfaceCatalogService;
	
	/**
	 * 展示列表页面
	 * @param modelMap
	 * @param module
	 * @return
	 * @throws Exception
	 */
//	//@RequiresPermissions("oauth:interfaceCatalog:view")
	@RequestMapping(value = "/{page}")
	public ModelAndView view(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, @PathVariable String page) throws Exception {
		return new ModelAndView("bussiness/oauth/interfaceCatalog/"+page, modelMap);
	}
	
	/**
	 * 根据ID查询单条数据
	 * @param modelMap
	 * @param module
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/data/{id}")
	@ResponseBody
	public Object data(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, @PathVariable String id) throws Exception {
		InterfaceCatalog interfaceCatalog = interfaceCatalogService.findOne(id);
		return interfaceCatalog;
	}
	
	/**
	 * 获取列表展示数据
	 * @param modelMap
	 * @param interfaceCatalog
	 * @return
	 * @throws Exception
	 */
//	//@RequiresPermissions("oauth:interfaceCatalog:select")
	@RequestMapping(value = "/list/data", method=RequestMethod.POST)
	@ResponseBody
	public Object listData(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, InterfaceCatalog interfaceCatalog) throws Exception {
		List<InterfaceCatalog> interfaceCatalogList = interfaceCatalogService.findList(interfaceCatalog);
		int total = interfaceCatalogService.findTotal(interfaceCatalog);
		EasyUIDataGrid eudg = new EasyUIDataGrid();
		eudg.setTotal(String.valueOf(total));
		eudg.setRows(interfaceCatalogList);
		return eudg;
	}
	
	/**
	 * 获取所有数据
	 * @param modelMap
	 * @param interfaceCatalog
	 * @return
	 * @throws Exception
	 */
//	//@RequiresPermissions("oauth:interfaceCatalog:select")
	@RequestMapping(value = "/list/all", method=RequestMethod.POST)
	@ResponseBody
	public Object listAll(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, InterfaceCatalog interfaceCatalog) throws Exception {
		List<InterfaceCatalog> interfaceCatalogList = interfaceCatalogService.findAll(interfaceCatalog);
		return interfaceCatalogList;
	}
	
	/**
	 * 保存新增的数据
	 * @param modelMap
	 * @param interfaceCatalog
	 * @return
	 * @throws Exception
	 */
//	//@RequiresPermissions("oauth:interfaceCatalog:insert")
	@RequestMapping(value = "/save", method=RequestMethod.POST)
	@ResponseBody
	public String save(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, InterfaceCatalog interfaceCatalog) throws Exception {
		if (!beanValidator(modelMap, interfaceCatalog)){
			RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.FALSE);
			return null;
		}
		interfaceCatalogService.save(interfaceCatalog);
		RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.TRUE);
		return null;
	}
	
	/**
	 * 保存修改的数据
	 * @param modelMap
	 * @param interfaceCatalog
	 * @return
	 * @throws Exception
	 */
//	//@RequiresPermissions("oauth:interfaceCatalog:update")
	@RequestMapping(value = "/update", method=RequestMethod.POST)
	@ResponseBody
	public String update(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, InterfaceCatalog interfaceCatalog) throws Exception {
		if (!beanValidator(modelMap, interfaceCatalog)){
			RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.FALSE);
			return null;
		}
		interfaceCatalogService.update(interfaceCatalog);
		RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.TRUE);
		return null;
	}
	
	/**
	 * 删除数据库
	 * @param modelMap
	 * @param interfaceCatalogId
	 * @return
	 * @throws Exception
	 */
//	//@RequiresPermissions("oauth:interfaceCatalog:delete")
	@RequestMapping(value = "/delete", method=RequestMethod.POST)
	@ResponseBody
	public String delete(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, String id) throws Exception {
		List<String> listId = Arrays.asList(id.split(","));
		interfaceCatalogService.delete(listId);
		RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.TRUE);
		return null;
	}

	/**
	 * 导出Excel数据
	 * @param modelMap
	 * @param interfaceCatalog
	 * @return
	 * @throws Exception
	 */
//	//@RequiresPermissions("oauth:interfaceCatalog:export")
	@RequestMapping(value = "/export")
	public String export(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, InterfaceCatalog interfaceCatalog) throws Exception {
		String fileName = DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
		List<InterfaceCatalog> interfaceCatalogList = interfaceCatalogService.findAll(interfaceCatalog);
		new ExportExcel(null, InterfaceCatalog.class).setDataList(interfaceCatalogList).write(response, fileName).dispose();
		return null;
	}
}
/*
 * Powered By cuichen
 * Since 2014 - 2016
 */
package com.seeyoui.kensite.bussiness.demo.controller;

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

import com.seeyoui.kensite.bussiness.demo.domain.Demo;
import com.seeyoui.kensite.bussiness.demo.service.DemoService;
import com.seeyoui.kensite.common.base.controller.BaseController;
import com.seeyoui.kensite.common.base.domain.EasyUIDataGrid;
import com.seeyoui.kensite.common.constants.StringConstant;
import com.seeyoui.kensite.common.util.DateUtils;
import com.seeyoui.kensite.common.util.RequestResponseUtil;
import com.seeyoui.kensite.common.util.excel.ExportExcel;
import com.seeyoui.kensite.framework.system.domain.SysUser;
import com.seeyoui.kensite.framework.system.service.SysUserService;
/**
 * 演示
 * @author cuichen
 * @version 1.0
 * @since 1.0
 * @date 2016-06-12
 */
@Controller
@RequestMapping(value = "bussiness/demo")
public class DemoController extends BaseController {
	
	@Autowired
	private DemoService demoService;
	@Autowired
	private SysUserService sysUserService;
	
	/**
	 * 展示列表页面
	 * @param modelMap
	 * @param module
	 * @return
	 * @throws Exception
	 */
	//@RequiresPermissions("bussiness:demo:view")
	@RequestMapping(value = "/{page}")
	public ModelAndView view(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, @PathVariable String page) throws Exception {
		return new ModelAndView("bussiness/demo/"+page, modelMap);
	}
	
	/**
	 * 根据ID查询单条数据
	 * @param modelMap
	 * @param module
	 * @return
	 * @throws Exception
	 */
	//@RequiresPermissions("bussiness:demo:select")
	@RequestMapping(value = "/data/{id}")
	@ResponseBody
	public Object data(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, @PathVariable String id) throws Exception {
		Demo demo = demoService.findOne(id);
		return demo;
	}
	
	/**
	 * 获取列表展示数据
	 * @param modelMap
	 * @param demo
	 * @return
	 * @throws Exception
	 */
	//@RequiresPermissions("bussiness:demo:select")
	@RequestMapping(value = "/list/data", method=RequestMethod.POST)
	@ResponseBody
	public Object listData(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, Demo demo) throws Exception {
		List<Demo> demoList = demoService.findList(demo);
		int total = demoService.findTotal(demo);
		EasyUIDataGrid eudg = new EasyUIDataGrid();
		eudg.setTotal(String.valueOf(total));
		eudg.setRows(demoList);
		return eudg;
	}
	
	/**
	 * 获取所有数据
	 * @param modelMap
	 * @param demo
	 * @return
	 * @throws Exception
	 */
	//@RequiresPermissions("bussiness:demo:select")
	@RequestMapping(value = "/list/all", method=RequestMethod.POST)
	@ResponseBody
	public Object listAll(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, Demo demo) throws Exception {
		List<Demo> demoList = demoService.findAll(demo);
		return demoList;
	}
	
	/**
	 * 保存新增的数据
	 * @param modelMap
	 * @param demo
	 * @return
	 * @throws Exception
	 */
	//@RequiresPermissions("bussiness:demo:insert")
	@RequestMapping(value = "/save", method=RequestMethod.POST)
	@ResponseBody
	public String save(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, Demo demo) throws Exception {
		if (!beanValidator(modelMap, demo)){
			RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.FALSE);
			return null;
		}
		demoService.save(demo);
		RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.TRUE);
		return null;
	}
	
	/**
	 * 保存修改的数据
	 * @param modelMap
	 * @param demo
	 * @return
	 * @throws Exception
	 */
	//@RequiresPermissions("bussiness:demo:update")
	@RequestMapping(value = "/update", method=RequestMethod.POST)
	@ResponseBody
	public String update(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, Demo demo) throws Exception {
		if (!beanValidator(modelMap, demo)){
			RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.FALSE);
			return null;
		}
		demoService.update(demo);
		RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.TRUE);
		return null;
	}
	
	/**
	 * 删除数据库
	 * @param modelMap
	 * @param demoId
	 * @return
	 * @throws Exception
	 */
	//@RequiresPermissions("bussiness:demo:delete")
	@RequestMapping(value = "/delete", method=RequestMethod.POST)
	@ResponseBody
	public String delete(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, String id) throws Exception {
		List<String> listId = Arrays.asList(id.split(","));
		demoService.delete(listId);
		RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.TRUE);
		return null;
	}

	/**
	 * 导出Excel数据
	 * @param modelMap
	 * @param demo
	 * @return
	 * @throws Exception
	 */
	//@RequiresPermissions("bussiness:demo:export")
	@RequestMapping(value = "/export")
	public String export(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, Demo demo) throws Exception {
		String fileName = DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
		List<Demo> demoList = demoService.findAll(demo);
		new ExportExcel(null, Demo.class).setDataList(demoList).write(response, fileName).dispose();
		return null;
	}
	
	@RequestMapping(value = "/getUserIdByDepartmentId", method=RequestMethod.POST)
	@ResponseBody
	public Object getUserIdByDepartmentId(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, String departmentId) throws Exception {
		SysUser sysUser = new SysUser();
		sysUser.setDepartmentId(departmentId);
		List<SysUser> sysUserList = sysUserService.findAll(sysUser);
		return sysUserList;
	}
}
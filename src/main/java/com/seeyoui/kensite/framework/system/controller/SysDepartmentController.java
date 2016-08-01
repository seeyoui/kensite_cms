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
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.seeyoui.kensite.framework.system.domain.SysDepartment;
import com.seeyoui.kensite.framework.system.service.SysDepartmentService;
/**
 * @author cuichen
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping(value = "sysDepartment")
public class SysDepartmentController extends BaseController {
	
	@Autowired
	private SysDepartmentService sysDepartmentService;
	
	/**
	 * 展示列表页面
	 * @param modelMap
	 * @param module
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("sysDepartment:view")
	@RequestMapping(value = "/{page}")
	public ModelAndView showSysDepartmentPageList(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, @PathVariable String page) throws Exception {
		return new ModelAndView("framework/system/department/"+page, modelMap);
	}
	
	/**
	 * 获取列表展示数据
	 * @param modelMap
	 * @param sysDepartment
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("sysDepartment:select")
	@RequestMapping(value = "/list/data", method=RequestMethod.POST)
	@ResponseBody
	public Object findList(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, SysDepartment sysDepartment) throws Exception{
		List<SysDepartment> sysDepartmentList = sysDepartmentService.findList(sysDepartment);
		Integer total = sysDepartmentService.findTotal(sysDepartment);
		EasyUIDataGrid eudg = new EasyUIDataGrid();
		eudg.setRows(sysDepartmentList);
		eudg.setTotal(String.valueOf(total));
		return eudg;
	}
	
	/**
	 * 获取列表展示数据
	 * @param modelMap
	 * @param sysDepartment
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("sysDepartment:select")
	@RequestMapping(value = "/list/all", method=RequestMethod.POST)
	@ResponseBody
	public Object findAll(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, SysDepartment sysDepartment) throws Exception{
		List<SysDepartment> sysDepartmentList = sysDepartmentService.findAll(sysDepartment);
		return sysDepartmentList;
	}
	
	/**
	 * 获取模块TREE页面JSON数据
	 * @return
	 * @throws Exception
	 */
	@RequiresUser
	@RequestMapping(value = "/tree", method=RequestMethod.POST)
	@ResponseBody
	public Object tree() throws Exception {
		List<SysDepartment> mList = sysDepartmentService.findAll(null);
		List<TreeJson> tList = new ArrayList<TreeJson>();
		for(int i=0; i<mList.size(); i++) {
			TreeJson tj = new TreeJson();
			tj.setId(mList.get(i).getId());
			tj.setPid(mList.get(i).getParentId());
			tj.setText(mList.get(i).getName());
			tList.add(tj);
		}
		TreeJson root = new TreeJson();
		root.setId(StringConstant.ROOT_ID_32);
		root.setText("系统部门");
		TreeJson.getTree(tList, root);
		JSONArray jsonObj = JSONArray.fromObject(root);
		return jsonObj;
	}
	
	/**
	 * 保存新增的数据
	 * @param modelMap
	 * @param sysDepartment
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("sysDepartment:insert")
	@RequestMapping(value = "/save", method=RequestMethod.POST)
	@ResponseBody
	public String save(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, SysDepartment sysDepartment) throws Exception{
		if (!beanValidator(modelMap, sysDepartment)){
			RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.FALSE);
			return null;
		}
		sysDepartmentService.save(sysDepartment);
		RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.TRUE);
		return null;
	}
	
	/**
	 * 保存修改的数据
	 * @param modelMap
	 * @param sysDepartment
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("sysDepartment:update")
	@RequestMapping(value = "/update", method=RequestMethod.POST)
	@ResponseBody
	public String update(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, SysDepartment sysDepartment) throws Exception{
		if (!beanValidator(modelMap, sysDepartment)){
			RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.FALSE);
			return null;
		}
		sysDepartmentService.update(sysDepartment);
		RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.TRUE);
		return null;
	}
	
	/**
	 * 删除数据库
	 * @param modelMap
	 * @param sysDepartmentId
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("sysDepartment:delete")
	@RequestMapping(value = "/delete", method=RequestMethod.POST)
	@ResponseBody
	public String delete(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, String id) throws Exception {
		List<String> listId = Arrays.asList(id.split(","));
		sysDepartmentService.delete(listId);
		RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.TRUE);
		return null;
	}
}
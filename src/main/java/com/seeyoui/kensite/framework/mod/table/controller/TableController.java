/*
 * Powered By cuichen
 * Since 2014 - 2015
 */
package com.seeyoui.kensite.framework.mod.table.controller;  
 
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.seeyoui.kensite.common.base.controller.BaseController;
import com.seeyoui.kensite.common.base.domain.EasyUIDataGrid;
import com.seeyoui.kensite.common.constants.StringConstant;
import com.seeyoui.kensite.common.util.RequestResponseUtil;
import com.seeyoui.kensite.framework.mod.db.service.DBService;
import com.seeyoui.kensite.framework.mod.table.domain.Table;
import com.seeyoui.kensite.framework.mod.table.service.TableService;
/**
 * @author cuichen
 * @version 1.0
 * @since 1.0
 * @date 2015-09-22
 */
@Controller
@RequestMapping(value = "sys/table")
public class TableController extends BaseController {
	
	@Autowired
	private TableService tableService;
	
	@Autowired
	private DBService dbService;
	
	/**
	 * 获取列表展示数据
	 * @param modelMap
	 * @param table
	 * @return
	 * @throws Exception
	 */
	////@RequiresPermissions("sys:table:select")
	@RequestMapping(value = "/list/data", method=RequestMethod.POST)
	@ResponseBody
	public Object listData(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, Table table) throws Exception{
		List<Table> tableList = tableService.findList(table);
		int total = tableService.findTotal(table);
		EasyUIDataGrid eudg = new EasyUIDataGrid();
		eudg.setRows(tableList);
		eudg.setTotal(String.valueOf(total));
		return eudg;
	}
	
	/**
	 * 获取所有数据
	 * @param modelMap
	 * @param table
	 * @return
	 * @throws Exception
	 */
	////@RequiresPermissions("sys:table:select")
	@RequestMapping(value = "/list/all", method=RequestMethod.POST)
	@ResponseBody
	public Object listAll(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, Table table) throws Exception{
		List<Table> tableList = tableService.findAll(table);
		return tableList;
	}
	
	/**
	 * 保存新增的数据
	 * @param modelMap
	 * @param table
	 * @return
	 * @throws Exception
	 */
	////@RequiresPermissions("sys:table:insert")
	@RequestMapping(value = "/save", method=RequestMethod.POST)
	@ResponseBody
	public String save(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, Table table) throws Exception{
		if (!beanValidator(modelMap, table)){
			RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.FALSE);
			return null;
		}
		Table validTable = new Table();
		validTable.setName(table.getName());
		int tableNum = tableService.findTotal(validTable);
		if(tableNum != 0) {
			Map<String, String> messageMap = new HashMap<String, String>();
			messageMap.put("name", "数据表已存在");
			modelMap.put("message", messageMap);
			RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.FALSE);
			return null;
		}
		tableService.save(table);
		RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.TRUE);
		return null;
	}
	
	/**
	 * 保存修改的数据
	 * @param modelMap
	 * @param table
	 * @return
	 * @throws Exception
	 */
	//@RequiresPermissions("sys:table:update")
	@RequestMapping(value = "/update", method=RequestMethod.POST)
	@ResponseBody
	public String update(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, Table table) throws Exception{
		if (!beanValidator(modelMap, table)){
			RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.FALSE);
			return null;
		}
		tableService.update(table);
		RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.TRUE);
		return null;
	}
	
	/**
	 * 删除数据库
	 * @param modelMap
	 * @param tableId
	 * @return
	 * @throws Exception
	 */
	//@RequiresPermissions("sys:table:delete")
	@RequestMapping(value = "/delete", method=RequestMethod.POST)
	@ResponseBody
	public String delete(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, String id) throws Exception {
		List<String> listId = Arrays.asList(id.split(","));
		tableService.delete(listId);
		RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.TRUE);
		return null;
	}
}
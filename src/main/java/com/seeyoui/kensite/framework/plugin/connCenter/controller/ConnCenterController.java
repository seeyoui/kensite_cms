/*
 * Powered By cuichen
 * Since 2014 - 2016
 */
package com.seeyoui.kensite.framework.plugin.connCenter.controller;

import java.sql.Connection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.seeyoui.kensite.common.base.controller.BaseController;
import com.seeyoui.kensite.common.base.domain.EasyUIDataGrid;
import com.seeyoui.kensite.common.base.domain.Page;
import com.seeyoui.kensite.common.constants.StringConstant;
import com.seeyoui.kensite.common.util.DBUtils;
import com.seeyoui.kensite.common.util.DateUtils;
import com.seeyoui.kensite.common.util.RequestResponseUtil;
import com.seeyoui.kensite.common.util.excel.ExportExcel;
import com.seeyoui.kensite.framework.plugin.connCenter.domain.ConnCenter;
import com.seeyoui.kensite.framework.plugin.connCenter.service.ConnCenterService;
import com.seeyoui.kensite.framework.plugin.connCenter.util.CCUtils;
/**
 * 数据库连接中心
 * @author cuichen
 * @version 2.0
 * @since 1.0
 * @date 2016-09-27
 */

@Controller
@RequestMapping(value = "ks/connCenter")
public class ConnCenterController extends BaseController {
	
	@Autowired
	private ConnCenterService connCenterService;
	
	/**
	 * 展示列表页面
	 * @param modelMap
	 * @param module
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/{page}")
	public ModelAndView view(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, @PathVariable String page) throws Exception {
		return new ModelAndView("framework/plugin/connCenter/"+page, modelMap);
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
		ConnCenter connCenter = connCenterService.findOne(id);
		return connCenter;
	}
	
	/**
	 * 获取列表展示数据
	 * @param modelMap
	 * @param connCenter
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/list/data", method=RequestMethod.POST)
	@ResponseBody
	public Object listData(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, ConnCenter connCenter) throws Exception {
		List<ConnCenter> connCenterList = connCenterService.findList(connCenter);
		int total = connCenterService.findTotal(connCenter);
		EasyUIDataGrid eudg = new EasyUIDataGrid();
		eudg.setTotal(String.valueOf(total));
		eudg.setRows(connCenterList);
		return eudg;
	}
	
	/**
	 * 获取列表展示数据带分页器用于jsp自己做分页
	 * @param modelMap
	 * @param connCenter
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/list/page", method=RequestMethod.POST)
	@ResponseBody
	public Object listPage(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, ConnCenter connCenter) throws Exception {
		List<ConnCenter> connCenterList = connCenterService.findList(connCenter);
		int total = connCenterService.findTotal(connCenter);
		Page<ConnCenter> page = new Page<ConnCenter>(connCenter.getPage(), connCenter.getRows(), total);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("rows", connCenterList);
		dataMap.put("page", page);
		return dataMap;
	}
	
	/**
	 * 获取所有数据
	 * @param modelMap
	 * @param connCenter
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/list/all", method=RequestMethod.POST)
	@ResponseBody
	public Object listAll(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, ConnCenter connCenter) throws Exception {
		List<ConnCenter> connCenterList = connCenterService.findAll(connCenter);
		return connCenterList;
	}
	
	/**
	 * 保存新增的数据
	 * @param modelMap
	 * @param connCenter
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/save", method=RequestMethod.POST)
	@ResponseBody
	public String save(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, ConnCenter connCenter) throws Exception {
		if (!beanValidator(modelMap, connCenter)){
			RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.FALSE);
			return null;
		}
		connCenterService.save(connCenter);
		RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.TRUE);
		return null;
	}
	
	/**
	 * 保存修改的数据
	 * @param modelMap
	 * @param connCenter
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/update", method=RequestMethod.POST)
	@ResponseBody
	public String update(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, ConnCenter connCenter) throws Exception {
		if (!beanValidator(modelMap, connCenter)){
			RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.FALSE);
			return null;
		}
		connCenterService.update(connCenter);
		RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.TRUE);
		return null;
	}
	
	/**
	 * 删除数据库
	 * @param modelMap
	 * @param connCenterId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/delete", method=RequestMethod.POST)
	@ResponseBody
	public String delete(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, String id) throws Exception {
		List<String> listId = Arrays.asList(id.split(","));
		connCenterService.delete(listId);
		RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.TRUE);
		return null;
	}

	/**
	 * 导出Excel数据
	 * @param modelMap
	 * @param connCenter
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/export")
	public String export(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, ConnCenter connCenter) throws Exception {
		String fileName = DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
		List<ConnCenter> connCenterList = connCenterService.findAll(connCenter);
		new ExportExcel(null, ConnCenter.class).setDataList(connCenterList).write(response, fileName).dispose();
		return null;
	}
	
	/**
	 * 测试数据库连接
	 * @param modelMap
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/conn")
	@ResponseBody
	public String conn(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, String id) throws Exception {
		Connection conn = CCUtils.getConnection(id);
//		System.out.println(conn);
//		System.out.println(DBUtils.getString(conn, "select * from sys_user where user_name like 'system'", "name", true));
		if(conn == null) {
			RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.FALSE);
			return null;
		} else {
			DBUtils.close(conn, null, null);
			RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.TRUE);
			return null;
		}
	}
}
/*
 * Powered By cuichen
 * Since 2014 - 2017
 */
package com.seeyoui.kensite.framework.report.chartEngine.controller;

import java.util.ArrayList;
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
import com.seeyoui.kensite.common.util.StringUtils;
import com.seeyoui.kensite.common.util.excel.ExportExcel;
import com.seeyoui.kensite.framework.report.chartEngine.domain.ChartEngine;
import com.seeyoui.kensite.framework.report.chartEngine.service.ChartEngineService;
/**
 * 统计图表
 * @author cuichen
 * @version 2.0
 * @since 1.0
 * @date 2017-01-20
 */

@Controller
@RequestMapping(value = "ks/chartEngine")
public class ChartEngineController extends BaseController {
	
	@Autowired
	private ChartEngineService chartEngineService;
	
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
		return new ModelAndView("framework/report/chartEngine/"+page, modelMap);
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
		ChartEngine chartEngine = chartEngineService.findOne(id);
		return chartEngine;
	}
	
	/**
	 * 根据ID查询单条数据并返回相应表单
	 * @param modelMap
	 * @param module
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/form/{page}/{id}")
	public ModelAndView form(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, @PathVariable String page, @PathVariable String id) throws Exception {
		ChartEngine chartEngine = chartEngineService.findOne(id);
		modelMap.put("chartEngine", chartEngine);
		if("pie".equals(page) || "line".equals(page) || "bar".equals(page)) {
			String sql = "select view_name from user_views where view_name like 'REPORT%'";
			List<Map<Object, Object>> viewList = DBUtils.executeQuery(sql, true);
			modelMap.put("viewList", viewList);
			if(StringUtils.isNotBlank(chartEngine.getZsource())) {
				sql = "select column_name from user_tab_columns where table_name = '"+chartEngine.getZsource()+"'";
				List<Map<Object, Object>> zList = DBUtils.executeQuery(sql, true);
				modelMap.put("zList", zList);
			}
			if(StringUtils.isNotBlank(chartEngine.getXsource())) {
				sql = "select column_name from user_tab_columns where table_name = '"+chartEngine.getXsource()+"'";
				List<Map<Object, Object>> xList = DBUtils.executeQuery(sql, true);
				modelMap.put("xList", xList);
			}
			if(StringUtils.isNotBlank(chartEngine.getYsource())) {
				sql = "select column_name from user_tab_columns where table_name = '"+chartEngine.getYsource()+"'";
				List<Map<Object, Object>> yList = DBUtils.executeQuery(sql, true);
				modelMap.put("yList", yList);
			}
			sql = "select value,label from sys_dict where category='oracle_operation' order by sequence";
			List<Map<Object, Object>> yOperation = DBUtils.executeQuery(sql, true);
			modelMap.put("yOperation", yOperation);
		}
		return new ModelAndView("framework/report/chartEngine/"+page, modelMap);
	}
	
	/**
	 * 获取列表展示数据
	 * @param modelMap
	 * @param chartEngine
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/list/data", method=RequestMethod.POST)
	@ResponseBody
	public Object listData(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, ChartEngine chartEngine) throws Exception {
		List<ChartEngine> chartEngineList = chartEngineService.findList(chartEngine);
		int total = chartEngineService.findTotal(chartEngine);
		EasyUIDataGrid eudg = new EasyUIDataGrid();
		eudg.setTotal(String.valueOf(total));
		eudg.setRows(chartEngineList);
		return eudg;
	}
	
	/**
	 * 获取列表展示数据带分页器用于jsp自己做分页
	 * @param modelMap
	 * @param chartEngine
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/list/page", method=RequestMethod.POST)
	@ResponseBody
	public Object listPage(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, ChartEngine chartEngine) throws Exception {
		List<ChartEngine> chartEngineList = chartEngineService.findList(chartEngine);
		int total = chartEngineService.findTotal(chartEngine);
		Page<ChartEngine> page = new Page<ChartEngine>(chartEngine.getPage(), chartEngine.getRows(), total);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("rows", chartEngineList);
		dataMap.put("page", page);
		return dataMap;
	}
	
	/**
	 * 获取所有数据
	 * @param modelMap
	 * @param chartEngine
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/list/all", method=RequestMethod.POST)
	@ResponseBody
	public Object listAll(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, ChartEngine chartEngine) throws Exception {
		List<ChartEngine> chartEngineList = chartEngineService.findAll(chartEngine);
		return chartEngineList;
	}
	
	/**
	 * 保存新增的数据
	 * @param modelMap
	 * @param chartEngine
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/save", method=RequestMethod.POST)
	@ResponseBody
	public String save(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, ChartEngine chartEngine) throws Exception {
		if (!beanValidator(modelMap, chartEngine)){
			RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.FALSE);
			return null;
		}
		chartEngineService.save(chartEngine);
		RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.TRUE);
		return null;
	}
	
	/**
	 * 保存修改的数据
	 * @param modelMap
	 * @param chartEngine
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/update", method=RequestMethod.POST)
	@ResponseBody
	public String update(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, ChartEngine chartEngine) throws Exception {
		if (!beanValidator(modelMap, chartEngine)){
			RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.FALSE);
			return null;
		}
		chartEngineService.update(chartEngine);
		RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.TRUE);
		return null;
	}
	
	/**
	 * 删除数据库
	 * @param modelMap
	 * @param chartEngineId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/delete", method=RequestMethod.POST)
	@ResponseBody
	public String delete(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, String id) throws Exception {
		List<String> listId = Arrays.asList(id.split(","));
		chartEngineService.delete(listId);
		RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.TRUE);
		return null;
	}

	/**
	 * 导出Excel数据
	 * @param modelMap
	 * @param chartEngine
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/export")
	public String export(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, ChartEngine chartEngine) throws Exception {
		String fileName = DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
		List<ChartEngine> chartEngineList = chartEngineService.findAll(chartEngine);
		new ExportExcel(null, ChartEngine.class).setDataList(chartEngineList).write(response, fileName).dispose();
		return null;
	}
	
	/**
	 * 图表配置数据
	 * @param modelMap
	 * @param module
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/config")
	@ResponseBody
	public Object config(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, ChartEngine chartEngine) throws Exception {
		String type = chartEngine.getType();
		if(StringUtils.isNoneBlank(type) && "pie".equals(type)) {
			return chartEngineService.pie(chartEngine);
		}
		return null;
	}
	
	/**
	 * 图表配置数据
	 * @param modelMap
	 * @param module
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/viewCol")
	@ResponseBody
	public Object viewCol(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, String viewName) throws Exception {
		String sql = "select column_name from user_tab_columns where table_name = '"+viewName+"'";
		List<Map<Object, Object>> colList = DBUtils.executeQuery(sql, true);
		return colList;
	}
	
}
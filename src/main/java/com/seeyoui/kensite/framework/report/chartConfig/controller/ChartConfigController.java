/*
 * Powered By cuichen
 * Since 2014 - 2016
 */
package com.seeyoui.kensite.framework.report.chartConfig.controller;

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
import com.seeyoui.kensite.common.util.DateUtils;
import com.seeyoui.kensite.common.util.RequestResponseUtil;
import com.seeyoui.kensite.common.util.StringUtils;
import com.seeyoui.kensite.common.util.excel.ExportExcel;
import com.seeyoui.kensite.framework.report.chartConfig.domain.ChartConfig;
import com.seeyoui.kensite.framework.report.chartConfig.service.ChartConfigService;
/**
 * 统计图表
 * @author cuichen
 * @version 2.0
 * @since 1.0
 * @date 2016-09-05
 */

@Controller
@RequestMapping(value = "ks/chartConfig")
public class ChartConfigController extends BaseController {
	
	@Autowired
	private ChartConfigService chartConfigService;
	
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
		return new ModelAndView("framework/report/chartConfig/"+page, modelMap);
	}
	
	/**
	 * 展示配置页面
	 * @param modelMap
	 * @param module
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/config")
	public ModelAndView config(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, String id) throws Exception {
		ChartConfig chartConfig = null;
		if(StringUtils.isNotBlank(id)) {
			chartConfig = chartConfigService.findOne(id);
		} else {
			chartConfig = new ChartConfig();
		}
		modelMap.put("chartConfig", chartConfig);
		return new ModelAndView("framework/report/chartConfig/config", modelMap);
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
		ChartConfig chartConfig = chartConfigService.findOne(id);
		return chartConfig;
	}
	
	/**
	 * 获取列表展示数据
	 * @param modelMap
	 * @param chartConfig
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/list/data", method=RequestMethod.POST)
	@ResponseBody
	public Object listData(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, ChartConfig chartConfig) throws Exception {
		List<ChartConfig> chartConfigList = chartConfigService.findList(chartConfig);
		int total = chartConfigService.findTotal(chartConfig);
		EasyUIDataGrid eudg = new EasyUIDataGrid();
		eudg.setTotal(String.valueOf(total));
		eudg.setRows(chartConfigList);
		return eudg;
	}
	
	/**
	 * 获取列表展示数据带分页器用于jsp自己做分页
	 * @param modelMap
	 * @param chartConfig
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/list/page", method=RequestMethod.POST)
	@ResponseBody
	public Object listPage(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, ChartConfig chartConfig) throws Exception {
		List<ChartConfig> chartConfigList = chartConfigService.findList(chartConfig);
		int total = chartConfigService.findTotal(chartConfig);
		Page<ChartConfig> page = new Page<ChartConfig>(chartConfig.getPage(), chartConfig.getRows(), total);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("rows", chartConfigList);
		dataMap.put("page", page);
		return dataMap;
	}
	
	/**
	 * 获取所有数据
	 * @param modelMap
	 * @param chartConfig
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/list/all", method=RequestMethod.POST)
	@ResponseBody
	public Object listAll(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, ChartConfig chartConfig) throws Exception {
		List<ChartConfig> chartConfigList = chartConfigService.findAll(chartConfig);
		return chartConfigList;
	}
	
	/**
	 * 保存新增的数据
	 * @param modelMap
	 * @param chartConfig
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/save", method=RequestMethod.POST)
	@ResponseBody
	public String save(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, ChartConfig chartConfig) throws Exception {
		if (!beanValidator(modelMap, chartConfig)){
			RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.FALSE);
			return null;
		}
		ChartConfig chartConfigEx = new ChartConfig();
		chartConfigEx.setCodeNum(chartConfig.getCodeNum());
		int total = chartConfigService.findExTotal(chartConfigEx);
		if(total > 0) {
			Map<Object, Object> map = new HashMap<Object, Object>();
			map.put("codeNum", "标识已存在");
			modelMap.put("message", map);
			RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.FALSE);
			return null;
		}
		chartConfigService.save(chartConfig);
		RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.TRUE);
		return null;
	}
	
	/**
	 * 保存修改的数据
	 * @param modelMap
	 * @param chartConfig
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/update", method=RequestMethod.POST)
	@ResponseBody
	public String update(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, ChartConfig chartConfig) throws Exception {
		if (!beanValidator(modelMap, chartConfig)){
			RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.FALSE);
			return null;
		}
		ChartConfig chartConfigEx = new ChartConfig();
		chartConfigEx.setId(chartConfig.getId());
		chartConfigEx.setCodeNum(chartConfig.getCodeNum());
		int total = chartConfigService.findExTotal(chartConfigEx);
		if(total > 0) {
			Map<Object, Object> map = new HashMap<Object, Object>();
			map.put("codeNum", "标识已存在");
			modelMap.put("message", map);
			RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.FALSE);
			return null;
		}
		chartConfigService.update(chartConfig);
		RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.TRUE);
		return null;
	}
	
	/**
	 * 删除数据库
	 * @param modelMap
	 * @param chartConfigId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/delete", method=RequestMethod.POST)
	@ResponseBody
	public String delete(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, String id) throws Exception {
		List<String> listId = Arrays.asList(id.split(","));
		chartConfigService.delete(listId);
		RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.TRUE);
		return null;
	}

	/**
	 * 导出Excel数据
	 * @param modelMap
	 * @param chartConfig
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/export")
	public String export(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, ChartConfig chartConfig) throws Exception {
		String fileName = DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
		List<ChartConfig> chartConfigList = chartConfigService.findAll(chartConfig);
		new ExportExcel(null, ChartConfig.class).setDataList(chartConfigList).write(response, fileName).dispose();
		return null;
	}
}
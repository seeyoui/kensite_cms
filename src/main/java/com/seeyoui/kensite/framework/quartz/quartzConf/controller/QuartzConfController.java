/*
 * Powered By cuichen
 * Since 2014 - 2016
 */
package com.seeyoui.kensite.framework.quartz.quartzConf.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.quartz.CronExpression;
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
import com.seeyoui.kensite.common.util.DateUtils;
import com.seeyoui.kensite.common.util.RequestResponseUtil;
import com.seeyoui.kensite.common.util.excel.ExportExcel;
import com.seeyoui.kensite.framework.quartz.quartzConf.domain.QuartzConf;
import com.seeyoui.kensite.framework.quartz.quartzConf.service.QuartzConfService;
/**
 * 定时任务计划配置
 * @author cuichen
 * @version 1.0
 * @since 1.0
 * @date 2016-04-08
 */
@Controller
@RequestMapping(value = "quartz/quartzConf")
public class QuartzConfController extends BaseController {
	
	@Autowired
	private QuartzConfService quartzConfService;
	
	/**
	 * 展示列表页面
	 * @param modelMap
	 * @param module
	 * @return
	 * @throws Exception
	 */
	//@RequiresPermissions("quartz:quartzConf:view")
	@RequestMapping(value = "/{page}")
	public ModelAndView view(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, @PathVariable String page) throws Exception {
		return new ModelAndView("framework/quartz/quartzConf/"+page, modelMap);
	}
	
	/**
	 * 根据ID查询单条数据
	 * @param modelMap
	 * @param module
	 * @return
	 * @throws Exception
	 */
	//@RequiresPermissions("quartz:quartzConf:select")
	@RequestMapping(value = "/data/{id}")
	@ResponseBody
	public Object data(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, @PathVariable String id) throws Exception {
		QuartzConf quartzConf = quartzConfService.findOne(id);
		return quartzConf;
	}
	
	/**
	 * 获取列表展示数据
	 * @param modelMap
	 * @param quartzConf
	 * @return
	 * @throws Exception
	 */
	//@RequiresPermissions("quartz:quartzConf:select")
	@RequestMapping(value = "/list/data", method=RequestMethod.POST)
	@ResponseBody
	public Object listData(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, QuartzConf quartzConf) throws Exception {
		List<QuartzConf> quartzConfList = quartzConfService.findList(quartzConf);
		int total = quartzConfService.findTotal(quartzConf);
		EasyUIDataGrid eudg = new EasyUIDataGrid();
		eudg.setTotal(String.valueOf(total));
		eudg.setRows(quartzConfList);
		return eudg;
	}
	
	/**
	 * 获取所有数据
	 * @param modelMap
	 * @param quartzConf
	 * @return
	 * @throws Exception
	 */
	//@RequiresPermissions("quartz:quartzConf:select")
	@RequestMapping(value = "/list/all", method=RequestMethod.POST)
	@ResponseBody
	public Object listAll(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, QuartzConf quartzConf) throws Exception {
		List<QuartzConf> quartzConfList = quartzConfService.findAll(quartzConf);
		return quartzConfList;
	}
	
	/**
	 * 保存新增的数据
	 * @param modelMap
	 * @param quartzConf
	 * @return
	 * @throws Exception
	 */
	//@RequiresPermissions("quartz:quartzConf:insert")
	@RequestMapping(value = "/save", method=RequestMethod.POST)
	@ResponseBody
	public String save(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, QuartzConf quartzConf) throws Exception {
		if (!beanValidator(modelMap, quartzConf)){
			RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.FALSE);
			return null;
		}
		Class jobClass = null;
		try {
			jobClass = Class.forName(quartzConf.getJobClass());
		} catch (ClassNotFoundException e) {
			Map<Object, Object> map = new HashMap<Object, Object>();
			map.put("jobClass", "类未找到");
			modelMap.put("message", map);
			RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.FALSE);
			return null;
		}
		if(!CronExpression.isValidExpression(quartzConf.getCronExpression())) {
			Map<Object, Object> map = new HashMap<Object, Object>();
			map.put("cronExpression", "cron表达式不合法");
			modelMap.put("message", map);
			RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.FALSE);
			return null;
		}
		QuartzConf quartzConfValid = new QuartzConf();
		quartzConfValid.setJobName(quartzConf.getJobName());
		int total = quartzConfService.findTotal(quartzConfValid);
		if(total != 0) {
			Map<Object, Object> map = new HashMap<Object, Object>();
			map.put("jobName", "已存在");
			modelMap.put("message", map);
			RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.FALSE);
			return null;
		}
		quartzConfService.save(quartzConf);
		RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.TRUE);
		return null;
	}
	
	/**
	 * 保存修改的数据
	 * @param modelMap
	 * @param quartzConf
	 * @return
	 * @throws Exception
	 */
	//@RequiresPermissions("quartz:quartzConf:update")
	@RequestMapping(value = "/update", method=RequestMethod.POST)
	@ResponseBody
	public String update(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, QuartzConf quartzConf) throws Exception {
		if (!beanValidator(modelMap, quartzConf)){
			RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.FALSE);
			return null;
		}
		Class jobClass = null;
		try {
			jobClass = Class.forName(quartzConf.getJobClass());
		} catch (ClassNotFoundException e) {
			Map<Object, Object> map = new HashMap<Object, Object>();
			map.put("jobClass", "类未找到");
			modelMap.put("message", map);
			RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.FALSE);
			return null;
		}
		if(!CronExpression.isValidExpression(quartzConf.getCronExpression())) {
			Map<Object, Object> map = new HashMap<Object, Object>();
			map.put("cronExpression", "cron表达式不合法");
			modelMap.put("message", map);
			RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.FALSE);
			return null;
		}
		QuartzConf quartzConfValid = new QuartzConf();
		quartzConfValid.setJobName(quartzConf.getJobName());
		int total = quartzConfService.findTotal(quartzConf);
		if(total != 0) {
			Map<Object, Object> map = new HashMap<Object, Object>();
			map.put("jobName", "已存在");
			modelMap.put("message", map);
			RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.FALSE);
			return null;
		}
		quartzConfService.update(quartzConf);
		RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.TRUE);
		return null;
	}
	
	/**
	 * 删除数据库
	 * @param modelMap
	 * @param quartzConfId
	 * @return
	 * @throws Exception
	 */
	//@RequiresPermissions("quartz:quartzConf:delete")
	@RequestMapping(value = "/delete", method=RequestMethod.POST)
	@ResponseBody
	public String delete(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, String id) throws Exception {
		List<String> listId = Arrays.asList(id.split(","));
		quartzConfService.delete(listId);
		RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.TRUE);
		return null;
	}

	/**
	 * 导出Excel数据
	 * @param modelMap
	 * @param quartzConf
	 * @return
	 * @throws Exception
	 */
	//@RequiresPermissions("quartz:quartzConf:export")
	@RequestMapping(value = "/operate/export")
	public String export(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, QuartzConf quartzConf) throws Exception {
		String fileName = DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
		List<QuartzConf> quartzConfList = quartzConfService.findAll(quartzConf);
		new ExportExcel(null, QuartzConf.class).setDataList(quartzConfList).write(response, fileName).dispose();
		return null;
	}
	
	/**
	 * 暂停
	 * @param modelMap
	 * @param id
	 * @return
	 * @throws Exception
	 */
	//@RequiresPermissions("quartz:quartzConf:delete")
	@RequestMapping(value = "/pause", method=RequestMethod.POST)
	@ResponseBody
	public String pause(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, String id) throws Exception {
		quartzConfService.pause(id);
		RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.TRUE);
		return null;
	}
	
	/**
	 * 恢复
	 * @param modelMap
	 * @param id
	 * @return
	 * @throws Exception
	 */
	//@RequiresPermissions("quartz:quartzConf:delete")
	@RequestMapping(value = "/resume", method=RequestMethod.POST)
	@ResponseBody
	public String resume(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, String id) throws Exception {
		quartzConfService.resume(id);
		RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.TRUE);
		return null;
	}
	
	/**
	 * 运行一次
	 * @param modelMap
	 * @param id
	 * @return
	 * @throws Exception
	 */
	//@RequiresPermissions("quartz:quartzConf:delete")
	@RequestMapping(value = "/runOnce", method=RequestMethod.POST)
	@ResponseBody
	public String runOnce(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, String id) throws Exception {
		quartzConfService.runOnce(id);
		RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.TRUE);
		return null;
	}
}
/*
 * Powered By cuichen
 * Since 2014 - 2016
 */
package com.seeyoui.kensite.framework.cms.tagDesc.controller;

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

import com.seeyoui.kensite.common.util.DateUtils;
import com.seeyoui.kensite.common.constants.StringConstant;
import com.seeyoui.kensite.common.base.domain.EasyUIDataGrid;
import com.seeyoui.kensite.common.base.controller.BaseController;
import com.seeyoui.kensite.common.util.RequestResponseUtil;
import com.seeyoui.kensite.common.util.excel.ExportExcel;

import org.apache.lucene.index.Term;

import com.seeyoui.kensite.common.base.domain.Page;
import com.seeyoui.kensite.framework.luence.domain.LuceneDocument;
import com.seeyoui.kensite.framework.luence.util.LuceneUtils;

import com.seeyoui.kensite.framework.cms.tagDesc.domain.TagDesc;
import com.seeyoui.kensite.framework.cms.tagDesc.service.TagDescService;
/**
 * 标签描述
 * @author cuichen
 * @version 2.0
 * @since 1.0
 * @date 2016-09-24
 */

@Controller
@RequestMapping(value = "cms/tagDesc")
public class TagDescController extends BaseController {
	
	@Autowired
	private TagDescService tagDescService;
	
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
		return new ModelAndView("framework/cms/tagDesc/"+page, modelMap);
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
		TagDesc tagDesc = tagDescService.findOne(id);
		return tagDesc;
	}
	
	/**
	 * 获取列表展示数据
	 * @param modelMap
	 * @param tagDesc
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/list/data", method=RequestMethod.POST)
	@ResponseBody
	public Object listData(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, TagDesc tagDesc) throws Exception {
		List<TagDesc> tagDescList = tagDescService.findList(tagDesc);
		int total = tagDescService.findTotal(tagDesc);
		EasyUIDataGrid eudg = new EasyUIDataGrid();
		eudg.setTotal(String.valueOf(total));
		eudg.setRows(tagDescList);
		return eudg;
	}
	
	/**
	 * 获取列表展示数据带分页器用于jsp自己做分页
	 * @param modelMap
	 * @param tagDesc
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/list/page", method=RequestMethod.POST)
	@ResponseBody
	public Object listPage(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, TagDesc tagDesc) throws Exception {
		List<TagDesc> tagDescList = tagDescService.findList(tagDesc);
		int total = tagDescService.findTotal(tagDesc);
		Page<TagDesc> page = new Page<TagDesc>(tagDesc.getPage(), tagDesc.getRows(), total);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("rows", tagDescList);
		dataMap.put("page", page);
		return dataMap;
	}
	
	/**
	 * 获取所有数据
	 * @param modelMap
	 * @param tagDesc
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/list/all", method=RequestMethod.POST)
	@ResponseBody
	public Object listAll(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, TagDesc tagDesc) throws Exception {
		List<TagDesc> tagDescList = tagDescService.findAll(tagDesc);
		return tagDescList;
	}
	
	/**
	 * 保存新增的数据
	 * @param modelMap
	 * @param tagDesc
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/save", method=RequestMethod.POST)
	@ResponseBody
	public String save(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, TagDesc tagDesc) throws Exception {
		if (!beanValidator(modelMap, tagDesc)){
			RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.FALSE);
			return null;
		}
		tagDescService.save(tagDesc);
		RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.TRUE);
		return null;
	}
	
	/**
	 * 保存修改的数据
	 * @param modelMap
	 * @param tagDesc
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/update", method=RequestMethod.POST)
	@ResponseBody
	public String update(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, TagDesc tagDesc) throws Exception {
		if (!beanValidator(modelMap, tagDesc)){
			RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.FALSE);
			return null;
		}
		tagDescService.update(tagDesc);
		RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.TRUE);
		return null;
	}
	
	/**
	 * 删除数据库
	 * @param modelMap
	 * @param tagDescId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/delete", method=RequestMethod.POST)
	@ResponseBody
	public String delete(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, String id) throws Exception {
		List<String> listId = Arrays.asList(id.split(","));
		tagDescService.delete(listId);
		RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.TRUE);
		return null;
	}

	/**
	 * 导出Excel数据
	 * @param modelMap
	 * @param tagDesc
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/export")
	public String export(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, TagDesc tagDesc) throws Exception {
		String fileName = DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
		List<TagDesc> tagDescList = tagDescService.findAll(tagDesc);
		new ExportExcel(null, TagDesc.class).setDataList(tagDescList).write(response, fileName).dispose();
		return null;
	}
}
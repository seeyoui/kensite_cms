/*
 * Powered By cuichen
 * Since 2014 - 2016
 */
package com.seeyoui.kensite.framework.cms.site.controller;

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

import com.seeyoui.kensite.framework.luence.domain.LuceneDocument;
import com.seeyoui.kensite.framework.luence.util.LuceneUtils;
import com.seeyoui.kensite.framework.cms.site.domain.Site;
import com.seeyoui.kensite.framework.cms.site.service.SiteService;
/**
 * 内容发布站点
 * @author cuichen
 * @version 2.0
 * @since 1.0
 * @date 2016-07-15
 */

@Controller
@RequestMapping(value = "cms/site")
public class SiteController extends BaseController {
	
	@Autowired
	private SiteService siteService;
	
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
		return new ModelAndView("framework/cms/site/"+page, modelMap);
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
		Site site = siteService.findOne(id);
		return site;
	}
	
	/**
	 * 获取列表展示数据
	 * @param modelMap
	 * @param site
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/list/data", method=RequestMethod.POST)
	@ResponseBody
	public Object listData(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, Site site) throws Exception {
		List<Site> siteList = siteService.findList(site);
		int total = siteService.findTotal(site);
		EasyUIDataGrid eudg = new EasyUIDataGrid();
		eudg.setTotal(String.valueOf(total));
		eudg.setRows(siteList);
		return eudg;
	}
	
	/**
	 * 获取所有数据
	 * @param modelMap
	 * @param site
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/list/all", method=RequestMethod.POST)
	@ResponseBody
	public Object listAll(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, Site site) throws Exception {
		List<Site> siteList = siteService.findAll(site);
		return siteList;
	}
	
	/**
	 * 保存新增的数据
	 * @param modelMap
	 * @param site
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/save", method=RequestMethod.POST)
	@ResponseBody
	public String save(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, Site site) throws Exception {
		if (!beanValidator(modelMap, site)){
			RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.FALSE);
			return null;
		}
		Site siteExt = new Site();
		siteExt.setId(site.getId());
		siteExt.setDomain(site.getDomain());
		int total = siteService.findExTotal(siteExt);
		if(total > 0) {
			Map<Object, Object> map = new HashMap<Object, Object>();
			map.put("domain", "站点域名冲突");
			modelMap.put("message", map);
			RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.FALSE);
			return null;
		}
		siteService.save(site);
		RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.TRUE);
		return null;
	}
	
	/**
	 * 保存修改的数据
	 * @param modelMap
	 * @param site
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/update", method=RequestMethod.POST)
	@ResponseBody
	public String update(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, Site site) throws Exception {
		if (!beanValidator(modelMap, site)){
			RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.FALSE);
			return null;
		}
		Site siteExt = new Site();
		siteExt.setId(site.getId());
		siteExt.setDomain(site.getDomain());
		int total = siteService.findExTotal(siteExt);
		if(total > 0) {
			Map<Object, Object> map = new HashMap<Object, Object>();
			map.put("domain", "站点域名冲突");
			modelMap.put("message", map);
			RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.FALSE);
			return null;
		}
		siteService.update(site);
		RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.TRUE);
		return null;
	}
	
	/**
	 * 删除数据库
	 * @param modelMap
	 * @param siteId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/delete", method=RequestMethod.POST)
	@ResponseBody
	public String delete(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, String id) throws Exception {
		List<String> listId = Arrays.asList(id.split(","));
		siteService.delete(listId);
		RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.TRUE);
		return null;
	}

	/**
	 * 导出Excel数据
	 * @param modelMap
	 * @param site
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/export")
	public String export(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, Site site) throws Exception {
		String fileName = DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
		List<Site> siteList = siteService.findAll(site);
		new ExportExcel(null, Site.class).setDataList(siteList).write(response, fileName).dispose();
		return null;
	}
}
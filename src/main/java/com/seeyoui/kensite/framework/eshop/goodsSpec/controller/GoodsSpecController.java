/*
 * Powered By cuichen
 * Since 2014 - 2017
 */
package com.seeyoui.kensite.framework.eshop.goodsSpec.controller;

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

import com.seeyoui.kensite.framework.eshop.goodsSpec.domain.GoodsSpec;
import com.seeyoui.kensite.framework.eshop.goodsSpec.service.GoodsSpecService;
/**
 * 商品规格
 * @author cuichen
 * @version 2.0
 * @since 1.0
 * @date 2017-03-09
 */

@Controller
@RequestMapping(value = "es/goodsSpec")
public class GoodsSpecController extends BaseController {
	
	@Autowired
	private GoodsSpecService goodsSpecService;
	
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
		return new ModelAndView("framework/eshop/goodsSpec/"+page, modelMap);
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
		GoodsSpec goodsSpec = goodsSpecService.findOne(id);
		return goodsSpec;
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
		GoodsSpec goodsSpec = goodsSpecService.findOne(id);
		modelMap.put("goodsSpec", goodsSpec);
		return new ModelAndView("framework/eshop/goodsSpec/"+page, modelMap);
	}
	
	/**
	 * 获取列表展示数据
	 * @param modelMap
	 * @param goodsSpec
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/list/data", method=RequestMethod.POST)
	@ResponseBody
	public Object listData(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, GoodsSpec goodsSpec) throws Exception {
		List<GoodsSpec> goodsSpecList = goodsSpecService.findList(goodsSpec);
		int total = goodsSpecService.findTotal(goodsSpec);
		EasyUIDataGrid eudg = new EasyUIDataGrid();
		eudg.setTotal(String.valueOf(total));
		eudg.setRows(goodsSpecList);
		return eudg;
	}
	
	/**
	 * 获取列表展示数据带分页器用于jsp自己做分页
	 * @param modelMap
	 * @param goodsSpec
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/list/page", method=RequestMethod.POST)
	@ResponseBody
	public Object listPage(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, GoodsSpec goodsSpec) throws Exception {
		List<GoodsSpec> goodsSpecList = goodsSpecService.findList(goodsSpec);
		int total = goodsSpecService.findTotal(goodsSpec);
		Page<GoodsSpec> page = new Page<GoodsSpec>(goodsSpec.getPage(), goodsSpec.getRows(), total);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("rows", goodsSpecList);
		dataMap.put("page", page);
		return dataMap;
	}
	
	/**
	 * 获取所有数据
	 * @param modelMap
	 * @param goodsSpec
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/list/all", method=RequestMethod.POST)
	@ResponseBody
	public Object listAll(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, GoodsSpec goodsSpec) throws Exception {
		List<GoodsSpec> goodsSpecList = goodsSpecService.findAll(goodsSpec);
		return goodsSpecList;
	}
	
	/**
	 * 保存新增的数据
	 * @param modelMap
	 * @param goodsSpec
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/save", method=RequestMethod.POST)
	@ResponseBody
	public String save(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, GoodsSpec goodsSpec) throws Exception {
		if (!beanValidator(modelMap, goodsSpec)){
			RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.FALSE);
			return null;
		}
		goodsSpecService.save(goodsSpec);
		RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.TRUE);
		return null;
	}
	
	/**
	 * 保存修改的数据
	 * @param modelMap
	 * @param goodsSpec
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/update", method=RequestMethod.POST)
	@ResponseBody
	public String update(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, GoodsSpec goodsSpec) throws Exception {
		if (!beanValidator(modelMap, goodsSpec)){
			RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.FALSE);
			return null;
		}
		goodsSpecService.update(goodsSpec);
		RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.TRUE);
		return null;
	}
	
	/**
	 * 删除数据库
	 * @param modelMap
	 * @param goodsSpecId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/delete", method=RequestMethod.POST)
	@ResponseBody
	public String delete(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, String id) throws Exception {
		List<String> listId = Arrays.asList(id.split(","));
		goodsSpecService.delete(listId);
		RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.TRUE);
		return null;
	}
	
	/**
	 * 假删除
	 * @param modelMap
	 * @param goodsSpec
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/remove", method=RequestMethod.POST)
	@ResponseBody
	public String remove(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, GoodsSpec goodsSpec) throws Exception {
		goodsSpecService.remove(goodsSpec);
		RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.TRUE);
		return null;
	}

	/**
	 * 导出Excel数据
	 * @param modelMap
	 * @param goodsSpec
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/export")
	public String export(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, GoodsSpec goodsSpec) throws Exception {
		String fileName = DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
		List<GoodsSpec> goodsSpecList = goodsSpecService.findAll(goodsSpec);
		new ExportExcel(null, GoodsSpec.class).setDataList(goodsSpecList).write(response, fileName).dispose();
		return null;
	}
}
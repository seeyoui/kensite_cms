/*
 * Powered By cuichen
 * Since 2014 - 2016
 */
package com.seeyoui.kensite.framework.cms.category.controller;

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

import com.seeyoui.kensite.common.base.controller.BaseController;
import com.seeyoui.kensite.common.base.domain.EasyUIDataGrid;
import com.seeyoui.kensite.common.constants.StringConstant;
import com.seeyoui.kensite.common.util.DateUtils;
import com.seeyoui.kensite.common.util.RequestResponseUtil;
import com.seeyoui.kensite.common.util.excel.ExportExcel;
import com.seeyoui.kensite.framework.cms.article.domain.Article;
import com.seeyoui.kensite.framework.cms.article.service.ArticleService;
import com.seeyoui.kensite.framework.cms.category.domain.Category;
import com.seeyoui.kensite.framework.cms.category.service.CategoryService;
/**
 * 内容发布栏目
 * @author cuichen
 * @version 2.0
 * @since 1.0
 * @date 2016-07-15
 */

@Controller
@RequestMapping(value = "cms/category")
public class CategoryController extends BaseController {
	
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ArticleService articleService;
	
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
		return new ModelAndView("framework/cms/category/"+page, modelMap);
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
		Category category = categoryService.findOne(id);
		return category;
	}
	
	/**
	 * 获取列表展示数据
	 * @param modelMap
	 * @param category
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/list/data", method=RequestMethod.POST)
	@ResponseBody
	public Object listData(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, Category category) throws Exception {
		List<Category> categoryList = categoryService.findList(category);
		int total = categoryService.findTotal(category);
		EasyUIDataGrid eudg = new EasyUIDataGrid();
		eudg.setTotal(String.valueOf(total));
		eudg.setRows(categoryList);
		return eudg;
	}
	
	/**
	 * 获取所有数据
	 * @param modelMap
	 * @param category
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/list/all", method=RequestMethod.POST)
	@ResponseBody
	public Object listAll(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, Category category) throws Exception {
		List<Category> categoryList = categoryService.findAll(category);
		return categoryList;
	}
	
	/**
	 * 获取所有数据
	 * @param modelMap
	 * @param category
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/list/tree", method=RequestMethod.POST)
	@ResponseBody
	public Object listTree(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, Category category) throws Exception {
		List<Category> categoryList = categoryService.findAll(category);
		Category cate = new Category();
		cate.setId(StringConstant.ROOT_ID_32);
		cate.setName("栏目");
		categoryList.add(cate);
		return categoryList;
	}
	
	/**
	 * 保存新增的数据
	 * @param modelMap
	 * @param category
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/save", method=RequestMethod.POST)
	@ResponseBody
	public String save(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, Category category) throws Exception {
		if (!beanValidator(modelMap, category)){
			RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.FALSE);
			return null;
		}
		categoryService.save(category);
		RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.TRUE);
		return null;
	}
	
	/**
	 * 保存修改的数据
	 * @param modelMap
	 * @param category
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/update", method=RequestMethod.POST)
	@ResponseBody
	public String update(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, Category category) throws Exception {
		if (!beanValidator(modelMap, category)){
			RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.FALSE);
			return null;
		}
		categoryService.update(category);
		RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.TRUE);
		return null;
	}
	
	/**
	 * 删除数据库
	 * @param modelMap
	 * @param categoryId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/delete", method=RequestMethod.POST)
	@ResponseBody
	public String delete(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, String id) throws Exception {
		List<String> listId = Arrays.asList(id.split(","));
		for(String pid : listId) {
			Category category = new Category();
			category.setParentId(pid);
			int cou = categoryService.findTotal(category);
			if(cou != 0) {
				RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.FALSE);
				return null;
			}
			Article article = new Article();
			article.setCategoryId(pid);
			cou = articleService.findTotal(article);
			if(cou != 0) {
				RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.FALSE);
				return null;
			}
		}
		categoryService.delete(listId);
		RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.TRUE);
		return null;
	}

	/**
	 * 导出Excel数据
	 * @param modelMap
	 * @param category
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/export")
	public String export(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, Category category) throws Exception {
		String fileName = DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
		List<Category> categoryList = categoryService.findAll(category);
		new ExportExcel(null, Category.class).setDataList(categoryList).write(response, fileName).dispose();
		return null;
	}
}
<#include "/custom.include">
<#include "/java_copyright.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
<#assign module = table.className[3..]?uncap_first>

package ${basepackage}.${innerpackage}.${table.classNameFirstLower}.controller;

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

import ${basepackage}.common.util.DateUtils;
import ${basepackage}.common.constants.StringConstant;
import ${basepackage}.common.base.domain.EasyUIDataGrid;
import ${basepackage}.common.base.controller.BaseController;
import ${basepackage}.common.util.RequestResponseUtil;
import ${basepackage}.common.util.excel.ExportExcel;

import org.apache.lucene.index.Term;

import com.seeyoui.kensite.common.base.domain.Page;
import com.seeyoui.kensite.framework.luence.domain.LuceneDocument;
import com.seeyoui.kensite.framework.luence.util.LuceneUtils;

import ${basepackage}.${innerpackage}.${table.classNameFirstLower}.domain.${className};
import ${basepackage}.${innerpackage}.${table.classNameFirstLower}.service.${className}Service;
<#include "/java_imports.include">

@Controller
@RequestMapping(value = "${moduleC}${table.classNameFirstLower}")
public class ${className}Controller extends BaseController {
	
	@Autowired
	private ${className}Service ${table.classNameFirstLower}Service;
	
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
		return new ModelAndView("${innerpackage_dir}/${table.classNameFirstLower}/"+page, modelMap);
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
		${className} ${table.classNameFirstLower} = ${table.classNameFirstLower}Service.findOne(id);
		return ${table.classNameFirstLower};
	}
	
	/**
	 * 获取列表展示数据
	 * @param modelMap
	 * @param ${table.classNameFirstLower}
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/list/data", method=RequestMethod.POST)
	@ResponseBody
	public Object listData(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, ${className} ${table.classNameFirstLower}) throws Exception {
		List<${className}> ${table.classNameFirstLower}List = ${table.classNameFirstLower}Service.findList(${table.classNameFirstLower});
		int total = ${table.classNameFirstLower}Service.findTotal(${table.classNameFirstLower});
		EasyUIDataGrid eudg = new EasyUIDataGrid();
		eudg.setTotal(String.valueOf(total));
		eudg.setRows(${table.classNameFirstLower}List);
		return eudg;
	}
	
	/**
	 * 获取列表展示数据带分页器用于jsp自己做分页
	 * @param modelMap
	 * @param ${table.classNameFirstLower}
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/list/page", method=RequestMethod.POST)
	@ResponseBody
	public Object listPage(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, ${className} ${table.classNameFirstLower}) throws Exception {
		List<${className}> ${table.classNameFirstLower}List = ${table.classNameFirstLower}Service.findList(${table.classNameFirstLower});
		int total = ${table.classNameFirstLower}Service.findTotal(${table.classNameFirstLower});
		Page<${className}> page = new Page<${className}>(${table.classNameFirstLower}.getPage(), ${table.classNameFirstLower}.getRows(), total);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("rows", ${table.classNameFirstLower}List);
		dataMap.put("page", page);
		return dataMap;
	}
	
	/**
	 * 获取所有数据
	 * @param modelMap
	 * @param ${table.classNameFirstLower}
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/list/all", method=RequestMethod.POST)
	@ResponseBody
	public Object listAll(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, ${className} ${table.classNameFirstLower}) throws Exception {
		List<${className}> ${table.classNameFirstLower}List = ${table.classNameFirstLower}Service.findAll(${table.classNameFirstLower});
		return ${table.classNameFirstLower}List;
	}
	
	/**
	 * 保存新增的数据
	 * @param modelMap
	 * @param ${table.classNameFirstLower}
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/save", method=RequestMethod.POST)
	@ResponseBody
	public String save(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, ${className} ${table.classNameFirstLower}) throws Exception {
		if (!beanValidator(modelMap, ${table.classNameFirstLower})){
			RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.FALSE);
			return null;
		}
		${table.classNameFirstLower}Service.save(${table.classNameFirstLower});
		<#if (lucene=="Y") >
		String luceneUrl = session.getServletContext().getRealPath("/")+StringConstant.LUCENE_INDEX_URL+"${table.classNameFirstLower}";
		LuceneDocument document = new LuceneDocument();
		document.setId(${table.classNameFirstLower}.getId());
		document.setContent(""
		<#list table.columns as column>
		<#if (column.columnName?lower_case!="id"&&column.columnName?lower_case!="createuser"&&column.columnName?lower_case!="createdate"&&column.columnName?lower_case!="updateuser"&&column.columnName?lower_case!="updatedate"&&column.columnName?lower_case!="remarks"&&column.columnName?lower_case!="delflag") >
			+","+${table.classNameFirstLower}.get${column.columnName}()
		</#if>
		</#list>
		);
		LuceneUtils.insert(luceneUrl, document);
		</#if>
		RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.TRUE);
		return null;
	}
	
	/**
	 * 保存修改的数据
	 * @param modelMap
	 * @param ${table.classNameFirstLower}
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/update", method=RequestMethod.POST)
	@ResponseBody
	public String update(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, ${className} ${table.classNameFirstLower}) throws Exception {
		if (!beanValidator(modelMap, ${table.classNameFirstLower})){
			RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.FALSE);
			return null;
		}
		${table.classNameFirstLower}Service.update(${table.classNameFirstLower});
		<#if (lucene=="Y") >
		String luceneUrl = session.getServletContext().getRealPath("/")+StringConstant.LUCENE_INDEX_URL+"${table.classNameFirstLower}";
		LuceneDocument document = new LuceneDocument();
		document.setId(${table.classNameFirstLower}.getId());
		document.setContent(""
		<#list table.columns as column>
		<#if (column.columnName?lower_case!="id"&&column.columnName?lower_case!="createuser"&&column.columnName?lower_case!="createdate"&&column.columnName?lower_case!="updateuser"&&column.columnName?lower_case!="updatedate"&&column.columnName?lower_case!="remarks"&&column.columnName?lower_case!="delflag") >
			+","+${table.classNameFirstLower}.get${column.columnName}()
		</#if>
		</#list>
		);
		Term term = new Term("id", ${table.classNameFirstLower}.getId());
		LuceneUtils.update(luceneUrl, term, document);
		</#if>
		RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.TRUE);
		return null;
	}
	
	/**
	 * 删除数据库
	 * @param modelMap
	 * @param ${table.classNameFirstLower}Id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/delete", method=RequestMethod.POST)
	@ResponseBody
	public String delete(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, String id) throws Exception {
		List<String> listId = Arrays.asList(id.split(","));
		${table.classNameFirstLower}Service.delete(listId);
		<#if (lucene=="Y") >
		String luceneUrl = session.getServletContext().getRealPath("/")+StringConstant.LUCENE_INDEX_URL+"${table.classNameFirstLower}";
		for(String key : listId) {
			Term term = new Term("id", key);
			LuceneUtils.delete(luceneUrl, term);
		}
		</#if>
		RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.TRUE);
		return null;
	}

	/**
	 * 导出Excel数据
	 * @param modelMap
	 * @param ${table.classNameFirstLower}
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/export")
	public String export(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, ${className} ${table.classNameFirstLower}) throws Exception {
		String fileName = DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
		List<${className}> ${table.classNameFirstLower}List = ${table.classNameFirstLower}Service.findAll(${table.classNameFirstLower});
		new ExportExcel(null, ${className}.class).setDataList(${table.classNameFirstLower}List).write(response, fileName).dispose();
		return null;
	}
	<#if (lucene=="Y") >
	/**
	 * 全文检索分页
	 * @param session
	 * @param response
	 * @param request
	 * @param modelMap
	 * @param searchStr
	 * @param page
	 * @param rows
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/lucene/data")
	@ResponseBody
	public Object luceneData(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, String searchStr, int page, int rows) throws Exception {
		String luceneUrl = session.getServletContext().getRealPath("/")+StringConstant.LUCENE_INDEX_URL+"${table.classNameFirstLower}";
		List<String> listId = LuceneUtils.search(luceneUrl, searchStr, page, rows);
		List<${className}> ${table.classNameFirstLower}List = ${table.classNameFirstLower}Service.findLucene(listId);
		return ${table.classNameFirstLower}List;
	}

	/**
	 * 全文检索
	 * @param session
	 * @param response
	 * @param request
	 * @param modelMap
	 * @param searchStr
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/lucene/all")
	@ResponseBody
	public Object luceneAll(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, String searchStr) throws Exception {
		String luceneUrl = session.getServletContext().getRealPath("/")+StringConstant.LUCENE_INDEX_URL+"${table.classNameFirstLower}";
		List<String> listId = LuceneUtils.search(luceneUrl, searchStr, 1, Integer.MAX_VALUE);
		List<${className}> ${table.classNameFirstLower}List = ${table.classNameFirstLower}Service.findLucene(listId);
		return ${table.classNameFirstLower}List;
	}
	
	/**
	 * 全文检索清空索引
	 * @param session
	 * @param response
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/lucene/clean")
	@ResponseBody
	public Object luceneClean(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap) throws Exception {
		String luceneUrl = session.getServletContext().getRealPath("/")+StringConstant.LUCENE_INDEX_URL+"${table.classNameFirstLower}";
		LuceneUtils.clean(luceneUrl);
		RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.TRUE);
		return null;
	}
	
	/**
	 * 
	 * @param session
	 * @param response
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/lucene/create")
	@ResponseBody
	public Object luceneCreate(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap) throws Exception {
		String luceneUrl = session.getServletContext().getRealPath("/")+StringConstant.LUCENE_INDEX_URL+"${table.classNameFirstLower}";
		List<LuceneDocument> documentList = new ArrayList<LuceneDocument>();
		List<${className}> ${table.classNameFirstLower}List = ${table.classNameFirstLower}Service.findAll(new ${className}());
		for(${className} ${table.classNameFirstLower} : ${table.classNameFirstLower}List) {
			LuceneDocument document = new LuceneDocument();
			document.setId(${table.classNameFirstLower}.getId());
			document.setContent(""
			<#list table.columns as column>
			<#if (column.columnName?lower_case!="id"&&column.columnName?lower_case!="createuser"&&column.columnName?lower_case!="createdate"&&column.columnName?lower_case!="updateuser"&&column.columnName?lower_case!="updatedate"&&column.columnName?lower_case!="remarks"&&column.columnName?lower_case!="delflag") >
				+","+${table.classNameFirstLower}.get${column.columnName}()
			</#if>
			</#list>
			);
			documentList.add(document);
		}
		LuceneUtils.create(luceneUrl, documentList);
		RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.TRUE);
		return null;
	}
	</#if>
}
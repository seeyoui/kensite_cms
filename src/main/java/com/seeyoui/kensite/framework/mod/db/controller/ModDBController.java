/*
 * Powered By cuichen
 * Since 2014 - 2015
 */
package com.seeyoui.kensite.framework.mod.db.controller;  
 
import java.util.ArrayList;
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
import org.springframework.web.servlet.ModelAndView;

import com.seeyoui.kensite.common.base.controller.BaseController;
import com.seeyoui.kensite.common.util.DateUtils;
import com.seeyoui.kensite.common.util.StringUtils;
import com.seeyoui.kensite.common.util.word.ExportWord;
import com.seeyoui.kensite.framework.mod.table.domain.Table;
import com.seeyoui.kensite.framework.mod.table.service.TableService;
import com.seeyoui.kensite.framework.mod.tableColumn.domain.TableColumn;
import com.seeyoui.kensite.framework.mod.tableColumn.service.TableColumnService;
/**
 * @author cuichen
 * @version 1.0
 * @since 1.0
 * @date 2015-09-22
 */
@Controller
@RequestMapping(value = "sys/mod/db")
public class ModDBController extends BaseController {
	
	@Autowired
	private TableService tableService;
	@Autowired
	private TableColumnService tableColumnService;
	
	/**
	 * 展示列表页面
	 * @param modelMap
	 * @param module
	 * @return
	 * @throws Exception
	 */
//	@RequiresPermissions("sys:db:view")
	@RequestMapping(value = "/{page}")
	public ModelAndView showTablePageList(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, @PathVariable String page, String tableName) throws Exception {
		modelMap.put("tableName", tableName);
		return new ModelAndView("framework/mod/db/"+page, modelMap);
	}
	
	/**
	 * 按照sql导出excel
	 * @param session
	 * @param response
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/export")
	public String export(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, String id) throws Exception {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		Table table = tableService.findOne(id);
		if(table == null) {
			return "表不存在";
		}
		dataMap.put("tableName", table.getName());
		dataMap.put("tableComments", table.getComments());
		TableColumn tableColumn = new TableColumn();
		tableColumn.setTableName(table.getName());
		List<TableColumn> tableColumnList = tableColumnService.findAll(tableColumn);
		List<Map<String, Object>> columnList = new ArrayList<Map<String, Object>>();
		for(int i=0; i<tableColumnList.size(); i++) {
			Map<String, Object> col = new HashMap<String, Object>();
			col.put("columnName", tableColumnList.get(i).getName());
			String dataType = "";
			dataType += tableColumnList.get(i).getJdbcType();
			if(StringUtils.isNoneBlank(tableColumnList.get(i).getJdbcLength())) {
				dataType += "(" + tableColumnList.get(i).getJdbcLength() + ")";
			}
			col.put("dataType", dataType);
			col.put("nullable", tableColumnList.get(i).getIsNull());
			col.put("tableComments", tableColumnList.get(i).getComments());
			columnList.add(col);
		}
		dataMap.put("columnList", columnList);
		
		String fileName = table.getName()+".doc";
		new ExportWord(dataMap, "table.ftl").write(response, fileName);
		return null;
	}
}
/*
 * Powered By cuichen
 * Since 2014 - 2016
 */
package com.seeyoui.kensite.framework.report.ksReport.controller;

import java.util.ArrayList;
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
import com.seeyoui.kensite.common.util.DBUtils;
import com.seeyoui.kensite.common.util.StringUtils;
import com.seeyoui.kensite.framework.report.ksReport.domain.KSReportBorder;
import com.seeyoui.kensite.framework.report.ksReport.domain.KSReportCell;
import com.seeyoui.kensite.framework.report.ksReport.domain.KSReportStyle;
import com.seeyoui.kensite.framework.report.ksReport.service.KSReportService;
import com.seeyoui.kensite.framework.report.ksReport.util.KSReportUtils;
/**
 * KS报表
 * @author cuichen
 * @version 1.0
 * @since 1.0
 * @date 2016-05-20
 */
@Controller
@RequestMapping(value = "ks/report")
public class KSReportController extends BaseController {
	
	@Autowired
	private KSReportService ksReportService;
	
	/**
	 * 展示报表页面
	 * @param modelMap
	 * @param module
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/{page}")
	public ModelAndView view(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, @PathVariable String page) throws Exception {
		return new ModelAndView("framework/report/ksReport/"+page, modelMap);
	}
	
	/**
	 * 获取报表Table数据集合
	 * @param session
	 * @param response
	 * @param request
	 * @param modelMap
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public Object getReportTableData(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, String sql) throws Exception {
		return DBUtils.executeQuery(sql, false);
	}
	
	/**
	 * 展示报表页面
	 * @param modelMap
	 * @param module
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/show")
	public ModelAndView show(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap) throws Exception {
		KSReportCell ksReportCell = new KSReportCell();
		ksReportCell.setRow(1);
		ksReportCell.setCol(1);
		ksReportCell.setWidth(33);
		ksReportCell.setHeight(33);
		ksReportCell.setDirection(1);
		ksReportCell.setValue("");
		KSReportStyle ksReportStyle = new KSReportStyle();
		ksReportStyle.setBackColor("#00ff51");
		ksReportStyle.setBackgroundImage("/kensite/static/exceldesign/img/field/sqlReport.png");
		ksReportStyle.setBackgroundImageLayout(3);
		ksReportStyle.setFont("italic bold 22pt YouYuan");
		ksReportStyle.setForeColor("#f700f7");
		ksReportStyle.sethAlign(1);
		ksReportStyle.setvAlign(1);
		ksReportStyle.setWordWrap(true);
		
		KSReportBorder border = new KSReportBorder();
		border.setColor("#000000");
		border.setStyle(1);
		ksReportStyle.setBorderTop(border);
		ksReportStyle.setBorderBottom(border);
		ksReportStyle.setBorderLeft(border);
		ksReportStyle.setBorderRight(border);
		ksReportCell.setStyle(ksReportStyle);
		
		StringBuffer ksReport = new StringBuffer();
		String sql = "select name from sys_user";
		
		int startRow = ksReportCell.getRow();
		int startCol = ksReportCell.getCol();
		double width = ksReportCell.getWidth();
		double height = ksReportCell.getHeight();
		
//		KSReportStyle ksReportStyle = ksReportCell.getStyle();
		if(ksReportStyle != null) {
			ksReport.append("var style = new $.wijmo.wijspread.Style();");
			String backColor = ksReportStyle.getBackColor();
			if(StringUtils.isNoneBlank(backColor)) {
				ksReport.append("style.backColor = '"+backColor+"';");
			}
			int hAlign = ksReportStyle.gethAlign();
			ksReport.append("style.hAlign = "+hAlign+";");
			int vAlign = ksReportStyle.getvAlign();
			ksReport.append("style.vAlign = "+vAlign+";");
			String font = ksReportStyle.getFont();
			if(StringUtils.isNoneBlank(font)) {
				ksReport.append("style.font = '"+font+"';");
			}
			String foreColor = ksReportStyle.getForeColor();
			if(StringUtils.isNoneBlank(foreColor)) {
				ksReport.append("style.foreColor = '"+foreColor+"';");
			}
			String backgroundImage = ksReportStyle.getBackgroundImage();
			int backgroundImageLayout = ksReportStyle.getBackgroundImageLayout();
			if(StringUtils.isNoneBlank(backgroundImage)) {
				ksReport.append("style.backgroundImage = '"+backgroundImage+"';");
				ksReport.append("style.backgroundImageLayout = "+backgroundImageLayout+";");
			}
			boolean wordWrap = ksReportStyle.isWordWrap();
			ksReport.append("style.wordWrap = "+wordWrap+";");
		}
		if(ksReportCell.getDirection() == 1) {
			ksReport.append("sheet.setRowHeight("+startRow+", "+height+"); \n");
		} else if(ksReportCell.getDirection() == 2) {
			ksReport.append("sheet.setColumnWidth("+startCol+", "+width+");");
		}
		int dataIndex = 0;
		List<Map<Object, Object>> result = DBUtils.executeQuery(sql, false);
		for(Map<Object, Object> map : result) {
			if(ksReportCell.getDirection() == 1) {
				ksReport.append("sheet.setValue("+startRow+", "+(startCol+dataIndex)+", \""+map.get("NAME").toString()+"\"); \n");
				ksReport.append("sheet.setColumnWidth("+(startCol+dataIndex)+", "+width+");");
				ksReport.append("sheet.setStyle("+startRow+", "+(startCol+dataIndex)+", style, $.wijmo.wijspread.SheetArea.viewport);");
			} else if (ksReportCell.getDirection() == 2) {
				ksReport.append("sheet.setValue("+(startRow+dataIndex)+", "+startCol+", \""+map.get("NAME").toString()+"\"); \n");
				ksReport.append("sheet.setRowHeight("+(startRow+dataIndex)+", "+height+"); \n");
				ksReport.append("sheet.setStyle("+(startRow+dataIndex)+", "+startCol+", style, $.wijmo.wijspread.SheetArea.viewport);");
			}
			dataIndex++;
		}
		modelMap.put("ksReport", ksReport.toString());
		return new ModelAndView("framework/report/ksReport/show", modelMap);
	}
	
	/**
	 * 展示报表页面
	 * @param modelMap
	 * @param module
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/report1")
	public ModelAndView report1(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap) throws Exception {
		KSReportCell ksReportCell = new KSReportCell();
		ksReportCell.setRow(1);
		ksReportCell.setCol(1);
		ksReportCell.setWidth(33);
		ksReportCell.setHeight(33);
		ksReportCell.setDirection(1);
		ksReportCell.setValue("select name from sys_user");
		KSReportStyle ksReportStyle = new KSReportStyle();
		ksReportStyle.setBackColor("#00ff51");
		ksReportStyle.setBackgroundImage("/kensite/static/exceldesign/img/field/sqlReport.png");
		ksReportStyle.setBackgroundImageLayout(3);
		ksReportStyle.setFont("italic bold 22pt YouYuan");
		ksReportStyle.setForeColor("#f700f7");
		ksReportStyle.sethAlign(1);
		ksReportStyle.setvAlign(1);
		ksReportStyle.setWordWrap(true);
		
		KSReportBorder border = new KSReportBorder();
		border.setColor("#000000");
		border.setStyle(1);
		ksReportStyle.setBorderTop(border);
		ksReportStyle.setBorderBottom(border);
		ksReportStyle.setBorderLeft(border);
		ksReportStyle.setBorderRight(border);
		ksReportCell.setStyle(ksReportStyle);
		
		modelMap.put("ksReport", ksReportService.report1(ksReportCell));
		return new ModelAndView("framework/report/ksReport/show", modelMap);
	}
	
	/**
	 * 展示报表页面
	 * @param modelMap
	 * @param module
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/report2")
	public ModelAndView report2(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap) throws Exception {
		List<KSReportCell> cellList = new ArrayList<KSReportCell>();
		KSReportCell ksReportCell = new KSReportCell();
		ksReportCell.setRow(1);
		ksReportCell.setCol(1);
		ksReportCell.setWidth(33);
		ksReportCell.setHeight(33);
		ksReportCell.setDirection(1);
		ksReportCell.setValue("select name from sys_user");
		KSReportStyle ksReportStyle = new KSReportStyle();
		ksReportStyle.setBackColor("#00ff51");
		ksReportStyle.setBackgroundImage("/kensite/static/exceldesign/img/field/sqlReport.png");
		ksReportStyle.setBackgroundImageLayout(3);
		ksReportStyle.setFont("italic bold 22pt YouYuan");
		ksReportStyle.setForeColor("#f700f7");
		ksReportStyle.sethAlign(1);
		ksReportStyle.setvAlign(1);
		ksReportStyle.setWordWrap(true);
		
		KSReportBorder border = new KSReportBorder();
		border.setColor("#000000");
		border.setStyle(1);
		ksReportStyle.setBorderTop(border);
		ksReportStyle.setBorderBottom(border);
		ksReportStyle.setBorderLeft(border);
		ksReportStyle.setBorderRight(border);
		ksReportCell.setStyle(ksReportStyle);
		cellList.add(ksReportCell);
		
		KSReportCell ksReportCell1 = new KSReportCell();
		ksReportCell1.setRow(2);
		ksReportCell1.setCol(1);
		ksReportCell1.setWidth(33);
		ksReportCell1.setHeight(50);
		ksReportCell1.setDirection(1);
		ksReportCell1.setValue("select user_name name from sys_user");
		KSReportStyle ksReportStyle1 = new KSReportStyle();
		ksReportStyle1.setBackColor("#00ff51");
		ksReportStyle1.setBackgroundImage("/kensite/static/exceldesign/img/field/sqlReport.png");
		ksReportStyle1.setBackgroundImageLayout(3);
		ksReportStyle1.setFont("italic bold 22pt YouYuan");
		ksReportStyle1.setForeColor("#f700f7");
		ksReportStyle1.sethAlign(1);
		ksReportStyle1.setvAlign(1);
		ksReportStyle1.setWordWrap(true);
		
		KSReportBorder border1 = new KSReportBorder();
		border1.setColor("#000000");
		border1.setStyle(1);
		ksReportStyle1.setBorderTop(border1);
		ksReportStyle1.setBorderBottom(border1);
		ksReportStyle1.setBorderLeft(border1);
		ksReportStyle1.setBorderRight(border1);
		ksReportCell1.setStyle(ksReportStyle1);
		cellList.add(ksReportCell1);
		
		modelMap.put("ksReport", ksReportService.report2(cellList));
		return new ModelAndView("framework/report/ksReport/show", modelMap);
	}
	
	/**
	 * 展示报表页面
	 * @param modelMap
	 * @param module
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/report3")
	public ModelAndView report3(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap) throws Exception {
		List<KSReportCell> cellList = new ArrayList<KSReportCell>();
		KSReportCell ksReportCell = new KSReportCell();
		ksReportCell.setRow(1);
		ksReportCell.setCol(1);
		ksReportCell.setWidth(33);
		ksReportCell.setHeight(33);
		ksReportCell.setDirection(2);
		ksReportCell.setValue("select name from sys_user");
		KSReportStyle ksReportStyle = new KSReportStyle();
		ksReportStyle.setBackColor("#00ff51");
		ksReportStyle.setBackgroundImage("/kensite/static/exceldesign/img/field/sqlReport.png");
		ksReportStyle.setBackgroundImageLayout(3);
		ksReportStyle.setFont("italic bold 22pt YouYuan");
		ksReportStyle.setForeColor("#f700f7");
		ksReportStyle.sethAlign(1);
		ksReportStyle.setvAlign(1);
		ksReportStyle.setWordWrap(true);
		
		KSReportBorder border = new KSReportBorder();
		border.setColor("#000000");
		border.setStyle(1);
		ksReportStyle.setBorderTop(border);
		ksReportStyle.setBorderBottom(border);
		ksReportStyle.setBorderLeft(border);
		ksReportStyle.setBorderRight(border);
		ksReportCell.setStyle(ksReportStyle);
		cellList.add(ksReportCell);
		
		KSReportCell ksReportCell1 = new KSReportCell();
		ksReportCell1.setRow(3);
		ksReportCell1.setCol(2);
		ksReportCell1.setWidth(33);
		ksReportCell1.setHeight(50);
		ksReportCell1.setDirection(1);
		ksReportCell1.setValue("select user_name name from sys_user");
		KSReportStyle ksReportStyle1 = new KSReportStyle();
		ksReportStyle1.setBackColor("#00ff51");
		ksReportStyle1.setBackgroundImage("/kensite/static/exceldesign/img/field/sqlReport.png");
		ksReportStyle1.setBackgroundImageLayout(3);
		ksReportStyle1.setFont("italic bold 22pt YouYuan");
		ksReportStyle1.setForeColor("#f700f7");
		ksReportStyle1.sethAlign(1);
		ksReportStyle1.setvAlign(1);
		ksReportStyle1.setWordWrap(true);
		
		KSReportBorder border1 = new KSReportBorder();
		border1.setColor("#000000");
		border1.setStyle(1);
		ksReportStyle1.setBorderTop(border1);
		ksReportStyle1.setBorderBottom(border1);
		ksReportStyle1.setBorderLeft(border1);
		ksReportStyle1.setBorderRight(border1);
		ksReportCell1.setStyle(ksReportStyle1);
		cellList.add(ksReportCell1);
		
		KSReportCell ksReportCell2 = new KSReportCell();
		ksReportCell2.setRow(2);
		ksReportCell2.setCol(2);
		ksReportCell2.setWidth(33);
		ksReportCell2.setHeight(50);
		ksReportCell2.setDirection(2);
		ksReportCell2.setValue("select name from sys_department");
		KSReportStyle ksReportStyle2 = new KSReportStyle();
		ksReportStyle2.setBackColor("#00ff51");
		ksReportStyle2.setBackgroundImage("/kensite/static/exceldesign/img/field/sqlReport.png");
		ksReportStyle2.setBackgroundImageLayout(3);
		ksReportStyle2.setFont("italic bold 22pt YouYuan");
		ksReportStyle2.setForeColor("#f700f7");
		ksReportStyle2.sethAlign(1);
		ksReportStyle2.setvAlign(1);
		ksReportStyle2.setWordWrap(true);
		
		KSReportBorder border2 = new KSReportBorder();
		border2.setColor("#000000");
		border2.setStyle(1);
		ksReportStyle2.setBorderTop(border2);
		ksReportStyle2.setBorderBottom(border2);
		ksReportStyle2.setBorderLeft(border2);
		ksReportStyle2.setBorderRight(border2);
		ksReportCell2.setStyle(ksReportStyle2);
		cellList.add(ksReportCell2);
		
		modelMap.put("ksReport", ksReportService.report3(cellList));
		return new ModelAndView("framework/report/ksReport/show", modelMap);
	}
	
	/**
	 * 展示报表页面
	 * @param modelMap
	 * @param module
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/report4")
	public ModelAndView report4(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap) throws Exception {
		List<KSReportCell> cellList = new ArrayList<KSReportCell>();
		KSReportCell ksReportCell = new KSReportCell();
		ksReportCell.setRow(1);
		ksReportCell.setCol(1);
		ksReportCell.setWidth(33);
		ksReportCell.setHeight(33);
		ksReportCell.setDirection(2);
		ksReportCell.setValue("select dept.name from sys_department dept left join sys_user us on dept.id=us.department_id where dept.id!='00000000000000000000000000000000' order by dept.name");
		KSReportStyle ksReportStyle = new KSReportStyle();
		ksReportStyle.setBackColor("#00ff51");
		ksReportStyle.setBackgroundImage("/kensite/static/exceldesign/img/field/sqlReport.png");
		ksReportStyle.setBackgroundImageLayout(3);
		ksReportStyle.setFont("italic bold 22pt YouYuan");
		ksReportStyle.setForeColor("#f700f7");
		ksReportStyle.sethAlign(1);
		ksReportStyle.setvAlign(1);
		ksReportStyle.setWordWrap(true);
		
		KSReportBorder border = new KSReportBorder();
		border.setColor("#000000");
		border.setStyle(1);
		ksReportStyle.setBorderTop(border);
		ksReportStyle.setBorderBottom(border);
		ksReportStyle.setBorderLeft(border);
		ksReportStyle.setBorderRight(border);
		ksReportCell.setStyle(ksReportStyle);
		cellList.add(ksReportCell);
		
		KSReportCell ksReportCell1 = new KSReportCell();
		ksReportCell1.setRow(1);
		ksReportCell1.setCol(2);
		ksReportCell1.setWidth(33);
		ksReportCell1.setHeight(50);
		ksReportCell1.setDirection(2);
		ksReportCell1.setValue("select us.name from sys_department dept left join sys_user us on dept.id=us.department_id where dept.id!='00000000000000000000000000000000' order by dept.name");
		KSReportStyle ksReportStyle1 = new KSReportStyle();
		ksReportStyle1.setBackColor("#00ff51");
		ksReportStyle1.setBackgroundImage("/kensite/static/exceldesign/img/field/sqlReport.png");
		ksReportStyle1.setBackgroundImageLayout(3);
		ksReportStyle1.setFont("italic bold 22pt YouYuan");
		ksReportStyle1.setForeColor("#f700f7");
		ksReportStyle1.sethAlign(1);
		ksReportStyle1.setvAlign(1);

		ksReportStyle1.setWordWrap(true);
		
		KSReportBorder border1 = new KSReportBorder();
		border1.setColor("#000000");
		border1.setStyle(2);
		ksReportStyle1.setBorderTop(border1);
		ksReportStyle1.setBorderBottom(border1);
		ksReportStyle1.setBorderLeft(border1);
		ksReportStyle1.setBorderRight(border1);
		ksReportCell1.setStyle(ksReportStyle1);
		cellList.add(ksReportCell1);
		
		modelMap.put("ksReport", KSReportUtils.fillData(cellList));
		return new ModelAndView("framework/report/ksReport/show", modelMap);
	}
	
}
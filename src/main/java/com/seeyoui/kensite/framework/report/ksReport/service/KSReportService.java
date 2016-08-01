/*
 * Powered By cuichen
 * Since 2014 - 2016
 */
package com.seeyoui.kensite.framework.report.ksReport.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.seeyoui.kensite.common.base.service.BaseService;
import com.seeyoui.kensite.common.exception.CRUDException;
import com.seeyoui.kensite.common.util.DBUtils;
import com.seeyoui.kensite.common.util.StringUtils;
import com.seeyoui.kensite.framework.report.ksReport.domain.KSReportCell;
import com.seeyoui.kensite.framework.report.ksReport.domain.KSReportStyle;
import com.seeyoui.kensite.framework.report.ksReport.util.KSReportUtils;

/**
 * 演示
 * @author cuichen
 * @version 1.0
 * @since 1.0
 * @date 2016-04-21
 */
@Service
public class KSReportService extends BaseService {

	/**
	 * 
	 * @param ksReportCell
	 * @return
	 * @throws CRUDException
	 * @throws Exception
	 */
	public String report1(KSReportCell ksReportCell) throws CRUDException, Exception {
		
		StringBuffer ksReport = new StringBuffer();
		String sql = ksReportCell.getValue();
		
		int startRow = ksReportCell.getRow();
		int startCol = ksReportCell.getCol();
		double width = ksReportCell.getWidth();
		double height = ksReportCell.getHeight();
		
		KSReportStyle ksReportStyle = ksReportCell.getStyle();
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
		return ksReport.toString();
	}
	
	/**
	 * 
	 * @param ksReportCell
	 * @return
	 * @throws CRUDException
	 * @throws Exception
	 */
	public String report2(List<KSReportCell> cellList) throws CRUDException, Exception {
		StringBuffer ksReport = new StringBuffer();
		for(KSReportCell ksReportCell : cellList) {
			String sql = ksReportCell.getValue();
			
			int startRow = ksReportCell.getRow();
			int startCol = ksReportCell.getCol();
			double width = ksReportCell.getWidth();
			double height = ksReportCell.getHeight();
			
			KSReportStyle ksReportStyle = ksReportCell.getStyle();
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
		}
		return ksReport.toString();
	}

	/**
	 * 
	 * @param ksReportCell
	 * @return
	 * @throws CRUDException
	 * @throws Exception
	 */
	public String report3(List<KSReportCell> cellList) throws CRUDException, Exception {
		StringBuffer ksReport = KSReportUtils.fillData(cellList);
		return ksReport.toString();
	}
}
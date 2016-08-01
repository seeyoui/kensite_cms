package com.seeyoui.kensite.framework.report.ksReport.util;

import java.util.List;
import java.util.Map;

import com.seeyoui.kensite.common.util.DBUtils;
import com.seeyoui.kensite.common.util.StringUtils;
import com.seeyoui.kensite.framework.report.ksReport.domain.KSReportBorder;
import com.seeyoui.kensite.framework.report.ksReport.domain.KSReportCell;
import com.seeyoui.kensite.framework.report.ksReport.domain.KSReportStyle;

public class KSReportUtils {
	//报表数组行数
	private static final int ROW_SIZE = 99;
	//报表数组列数
	private static final int COL_SIZE = 99;
	
	public static StringBuffer fillData(List<KSReportCell> cellList) throws Exception {
		StringBuffer ksReport = new StringBuffer();
		for(KSReportCell ksReportCell : cellList) {
			String sql = ksReportCell.getValue();
			List<Map<Object, Object>> result = DBUtils.executeQuery(sql, false);
			ksReportCell.setCellValue(result);
		}
		cellReLayout(cellList);
		for(KSReportCell ksReportCell : cellList) {
			int startRow = ksReportCell.getRow();
			int startCol = ksReportCell.getCol();
			double width = ksReportCell.getWidth();
			double height = ksReportCell.getHeight();
			
			KSReportStyle ksReportStyle = ksReportCell.getStyle();
			ksReport.append("var style = new $.wijmo.wijspread.Style();");
			if(ksReportStyle != null) {
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
				KSReportBorder borderTop = ksReportStyle.getBorderTop();
				if(borderTop != null) {
					String bordercolor = borderTop.getColor();
					int borderstyle = borderTop.getStyle();
					ksReport.append("style.borderTop = new $.wijmo.wijspread.LineBorder('"+bordercolor+"', "+borderstyle+");");
				}
				KSReportBorder borderBottom = ksReportStyle.getBorderBottom();
				if(borderBottom != null) {
					String bordercolor = borderBottom.getColor();
					int borderstyle = borderBottom.getStyle();
					ksReport.append("style.borderBottom = new $.wijmo.wijspread.LineBorder('"+bordercolor+"', "+borderstyle+");");
				}
				KSReportBorder borderLeft = ksReportStyle.getBorderLeft();
				if(borderLeft != null) {
					String bordercolor = borderLeft.getColor();
					int borderstyle = borderLeft.getStyle();
					ksReport.append("style.borderLeft = new $.wijmo.wijspread.LineBorder('"+bordercolor+"', "+borderstyle+");");
				}
				KSReportBorder borderRight = ksReportStyle.getBorderRight();
				if(borderRight != null) {
					String bordercolor = borderRight.getColor();
					int borderstyle = borderRight.getStyle();
					ksReport.append("style.borderRight = new $.wijmo.wijspread.LineBorder('"+bordercolor+"', "+borderstyle+");");
				}
			}
			if(ksReportCell.getDirection() == 1) {
				ksReport.append("sheet.setRowHeight("+startRow+", "+height+"); \n");
			} else if(ksReportCell.getDirection() == 2) {
				ksReport.append("sheet.setColumnWidth("+startCol+", "+width+");");
			}
			int dataIndex = 0;
			List<Map<Object, Object>> result = ksReportCell.getCellValue();
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
		return ksReport;
	}
	
	/**
	 * cell重新布局，以便往excel里面铺数据
	 * @param cellList
	 */
	public static void cellReLayout(List<KSReportCell> cellList) {
		KSReportCell[][] cellArray = new KSReportCell[ROW_SIZE][COL_SIZE];
		for(KSReportCell ksReportCell : cellList) {
			int row = ksReportCell.getRow();
			int col = ksReportCell.getCol();
			cellArray[row][col] = ksReportCell;
		}
		int[] maxRowSize = new int[ROW_SIZE];
		int[] maxColSize = new int[COL_SIZE];
		maxCellRowSize(maxRowSize, maxColSize, cellArray);
		reLocation(maxRowSize, maxColSize, cellArray);
	}
	
	/**
	 * 同行最大列及同列最大行，数量计算
	 * @param maxRowSize
	 * @param maxColSize
	 * @param cellArray
	 */
	public static void maxCellRowSize(int[] maxRowSize, int[] maxColSize, KSReportCell[][] cellArray) {
		for(int i=0; i<ROW_SIZE; i++) {
			int colSize = maxColSize[i];
			for(int j=0; j<COL_SIZE; j++) {
				if(cellArray[i][j] != null && cellArray[i][j].getDirection()==2 && cellArray[i][j].getCellValueSize() > colSize) {
					maxColSize[i] = cellArray[i][j].getCellValueSize()-1;
				}
			}
		}
		KSReportCell[][] cellArrayTurn = new KSReportCell[ROW_SIZE][COL_SIZE];
		for(int i=0 ; i<ROW_SIZE; i++){
			for(int j=0; j<COL_SIZE; j++){
				cellArrayTurn[j][i] = cellArray[i][j];
			}
		}
		for(int i=0; i<COL_SIZE; i++) {
			int rowSize = maxRowSize[i];
			for(int j=0; j<COL_SIZE; j++) {
				if(cellArrayTurn[i][j] != null && cellArrayTurn[i][j].getDirection()==1 && cellArrayTurn[i][j].getCellValueSize() > rowSize) {
					maxRowSize[i] = cellArrayTurn[i][j].getCellValueSize()-1;
				}
			}
		}
	}
	
	/**
	 * 单元格按照最大行与列重新放置位置
	 * @param maxRowSize
	 * @param maxColSize
	 * @param cellArray
	 */
	public static void reLocation(int[] maxRowSize, int[] maxColSize, KSReportCell[][] cellArray) {
		for(int i=0; i<ROW_SIZE; i++) {
			for(int j=0; j<COL_SIZE; j++) {
				KSReportCell ksReportCell = cellArray[i][j];
				if(ksReportCell == null) {
					continue;
				}
				int rowAdd = getSumSize(maxColSize, i);
				int colAdd = getSumSize(maxRowSize, j);
				int row = ksReportCell.getRow();
				int col = ksReportCell.getCol();
				ksReportCell.setRow(row + rowAdd);
				ksReportCell.setCol(col + colAdd);
			}
		}
	}
	
	//按照下标获取左或上，的最大行列扩展数
	public static int getSumSize(int[] size, int index) {
		int sum = 0;
		for(int i=0; i<index; i++) {
			sum += size[i];
		}
		return sum;
	}
}

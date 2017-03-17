package com.seeyoui.kensite.common.util.excel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.HtmlUtils;

import com.seeyoui.kensite.common.util.Encodes;
import com.seeyoui.kensite.common.util.StringUtils;

/**
 * 导出Excel文件（导出“XLSX”格式，支持大数据量导出 @see org.apache.poi.ss.SpreadsheetVersion）
 * 
 * @author ThinkGem
 * @version 2013-04-21
 */
public class ExportSpreadjs {
	
	/**
	 * 工作薄对象
	 */
	private SXSSFWorkbook wb;

	/**
	 * 构造函数
	 * @param title 标题
	 * @param sql 导出sql
	 * @param headList 表头
	 */
	public ExportSpreadjs(String spreadjs) throws Exception {
		spreadjs = HtmlUtils.htmlUnescape(spreadjs);
		System.out.println(spreadjs);
		JSONObject spreadObj = JSONObject.fromObject(spreadjs);
		initialize(spreadObj);
	}

	/**
	 * 初始化函数
	 * @param spreadObj
	 */
	private void initialize(JSONObject spreadObj) {
		this.wb = new SXSSFWorkbook(500);
		JSONObject sheets = spreadObj.getJSONObject("sheets");
		Iterator iterator = sheets.keys();
		while(iterator.hasNext()) {
			String key = (String) iterator.next();
			//初始化sheet页名称
			Sheet sheet = wb.createSheet(key);
			initializeSheet(sheet, sheets.getJSONObject(key));
		}
	}
	
	/**
	 * 初始化sheet页
	 * @param sheet
	 * @param spreadObj
	 */
	private void initializeSheet(Sheet sheet, JSONObject spreadObj) {
		//设置默认列宽
		sheet.setDefaultColumnWidth(9);
		if(spreadObj.get("columns") != null) {
			JSONArray columns = spreadObj.getJSONArray("columns");
			//根据配置设置列宽
			for(int i=0; i<columns.size(); i++) {
				if(columns.get(i) != null &&  !"null".equals(columns.get(i).toString()) && !"".equals(columns.get(i).toString())) {
					JSONObject column = (JSONObject) columns.get(i);
					int size = column.getInt("size");
					sheet.setColumnWidth(i, size*36);
				}
			}
		}
		//设置初始化行高
		sheet.setDefaultRowHeightInPoints(20);
		if(spreadObj.get("rows") != null) {
			JSONArray rows = spreadObj.getJSONArray("rows");
			//根据配置设置行高
			for(int i=0; i<rows.size(); i++) {
				if(rows.get(i) != null &&  !"null".equals(rows.get(i).toString()) && !"".equals(rows.get(i).toString())) {
					JSONObject row = (JSONObject) rows.get(i);
					int size = row.getInt("size");
					Row r = sheet.createRow(i);
					r.setHeightInPoints(size);
				}
			}
		}
		if(spreadObj.get("spans") != null) {
			JSONArray spans = spreadObj.getJSONArray("spans");
			//根据配置设置单元格合并
			for(int i=0; i<spans.size(); i++) {
				if(spans.get(i) != null &&  !"null".equals(spans.get(i).toString()) && !"".equals(spans.get(i).toString())) {
					JSONObject span = (JSONObject) spans.get(i);
					int row = span.getInt("row");
					int rowCount = span.getInt("rowCount");
					int col = span.getInt("col");
					int colCount = span.getInt("colCount");
					sheet.addMergedRegion(new CellRangeAddress(row, row+rowCount-1, col, col+colCount-1));
				}
			}
		}
		//填充数据
		if(spreadObj.get("data") != null) {
			JSONObject data = spreadObj.getJSONObject("data");
			if(data.get("dataTable") != null) {
				JSONObject dataTable = data.getJSONObject("dataTable");
				renderData(sheet, dataTable);
			}
		}
	}
	
	/**
	 * 填充数据
	 * @param sheet
	 * @param dataTable
	 */
	private void renderData(Sheet sheet, JSONObject dataTable) {
		//遍历行
		Iterator rowIt = dataTable.keys();
		while(rowIt.hasNext()) {
			String rowNum = (String) rowIt.next();
			if(dataTable.get(rowNum) == null) {
				continue;
			}
			JSONObject row = dataTable.getJSONObject(rowNum);
			//遍历列
			Iterator colIt = row.keys();
			while(colIt.hasNext()) {
				String colNum = (String) colIt.next();
				if(row.get(colNum) == null) {
					continue;
				}
				JSONObject cell = row.getJSONObject(colNum);
				//填充单元格数据
				addCell(sheet, rowNum, colNum, cell);
			}
		}
	}
	
	/**
	 * 填充单元格数据
	 * @param sheet
	 * @param rowNum
	 * @param colNum
	 * @param cell
	 */
	private void addCell(Sheet sheet, String rowNum, String colNum, JSONObject cell) {
		Row row = sheet.getRow(Integer.parseInt(rowNum));
		if(row == null) {
			row = sheet.createRow(Integer.parseInt(rowNum));
		}
		Cell c = row.createCell(Integer.parseInt(colNum));
		//设置单元格数据
		if(cell.get("value")!=null) {
			if(StringUtils.isNumeric(cell.get("value").toString())) {
				c.setCellValue(cell.getDouble("value"));
			} else {
				c.setCellValue(cell.getString("value"));
			}
		}
		//设置单元格样式
		if(cell.get("style") != null) {
			setStyle(c, (JSONObject) cell.get("style"));
		}
		//设置单元格公式
		if(cell.get("formula") != null) {
			c.setCellFormula(cell.get("formula").toString());
		}
	}

	/**
	 * 设置单元格样式
	 * @param cell
	 * @param styleObj
	 */
	private void setStyle(Cell cell, JSONObject styleObj) {
		XSSFCellStyle style = (XSSFCellStyle) wb.createCellStyle();
		//设置居中方式
		if(styleObj.get("hAlign") != null) {
			style.setAlignment((short) (Short.parseShort(styleObj.get("hAlign").toString())+1));
		}
		if(styleObj.get("vAlign") != null) {
			style.setVerticalAlignment(Short.parseShort(styleObj.get("vAlign").toString()));
		}
		//设置边框样式
		if(styleObj.get("borderRight") != null) {
			JSONObject borderRight = styleObj.getJSONObject("borderRight");
			if(borderRight.get("style") != null) {
				style.setBorderRight((short) borderRight.getInt("style"));
			} else {
				style.setBorderRight(CellStyle.BORDER_THIN);
			}
			if(borderRight.get("color") != null && borderRight.getString("color").startsWith("#")) {
				String color = borderRight.get("color").toString();
				java.awt.Color borderColor = getColor(color);
				style.setRightBorderColor(new XSSFColor(borderColor));
			} else {
				style.setRightBorderColor(IndexedColors.BLACK.getIndex());
			}
		}
		if(styleObj.get("borderLeft") != null) {
			JSONObject borderLeft = styleObj.getJSONObject("borderLeft");
			if(borderLeft.get("style") != null) {
				style.setBorderLeft((short) borderLeft.getInt("style"));
			} else {
				style.setBorderLeft(CellStyle.BORDER_THIN);
			}
			if(borderLeft.get("color") != null && borderLeft.getString("color").startsWith("#")) {
				String color = borderLeft.get("color").toString();
				java.awt.Color borderColor = getColor(color);
				style.setLeftBorderColor(new XSSFColor(borderColor));
			} else {
				style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
			}
		}
		if(styleObj.get("borderTop") != null) {
			JSONObject borderLeft = styleObj.getJSONObject("borderLeft");
			if(borderLeft.get("style") != null) {
				style.setBorderTop((short) borderLeft.getInt("style"));
			} else {
				style.setBorderTop(CellStyle.BORDER_THIN);
			}
			if(borderLeft.get("color") != null && borderLeft.getString("color").startsWith("#")) {
				String color = borderLeft.get("color").toString();
				java.awt.Color borderColor = getColor(color);
				style.setTopBorderColor(new XSSFColor(borderColor));
			} else {
				style.setTopBorderColor(IndexedColors.BLACK.getIndex());
			}
		}
		if(styleObj.get("borderBottom") != null) {
			JSONObject borderBottom = styleObj.getJSONObject("borderBottom");
			if(borderBottom.get("style") != null) {
				style.setBorderBottom((short) borderBottom.getInt("style"));
			} else {
				style.setBorderBottom(CellStyle.BORDER_THIN);
			}
			if(borderBottom.get("color") != null && borderBottom.getString("color").startsWith("#")) {
				String color = borderBottom.get("color").toString();
				java.awt.Color borderColor = getColor(color);
				style.setBottomBorderColor(new XSSFColor(borderColor));
			} else {
				style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
			}
		}
		//设置字体样式
		XSSFFont font = (XSSFFont)wb.createFont();
		if(styleObj.get("font") != null) {
			String fontStr = styleObj.get("font").toString();
			if(fontStr.indexOf("bold") != -1) {
				fontStr = fontStr.replace("bold", "");
				font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			}
			if(fontStr.indexOf("italic") != -1) {
				fontStr = fontStr.replace("italic", "");
				font.setItalic(true);
			}
			fontStr = fontStr.trim();
			if(fontStr.indexOf("pt") != -1) {
				String[] fonts = fontStr.split("pt");
				font.setFontHeightInPoints(Short.parseShort(fonts[0]));
				font.setFontName(fonts[1]);
			} else if(fontStr.indexOf("px") != -1) {
				String[] fonts = fontStr.split("px");
				font.setFontHeightInPoints(Short.parseShort(fonts[0]));
				font.setFontName(fonts[1]);
			} else {
				font.setFontName(fontStr);
			}
			/*
			 * 12pt SimHei
			 * bold 9pt Microsoft YaHei
			 * italic 9pt Microsoft YaHei
			 * 9pt SimHei
			 * 10pt Microsoft YaHei
			 */
			/*
			font.setFontName(fonts[2]);
			//titleFont.setFontHeightInPoints((short) 20);
			font.setFontHeightInPoints(Short.parseShort(fonts[1].replace("px", "")));
			if("bold".equals(fonts[0])) {
				
			}
			*/
		}
		//设置前景色
		if(styleObj.get("foreColor") != null) {
			String color = styleObj.get("foreColor").toString();
			java.awt.Color fontColor = getColor(color);
			font.setColor(new XSSFColor(fontColor));
		}
		style.setFont(font);
		//设置背景色
		if(styleObj.get("backColor") != null) {
			String color = styleObj.get("backColor").toString();
			java.awt.Color backColor = getColor(color);
			style.setFillForegroundColor(new XSSFColor(backColor));
			style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		}
		
		//设置单元格样式
		cell.setCellStyle(style);
	}
	
	private java.awt.Color getColor(String color) {
		String r = color.substring(1,3);
        String g = color.substring(3,5);
        String b = color.substring(5,7);
        int red = Integer.parseInt(r,16);
        int green = Integer.parseInt(g,16);
        int blue = Integer.parseInt(b,16);
        return new java.awt.Color(red, green, blue);
	}
	
	/**
	 * 输出数据流
	 * 
	 * @param os
	 *            输出数据流
	 */
	public ExportSpreadjs write(OutputStream os) throws IOException {
		wb.write(os);
		return this;
	}

	/**
	 * 输出到客户端
	 * 
	 * @param fileName
	 *            输出文件名
	 */
	public ExportSpreadjs write(HttpServletResponse response, String fileName)
			throws IOException {
		response.reset();
		response.setContentType("application/octet-stream; charset=utf-8");
		response.setHeader("Content-Disposition", "attachment; filename="
				+ Encodes.urlEncode(fileName));
		write(response.getOutputStream());
		return this;
	}

	/**
	 * 输出到文件
	 * 
	 * @param fileName
	 *            输出文件名
	 */
	public ExportSpreadjs writeFile(String name) throws FileNotFoundException,
			IOException {
		FileOutputStream os = new FileOutputStream(name);
		this.write(os);
		return this;
	}

	/**
	 * 清理临时文件
	 */
	public ExportSpreadjs dispose() {
		wb.dispose();
		return this;
	}

}

package com.seeyoui.kensite.framework.report.ksChart.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import com.seeyoui.kensite.common.base.controller.BaseController;
import com.seeyoui.kensite.common.util.DBUtils;
import com.seeyoui.kensite.common.util.StringUtils;

/**
 * 百度Echarts统计图表
 * @author cuichen
 * @version 1.0
 * @since 1.0
 * @date 2015-11-16
 */
@Controller
@RequestMapping(value = "ks/chart")
public class KSChartController extends BaseController {
	
	@RequestMapping(value = "/line")
	@ResponseBody
	public Object line(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, String sqlx, String sqly, String sqlz) throws Exception {
		sqlx= HtmlUtils.htmlUnescape(sqlx);
		sqly= HtmlUtils.htmlUnescape(sqly);
		sqlz= HtmlUtils.htmlUnescape(sqlz);
		List<Map<Object, Object>> xList = DBUtils.executeQuery(sqlx, false);
		List<Map<Object, Object>> yList = DBUtils.executeQuery(sqly, false);
		List<Map<Object, Object>> zList = DBUtils.executeQuery(sqlz, false);
		Map<String, Object> option = new HashMap<String, Object>();
		Map<String, Object> xAxis = new HashMap<String, Object>();
		List<String> data = new ArrayList<String>();
		for(int i=0; i<xList.size(); i++) {
			data.add(xList.get(i).get("VALUE").toString());
		}
		xAxis.put("data", data);
		option.put("xAxis", xAxis);
		Map<String, Object> legend = new HashMap<String, Object>();
		data = new ArrayList<String>();
		for(int i=0; i<zList.size(); i++) {
			data.add(zList.get(i).get("NAME").toString());
		}
		legend.put("data", data);
		option.put("legend", legend);
		List<Map<Object, Object>> series = new ArrayList<Map<Object,Object>>();
		for(int i=0; i<zList.size(); i++) {
			Map<Object, Object> zMap = zList.get(i);
			Map<Object, Object> seriesMap = new HashMap<Object, Object>();
			String zKey = zMap.get("KEY").toString();
			seriesMap.put("name", zMap.get("NAME"));
			seriesMap.put("type", "line");
			List<Double> yData = new ArrayList<Double>();
			for(int j=0; j<xList.size(); j++) {
				Map<Object, Object> xMap = xList.get(j);
				String xkey = xMap.get("KEY").toString();
				boolean isAdd = false;
				for(int k=0; k<yList.size(); k++) {
					Map<Object, Object> yMap = yList.get(k);
					if(yMap.get("ZKEY") != null && yMap.get("XKEY") != null && zKey.equals(yMap.get("ZKEY").toString()) && xkey.equals(yMap.get("XKEY").toString())) {
						yData.add(Double.parseDouble(yMap.get("VALUE").toString()));
						isAdd = true;
						break;
					}
				}
				if(!isAdd) {
					yData.add(0D);
				}
			}
			seriesMap.put("data", yData);
			series.add(seriesMap);
		}
		option.put("series", series);
		return option;
	}

	@RequestMapping(value = "/bar")
	@ResponseBody
	public Object bar(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, String sqlx, String sqly, String sqlz) throws Exception {
		sqlx= HtmlUtils.htmlUnescape(sqlx);
		sqly= HtmlUtils.htmlUnescape(sqly);
		sqlz= HtmlUtils.htmlUnescape(sqlz);
		List<Map<Object, Object>> xList = DBUtils.executeQuery(sqlx, false);
		List<Map<Object, Object>> yList = DBUtils.executeQuery(sqly, false);
		List<Map<Object, Object>> zList = DBUtils.executeQuery(sqlz, false);
		Map<String, Object> option = new HashMap<String, Object>();
		Map<String, Object> xAxis = new HashMap<String, Object>();
		List<String> data = new ArrayList<String>();
		for(int i=0; i<xList.size(); i++) {
			data.add(xList.get(i).get("VALUE").toString());
		}
		xAxis.put("data", data);
		option.put("xAxis", xAxis);
		Map<String, Object> legend = new HashMap<String, Object>();
		data = new ArrayList<String>();
		for(int i=0; i<zList.size(); i++) {
			data.add(zList.get(i).get("NAME").toString());
		}
		legend.put("data", data);
		option.put("legend", legend);
		List<Map<Object, Object>> series = new ArrayList<Map<Object,Object>>();
		for(int i=0; i<zList.size(); i++) {
			Map<Object, Object> zMap = zList.get(i);
			Map<Object, Object> seriesMap = new HashMap<Object, Object>();
			String zKey = zMap.get("KEY").toString();
			seriesMap.put("name", zMap.get("NAME"));
			seriesMap.put("type", "bar");
			if(zMap.get("STACK")!=null && StringUtils.isNoneBlank(zMap.get("STACK").toString())) {
				seriesMap.put("stack", zMap.get("STACK").toString());
			}
			List<Double> yData = new ArrayList<Double>();
			for(int j=0; j<xList.size(); j++) {
				Map<Object, Object> xMap = xList.get(j);
				String xkey = xMap.get("KEY").toString();
				boolean isAdd = false;
				for(int k=0; k<yList.size(); k++) {
					Map<Object, Object> yMap = yList.get(k);
					if(yMap.get("ZKEY") != null && yMap.get("XKEY") != null && zKey.equals(yMap.get("ZKEY").toString()) && xkey.equals(yMap.get("XKEY").toString())) {
						yData.add(Double.parseDouble(yMap.get("VALUE").toString()));
						isAdd = true;
						break;
					}
				}
				if(!isAdd) {
					yData.add(0D);
				}
			}
			seriesMap.put("data", yData);
			series.add(seriesMap);
		}
		option.put("series", series);		
		return option;
	}

	@RequestMapping(value = "/pie")
	@ResponseBody
	public Object pie(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, String sqlx, String sqly) throws Exception {
		sqlx= HtmlUtils.htmlUnescape(sqlx);
		sqly= HtmlUtils.htmlUnescape(sqly);
		List<Map<Object, Object>> xList = DBUtils.executeQuery(sqlx, false);
		List<Map<Object, Object>> yList = DBUtils.executeQuery(sqly, false);
		Map<String, Object> option = new HashMap<String, Object>();
		Map<String, Object> legend = new HashMap<String, Object>();
		List<String> data = new ArrayList<String>();
		for(int i=0; i<xList.size(); i++) {
			data.add(xList.get(i).get("VALUE").toString());
		}
		legend.put("data", data);
		option.put("legend", legend);
		List<Map<Object, Object>> series = new ArrayList<Map<Object,Object>>();
		Map<Object, Object> seriesMap = new HashMap<Object, Object>();
		List<Map<Object, Object>> seriesData = new ArrayList<Map<Object,Object>>();
		for(int i=0; i<xList.size(); i++) {
			Map<Object, Object> xMap = xList.get(i);
			String xkey = xMap.get("KEY").toString();
			String xvalue = xList.get(i).get("VALUE").toString();
			boolean isAdd = false;
			for(int k=0; k<yList.size(); k++) {
				Map<Object, Object> yMap = yList.get(k);
				if(yMap.get("XKEY") != null && xkey.equals(yMap.get("XKEY").toString())) {
					Map<Object, Object> sd = new HashMap<Object, Object>();
					sd.put("value", Double.parseDouble(yMap.get("VALUE").toString()));
					sd.put("name", xvalue);
					seriesData.add(sd);
					isAdd = true;
					break;
				}
			}
			if(!isAdd) {
				Map<Object, Object> sd = new HashMap<Object, Object>();
				sd.put("value", 0D);
				sd.put("name", xvalue);
				seriesData.add(sd);
			}
		}
		seriesMap.put("data", seriesData);
		series.add(seriesMap);
		option.put("series", series);
		return option;
	}
}
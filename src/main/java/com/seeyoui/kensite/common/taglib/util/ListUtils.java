package com.seeyoui.kensite.common.taglib.util;

import java.util.List;
import java.util.Map;

import com.seeyoui.kensite.common.constants.StringConstant;
import com.seeyoui.kensite.common.taglib.constants.TableColumnConstants;
import com.seeyoui.kensite.common.taglib.expression.ExpressionUtils;
import com.seeyoui.kensite.common.util.CacheUtils;
import com.seeyoui.kensite.common.util.DBUtils;
import com.seeyoui.kensite.common.util.StringUtils;
import com.seeyoui.kensite.framework.mod.tableColumn.domain.TableColumn;
import com.seeyoui.kensite.framework.plugin.dict.domain.Dict;
import com.seeyoui.kensite.framework.system.util.DictUtils;

/**
 * 表单控件工具类
 * @author Ken
 * @version 2015-11-10
 */
public class ListUtils {
	
	public static StringBuffer getTableColumnStr(TableColumn tableColumn) throws Exception {
		TableColumn tc = TagCacheUtils.getTableColumn(tableColumn);
		if(tc == null) {
			return null;
		}
		if(StringUtils.isNotBlank(tc.getDefaultValue())) {
			tc.setDefaultValue(ExpressionUtils.parse(tc.getDefaultValue()));
		}
		if(StringUtils.isNoneBlank(tc.getSettings())) {
			tc.setSettings(ExpressionUtils.parse(tc.getSettings()));
		}
		StringBuffer result = getEasyUIStr(tc);
		return result;
	}
	
	private static StringBuffer getEasyUIStr(TableColumn tableColumn) throws Exception {
		StringBuffer result = (StringBuffer)CacheUtils.get(TableColumnConstants.CACHE_LIST+TableColumnConstants.CACHE_SPLIT+TableColumnConstants.CACHE_EASYUI+TableColumnConstants.CACHE_SPLIT+tableColumn.getTableName()+TableColumnConstants.CACHE_SPLIT+tableColumn.getName());
		if (result !=  null){
			return result;
		}
		boolean needCache = true;
		result = new StringBuffer();
		String column = tableColumn.getName();
		column = StringUtils.toCamelCase(column);
		result.append("<th data-options=\"halign:'center',");
//		result.append("sortable:true,");
		result.append("field:'"+column+"',");
		if(StringUtils.isNoneBlank(tableColumn.getListWidth())) {
			result.append(" width:"+tableColumn.getListWidth()+",");
		} else {
			result.append(" width:100,");
		}
		if((StringUtils.isNoneBlank(tableColumn.getIsList()) && StringConstant.NO.equals(tableColumn.getIsList()))) {
			result.append(" hidden:true,");
		}
		if((StringUtils.isNoneBlank(tableColumn.getIsSort()) && StringConstant.YES.equals(tableColumn.getIsList()))) {
			result.append(" sortable:true,");
		}
		if(TableColumnConstants.TEXTBOX.equals(tableColumn.getCategory()) || TableColumnConstants.TEXTAREA.equals(tableColumn.getCategory()) || TableColumnConstants.SELECTBUTTON.equals(tableColumn.getCategory())) {
		}
		if(TableColumnConstants.NUMBERBOX.equals(tableColumn.getCategory())) {
			result.append(" align:'right',");
			result.append(" formatter: function(val,row,index){");
			if(StringUtils.isNoneBlank(tableColumn.getSettings())) {
				String[] settingsArr = tableColumn.getSettings().split(",");
				result.append("if(val==null||val=='') {return val;} else {return parseFloat(val).toFixed("+settingsArr[2].replace("precision:", "")+");}}");
			}
		}
		if(TableColumnConstants.COMBOBOX.equals(tableColumn.getCategory()) || TableColumnConstants.RADIOBOX.equals(tableColumn.getCategory()) || TableColumnConstants.CHECKBOX.equals(tableColumn.getCategory())) {
			needCache = false;
			result.append(" formatter: function(val,row,index){");
			StringBuffer sb = new StringBuffer();
			if(StringUtils.isNoneBlank(tableColumn.getSettings())) {
				if(TableColumnConstants.CHECKBOX.equals(tableColumn.getCategory())) {
				}
				String settings = tableColumn.getSettings();
				if(settings.indexOf("SQL>") != -1) {
					sb.append("var jsonObj = [");
					String[] settingsArr = settings.split("\\|");
					String sql = settingsArr[0].replace("SQL>", "");
					String value = settingsArr[1];
					String label = settingsArr[2];
					List<Map<Object, Object>> list = DBUtils.executeQuery(sql, false);
					for(Map<Object, Object> map : list) {
						sb.append("{value: '"+map.get(value.toUpperCase())+"',label: '"+map.get(label.toUpperCase())+"'},");
					}
					if(sb.lastIndexOf(",") > 1) {
						sb.deleteCharAt(sb.lastIndexOf(","));
					}
					sb.append("];");
				} else if(settings.indexOf("DICT>") != -1) {
					sb.append("var jsonObj = [");
					List<Dict> dictList = DictUtils.getDictList(DictUtils.getDict(settings.replace("DICT>", "")).getValue());
					for(Dict dict : dictList) {
						sb.append("{value: '"+dict.getValue()+"',label: '"+dict.getLabel()+"'},");
					}
					if(sb.lastIndexOf(",") > 1) {
						sb.deleteCharAt(sb.lastIndexOf(","));
					}
					sb.append("];");
				} else if(settings.indexOf("URL>") != -1) {
					sb.append("var jsonObj = null;");
				} else  {
					sb.append("var jsonObj = [");
					String[] settingsArr = settings.split("\\|");
					for(String set : settingsArr) {
						if(set.indexOf(":") == -1) {
							sb.append("{label: '"+set+"',value: '"+set+"'},");
						} else {
							String[] setArr = set.split(":");
							sb.append("{value: '"+setArr[0]+"',label: '"+setArr[1]+"'},");
						}
					}
					if(sb.lastIndexOf(",") > 1) {
						sb.deleteCharAt(sb.lastIndexOf(","));
					}
					sb.append("];");
				}
			}
			result.append(sb);
			result.append("if(jsonObj == null||val==null||val=='') {return val;}var varArr = val.split(',');var result = '';for(var i=0; i<varArr.length; i++) {for(var obj in jsonObj) {if(jsonObj[obj].value == varArr[i]) {result += (jsonObj[obj].label+',');}}}return result.substring(0, result.length-1);},");
		}
		if(TableColumnConstants.COMBOTREE.equals(tableColumn.getCategory())) {
			needCache = false;
			result.append(" formatter: function(val,row,index){");
			StringBuffer sb = new StringBuffer();
			if(StringUtils.isNoneBlank(tableColumn.getSettings())) {
				String settings = tableColumn.getSettings();
				if(settings.indexOf("SQL>") != -1) {
					sb.append("var jsonObj = [");
					String[] settingsArr = settings.split("\\|");
					String sql = settingsArr[0].replace("SQL>", "");
					String value = settingsArr[1];
					String label = settingsArr[2];
					List<Map<Object, Object>> list = DBUtils.executeQuery(sql, false);
					for(Map<Object, Object> map : list) {
						sb.append("{value: '"+map.get(value.toUpperCase())+"',label: '"+map.get(label.toUpperCase())+"'},");
					}
					if(sb.lastIndexOf(",") > 1) {
						sb.deleteCharAt(sb.lastIndexOf(","));
					}
					sb.append("];");
				} else if(settings.indexOf("DICT>") != -1) {
					sb.append("var jsonObj = [");
					List<Dict> dictList = DictUtils.getDictList(DictUtils.getDict(settings.replace("DICT>", "")).getValue());
					for(Dict dict : dictList) {
						sb.append("{value: '"+dict.getValue()+"',label: '"+dict.getLabel()+"'},");
					}
					if(sb.lastIndexOf(",") > 1) {
						sb.deleteCharAt(sb.lastIndexOf(","));
					}
					sb.append("];");
				} else if(settings.indexOf("URL>") != -1) {
					sb.append("var jsonObj = null;");
				} else  {
					sb.append("var jsonObj = [");
					String[] settingsArr = settings.split("\\|");
					for(String set : settingsArr) {
						if(set.indexOf(":") == -1) {
							sb.append("{label: '"+set+"',value: '"+set+"'},");
						} else {
							String[] setArr = set.split(":");
							sb.append("{value: '"+setArr[0]+"',label: '"+setArr[1]+"'},");
						}
					}
					if(sb.lastIndexOf(",") > 1) {
						sb.deleteCharAt(sb.lastIndexOf(","));
					}
					sb.append("];");
				}
			}
			result.append(sb);
			result.append("if(jsonObj == null||val==null||val=='') {return val;}for(var obj in jsonObj) {if(jsonObj[obj].value == val) {return jsonObj[obj].label;}} return ''}");
		}
		if(TableColumnConstants.DATEBOX.equals(tableColumn.getCategory())) {
			result.append(" align:'center',");
		}
		if(TableColumnConstants.HTMLDESIGN.equals(tableColumn.getCategory())) {
		}
		result.append("\">"+tableColumn.getComments()+"</th>");
		if(needCache) {
			CacheUtils.put(TableColumnConstants.CACHE_LIST+TableColumnConstants.CACHE_SPLIT+TableColumnConstants.CACHE_EASYUI+TableColumnConstants.CACHE_SPLIT+tableColumn.getTableName()+TableColumnConstants.CACHE_SPLIT+tableColumn.getName(), result);
		}
		return result;
	}
}

package com.seeyoui.kensite.common.taglib.util;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.seeyoui.kensite.common.constants.StringConstant;
import com.seeyoui.kensite.common.taglib.constants.TableColumnConstants;
import com.seeyoui.kensite.common.util.CacheUtils;
import com.seeyoui.kensite.common.util.DBUtils;
import com.seeyoui.kensite.common.util.Global;
import com.seeyoui.kensite.common.util.StringUtils;
import com.seeyoui.kensite.framework.mod.tableColumn.domain.TableColumn;
import com.seeyoui.kensite.framework.plugin.dict.domain.Dict;
import com.seeyoui.kensite.framework.system.util.DictUtils;

/**
 * 表单控件工具类
 * @author Ken
 * @version 2015-11-10
 */
public class QueryUtils {
	
	private static final String ALL_STR = "全部";
	
	public static StringBuffer getTableColumnStr(TableColumn tableColumn) throws Exception {
		TableColumn tc = TagCacheUtils.getTableColumn(tableColumn);
		if(tc == null || StringConstant.NO.equals(tc.getIsQuery())) {
			return null;
		}
		StringBuffer result = getEasyUIStr(tc);
		return result;
	}
	
	private static StringBuffer getEasyUIStr(TableColumn tableColumn) throws Exception {
		StringBuffer result = (StringBuffer)CacheUtils.get(TableColumnConstants.CACHE_QUERY+TableColumnConstants.CACHE_SPLIT+TableColumnConstants.CACHE_EASYUI+TableColumnConstants.CACHE_SPLIT+tableColumn.getTableName()+TableColumnConstants.CACHE_SPLIT+tableColumn.getName());
		if (result !=  null){
			return result;
		}
		boolean needCache = true;
		result = new StringBuffer();
		String column = tableColumn.getName();
		column = StringUtils.toCamelCase(column);
		result.append("<span>"+tableColumn.getComments()+"</span>");
		if(TableColumnConstants.TEXTBOX.equals(tableColumn.getCategory()) || TableColumnConstants.TEXTAREA.equals(tableColumn.getCategory()) || TableColumnConstants.HTMLDESIGN.equals(tableColumn.getCategory()) || TableColumnConstants.SELECTBUTTON.equals(tableColumn.getCategory())) {
			result.append("<input class=\"easyui-textbox\" id=\"sel_");
			result.append(column);
			result.append("\" name=\"sel_");
			result.append(column);
			result.append("\" data-options=\"");
			if(StringUtils.isNoneBlank(tableColumn.getQueryWidth())) {
				result.append(" width:"+tableColumn.getQueryWidth()+",");
			} else {
				result.append(" width:100,");
			}
			result.append("\"/>");
		}
		if(TableColumnConstants.NUMBERBOX.equals(tableColumn.getCategory())) {
			result.append("<input class=\"easyui-numberbox\" id=\"sel_");
			result.append(column);
			result.append("\" name=\"sel_");
			result.append(column);
			result.append("\" data-options=\"");
			if(StringUtils.isNoneBlank(tableColumn.getQueryWidth())) {
				result.append(" width:"+tableColumn.getQueryWidth()+",");
			} else {
				result.append(" width:100,");
			}
			if(StringUtils.isNoneBlank(tableColumn.getSettings())) {
				result.append(tableColumn.getSettings());
			}
			result.append("\"/>");
		}
		if(TableColumnConstants.COMBOBOX.equals(tableColumn.getCategory()) || TableColumnConstants.RADIOBOX.equals(tableColumn.getCategory()) || TableColumnConstants.CHECKBOX.equals(tableColumn.getCategory())) {
			needCache = false;
			result.append("<input class=\"easyui-combobox\" id=\"sel_");
			result.append(column);
			result.append("\" name=\"sel_");
			result.append(column);
			result.append("\" data-options=\"editable:false, ");
			int dataCount = 0;
			if(StringUtils.isNoneBlank(tableColumn.getSettings())) {
				if(StringUtils.isNoneBlank(tableColumn.getQueryWidth())) {
					result.append(" width:"+tableColumn.getQueryWidth()+",");
				} else {
					result.append(" width:100,");
				}
//				if(TableColumnConstants.CHECKBOX.equals(tableColumn.getCategory())) {
//					result.append("multiple:true,");
//				}
				String settings = tableColumn.getSettings();
				if(settings.indexOf("SQL>") != -1) {
					String[] settingsArr = settings.split("\\|");
					String sql = settingsArr[0].replace("SQL>", "");
					String value = settingsArr[1];
					String label = settingsArr[2];
					result.append("valueField: '"+StringUtils.toCamelCase(value)+"',textField: '"+StringUtils.toCamelCase(label)+"',");
					result.append("data: [");
					result.append("{"+StringUtils.toCamelCase(value)+": '',"+StringUtils.toCamelCase(label)+": '"+ALL_STR+"'},");
					List<Map<Object, Object>> list = DBUtils.executeQuery(sql, false);
					for(Map<Object, Object> map : list) {
						dataCount++;
						Iterator entries = map.entrySet().iterator();
						result.append("{"+StringUtils.toCamelCase(value)+": '"+map.get(value.toUpperCase())+"',"+StringUtils.toCamelCase(label)+": '"+map.get(label.toUpperCase())+"'");
						while (entries.hasNext()) {
						    Entry entry = (Entry) entries.next();
						    String k = (String)entry.getKey();
						    String v = (String)entry.getValue();
						    if(value.toUpperCase().equals(k) || label.toUpperCase().equals(k)) {
						    	continue;
						    }
						    result.append("," + StringUtils.toCamelCase(k)+": '"+v+"'");
						}
						result.append("},");
					}
					if(result.toString().endsWith(",")) {
						result.deleteCharAt(result.lastIndexOf(","));
					}
					result.append("]");
				} else if(settings.indexOf("DICT>") != -1) {
					result.append("valueField: 'value',textField: 'label',");
					result.append("data: [");
					result.append("{value: '',label: '"+ALL_STR+"'},");
					List<Dict> dictList = DictUtils.getDictList(DictUtils.getDict(settings.replace("DICT>", "")).getValue());
					for(Dict dict : dictList) {
						dataCount++;
						result.append("{value: '"+dict.getValue()+"',label: '"+dict.getLabel()+"'},");
					}
					if(result.toString().endsWith(",")) {
						result.deleteCharAt(result.lastIndexOf(","));
					}
					result.append("]");
				} else if(settings.indexOf("URL>") != -1) {
					String[] settingsArr = settings.split("\\|");
					String url = settingsArr[0].replace("URL>", "");
					String value = settingsArr[1];
					String label = settingsArr[2];
					result.append("valueField: '"+StringUtils.toCamelCase(value)+"',textField: '"+StringUtils.toCamelCase(label)+"',");
					result.append("url:'/"+Global.getConfig("productName")+url+"'");
				} else  {
					result.append("valueField: 'value',textField: 'label',");
					result.append("data: [");
					result.append("{value: '',label: '"+ALL_STR+"'},");
					String[] settingsArr = settings.split("\\|");
					for(String set : settingsArr) {
						dataCount++;
						if(set.indexOf(":") == -1) {
							result.append("{label: '"+set+"',value: '"+set+"'},");
						} else {
							String[] setArr = set.split(":");
							result.append("{value: '"+setArr[0]+"',label: '"+setArr[1]+"'},");
						}
					}
					if(result.toString().endsWith(",")) {
						result.deleteCharAt(result.lastIndexOf(","));
					}
					result.append("]");
				}
			}
			if(dataCount <= 5) {
				result.append(",panelHeight:'auto',");
			}
			result.append("\" "+tableColumn.getHtmlInner()+"/>");
		}
		if(TableColumnConstants.COMBOTREE.equals(tableColumn.getCategory())) {
			needCache = false;
			result.append("<input class=\"easyui-combotree\" id=\"sel_");
			result.append(column);
			result.append("\" name=\"sel_");
			result.append(column);
			result.append("\" data-options=\"");
			result.append("editable:false,");
			result.append("icons: [{iconCls:'icon-clear',handler: function(e){$(e.data.target).combobox('clear');}}],");
			int dataCount = 0;
			if(StringUtils.isNoneBlank(tableColumn.getSettings())) {
				String settings = tableColumn.getSettings();
				if(settings.indexOf("SQL>") != -1) {
					String[] settingsArr = settings.split("\\|");
					String sql = settingsArr[0].replace("SQL>", "");
					String id = settingsArr[1];
					String text = settingsArr[2];
					String parent = settingsArr[3];
					result.append("idField: '"+StringUtils.toCamelCase(id)+"',textField: '"+StringUtils.toCamelCase(text)+"',parentField: '"+StringUtils.toCamelCase(parent)+"',");
					result.append("data: [");
					List<Map<Object, Object>> list = DBUtils.executeQuery(sql, false);
					for(Map<Object, Object> map : list) {
						dataCount++;
						Iterator entries = map.entrySet().iterator();
						result.append("{"+StringUtils.toCamelCase(id)+": '"+map.get(id.toUpperCase())+"',"+StringUtils.toCamelCase(text)+": '"+map.get(text.toUpperCase())+"',"+StringUtils.toCamelCase(parent)+": '"+map.get(parent.toUpperCase())+"'");
						while (entries.hasNext()) {
						    Entry entry = (Entry) entries.next();
						    String k = (String)entry.getKey();
						    String v = (String)entry.getValue();
						    if(id.toUpperCase().equals(k) || text.toUpperCase().equals(k) || parent.toUpperCase().equals(k)) {
						    	continue;
						    }
						    result.append("," + StringUtils.toCamelCase(k)+": '"+v+"'");
						}
						result.append("},");
					}
					if(result.toString().endsWith(",")) {
						result.deleteCharAt(result.lastIndexOf(","));
					}
					result.append("]");
				} else if(settings.indexOf("DICT>") != -1) {
					result.append("idField: 'value',textField: 'label',parentField: 'category',");
					result.append("data: [");
					List<Dict> dictList = DictUtils.getDictList(DictUtils.getDict(settings.replace("DICT>", "")).getValue());
					for(Dict dict : dictList) {
						dataCount++;
						result.append("{value: '"+dict.getValue()+"',label: '"+dict.getLabel()+"',category: '"+dict.getCategory()+"'},");
					}
					if(result.toString().endsWith(",")) {
						result.deleteCharAt(result.lastIndexOf(","));
					}
					result.append("]");
				} else if(settings.indexOf("URL>") != -1) {
					String[] settingsArr = settings.split("\\|");
					String url = settingsArr[0].replace("URL>", "");
					String id = settingsArr[1];
					String text = settingsArr[2];
					String parent = settingsArr[3];
					result.append("idField: '"+StringUtils.toCamelCase(id)+"',textField: '"+StringUtils.toCamelCase(text)+"',parentField: '"+StringUtils.toCamelCase(parent)+"',");
					result.append("url:'/"+Global.getConfig("productName")+url+"'");
				} else {
					result.append("idField: 'id',textField: 'text',parentField: 'pid',");
					result.append("data: [");
					String[] settingsArr = settings.split("\\|");
					for(String set : settingsArr) {
						dataCount++;
						String[] setArr = set.split(":");
						result.append("{id: '"+setArr[0]+"',text: '"+setArr[1]+"',pid: '"+setArr[2]+"'},");
					}
					if(result.toString().endsWith(",")) {
						result.deleteCharAt(result.lastIndexOf(","));
					}
					result.append("]");
				}
			}
			if(dataCount <= 5) {
				result.append(",panelHeight:'auto',");
			} else {
				result.append(",panelHeight:'130',");
			}
			result.append("\" "+tableColumn.getHtmlInner());
			result.append("/>");
		}
		if(TableColumnConstants.DATEBOX.equals(tableColumn.getCategory())) {
			result.append("<input class=\"date-input easyui-validatebox\" id=\"sel_");
			result.append(column);
			result.append("\" name=\"sel_");
			result.append(column);
			result.append("\" "+tableColumn.getHtmlInner());
			if(StringUtils.isNoneBlank(tableColumn.getSettings())) {
				result.append(" onClick=\"WdatePicker({");
				result.append(tableColumn.getSettings().replaceAll(",maxDate:''", "").replaceAll(",minDate:''", ""));
				result.append("})\"");
			} else {
				result.append(" onClick=\"WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})\"");
			}
			if(StringUtils.isNoneBlank(tableColumn.getQueryWidth())) {
				result.append(" style=\"width:"+tableColumn.getQueryWidth()+"px;\"");
			} else {
				result.append(" style=\"width:100px;\"");
			}
			result.append("/>");
		}
		if(needCache) {
			CacheUtils.put(TableColumnConstants.CACHE_QUERY+TableColumnConstants.CACHE_SPLIT+TableColumnConstants.CACHE_EASYUI+TableColumnConstants.CACHE_SPLIT+tableColumn.getTableName()+TableColumnConstants.CACHE_SPLIT+tableColumn.getName(), result);
		}
		return result;
	}
}

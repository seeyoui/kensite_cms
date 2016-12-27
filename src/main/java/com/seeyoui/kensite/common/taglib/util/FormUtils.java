package com.seeyoui.kensite.common.taglib.util;

import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.seeyoui.kensite.common.constants.StringConstant;
import com.seeyoui.kensite.common.taglib.constants.TableColumnConstants;
import com.seeyoui.kensite.common.taglib.expression.ExpressionUtils;
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
public class FormUtils {
	
	public static StringBuffer getTableColumnStr(TableColumn tableColumn, String theme) throws Exception {
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
		StringBuffer result = null;
		if(StringUtils.isBlank(theme)) {
			result = getEasyUIStr(tc);
		} else if("layer".equals(theme)) {
			result = getLayerUIStr(tc);
		} else {
			result = getEasyUIStr(tc);
		}
		return result;
	}
	
	private static StringBuffer getEasyUIStr(TableColumn tableColumn) throws Exception {
		StringBuffer result = (StringBuffer)CacheUtils.get(TableColumnConstants.CACHE_FORM+TableColumnConstants.CACHE_SPLIT+TableColumnConstants.CACHE_EASYUI+TableColumnConstants.CACHE_SPLIT+tableColumn.getTableName()+TableColumnConstants.CACHE_SPLIT+tableColumn.getName());
		if (result !=  null){
			return result;
		}
		if(StringUtils.isNoneBlank(tableColumn.getIsEdit()) && StringConstant.HIDDEN.equals(tableColumn.getIsEdit())) {
			return new StringBuffer();
		}
		boolean needCache = true;
		result = new StringBuffer();
		String column = tableColumn.getName();
		column = StringUtils.toCamelCase(column);
		result.append("<label>"+tableColumn.getComments()+"</label>");
		if(TableColumnConstants.TEXTBOX.equals(tableColumn.getCategory()) || TableColumnConstants.TEXTAREA.equals(tableColumn.getCategory())) {
			result.append("<input class=\"easyui-textbox\" id=\"");
			result.append(column);
			result.append("\" name=\"");
			result.append(column);
			result.append("\" data-options=\"tipPosition:'bottom',");
			if(TableColumnConstants.TEXTAREA.equals(tableColumn.getCategory())) {
				result.append("multiline:true,");
			}
			if(StringConstant.NO.equals(tableColumn.getIsEdit())) {
				result.append("readonly:true,");
			}
			if(StringConstant.DISABLE.equals(tableColumn.getIsEdit())) {
				result.append("disabled:true,");
			}
			if(StringConstant.NO.equals(tableColumn.getIsNull())) {
				result.append("required:true,");
			}
			if(StringUtils.isNoneBlank(tableColumn.getValidType())) {
				result.append("validType:'"+tableColumn.getValidType()+"',");
			}
			if(StringUtils.isNoneBlank(tableColumn.getDefaultValue())) {
				result.append("value:'"+tableColumn.getDefaultValue()+"',");
			}
			if(StringUtils.isNoneBlank(tableColumn.getSettings())) {
				result.append(tableColumn.getSettings());
			}
			result.append("\" ");
			if(StringUtils.isNoneBlank(tableColumn.getHtmlInner())) {
				result.append(tableColumn.getHtmlInner()+" ");
			}
			result.append("/>");
		}
		if(TableColumnConstants.NUMBERBOX.equals(tableColumn.getCategory())) {
			result.append("<input class=\"easyui-numberbox\" id=\"");
			result.append(column);
			result.append("\" name=\"");
			result.append(column);
			result.append("\" data-options=\"tipPosition:'bottom',");
			if(StringConstant.NO.equals(tableColumn.getIsEdit())) {
				result.append("readonly:true,");
			}
			if(StringConstant.DISABLE.equals(tableColumn.getIsEdit())) {
				result.append("disabled:true,");
			}
			if(StringConstant.NO.equals(tableColumn.getIsNull())) {
				result.append("required:true,");
			}
			if(StringUtils.isNoneBlank(tableColumn.getValidType())) {
				result.append("validType:'"+tableColumn.getValidType()+"',");
			}
			if(StringUtils.isNoneBlank(tableColumn.getDefaultValue())) {
				result.append("value:'"+tableColumn.getDefaultValue()+"',");
			}
			if(StringUtils.isNoneBlank(tableColumn.getSettings())) {
				result.append(tableColumn.getSettings());
			}
			result.append("\" ");
			if(StringUtils.isNoneBlank(tableColumn.getHtmlInner())) {
				result.append(tableColumn.getHtmlInner()+" ");
			}
			result.append("/>");
		}
		if(TableColumnConstants.COMBOBOX.equals(tableColumn.getCategory()) || TableColumnConstants.RADIOBOX.equals(tableColumn.getCategory()) || TableColumnConstants.CHECKBOX.equals(tableColumn.getCategory())) {
			needCache = false;
			result.append("<input class=\"easyui-combobox\" id=\"");
			result.append(column);
			result.append("\" name=\"");
			result.append(column);
			result.append("\" data-options=\"tipPosition:'top',");
			result.append("editable:false,");
			if(StringConstant.NO.equals(tableColumn.getIsEdit())) {
				result.append("readonly:true,");
			} else if(StringConstant.DISABLE.equals(tableColumn.getIsEdit())) {
				result.append("disabled:true,");
			} else {
				result.append("icons: [{iconCls:'icon-clear',handler: function(e){$(e.data.target).combobox('clear');}}],");
			}
			if(StringConstant.NO.equals(tableColumn.getIsNull())) {
				result.append("required:true,");
			}
			if(StringUtils.isNoneBlank(tableColumn.getValidType())) {
				result.append("validType:'"+tableColumn.getValidType()+"',");
			}
			if(StringUtils.isNoneBlank(tableColumn.getDefaultValue())) {
				result.append("value:'"+tableColumn.getDefaultValue()+"',");
			}
			int dataCount = 0;
			if(StringUtils.isNoneBlank(tableColumn.getSettings())) {
				if(TableColumnConstants.CHECKBOX.equals(tableColumn.getCategory())) {
					result.append("multiple:true,");
				}
				String settings = tableColumn.getSettings();
				if(settings.indexOf("SQL>") != -1) {
					String[] settingsArr = settings.split("\\|");
					String sql = settingsArr[0].replace("SQL>", "");
					String value = settingsArr[1];
					String label = settingsArr[2];
					result.append("valueField: '"+StringUtils.toCamelCase(value)+"',textField: '"+StringUtils.toCamelCase(label)+"',");
					result.append("data: [");
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
					result.append("valueField: '"+value+"',textField: '"+label+"',");
					result.append("url:'/"+Global.getConfig("productName")+url+"'");
				} else {
					result.append("valueField: 'value',textField: 'label',");
					result.append("data: [");
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
			} else {
				result.append(",panelHeight:'130',");
			}
			result.append("\" ");
			if(StringUtils.isNoneBlank(tableColumn.getHtmlInner())) {
				result.append(tableColumn.getHtmlInner()+" ");
			}
			result.append("/>");
		}
		if(TableColumnConstants.COMBOTREE.equals(tableColumn.getCategory())) {
			needCache = false;
			result.append("<input class=\"easyui-combotree\" id=\"");
			result.append(column);
			result.append("\" name=\"");
			result.append(column);
			result.append("\" data-options=\"tipPosition:'top',");
			result.append("editable:false,");
			if(StringConstant.NO.equals(tableColumn.getIsEdit())) {
				result.append("readonly:true,");
			} else if(StringConstant.DISABLE.equals(tableColumn.getIsEdit())) {
				result.append("disabled:true,");
			} else {
				result.append("icons: [{iconCls:'icon-clear',handler: function(e){$(e.data.target).combobox('clear');}}],");
			}
			if(StringConstant.NO.equals(tableColumn.getIsNull())) {
				result.append("required:true,");
			}
			if(StringUtils.isNoneBlank(tableColumn.getValidType())) {
				result.append("validType:'"+tableColumn.getValidType()+"',");
			}
			if(StringUtils.isNoneBlank(tableColumn.getDefaultValue())) {
				result.append("value:'"+tableColumn.getDefaultValue()+"',");
			}
			int dataCount = 0;
			if(StringUtils.isNoneBlank(tableColumn.getSettings())) {
				if(TableColumnConstants.CHECKBOX.equals(tableColumn.getCategory())) {
					result.append("multiple:true,");
				}
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
						result.append("{value: '"+dict.getValue()+"',label: '"+dict.getLabel()+"',category:'"+dict.getCategory()+"'},");
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
						result.append("{id: '"+setArr[0]+"',text: '"+setArr[1]+"',pid:'"+setArr[2]+"'},");
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
			result.append("\" ");
			if(StringUtils.isNoneBlank(tableColumn.getHtmlInner())) {
				result.append(tableColumn.getHtmlInner()+" ");
			}
			result.append("/>");
		}
		if(TableColumnConstants.DATEBOX.equals(tableColumn.getCategory())) {
			result.append("<input class=\"date-input easyui-validatebox\" id=\"");
			result.append(column);
			result.append("\" name=\"");
			result.append(column);
			result.append("\" ");
			if(StringUtils.isNoneBlank(tableColumn.getHtmlInner())) {
				result.append(tableColumn.getHtmlInner()+" ");
			}
			result.append("\" data-options=\"tipPosition:'bottom',");
			if(StringConstant.NO.equals(tableColumn.getIsNull())) {
				result.append("required:true,");
			}
			result.append("\"");
			if(StringUtils.isNoneBlank(tableColumn.getDefaultValue())) {
				result.append(" value=\""+tableColumn.getDefaultValue()+"\"");
			}
			if(StringConstant.NO.equals(tableColumn.getIsEdit())) {
				result.append(" readonly=true");
			}
			if(StringConstant.DISABLE.equals(tableColumn.getIsEdit())) {
				result.append(" disabled = true");
			}
			if(!StringConstant.NO.equals(tableColumn.getIsEdit())) {
				if(StringUtils.isNoneBlank(tableColumn.getSettings())) {
					result.append(" onClick=\"WdatePicker({");
					result.append(tableColumn.getSettings().replaceAll(",maxDate:''", "").replaceAll(",minDate:''", ""));
					result.append("})\"");
				} else {
					result.append(" onClick=\"WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})\"");
				}
			}
			result.append("/>");
		}
		if(TableColumnConstants.HTMLDESIGN.equals(tableColumn.getCategory())) {
			result.append("<script id=\"");
			result.append(column);
			result.append("\" name=\"");
			result.append(column);
			result.append("\" type=\"text/plain\">");
			if(StringUtils.isNoneBlank(tableColumn.getDefaultValue())) {
				result.append(tableColumn.getDefaultValue());
			}
			result.append("</script>");
			result.append("<script type=\"text/javascript\">");
			result.append("var "+column+" = UE.getEditor('"+column+"', {autoHeight: false});");
			if(StringUtils.isNoneBlank(tableColumn.getSettings())) {
				result.append(""+column+".ready(function() {"+column+".setHeight("+tableColumn.getSettings()+");});");
			}
			result.append("</script>");
		}
		if(TableColumnConstants.SELECTBUTTON.equals(tableColumn.getCategory())) {
			result.append("<input class=\"easyui-textbox\" id=\"");
			result.append(column);
			result.append("\" name=\"");
			result.append(column);
			result.append("\" data-options=\"tipPosition:'bottom',");
			if(StringConstant.NO.equals(tableColumn.getIsEdit())) {
				result.append("readonly:true,");
			}
			if(StringConstant.DISABLE.equals(tableColumn.getIsEdit())) {
				result.append("disabled:true,");
			}
			if(StringConstant.NO.equals(tableColumn.getIsNull())) {
				result.append("required:true,");
			}
			if(StringUtils.isNoneBlank(tableColumn.getValidType())) {
				result.append("validType:'"+tableColumn.getValidType()+"',");
			}
			if(StringUtils.isNoneBlank(tableColumn.getDefaultValue())) {
				result.append("value:'"+tableColumn.getDefaultValue()+"',");
			}
			if(StringUtils.isNoneBlank(tableColumn.getSettings())) {
				String settings = tableColumn.getSettings();
				if(settings.indexOf("SQL>") != -1) {
					String[] settingsArr = settings.split("\\|");
					String sql = settingsArr[0].replace("SQL>", "");
					String mapper = settingsArr[1];
					result.append("iconWidth: 22,icons: [{iconCls:'icon-search',handler: function(e){");
					result.append("var sqlStr = '"+URLEncoder.encode(sql, "UTF-8")+"';");
					result.append("var mapperStr = '"+URLEncoder.encode(mapper, "UTF-8")+"';");
					result.append("var url = '/"+Global.getConfig("productName")+"/static/form/mod/sqlMapper.jsp?sqlStr='+sqlStr+'&mapperStr='+mapperStr;layerOpenSqlMapper(url);}}]");
				}
			}
			result.append("\" ");
			if(StringUtils.isNoneBlank(tableColumn.getHtmlInner())) {
				result.append(tableColumn.getHtmlInner()+" ");
			}
			result.append("/>");
		}
		result.append("<span id=\"msg-"+column+"\" class=\"err-msg\"></span>");
		if(needCache) {
			CacheUtils.put(TableColumnConstants.CACHE_FORM+TableColumnConstants.CACHE_SPLIT+TableColumnConstants.CACHE_EASYUI+TableColumnConstants.CACHE_SPLIT+tableColumn.getTableName()+TableColumnConstants.CACHE_SPLIT+tableColumn.getName(), result);
		}
		return result;
	}
	
	private static StringBuffer getLayerUIStr(TableColumn tableColumn) throws Exception {
		StringBuffer result = (StringBuffer)CacheUtils.get(TableColumnConstants.CACHE_FORM+TableColumnConstants.CACHE_SPLIT+TableColumnConstants.CACHE_EASYUI+TableColumnConstants.CACHE_SPLIT+tableColumn.getTableName()+TableColumnConstants.CACHE_SPLIT+tableColumn.getName());
		if (result !=  null){
			return result;
		}
		if(StringUtils.isNoneBlank(tableColumn.getIsEdit()) && StringConstant.HIDDEN.equals(tableColumn.getIsEdit())) {
			return new StringBuffer();
		}
		boolean needCache = true;
		result = new StringBuffer();
		String column = tableColumn.getName();
		column = StringUtils.toCamelCase(column);
		result.append("<label class=\"layui-form-label\">"+tableColumn.getComments()+"</label>");
		result.append("<div class=\"layui-input-block\">");
		if(TableColumnConstants.TEXTBOX.equals(tableColumn.getCategory())) {
			result.append("<input class=\"layui-input\" id=\"");
			result.append(column);
			result.append("\" name=\"");
			result.append(column);
			result.append("\" type=\"text\" autocomplete=\"off\" ");
			if(StringConstant.NO.equals(tableColumn.getIsEdit())) {
				result.append("readonly=true ");
			}
			if(StringConstant.DISABLE.equals(tableColumn.getIsEdit())) {
				result.append("disabled=disabled ");
			}
			if(StringConstant.NO.equals(tableColumn.getIsNull())) {
				result.append("required ");
			}
			if(StringUtils.isNoneBlank(tableColumn.getValidType())) {
				result.append("lay-verify=\""+tableColumn.getValidType()+"\" ");
			}
			if(StringUtils.isNoneBlank(tableColumn.getDefaultValue())) {
				result.append("value=\""+tableColumn.getDefaultValue()+"\" ");
			}
			if(StringUtils.isNoneBlank(tableColumn.getSettings())) {
				result.append(tableColumn.getSettings().replace("prompt:'", "placeholder=\"").replace("'", "\" "));
			}
			if(StringUtils.isNoneBlank(tableColumn.getHtmlInner())) {
				result.append(tableColumn.getHtmlInner()+" ");
			}
			result.append("/>");
		}
		if(TableColumnConstants.TEXTAREA.equals(tableColumn.getCategory())) {
			result.append("<textarea class=\"layui-textarea\" id=\"");
			result.append(column);
			result.append("\" name=\"");
			result.append(column);
			result.append("\" ");
			if(StringConstant.NO.equals(tableColumn.getIsEdit())) {
				result.append("readonly=true ");
			}
			if(StringConstant.DISABLE.equals(tableColumn.getIsEdit())) {
				result.append("disabled=disabled ");
			}
			if(StringConstant.NO.equals(tableColumn.getIsNull())) {
				result.append("required ");
			}
			if(StringUtils.isNoneBlank(tableColumn.getValidType())) {
				result.append("lay-verify=\""+tableColumn.getValidType()+"\" ");
			}
			if(StringUtils.isNoneBlank(tableColumn.getSettings())) {
				result.append(tableColumn.getSettings().replace("prompt:'", "placeholder=\"").replace("'", "\" "));
			}
			if(StringUtils.isNoneBlank(tableColumn.getHtmlInner())) {
				result.append(tableColumn.getHtmlInner()+" ");
			}
			result.append(">");
			if(StringUtils.isNoneBlank(tableColumn.getDefaultValue())) {
				result.append(tableColumn.getDefaultValue());
			}
			result.append("</textarea>");
		}
		if(TableColumnConstants.NUMBERBOX.equals(tableColumn.getCategory())) {
			result.append("<input class=\"layui-input\" id=\"");
			result.append(column);
			result.append("\" name=\"");
			result.append(column);
			result.append("\" ");
			if(StringConstant.NO.equals(tableColumn.getIsEdit())) {
				result.append("readonly=true ");
			}
			if(StringConstant.DISABLE.equals(tableColumn.getIsEdit())) {
				result.append("disabled=disabled ");
			}
			if(StringConstant.NO.equals(tableColumn.getIsNull())) {
				result.append("required ");
			}
			if(StringUtils.isNoneBlank(tableColumn.getValidType())) {
				result.append("lay-verify=\""+tableColumn.getValidType()+"\" ");
			}
			if(StringUtils.isNoneBlank(tableColumn.getDefaultValue())) {
				result.append("value=\""+tableColumn.getDefaultValue()+"\" ");
			}
			if(StringUtils.isNoneBlank(tableColumn.getSettings())) {
				result.append(tableColumn.getSettings().replace("prompt:'", "placeholder=\"").replace("'", "\" "));
			}
			if(StringUtils.isNoneBlank(tableColumn.getHtmlInner())) {
				result.append(tableColumn.getHtmlInner()+" ");
			}
			result.append("/>");
		}
		if(TableColumnConstants.COMBOBOX.equals(tableColumn.getCategory())) {
			needCache = false;
			result.append("<select id=\"");
			result.append(column);
			result.append("\" name=\"");
			result.append(column);
			result.append("\" lay-filter=\"aihao\" ");
			if(StringUtils.isNoneBlank(tableColumn.getHtmlInner())) {
				result.append(tableColumn.getHtmlInner()+" ");
			}
			if(StringConstant.NO.equals(tableColumn.getIsEdit())) {
				result.append("readonly=true ");
			}
			if(StringConstant.DISABLE.equals(tableColumn.getIsEdit())) {
				result.append("disabled=disabled ");
			}
			result.append(">");
			result.append("<option value=\"\"></option>");
			if(StringUtils.isNoneBlank(tableColumn.getSettings())) {
				String settings = tableColumn.getSettings();
				if(settings.indexOf("SQL>") != -1) {
					String[] settingsArr = settings.split("\\|");
					String sql = settingsArr[0].replace("SQL>", "");
					String value = settingsArr[1];
					String label = settingsArr[2];
					List<Map<Object, Object>> list = DBUtils.executeQuery(sql, false);
					for(Map<Object, Object> map : list) {
						String selected = "";
						if(StringUtils.isNoneBlank(tableColumn.getDefaultValue()) && tableColumn.getDefaultValue().equals(map.get(value.toUpperCase()))) {
							selected = "selected=\"\"";
						}
						result.append("<option value=\""+map.get(value.toUpperCase())+"\" "+selected+">"+map.get(label.toUpperCase())+"</option>");
					}
				} else if(settings.indexOf("DICT>") != -1) {
					List<Dict> dictList = DictUtils.getDictList(DictUtils.getDict(settings.replace("DICT>", "")).getValue());
					for(Dict dict : dictList) {
						String selected = "";
						if(StringUtils.isNoneBlank(tableColumn.getDefaultValue()) && tableColumn.getDefaultValue().equals(dict.getValue())) {
							selected = "selected=\"\"";
						}
						result.append("<option value=\""+dict.getValue()+"\" "+selected+">"+dict.getLabel()+"</option>");
					}
				} else if(settings.indexOf("URL>") != -1) {
					result.append("<option value=\"err\">Layui不支持URL配置</option>");
				} else {
					String[] settingsArr = settings.split("\\|");
					for(String set : settingsArr) {
						if(set.indexOf(":") == -1) {
							String selected = "";
							if(StringUtils.isNoneBlank(tableColumn.getDefaultValue()) && tableColumn.getDefaultValue().equals(set)) {
								selected = "selected=\"\"";
							}
							result.append("<option value=\""+set+"\" "+selected+">"+set+"</option>");
						} else {
							String[] setArr = set.split(":");
							String selected = "";
							if(StringUtils.isNoneBlank(tableColumn.getDefaultValue()) && tableColumn.getDefaultValue().equals(setArr[0])) {
								selected = "selected=\"\"";
							}
							result.append("<option value=\""+setArr[0]+"\" "+selected+">"+setArr[1]+"</option>");
						}
					}
				}
			}
			result.append("</select>");
		}
		if(TableColumnConstants.RADIOBOX.equals(tableColumn.getCategory())) {
			needCache = false;
			if(StringUtils.isNoneBlank(tableColumn.getSettings())) {
				String settings = tableColumn.getSettings();
				int count = 1;
				if(settings.indexOf("SQL>") != -1) {
					String[] settingsArr = settings.split("\\|");
					String sql = settingsArr[0].replace("SQL>", "");
					String value = settingsArr[1];
					String label = settingsArr[2];
					List<Map<Object, Object>> list = DBUtils.executeQuery(sql, false);
					for(Map<Object, Object> map : list) {
						String checked = "";
						if(StringUtils.isNoneBlank(tableColumn.getDefaultValue()) && tableColumn.getDefaultValue().equals(map.get(value.toUpperCase()))) {
							checked = "checked=\"\" ";
						}
						result.append("<input type=\"radio\" id=\""+column+(count++)+"\" name=\""+column+"\" value=\""+map.get(value.toUpperCase())+"\" title=\""+map.get(label.toUpperCase())+"\" "+checked);
						if(StringUtils.isNoneBlank(tableColumn.getHtmlInner())) {
							result.append(tableColumn.getHtmlInner()+" ");
						}
						if(StringConstant.NO.equals(tableColumn.getIsEdit())) {
							result.append("readonly=true ");
						}
						if(StringConstant.DISABLE.equals(tableColumn.getIsEdit())) {
							result.append("disabled=disabled ");
						}
						result.append("/>");
					}
				} else if(settings.indexOf("DICT>") != -1) {
					List<Dict> dictList = DictUtils.getDictList(DictUtils.getDict(settings.replace("DICT>", "")).getValue());
					for(Dict dict : dictList) {
						String checked = "";
						if(StringUtils.isNoneBlank(tableColumn.getDefaultValue()) && tableColumn.getDefaultValue().equals(dict.getValue())) {
							checked = "checked=\"\" ";
						}
						result.append("<input type=\"radio\" id=\""+column+(count++)+"\" name=\""+column+"\" value=\""+dict.getValue()+"\" title=\""+dict.getLabel()+"\" "+checked);
						if(StringUtils.isNoneBlank(tableColumn.getHtmlInner())) {
							result.append(tableColumn.getHtmlInner()+" ");
						}
						if(StringConstant.NO.equals(tableColumn.getIsEdit())) {
							result.append("readonly=true ");
						}
						if(StringConstant.DISABLE.equals(tableColumn.getIsEdit())) {
							result.append("disabled=disabled ");
						}
						result.append("/>");
					}
				} else if(settings.indexOf("URL>") != -1) {
					result.append("<option value=\"err\">Layui不支持URL配置</option>");
				} else {
					String[] settingsArr = settings.split("\\|");
					for(String set : settingsArr) {
						if(set.indexOf(":") == -1) {
							String checked = "";
							if(StringUtils.isNoneBlank(tableColumn.getDefaultValue()) && tableColumn.getDefaultValue().equals(set)) {
								checked = "checked=\"\" ";
							}
							result.append("<input type=\"radio\" id=\""+column+(count++)+"\" name=\""+column+"\" value=\""+set+"\" title=\""+set+"\" "+checked);
							if(StringUtils.isNoneBlank(tableColumn.getHtmlInner())) {
								result.append(tableColumn.getHtmlInner()+" ");
							}
							if(StringConstant.NO.equals(tableColumn.getIsEdit())) {
								result.append("readonly=true ");
							}
							if(StringConstant.DISABLE.equals(tableColumn.getIsEdit())) {
								result.append("disabled=disabled ");
							}
							result.append("/>");
						} else {
							String[] setArr = set.split(":");
							String checked = "";
							if(StringUtils.isNoneBlank(tableColumn.getDefaultValue()) && tableColumn.getDefaultValue().equals(setArr[0])) {
								checked = "checked=\"\" ";
							}
							result.append("<input type=\"radio\" id=\""+column+(count++)+"\" name=\""+column+"\" value=\""+setArr[0]+"\" title=\""+setArr[1]+"\" "+checked);
							if(StringUtils.isNoneBlank(tableColumn.getHtmlInner())) {
								result.append(tableColumn.getHtmlInner()+" ");
							}
							if(StringConstant.NO.equals(tableColumn.getIsEdit())) {
								result.append("readonly=true ");
							}
							if(StringConstant.DISABLE.equals(tableColumn.getIsEdit())) {
								result.append("disabled=disabled ");
							}
							result.append("/>");
						}
					}
				}
			}
		}if(TableColumnConstants.CHECKBOX.equals(tableColumn.getCategory())) {
			needCache = false;
			if(StringUtils.isNoneBlank(tableColumn.getSettings())) {
				String settings = tableColumn.getSettings();
				int count = 1;
				if(settings.indexOf("SQL>") != -1) {
					String[] settingsArr = settings.split("\\|");
					String sql = settingsArr[0].replace("SQL>", "");
					String value = settingsArr[1];
					String label = settingsArr[2];
					List<Map<Object, Object>> list = DBUtils.executeQuery(sql, false);
					for(Map<Object, Object> map : list) {
						String checked = "";
						if(StringUtils.isNoneBlank(tableColumn.getDefaultValue()) && tableColumn.getDefaultValue().equals(map.get(value.toUpperCase()))) {
							checked = "checked=\"\" ";
						}
						result.append("<input type=\"checkbox\" id=\""+column+(count++)+"\" name=\""+column+"\" value=\""+map.get(value.toUpperCase())+"\" title=\""+map.get(label.toUpperCase())+"\" "+checked);
						if(StringUtils.isNoneBlank(tableColumn.getHtmlInner())) {
							result.append(tableColumn.getHtmlInner()+" ");
						}
						if(StringConstant.NO.equals(tableColumn.getIsEdit())) {
							result.append("readonly=true ");
						}
						if(StringConstant.DISABLE.equals(tableColumn.getIsEdit())) {
							result.append("disabled=disabled ");
						}
						result.append("/>");
					}
				} else if(settings.indexOf("DICT>") != -1) {
					List<Dict> dictList = DictUtils.getDictList(DictUtils.getDict(settings.replace("DICT>", "")).getValue());
					for(Dict dict : dictList) {
						String checked = "";
						if(StringUtils.isNoneBlank(tableColumn.getDefaultValue()) && tableColumn.getDefaultValue().equals(dict.getValue())) {
							checked = "checked=\"\" ";
						}
						result.append("<input type=\"checkbox\" id=\""+column+(count++)+"\" name=\""+column+"\" value=\""+dict.getValue()+"\" title=\""+dict.getLabel()+"\" "+checked);
						if(StringUtils.isNoneBlank(tableColumn.getHtmlInner())) {
							result.append(tableColumn.getHtmlInner()+" ");
						}
						if(StringConstant.NO.equals(tableColumn.getIsEdit())) {
							result.append("readonly=true ");
						}
						if(StringConstant.DISABLE.equals(tableColumn.getIsEdit())) {
							result.append("disabled=disabled ");
						}
						result.append("/>");
					}
				} else if(settings.indexOf("URL>") != -1) {
					result.append("<option value=\"err\">Layui不支持URL配置</option>");
				} else {
					String[] settingsArr = settings.split("\\|");
					for(String set : settingsArr) {
						if(set.indexOf(":") == -1) {
							String checked = "";
							if(StringUtils.isNoneBlank(tableColumn.getDefaultValue()) && tableColumn.getDefaultValue().equals(set)) {
								checked = "checked=\"\" ";
							}
							result.append("<input type=\"checkbox\" id=\""+column+(count++)+"\" name=\""+column+"\" value=\""+set+"\" title=\""+set+"\" "+checked);
							if(StringUtils.isNoneBlank(tableColumn.getHtmlInner())) {
								result.append(tableColumn.getHtmlInner()+" ");
							}
							if(StringConstant.NO.equals(tableColumn.getIsEdit())) {
								result.append("readonly=true ");
							}
							if(StringConstant.DISABLE.equals(tableColumn.getIsEdit())) {
								result.append("disabled=disabled ");
							}
							result.append("/>");
						} else {
							String[] setArr = set.split(":");
							String checked = "";
							if(StringUtils.isNoneBlank(tableColumn.getDefaultValue()) && tableColumn.getDefaultValue().equals(setArr[0])) {
								checked = "checked=\"\"";
							}
							result.append("<input type=\"checkbox\" id=\""+column+(count++)+"\" name=\""+column+"\" value=\""+setArr[0]+"\" title=\""+setArr[1]+"\" "+checked);
							if(StringUtils.isNoneBlank(tableColumn.getHtmlInner())) {
								result.append(tableColumn.getHtmlInner()+" ");
							}
							if(StringConstant.NO.equals(tableColumn.getIsEdit())) {
								result.append("readonly=true ");
							}
							if(StringConstant.DISABLE.equals(tableColumn.getIsEdit())) {
								result.append("disabled=disabled ");
							}
							result.append("/>");
						}
					}
				}
			}
		}
		if(TableColumnConstants.COMBOTREE.equals(tableColumn.getCategory())) {
			needCache = false;
			result.append("Layui不支持此插件");
		}
		if(TableColumnConstants.DATEBOX.equals(tableColumn.getCategory())) {
			result.append("<input class=\"layui-input\" id=\"");
			result.append(column);
			result.append("\" name=\"");
			result.append(column);
			result.append("\" ");
			if(StringUtils.isNoneBlank(tableColumn.getHtmlInner())) {
				result.append(tableColumn.getHtmlInner()+" ");
			}
			if(StringUtils.isNoneBlank(tableColumn.getDefaultValue())) {
				result.append("value=\""+tableColumn.getDefaultValue()+"\" ");
			}
			if(StringConstant.NO.equals(tableColumn.getIsEdit())) {
				result.append(" readonly=true");
			}
			if(StringConstant.DISABLE.equals(tableColumn.getIsEdit())) {
				result.append(" disabled = true");
			}
			if(StringConstant.NO.equals(tableColumn.getIsNull())) {
				result.append("required ");
			}
			if(StringUtils.isNoneBlank(tableColumn.getValidType())) {
				result.append("lay-verify=\""+tableColumn.getValidType()+"\" ");
			}
			if(!StringConstant.NO.equals(tableColumn.getIsEdit())) {
				if(StringUtils.isNoneBlank(tableColumn.getSettings())) {
					result.append(" onClick=\"WdatePicker({");
					result.append(tableColumn.getSettings().replaceAll(",maxDate:''", "").replaceAll(",minDate:''", ""));
					result.append("})\"");
				} else {
					result.append(" onClick=\"WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})\"");
				}
			}
			result.append("/>");
		}
		if(TableColumnConstants.HTMLDESIGN.equals(tableColumn.getCategory())) {
			result.append("<script id=\"");
			result.append(column);
			result.append("\" name=\"");
			result.append(column);
			result.append("\" type=\"text/plain\">");
			if(StringUtils.isNoneBlank(tableColumn.getDefaultValue())) {
				result.append(tableColumn.getDefaultValue());
			}
			result.append("</script>");
			result.append("<script type=\"text/javascript\">");
			result.append("var "+column+" = UE.getEditor('"+column+"', {autoHeight: false});");
			if(StringUtils.isNoneBlank(tableColumn.getSettings())) {
				result.append(""+column+".ready(function() {"+column+".setHeight("+tableColumn.getSettings()+");});");
			}
			result.append("</script>");
		}
		if(TableColumnConstants.SELECTBUTTON.equals(tableColumn.getCategory())) {
			needCache = false;
			result.append("Layui不支持此插件");
		}
		result.append("</div>");
		result.append("<span id=\"msg-"+column+"\" class=\"err-msg\"></span>");
		if(needCache) {
			CacheUtils.put(TableColumnConstants.CACHE_FORM+TableColumnConstants.CACHE_SPLIT+TableColumnConstants.CACHE_EASYUI+TableColumnConstants.CACHE_SPLIT+tableColumn.getTableName()+TableColumnConstants.CACHE_SPLIT+tableColumn.getName(), result);
		}
		return result;
	}
}

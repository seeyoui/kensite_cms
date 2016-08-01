package com.seeyoui.kensite.common.taglib.util;

import com.seeyoui.kensite.common.constants.StringConstant;
import com.seeyoui.kensite.common.taglib.constants.TableColumnConstants;
import com.seeyoui.kensite.common.util.CacheUtils;
import com.seeyoui.kensite.common.util.StringUtils;
import com.seeyoui.kensite.framework.mod.tableColumn.domain.TableColumn;

/**
 * 表单控件工具类
 * @author Ken
 * @version 2015-11-10
 */
public class QueryJsUtils {
	
	public static StringBuffer getTableColumnStr(TableColumn tableColumn) throws Exception {
		TableColumn tc = TagCacheUtils.getTableColumn(tableColumn);
		if(tc == null || StringConstant.NO.equals(tc.getIsQuery())) {
			return null;
		}
		StringBuffer result = getEasyUIStr(tc);
		return result;
	}
	
	private static StringBuffer getEasyUIStr(TableColumn tableColumn) throws Exception {
		StringBuffer result = (StringBuffer)CacheUtils.get(TableColumnConstants.CACHE_QUERY_JS+TableColumnConstants.CACHE_SPLIT+TableColumnConstants.CACHE_EASYUI+TableColumnConstants.CACHE_SPLIT+tableColumn.getTableName()+TableColumnConstants.CACHE_SPLIT+tableColumn.getName());
		if (result !=  null){
			return result;
		}
		boolean needCache = true;
		result = new StringBuffer();
		String column = tableColumn.getName();
		column = StringUtils.toCamelCase(column);
		result.append(column+" : $('#sel_"+column+"').");
		if(TableColumnConstants.TEXTBOX.equals(tableColumn.getCategory()) || TableColumnConstants.TEXTAREA.equals(tableColumn.getCategory()) || TableColumnConstants.HTMLDESIGN.equals(tableColumn.getCategory()) || TableColumnConstants.SELECTBUTTON.equals(tableColumn.getCategory())) {
			result.append("textbox('getValue')");
		}
		if(TableColumnConstants.NUMBERBOX.equals(tableColumn.getCategory())) {
			result.append("numberbox('getValue')");
		}
		if(TableColumnConstants.COMBOBOX.equals(tableColumn.getCategory())) {
			result.append("combobox('getValue')");
		}
		if(TableColumnConstants.RADIOBOX.equals(tableColumn.getCategory())) {
			result.append("combobox('getValue')");
		}
		if(TableColumnConstants.CHECKBOX.equals(tableColumn.getCategory())) {
			result.append("combobox('getValue')");
		}
		if(TableColumnConstants.DATEBOX.equals(tableColumn.getCategory())) {
			result.append("val()");
		}
		if(TableColumnConstants.COMBOTREE.equals(tableColumn.getCategory())) {
			result.append("combotree('getValue')");
		}
		result.append(",");
		if(needCache) {
			CacheUtils.put(TableColumnConstants.CACHE_QUERY_JS+TableColumnConstants.CACHE_SPLIT+TableColumnConstants.CACHE_EASYUI+TableColumnConstants.CACHE_SPLIT+tableColumn.getTableName()+TableColumnConstants.CACHE_SPLIT+tableColumn.getName(), result);
		}
		return result;
	}
}

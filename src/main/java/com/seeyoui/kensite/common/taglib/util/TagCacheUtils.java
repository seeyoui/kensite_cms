package com.seeyoui.kensite.common.taglib.util;

import com.seeyoui.kensite.common.taglib.constants.TableColumnConstants;
import com.seeyoui.kensite.common.util.CacheUtils;
import com.seeyoui.kensite.common.util.SpringContextHolder;
import com.seeyoui.kensite.framework.mod.tableColumn.domain.TableColumn;
import com.seeyoui.kensite.framework.mod.tableColumn.persistence.TableColumnMapper;

public class TagCacheUtils {

	private static TableColumnMapper tableColumnMapper = SpringContextHolder.getBean(TableColumnMapper.class);
	
	public static TableColumn getTableColumn(TableColumn tableColumn){
		TableColumn tc = null;
		if (tableColumn!=null){
			tc = (TableColumn)CacheUtils.get(TableColumnConstants.CACHE_COLUMN+TableColumnConstants.CACHE_SPLIT+tableColumn.getTableName()+TableColumnConstants.CACHE_SPLIT+tableColumn.getName());
			if (tc == null){
				tc = tableColumnMapper.findOne(tableColumn);
				CacheUtils.put(TableColumnConstants.CACHE_COLUMN+TableColumnConstants.CACHE_SPLIT+tableColumn.getTableName()+TableColumnConstants.CACHE_SPLIT+tableColumn.getName(), tc);
			}
		}
		return tc;
	}
	
	public static void removeCache(TableColumn tableColumn) {
		CacheUtils.remove(TableColumnConstants.CACHE_COLUMN+TableColumnConstants.CACHE_SPLIT+tableColumn.getTableName()+TableColumnConstants.CACHE_SPLIT+tableColumn.getName());
		CacheUtils.remove(TableColumnConstants.CACHE_LIST+TableColumnConstants.CACHE_SPLIT+TableColumnConstants.CACHE_EASYUI+TableColumnConstants.CACHE_SPLIT+tableColumn.getTableName()+TableColumnConstants.CACHE_SPLIT+tableColumn.getName());
		CacheUtils.remove(TableColumnConstants.CACHE_FORM+TableColumnConstants.CACHE_SPLIT+TableColumnConstants.CACHE_EASYUI+TableColumnConstants.CACHE_SPLIT+tableColumn.getTableName()+TableColumnConstants.CACHE_SPLIT+tableColumn.getName());
		CacheUtils.remove(TableColumnConstants.CACHE_QUERY+TableColumnConstants.CACHE_SPLIT+TableColumnConstants.CACHE_EASYUI+TableColumnConstants.CACHE_SPLIT+tableColumn.getTableName()+TableColumnConstants.CACHE_SPLIT+tableColumn.getName());
		CacheUtils.remove(TableColumnConstants.CACHE_QUERY_JS+TableColumnConstants.CACHE_SPLIT+TableColumnConstants.CACHE_EASYUI+TableColumnConstants.CACHE_SPLIT+tableColumn.getTableName()+TableColumnConstants.CACHE_SPLIT+tableColumn.getName());
	}
}

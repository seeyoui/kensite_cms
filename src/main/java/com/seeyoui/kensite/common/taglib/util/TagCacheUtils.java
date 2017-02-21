package com.seeyoui.kensite.common.taglib.util;

import org.springframework.beans.BeanUtils;

import com.seeyoui.kensite.common.taglib.constants.TableColumnConstants;
import com.seeyoui.kensite.common.util.CacheUtils;
import com.seeyoui.kensite.common.util.SpringContextHolder;
import com.seeyoui.kensite.framework.mod.tableColumn.domain.TableColumn;
import com.seeyoui.kensite.framework.mod.tableColumn.persistence.TableColumnMapper;

public class TagCacheUtils {

	private static TableColumnMapper tableColumnMapper = SpringContextHolder.getBean(TableColumnMapper.class);
	
	public static TableColumn getTableColumn(TableColumn tableColumn){
		if(tableColumn.getName().equals("AREA_ID")) {
			System.out.println("==");
		}
		TableColumn tc = null;
		TableColumn tbc = null;
		if (tableColumn!=null){
			tc = (TableColumn)CacheUtils.get(TableColumnConstants.CACHE_COLUMN+TableColumnConstants.CACHE_SPLIT+tableColumn.getTableName()+TableColumnConstants.CACHE_SPLIT+tableColumn.getName());
			if (tc == null){
				tc = tableColumnMapper.findOne(tableColumn);
				if(tc != null) {
					CacheUtils.put(TableColumnConstants.CACHE_COLUMN+TableColumnConstants.CACHE_SPLIT+tableColumn.getTableName()+TableColumnConstants.CACHE_SPLIT+tableColumn.getName(), tc);
					tbc = new TableColumn();
					BeanUtils.copyProperties(tc, tbc);
				} else {
					CacheUtils.put(TableColumnConstants.CACHE_COLUMN+TableColumnConstants.CACHE_SPLIT+tableColumn.getTableName()+TableColumnConstants.CACHE_SPLIT+tableColumn.getName(), null);
				}
			} else {
				tbc = new TableColumn();
				BeanUtils.copyProperties(tc, tbc);
			}
		}
		return tbc;
	}
	
	public static void removeCache(TableColumn tableColumn) {
		CacheUtils.remove(TableColumnConstants.CACHE_COLUMN+TableColumnConstants.CACHE_SPLIT+tableColumn.getTableName()+TableColumnConstants.CACHE_SPLIT+tableColumn.getName());
		CacheUtils.remove(TableColumnConstants.CACHE_LIST+TableColumnConstants.CACHE_SPLIT+TableColumnConstants.CACHE_EASYUI+TableColumnConstants.CACHE_SPLIT+tableColumn.getTableName()+TableColumnConstants.CACHE_SPLIT+tableColumn.getName());
		CacheUtils.remove(TableColumnConstants.CACHE_FORM+TableColumnConstants.CACHE_SPLIT+TableColumnConstants.CACHE_EASYUI+TableColumnConstants.CACHE_SPLIT+tableColumn.getTableName()+TableColumnConstants.CACHE_SPLIT+tableColumn.getName());
		CacheUtils.remove(TableColumnConstants.CACHE_QUERY+TableColumnConstants.CACHE_SPLIT+TableColumnConstants.CACHE_EASYUI+TableColumnConstants.CACHE_SPLIT+tableColumn.getTableName()+TableColumnConstants.CACHE_SPLIT+tableColumn.getName());
		CacheUtils.remove(TableColumnConstants.CACHE_QUERY_JS+TableColumnConstants.CACHE_SPLIT+TableColumnConstants.CACHE_EASYUI+TableColumnConstants.CACHE_SPLIT+tableColumn.getTableName()+TableColumnConstants.CACHE_SPLIT+tableColumn.getName());
	}
}

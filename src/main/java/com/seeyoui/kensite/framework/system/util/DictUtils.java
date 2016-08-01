package com.seeyoui.kensite.framework.system.util;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.seeyoui.kensite.common.base.persistence.JsonMapper;
import com.seeyoui.kensite.common.util.CacheUtils;
import com.seeyoui.kensite.common.util.SpringContextHolder;
import com.seeyoui.kensite.framework.plugin.dict.domain.Dict;
import com.seeyoui.kensite.framework.plugin.dict.persistence.DictMapper;

/**
 * 字典工具类
 * @author ThinkGem
 * @version 2013-5-29
 */
public class DictUtils {
	
	private static DictMapper dictMapper = SpringContextHolder.getBean(DictMapper.class);

	public static final String CACHE_DICT_MAP = "dictMap";
	public static final String CACHE_DICT_LIST = "dictList";
	
	public static String getDictLabel(String value, String category, String defaultValue){
		if (StringUtils.isNotBlank(category) && StringUtils.isNotBlank(value)){
			for (Dict dict : getDictList(category)){
				if (category.equals(dict.getCategory()) && value.equals(dict.getValue())){
					return dict.getLabel();
				}
			}
		}
		return defaultValue;
	}
	
	public static String getDictLabels(String values, String category, String defaultValue){
		if (StringUtils.isNotBlank(category) && StringUtils.isNotBlank(values)){
			List<String> valueList = Lists.newArrayList();
			for (String value : StringUtils.split(values, ",")){
				valueList.add(getDictLabel(value, category, defaultValue));
			}
			return StringUtils.join(valueList, ",");
		}
		return defaultValue;
	}

	public static String getDictValue(String label, String category, String defaultLabel){
		if (StringUtils.isNotBlank(category) && StringUtils.isNotBlank(label)){
			for (Dict dict : getDictList(category)){
				if (category.equals(dict.getCategory()) && label.equals(dict.getLabel())){
					return dict.getValue();
				}
			}
		}
		return defaultLabel;
	}

	public static Dict getDict(String id){
		if (StringUtils.isNotBlank(id)){
			for (Dict dict : getDictList()){
				if(dict.getId().equals(id)) {
					return dict;
				}
			}
		}
		return null;
	}

	public static List<Dict> getDictList(){
		@SuppressWarnings("unchecked")
		List<Dict> dictList = (List<Dict>)CacheUtils.get(CACHE_DICT_LIST);
		if(dictList==null) {
			dictList = dictMapper.findAll(new Dict());
			CacheUtils.put(CACHE_DICT_LIST, dictList);
		}
		return dictList;
	}
	
	public static List<Dict> getDictList(String category){
		@SuppressWarnings("unchecked")
		Map<String, List<Dict>> dictMap = (Map<String, List<Dict>>)CacheUtils.get(CACHE_DICT_MAP);
		if (dictMap==null){
			dictMap = Maps.newHashMap();
			for (Dict dict : dictMapper.findAll(new Dict())){
				List<Dict> dictList = dictMap.get(dict.getCategory());
				if (dictList != null){
					dictList.add(dict);
				}else{
					dictMap.put(dict.getCategory(), Lists.newArrayList(dict));
				}
			}
			CacheUtils.put(CACHE_DICT_MAP, dictMap);
		}
		List<Dict> dictList = dictMap.get(category);
		if (dictList == null){
			dictList = Lists.newArrayList();
		}
		return dictList;
	}
	
	/**
	 * 返回字典列表（JSON）
	 * @param category
	 * @return
	 */
	public static String getDictListJson(String category){
		return JsonMapper.toJsonString(getDictList(category));
	}
	
}

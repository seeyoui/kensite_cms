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
import com.seeyoui.kensite.framework.plugin.skins.domain.Skins;
import com.seeyoui.kensite.framework.plugin.skins.persistence.SkinsMapper;

/**
 * 字典工具类
 * @author ThinkGem
 * @version 2013-5-29
 */
public class SkinsUtils {
	
	private static SkinsMapper skinsMapper = SpringContextHolder.getBean(SkinsMapper.class);

	public static final String CACHE_SYS_SKINS = "sysSkins";
	
	/**
	 * 获得当前系统皮肤
	 * @return
	 */
	public static Skins getCurSysSkins(){
		Skins skins = (Skins)CacheUtils.get(CACHE_SYS_SKINS);
		if (skins == null){
			skins = skinsMapper.findCurrent();
			CacheUtils.put(CACHE_SYS_SKINS, skins);
		}
		return skins;
	}
	
	/**
	 * 清空系统皮肤缓存
	 */
	public static void cleanCache(){
		CacheUtils.remove(CACHE_SYS_SKINS);
	}
	
}

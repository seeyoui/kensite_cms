package com.seeyoui.kensite.bussiness.oauth.interfaceManager.util;

import java.util.List;

import com.seeyoui.kensite.bussiness.oauth.interfaceDesc.domain.InterfaceDesc;
import com.seeyoui.kensite.bussiness.oauth.interfaceDesc.persistence.InterfaceDescMapper;
import com.seeyoui.kensite.common.util.CacheUtils;
import com.seeyoui.kensite.common.util.SpringContextHolder;

/**
 * 接口工具类
 * @author SeeYoui
 * @version 2015-12-10
 */
public class OAuthUtils {
	
	private static InterfaceDescMapper interfaceDescMapper = SpringContextHolder.getBean(InterfaceDescMapper.class);

	public static final String CACHE_INTERFACE_LIST = "interfaceDescList";

	public static List<InterfaceDesc> getInterfaceDescList(){
		@SuppressWarnings("unchecked")
		List<InterfaceDesc> interfaceDescList = (List<InterfaceDesc>)CacheUtils.get(CACHE_INTERFACE_LIST);
		if(interfaceDescList==null) {
			interfaceDescList = interfaceDescMapper.findAll(new InterfaceDesc());
			CacheUtils.put(CACHE_INTERFACE_LIST, interfaceDescList);
		}
		return interfaceDescList;
	}
	
	public static void removeCache() {
		CacheUtils.remove(CACHE_INTERFACE_LIST);
	}
	
}

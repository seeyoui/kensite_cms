package com.seeyoui.kensite.common.util.excel.fieldType;

import java.util.List;

import com.google.common.collect.Lists;
import com.seeyoui.kensite.common.util.Collections3;
import com.seeyoui.kensite.common.util.SpringContextHolder;
import com.seeyoui.kensite.common.util.StringUtils;
import com.seeyoui.kensite.framework.system.domain.SysRole;
import com.seeyoui.kensite.framework.system.service.SysRoleService;

/**
 * 字段类型转换
 * @author ThinkGem
 * @version 2013-5-29
 */
public class RoleListType {

	private static SysRoleService sysRoleService = SpringContextHolder.getBean(SysRoleService.class);
	
	/**
	 * 获取对象值（导入）
	 */
	public static Object getValue(String val) {
		List<SysRole> roleList = Lists.newArrayList();
		List<SysRole> allRoleList = sysRoleService.findAll(null);
		for (String s : StringUtils.split(val, ",")){
			for (SysRole e : allRoleList){
				if (StringUtils.trimToEmpty(s).equals(e.getName())){
					roleList.add(e);
				}
			}
		}
		return roleList.size()>0?roleList:null;
	}

	/**
	 * 设置对象值（导出）
	 */
	public static String setValue(Object val) {
		if (val != null){
			@SuppressWarnings("unchecked")
			List<SysRole> roleList = (List<SysRole>)val;
			return Collections3.extractToString(roleList, "name", ", ");
		}
		return "";
	}
	
}

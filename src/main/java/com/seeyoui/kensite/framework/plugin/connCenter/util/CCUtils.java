package com.seeyoui.kensite.framework.plugin.connCenter.util;

import java.sql.Connection;

import com.seeyoui.kensite.common.util.DBUtils;
import com.seeyoui.kensite.common.util.SpringContextHolder;
import com.seeyoui.kensite.common.util.StringUtils;
import com.seeyoui.kensite.framework.plugin.connCenter.domain.ConnCenter;
import com.seeyoui.kensite.framework.plugin.connCenter.persistence.ConnCenterMapper;

public class CCUtils {
	private static ConnCenterMapper connCenterMapper = SpringContextHolder.getBean(ConnCenterMapper.class);
	
	public static Connection getConnection(String id) {
		ConnCenter connCenter = connCenterMapper.findOne(id);
		if(connCenter == null || StringUtils.isBlank(connCenter.getDriver())
				 || StringUtils.isBlank(connCenter.getUrl()) || StringUtils.isBlank(connCenter.getUsername())
				 || StringUtils.isBlank(connCenter.getPassword())) {
			return null;
		}
		Connection conn = null;
		try {
			conn = DBUtils.getConnection(connCenter.getDriver(), connCenter.getUrl(), connCenter.getUsername(), connCenter.getPassword());
		} catch (Exception e) {
			return null;
		}
		return conn;
	}
}

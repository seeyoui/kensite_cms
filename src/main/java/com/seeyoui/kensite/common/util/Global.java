package com.seeyoui.kensite.common.util;

import java.io.InputStream;
import java.util.Properties;

/**
 * 全局配置类 读取config.properties文件的属性值
 * 
 */

public class Global {
	static Properties prop = new Properties();
	static {
		ClassLoader loader = Global.class.getClassLoader();
		InputStream ips = loader
				.getResourceAsStream("kensite.properties");
		try {
			prop.load(ips);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取properties文件中的value
	 */
	public static String getConfig(String key) {
		return prop.getProperty(key);
	}

	public static String getConfig(String file, String key) {
		Properties prop = new Properties();
		ClassLoader loader = Global.class.getClassLoader();
		InputStream ips = loader
				.getResourceAsStream(file+".properties");
		try {
			prop.load(ips);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prop.getProperty(key);
	}
}

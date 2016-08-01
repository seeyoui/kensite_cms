package com.seeyoui.kensite.common.util;


import java.util.UUID;

public class GeneratorUUID {

	/**
	 * 系统主键生成规则，与流程引擎主键生成规则对应
	 * @return
	 */
	public static String getId() {
		return String.valueOf(UUID.randomUUID()).replaceAll("-", "");
	}
}

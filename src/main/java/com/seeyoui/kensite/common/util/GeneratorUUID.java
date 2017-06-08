package com.seeyoui.kensite.common.util;


import java.util.UUID;

import org.activiti.engine.impl.cfg.IdGenerator;

public class GeneratorUUID implements IdGenerator {

	@Override
	public String getNextId() {
		// TODO Auto-generated method stub
		return String.valueOf(UUID.randomUUID()).replaceAll("-", "");
	}
	
	/**
	 * 系统主键生成规则，与流程引擎主键生成规则对应
	 * @return
	 */
	public static String getId() {
		return String.valueOf(UUID.randomUUID()).replaceAll("-", "");
	}
}

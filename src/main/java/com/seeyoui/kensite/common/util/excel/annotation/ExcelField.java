package com.seeyoui.kensite.common.util.excel.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Excel注解定义
 * @author ThinkGem
 * @version 2013-03-10
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelField {

	/**
	 * 导出字段名（默认调用当前字段的“get”方法，如指定导出字段为对象，请填写“对象名.对象属性”，例：“area.name”、“office.name”）
	 */
	String value() default "";
	
	/**
	 * 导出字段标题（需要添加批注请用“**”分隔，标题**批注，仅对导出模板有效）
	 */
	String title();
	
	/**
	 * 字段类型（0：导出导入；1：仅导出；2：仅导入）
	 */
	int type() default 0;

	/**
	 * 导出字段对齐方式（0：自动；1：靠左；2：居中；3：靠右）
	 */
	int align() default 0;
	
	/**
	 * 导出字段字段排序（升序）
	 */
	int sort() default 0;

	/**
	 * 如果是字典类型，请设置字典的category值
	 * 优先级dict>constants>sql>mod
	 */
	String dict() default "";
	
	/**
	 * 如果是常量类型，请设置常量的值
	 * 优先级dict>constants>sql>mod
	 * 例：value1[:label1]|value2[:label2]|value3[:label3]
	 */
	String constants() default "";
	
	/**
	 * 如果是SQL类型，请设置常量的值
	 * 优先级dict>constants>sql>mod
	 * 例：select value,label from table|value|label
	 */
	String sql() default "";
	
	/**
	 * 如果是MOD配置类型，请设置常量的值
	 * 优先级dict>constants>sql>mod
	 * 例：table|column
	 */
	String mod() default "";
	
	/**
	 * 反射类型
	 */
	Class<?> fieldType() default Class.class;
	
	/**
	 * 字段归属组（根据分组导出导入）
	 */
	int[] groups() default {};
}

package com.seeyoui.kensite.common.dao.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 反射的Utils函数集合.
 * 
 * 提供侵犯隐私的直接读取filed的能力.
 */
public class BeanUtils {

	protected static Logger logger = LoggerFactory.getLogger(BeanUtils.class);

	private BeanUtils() {
	}

	/**
	 * 直接读取对象属性值,无视private/protected修饰符,不经过getter函数.
	 */
	public static Object getFieldValue(Object object, String fieldName) throws NoSuchFieldException {
		Field field = getDeclaredField(object, fieldName);
		if (!field.isAccessible()) {
			field.setAccessible(true);
		}

		Object result = null;
		try {
			result = field.get(object);
		} catch (IllegalAccessException e) {
			logger.error("不可能抛出的异常{}", e.getMessage());
		}
		return result;
	}

	/**
	 * 直接设置对象属性值,无视private/protected修饰符,不经过setter函数.
	 */
	public static void setFieldValue(Object object, String fieldName, Object value) throws NoSuchFieldException {
		Field field = getDeclaredField(object, fieldName);
		if (!field.isAccessible()) {
			field.setAccessible(true);
		}
		try {
			field.set(object, value);
		} catch (IllegalAccessException e) {
			logger.error("不可能抛出的异常:{}", e.getMessage());
		}
	}

	/**
	 * 循环向上转型,获取对象的DeclaredField.
	 */
	public static Field getDeclaredField(Object object, String fieldName) throws NoSuchFieldException {
		return getDeclaredField(object.getClass(), fieldName);
	}
	
	/**
	 * 循环向上转型,获取类的DeclaredField.
	 */
	@SuppressWarnings("unchecked")
	public static Field getDeclaredField(Class clazz, String fieldName) throws NoSuchFieldException {
		for (Class superClass = clazz; superClass != Object.class; superClass = superClass.getSuperclass()) {
			try {
				return superClass.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {
				// Field不在当前类定义,继续向上转型
			}
		}
		throw new NoSuchFieldException("No such field: " + clazz.getName() + '.' + fieldName);
	}

	/**
	 * 获取给定对象中该类及其父类的所有可读属性列表集合
	 */
	public static List<Field> getDeclaredFields(Object object) {
		return getDeclaredFields(object.getClass());
	}
	
	/**
	 * 获取给定类及其父类的所有可读属性列表集合
	 */
	public static List<Field> getDeclaredFields(Class<?> clazz) {
		Map<String, Field> fields = new HashMap<String, Field>();
		List<Field> fieldList = new ArrayList<Field>();
		
		//获取所有的类属性，添加到Map中
		insertField(clazz, fields, fieldList, clazz.getDeclaredFields());
		
		//遍历父类，搜索父类属性，添加到Map中，如果父类中包含了和子类相同的属性，则忽略它
		Class<?> superClazz = clazz.getSuperclass();
		while (superClazz != null) {
			insertField(clazz, fields, fieldList, superClazz.getDeclaredFields());
			
			superClazz = superClazz.getSuperclass();
		}
		
		return fieldList;
	}
	
	private static void insertField(Class<?> clazz, Map<String, Field> map, List<Field>list, Field[] fields) {
		// 添加属性值到Map中，如果Map中包含了同名的属性，则忽略它
		for (int i = 0; i < fields.length; i++) {
			String name = fields[i].getName();
			
			try {
				if (!BeanUtils.isFieldReadable(clazz, name))
					continue;
			} catch (Exception e) {
				continue;
			}
			
			if (!map.containsKey(name)) {
				map.put(name, fields[i]);
				list.add(fields[i]);
			}
		}
	}

	/**
	 * 获取给定对象中该类及其父类的所有可读属性名数组
	 */
	public static String[] getDeclaredFieldNames(Object object) {
		return getDeclaredFieldNames(object.getClass());
	}

	/**
	 * 获取给定类及其父类的所有可读属性名数组
	 */
	public static String[] getDeclaredFieldNames(Class<?> clazz) {
		List<Field> fields = getDeclaredFields(clazz);
		String[] fieldNames = new String[fields.size()];
		
		for (int i = 0, len = fields.size(); i < len; i++)
			fieldNames[i] = fields.get(i).getName();
		
		return fieldNames;
	}
	
	/**
	 * 判断给定的属性是否可读
	 *
	 * @param clazz
	 * @param fieldName
	 * @return
	 * @throws NoSuchFieldException 
	 * @throws SecurityException 
	 */
	@SuppressWarnings({ "unchecked" })
	public static boolean isFieldReadable(Class clazz, String fieldName) throws SecurityException, NoSuchFieldException {
		try {
			getFieldsReadMethod(clazz, fieldName);
		} catch (NoSuchMethodException e) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * 获取属性的getter方法
	 *
	 * @param clazz
	 * @param fieldName
	 * @return
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 */
	@SuppressWarnings("unchecked")
	public static Method getFieldsReadMethod(Class clazz, String fieldName) throws NoSuchFieldException, SecurityException, NoSuchMethodException {
		Field field = getDeclaredField(clazz, fieldName);
		Class type = field.getType();
		
		String readMethodName;
		if (type == boolean.class || type == null) {
		    readMethodName = "is" + capitalize(fieldName);
		} else {
		    readMethodName = "get" + capitalize(fieldName);
		}
		
		Method method = null;
		try {
			method = clazz.getMethod(readMethodName, new Class[0]);
		} catch (NoSuchMethodException e) {
			if (readMethodName.startsWith("get"))
				throw e;
			
			readMethodName = "get" + capitalize(fieldName);
			method = clazz.getMethod(readMethodName, new Class[0]);
		}
		
		return method;
	}

	/**
	 * 判断给定的属性是否可写
	 *
	 * @param clazz
	 * @param fieldName
	 * @return
	 * @throws NoSuchFieldException 
	 * @throws SecurityException 
	 */
	@SuppressWarnings({ "unchecked" })
	public static boolean isFieldWriteable(Class clazz, String fieldName) throws SecurityException, NoSuchFieldException {
		try {
			getFieldsWriteMethod(clazz, fieldName);
		} catch (NoSuchMethodException e) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * 获取属性的setter方法
	 *
	 * @param clazz
	 * @param fieldName
	 * @return
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 */
	@SuppressWarnings("unchecked")
	public static Method getFieldsWriteMethod(Class clazz, String fieldName) throws NoSuchFieldException, SecurityException, NoSuchMethodException {
		Field field = getDeclaredField(clazz, fieldName);
		Class type = field.getType();
		
		String writeMethodName = "set" + capitalize(fieldName);
		return clazz.getMethod(writeMethodName, new Class[]{ type });
	}
	
	/**
	 * 首字母大写
	 *
	 * @param str
	 * @return
	 */
	private static String capitalize(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}
}

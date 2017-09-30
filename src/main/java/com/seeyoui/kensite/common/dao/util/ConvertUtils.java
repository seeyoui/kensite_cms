package com.seeyoui.kensite.common.dao.util;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;

@SuppressWarnings("unchecked")
public class ConvertUtils {
	//模型字段类型定义
	public static final String TYPE_BOOLEAN = "boolean";
	public static final String TYPE_BYTE = "byte";
	public static final String TYPE_INTEGER = "integer";
	public static final String TYPE_LONG = "long";
	public static final String TYPE_STRING = "string";
	public static final String TYPE_TEXT = "text";
	public static final String TYPE_DECIMAL = "decimal";
	public static final String TYPE_FLOAT = "float";
	public static final String TYPE_DOUBLE = "double";
	public static final String TYPE_DATE = "date";
	public static final String TYPE_TIME = "time";
	public static final String TYPE_DATETIME = "datetime";

	private static Map<String, Class<?>> typeClassMap = new HashMap<String, Class<?>>();
	
	static {
		registerDateConverter();
		
		typeClassMap.put(TYPE_BOOLEAN, Boolean.class);
		typeClassMap.put(TYPE_BYTE, Byte.class);
		typeClassMap.put(TYPE_INTEGER, Integer.class);
		typeClassMap.put(TYPE_LONG, Long.class);
		typeClassMap.put(TYPE_DECIMAL, Double.class);
		typeClassMap.put(TYPE_FLOAT, Float.class);
		typeClassMap.put(TYPE_DOUBLE, Double.class);
		typeClassMap.put(TYPE_STRING, String.class);
		typeClassMap.put(TYPE_TEXT, String.class);
		typeClassMap.put(TYPE_DATE, Date.class);
		typeClassMap.put(TYPE_TIME, Date.class);
		typeClassMap.put(TYPE_DATETIME, Date.class);
	}
	
	public static Class<?> getTypeClass(String type) {
		Class<?> typeClass = typeClassMap.get(type);
		if (typeClass == null)
			throw new RuntimeException("不识别的数据类型[" + type + "]！");
		
		return typeClass;
	}

	/**
	 * 提取集合中的对象的属性(通过getter函数), 组合成List.
	 * 
	 * @param collection 来源集合.
	 * @param propertyName 要提取的属性名.
	 */
	public static List convertElementPropertyToList(final Collection collection, final String propertyName) {
		List list = new ArrayList();

		try {
			for (Object obj : collection) {
				list.add(PropertyUtils.getProperty(obj, propertyName));
			}
		} catch (Exception e) {
			throw convertReflectionExceptionToUnchecked(e);
		}

		return list;
	}

	/**
	 * 提取集合中的对象的属性(通过getter函数), 组合成由分割符分隔的字符串.
	 * 
	 * @param collection 来源集合.
	 * @param propertyName 要提取的属性名.
	 * @param separator 分隔符.
	 */
	public static String convertElementPropertyToString(final Collection collection, final String propertyName,
			final String separator) {
		List list = convertElementPropertyToList(collection, propertyName);
		return StringUtils.join(list, separator);
	}
	
	/**
	 * 基于Apache BeanUtils转换字符串到相应类型.
	 * 
	 * @param value 待转换的字符串.
	 * @param toType 转换目标类型.
	 */
	public static Object convert(String value, Class<?> toType) {
		if (toType == String.class)
			return value;
		
		try {
			return org.apache.commons.beanutils.ConvertUtils.convert(value.trim(), toType);
		} catch (Exception e) {
			if (Date.class.equals(toType)) {
				//如果转换对象为Date，则试着把字符串转换为long，再把long转换为Date
				try {
					Long v = (Long)org.apache.commons.beanutils.ConvertUtils.convert(value.trim(), Long.class);
					
					return org.apache.commons.beanutils.ConvertUtils.convert(v, toType);
				} catch (Exception e1) {
					throw convertReflectionExceptionToUnchecked(e);
				}
			}
			
			throw convertReflectionExceptionToUnchecked(e);
		}
	}

	public static Object convert(String value, String toType) {
		return convert(value, getTypeClass(toType));
	}

	/**
	 * 基于Apache BeanUtils转换对象到相应类型.
	 * 
	 * @param value 待转换的对象.
	 * @param toType 转换目标类型.
	 */
	public static Object convert(Object value, Class<?> toType) {
		if (value == null || value.getClass().equals(toType))
			return value;
		
		try {
			return org.apache.commons.beanutils.ConvertUtils.convert(value, toType);
		} catch (Exception e) {
			throw convertReflectionExceptionToUnchecked(e);
		}
	}

	public static Object convert(Object value, String toType) {
		return convert(value, getTypeClass(toType));
	}
	
	public static Object[] convert(String[] values, Class<?> toType) {
		if (values == null)
			return null;
		
		if (toType == String.class)
			return values;
		
		Object[] result = new Object[values.length];
		for(int i = 0, len = values.length; i < len; i++)
			result[i] = convert(values[i], toType);
		
		return result;
	}

	public static Object[] convert(String[] values, String toType) {
		return convert(values, getTypeClass(toType));
	}
	
	public static <T> T convert2(String value, Class<T> toType) {
		if (toType == String.class)
			return (T)value;
		
		try {
			return (T)org.apache.commons.beanutils.ConvertUtils.convert(value.trim(), toType);
		} catch (Exception e) {
			throw convertReflectionExceptionToUnchecked(e);
		}
	}
	
	public static <T> T[] convert2(String[] values, Class<T> toType) {
		if (values == null)
			return null;
		
		if (toType == String.class)
			return (T[])values;
		
		T[] result = (T[])Array.newInstance(toType,values.length); 

		for(int i = 0, len = values.length; i < len; i++)
			result[i] = (T)convert(values[i], toType);
		
		return result;
	}

	public static String convert(Object value) {
		return org.apache.commons.beanutils.ConvertUtils.convert(value);
	}

	/**
	 * 定义Apache BeanUtils日期Converter的格式: yyyy-MM-dd 或 yyyy-MM-dd HH:mm:ss
	 */
	private static void registerDateConverter() {
		DapDateConvert dc = new DapDateConvert();
		org.apache.commons.beanutils.ConvertUtils.register(dc, Timestamp.class);
		org.apache.commons.beanutils.ConvertUtils.register(dc, Date.class);
		org.apache.commons.beanutils.ConvertUtils.register(dc, String.class);
	}

	/**
	 * 将反射时的checked exception转换为unchecked exception.
	 */
	private static RuntimeException convertReflectionExceptionToUnchecked(Exception e) {
		if (e instanceof IllegalAccessException || e instanceof IllegalArgumentException
				|| e instanceof NoSuchMethodException) {
			return new IllegalArgumentException("Reflection Exception.", e);
		} else if (e instanceof InvocationTargetException) {
			return new RuntimeException("Reflection Exception.", ((InvocationTargetException) e).getTargetException());
		} else if (e instanceof RuntimeException) {
			return (RuntimeException) e;
		}
		return new RuntimeException("Unexpected Checked Exception.", e);
	}
	
	public static void main(String[] args) {
		Long org = 1001l;
		
		double tgt = (Double)ConvertUtils.convert(org, Double.class);
		System.out.println(tgt);
		
		boolean b = (boolean)ConvertUtils.convert("true", Boolean.class);
		System.out.println(b);
		
		b = (boolean)ConvertUtils.convert("True", Boolean.class);
		System.out.println(b);
		
		b = (boolean)ConvertUtils.convert("false", Boolean.class);
		System.out.println(b);
		
		b = (boolean)ConvertUtils.convert("FALSE", Boolean.class);
		System.out.println(b);
		
		b = (boolean)ConvertUtils.convert("1", Boolean.class);
		System.out.println(b);
		
		b = (boolean)ConvertUtils.convert("0", Boolean.class);
		System.out.println(b);
		
		b = (boolean)ConvertUtils.convert("true/false", Boolean.class);
		System.out.println(b);
	}
}

package com.seeyoui.kensite.common.dao.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.ArrayUtils;
import org.xidea.el.Expression;
import org.xidea.el.ExpressionFactory;

/**
 * <code>TransferUtils</code>
 * <p>数据传递工具类，提供数据复制和数据转换的工具方法</p>
 *
 * @author 邹学模
 * @date 2008-5-22
 */
public class TransferUtils {
	public static String TRANSFER_SOURCE_OBJECT_PREFIX = "$source";
	
	private static ExpressionFactory expressionFactory = ExpressionFactory.getInstance();

	private static Map<Class<?>, List<String>> classFieldNameCacheMap = new HashMap<Class<?>, List<String>>();
	
	/**
	 * 同一类型对象之间复制数据，要求复制的属性要提供getter、setter方法，如果属性不可读或不可写，则忽略不复制<br>
	 * 复制数据出错时抛出RuntimeException异常
	 *
	 * @param src 要复制的源对象
	 * @param tgt 复制到的目标对象
	 * @param includes 要复制的对象属性字符串数组，不在这个数组中的属性将不复制，如果设置includes为null，表示属性都要复制
	 * @param excludes 不复制的对象属性字符串数组，在这个数组中的属性将不复制，如果设置excludes为null，表示没有需要排除的属性
	 * @param callback 复制每个对象数据时调用回调方法，在回调方法中可以控制是否要复制这个对象属性
	 */
	@SuppressWarnings("unchecked")
	public static <T> void copy(T src, T tgt, String[] includes, String[] excludes, TransferCallback callback) {
		if (src == null)	throw new IllegalArgumentException("源对象不允许为空");
		if (tgt == null)	throw new IllegalArgumentException("目标对象不允许为空");
		
		Class clazz = src.getClass();
		
		if (includes == null)
			includes = getBeansPropertyNames(clazz);
		
		for (String name : includes) {
			if (excludes != null && ArrayUtils.contains(excludes, name))
				continue;
			
			try {
				Object v = PropertyUtils.getProperty(src, name);
				
				if (callback != null && !callback.readyCopy(src, name, v, tgt, name, PropertyUtils.getProperty(tgt, name)))
					continue;
					
				PropertyUtils.setProperty(tgt, name, v);
			} catch (Exception e) {
				throw new RuntimeException("复制字段[" + name + "]数据出错", e);
			}
		}
	}
	
	/**
	 * 复制源对象数据到目标对象（源对象和目标对象不一定是同一种对象类型），要求源对象属性提供getter方法、目标对象属性要提供setter方法<br>
	 * 复制数据出错时（要复制的属性不存在、要复制的属性不支持getter/setter方法、等等）抛出RuntimeException异常
	 *
	 * @param src 源对象，可以是一个Bean、或者是一个Map
	 * @param tgt 目标对象，可以是一个Bean、或者是一个Map
	 * @param callback 复制每个对象数据时调用回调方法，在回调方法中可以控制是否要复制这个对象属性
	 */
	public static void copy(Object src, Object tgt, TransferCallback callback) {
		copy(null, src, tgt, true, callback, false);
	}

	/**
	 * 复制源对象数据到目标对象（源对象和目标对象不一定是同一种对象类型），要求源对象属性提供getter方法、目标对象属性要提供setter方法<br>
	 * 复制数据出错时（要复制的属性不存在、要复制的属性不支持getter/setter方法、等等）根据ignoreIfNoProperty参数确定是否抛出RuntimeException异常还是忽略继续复制
	 *
	 * @param src 源对象，可以是一个Bean、或者是一个Map
	 * @param tgt 目标对象，可以是一个Bean、或者是一个Map
	 * @param callback 复制每个对象数据时调用回调方法，在回调方法中可以控制是否要复制这个对象属性
	 * @param ignoreIfNoProperty 源对象或目标对象没有对应需要拷贝的字段时是否忽略。true：未找到字段时忽略；false：未找到字段时抛出异常
	 */
	public static void copy(Object src, Object tgt, TransferCallback callback, boolean ignoreIfNoProperty) {
		copy(null, src, tgt, true, callback, ignoreIfNoProperty);
	}
	
	/**
	 * 复制源对象数据到目标对象（源对象和目标对象不一定是同一种对象类型），要求源对象属性提供getter方法、目标对象属性要提供setter方法
	 * <p>配置字符串有两种格式："field-field"，通过"-"分隔指定源对象属性和目标对象属性；"field"，表示源对象属性和目标对象属性为同一个属性</p>
	 * <p>示例：</p>
	 * <p>String[] rules = new String[]{"src_field1-tgt_field3", "field2"}</p>
	 * 复制数据出错时（要复制的属性不存在、要复制的属性不支持getter/setter方法、等等）抛出RuntimeException异常
	 *
	 * @param rules 复制规则字符串数组，存放要从源对象复制到目标对象的属性值，如果未定义，则从源对象中读取所有属性名作为复制规则
	 * @param src 源对象，可以是一个Bean、或者是一个Map
	 * @param tgt 目标对象，可以是一个Bean、或者是一个Map
	 * @param callback 复制每个对象数据时调用回调方法，在回调方法中可以控制是否要复制这个对象属性
	 */
	public static void copy(String[] rules, Object src, Object tgt, TransferCallback callback) {
		copy(rules, src, tgt, true, callback, false);
	}
	
	/**
	 * 复制源对象数据到目标对象（源对象和目标对象不一定是同一种对象类型），要求源对象属性提供getter方法、目标对象属性要提供setter方法
	 * <p>配置字符串有两种格式："field-field"，通过"-"分隔指定源对象属性和目标对象属性；"field"，表示源对象属性和目标对象属性为同一个属性</p>
	 * <p>示例：</p>
	 * <p>String[] rules = new String[]{"src_field1-tgt_field3", "field2"}</p>
	 * 复制数据出错时（要复制的属性不存在、要复制的属性不支持getter/setter方法、等等）根据ignoreIfNoProperty参数确定是否抛出RuntimeException异常还是忽略继续复制
	 *
	 * @param rules 复制规则字符串数组，存放要从源对象复制到目标对象的属性值，如果未定义，则从源对象中读取所有属性名作为复制规则
	 * @param src 源对象，可以是一个Bean、或者是一个Map
	 * @param tgt 目标对象，可以是一个Bean、或者是一个Map
	 * @param callback 复制每个对象数据时调用回调方法，在回调方法中可以控制是否要复制这个对象属性
	 * @param ignoreIfNoProperty 源对象或目标对象没有对应需要拷贝的字段时是否忽略。true：未找到字段时忽略；false：未找到字段时抛出异常
	 */
	public static void copy(String[] rules, Object src, Object tgt, TransferCallback callback, boolean ignoreIfNoProperty) {
		copy(rules, src, tgt, true, callback, ignoreIfNoProperty);
	}
	
	/**
	 * 在源对象与目标对象之间复制数据（源对象和目标对象不一定是同一种对象类型）<br>
	 * 当从源对象复制到目标对象时，要求源对象属性提供getter方法、目标对象属性要提供setter方法<br>
	 * 当从目标对象复制到源对象是，要求目标对象属性提供getter方法、源对象属性要提供setter方法<br>
	 * <p>配置字符串有两种格式："field-field"，通过"-"分隔指定源对象属性和目标对象属性；"field"，表示源对象属性和目标对象属性为同一个属性</p>
	 * <p>示例：</p>
	 * <p>String[] rules = new String[]{"src_field1-tgt_field3", "field2"}</p>
	 * 复制数据出错时（要复制的属性不存在、要复制的属性不支持getter/setter方法、等等）抛出RuntimeException异常
	 *
	 * @param rules 复制规则字符串数组，存放要从源对象复制到目标对象的属性值，如果未定义，则从源对象中读取所有属性名作为复制规则
	 * @param src 源对象，可以是一个Bean、或者是一个Map
	 * @param tgt 目标对象，可以是一个Bean、或者是一个Map
	 * @param direction 复制方向，为true，表示从源对象复制到目标对象，为false，表示从目标对象复制到源对象
	 * @param callback 复制每个对象数据时调用回调方法，在回调方法中可以控制是否要复制这个对象属性
	 */
	public static void copy(String[] rules, Object src, Object tgt, boolean direction, TransferCallback callback) {
		copy(rules, src, tgt, direction, callback, false);
	}
	
	/**
	 * 在源对象与目标对象之间复制数据（源对象和目标对象不一定是同一种对象类型）<br>
	 * 当从源对象复制到目标对象时，要求源对象属性提供getter方法、目标对象属性要提供setter方法<br>
	 * 当从目标对象复制到源对象是，要求目标对象属性提供getter方法、源对象属性要提供setter方法<br>
	 * <p>配置字符串有两种格式："field-field"，通过"-"分隔指定源对象属性和目标对象属性；"field"，表示源对象属性和目标对象属性为同一个属性</p>
	 * <p>示例：</p>
	 * <p>String[] rules = new String[]{"src_field1-tgt_field3", "field2"}</p>
	 * 复制数据出错时（要复制的属性不存在、要复制的属性不支持getter/setter方法、等等）根据ignoreIfNoProperty参数确定是否抛出RuntimeException异常还是忽略继续复制
	 *
	 * @param rules 复制规则字符串数组，存放要从源对象复制到目标对象的属性值，如果未定义，则从源对象中读取所有属性名作为复制规则
	 * @param src 源对象，可以是一个Bean、或者是一个Map
	 * @param tgt 目标对象，可以是一个Bean、或者是一个Map
	 * @param direction 复制方向，为true，表示从源对象复制到目标对象，为false，表示从目标对象复制到源对象
	 * @param callback 复制每个对象数据时调用回调方法，在回调方法中可以控制是否要复制这个对象属性
	 * @param ignoreIfNoProperty 源对象或目标对象没有对应需要拷贝的字段时是否忽略。true：未找到字段时忽略；false：未找到字段时抛出异常
	 */
	public static void copy(String[] rules, Object src, Object tgt, boolean direction, TransferCallback callback, boolean ignoreIfNoProperty) {
		if (src == null)	throw new IllegalArgumentException("源对象不允许为空");
		if (tgt == null)	throw new IllegalArgumentException("目标对象不允许为空");
		
		if (rules == null)
			rules = getObjectsPropertyNames(src);
		
		for (String rule : rules) {
			String srcFieldName, tgtFieldName;
			
			String[] tmp = rule.split("-", 2);
			srcFieldName = tmp[0].trim();
			if (tmp.length == 1)
				tgtFieldName = tmp[0].trim();
			else
				tgtFieldName = tmp[1].trim();
			
			if (direction)
				copy(src, srcFieldName, tgt, tgtFieldName, callback, ignoreIfNoProperty);
			else
				copy(tgt, tgtFieldName, src, srcFieldName, callback, ignoreIfNoProperty);
		}
	}

	/**
	 * 复制源对象的指定属性值到目标对象的指定属性中，并自动调用回调方法发送复制消息<br>
	 * 复制数据出错时（要复制的属性不存在、要复制的属性不支持getter/setter方法、等等）抛出RuntimeException异常
	 *
	 * @param src 源对象，可以是一个Bean、或者是一个Map
	 * @param srcFieldName
	 * @param tgt 目标对象，可以是一个Bean、或者是一个Map
	 * @param tgtFieldName
	 * @param callback
	 */
	public static void copy(Object src, String srcFieldName, Object tgt, String tgtFieldName, TransferCallback callback) {
		copy(src, srcFieldName, tgt, tgtFieldName, callback, false);
	}
	
	/**
	 * 复制源对象的指定属性值到目标对象的指定属性中，并自动调用回调方法发送复制消息<br>
	 * 复制数据出错时（要复制的属性不存在、要复制的属性不支持getter/setter方法、等等）根据ignoreIfNoProperty参数确定是否抛出RuntimeException异常
	 *
	 * @param src 源对象，可以是一个Bean、或者是一个Map
	 * @param srcFieldName
	 * @param tgt 目标对象，可以是一个Bean、或者是一个Map
	 * @param tgtFieldName
	 * @param callback
	 * @param ignoreIfNoProperty 源对象或目标对象没有对应需要拷贝的字段时是否忽略。true：未找到字段时忽略；false：未找到字段时抛出异常
	 */
	public static void copy(Object src, String srcFieldName, Object tgt, String tgtFieldName, TransferCallback callback, boolean ignoreIfNoProperty) {
		try {
			Object v1 = getPropertyValue(src, srcFieldName);
			Object v2 = null;
			try {
				v2 = getPropertyValue(tgt, tgtFieldName);
			} catch (Exception e) {}
			
			if (callback != null && !callback.readyCopy(src, srcFieldName, v1, tgt, tgtFieldName, v2))
				return;
			
			v1 = convert(v1, tgt, tgtFieldName);
			setPropertyValue(tgt, tgtFieldName, v1);
		} catch (Exception e) {
			if (e instanceof NoSuchMethodException && ignoreIfNoProperty)
				return;
			
			throw new RuntimeException("数据复制[" + srcFieldName + "至" + tgtFieldName + "]出错", e);
		}
	}
	
	/**
	 * 设置给定对象的属性值，如果给定属性值与对象属性类型不一致，则试着转换属性值为同样类型<br>
	 * 复制数据出错时（要设置的属性不存在、要设置的属性不支持setter方法、等等）抛出RuntimeException异常
	 * 
	 * @param tgt
	 * @param tgtFieldName 目标对象，可以是一个Bean、或者是一个Map
	 * @param tgtFieldVal
	 */
	public static void setProperty(Object tgt, String tgtFieldName, Object tgtFieldVal) {
		try {
			Object val = convert(tgtFieldVal, tgt, tgtFieldName);
			setPropertyValue(tgt, tgtFieldName, val);
		} catch (Exception e) {
			throw new RuntimeException("设置属性[" + tgtFieldName + "值为" + tgtFieldVal + "]出错", e);
		}
	}
	
	/**
	 * 功能描述：创建Map的List，并复制Object的List中的指定字段的数据到Map的List中<br>
	 *
	 * 前置条件： <br>
	 * 1.list不能为空
	 * 2.list中的obj 不能为空<br>
	 * 3.复制规则数组不能为空或长度为0<br>
	 *
	 * @param rules
	 * @param list
	 * @param callback
	 * @return
	*/
	public static List<Map<String, ?>> buildAndCopyObjectListToMapList(String[] rules, List<?> list, TransferCallback callback) {
		List<Map<String, ?>> result = new ArrayList<Map<String, ?>>();
		
		for (Object obj : list) {
			Map<String, ?> map = new HashMap<String, Object>();
			TransferUtils.copy(rules, obj, map, callback);
			
			result.add(map);
		}
		
		return result;
	}
    
	/**
	 * 提供1到多个数据源，通过表达式计算进行数据转换，写入到目标数据中<br>
	 * 转换规则为用等号分隔的赋值表达式字符串数组，等号左边是目标对象的指定字段，等号右边是包含源对象字段的表达式，表达式还支持调用函数方法<br>
	 * 对于传入多个数据源，表达式可以通过"$source[index].field"来引用某个数据源的某个属性字段，其中index为数据源在参数中的顺序，以0开始<br>
	 * 而对于第一个数据源的属性，即可以通过上面说的"$source[0].field"来引用，也可以直接指定field来引用<br>
	 * 示例：<br>
	 * 	String[] rules = new String[]{"tgt_field1=src_field1+10", "tgt_field2=$source[0].field2"}
	 *
	 * @param rules 数据转换规则字符串数组
	 * @param tgt 要写入的目标对象，可以是一个Bean，或者是一个Map
	 * @param src 多个源对象，可以是一个Bean、或者是一个Map
	 */
	@SuppressWarnings("unchecked")
	public static void transfer(String[] rules, Object tgt, Object... src) {
		if (src == null || src.length == 0)	throw new IllegalArgumentException("源对象不允许为空");
		if (tgt == null)	throw new IllegalArgumentException("目标对象不允许为空");
		if (rules == null || rules.length == 0)	throw new IllegalArgumentException("转换规则集合未定义");
		
		//构造JSEL表达式需要的参数Map，首先把所有源对象List作为键为TRANSFER_SOURCE_OBJECT_PREFIX的值添加到参数Map中
		//然后填充第一个源对象的所有属性名－值对到参数Map
		Map context = new HashMap();
		
		List list = new ArrayList();
		for (Object obj : src)
			list.add(obj);
		
		context.put(TRANSFER_SOURCE_OBJECT_PREFIX, list);
		
		Object obj = list.get(0);
		try {
			Map map = PropertyUtils.describe(obj);
			
			context.putAll(map);
		} catch (IllegalAccessException e) {
		} catch (InvocationTargetException e) {
		} catch (NoSuchMethodException e) {
		}
		
		//遍历每条转换规则，调用JSEL执行表达式计算，把计算结果经过转换写入目标对象的目标字段中
		for (String rule : rules) {
			if (rule == null || rule.length() == 0)
				throw new IllegalArgumentException("转换规则未定义");
			
			String[] tmp = rule.split("=", 2);
			if (tmp.length != 2)
				throw new IllegalArgumentException("转换规则格式[" + rule + "]错误，格式要求为'xxx=xxx'形式");
			
			String tgtFieldName = tmp[0].trim();
			String transferExpression = tmp[1].trim();
			
			try {
				Expression el = expressionFactory.create(transferExpression);
				Object result = el.evaluate(context);
				
				result = convert(result, tgt, tgtFieldName);
				setPropertyValue(tgt, tgtFieldName, result);
			} catch (Exception e) {
				throw new RuntimeException("执行转换规则[" + rule + "]出错", e);
			}
		}
	}
	
	/**
	 * 试着转换值为指定对象的指定字段的类型，如果不能转换，抛出异常<br>
	 * 如果指定对象是一个Map、或者字段类型和值类型相同，值不会转换，直接返回，否则会试着转换类型
	 * 
	 * @param value
	 * @param tgt
	 * @param tgtFieldName
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	@SuppressWarnings("unchecked")
	private static Object convert(Object value, Object bean, String fieldName) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		if (!(bean instanceof Map)) {
			Class srcFieldClass = value.getClass();
			
			Class tgtFieldClass;
			if (bean instanceof Class)
				tgtFieldClass = PropertyUtils.getPropertyType(bean, fieldName);
			else
				tgtFieldClass = PropertyUtils.getPropertyType(bean, fieldName);
			
			if (!srcFieldClass.equals(tgtFieldClass)) {
				if (tgtFieldClass.equals(String.class)) {
					value = ConvertUtils.convert(value);
				} else if (srcFieldClass.equals(String.class)) {
					value = ConvertUtils.convert((String)value, tgtFieldClass);
				} else {
					value = ConvertUtils.convert(value, tgtFieldClass);
				}
			}
		}
		
		return value;
	}
	
	private static String[] getObjectsPropertyNames(Object obj) {
		if (obj instanceof Map<?, ?>) {
			Map<?, ?> map = (Map<?, ?>)obj;
			Set<?> set = map.keySet();
			
			int index = 0;
			String[] property = new String[set.size()];
			for (Object o : set)
				property[index++] = o.toString();
			
			return property;
		} else {
			Class<?> clazz = obj.getClass();
			String[] property = getBeansPropertyNames(clazz);
			
			return property;
		}
	}
	
	private static String[] getBeansPropertyNames(Class<?> clazz) {
		List<String> list;
		if (classFieldNameCacheMap.containsKey(clazz))
			list = classFieldNameCacheMap.get(clazz);
		else {
			list = new ArrayList<String>();
			
			//获取所有可读属性列表（包括父类）
			List<Field> fields = BeanUtils.getDeclaredFields(clazz);
			for (Field field : fields) {
				String name = field.getName();
				
				try {
					if (BeanUtils.isFieldWriteable(clazz, name))
						list.add(name);
				} catch (SecurityException e) {
				} catch (NoSuchFieldException e) {
				}
			}

			classFieldNameCacheMap.put(clazz, list);
		}

		return list.toArray(new String[0]);
	}
	
	@SuppressWarnings("unchecked")
	private static Object getPropertyValue(Object obj, String property) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		if (obj instanceof Map) {
			Map map = (Map)obj;
			if (!map.containsKey(property))
				throw new NoSuchMethodException("Map中不包含键为[" + property + "]的数据");
				
			return map.get(property);
		} else
			return PropertyUtils.getProperty(obj, property);
	}
	
	@SuppressWarnings("unchecked")
	private static void setPropertyValue(Object obj, String property, Object value) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		if (obj instanceof Map) {
			Map map = (Map)obj;
			
			if (map.containsKey(property))
				map.remove(property);
			
			map.put(property, value);
		} else
			PropertyUtils.setProperty(obj, property, value);
	}
	
	public static void main(String[] args) {
		String[] ary = "919729$$123$和平路蓝岸雅居1幢1单元2606$0$0$17".split("\\$");
		for (int index = 0; index < ary.length; index++) {
			System.out.println(index + " - " + ary[index]);
		}
	}
}

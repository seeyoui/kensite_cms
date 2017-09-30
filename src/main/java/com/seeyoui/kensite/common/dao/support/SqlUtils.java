package com.seeyoui.kensite.common.dao.support;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.seeyoui.kensite.common.dao.util.ConvertUtils;

public class SqlUtils {

	/**
	 * 按照指定分隔符分割脚本，分割的脚本存入List集合
	 * 
	 * @param script SQL脚本
	 * @param delim 分隔符，典型分隔符为';'
	 * @param statements 保存分隔的SQL语句的集合
	 */
	public static void splitSqlScript(String script, char delim,
			List<String> statements) {
		StringBuilder sb = new StringBuilder();
		boolean inLiteral = false;
		char[] content = script.toCharArray();
		for (int i = 0; i < script.length(); i++) {
			if (content[i] == '\'') {
				inLiteral = !inLiteral;
			}
			if (content[i] == delim && !inLiteral) {
				if (sb.length() > 0) {
					statements.add(sb.toString().trim());
					sb = new StringBuilder();
				}
			} else {
				sb.append(content[i]);
			}
		}
		if (sb.length() > 0) {
			statements.add(sb.toString());
		}
	}

	/**
	 * 分解指定分隔符分隔的ID集合字符串为Long数组
	 * 
	 * @param ids
	 * @param delim
	 * @return
	 */
	public static Long[] splitIdStr(String ids, char delim) {
		String[] ary = StringUtils.split(ids, delim);
		for (int index = 0, len = ary.length; index < len; index++)
			ary[index] = ary[index].trim();

		return ConvertUtils.convert2(ary, Long.class);
	}

	/**
	 * 转换long数组为Long包装类数组
	 * 
	 * @param ary
	 * @return
	 */
	public static Long[] convertLongArray(long[] ary) {
		Long[] result = null;

		if (ary != null) {
			result = new Long[ary.length];

			for (int i = 0, len = ary.length; i < len; i++)
				result[i] = new Long(ary[i]);
		}

		return result;
	}

	/**
	 * 将驼峰式命名的字符串转换为下划线大写或小写方式。如果转换前的驼峰式命名的字符串为空，则返回空字符串。</br>
	 * 例如：helloWorld->HELLO_WORLD
	 * 
	 * @param name 转换前的驼峰式命名的字符串
	 * @param toUpperCase true则转换成大写，false则转换成小写
	 * @return 转换后下划线大写或小写方式命名的字符串
	 */
	public static String underscoreName(String name, boolean toUpperCase) {
		StringBuilder result = new StringBuilder();
		if (name != null && name.length() > 0) {
			// 将第一个字符处理成大写
			result.append(toUpperCase ? name.substring(0, 1).toUpperCase() : name.substring(0, 1).toLowerCase());
			// 循环处理其余字符
			for (int i = 1; i < name.length(); i++) {
				String s = name.substring(i, i + 1);
				// 在大写字母前添加下划线
				if (s.equals(s.toUpperCase())
						&& !Character.isDigit(s.charAt(0))) {
					result.append("_");
				}
				// 其他字符直接转成大写
				result.append(toUpperCase ? s.toUpperCase() : s.toLowerCase());
			}
		}
		return result.toString();
	}
	
	/**
	 * 将存放Map的List中各个Map对应的KEY值转换从驼峰式命令转换为下划线大写或小写方式。</br>
	 * 例如：helloWorld->HELLO_WORLD
	 * 
	 * @param data 存放Map的List，对应前台传入的数据集
	 * @param toUpperCase true则转换成大写，false则转换成小写
	 * @return
	 */
	public static List<Map<String, Object>> underscoreName(List<Map<String, Object>> data, boolean toUpperCase) {
		if (data.size() == 0)
			return data;
		
		Map<String, String> fieldNameMapping = new HashMap<String, String>();
		for (String orgFieldName : data.get(0).keySet()) {
			String mappingFieldName = underscoreName(orgFieldName, toUpperCase);
			
			fieldNameMapping.put(orgFieldName, mappingFieldName);
		}
		
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> r1 : data) {
			Map<String, Object> r2 = new LinkedHashMap<String, Object>();
			for (Map.Entry<String, Object> entry : r1.entrySet()) {
				String name = entry.getKey();
				name = fieldNameMapping.get(name);
				
				r2.put(name, entry.getValue());
			}
			
			result.add(r2);
		}
		
		return result;
	}

	/**
	 * 将下划线大写或小写方式命名的字符串转换为驼峰式。如果转换前的下划线大写或小写方式命名的字符串为空，则返回空字符串。</br>
	 * 例如：HELLO_WORLD->helloWorld</br>
	 * 　　　hello_world->helloWorld
	 * 
	 * @param name 转换前的下划线大写或小写方式命名的字符串
	 * @return 转换后的驼峰式命名的字符串
	 */
	public static String camelName(String name) {
		StringBuilder result = new StringBuilder();
		// 快速检查
		if (name == null || name.length() == 0) {
			// 没必要转换
			return "";
		} else if (!name.contains("_")) {
			// 不含下划线，仅将首字母小写
			return name.substring(0, 1).toLowerCase() + name.substring(1);
		}
		// 用下划线将原始字符串分割
		String camels[] = name.split("_");
		for (String camel : camels) {
			// 跳过原始字符串中开头、结尾的下换线或双重下划线
			if (camel.length() == 0) {
				continue;
			}
			// 处理真正的驼峰片段
			if (result.length() == 0) {
				// 第一个驼峰片段，全部字母都小写
				result.append(camel.toLowerCase());
			} else {
				// 其他的驼峰片段，首字母大写
				result.append(camel.substring(0, 1).toUpperCase());
				result.append(camel.substring(1).toLowerCase());
			}
		}
		return result.toString();
	}
	
	/**
	 * 将存放Map的List中各个Map对应的KEY值转换为驼峰式。</br>
	 * 例如：HELLO_WORLD->helloWorld</br>
	 * 　　　hello_world->helloWorld
	 * 
	 * @param data 存放Map的List，对应通过dao.sqlQuery返回的查询结果
	 * @return
	 */
	public static List<Map<String, Object>> camelName(List<Map<String, Object>> data) {
		if (data.size() == 0)
			return data;
		
		Map<String, String> fieldNameMapping = new HashMap<String, String>();
		for (String orgFieldName : data.get(0).keySet()) {
			String mappingFieldName = camelName(orgFieldName);
			
			fieldNameMapping.put(orgFieldName, mappingFieldName);
		}
		
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> r1 : data) {
			Map<String, Object> r2 = new LinkedHashMap<String, Object>();
			for (Map.Entry<String, Object> entry : r1.entrySet()) {
				String name = entry.getKey();
				name = fieldNameMapping.get(name);
				
				r2.put(name, entry.getValue());
			}
			
			result.add(r2);
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println(SqlUtils.camelName("COMPANY_CODE"));
		System.out.println(SqlUtils.camelName("company_code"));
		System.out.println(SqlUtils.underscoreName("companyCode", true));
		System.out.println(SqlUtils.underscoreName("companyCode", false));
	}
}

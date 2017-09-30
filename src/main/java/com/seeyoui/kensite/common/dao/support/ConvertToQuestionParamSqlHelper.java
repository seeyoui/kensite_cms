package com.seeyoui.kensite.common.dao.support;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ArrayUtils;

import com.seeyoui.kensite.common.dao.Condition;
import com.seeyoui.kensite.common.dao.Dao;
import com.seeyoui.kensite.common.dao.util.FieldTokenProcessor;

/**
 * 实现把SQL中以":"开头的变量参数、或者Condition参数替换为"?"标识的变量参数的工具类
 * 
 * @author zouxuemo
 *
 */
public class ConvertToQuestionParamSqlHelper {
	/**
	 * 连接数据库标识，支持标识见Dao接口类的数据库标识名称常量定义
	 */
	private String databaseIdentity;
	
	/**
	 * 连接数据库版本号
	 */
	private String databaseVersion;
	
	private String sql;
	
	private Object[] values = null;

	private static FieldTokenProcessor colonParamProcessor = new FieldTokenProcessor(':', FieldTokenProcessor.NO_VARIABLE_SYMBOL);
	
	public ConvertToQuestionParamSqlHelper(String databaseIdentity, String databaseVersion, String sql, Map<String, Object> params) {
		this.databaseIdentity = databaseIdentity;
		this.databaseVersion = databaseVersion;
		this.sql = sql;
		
		convert(params);
	}
	
	public ConvertToQuestionParamSqlHelper(String databaseIdentity, String databaseVersion, String sql, Condition condition, boolean includeOrder) {
		this.databaseIdentity = databaseIdentity;
		this.databaseVersion = databaseVersion;
		this.sql = sql;
		
		convert(condition, includeOrder);
	}
	
	public ConvertToQuestionParamSqlHelper(String databaseIdentity, String databaseVersion, String sql, Map<String, Object> params, Condition condition, boolean includeOrder) {
		this.databaseIdentity = databaseIdentity;
		this.databaseVersion = databaseVersion;
		this.sql = sql;
		
		convert(params);
		convert(condition, includeOrder);
	}
	
	private void convert(Condition condition, boolean includeOrder) {
		if (condition == null) 
			return;
		
		ConditionParser parser = new ConditionParser(databaseIdentity, databaseVersion, condition);
		
		String where = parser.parse(includeOrder);
		Object[] values = parser.params();
		
		sql = combinSql(sql, where);
		setValues(values);
	}
	
	private void convert(Map<String, Object> params) {
		// 搜索所有以":"开头的参数变量名集合
		List<String> list = colonParamProcessor.searchFieldToken(sql, false);
		if (list.size() == 0)
			return;
		
		String[] paramNames = list.toArray(new String[0]);
		int length = paramNames.length;
		int valuesLength = 0;
		
		// 把sql中所有以":"开头的参数变量名替换为"?"
		Object[] replaces = new String[length];
		for (int index = 0; index < length; index++) {
			if (!params.containsKey(paramNames[index]))
				throw new RuntimeException("带参数SQL中的变量[" + paramNames[index] + "]未找到对应的参数值！");
		
			Object value = params.get(paramNames[index]);
			int len = getValueArrayLength(value);
			valuesLength += len;
			
			replaces[index] = combinQuestionMark(len);
		}
		sql = colonParamProcessor.replaceFieldToken(sql, replaces);

		// 根据搜索的参数变量名集合，从Map中读取对应变量值，生成变量值数组
		Object[] values = new Object[valuesLength];
		int pos = 0;
		for (int index = 0; index < length; index++) {
			pos = addValues(values, pos, params.get(paramNames[index]));
		}
		
		setValues(values);
	}
	
	/**
	 * 判断对象是否为数组或者集合，如果是则返回对应长度，否则返回1
	 * 
	 * @param value
	 * @return
	 */
	private int getValueArrayLength(Object value) {
		if (value == null)
			return 1;
		else if (value.getClass().isArray())
			return ArrayUtils.getLength(value);
		else if (value instanceof Collection<?>)
			return ((Collection<?>)value).size();
		else
			return 1;
	}
	
	/**
	 * 组给定数量的问号，多于一个问号之间用,分隔<br>
	 * 示例：combinQuestionMark(3)	//result: ?,?,?
	 * 
	 * @param length
	 * @return
	 */
	private String combinQuestionMark(int length) {
		String result = "?";
		
		while (length-- > 1) {
			result += ", ?";
		}
		
		return result;
	}
	
	/**
	 * 把值加到数组中，并返回新的插入位置<br>
	 * 如果值是数组或者集合，则把数组或集合的所有值逐个插入数组
	 * 
	 * @param array
	 * @param pos
	 * @param value
	 * @return
	 */
	private int addValues(Object[] array, int pos, Object value) {
		if (value == null) {
			array[pos++] = null;
		} else if (value.getClass().isArray()) {
			for (Object v : (Object[])value)
				array[pos++] = v;
			
		} else if (value instanceof Collection<?>) {
			for (Object v : ((Collection<?>)value))
				array[pos++] = v;
		} else {
			array[pos++] = value;
		}
		
		return pos;
	}
	
	private void setValues(Object[] values) {
		if (this.values == null)
			this.values = values;
		else
			this.values = ArrayUtils.addAll(this.values, values);
	}
	
	private String combinSql(String sql, String where) {
		sql = StringUtils.trim(sql);
		
		if (sql == null || "".equals(sql))
			return sql;

		boolean endWithSemicolon = false;
		if (sql.charAt(sql.length() - 1) == ';') {
			endWithSemicolon = true;
			
			sql = sql.substring(0, sql.length() - 1);
		}
		
		if (where != null) {
			if (where.startsWith(" where ")) {		//如果where子句中有where查询条件
				boolean needReplaceWhereSymbol = false;
				
				//检查给定的sql语句结尾是否包含where子句
				int pos = StringUtils.lastIndexOf(sql, "where");
				if (pos > 0) {
					String s = sql.substring(pos + 5);
					if (StringUtils.indexOf(s, " from ") == -1 && StringUtils.indexOf(s, " on ") == -1)
						needReplaceWhereSymbol = true;
				}
				
				//如果给定的sql语句结尾包含where子句，则需要替换where子句开头的" where "为" and "
				if (needReplaceWhereSymbol)
					where = StringUtils.replaceOnce(where, " where ", " and ");
			}
			
			sql += where;
		}
		
		if (endWithSemicolon)
			sql += ';';
		
		return sql;
	}

	public String getSql() {
		return sql;
	}

	public Object[] getValues() {
		return values;
	}
	
	public static void main(String[] args) {
		String sql = "select * from t where (a in (:a) or b in (:b));";
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		List<Integer> list = new ArrayList<Integer>();
		list.add(12345);
		params.put("a", list);
		params.put("b", new Object[]{"valueB1", "valueB2"});
		
		Condition condition = Condition.create();
		condition.eq("a", "");
		condition.ne("b", "");
		condition.eq("e", "qaz");
		condition.llike("c", "valueC");
		condition.asc("d");
		
		ConvertToQuestionParamSqlHelper helper = new ConvertToQuestionParamSqlHelper(Dao.DATABASE_ORACLE, "", sql, params, condition, false);
		System.out.println(helper.getSql());
		System.out.println(StringUtils.join(helper.getValues(), ","));
		
		helper = new ConvertToQuestionParamSqlHelper(Dao.DATABASE_MSSQL, "", sql, params, condition, false);
		System.out.println(helper.getSql());
		System.out.println(StringUtils.join(helper.getValues(), ","));
	}
}

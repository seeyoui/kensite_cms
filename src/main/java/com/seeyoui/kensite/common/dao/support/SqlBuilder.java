/**
 * 
 */
package com.seeyoui.kensite.common.dao.support;

import java.util.ArrayList;
import java.util.List;

/**
 * SQL拼接器，为了避免SQL注入问题，提供SQL片段及参数值拼接支持<p>
 * 先通过调用append方法往拼接器内追加sql片段和参数值，最后通过sql和values方法分别返回拼接好的sql字符串和参数值数组<br>
 * <b>注意：如果append方法中给出了参数值，则要求sql片段字符串参数中必须有和参数值对应数量的“?”占位符<br>
 * 　　　追加的sql片段字符串如果开始不带空格，函数会自动添加空格</b><br>
 * 示例： <br>
 * 　　　SqlBuilder sb = SqlBuilder.instance()<br>
 * 　　　　　　　　　　　　　　　　.append("select * from tableA")<br>
 * 　　　　　　　　　　　　　　　　.append("where field1 = ? and field2 = ?", "val1", 12345.6789)<br>
 * 　　　　　　　　　　　　　　　　.append("order by field3");<br>
 * 　　　dao.sqlQuery(sb.sql(), sb.values());<br>
 * 
 * 
 * @author 邹学模
 *
 */
public class SqlBuilder {
	private StringBuffer _sql;
	
	private List<Object> _values = null;
	
	public SqlBuilder() {
		_sql = new StringBuffer();
		
		_values = new ArrayList<Object>();
	}
	
	/**
	 * 生成并返回SqlBuilder实例
	 * 
	 * @return
	 */
	public static SqlBuilder instance() {
		return new SqlBuilder();
	}
	
	/**
	 * 往拼接器内追加sql片段和参数值，并返回SqlBuilder实例<br>
	 * 如果给出了参数值，则要求sql片段中必须有和参数值对应数量的“？”占位符<br>
	 * 例如：append("where field1 = ? and field2 = ?", "val1", 12345.6789)
	 * 
	 * @param fragment SQL片段
	 * @param values 参数值
	 * @return
	 */
	public SqlBuilder append(String fragment, Object... values) {
		if (fragment != null && fragment.length() > 0) {
			if (fragment.charAt(0) != ' ')
				_sql.append(' ');
			
			_sql.append(fragment);
		}
		
		if (values != null && values.length > 0)
			for (Object v : values)
				_values.add(v);
		
		return this;
	}
	
	/**
	 * 返回拼接后的SQL语句
	 * 
	 * @return
	 */
	public String sql() {
		return _sql.toString();
	}
	
	/**
	 * 返回所有参数值
	 * 
	 * @return
	 */
	public Object[] values() {
		return _values.toArray(new Object[0]);
	}
	
	public static void main(String[] args) {
		SqlBuilder sb = SqlBuilder.instance().append("select * from tableA").append(" where field1 = ? and field2 = ?", "val1", 12345.6789).append("order by field3");
		System.out.print(sb.sql() + " - ");
		for (Object o :  sb.values())
			System.out.print(o + ", ");
	}
}

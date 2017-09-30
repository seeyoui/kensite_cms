/**
 * 
 */
package com.seeyoui.kensite.common.dao.hibernate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.seeyoui.kensite.common.dao.SqlFieldMetaData;

/**
 * 获取给定SQL的结果集元数据结构的Work
 * 
 * @author Administrator
 *
 */
public class SqlQueryMetaDataWork implements Work {
	private String sql = null;

	private Object[] values = null;
	
	private List<SqlFieldMetaData> fields = null;
	
	public SqlQueryMetaDataWork(String sql, Object... values) {
		this.sql = rebuildSql(sql);
		this.values = values;
	}
	
	private String rebuildSql(String sql) {
		int pos = StringUtils.lastIndexOf(sql, " order ");
		if (pos > 0)
			sql = sql.substring(0, pos);
		
		sql = "SELECT * FROM (" + sql + ") AS t WHERE 0 = 1;";
		
		return sql;
	}
	
	/* (non-Javadoc)
	 * @see org.hibernate.jdbc.Work#execute(java.sql.Connection)
	 */
	public void execute(Connection connection) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			if (values != null && values.length > 0) {
				for (int i = 0; i < values.length; i++)
					pstmt.setObject(i + 1, values[i]);
			}

			rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			
			int count = rsmd.getColumnCount();
			fields = new ArrayList<SqlFieldMetaData>(count);
			
			for (int index = 1; index <= count; index++) {
				SqlFieldMetaData field = new SqlFieldMetaData();
				fields.add(field);
				
				field.setName(rsmd.getColumnName(index));
				field.setLabel(rsmd.getColumnLabel(index));
				field.setType(rsmd.getColumnType(index));
				field.setTypeName(rsmd.getColumnTypeName(index));
			}
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {}
			
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e) {}
		}
	}

	public List<SqlFieldMetaData> getFieldMetaDatas() {
		return fields;
	}
}

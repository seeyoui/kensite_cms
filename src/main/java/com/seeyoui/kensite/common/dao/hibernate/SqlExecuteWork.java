package com.seeyoui.kensite.common.dao.hibernate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.seeyoui.kensite.common.dao.Dao;

/**
 * 执行SQL更新的Work
 * 
 * 
 * date: 2010-12-2
 * 
 * @author zouxuemo
 */
public class SqlExecuteWork implements Work {
	private String sql = null;

	private Object[] values = null;

	private String databaseIdentity = Dao.DATABASE_MSSQL;
	
	private boolean needReturnGeneratedKey = false;
	
	private long autoIncKey = -1;
	
	public SqlExecuteWork(String sql, Object... values) {
		this.sql = sql;
		this.values = values;
	}

	public void setDatabaseIdentity(String databaseIdentity) {
		this.databaseIdentity = databaseIdentity;
	}

	public void setNeedReturnGeneratedKey(boolean needReturnGeneratedKey) {
		this.needReturnGeneratedKey = needReturnGeneratedKey;
	}

	public long getAutoIncKey() {
		return autoIncKey;
	}

	public void execute(Connection connection) throws SQLException {
		PreparedStatement pstmt = null;

		try {
			if (needReturnGeneratedKey) {
				//ORACLE数据库与其他数据库获取ID值的方式不一样，需要单独处理
				//！！！注意：要求Oracle的JDBC驱动在10.2.0.1.0以后版本
				if (Dao.DATABASE_ORACLE.equals(databaseIdentity))
					pstmt = connection.prepareStatement(sql, new String[]{"ID"});
				else 
					pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			} else
				pstmt = connection.prepareStatement(sql);
			
			if (values != null && values.length > 0) {
				for (int i = 0; i < values.length; i++)
					pstmt.setObject(i + 1, values[i]);
			}

			pstmt.executeUpdate();
			if (needReturnGeneratedKey) {
			    ResultSet rs = pstmt.getGeneratedKeys();
			    if (rs.next())
			    	autoIncKey = rs.getLong(1);
			}
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e) {}
		}
	}
}

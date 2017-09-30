package com.seeyoui.kensite.common.dao.hibernate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 执行SQL更新的Work
 * 
 * 
 * date: 2010-12-2
 * 
 * @author zouxuemo
 */
public class BatchSqlExecuteByParamListWork implements Work {
	private String sql = null;
	
	private int batchSize = 50;
	
	List<Object[]> paramList = new ArrayList<Object[]>();

	public BatchSqlExecuteByParamListWork(String sql) {
		this.sql = sql;
	}

	public BatchSqlExecuteByParamListWork(String sql, List<Object[]> paramList) {
		this.sql = sql;
		this.paramList = paramList;
	}
	
	public int getBatchSize() {
		return batchSize;
	}

	public void setBatchSize(int batchSize) {
		this.batchSize = batchSize;
	}

	public void setParamList(List<Object[]> paramList) {
		this.paramList = paramList;
	}

	public void addParam(Object[] params) {
		paramList.add(params);
	}

	public void execute(Connection connection) throws SQLException {
		boolean autoCommit = connection.getAutoCommit();
		
		PreparedStatement stmt = connection.prepareStatement(sql);
		connection.setAutoCommit(false);

		try {
			int count = 0;
			for (Object[] params : paramList) {
				int index = 1;
				for (Object param : params)
					stmt.setObject(index++, param);
				
			    stmt.addBatch();
			    
			    if (++count % batchSize == 0) {
			        stmt.executeBatch();
			        connection.commit();
			    }
			}
			
			if (count % batchSize != 0) { //防止参数列表大小正好是批量提交大小的倍数，导致执行两次批量提交
				stmt.executeBatch();
				connection.commit();
			}
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {}
			
			connection.setAutoCommit(autoCommit);
		}
	}
}

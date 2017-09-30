package com.seeyoui.kensite.common.dao.hibernate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

/**
 * 执行SQL查询的Work
 * 
 *
 * date: 2010-12-2
 * @author zouxuemo
 */
public  class SqlQueryWork<T> implements Work {
	private String sql = null;

	private int start = 0;
	
	private int limit = 0;
	
	private Object[] values = null;

	private RowMapper<T> mapper = null;
	
	private List<T> result = null;
	
	public SqlQueryWork(String sql, RowMapper<T> mapper, final int start, final int limit, Object... values) {
		this.sql = sql;
		this.start = start;
		this.limit = limit;
		this.values = values;
		this.mapper = mapper;
	}

	public void execute(Connection connection) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			if (values != null && values.length > 0) {
				for (int i = 0; i < values.length; i++)
					pstmt.setObject(i + 1, values[i]);
			}

        	if (limit > 0)
        		pstmt.setMaxRows(start + limit);
        	
			rs = pstmt.executeQuery();
			
			if (start > 0)
				rs.absolute(start);
			
			result =  new ArrayList<T>();
			int rowNum = 0;
			
			while (rs.next()) 
				result.add(mapper.mapRow(rs, rowNum++));
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
	
	public List<T> getResult() {
		return result;
	}
}

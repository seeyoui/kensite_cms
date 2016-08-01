package com.seeyoui.kensite.common.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.web.util.HtmlUtils;

public class DBUtils {
	private static DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * 获取默认数据库连接
	 * @return
	 * @throws Exception
	 */
	public static Connection getConnection() throws Exception {
		Connection conn=null;
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			throw e;
		}
		return conn;
	}
	/**
	 * 关闭数据库资源
	 * @param con Connection
	 * @param stm Statement
	 * @param rst ResultSet
	 * @throws Exception
	 */
	public static void close(Connection con, Statement stm, ResultSet rst) throws Exception {
		try {
			if(rst != null) {
				rst.close();
			}
			if(stm != null) {
				stm.close();
			}
			if(con != null) {
				con.close();
			}
		} catch (SQLException e) {
			throw e;
		}
	}
	
	/**
	 * 根据sql(该sql结果必须为单行数据)语句获得某列值
	 * @param sql 查询sql
	 * @param columnName 查询列名
	 * @return
	 * @throws Exception
	 */
	public static String getString(String sql, String columnName) throws Exception {
		Connection con = getConnection();
		Statement stm = null;
		ResultSet rst = null;
		String result = "";
		try {
			stm = con.createStatement();
			rst = stm.executeQuery(sql);
			if(rst.next()) {
				result = rst.getString(columnName);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			close(con, stm, rst);
		}
		return result;
	}
	
	/**
	 * 执行一个sql查询语句，结果集转换为List<Map<Object, Object>>返回
	 * @param sql 查询sql
	 * @param camel 是否采用驼峰标识，是则map的key为列的驼峰标识，否则map的key为列的大写
	 * @return
	 * @throws Exception
	 */
	public static List<Map<Object, Object>> executeQuery(String sql, boolean camel) throws Exception {
		Connection con = getConnection();
		Statement stm = null;
		ResultSet rst = null;
		List<Map<Object, Object>> result = null;
		try {
			stm = con.createStatement();
			rst = stm.executeQuery(sql);
			result = getResultMapList(rst, camel);
		} catch (Exception e) {
			throw e;
		} finally {
			close(con, stm, rst);
		}
		return result;
	}
	
	/**
	 * 根据sql(该sql结果必须为单行数据)语句获得某列值
	 * @param con 数据库连接
	 * @param sql 查询sql
	 * @param columnName 查询列名
	 * @param close 是否关闭数据库连接
	 * @return
	 * @throws Exception
	 */
	public static String getString(Connection con, String sql, String columnName, boolean close) throws Exception {
		Statement stm = null;
		ResultSet rst = null;
		String result = "";
		try {
			stm = con.createStatement();
			rst = stm.executeQuery(sql);
			if(rst.next()) {
				result = rst.getString(columnName);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if(close) {
				close(con, stm, rst);
			}
		}
		return result;
	}
	
	/**
	 * 执行一个sql查询语句，结果集转换为List<Map<Object, Object>>返回
	 * @param con 数据库连接
	 * @param sql 查询sql
	 * @param camel 是否采用驼峰标识，是则map的key为列的驼峰标识，否则map的key为列的大写
	 * @param close 是否关闭数据库连接
	 * @return
	 * @throws Exception
	 */
	public static List<Map<Object, Object>> executeQuery(Connection con, String sql, boolean camel, boolean close) throws Exception {
		Statement stm = null;
		ResultSet rst = null;
		List<Map<Object, Object>> result = null;
		try {
			stm = con.createStatement();
			rst = stm.executeQuery(sql);
			result = getResultMapList(rst, camel);
		} catch (Exception e) {
			throw e;
		} finally {
			if(close) {
				close(con, stm, rst);
			}
		}
		return result;
	}
	
	public static List<Map<Object, Object>> getResultMapList(ResultSet rst, boolean camel) throws Exception {
		// 获取列数  
		ResultSetMetaData metaData = null;
		int columnCount = 0;
		metaData = rst.getMetaData();
		columnCount = metaData.getColumnCount();
		List<Map<Object, Object>> list = new ArrayList<Map<Object,Object>>();
		while (rst.next()) {
	        Map<Object, Object> map = new HashMap<Object, Object>();
	        // 遍历每一列  
	        for (int i = 1; i <= columnCount; i++) {
	            String columnName =metaData.getColumnLabel(i);
	            String value = rst.getString(columnName);
	            if(camel) {
	            	map.put(StringUtils.toCamelCase(columnName), HtmlUtils.htmlUnescape(value));
	            } else {
	            	map.put(columnName.toUpperCase(), HtmlUtils.htmlUnescape(value));
	            }
	        }
	        list.add(map);
	    }
		return list;
	}
}
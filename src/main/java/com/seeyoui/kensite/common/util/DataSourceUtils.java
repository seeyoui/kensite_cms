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

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class DataSourceUtils {
	
	/**
	 * 功能：获取数据库连接
	 * @return
	 * @throws Exception
	 */
	public static Connection getConnection(){
		try {
			BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource(
					"spring-mvc.xml"));
//			ApplicationContext ac = new ClassPathXmlApplicationContext("spring-mvc.xml");
			DataSource dataSource = beanFactory.getBean("dataSource", DataSource.class);
			return dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 功能：关闭连接
	 * @param con
	 * @param stm
	 * @param rst
	 */
	public static void close(Connection con, Statement stm, ResultSet rst) {
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
			e.printStackTrace();
		}
	}
	/**
	 * 功能：查询单条sql，并获取单个返回值
	 * @param sql
	 * @param columnName
	 * @return
	 */
	public static String getString(String sql, String columnName) {
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(con, stm, rst);
		}
		return result;
	}
	/**
	 * 功能：查询sql，并返回ResultSet结果集
	 * @param sql
	 * @return
	 */
	public static ResultSet executeQuery(String sql) {
		Connection con = getConnection();
		Statement stm = null;
		ResultSet rst = null;
		try {
			stm = con.createStatement();
			rst = stm.executeQuery(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		}
		return rst;
	}
	/**
	 * 功能：根据Connection，sql，列名查询单个结果
	 * @param con
	 * @param sql
	 * @param columnName
	 * @return
	 */
	public static String getString(Connection con, String sql, String columnName) {
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(con, stm, rst);
		}
		return result;
	}
	
	/**
	 * 功能：获取ResultSet结果集
	 * @param con
	 * @param sql
	 * @return
	 */
	public static ResultSet executeQuery(Connection con, String sql) {
		Statement stm = null;
		ResultSet rst = null;
		try {
			stm = con.createStatement();
			rst = stm.executeQuery(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		}
		return rst;
	}
	/**
	 * 功能：结果集中有多条数据时，通过对resultSet的遍历获取数据
	 * @param rst
	 * @return
	 * @throws Exception
	 */
	public static List<Map<Object, Object>> getResultMapList(ResultSet rst) throws Exception {
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
	            map.put(columnName.toUpperCase(), value);
	        }
	        list.add(map);
	    }
		return list;
	}
	public static void main(String[] args) {
		String sql = "select s_book_num_seq.nextval as num from dual";
		String str = getString(sql, "num");
		System.out.println(str);
	}
}

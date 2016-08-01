package com.seeyoui.kensite.sqlGeneration;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.seeyoui.kensite.common.util.DBUtils;
import com.seeyoui.kensite.common.util.StringUtils;

public class Oracle2Oracle {
	private static Connection con = null;
	private static Statement sta = null;
	private static Statement ssta = null;
	private static Statement dsta = null;
	private static Statement psta = null;
	private static ResultSet rs = null;
	private static ResultSet srs = null;
	private static ResultSet drs = null;
	private static ResultSet prs = null;
	
	private static final String IP = "192.168.31.251";
	private static final String USERNAME = "kensitecms";
	private static final String PASSWORD = "kensite";

	public static void main(String[] args) {
		String filePath = "d:/kensite.oracle.sql";
//		System.out.println(createSql());
		contentToTxt(filePath, createSql());
		System.out.println("写入建表语句完成");
//		System.out.println(insertSql());
		contentToTxt(filePath, insertSql());
		System.out.println("写入插入语句完成");
	}

	private static String createSql() {
		con = DBConnection.oracleConn(USERNAME, PASSWORD, IP);
		StringBuffer result = new StringBuffer();
		String sql = "SELECT T.TABLE_NAME,TC.COMMENTS FROM USER_TABLES T LEFT JOIN USER_TAB_COMMENTS TC ON T.TABLE_NAME = TC.TABLE_NAME WHERE T.TABLE_NAME NOT LIKE 'ACT%' ORDER BY T.TABLE_NAME ASC ";
		String sub = "SELECT T.COLUMN_NAME,T.DATA_TYPE,T.DATA_LENGTH,T.DATA_PRECISION,T.DATA_SCALE,T.NULLABLE,CC.COMMENTS FROM USER_TAB_COLUMNS T LEFT JOIN USER_COL_COMMENTS CC ON T.TABLE_NAME=CC.TABLE_NAME AND T.COLUMN_NAME=CC.COLUMN_NAME";
		String pk = "SELECT 'ALTER TABLE '||CU.TABLE_NAME||' ADD CONSTRAINT '||CU.CONSTRAINT_NAME||' PRIMARY KEY ('||WM_CONCAT(CU.COLUMN_NAME)||');' FROM USER_CONS_COLUMNS CU, USER_CONSTRAINTS AU WHERE CU.CONSTRAINT_NAME = AU.CONSTRAINT_NAME AND AU.CONSTRAINT_TYPE = 'P' AND AU.TABLE_NAME = '#TABLENAME#' GROUP BY CU.TABLE_NAME,CU.CONSTRAINT_NAME";
		try {
			sta = con.createStatement();
			ssta = con.createStatement();
			rs = sta.executeQuery(sql);
			while (rs.next()) {
				String tableName = rs.getString(1);
				String tableComments = rs.getString(2);
				result.append("drop table ".toUpperCase()+tableName+" cascade constraints;".toUpperCase());
				result.append("\n");
				result.append("CREATE TABLE " + tableName + " (");
				result.append("\n");
				srs = ssta.executeQuery(sub + " WHERE T.TABLE_NAME LIKE '"
						+ tableName + "' ORDER BY T.COLUMN_ID ASC ");
				List<String> commentsList = new ArrayList<String>();
				List<String> columnList = new ArrayList<String>();
				while (srs.next()) {
					String column = srs.getString(1);
					columnList.add(column);
					String type = srs.getString(2);
					int length = srs.getInt(3);
					int precision = srs.getInt(4);
					int scale = srs.getInt(5);
					String nullAble = srs.getString(6);
					String comments = srs.getString(7);
					commentsList.add(comments);
					result.append(column + " "
							+ jdbcType(type, length, precision, scale)
							+ nullAble(nullAble)+",");
					result.append("\n");
				}
				result.deleteCharAt(result.lastIndexOf(","));
				result.append(");");
				result.append("\n");
				psta = con.createStatement();
				prs = psta.executeQuery(pk.replace("#TABLENAME#", tableName));
				while (prs.next()) {
					result.append(prs.getString(1));
				}
				result.append("\n");
				result.append("COMMENT ON TABLE "+tableName+" IS '" + tableComments + "';");
				result.append("\n");
				for(int i=0; i<columnList.size(); i++) {
					String column = columnList.get(i);
					String comments = commentsList.get(i);
					result.append("COMMENT ON COLUMN "+tableName+"."+column+" IS '" + comments + "';");
					result.append("\n");
				}
				result.append("\n");
				result.append("\n");
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result.toString();
	}

	private static String insertSql() {
		con = DBConnection.oracleConn(USERNAME, PASSWORD, IP);

		StringBuffer finalResult = new StringBuffer();
		String sql = "SELECT T.TABLE_NAME,TC.COMMENTS FROM USER_TABLES T LEFT JOIN USER_TAB_COMMENTS TC ON T.TABLE_NAME = TC.TABLE_NAME WHERE T.TABLE_NAME NOT LIKE 'ACT%' ORDER BY T.TABLE_NAME ASC ";
		String sub = "SELECT T.COLUMN_NAME,T.DATA_TYPE,T.DATA_LENGTH,T.DATA_PRECISION,T.DATA_SCALE,T.NULLABLE,CC.COMMENTS FROM USER_TAB_COLUMNS T LEFT JOIN USER_COL_COMMENTS CC ON T.TABLE_NAME=CC.TABLE_NAME AND T.COLUMN_NAME=CC.COLUMN_NAME";
		try {
			sta = con.createStatement();
			ssta = con.createStatement();
			rs = sta.executeQuery(sql);
			dsta = con.createStatement();
			while (rs.next()) {
				String tableName = rs.getString(1);
				String tableComments = rs.getString(2);
				drs = dsta.executeQuery("SELECT * FROM " + tableName);
				List<Map<Object, Object>> dataMapList = DBUtils
						.getResultMapList(drs, false);
				srs = ssta.executeQuery(sub + " WHERE T.TABLE_NAME LIKE '"
						+ tableName + "' ORDER BY T.COLUMN_ID ASC ");
				List<String> columnList = new ArrayList<String>();
				List<String> columnDataTypeList = new ArrayList<String>();
				while (srs.next()) {
					String column = srs.getString(1);
					columnList.add(column);
					String columnDataType = srs.getString(2);
					columnDataTypeList.add(columnDataType);
				}
				for (Map<Object, Object> dataMap : dataMapList) {
					StringBuffer result = new StringBuffer();
					result.append("INSERT INTO " + tableName + " (");
					for (String column : columnList) {
						result.append(column + ",");
					}
					result.deleteCharAt(result.lastIndexOf(","));
					result.append(") \n");
					result.append("VALUES (");
					for (int i = 0; i < columnList.size(); i++) {
						String column = columnList.get(i);
						String columnDataType = columnDataTypeList.get(i);
						if (null == dataMap.get(column.toUpperCase())
								|| StringUtils.isBlank(dataMap.get(
										column.toUpperCase()).toString())
								|| "NULL".equals(dataMap.get(
										column.toUpperCase()).toString())) {
							result.append("null,");
						} else {
							String columnData = dataMap.get(column.toUpperCase())
									.toString();
							columnData = turnString(columnData);
							if ("CHAR".equals(columnDataType)
									|| "VARCHAR2".equals(columnDataType)
									|| "CLOB".equals(columnDataType)) {
								result.append("'"
										+ columnData + "',");
							} else if ("DATE".equals(columnDataType)) {
								result.append("to_date('"
										+ columnData.substring(0, 19)
										+ "','yyyy-mm-dd hh24:mi:ss'),");
							} else {
								result.append(columnData + ",");
							}
						}
					}
					result.deleteCharAt(result.lastIndexOf(","));
					result.append(");");
					result.append("\n");
					result.append("\n");
					finalResult.append(result);
				}
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return finalResult.toString();
	}

	private static String jdbcType(String type, int length, int precision,
			int scale) {
		if ("CHAR".equals(type)) {
			return "CHAR(" + length + ")";
		}
		if ("VARCHAR2".equals(type)) {
			return "VARCHAR2(" + length + ")";
		}
		if ("DATE".equals(type)) {
			return "DATE";
		}
		if ("NUMBER".equals(type)) {
			if (scale == 0 && precision == 0) {
				return "NUMBER(10)";
			} else if (scale == 0) {
				return "NUMBER(" + precision + ")";
			} else {
				return "NUMBER(" + precision + "," + scale + ")";
			}
		}
		if ("CLOB".equals(type)) {
			return "CLOB";
		}
		return "";
	}

	private static String nullAble(String nullAble) {
		if (nullAble != null && "N".equals(nullAble)) {
			return " not null ";
		}
		return "";
	}
	
	private static String turnString(String data) {
		return data.replaceAll("'", "''").replaceAll("&gt;", ">").replaceAll("&lt;", "<");
	}

	public static void contentToTxt(String filePath, String content) {
		String str = new String(); // 原有txt内容
		String s1 = new String();// 内容更新
		try {
			File f = new File(filePath);
			if (f.exists()) {
				System.out.println("文件存在,正在写入...");
			} else {
				System.out.println("文件不存在,正在创建并写入...");
				f.createNewFile();// 不存在则创建
			}
			BufferedReader input = new BufferedReader(new FileReader(f));

			while ((str = input.readLine()) != null) {
				s1 += str + "\n";
			}
			input.close();
			s1 += content;

			BufferedWriter output = new BufferedWriter(new FileWriter(f));
			output.write(s1);
			output.close();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

}

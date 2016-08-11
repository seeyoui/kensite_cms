package com.seeyoui.kensite.modCarrier;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.seeyoui.kensite.common.util.DBUtils;
import com.seeyoui.kensite.common.util.StringUtils;

public class CreateSql {
    public static Connection oracleConn(Carrier carrier) {
        Connection c = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            c = DriverManager.getConnection(
            		carrier.getJdbcUrl(), carrier.getJdbcUserName(), carrier.getJdbcPassWord());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
     }

	public static String createSql(Carrier carrier) {
		Connection con = oracleConn(carrier);
		StringBuffer result = new StringBuffer();
		String sql = "SELECT T.TABLE_NAME,TC.COMMENTS FROM USER_TABLES T LEFT JOIN USER_TAB_COMMENTS TC ON T.TABLE_NAME = TC.TABLE_NAME WHERE T.TABLE_NAME = '"+carrier.getTableName()+"'";
		String sub = "SELECT T.COLUMN_NAME,T.DATA_TYPE,T.DATA_LENGTH,T.DATA_PRECISION,T.DATA_SCALE,T.NULLABLE,CC.COMMENTS FROM USER_TAB_COLUMNS T LEFT JOIN USER_COL_COMMENTS CC ON T.TABLE_NAME=CC.TABLE_NAME AND T.COLUMN_NAME=CC.COLUMN_NAME WHERE T.TABLE_NAME='"+carrier.getTableName()+"' ORDER BY T.COLUMN_ID ASC";
		String pk = "SELECT 'ALTER TABLE '||CU.TABLE_NAME||' ADD CONSTRAINT '||CU.CONSTRAINT_NAME||' PRIMARY KEY ('||WM_CONCAT(CU.COLUMN_NAME)||');' FROM USER_CONS_COLUMNS CU, USER_CONSTRAINTS AU WHERE CU.CONSTRAINT_NAME = AU.CONSTRAINT_NAME AND AU.CONSTRAINT_TYPE = 'P' AND AU.TABLE_NAME = '"+carrier.getTableName()+"' GROUP BY CU.TABLE_NAME,CU.CONSTRAINT_NAME";
		try {
			Statement sta = con.createStatement();
			Statement ssta = con.createStatement();
			ResultSet rs = sta.executeQuery(sql);
			while (rs.next()) {
				String tableName = rs.getString(1);
				String tableComments = rs.getString(2);
//				result.append("drop table ".toUpperCase()+tableName+" cascade constraints;".toUpperCase());
				result.append("\n");
				result.append("CREATE TABLE " + tableName + " (");
				ResultSet srs = ssta.executeQuery(sub);
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
				}
				result.deleteCharAt(result.lastIndexOf(","));
				result.append(");");
				result.append("\n");
				result.append("\n");
				Statement psta = con.createStatement();
				ResultSet prs = psta.executeQuery(pk);
				while (prs.next()) {
					result.append(prs.getString(1));
				}
				result.append("\n");
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

	public static String insertSql(Carrier carrier) {
		Connection con = oracleConn(carrier);

		StringBuffer finalResult = new StringBuffer();
		String sql = "SELECT T.TABLE_NAME,TC.COMMENTS FROM USER_TABLES T LEFT JOIN USER_TAB_COMMENTS TC ON T.TABLE_NAME = TC.TABLE_NAME WHERE T.TABLE_NAME = '"+carrier.getTableName()+"'";
		String sub = "SELECT T.COLUMN_NAME,T.DATA_TYPE,T.DATA_LENGTH,T.DATA_PRECISION,T.DATA_SCALE,T.NULLABLE,CC.COMMENTS FROM USER_TAB_COLUMNS T LEFT JOIN USER_COL_COMMENTS CC ON T.TABLE_NAME=CC.TABLE_NAME AND T.COLUMN_NAME=CC.COLUMN_NAME WHERE T.TABLE_NAME='"+carrier.getTableName()+"' ORDER BY T.COLUMN_ID ASC";
		try {
			Statement sta = con.createStatement();
			Statement ssta = con.createStatement();
			ResultSet rs = sta.executeQuery(sql);
			Statement dsta = con.createStatement();
			while (rs.next()) {
				String tableName = rs.getString(1);
				ResultSet drs = dsta.executeQuery("SELECT * FROM " + tableName);
				List<Map<Object, Object>> dataMapList = DBUtils
						.getResultMapList(drs, false);
				ResultSet srs = ssta.executeQuery(sub);
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
					result.append(") ");
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

	public static String modInsertSql(Carrier carrier) {
		Connection con = oracleConn(carrier);

		StringBuffer finalResult = new StringBuffer();
		String tableSql = "SELECT T.COLUMN_NAME,T.DATA_TYPE,T.DATA_LENGTH,T.DATA_PRECISION,T.DATA_SCALE,T.NULLABLE,CC.COMMENTS FROM USER_TAB_COLUMNS T LEFT JOIN USER_COL_COMMENTS CC ON T.TABLE_NAME=CC.TABLE_NAME AND T.COLUMN_NAME=CC.COLUMN_NAME WHERE T.TABLE_NAME='MOD_TABLE' ORDER BY T.COLUMN_ID ASC";
		String columnSql = "SELECT T.COLUMN_NAME,T.DATA_TYPE,T.DATA_LENGTH,T.DATA_PRECISION,T.DATA_SCALE,T.NULLABLE,CC.COMMENTS FROM USER_TAB_COLUMNS T LEFT JOIN USER_COL_COMMENTS CC ON T.TABLE_NAME=CC.TABLE_NAME AND T.COLUMN_NAME=CC.COLUMN_NAME WHERE T.TABLE_NAME='MOD_TABLE_COLUMN' ORDER BY T.COLUMN_ID ASC";
		try {
			Statement staTable = con.createStatement();
			ResultSet rsTable = staTable.executeQuery(tableSql);
			List<String> columnListTable = new ArrayList<String>();
			List<String> columnDataTypeListTable = new ArrayList<String>();
			while (rsTable.next()) {
				String columnName = rsTable.getString(1);
				columnListTable.add(columnName);
				String columnDataType = rsTable.getString(2);
				columnDataTypeListTable.add(columnDataType);
			}
			
			Statement staColumn = con.createStatement();
			ResultSet rsColumn = staColumn.executeQuery(columnSql);
			List<String> columnListColumn = new ArrayList<String>();
			List<String> columnDataTypeListColumn = new ArrayList<String>();
			while (rsColumn.next()) {
				String columnName = rsColumn.getString(1);
				columnListColumn.add(columnName);
				String columnDataType = rsColumn.getString(2);
				columnDataTypeListColumn.add(columnDataType);
			}
			
			String tableDataSql = "SELECT * FROM MOD_TABLE WHERE NAME = '"+carrier.getTableName()+"'";
			Statement staTableData = con.createStatement();
			ResultSet rsTableData = staTableData.executeQuery(tableDataSql);
			List<Map<Object, Object>> tableMapList = DBUtils.getResultMapList(rsTableData, false);
			
			String columnDataSql = "SELECT * FROM MOD_TABLE_COLUMN WHERE TABLE_NAME = '"+carrier.getTableName()+"'";
			Statement staColumnData = con.createStatement();
			ResultSet rsColumnData = staColumnData.executeQuery(columnDataSql);
			List<Map<Object, Object>> columnMapList = DBUtils.getResultMapList(rsColumnData, false);
			
			for (Map<Object, Object> dataMap : tableMapList) {
				StringBuffer result = new StringBuffer();
				result.append("INSERT INTO MOD_TABLE (");
				for (String column : columnListTable) {
					result.append(column + ",");
				}
				result.deleteCharAt(result.lastIndexOf(","));
				result.append(") ");
				result.append("VALUES (");
				for (int i = 0; i < columnListTable.size(); i++) {
					String column = columnListTable.get(i);
					String columnDataType = columnDataTypeListTable.get(i);
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
			
			for (Map<Object, Object> dataMap : columnMapList) {
				StringBuffer result = new StringBuffer();
				result.append("INSERT INTO MOD_TABLE_COLUMN (");
				for (String column : columnListColumn) {
					result.append(column + ",");
				}
				result.deleteCharAt(result.lastIndexOf(","));
				result.append(") ");
				result.append("VALUES (");
				for (int i = 0; i < columnListColumn.size(); i++) {
					String column = columnListColumn.get(i);
					String columnDataType = columnDataTypeListColumn.get(i);
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
}

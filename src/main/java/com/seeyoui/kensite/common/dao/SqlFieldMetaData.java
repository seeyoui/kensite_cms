/**
 * 
 */
package com.seeyoui.kensite.common.dao;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

/**
 * SQL字段结构定义
 * 
 * @author Administrator
 *
 */
public class SqlFieldMetaData {
	public static String JAVATYPE_INT = "int";
	public static String JAVATYPE_LONG = "long";
	public static String JAVATYPE_NUMERIC = "numeric";
	public static String JAVATYPE_STRING = "string";
	public static String JAVATYPE_DATE = "date";
	public static String JAVATYPE_DATETIME = "datetime";
	public static String JAVATYPE_BOOLEAN = "boolean";
	public static String JAVATYPE_BYTEDATA = "byte[]";
	public static String JAVATYPE_OBJECT = "object";
	public static String JAVATYPE_NULL = "null";
	
	private static Map<Integer, String> javaTypeMapping = new HashMap<Integer, String>();
	static {
		javaTypeMapping.put(Types.BIT, JAVATYPE_INT);
		javaTypeMapping.put(Types.TINYINT, JAVATYPE_INT);
		javaTypeMapping.put(Types.SMALLINT, JAVATYPE_INT);
		javaTypeMapping.put(Types.INTEGER, JAVATYPE_INT);
		javaTypeMapping.put(Types.BIGINT, JAVATYPE_LONG);
		javaTypeMapping.put(Types.FLOAT, JAVATYPE_NUMERIC);
		javaTypeMapping.put(Types.REAL, JAVATYPE_NUMERIC);
		javaTypeMapping.put(Types.DOUBLE, JAVATYPE_NUMERIC);
		javaTypeMapping.put(Types.NUMERIC, JAVATYPE_NUMERIC);
		javaTypeMapping.put(Types.DECIMAL, JAVATYPE_NUMERIC);
		javaTypeMapping.put(Types.CHAR, JAVATYPE_STRING);
		javaTypeMapping.put(Types.VARCHAR, JAVATYPE_STRING);
		javaTypeMapping.put(Types.LONGVARCHAR, JAVATYPE_STRING);
		javaTypeMapping.put(Types.DATE, JAVATYPE_DATE);
		javaTypeMapping.put(Types.TIME, JAVATYPE_DATETIME);
		javaTypeMapping.put(Types.TIMESTAMP, JAVATYPE_DATETIME);
		javaTypeMapping.put(Types.BINARY, JAVATYPE_BYTEDATA);
		javaTypeMapping.put(Types.VARBINARY, JAVATYPE_BYTEDATA);
		javaTypeMapping.put(Types.LONGVARBINARY, JAVATYPE_BYTEDATA);
		javaTypeMapping.put(Types.NULL, JAVATYPE_NULL);
		javaTypeMapping.put(Types.JAVA_OBJECT, JAVATYPE_OBJECT);
		javaTypeMapping.put(Types.BLOB, JAVATYPE_BYTEDATA);
		javaTypeMapping.put(Types.CLOB, JAVATYPE_BYTEDATA);
		javaTypeMapping.put(Types.BOOLEAN, JAVATYPE_BOOLEAN);
		javaTypeMapping.put(Types.ROWID, JAVATYPE_STRING);
		javaTypeMapping.put(Types.NCHAR, JAVATYPE_STRING);
		javaTypeMapping.put(Types.NVARCHAR, JAVATYPE_STRING);
		javaTypeMapping.put(Types.LONGNVARCHAR, JAVATYPE_STRING);
		javaTypeMapping.put(Types.NCLOB, JAVATYPE_BYTEDATA);
	}
	
	private String name;
	
	private String label;
	
	private int type;
	
	private String typeName;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	public String getJavaTypeName() {
		return javaTypeMapping.get(type);
	}

	@Override
	public String toString() {
		return "SqlFieldMetaData [name=" + name + ", label=" + label
				+ ", type=" + type + ", typeName=" + typeName + ", javaTypeName=" + getJavaTypeName() + "]";
	}
}

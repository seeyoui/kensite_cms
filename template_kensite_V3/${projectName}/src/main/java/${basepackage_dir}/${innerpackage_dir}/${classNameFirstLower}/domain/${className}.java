<#include "/custom.include">
<#include "/java_copyright.include">
<#assign className = table.className> 
<#assign classNameLower = className?uncap_first>
<#function getJavaType column>
<#assign dbtype=column.sqlTypeName?lower_case>
<#assign colname=column.columnName?lower_case>
<#assign rtn>
<#if dbtype=="number" >
	<#if column.decimalDigits==0>
	String
	<#else>
	String
	</#if>
<#elseif (dbtype=="varchar2"||dbtype=="char"||dbtype=="nvarchar2")>
String
<#elseif (dbtype=="clob")>
String
<#elseif (dbtype=="date")>
java.util.Date
</#if>
</#assign>
<#return rtn?trim>
</#function>

package ${basepackage}.${innerpackage}.${table.classNameFirstLower}.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ${basepackage}.common.base.domain.DataEntity;
import ${basepackage}.common.util.excel.annotation.ExcelField;

<#include "/java_imports.include">

@JsonIgnoreProperties(ignoreUnknown = true)
public class ${className} extends DataEntity<${className}> {
	private static final long serialVersionUID = 1L;

	<#list table.columns as column>
	<#if (column.columnName?lower_case=="id"||column.columnName?lower_case=="createuser"||column.columnName?lower_case=="createdate"||column.columnName?lower_case=="updateuser"||column.columnName?lower_case=="updatedate"||column.columnName?lower_case=="remarks"||column.columnName?lower_case=="delflag") ><#else>
	<#if (column.sqlTypeName?lower_case=="date")>
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	</#if>
	@ExcelField(title="${column.columnAlias}", type=1, align=2, sort=${column_index}, mod="${table.sqlName}|${column.sqlName}")
	private ${getJavaType(column)} ${column.columnNameLower};//${column.columnAlias}
	</#if>
	</#list>

	<#list table.columns as column>
	<#if (column.columnName?lower_case=="id"||column.columnName?lower_case=="createuser"||column.columnName?lower_case=="createdate"||column.columnName?lower_case=="updateuser"||column.columnName?lower_case=="updatedate"||column.columnName?lower_case=="remarks"||column.columnName?lower_case=="delflag") ><#else>
	public void set${column.columnName}(${getJavaType(column)} ${column.columnNameLower}) {
		this.${column.columnNameLower} = ${column.columnNameLower};
	}

	public ${getJavaType(column)} get${column.columnName}() {
		return this.${column.columnNameLower};
	}
	</#if>
	</#list>
}
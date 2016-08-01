<%@ page import="com.seeyoui.kensite.common.constants.StringConstant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/taglib/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>    
    <title>数据库字典</title>
	<%@ include file="/WEB-INF/view/taglib/header.jsp" %>
	<%@ include file="/WEB-INF/view/taglib/easyui.jsp" %>
	<%@ include file="/WEB-INF/view/taglib/layer.jsp" %>
  </head>
  <body style="overflow:hidden">
 	<div style="position:absolute;top:0px;left:0px;right:0px;bottom:0px;">
		<div style="position:absolute;top:0px;bottom:0px;width:600px;">
		    <table id="dataList" title="数据表" class="easyui-datagrid" style="width:100%;height:100%"
		    		url="${ctx}/sys/userTables/list/data"
		            toolbar="#toolbar" pagination="true"
		            rownumbers="true" fitColumns="true" singleSelect="false">
		        <thead>
		            <tr>
		            	<th data-options="field:'ck',checkbox:true"></th>
					    <th field="tableName" width="100px">表名</th>
					    <th field="tableType" width="100px" formatter="formatTypeCol">类型</th>
					    <th field="comments" width="100px">注释</th>
					    <th field="tablespaceName" width="100px">表空间</th>
					    <th field="numRows" width="50px" align="right">记录数</th>
		            </tr>
		        </thead>
		    </table>
		    <div id="toolbar">
				表名<input id="sel_tableName" name="sel_tableName" class="easyui-textbox" data-options="" style="width:100px;"/>
				类型<input id="sel_tableType" name="sel_tableType"  class="easyui-combobox" data-options="panelHeight: 'auto',valueField: 'value',textField: 'text'" style="width:80px;"/>
				注释<input id="sel_tab_comments" name="sel_tab_comments" class="easyui-textbox" data-options="" style="width:100px;"/>
				表空间<input id="sel_tablespaceName" name="sel_tablespaceName" class="easyui-textbox" data-options="" style="width:100px;"/>
			    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="selectData()">查询</a>
		    </div>
	    </div>
		<div style="position:absolute;top:0px;right:0px;left:600px;bottom:0px;">
		    <table id="subDataList" title="数据列" class="easyui-datagrid" style="width:100%;height:100%"
		            toolbar="#subtoolbar" pagination="true"
		            rownumbers="true" fitColumns="true" singleSelect="true">
		        <thead>
		            <tr>
					    <th field="columnId" width="50px" align="right" hidden>顺序号</th>
					    <th field="tableName" width="100px" hidden>表名</th>
					    <th field="columnName" width="100px">列名</th>
					    <th field="viewDataType" width="100px">类型</th>
					    <th field="dataLength" width="50px" align="right" hidden>长度</th>
					    <th field="dataPrecision" width="50px" align="right" hidden>精度</th>
					    <th field="dataScale" width="50px" align="right" hidden>量级</th>
					    <th field="nullable" width="100px" formatter="formatNullable">是否可为空</th>
					    <th field="numDistinct" width="50px" align="right" hidden>numDistinct</th>
					    <th field="density" width="50px" align="right" hidden>density</th>
					    <th field="numNulls" width="50px" align="right" hidden>numNulls</th>
					    <th field="numBuckets" width="50px" align="right" hidden>numBuckets</th>
					    <th field="avgColLen" width="50px" align="right" hidden>avgColLen</th>
					    <th field="charLength" width="50px" align="right" hidden>charLength</th>
					    <th field="charUsed" width="100px" hidden>charUsed</th>
					    <th field="comments" width="100px">注释</th>
		            </tr>
		        </thead>
		    </table>
		    <div id="subtoolbar">
				列名<input id="sel_columnName" name="sel_columnName" class="easyui-textbox" data-options="" style="width:100px;"/>
				类型<input id="sel_dataType" name="sel_dataType" class="easyui-textbox" data-options="" style="width:100px;"/>
				是否可为空<input id="sel_nullable" name="sel_nullable" class="easyui-combobox" data-options="panelHeight: 'auto',valueField: 'value',textField: 'text'" style="width:40px;"/>
				注释<input id="sel_col_comments" name="sel_col_comments" class="easyui-textbox" data-options="" style="width:100px;"/>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="selectSubData()">查询</a>
			    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-page_excel" plain="true" onclick="expertExcel()">导出</a>
		    </div>
	    </div>
    </div>
    <script type="text/javascript">
    var typeJson = [{text: '数据表',value: 'TABLE'	},{text: '数据视图',value: 'VIEW'}];
    var nullableJson = [{text: '是',value: 'Y'},{text: '否',value: 'N'}];
    var tableName;
    $(document).ready(function(){
		$('#sel_tableType').combobox('loadData', typeJson);
		$('#sel_nullable').combobox('loadData', nullableJson);
    	$('#dataList').datagrid({
    		onDblClickRow: function(index,row){
				tableName = row.tableName;
				changeTabCol(row.tableName);
			}
		});
    	$('#subDataList').datagrid('loadData',{total:0,rows:[]});
    });
    
    function changeTabCol(tableName) {
    	//清空历史查询结果
    	$('#subDataList').datagrid('loadData',{total:0,rows:[]});
    	$('#subDataList').datagrid({url:'${ctx}/sys/userTabColumns/list/data?tableName='+tableName});
    }
    
    function selectData() {
	    var sel_tableName = $("#sel_tableName").val();
	    var sel_tablespaceName = $("#sel_tablespaceName").val();
	    var sel_tableType = $("#sel_tableType").combobox('getValue');
	    var sel_tab_comments = $("#sel_tab_comments").val();
    	$('#dataList').datagrid('load',{
		    tableName:sel_tableName,
		    tablespaceName:sel_tablespaceName,
		    tableType:sel_tableType,
		    comments:sel_tab_comments
    	});
    }
    
    function formatTypeCol(val, row) {
    	if(val == null) {
    		return "";
    	}
    	var typeStr = "";
    	for(var obj in typeJson) {
    		if(val.indexOf(typeJson[obj].value) != -1) {
    			typeStr = typeJson[obj].text;
    		}
    	}
		return typeStr;
    }
    
    function formatDataType(val,row) {
    	if(val==null) {
    		return "";
    	}
    	var res = val;
    	if(val == "CHAR" || val == "VARCHAR2" || val == "NVARCHAR2") {
    		res += "("+row.charLength+")";
    	} else if(val == "DATE") {
    		res += "";
    	} else if(val == "NUMBER") {
    		if(row.dataScale != 0 && row.dataPrecision != "") {
    			res += "("+row.dataPrecision+","+row.dataScale+")";
    		} else if(row.dataPrecision != "") {
    			res += "("+row.dataPrecision+")";
    		}
    	}
    	return res;
    }
    
    function formatNullable(val,row) {
    	if(nullableJson == null) {
    		return "";
    	}
    	for(var obj in nullableJson) {
    		if(nullableJson[obj].value == val) {
    			return nullableJson[obj].text;
    		}
    	}
    }
    
    function selectSubData() {
	    var sel_columnName = $("#sel_columnName").val();
	    var sel_dataType = $("#sel_dataType").val();
	    var sel_nullable = $("#sel_nullable").combobox('getValue');
	    var sel_col_comments = $("#sel_col_comments").val();
    	$('#subDataList').datagrid('load',{
		    columnName:sel_columnName,
		    dataType:sel_dataType,
		    nullable:sel_nullable,
		    comments:sel_col_comments
    	});
    }
    
    function expertExcel() {
    	if(tableName == null) {
    		return;
    	}
    	window.open("${ctx}/sys/db/export?tableName="+tableName);
    }
    </script>
  </body>
</html>

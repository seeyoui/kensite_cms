<%@ page import="com.seeyoui.kensite.common.constants.StringConstant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/taglib/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>    
    <title>系统日志</title>
	<%@ include file="/WEB-INF/view/taglib/header.jsp" %>
	<%@ include file="/WEB-INF/view/taglib/easyui.jsp" %>
	<%@ include file="/WEB-INF/view/taglib/layer.jsp" %>
  </head>
  <body>
  
  	<div style="position:absolute;top:0px;left:0px;right:0px;bottom:0px;">
		<div style="position:absolute;top:0px;left:0px;right:0px;bottom:0px;">
		    <table id="dataList" title="" class="easyui-datagrid" style="width:100%;height:100%"
		    		url="${ctx}/sys/log/list/data"
		            toolbar="#toolbar" pagination="true"
		            rownumbers="true" fitColumns="true" singleSelect="true">
		        <thead>
		            <tr>
					    <th data-options="field:'id',hidden:true">ID</th>
					    <th field="type" width="100px">类型</th>
					    <th field="title" width="100px">标题</th>
					    <th field="remoteAddr" width="100px">IP</th>
					    <th field="userAgent" width="100px">代理信息</th>
					    <th field="requestUri" width="100px">URI</th>
					    <th field="method" width="50px">方式</th>
					    <th field="params" width="150px">提交数据</th>
					    <th field="exception" width="100px" data-options="hidden:true">异常信息</th>
					    <th field="spendTime" width="50px">耗时</th>
		            </tr>
		        </thead>
		    </table>
		    <div id="toolbar">
				<span class="toolbar-type">类型</span><input id="sel_type" name="sel_type" class="easyui-textbox" data-options=""/>
				<span class="toolbar-title">标题</span><input id="sel_title" name="sel_title" class="easyui-textbox" data-options=""/>
				<span class="toolbar-method">方式</span><input id="sel_method" name="sel_method" class="easyui-textbox" data-options=""/>
				<span class="toolbar-exception">异常信息</span><input id="sel_exception" name="sel_exception" class="easyui-textbox" data-options=""/>
			    <a href="javascript:void(0)" class="easyui-linkbutton success" iconCls="icon-search" plain="true" onclick="selectData()">查询</a>
		    </div>
	    </div>
    </div>
    <script type="text/javascript">
	    $(document).ready(function(){
	    	$('#dataList').datagrid({
	    		onDblClickRow: function(rowIndex, rowData) {
	    			if(rowData.exception) {
		    			layer.open({
		    				type: 1,
		    				title: false,
		    				closeBtn: 0,
		    				shadeClose: true,
		    				content: rowData.exception
		    			});
	    			}
	    		}
	    	});
	    });
	    
	    function selectData() {
		    var sel_type = $("#sel_type").val();
		    var sel_title = $("#sel_title").val();
		    var sel_method = $("#sel_method").val();
		    var sel_exception = $("#sel_exception").val();
        	$('#dataList').datagrid('load',{
        		type : sel_type,
        		title : sel_title,
        		method : sel_method,
        		exception : sel_exception
        	});
        }
    </script>
  </body>
</html>

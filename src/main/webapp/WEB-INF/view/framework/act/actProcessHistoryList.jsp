<%@ page import="com.seeyoui.kensite.common.constants.StringConstant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/taglib/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>    
    <title>已结束流程管理</title>
	<%@ include file="/WEB-INF/view/taglib/header.jsp" %>
	<%@ include file="/WEB-INF/view/taglib/easyui.jsp" %>
	<%@ include file="/WEB-INF/view/taglib/layer.jsp" %>
  </head>
  <body>
  
  	<div style="position:absolute;top:0px;left:0px;right:0px;bottom:0px;">
		<div style="position:absolute;top:0px;left:0px;right:0px;bottom:0px;">
		    <table id="dataList" title="" class="easyui-datagrid" style="width:100%;height:100%"
		    		url="${ctx}/actProcess/list/history"
		            toolbar="#toolbar" pagination="true"
		            rownumbers="true" fitColumns="true" singleSelect="true">
		        <thead>
		            <tr>
					    <th field="id" width="100px">执行ID</th>
					    <th field="startUserId" width="100px">流程发起人</th>
					    <th field="processInstanceId" width="100px">流程实例ID</th>
					    <th field="processDefinitionId" width="100px">流程定义ID</th>
					    <th field="startTime" width="100px" formatter="formatDateTimeCol">流程启动时间</th>
					    <th field="endTime" width="100px" formatter="formatDateTimeCol">流程结束时间</th>
					    <th field="deleteReason" width="100px" formatter="formatState">流程状态</th>
		            </tr>
		        </thead>
		    </table>
		    <div id="toolbar">
		        <!-- <a href="javascript:void(0)" class="easyui-linkbutton error" iconCls="icon-remove" plain="true" onclick="openWin()">删除流程实例</a> -->
		    </div>
	    </div>
    </div>
    <script type="text/javascript">
        function formatState(val, row){
    		if (val){
    			return "<font color='red'>[流程作废]原因："+val+"</font>";
            } else {
            	return "<font color='green'>[正常结束]</font>";
            }
        }
    	
	    $(document).ready(function(){
	    });
	    
	    function selectData() {
		    var sel_name = $("#sel_name").val();
        	$('#dataList').datagrid('load',{
    		    name:sel_name
        	});
        }
	    
	    function reloadData() {
        	selectData();
        }
        
    </script>
  </body>
</html>

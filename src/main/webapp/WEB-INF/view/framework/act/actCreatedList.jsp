<%@ page import="com.seeyoui.kensite.common.constants.StringConstant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/taglib/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>    
    <title>发起的任务</title>
	<%@ include file="/WEB-INF/view/taglib/header.jsp" %>
	<%@ include file="/WEB-INF/view/taglib/easyui.jsp" %>
	<%@ include file="/WEB-INF/view/taglib/layer.jsp" %>
  </head>
  <body>
  
  	<div style="position:absolute;top:0px;left:0px;right:0px;bottom:0px;">
		<div style="position:absolute;top:0px;left:0px;right:0px;bottom:0px;">
		    <table id="dataList" title="" class="easyui-datagrid" style="width:100%;height:100%"
		    		url="${ctx}/actTask/list/created"
		            toolbar="#toolbar" pagination="true"
		            rownumbers="true" fitColumns="true" singleSelect="true">
		        <thead>
		            <tr>
					    <th field="processName" width="110px">流程名称</th>
					    <th field="task.name" width="80px">当前环节</th>
					    <th field="task.assignee" width="80px">当前办理者</th>
					    <th field="startTime" width="100px">发起时间</th>
                        <th field="endTime" width="100px">完成时间</th>
                        <th field="status" width="50px" formatter="formatStatus">状态</th>
					    <th field="operate" width="80px" formatter="formatOperate">操作</th>
		            </tr>
		        </thead>
		    </table>
		    <div id="toolbar">
		        <input id="sel_finished" name="sel_finished" class="easyui-combobox" data-options="valueField:'value',textField:'label',editable:false,panelHeight:'auto',icons: [{iconCls:'icon-clear',handler: function(e){$(e.data.target).combobox('clear');}}],data: [{value:'',label:'全部'},{value:'finished',label:'已完成'},{value:'unfinished',label:'未完成'}]">
		        <a href="javascript:void(0)" class="easyui-linkbutton success" iconCls="icon-search" plain="true" onclick="selectData()">查询</a>
		    </div>
	    </div>
    </div>
    <script type="text/javascript">
        function formatOperate(val, row){
			var arra = new Array();
			arra.push('<a href="javascript:trace(\''+row['processDefinitionId']+'\', \''+row['task.id']+'\')">【跟踪】</a>');
			arra.push('<a href="${ctx}/actTask/form?taskId='+row['task.id']+'&taskName='+encodeURI(row['task.name'])+'&taskDefKey='+row['task.taskDefinitionKey']+'&procInsId='+row['task.processInstanceId']+'&procDefId='+row['task.processDefinitionId']+'&status=view">【任务查看】</a>');
			return arra.join("");
        }
        
        function formatStatus(val, row){
			var arra = new Array();
			if(row.endTime) {
				arra.push('<span style="color:green;">已完成</span>');
			} else {
				arra.push('<span style="color:red;">未完成</span>');
			}
			return arra.join("");
        }
    	
	    $(document).ready(function(){
	    });
	    
	    function selectData() {
		    var sel_finished = $("#sel_finished").combobox('getValue');
        	$('#dataList').datagrid('load',{
        		finished : sel_finished
        	});
        }
	    
	    function reloadData() {
        	selectData();
        }
	    
		function trace(processDefinitionId, executionId) {
			 var url = "${ctx}/actTask/trace/photo/"+processDefinitionId+"/"+executionId;
			 layer.open({
				    type: 2,
				    title: false,
				    closeBtn: false,
				    shadeClose: true,
				    shade: 0.8,
				    area: ['80%', '90%'],
				    content: url //iframe的url
				}); 
		 }
    </script>
  </body>
</html>

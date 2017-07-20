<%@ page import="com.seeyoui.kensite.common.constants.StringConstant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/taglib/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>    
    <title>待办任务</title>
	<%@ include file="/WEB-INF/view/taglib/header.jsp" %>
	<%@ include file="/WEB-INF/view/taglib/easyui.jsp" %>
	<%@ include file="/WEB-INF/view/taglib/layer.jsp" %>
  </head>
  <body>
  
  	<div style="position:absolute;top:0px;left:0px;right:0px;bottom:0px;">
		<div style="position:absolute;top:0px;left:0px;right:0px;bottom:0px;">
		    <table id="dataList" title="" class="easyui-datagrid" style="width:100%;height:100%"
		    		url="${ctx}/actTask/list/historic"
		            toolbar="#toolbar" pagination="true"
		            rownumbers="true" fitColumns="true" singleSelect="true">
		        <thead>
		            <tr>
					    <th field="vars.title" width="100px">标题</th>
					    <th field="task.name" width="100px">当前环节</th>
					    <th field="task.description" width="100px">任务内容</th>
					    <th field="procDef.name" width="100px">流程名称</th>
					    <th field="procDef.version" width="100px">流程版本</th>
					    <th field="vars.applyUserId" width="100px">流程发起人</th>
					    <th field="task.createTime" width="100px">创建时间</th>
					    <th field="operate" width="200px" formatter="formatOperate">操作</th>
		            </tr>
		        </thead>
		    </table>
		    <div id="toolbar">
		        <!-- <a href="javascript:void(0)" class="easyui-linkbutton error" iconCls="icon-remove" plain="true" onclick="openWin()">删除流程实例</a> -->
		    </div>
	    </div>
    </div>
    <script type="text/javascript">
        function formatOperate(val, row){
			var arra = new Array();
			arra.push('<a href="javascript:trace(\''+row['task.processDefinitionId']+'\', \''+row['task.executionId']+'\')">【跟踪】</a>');
			return arra.join("");
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
	    
	    /**
		 * 签收任务
		 * @param {Object} taskId
		 */
		function claim(taskId) {
			$.get('${ctx}/actTask/claim' ,{taskId: taskId}, function(data) {
	        	layer.msg("签收成功！", {icon: 6,time: layerMsgTime});
	        	reloadData();
		    });
		}
		function prompt(title, url) {
			layer.prompt({
				formType: 2,
				value: '',
				title: prompt,
				area: ['300px', '200px'] //自定义文本域宽高
			}, function(value, index, elem){
				alert(value); //得到value
				layer.close(index);
			});
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

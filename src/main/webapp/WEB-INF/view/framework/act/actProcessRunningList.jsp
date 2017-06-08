<%@ page import="com.seeyoui.kensite.common.constants.StringConstant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/taglib/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>    
    <title>运行中流程管理</title>
	<%@ include file="/WEB-INF/view/taglib/header.jsp" %>
	<%@ include file="/WEB-INF/view/taglib/easyui.jsp" %>
	<%@ include file="/WEB-INF/view/taglib/layer.jsp" %>
  </head>
  <body>
  
  	<div style="position:absolute;top:0px;left:0px;right:0px;bottom:0px;">
		<div style="position:absolute;top:0px;left:0px;right:0px;bottom:0px;">
		    <table id="dataList" title="" class="easyui-datagrid" style="width:100%;height:100%"
		    		url="${ctx}/actProcess/list/running"
		            toolbar="#toolbar" pagination="true"
		            rownumbers="true" fitColumns="true" singleSelect="true">
		        <thead>
		            <tr>
					    <th field="id" width="100px">执行ID</th>
					    <th field="processInstanceId" width="100px">流程实例ID</th>
					    <th field="processDefinitionId" width="100px">流程定义ID</th>
					    <th field="activityId" width="100px">当前环节</th>
					    <th field="suspended" width="100px" formatter="formatState">流程状态</th>
		            </tr>
		        </thead>
		    </table>
		    <div id="toolbar">
		        <a href="javascript:void(0)" class="easyui-linkbutton error" iconCls="icon-remove" plain="true" onclick="openWin()">删除流程实例</a>
		    </div>
		    <div id="dataWin" class="easyui-window" title="流程实例删除" data-options="modal:true,closed:true,iconCls:'icon-save',resizable:false" style="width:400px;height:260px;padding:10px;">
		        <div class="ftitle">删除原因</div>
		        <form id="dataForm" method="post" enctype="multipart/form-data">
					<div class="fitem">
		                <label>原因</label>
		                <input id="reason" name="reason" class="easyui-textbox" data-options="required:true"/>
		            </div>
				</form>
				
			    <div id="dataWin-buttons">
			        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="destroyInfo()" style="width:90px">保存</a>
			        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dataWin').window('close')" style="width:90px">取消</a>
			    </div>
		    </div>
	    </div>
    </div>
    <script type="text/javascript">
        function formatState(val, row){
    		if (val){
                return '挂起中';
            } else {
                return '激活中';
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
        
        function openWin(){
            var row = $('#dataList').datagrid('getSelected');
            if (row){
            	$('#dataForm').form('clear');
                $('#dataWin').window('open');
            }
        }
        var loadi;
        function destroyInfo(){
            var row = $('#dataList').datagrid('getSelected');
            var reason = $('#reason').val();
            if (row){
                $.messager.confirm('确认','你确定删除该记录吗？',function(r){
                    if (r){
                    	$.ajax({
							type: "post",
							url: "${ctx}/actProcess/deleteProcIns",
							data: {procInsId:row.processInstanceId,reason:reason},
							dataType: 'text',
							beforeSend: function(XMLHttpRequest){
							},
							success: function(data, textStatus){
								$('#dataWin').window('close'); 
			                    layer.msg(data, {time: layerMsgTime});
								reloadData();
							}
						});
                    }
                });
            }
        }
    </script>
  </body>
</html>

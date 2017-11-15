<%@ page import="com.seeyoui.kensite.common.constants.StringConstant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/taglib/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>    
    <title>流程管理</title>
	<%@ include file="/WEB-INF/view/taglib/header.jsp" %>
	<%@ include file="/WEB-INF/view/taglib/easyui.jsp" %>
	<%@ include file="/WEB-INF/view/taglib/layer.jsp" %>
  </head>
  <body>
  
  	<div style="position:absolute;top:0px;left:0px;right:0px;bottom:0px;">
		<div style="position:absolute;top:0px;left:0px;right:0px;bottom:0px;">
		    <table id="dataList" title="" class="easyui-datagrid" style="width:100%;height:100%"
		    		url="${ctx}/actProcess/list/data"
		            toolbar="#toolbar" pagination="true"
		            rownumbers="true" fitColumns="true" singleSelect="true">
		        <thead>
		            <tr>
					    <th field="id" width="100px">流程ID</th>
					    <th field="deploymentId" width="100px">部署ID</th>
					    <th field="category" width="100px" formatter="formatCategory">流程分类</th>
					    <th field="key" width="100px">流程标识</th>
					    <th field="name" width="100px" formatter="formatName">流程名称</th>
					    <th field="version" width="50px" align="right">流程版本</th>
					    <th field="resourceName" width="100px" formatter="formatProcessXml">流程XML</th>
					    <th field="diagramResourceName" width="100px" formatter="formatProcessImg">流程图片</th>
					    <th field="deploymentTime" width="100px" formatter="formatDateTimeCol">部署时间</th>
					    <th field="suspended" width="100px" formatter="formatState">流程状态</th>
		            </tr>
		        </thead>
		    </table>
		    <div id="toolbar">
		        <a href="javascript:void(0)" class="easyui-linkbutton info" iconCls="icon-edit" plain="true" onclick="active()">激活</a>
		        <a href="javascript:void(0)" class="easyui-linkbutton warning" iconCls="icon-edit" plain="true" onclick="suspend()">挂起</a>
		        <a href="javascript:void(0)" class="easyui-linkbutton primary" iconCls="icon-remove" plain="true" onclick="convertToModel()">转换为模型</a>
		        <a href="javascript:void(0)" class="easyui-linkbutton error" iconCls="icon-remove" plain="true" onclick="destroyInfo()">删除</a>
		    </div>
	    </div>
    </div>
    <script type="text/javascript">
    	function formatProcessXml(val, row){
    		var xmlstr = '<a target="_blank" href="${ctx}/actProcess/resource?procDefId='+row.id+'&resType=xml">'+val+'</a>';
            return xmlstr;
        }
        
        function formatProcessImg(val, row){
    		var imgstr = '<a target="_blank" href="${ctx}/actProcess/resource?procDefId='+row.id+'&resType=image">'+val+'</a>';
            return imgstr;
        }
        
        function formatState(val, row){
    		if (val){
                return '挂起中';
            } else {
                return '激活中';
            }
        }
		var categoryDict = ${ksfn:toJson(ksfn:getDictList('act_type'))};
	    function formatCategory(value, row, index) {
    		for(var i=0; i<categoryDict.length; i++) {
    			if(value == categoryDict[i].value) {
    				return categoryDict[i].label;
    			}
    		}
	    	return '';
	    }
	    function formatName(value, row, index) {
    		if(value) return value;
	    	return row.resourceName.replace('.bpmn20.xml', '');
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
        function destroyInfo(){
            var row = $('#dataList').datagrid('getSelected');
            if (row){
                $.messager.confirm('确认','你确定删除该记录吗？',function(r){
                    if (r){
                    	$.ajax({
							type: "post",
							url: "${ctx}/actProcess/remove",
							data: {deploymentId:row.deploymentId},
							dataType: 'text',
							beforeSend: function(XMLHttpRequest){
							},
							success: function(data, textStatus){
								if (data==TRUE){
			                        layer.msg("操作成功！", {offset: 'rb',icon: 6,shift: 8,time: layerMsgTime});
			                    } else {
				                    layer.msg("操作失败！", {offset: 'rb',icon: 5,shift: 8,time: layerMsgTime});
			                    }
								reloadData();
							}
						});
                    }
                });
            }
        }
        function active(){
            excuteCmd("激活", "update/active");
        }
        function suspend(){
            excuteCmd("挂起", "update/suspend");
        }
        function convertToModel() {
        	excuteCmd("转换", "convertToModel");
        }
        
        function excuteCmd(conText, cmd) {
        	var row = $('#dataList').datagrid('getSelected');
            if (row){
                $.messager.confirm('确认','你确定'+conText+'该记录吗？',function(r){
                    if (r){
                    	$.ajax({
							type: "post",
							url: "${ctx}/actProcess/"+cmd,
							data: {procDefId : row.id},
							dataType: 'text',
							beforeSend: function(XMLHttpRequest){
							},
							success: function(data, textStatus){
			                    layer.msg(data, {icon: 6,time: layerMsgTime});
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

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/taglib/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>${table.tableAlias}</title>
		<%@ include file="/WEB-INF/view/taglib/header.jsp" %>
		<%@ include file="/WEB-INF/view/taglib/layui.jsp" %>
		<%@ include file="/WEB-INF/view/taglib/uedit.jsp" %>
	</head>
	<body>
	 	<div style="position:absolute;top:10px;left:20px;right:20px;bottom:10px;">
			<form id="dataForm" method="post" class="layui-form">
			<#list table.columns as column>
				<#if (column.columnName?lower_case=="id"||column.columnName?lower_case=="createuser"||column.columnName?lower_case=="createdate"||column.columnName?lower_case=="updateuser"||column.columnName?lower_case=="updatedate"||column.columnName?lower_case=="delflag") ><#else>
				<div class="layui-form-item">
					<ks:formTag table="${table.sqlName}" column="${column.sqlName}" theme="layer"/>
				</div>
				</#if>
			</#list>
				<!-- <div class="layui-inline"> -->
				<input id="id" name="id" type="hidden"/>
			</form>
		</div>
		<script type="text/javascript">
			var loadi,url,index = parent.layer.getFrameIndex(window.name);
			var form, layer, layedit, laydate;
			$(document).ready(function() {
				layui.use(['form', 'layedit', 'laydate'], function() {
				    form = layui.form()
				    ,layer = layui.layer
				    ,layedit = layui.layedit
				    ,laydate = layui.laydate;
					var row = parent.$('#dataList').datagrid('getSelected');
					url = '${"${"}ctx${"}"}/${moduleC}${table.classNameFirstLower}/save';
					if(row != null) {//row对象不为空可认定是修改
						loadData(row.id);
						url = '${"${"}ctx${"}"}/${moduleC}${table.classNameFirstLower}/update';
					} else {//反之则为新建
						//新建初始化字段示例
					}
				});
			});
			
			function submitInfo() {
				$.ajax({
					type: "post",
					url: url,
					data: $('#dataForm').serialize(),
					dataType: 'json',
					timeout: layerLoadMaxTime,
					beforeSend: function(XMLHttpRequest) {
						loadi = parent.layer.load(2, {shade: layerLoadShade,time: layerLoadMaxTime});
					},
					success: function(data, textStatus) {
						parent.layer.close(loadi);
						if (data.success==TRUE) {
							parent.$.${table.classNameFirstLower}.reloadData();
							parent.layer.msg("操作成功！", {offset: layerMsgOffset,icon: 6,shift: 8,time: layerMsgTime});
							parent.layer.close(index);
	                    } else {
	                    	renderErrMsg(data.message);
	                    }
					},
					error: function(request, errType) {
						parent.layer.close(loadi);
						//"timeout", "error", "notmodified" 和 "parsererror"
						if(errType == 'timeout') {
							parent.layer.msg('请求超时', {offset: 'rb',icon: 6,shift: 8,time: layerMsgTime});
						}
						if(errType == 'error') {
							parent.layer.msg('系统错误，请联系管理员', {offset: 'rb',icon: 6,shift: 8,time: layerMsgTime});
						}
					}
				});
			}
			
			function loadData(id) {
				$.ajax({
					type: "post",
					url: '${"${"}ctx${"}"}/${moduleC}${table.classNameFirstLower}/data/'+id,
					data: {},
					dataType: 'json',
					timeout: layerLoadMaxTime,
					beforeSend: function(XMLHttpRequest) {
						loadi = parent.layer.load(2, {shade: layerLoadShade,time: layerLoadMaxTime});
					},
					success: function(data, textStatus) {
						parent.layer.close(loadi);
						renderFormData(data);
						form.render();
					},
					error: function(request, errType) {
						parent.layer.close(loadi);
						//"timeout", "error", "notmodified" 和 "parsererror"
						if(errType == 'timeout') {
							parent.layer.msg('请求超时', {offset: 'rb',icon: 6,shift: 8,time: layerMsgTime});
						}
						if(errType == 'error') {
							parent.layer.msg('系统错误，请联系管理员', {offset: 'rb',icon: 6,shift: 8,time: layerMsgTime});
						}
					}
				});
			}
		</script>
	</body>
</html>

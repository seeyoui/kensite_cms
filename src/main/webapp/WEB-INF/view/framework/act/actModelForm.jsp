<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/taglib/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>内容发布文章</title>
		<%@ include file="/WEB-INF/view/taglib/header.jsp" %>
		<%@ include file="/WEB-INF/view/taglib/easyui.jsp" %>
		<%@ include file="/WEB-INF/view/taglib/layer.jsp" %>
		<style type="text/css">
		</style>
	</head>
	<body>
	 	<div style="position:absolute;top:10px;left:20px;right:20px;bottom:10px;">
			<form id="dataForm" method="post">
				<div class="fitem">
					<label>流程分类</label>
	                <input id="category" name="category" class="easyui-combobox" data-options="required:true,valueField:'value',textField:'label',editable:false,panelHeight:'auto',url:'${ctx}/sys/dict/cache/json?category=act_type'">
				</div>
				<div class="fitem">
					<label>模型标识</label>
	                <input id="key" name="key" class="easyui-textbox" data-options="required:true"/>
				</div>
				<div class="fitem">
	                <label>模型名称</label>
	                <input id="name" name="name" class="easyui-textbox" data-options="required:true"/>
				</div>
				<div class="fitem">
					<label>描述</label>
	                <input id="description" name="description" class="easyui-textbox" data-options="required:true"/>
				</div>
				<input id="id" name="id" type="hidden"/>
			</form>
		</div>
		<script type="text/javascript">
			var loadi,url,index = parent.layer.getFrameIndex(window.name);
			$(document).ready(function(){
				var row = parent.$('#dataList').datagrid('getSelected');
				url = '${ctx}/actModel/save';
				if(row != null) {
					$('#dataForm').form('load', row);
					url = '${ctx}/actModel/update';
				}
			});
			
			function submitInfo() {
				$('#dataForm').form('submit',{
					url: url,
					onSubmit: function(param) {
						if($(this).form('validate')) {
							loadi = parent.layer.load(2, {shade: layerLoadShade,time: layerLoadMaxTime});
						}
						return $(this).form('validate');
					},
					success: function(data) {
						parent.layer.close(loadi);
						if (data==TRUE) {
							parent.reloadData();
							parent.layer.msg("操作成功！", {offset: layerMsgOffset,icon: 6,shift: 8,time: layerMsgTime});
							parent.layer.close(index);
						} else {
						}
					}
				});
			}
		</script>
	</body>
</html>

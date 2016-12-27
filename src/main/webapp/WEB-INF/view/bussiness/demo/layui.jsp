<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/taglib/common.jsp" %>
<!doctype html>
<html>
	<head>
		<title>演示</title>
		<%@ include file="/WEB-INF/view/taglib/header.jsp" %>
		<%@ include file="/WEB-INF/view/taglib/layui.jsp" %>
		<style type="text/css">
		</style>
	</head>
	<body>
	 	<div style="position:absolute;top:10px;left:20px;right:20px;bottom:10px;">
			<form id="dataForm" method="post" class="layui-form">
				<div class="layui-form-item">
					<ks:formTag table="BO_DEMO" column="USER_NAME" theme="layer"/>
				</div>
				<div class="layui-form-item">
					<ks:formTag table="BO_DEMO" column="USER_SEX" theme="layer"/>
				</div>
				<div class="layui-form-item">
					<div class="layui-inline">
						<ks:formTag table="BO_DEMO" column="USER_BIRTHDAY" theme="layer"/>
					</div>
					<div class="layui-inline">
						<ks:formTag table="BO_DEMO" column="USER_AGE" theme="layer"/>
					</div>
				</div>
				<div class="layui-form-item">
					<ks:formTag table="BO_DEMO" column="DEPARTMENT_ID" theme="layer"/>
				</div>
				<div class="layui-form-item">
					<ks:formTag table="BO_DEMO" column="MANAGER_ID" theme="layer"/>
				</div>
				<div class="layui-form-item">
					<ks:formTag table="BO_DEMO" column="USER_SUMMARY" theme="layer"/>
				</div>
				<input id="id" name="id" type="hidden"/>
				<input type="reset" name="reset" style="display: none;" />
			</form>
		</div>
		<script type="text/javascript">
			var loadi,url,index = parent.layer.getFrameIndex(window.name);
			$(document).ready(function(){
				layui.use(['form', 'layedit', 'laydate'], function(){
				    var form = layui.form()
				    ,layer = layui.layer
				    ,layedit = layui.layedit
				    ,laydate = layui.laydate;
				});
				var row = parent.$('#dataList').datagrid('getSelected');
				url = '${ctx}/bussiness/demo/save';
				if(row != null) {//row对象不为空可认定是修改
					loadData(row.id);
					url = '${ctx}/bussiness/demo/update';
				} else {//反之则为新建
					//新建初始化字段示例
				}
			});
		    
			function submitInfo(){
				$.ajax({
					type: "post",
					url: url,
					data: $('#dataForm').serialize(),
					dataType: 'json',
					timeout: layerLoadMaxTime,
					beforeSend: function(XMLHttpRequest){
						loadi = parent.layer.load(2, {shade: layerLoadShade,time: layerLoadMaxTime});
					},
					success: function(data, textStatus){
						parent.layer.close(loadi);
						if (data.success==TRUE){
							parent.$.demo.reloadData();
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
					url: '${ctx}/bussiness/demo/data/'+id,
					data: {},
					dataType: 'json',
					timeout: layerLoadMaxTime,
					beforeSend: function(XMLHttpRequest){
						loadi = parent.layer.load(2, {shade: layerLoadShade,time: layerLoadMaxTime});
					},
					success: function(data, textStatus){
						parent.layer.close(loadi);
						renderFormData(data);
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

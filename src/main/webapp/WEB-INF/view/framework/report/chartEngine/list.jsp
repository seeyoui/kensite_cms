<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/taglib/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>统计图表</title>
		<%@ include file="/WEB-INF/view/taglib/header.jsp" %>
		<%@ include file="/WEB-INF/view/taglib/easyui.jsp" %>
		<%@ include file="/WEB-INF/view/taglib/layer.jsp" %>
	</head>
	<body>
	 	<div style="position:absolute;top:0px;left:0px;right:0px;bottom:0px;">
			<div style="position:absolute;top:0px;left:0px;right:0px;bottom:0px;">
				<table id="dataList" title="统计图表列表<c:if test="${ksfn:getConfig('debug')=='T'}"><a href='javascript:void(0)' onclick='modDebug()'>debug</a></c:if>" 
						class="easyui-datagrid" style="width:100%;height:100%"
						url="${ctx}/ks/chartEngine/list/data"
						toolbar="#toolbar" pagination="true"
						rownumbers="true" fitColumns="true" singleSelect="false">
					<thead>
						<tr>
							<th data-options="field:'ck',checkbox:true"></th>
							<th data-options="field:'id'">UUID</th>
							<ks:listTag table="KS_CHART_ENGINE" column="REMARKS"/>
							<ks:listTag table="KS_CHART_ENGINE" column="DESCRIBES"/>
							<ks:listTag table="KS_CHART_ENGINE" column="TYPE"/>
							<ks:listTag table="KS_CHART_ENGINE" column="SET_OPTION"/>
							<ks:listTag table="KS_CHART_ENGINE" column="Z_SOURCE"/>
							<ks:listTag table="KS_CHART_ENGINE" column="Z_KEY"/>
							<ks:listTag table="KS_CHART_ENGINE" column="Z_VALUE"/>
							<ks:listTag table="KS_CHART_ENGINE" column="X_SOURCE"/>
							<ks:listTag table="KS_CHART_ENGINE" column="X_KEY"/>
							<ks:listTag table="KS_CHART_ENGINE" column="X_Z_KEY"/>
							<ks:listTag table="KS_CHART_ENGINE" column="X_VALUE"/>
							<ks:listTag table="KS_CHART_ENGINE" column="Y_SOURCE"/>
							<ks:listTag table="KS_CHART_ENGINE" column="Y_Z_KEY"/>
							<ks:listTag table="KS_CHART_ENGINE" column="Y_X_KEY"/>
							<ks:listTag table="KS_CHART_ENGINE" column="Y_VALUE"/>
							<ks:listTag table="KS_CHART_ENGINE" column="FUNC"/>
					    	<th field="config" width="100px" formatter="formatConfig">操作</th>
						</tr>
					</thead>
				</table>
				<div id="toolbar">
					<a href="javascript:void(0)" class="easyui-linkbutton info" iconCls="icon-add" plain="true" onclick="$.chartEngine.newInfo()">新建</a>
					<a href="javascript:void(0)" class="easyui-linkbutton warning" iconCls="icon-edit" plain="true" onclick="$.chartEngine.editInfo()">修改</a>
					<a href="javascript:void(0)" class="easyui-linkbutton error" iconCls="icon-remove" plain="true" onclick="$.chartEngine.destroyInfo()">删除</a>
					<ks:queryTag table="KS_CHART_ENGINE" column="REMARKS"/>
					<ks:queryTag table="KS_CHART_ENGINE" column="DESCRIBES"/>
					<ks:queryTag table="KS_CHART_ENGINE" column="TYPE"/>
					<ks:queryTag table="KS_CHART_ENGINE" column="SET_OPTION"/>
					<ks:queryTag table="KS_CHART_ENGINE" column="Z_SOURCE"/>
					<ks:queryTag table="KS_CHART_ENGINE" column="Z_KEY"/>
					<ks:queryTag table="KS_CHART_ENGINE" column="Z_VALUE"/>
					<ks:queryTag table="KS_CHART_ENGINE" column="X_SOURCE"/>
					<ks:queryTag table="KS_CHART_ENGINE" column="X_KEY"/>
					<ks:queryTag table="KS_CHART_ENGINE" column="X_Z_KEY"/>
					<ks:queryTag table="KS_CHART_ENGINE" column="X_VALUE"/>
					<ks:queryTag table="KS_CHART_ENGINE" column="Y_SOURCE"/>
					<ks:queryTag table="KS_CHART_ENGINE" column="Y_Z_KEY"/>
					<ks:queryTag table="KS_CHART_ENGINE" column="Y_X_KEY"/>
					<ks:queryTag table="KS_CHART_ENGINE" column="Y_VALUE"/>
					<ks:queryTag table="KS_CHART_ENGINE" column="FUNC"/>
					<a href="javascript:void(0)" class="easyui-linkbutton success" iconCls="icon-search" plain="true" onclick="$.chartEngine.selectData()">查询</a>
				</div>
			</div>
		</div>
		<script type="text/javascript">
			var tableName = "KS_CHART_ENGINE";
			$(document).ready(function(){
			});
			var url, loadi;
			var iframeWin = null, iframeBody=null;
			$.chartEngine = {
		   		selectData : function () {
					$('#dataList').datagrid('load',{
						<ks:queryJsTag table="KS_CHART_ENGINE" column="REMARKS"/>
						<ks:queryJsTag table="KS_CHART_ENGINE" column="DESCRIBES"/>
						<ks:queryJsTag table="KS_CHART_ENGINE" column="TYPE"/>
						<ks:queryJsTag table="KS_CHART_ENGINE" column="SET_OPTION"/>
						<ks:queryJsTag table="KS_CHART_ENGINE" column="Z_SOURCE"/>
						<ks:queryJsTag table="KS_CHART_ENGINE" column="Z_KEY"/>
						<ks:queryJsTag table="KS_CHART_ENGINE" column="Z_VALUE"/>
						<ks:queryJsTag table="KS_CHART_ENGINE" column="X_SOURCE"/>
						<ks:queryJsTag table="KS_CHART_ENGINE" column="X_KEY"/>
						<ks:queryJsTag table="KS_CHART_ENGINE" column="X_Z_KEY"/>
						<ks:queryJsTag table="KS_CHART_ENGINE" column="X_VALUE"/>
						<ks:queryJsTag table="KS_CHART_ENGINE" column="Y_SOURCE"/>
						<ks:queryJsTag table="KS_CHART_ENGINE" column="Y_Z_KEY"/>
						<ks:queryJsTag table="KS_CHART_ENGINE" column="Y_X_KEY"/>
						<ks:queryJsTag table="KS_CHART_ENGINE" column="Y_VALUE"/>
						<ks:queryJsTag table="KS_CHART_ENGINE" column="FUNC"/>
					});
				},
				reloadData : function () {
					$.chartEngine.selectData();
				},
				newInfo : function (){
					$('#dataList').datagrid('clearSelections');
					$.chartEngine.layerOpen(url);
				},
				editInfo : function (){
					var row = $('#dataList').datagrid('getSelected');
					if (row){
						$.chartEngine.layerOpen(url);
					} else {
						layer.msg("请先选择要修改的记录！", {offset: 'rb',icon: 5,shift: 8,time: layerMsgTime});
					}
				},
				exportExcel : function () {
					window.open("${ctx}/ks/chartEngine/export");
				},
				layerOpen : function (url) {
					url = '${ctx}/ks/chartEngine/form';
					layer.open({
						type: 2,
						title: '统计图表基本信息',
						area: ['310px', '250px'],
						fix: false, //不固定
						maxmin: false,
						content: url,
						btn: ['保存', '取消'],
						success: function(layero, index){
							iframeBody = layer.getChildFrame('body', index);
							iframeWin = window[layero.find('iframe')[0]['name']];
						},
						yes: function(index, layero) {
							if(iframeWin != null) {
								iframeWin.submitInfo();
							}
						},
						cancel: function(index){
							layer.close(index);
						}
					});
				},
				destroyInfo : function (){
					//var row = $('#dataList').datagrid('getSelected');
					var rows = $('#dataList').datagrid('getSelections');
					if (rows && rows.length>0){
						layer.confirm('是否确认删除？', {
							btn: ['确定','取消'] //按钮
						}, function(){
							var id = "";
							//id = row.id;
							for(var i=0; i<rows.length; i++) {
								id += rows[i].id+",";
							}
							$.ajax({
								type: "post",
								url: '${ctx}/ks/chartEngine/delete',
								data: {id:id},
								dataType: 'json',
								timeout: layerLoadMaxTime,
								beforeSend: function(XMLHttpRequest){
									loadi = layer.load(2, {shade: layerLoadShade,time: layerLoadMaxTime});
								},
								success: function(data, textStatus){
									layer.close(loadi);
									if (data.success==TRUE){
										layer.msg("操作成功！", {offset: 'rb',icon: 6,shift: 8,time: layerMsgTime});
										$.chartEngine.reloadData();
									} else {
										layer.msg("操作失败！", {offset: 'rb',icon: 5,shift: 8,time: layerMsgTime});
									}
								},
								error: function(request, errType) {
									layer.close(loadi);
									//"timeout", "error", "notmodified" 和 "parsererror"
									if(errType == 'timeout') {
										layer.msg('请求超时', {offset: 'rb',icon: 6,shift: 8,time: layerMsgTime});
									}
									if(errType == 'error') {
										layer.msg('系统错误，请联系管理员', {offset: 'rb',icon: 6,shift: 8,time: layerMsgTime});
									}
								}
							});
						}, function(){
						});
					} else {
						layer.msg("请先选择要删除的记录！", {offset: 'rb',icon: 5,shift: 8,time: layerMsgTime});
					}
				}
			}
		    
		    function formatConfig(val,row) {
				var returnStr = "&nbsp;&nbsp;<a href='javascript:config(\""+row.id+"\",\""+row.type+"\")'>配置</a>";
				return returnStr;
		    }
			
			function config(id, type) {
				if(type == null || type == '') {
					return;
				}
				window.open('${ctx}/ks/chartEngine/form/'+type+'/'+id);
			}
		</script>
	</body>
</html>

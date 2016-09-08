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
						url="${ctx}/ks/chartConfig/list/data"
						toolbar="#toolbar" pagination="true"
						rownumbers="true" fitColumns="false" singleSelect="false">
					<thead>
						<tr>
							<th data-options="field:'id',hidden:true">ID</th>
							<th data-options="field:'ck',checkbox:true"></th>
							<ks:listTag table="KS_CHART_CONFIG" column="REMARKS"/>
							<ks:listTag table="KS_CHART_CONFIG" column="TITLE"/>
							<ks:listTag table="KS_CHART_CONFIG" column="CODE_NUM"/>
							<ks:listTag table="KS_CHART_CONFIG" column="TYPE"/>
							<ks:listTag table="KS_CHART_CONFIG" column="DESCRIBE"/>
							<ks:listTag table="KS_CHART_CONFIG" column="CHART_OPTION"/>
							<ks:listTag table="KS_CHART_CONFIG" column="SERIES_OPTION"/>
							<ks:listTag table="KS_CHART_CONFIG" column="SQLX"/>
							<ks:listTag table="KS_CHART_CONFIG" column="SQLY"/>
							<ks:listTag table="KS_CHART_CONFIG" column="SQLZ"/>
					    	<th field="config" width="100px" formatter="formatConfig">操作</th>
						</tr>
					</thead>
				</table>
				<div id="toolbar">
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="$.chartConfig.newInfo()">新建</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="$.chartConfig.editInfo()">修改</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="$.chartConfig.destroyInfo()">删除</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-page_excel" plain="true" onclick="$.chartConfig.exportExcel()">导出</a>
					<ks:queryTag table="KS_CHART_CONFIG" column="REMARKS"/>
					<ks:queryTag table="KS_CHART_CONFIG" column="TITLE"/>
					<ks:queryTag table="KS_CHART_CONFIG" column="CODE_NUM"/>
					<ks:queryTag table="KS_CHART_CONFIG" column="TYPE"/>
					<ks:queryTag table="KS_CHART_CONFIG" column="DESCRIBE"/>
					<ks:queryTag table="KS_CHART_CONFIG" column="CHART_OPTION"/>
					<ks:queryTag table="KS_CHART_CONFIG" column="SERIES_OPTION"/>
					<ks:queryTag table="KS_CHART_CONFIG" column="SQLX"/>
					<ks:queryTag table="KS_CHART_CONFIG" column="SQLY"/>
					<ks:queryTag table="KS_CHART_CONFIG" column="SQLZ"/>
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="$.chartConfig.selectData()">查询</a>
				</div>
			</div>
		</div>
		<script type="text/javascript">
			var tableName = "KS_CHART_CONFIG";
			$(document).ready(function(){
			});
			var url, loadi;
			var iframeWin = null, iframeBody=null;
			$.chartConfig = {
		   		selectData : function () {
					$('#dataList').datagrid('load',{
						<ks:queryJsTag table="KS_CHART_CONFIG" column="REMARKS"/>
						<ks:queryJsTag table="KS_CHART_CONFIG" column="TITLE"/>
						<ks:queryJsTag table="KS_CHART_CONFIG" column="CODE_NUM"/>
						<ks:queryJsTag table="KS_CHART_CONFIG" column="TYPE"/>
						<ks:queryJsTag table="KS_CHART_CONFIG" column="DESCRIBE"/>
						<ks:queryJsTag table="KS_CHART_CONFIG" column="CHART_OPTION"/>
						<ks:queryJsTag table="KS_CHART_CONFIG" column="SERIES_OPTION"/>
						<ks:queryJsTag table="KS_CHART_CONFIG" column="SQLX"/>
						<ks:queryJsTag table="KS_CHART_CONFIG" column="SQLY"/>
						<ks:queryJsTag table="KS_CHART_CONFIG" column="SQLZ"/>
					});
				},
				reloadData : function () {
					$.chartConfig.selectData();
				},
				newInfo : function (){
					$('#dataList').datagrid('clearSelections');
					$.chartConfig.layerOpen(url);
				},
				editInfo : function (){
					var row = $('#dataList').datagrid('getSelected');
					if (row){
						$.chartConfig.layerOpen(url);
					} else {
						layer.msg("请先选择要修改的记录！", {offset: 'rb',icon: 5,shift: 8,time: layerMsgTime});
					}
				},
				exportExcel : function () {
					window.open("${ctx}/ks/chartConfig/export");
				},
				layerOpen : function (url) {
					url = '${ctx}/ks/chartConfig/form';
					layer.open({
						type: 2,
						title: '统计图表基本信息',
						area: ['310px', '350px'],
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
					if (rows){
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
								url: '${ctx}/ks/chartConfig/delete',
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
										$.chartConfig.reloadData();
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
				var returnStr = "&nbsp;&nbsp;<a href='javascript:config(\""+row.id+"\")'>配置</a>";
				return returnStr;
		    }
			
			function config(id) {
				window.open('${ctx}/ks/chartConfig/config?id='+id);
			}
		</script>
	</body>
</html>

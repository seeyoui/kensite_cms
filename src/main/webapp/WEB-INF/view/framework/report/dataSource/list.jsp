<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/taglib/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>数据源</title>
		<%@ include file="/WEB-INF/view/taglib/header.jsp" %>
		<%@ include file="/WEB-INF/view/taglib/easyui.jsp" %>
		<%@ include file="/WEB-INF/view/taglib/layer.jsp" %>
	</head>
	<body>
	 	<div style="position:absolute;top:0px;left:0px;right:0px;bottom:0px;">
			<div style="position:absolute;top:0px;left:0px;right:0px;bottom:0px;">
				<table id="dataList" title="数据源列表<c:if test="${ksfn:getConfig('debug')=='T'}"><a href='javascript:void(0)' onclick='modDebug()'>debug</a></c:if>" 
						class="easyui-datagrid" style="width:100%;height:100%"
						url="${ctx}/ks/dataSource/list/data"
						toolbar="#toolbar" pagination="true"
						rownumbers="true" fitColumns="true" singleSelect="false">
					<thead>
						<tr>
							<th data-options="field:'id',hidden:true">ID</th>
							<th data-options="field:'ck',checkbox:true"></th>
							<ks:listTag table="KS_DATA_SOURCE" column="REMARKS"/>
							<ks:listTag table="KS_DATA_SOURCE" column="NAME"/>
							<ks:listTag table="KS_DATA_SOURCE" column="CODE_NUM"/>
							<ks:listTag table="KS_DATA_SOURCE" column="TYPE"/>
							<ks:listTag table="KS_DATA_SOURCE" column="DESCRIBE"/>
							<ks:listTag table="KS_DATA_SOURCE" column="CONTENT"/>
							<ks:listTag table="KS_DATA_SOURCE" column="VIEW_SQL"/>
						</tr>
					</thead>
				</table>
				<div id="toolbar">
					<!-- <a href="javascript:void(0)" class="easyui-linkbutton info" iconCls="icon-add" plain="true" onclick="$.dataSource.newInfo()">新建</a> -->
					<a href="javascript:void(0)" class="easyui-linkbutton info" iconCls="icon-add" plain="true" onclick="$.dataSource.newInfo('sql')">SQL数据源</a>
					<a href="javascript:void(0)" class="easyui-linkbutton info" iconCls="icon-add" plain="true" onclick="$.dataSource.newInfo('st')">静态数据源</a>
					<a href="javascript:void(0)" class="easyui-linkbutton warning" iconCls="icon-edit" plain="true" onclick="$.dataSource.editInfo()">修改</a>
					<a href="javascript:void(0)" class="easyui-linkbutton error" iconCls="icon-remove" plain="true" onclick="$.dataSource.destroyInfo()">删除</a>
					<ks:queryTag table="KS_DATA_SOURCE" column="REMARKS"/>
					<ks:queryTag table="KS_DATA_SOURCE" column="NAME"/>
					<ks:queryTag table="KS_DATA_SOURCE" column="CODE_NUM"/>
					<ks:queryTag table="KS_DATA_SOURCE" column="TYPE"/>
					<ks:queryTag table="KS_DATA_SOURCE" column="DESCRIBE"/>
					<ks:queryTag table="KS_DATA_SOURCE" column="CONTENT"/>
					<ks:queryTag table="KS_DATA_SOURCE" column="VIEW_SQL"/>
					<a href="javascript:void(0)" class="easyui-linkbutton success" iconCls="icon-search" plain="true" onclick="$.dataSource.selectData()">查询</a>
				</div>
			</div>
		</div>
		<script type="text/javascript">
			var tableName = "KS_DATA_SOURCE";
			$(document).ready(function(){
			});
			var url, loadi;
			var iframeWin = null, iframeBody=null;
			$.dataSource = {
		   		selectData : function () {
					$('#dataList').datagrid('load',{
						<ks:queryJsTag table="KS_DATA_SOURCE" column="REMARKS"/>
						<ks:queryJsTag table="KS_DATA_SOURCE" column="NAME"/>
						<ks:queryJsTag table="KS_DATA_SOURCE" column="CODE_NUM"/>
						<ks:queryJsTag table="KS_DATA_SOURCE" column="TYPE"/>
						<ks:queryJsTag table="KS_DATA_SOURCE" column="DESCRIBE"/>
						<ks:queryJsTag table="KS_DATA_SOURCE" column="CONTENT"/>
						<ks:queryJsTag table="KS_DATA_SOURCE" column="VIEW_SQL"/>
					});
				},
				reloadData : function () {
					$.dataSource.selectData();
				},
				newInfo : function (type){
					if(type == null || type == '') {
						type = 'form';
					}
					var url = '${ctx}/ks/dataSource/'+type;
					$('#dataList').datagrid('clearSelections');
					$.dataSource.layerOpen(url);
				},
				editInfo : function (){
					var row = $('#dataList').datagrid('getSelected');
					if (row){
						var type = 'form';
						if(row.type == null || row.type == '') {
						} else {
							type = row.type;
						}
						var url = '${ctx}/ks/dataSource/'+type;
						$.dataSource.layerOpen(url);
					} else {
						layer.msg("请先选择要修改的记录！", {offset: 'rb',icon: 5,shift: 8,time: layerMsgTime});
					}
				},
				exportExcel : function () {
					window.open("${ctx}/ks/dataSource/export");
				},
				layerOpen : function (url) {
					if(url == null || url == '') {
						url = '${ctx}/ks/dataSource/form';
					}
					layer.open({
						type: 2,
						title: '数据源基本信息',
						area: ['680px', '450px'],
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
								url: '${ctx}/ks/dataSource/delete',
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
										$.dataSource.reloadData();
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
		</script>
	</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/taglib/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>内容发布收藏</title>
		<%@ include file="/WEB-INF/view/taglib/header.jsp" %>
		<%@ include file="/WEB-INF/view/taglib/easyui.jsp" %>
		<%@ include file="/WEB-INF/view/taglib/layer.jsp" %>
	</head>
	<body>
	 	<div style="position:absolute;top:0px;left:0px;right:0px;bottom:0px;">
			<div style="position:absolute;top:0px;left:0px;right:0px;bottom:0px;">
				<table id="dataList" title="内容发布收藏列表<c:if test="${ksfn:getConfig('debug')=='T'}"><a href='javascript:void(0)' onclick='modDebug()'>debug</a></c:if>" 
						class="easyui-datagrid" style="width:100%;height:100%"
						url="${ctx}/cms/collections/list/data"
						toolbar="#toolbar" pagination="true"
						rownumbers="true" fitColumns="true" singleSelect="false">
					<thead>
						<tr>
							<th data-options="field:'id',hidden:true">ID</th>
							<th data-options="field:'ck',checkbox:true"></th>
							<ks:listTag table="CMS_COLLECTIONS" column="REMARKS"/>
							<ks:listTag table="CMS_COLLECTIONS" column="SITE_ID"/>
							<ks:listTag table="CMS_COLLECTIONS" column="CATEGORY_ID"/>
							<ks:listTag table="CMS_COLLECTIONS" column="BASIC_ID"/>
							<ks:listTag table="CMS_COLLECTIONS" column="TYPE"/>
							<ks:listTag table="CMS_COLLECTIONS" column="CATEGORY"/>
						</tr>
					</thead>
				</table>
				<div id="toolbar">
					<a href="javascript:void(0)" class="easyui-linkbutton info" iconCls="icon-add" plain="true" onclick="$.collections.newInfo()">新建</a>
					<a href="javascript:void(0)" class="easyui-linkbutton warning" iconCls="icon-edit" plain="true" onclick="$.collections.editInfo()">修改</a>
					<a href="javascript:void(0)" class="easyui-linkbutton error" iconCls="icon-remove" plain="true" onclick="$.collections.destroyInfo()">删除</a>
					<a href="javascript:void(0)" class="easyui-linkbutton primary" iconCls="icon-export" plain="true" onclick="$.collections.exportExcel()">导出</a>
					<ks:queryTag table="CMS_COLLECTIONS" column="REMARKS"/>
					<ks:queryTag table="CMS_COLLECTIONS" column="SITE_ID"/>
					<ks:queryTag table="CMS_COLLECTIONS" column="CATEGORY_ID"/>
					<ks:queryTag table="CMS_COLLECTIONS" column="BASIC_ID"/>
					<ks:queryTag table="CMS_COLLECTIONS" column="TYPE"/>
					<ks:queryTag table="CMS_COLLECTIONS" column="CATEGORY"/>
					<a href="javascript:void(0)" class="easyui-linkbutton success" iconCls="icon-search" plain="true" onclick="$.collections.selectData()">查询</a>
				</div>
			</div>
		</div>
		<script type="text/javascript">
			var tableName = "CMS_COLLECTIONS";
			$(document).ready(function(){
			});
			var url, loadi;
			var iframeWin = null, iframeBody=null;
			$.collections = {
		   		selectData : function () {
					$('#dataList').datagrid('load',{
						<ks:queryJsTag table="CMS_COLLECTIONS" column="REMARKS"/>
						<ks:queryJsTag table="CMS_COLLECTIONS" column="SITE_ID"/>
						<ks:queryJsTag table="CMS_COLLECTIONS" column="CATEGORY_ID"/>
						<ks:queryJsTag table="CMS_COLLECTIONS" column="BASIC_ID"/>
						<ks:queryJsTag table="CMS_COLLECTIONS" column="TYPE"/>
						<ks:queryJsTag table="CMS_COLLECTIONS" column="CATEGORY"/>
					});
				},
				reloadData : function () {
					$.collections.selectData();
				},
				newInfo : function (){
					$('#dataList').datagrid('clearSelections');
					$.collections.layerOpen(url);
				},
				editInfo : function (){
					var row = $('#dataList').datagrid('getSelected');
					if (row){
						$.collections.layerOpen(url);
					} else {
						layer.msg("请先选择要修改的记录！", {offset: 'rb',icon: 5,shift: 8,time: layerMsgTime});
					}
				},
				exportExcel : function () {
					window.open("${ctx}/cms/collections/export");
				},
				layerOpen : function (url) {
					url = '${ctx}/cms/collections/form';
					layer.open({
						type: 2,
						title: '内容发布收藏基本信息',
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
								url: '${ctx}/cms/collections/delete',
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
										$.collections.reloadData();
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

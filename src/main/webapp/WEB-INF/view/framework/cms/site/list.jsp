<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/taglib/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>内容发布站点</title>
		<%@ include file="/WEB-INF/view/taglib/header.jsp" %>
		<%@ include file="/WEB-INF/view/taglib/easyui.jsp" %>
		<%@ include file="/WEB-INF/view/taglib/layer.jsp" %>
	</head>
	<body>
	 	<div style="position:absolute;top:0px;left:0px;right:0px;bottom:0px;">
			<div style="position:absolute;top:0px;left:0px;right:0px;bottom:0px;">
				<table id="dataList" title="内容发布站点列表<c:if test="${ksfn:getConfig('debug')=='T'}"><a href='javascript:void(0)' onclick='modDebug()'>debug</a></c:if>" 
						class="easyui-datagrid" style="width:100%;height:100%"
						url="${ctx}/cms/site/list/data"
						toolbar="#toolbar" pagination="true"
						rownumbers="true" fitColumns="true" singleSelect="false">
					<thead>
						<tr>
							<th data-options="field:'id',hidden:true">ID</th>
							<th data-options="field:'ck',checkbox:true"></th>
							<ks:listTag table="CMS_SITE" column="REMARKS"/>
							<ks:listTag table="CMS_SITE" column="NAME"/>
							<ks:listTag table="CMS_SITE" column="TITLE"/>
							<ks:listTag table="CMS_SITE" column="LOGO"/>
							<ks:listTag table="CMS_SITE" column="DOMAIN"/>
							<ks:listTag table="CMS_SITE" column="DESCRIPTION"/>
							<ks:listTag table="CMS_SITE" column="KEYWORDS"/>
							<ks:listTag table="CMS_SITE" column="THEME"/>
							<ks:listTag table="CMS_SITE" column="COPYRIGHT"/>
							<ks:listTag table="CMS_SITE" column="CUSTOM_VIEW"/>
					    	<th field="viewHtml" width="100px" formatter="formatViewHtml">演示</th>
						</tr>
					</thead>
				</table>
				<div id="toolbar">
					<a href="javascript:void(0)" class="easyui-linkbutton info" iconCls="icon-add" plain="true" onclick="$.site.newInfo()">新建</a>
					<a href="javascript:void(0)" class="easyui-linkbutton warning" iconCls="icon-edit" plain="true" onclick="$.site.editInfo()">修改</a>
					<a href="javascript:void(0)" class="easyui-linkbutton error" iconCls="icon-remove" plain="true" onclick="$.site.destroyInfo()">删除</a>
					<a href="javascript:void(0)" class="easyui-linkbutton primary" iconCls="icon-export" plain="true" onclick="$.site.exportExcel()">导出</a>
					<ks:queryTag table="CMS_SITE" column="REMARKS"/>
					<ks:queryTag table="CMS_SITE" column="NAME"/>
					<ks:queryTag table="CMS_SITE" column="TITLE"/>
					<ks:queryTag table="CMS_SITE" column="LOGO"/>
					<ks:queryTag table="CMS_SITE" column="DOMAIN"/>
					<ks:queryTag table="CMS_SITE" column="DESCRIPTION"/>
					<ks:queryTag table="CMS_SITE" column="KEYWORDS"/>
					<ks:queryTag table="CMS_SITE" column="THEME"/>
					<ks:queryTag table="CMS_SITE" column="COPYRIGHT"/>
					<ks:queryTag table="CMS_SITE" column="CUSTOM_VIEW"/>
					<a href="javascript:void(0)" class="easyui-linkbutton success" iconCls="icon-search" plain="true" onclick="$.site.selectData()">查询</a>
				</div>
			</div>
		</div>
		<script type="text/javascript">
			var tableName = "CMS_SITE";
			$(document).ready(function(){
			});
			var url, loadi;
			var iframeWin = null, iframeBody=null;
			$.site = {
		   		selectData : function () {
					$('#dataList').datagrid('load',{
						<ks:queryJsTag table="CMS_SITE" column="REMARKS"/>
						<ks:queryJsTag table="CMS_SITE" column="NAME"/>
						<ks:queryJsTag table="CMS_SITE" column="TITLE"/>
						<ks:queryJsTag table="CMS_SITE" column="LOGO"/>
						<ks:queryJsTag table="CMS_SITE" column="DOMAIN"/>
						<ks:queryJsTag table="CMS_SITE" column="DESCRIPTION"/>
						<ks:queryJsTag table="CMS_SITE" column="KEYWORDS"/>
						<ks:queryJsTag table="CMS_SITE" column="THEME"/>
						<ks:queryJsTag table="CMS_SITE" column="COPYRIGHT"/>
						<ks:queryJsTag table="CMS_SITE" column="CUSTOM_VIEW"/>
					});
				},
				reloadData : function () {
					$.site.selectData();
				},
				newInfo : function (){
					$('#dataList').datagrid('clearSelections');
					$.site.layerOpen(url);
				},
				editInfo : function (){
					var row = $('#dataList').datagrid('getSelected');
					if (row){
						$.site.layerOpen(url);
					} else {
						layer.msg("请先选择要修改的记录！", {offset: 'rb',icon: 5,shift: 8,time: layerMsgTime});
					}
				},
				exportExcel : function () {
					window.open("${ctx}/cms/site/export");
				},
				layerOpen : function (url) {
					url = '${ctx}/cms/site/form';
					layer.open({
						type: 2,
						title: '内容发布站点基本信息',
						area: ['570px', '470px'],
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
								url: '${ctx}/cms/site/delete',
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
										$.site.reloadData();
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
		    
		    function formatViewHtml(val, row) {
				return "<a href='${ctx}/portal/"+row.domain+"/index.jsp' target='_blank'>查看</a>";
		    }
		</script>
	</body>
</html>

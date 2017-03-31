<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/taglib/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>内容发布留言板</title>
		<%@ include file="/WEB-INF/view/taglib/header.jsp" %>
		<%@ include file="/WEB-INF/view/taglib/easyui.jsp" %>
		<%@ include file="/WEB-INF/view/taglib/layer.jsp" %>
	</head>
	<body>
	 	<div style="position:absolute;top:0px;left:0px;right:0px;bottom:0px;">
			<div style="position:absolute;top:0px;bottom:0px;width:200px;">
	        	<table id="siteDataList" title="内容发布站点列表<c:if test="${ksfn:getConfig('debug')=='T'}"><a href='javascript:void(0)' onclick='modDebug()'>debug</a></c:if>" 
						class="easyui-datagrid" style="width:100%;height:100%"
						url="${ctx}/cms/site/list/all" rownumbers="true" 
						fitColumns="false" singleSelect="true">
					<thead>
						<tr>
							<th data-options="field:'id',hidden:true">ID</th>
							<th data-options="field:'name',width:150">站点名称</th>
						</tr>
					</thead>
				</table>
	        </div>
			<div style="position:absolute;top:0px;left:200px;right:0px;bottom:0px;">
				<table id="dataList" title="内容发布留言板列表<c:if test="${ksfn:getConfig('debug')=='T'}"><a href='javascript:void(0)' onclick='modDebug()'>debug</a></c:if>" 
						class="easyui-datagrid" style="width:100%;height:100%"
						url="${ctx}/cms/guestbook/list/data"
						toolbar="#toolbar" pagination="true"
						rownumbers="true" fitColumns="true" singleSelect="false">
					<thead>
						<tr>
							<th data-options="field:'id',hidden:true">ID</th>
							<th data-options="field:'ck',checkbox:true"></th>
							<ks:listTag table="CMS_GUESTBOOK" column="REMARKS"/>
							<ks:listTag table="CMS_GUESTBOOK" column="TYPE"/>
							<ks:listTag table="CMS_GUESTBOOK" column="CONTENT"/>
							<ks:listTag table="CMS_GUESTBOOK" column="NAME"/>
							<ks:listTag table="CMS_GUESTBOOK" column="EMAIL"/>
							<ks:listTag table="CMS_GUESTBOOK" column="PHONE"/>
							<ks:listTag table="CMS_GUESTBOOK" column="WORKUNIT"/>
							<ks:listTag table="CMS_GUESTBOOK" column="IP"/>
							<ks:listTag table="CMS_GUESTBOOK" column="REUSERID"/>
							<ks:listTag table="CMS_GUESTBOOK" column="REDATE"/>
							<ks:listTag table="CMS_GUESTBOOK" column="RECONTENT"/>
							<ks:listTag table="CMS_GUESTBOOK" column="SITE_ID"/>
						</tr>
					</thead>
				</table>
				<div id="toolbar">
					<!-- <a href="javascript:void(0)" class="easyui-linkbutton info" iconCls="icon-add" plain="true" onclick="$.guestbook.newInfo()">新建</a> -->
					<a href="javascript:void(0)" class="easyui-linkbutton primary" iconCls="icon-edit" plain="true" onclick="$.guestbook.editInfo()">回复</a>
					<a href="javascript:void(0)" class="easyui-linkbutton error" iconCls="icon-remove" plain="true" onclick="$.guestbook.destroyInfo()">删除</a>
					<!-- <a href="javascript:void(0)" class="easyui-linkbutton primary" iconCls="icon-export" plain="true" onclick="$.guestbook.exportExcel()">导出</a> -->
					<ks:queryTag table="CMS_GUESTBOOK" column="REMARKS"/>
					<ks:queryTag table="CMS_GUESTBOOK" column="TYPE"/>
					<ks:queryTag table="CMS_GUESTBOOK" column="CONTENT"/>
					<ks:queryTag table="CMS_GUESTBOOK" column="NAME"/>
					<ks:queryTag table="CMS_GUESTBOOK" column="EMAIL"/>
					<ks:queryTag table="CMS_GUESTBOOK" column="PHONE"/>
					<ks:queryTag table="CMS_GUESTBOOK" column="WORKUNIT"/>
					<ks:queryTag table="CMS_GUESTBOOK" column="IP"/>
					<ks:queryTag table="CMS_GUESTBOOK" column="REUSERID"/>
					<ks:queryTag table="CMS_GUESTBOOK" column="REDATE"/>
					<ks:queryTag table="CMS_GUESTBOOK" column="RECONTENT"/>
					<input id="sel_siteId" name="sel_siteId" type="hidden" value='${ksfn:getConst("ROOT_ID_32")}'/>
					<a href="javascript:void(0)" class="easyui-linkbutton success" iconCls="icon-search" plain="true" onclick="$.guestbook.selectData()">查询</a>
				</div>
			</div>
		</div>
		<script type="text/javascript">
			var tableName = "CMS_GUESTBOOK";
			$(document).ready(function(){
				$('#siteDataList').datagrid({
		    		onDblClickRow: function(index, row){
		    			$('#sel_siteId').val(row.id);
		    			$.guestbook.reloadData();
		    		}
		    	});
			});
			var url, loadi;
			var iframeWin = null, iframeBody=null;
			$.guestbook = {
		   		selectData : function () {
					$('#dataList').datagrid('load',{
						<ks:queryJsTag table="CMS_GUESTBOOK" column="REMARKS"/>
						<ks:queryJsTag table="CMS_GUESTBOOK" column="TYPE"/>
						<ks:queryJsTag table="CMS_GUESTBOOK" column="CONTENT"/>
						<ks:queryJsTag table="CMS_GUESTBOOK" column="NAME"/>
						<ks:queryJsTag table="CMS_GUESTBOOK" column="EMAIL"/>
						<ks:queryJsTag table="CMS_GUESTBOOK" column="PHONE"/>
						<ks:queryJsTag table="CMS_GUESTBOOK" column="WORKUNIT"/>
						<ks:queryJsTag table="CMS_GUESTBOOK" column="IP"/>
						<ks:queryJsTag table="CMS_GUESTBOOK" column="REUSERID"/>
						<ks:queryJsTag table="CMS_GUESTBOOK" column="REDATE"/>
						<ks:queryJsTag table="CMS_GUESTBOOK" column="RECONTENT"/>
						siteId: $('#sel_siteId').val()
					});
				},
				reloadData : function () {
					$.guestbook.selectData();
				},
				newInfo : function (){
					$('#dataList').datagrid('clearSelections');
					$.guestbook.layerOpen(url);
				},
				editInfo : function (){
					var row = $('#dataList').datagrid('getSelected');
					if (row){
						$.guestbook.layerOpen(url);
					} else {
						layer.msg("请先选择要修改的记录！", {offset: 'rb',icon: 5,shift: 8,time: layerMsgTime});
					}
				},
				exportExcel : function () {
					window.open("${ctx}/cms/guestbook/export");
				},
				layerOpen : function (url) {
					url = '${ctx}/cms/guestbook/form';
					layer.open({
						type: 2,
						title: '内容发布留言板基本信息',
						area: ['550px', '480px'],
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
								url: '${ctx}/cms/guestbook/delete',
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
										$.guestbook.reloadData();
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

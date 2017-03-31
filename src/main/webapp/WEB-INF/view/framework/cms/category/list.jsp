<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/taglib/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>内容发布栏目</title>
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
							<th data-options="field:'name',width:165">站点名称</th>
						</tr>
					</thead>
				</table>
	        </div>
			<div style="position:absolute;top:0px;bottom:0px;left:200px;width:200px;">
	        	<table id="dataTreeList" title="栏目树列表<c:if test="${ksfn:getConfig('debug')=='T'}"><a href='javascript:void(0)' onclick='modDebug()'>debug</a></c:if>" 
						class="easyui-treegrid" style="width:100%;height:100%"
						data-options="idField: 'id',treeField: 'name',parentField: 'parentId'"
						rownumbers="true" fitColumns="true" singleSelect="true">
					<thead>
						<tr>
							<th data-options="field:'id',hidden:true">ID</th>
							<th data-options="field:'name',width:165">栏目名称</th>
						</tr>
					</thead>
				</table>
	        </div>
			<div style="position:absolute;top:0px;left:400px;right:0px;bottom:0px;">
				<table id="dataList" title="内容发布栏目列表<c:if test="${ksfn:getConfig('debug')=='T'}"><a href='javascript:void(0)' onclick='modDebug()'>debug</a></c:if>" 
						class="easyui-datagrid" style="width:100%;height:100%"
						toolbar="#toolbar" pagination="true"
						rownumbers="true" fitColumns="true" singleSelect="false">
					<thead>
						<tr>
							<th data-options="field:'id',hidden:true">ID</th>
							<th data-options="field:'ck',checkbox:true"></th>
							<ks:listTag table="CMS_CATEGORY" column="REMARKS"/>
							<ks:listTag table="CMS_CATEGORY" column="SITE_ID"/>
							<ks:listTag table="CMS_CATEGORY" column="MODULE"/>
							<ks:listTag table="CMS_CATEGORY" column="NAME"/>
							<ks:listTag table="CMS_CATEGORY" column="PARENT_ID"/>
							<ks:listTag table="CMS_CATEGORY" column="HREF"/>
							<ks:listTag table="CMS_CATEGORY" column="EXTENDS_INFO"/>
							<ks:listTag table="CMS_CATEGORY" column="TARGET"/>
							<ks:listTag table="CMS_CATEGORY" column="DESCRIPTION"/>
							<ks:listTag table="CMS_CATEGORY" column="KEYWORDS"/>
							<ks:listTag table="CMS_CATEGORY" column="SEQ"/>
							<ks:listTag table="CMS_CATEGORY" column="IN_MENU"/>
							<ks:listTag table="CMS_CATEGORY" column="IN_LIST"/>
							<ks:listTag table="CMS_CATEGORY" column="IS_COMMENT"/>
							<ks:listTag table="CMS_CATEGORY" column="IS_AUDIT"/>
							<ks:listTag table="CMS_CATEGORY" column="CUSTOM_LIST_VIEW"/>
							<ks:listTag table="CMS_CATEGORY" column="CUSTOM_CONTENT_VIEW"/>
							<ks:listTag table="CMS_CATEGORY" column="VIEW_CONFIG"/>
						</tr>
					</thead>
				</table>
				<div id="toolbar">
					<a href="javascript:void(0)" class="easyui-linkbutton info" iconCls="icon-add" plain="true" onclick="$.category.newInfo()">新建</a>
					<a href="javascript:void(0)" class="easyui-linkbutton warning" iconCls="icon-edit" plain="true" onclick="$.category.editInfo()">修改</a>
					<a href="javascript:void(0)" class="easyui-linkbutton error" iconCls="icon-remove" plain="true" onclick="$.category.destroyInfo()">删除</a>
					<!-- <a href="javascript:void(0)" class="easyui-linkbutton primary" iconCls="icon-export" plain="true" onclick="$.category.exportExcel()">导出</a> -->
					<ks:queryTag table="CMS_CATEGORY" column="REMARKS"/>
					<ks:queryTag table="CMS_CATEGORY" column="SITE_ID"/>
					<ks:queryTag table="CMS_CATEGORY" column="MODULE"/>
					<ks:queryTag table="CMS_CATEGORY" column="NAME"/>
					<ks:queryTag table="CMS_CATEGORY" column="HREF"/>
					<ks:queryTag table="CMS_CATEGORY" column="EXTENDS_INFO"/>
					<ks:queryTag table="CMS_CATEGORY" column="TARGET"/>
					<ks:queryTag table="CMS_CATEGORY" column="DESCRIPTION"/>
					<ks:queryTag table="CMS_CATEGORY" column="KEYWORDS"/>
					<ks:queryTag table="CMS_CATEGORY" column="SEQ"/>
					<ks:queryTag table="CMS_CATEGORY" column="IN_MENU"/>
					<ks:queryTag table="CMS_CATEGORY" column="IN_LIST"/>
					<ks:queryTag table="CMS_CATEGORY" column="IS_COMMENT"/>
					<ks:queryTag table="CMS_CATEGORY" column="IS_AUDIT"/>
					<ks:queryTag table="CMS_CATEGORY" column="CUSTOM_LIST_VIEW"/>
					<ks:queryTag table="CMS_CATEGORY" column="CUSTOM_CONTENT_VIEW"/>
					<ks:queryTag table="CMS_CATEGORY" column="VIEW_CONFIG"/>
					<input id="sel_parentId" name="sel_parentId" type="hidden" value='${ksfn:getConst("ROOT_ID_32")}'/>
					<input id="sel_siteId" name="sel_siteId" type="hidden" value='${ksfn:getConst("ROOT_ID_32")}'/>
					<a href="javascript:void(0)" class="easyui-linkbutton success" iconCls="icon-search" plain="true" onclick="$.category.selectData()">查询</a>
				</div>
			</div>
		</div>
		<script type="text/javascript">
			var tableName = "CMS_CATEGORY";
			$(document).ready(function(){
				$('#siteDataList').datagrid({
		    		onDblClickRow: function(index, row){
		    			$('#sel_siteId').val(row.id);
		    			$('#sel_parentId').val('${ksfn:getConst("ROOT_ID_32")}');
		    			$.category.reloadData();
		    		}
		    	});
				$("#dataTreeList").treegrid({
					onDblClickRow: function(row){
		    			$('#sel_parentId').val(row.id);
		    			$.category.selectData();
		    		}
		    	});
				$('#dataList').datagrid({
		    		url:'${ctx}/cms/category/list/data',
		    		queryParams: {
		    			parentId:$('#sel_parentId').val(),
		    			siteId:$('#sel_siteId').val()
		    		}
		    	});
				$('#dataTreeList').treegrid({
		    		url:'${ctx}/cms/category/list/tree',
		    		queryParams: {
		    			siteId:$('#sel_siteId').val()
		    		}
		    	});
			});
			var url, loadi;
			var iframeWin = null, iframeBody=null;
			$.category = {
		   		selectData : function () {
					$('#dataList').datagrid('load',{
						<ks:queryJsTag table="CMS_CATEGORY" column="REMARKS"/>
						<ks:queryJsTag table="CMS_CATEGORY" column="MODULE"/>
						<ks:queryJsTag table="CMS_CATEGORY" column="NAME"/>
						<ks:queryJsTag table="CMS_CATEGORY" column="HREF"/>
						<ks:queryJsTag table="CMS_CATEGORY" column="EXTENDS_INFO"/>
						<ks:queryJsTag table="CMS_CATEGORY" column="TARGET"/>
						<ks:queryJsTag table="CMS_CATEGORY" column="DESCRIPTION"/>
						<ks:queryJsTag table="CMS_CATEGORY" column="KEYWORDS"/>
						<ks:queryJsTag table="CMS_CATEGORY" column="SEQ"/>
						<ks:queryJsTag table="CMS_CATEGORY" column="IN_MENU"/>
						<ks:queryJsTag table="CMS_CATEGORY" column="IN_LIST"/>
						<ks:queryJsTag table="CMS_CATEGORY" column="IS_COMMENT"/>
						<ks:queryJsTag table="CMS_CATEGORY" column="IS_AUDIT"/>
						<ks:queryJsTag table="CMS_CATEGORY" column="CUSTOM_LIST_VIEW"/>
						<ks:queryJsTag table="CMS_CATEGORY" column="CUSTOM_CONTENT_VIEW"/>
						<ks:queryJsTag table="CMS_CATEGORY" column="VIEW_CONFIG"/>
						parentId: $("#sel_parentId").val(),
						siteId: $("#sel_siteId").val()
					});
				},
				reloadData : function () {
					$.category.selectData();
	    			$('#dataTreeList').treegrid('load',{
						siteId: $("#sel_siteId").val()
					});
				},
				newInfo : function (){
					if($('#sel_siteId').val() == '' || $('#sel_siteId').val() == '${ksfn:getConst("ROOT_ID_32")}') {
						layer.msg("请先选择站点！", {offset: 'rb',icon: 5,shift: 8,time: layerMsgTime});
						return;
					}
					if($('#sel_parentId').val() == '') {
						layer.msg("请先选择栏目！", {offset: 'rb',icon: 5,shift: 8,time: layerMsgTime});
						return;
					}
					$('#dataList').datagrid('clearSelections');
					$.category.layerOpen(url);
				},
				editInfo : function (){
					var row = $('#dataList').datagrid('getSelected');
					if (row){
						$.category.layerOpen(url);
					} else {
						layer.msg("请先选择要修改的记录！", {offset: 'rb',icon: 5,shift: 8,time: layerMsgTime});
					}
				},
				exportExcel : function () {
					window.open("${ctx}/cms/category/export");
				},
				layerOpen : function (url) {
					url = '${ctx}/cms/category/form';
					layer.open({
						type: 2,
						title: '内容发布栏目基本信息',
						area: ['550px', '490px'],
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
								url: '${ctx}/cms/category/delete',
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
										$.category.reloadData();
									} else {
										layer.msg("操作失败！请检查该目录是否存在子栏目或文章", {offset: 'rb',icon: 5,shift: 8,time: layerMsgTime});
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

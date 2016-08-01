<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/taglib/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>内容发布评价</title>
		<%@ include file="/WEB-INF/view/taglib/header.jsp" %>
		<%@ include file="/WEB-INF/view/taglib/easyui.jsp" %>
		<%@ include file="/WEB-INF/view/taglib/layer.jsp" %>
	</head>
	<body>
	 	<div style="position:absolute;top:0px;left:0px;right:0px;bottom:0px;">
			<div style="position:absolute;top:0px;left:0px;right:0px;bottom:0px;">
				<table id="dataList" title="内容发布评价列表<c:if test="${ksfn:getConfig('debug')=='T'}"><a href='javascript:void(0)' onclick='modDebug()'>debug</a></c:if>" 
						class="easyui-datagrid" style="width:100%;height:100%"
						toolbar="#toolbar" pagination="true"
						rownumbers="true" fitColumns="true" singleSelect="false">
					<thead>
						<tr>
							<th data-options="field:'id',hidden:true">ID</th>
							<th data-options="field:'ck',checkbox:true"></th>
							<ks:listTag table="CMS_COMMENT" column="REMARKS"/>
							<ks:listTag table="CMS_COMMENT" column="SITE_ID"/>
							<ks:listTag table="CMS_COMMENT" column="CATEGORY_ID"/>
							<ks:listTag table="CMS_COMMENT" column="BASIC_ID"/>
							<%-- <ks:listTag table="CMS_COMMENT" column="SCORE"/> --%>
							<th field="score" width="100px" align="right" formatter="formatRank">评论等级</th>
							<ks:listTag table="CMS_COMMENT" column="CONTENT"/>
							<ks:listTag table="CMS_COMMENT" column="USER_ID"/>
							<ks:listTag table="CMS_COMMENT" column="USER_NAME"/>
							<ks:listTag table="CMS_COMMENT" column="IP"/>
							<ks:listTag table="CMS_COMMENT" column="AUDIT_USER_ID"/>
							<ks:listTag table="CMS_COMMENT" column="AUDIT_USER_NAME"/>
							<ks:listTag table="CMS_COMMENT" column="AUDIT_DATE"/>
							<%-- <ks:listTag table="CMS_COMMENT" column="AUDIT_STATE"/> --%>
							<th data-options="field:'auditState',formatter:formatState">是否屏蔽</th>
						</tr>
					</thead>
				</table>
				<div id="toolbar">
					<ks:queryTag table="CMS_COMMENT" column="REMARKS"/>
					<ks:queryTag table="CMS_COMMENT" column="SITE_ID"/>
					<ks:queryTag table="CMS_COMMENT" column="CATEGORY_ID"/>
					<ks:queryTag table="CMS_COMMENT" column="BASIC_ID"/>
					<ks:queryTag table="CMS_COMMENT" column="SCORE"/>
					<ks:queryTag table="CMS_COMMENT" column="CONTENT"/>
					<ks:queryTag table="CMS_COMMENT" column="USER_ID"/>
					<ks:queryTag table="CMS_COMMENT" column="USER_NAME"/>
					<ks:queryTag table="CMS_COMMENT" column="IP"/>
					<ks:queryTag table="CMS_COMMENT" column="AUDIT_USER_ID"/>
					<ks:queryTag table="CMS_COMMENT" column="AUDIT_USER_NAME"/>
					<ks:queryTag table="CMS_COMMENT" column="AUDIT_DATE"/>
					<ks:queryTag table="CMS_COMMENT" column="AUDIT_STATE"/>
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="$.comment.selectData()">查询</a>
				</div>
			</div>
		</div>
		<script type="text/javascript">
			var tableName = "CMS_COMMENT";
			$(document).ready(function(){
				$('#sel_auditState').combobox('setValue', 'N');
				$('#dataList').datagrid({
		    		url:'${ctx}/cms/comment/list/data?basicId=${basicId}',
		    		queryParams: {
		    			auditState:$('#sel_auditState').val()
		    		}
		    	});
			});

	        function formatRank(val,row){
	        	if(val==null) {
	        		return "";
	        	}
	        	var res = "";
	        	for(var i=1; i<=val; i++) {
	        		res += "<img src='${ctx_common}/img/icon/star.png' style='width: 20px;height: 20px;'/>";
	        	}
	        	return res;
		    }
	        
	        function formatState(val,row) {
	        	if(val==null || val=="N") {
	        		return "否&nbsp;&nbsp;<a href='javascript:changeState(\""+row.id+"\",\"Y\")'>屏蔽</a>";
	        	} else if(val=="Y") {
	        		return "是&nbsp;&nbsp;<a href='javascript:changeState(\""+row.id+"\",\"N\")'>取消</a>";
	        	}
	        }
	        
	        function changeState(id, auditState) {
	        	$.ajax({
					type: "post",
					url: '${ctx}/cms/comment/changeState',
					data: {
						id : id,
						auditState : auditState
					},
					dataType: 'json',
					timeout: layerLoadMaxTime,
					beforeSend: function(XMLHttpRequest){
						loadi = layer.load(2, {shade: layerLoadShade,time: layerLoadMaxTime});
					},
					success: function(data, textStatus){
						layer.close(loadi);
						if (data.success==TRUE){
							layer.msg("操作成功！", {offset: 'rb',icon: 6,shift: 8,time: layerMsgTime});
							$.comment.reloadData();
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
	        }
			var url, loadi;
			var iframeWin = null, iframeBody=null;
			$.comment = {
		   		selectData : function () {
					$('#dataList').datagrid('load',{
						<ks:queryJsTag table="CMS_COMMENT" column="REMARKS"/>
						<ks:queryJsTag table="CMS_COMMENT" column="SITE_ID"/>
						<ks:queryJsTag table="CMS_COMMENT" column="CATEGORY_ID"/>
						<ks:queryJsTag table="CMS_COMMENT" column="BASIC_ID"/>
						<ks:queryJsTag table="CMS_COMMENT" column="SCORE"/>
						<ks:queryJsTag table="CMS_COMMENT" column="CONTENT"/>
						<ks:queryJsTag table="CMS_COMMENT" column="USER_ID"/>
						<ks:queryJsTag table="CMS_COMMENT" column="USER_NAME"/>
						<ks:queryJsTag table="CMS_COMMENT" column="IP"/>
						<ks:queryJsTag table="CMS_COMMENT" column="AUDIT_USER_ID"/>
						<ks:queryJsTag table="CMS_COMMENT" column="AUDIT_USER_NAME"/>
						<ks:queryJsTag table="CMS_COMMENT" column="AUDIT_DATE"/>
						<ks:queryJsTag table="CMS_COMMENT" column="AUDIT_STATE"/>
					});
				},
				reloadData : function () {
					$.comment.selectData();
				},
				newInfo : function (){
					$('#dataList').datagrid('clearSelections');
					$.comment.layerOpen(url);
				},
				editInfo : function (){
					var row = $('#dataList').datagrid('getSelected');
					if (row){
						$.comment.layerOpen(url);
					} else {
						layer.msg("请先选择要修改的记录！", {offset: 'rb',icon: 5,shift: 8,time: layerMsgTime});
					}
				},
				exportExcel : function () {
					window.open("${ctx}/cms/comment/export");
				},
				layerOpen : function (url) {
					url = '${ctx}/cms/comment/form';
					layer.open({
						type: 2,
						title: '内容发布评价基本信息',
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
								url: '${ctx}/cms/comment/delete',
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
										$.comment.reloadData();
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

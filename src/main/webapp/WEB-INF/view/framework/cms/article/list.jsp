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
				<table id="dataList" title="内容发布文章列表<c:if test="${ksfn:getConfig('debug')=='T'}"><a href='javascript:void(0)' onclick='modDebug()'>debug</a></c:if>" 
						class="easyui-datagrid" style="width:100%;height:100%"
						toolbar="#toolbar" pagination="true"
						rownumbers="true" fitColumns="true" singleSelect="false">
					<thead>
						<tr>
							<th data-options="field:'id',hidden:true">ID</th>
							<th data-options="field:'ck',checkbox:true"></th>
							<ks:listTag table="CMS_ARTICLE" column="REMARKS"/>
							<ks:listTag table="CMS_ARTICLE" column="SITE_ID"/>
							<ks:listTag table="CMS_ARTICLE" column="CATEGORY_ID"/>
							<ks:listTag table="CMS_ARTICLE" column="TITLE"/>
							<ks:listTag table="CMS_ARTICLE" column="SUB_TITLE"/>
							<ks:listTag table="CMS_ARTICLE" column="KEYWORDS"/>
							<ks:listTag table="CMS_ARTICLE" column="TAG_ID"/>
							<ks:listTag table="CMS_ARTICLE" column="DESCRIPTION"/>
							<ks:listTag table="CMS_ARTICLE" column="SEQ"/>
							<ks:listTag table="CMS_ARTICLE" column="HITS"/>
							<ks:listTag table="CMS_ARTICLE" column="CONTENT"/>
							<ks:listTag table="CMS_ARTICLE" column="COPYFROM"/>
							<ks:listTag table="CMS_ARTICLE" column="CUSTOM_CONTENT_VIEW"/>
							<ks:listTag table="CMS_ARTICLE" column="VIEW_CONFIG"/>
							<ks:listTag table="CMS_ARTICLE" column="POSTER"/>
						</tr>
					</thead>
				</table>
				<div id="toolbar">
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="$.article.newInfo()">新建</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="$.article.editInfo()">修改</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="$.article.destroyInfo()">删除</a>
					<!-- <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-page_excel" plain="true" onclick="$.article.exportExcel()">导出</a> -->
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-16099" plain="true" onclick="$.article.showCommon()">管理评价</a>
					<ks:queryTag table="CMS_ARTICLE" column="REMARKS"/>
					<ks:queryTag table="CMS_ARTICLE" column="SITE_ID"/>
					<ks:queryTag table="CMS_ARTICLE" column="TITLE"/>
					<ks:queryTag table="CMS_ARTICLE" column="SUB_TITLE"/>
					<ks:queryTag table="CMS_ARTICLE" column="KEYWORDS"/>
					<ks:queryTag table="CMS_ARTICLE" column="TAG_ID"/>
					<ks:queryTag table="CMS_ARTICLE" column="DESCRIPTION"/>
					<ks:queryTag table="CMS_ARTICLE" column="SEQ"/>
					<ks:queryTag table="CMS_ARTICLE" column="HITS"/>
					<ks:queryTag table="CMS_ARTICLE" column="CONTENT"/>
					<ks:queryTag table="CMS_ARTICLE" column="COPYFROM"/>
					<ks:queryTag table="CMS_ARTICLE" column="CUSTOM_CONTENT_VIEW"/>
					<ks:queryTag table="CMS_ARTICLE" column="VIEW_CONFIG"/>
					<input id="sel_categoryId" name="sel_categoryId" type="hidden" value=''/>
					<input id="sel_siteId" name="sel_siteId" type="hidden" value='${ksfn:getConst("ROOT_ID_32")}'/>
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="$.article.selectData()">查询</a>
				</div>
			</div>
		</div>
		<script type="text/javascript">
			var tableName = "CMS_ARTICLE";
			$(document).ready(function(){
				$('#siteDataList').datagrid({
		    		onClickRow: function(index, row){
		    			$('#sel_siteId').val(row.id);
		    			$('#sel_categoryId').val('');
		    			$.article.reloadData();
		    		}
		    	});
				$("#dataTreeList").treegrid({
					onClickRow: function(row){
		    			if(row.id == '${ksfn:getConst("ROOT_ID_32")}') {
		    				$('#sel_categoryId').val('');
		    			} else {
			    			$('#sel_categoryId').val(row.id);
		    			}
		    			$.article.reloadData();
		    		}
		    	});
				$('#dataList').datagrid({
		    		url:'${ctx}/cms/article/list/data',
		    		queryParams: {
		    			categoryId:$('#sel_categoryId').val(),
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
			$.article = {
		   		selectData : function () {
					$('#dataList').datagrid('load',{
						<ks:queryJsTag table="CMS_ARTICLE" column="REMARKS"/>
						<ks:queryJsTag table="CMS_ARTICLE" column="TITLE"/>
						<ks:queryJsTag table="CMS_ARTICLE" column="SUB_TITLE"/>
						<ks:queryJsTag table="CMS_ARTICLE" column="KEYWORDS"/>
						<ks:queryJsTag table="CMS_ARTICLE" column="TAG_ID"/>
						<ks:queryJsTag table="CMS_ARTICLE" column="DESCRIPTION"/>
						<ks:queryJsTag table="CMS_ARTICLE" column="SEQ"/>
						<ks:queryJsTag table="CMS_ARTICLE" column="HITS"/>
						<ks:queryJsTag table="CMS_ARTICLE" column="CONTENT"/>
						<ks:queryJsTag table="CMS_ARTICLE" column="COPYFROM"/>
						<ks:queryJsTag table="CMS_ARTICLE" column="CUSTOM_CONTENT_VIEW"/>
						<ks:queryJsTag table="CMS_ARTICLE" column="VIEW_CONFIG"/>
						categoryId: $('#sel_categoryId').val(),
						siteId: $('#sel_siteId').val()
					});
				},
				reloadData : function () {
					$.article.selectData();
	    			$('#dataTreeList').treegrid('load',{
						siteId: $("#sel_siteId").val()
					});
				},
				newInfo : function (){
					if($('#sel_categoryId').val() == '') {
						layer.msg("请先选择栏目！", {offset: 'rb',icon: 5,shift: 8,time: layerMsgTime});
						return;
					}
					$('#dataList').datagrid('clearSelections');
					$.article.layerOpen(url);
				},
				editInfo : function (){
					var row = $('#dataList').datagrid('getSelected');
					if (row){
						$.article.layerOpen(url);
					} else {
						layer.msg("请先选择要修改的记录！", {offset: 'rb',icon: 5,shift: 8,time: layerMsgTime});
					}
				},
				exportExcel : function () {
					window.open("${ctx}/cms/article/export");
				},
		        showCommon : function (url) {
		        	var row = $('#dataList').datagrid('getSelected');
		            if (row){
			            url = '${ctx}/cms/comment/basic?basicId='+row.id;
			        	layer.open({
			        	    type: 2,
			        	    title: '客户评价',
			        	    area: ['800px', '450px'],
			        	    fix: false, //不固定
			        	    maxmin: false,
			        	    content: url,
			        	    btn: [],
				            success: function(layero, index){
				                iframeBody = layer.getChildFrame('body', index);
				                iframeWin = window[layero.find('iframe')[0]['name']];
				            },
			        	    yes: function(index, layero) {
			        	    	layer.close(index);
			        	    }
			        	});
		            } else {
		            	layer.msg("请先选择文章", {offset: 'rb',icon: 5,shift: 8,time: layerMsgTime});
		            }
		        },
				layerOpen : function (url) {
					url = '${ctx}/cms/article/form';
					layer.open({
						type: 2,
						title: '内容发布文章基本信息',
						area: ['550px', '470px'],
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
								url: '${ctx}/cms/article/delete',
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
										$.article.reloadData();
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

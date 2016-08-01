<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/taglib/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>演示</title>
	<%@ include file="/WEB-INF/view/taglib/header.jsp" %>
	<%@ include file="/WEB-INF/view/taglib/easyui.jsp" %>
	<%@ include file="/WEB-INF/view/taglib/layer.jsp" %>
  </head>
  <body>
 	<div style="position:absolute;top:0px;left:0px;right:0px;bottom:0px;">
		<div style="position:absolute;top:0px;left:0px;right:0px;bottom:0px;">
		    <table id="dataList" title="演示列表<c:if test="${ksfn:getConfig('debug')=='T'}"><a href='javascript:void(0)' onclick='modDebug()'>debug</a></c:if>" class="easyui-datagrid" style="width:100%;height:100%"
		    		url="${ctx}/bussiness/demo/list/data"
		            toolbar="#toolbar" pagination="true"
		            rownumbers="true" fitColumns="true" singleSelect="false">
		        <thead>
		            <tr>
					    <th data-options="field:'id',hidden:true">ID</th>
					    <th data-options="field:'ck',checkbox:true"></th>
				    	<ks:listTag table="BO_DEMO" column="REMARKS"/>
				    	<ks:listTag table="BO_DEMO" column="TREE_ID"/>
				    	<ks:listTag table="BO_DEMO" column="EXPRESSION"/>
				    	<ks:listTag table="BO_DEMO" column="USER_NAME"/>
				    	<ks:listTag table="BO_DEMO" column="USER_SEX"/>
				    	<ks:listTag table="BO_DEMO" column="USER_AGE"/>
				    	<%-- <th field="userName" width="80" align="right" sortable="true">姓名</th>
				    	<th field="userSex" width="80" align="right" sortable="true">性别</th>
				    	<th field="userAge" width="80" align="right" sortable="true">年龄</th> --%>
				    	<ks:listTag table="BO_DEMO" column="USER_BIRTHDAY"/>
				    	<ks:listTag table="BO_DEMO" column="USER_ICON"/>
				    	<ks:listTag table="BO_DEMO" column="USER_SUMMARY"/>
				    	<ks:listTag table="BO_DEMO" column="DEPARTMENT_ID"/>
				    	<ks:listTag table="BO_DEMO" column="MANAGER_ID"/>
		            </tr>
		        </thead>
		    </table>
		    <div id="toolbar">
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="$.demo.newInfo()">新建</a>
		    	<shiro:hasPermission name="bussiness:demo:insert">
		        </shiro:hasPermission>
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="$.demo.editInfo()">修改</a>
		        <shiro:hasPermission name="bussiness:demo:update">
		        </shiro:hasPermission>
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="$.demo.destroyInfo()">删除</a>
		        <shiro:hasPermission name="bussiness:demo:delete">
		        </shiro:hasPermission>
		        <shiro:hasPermission name="bussiness:demo:export">
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-page_excel" plain="true" onclick="$.demo.exportExcel()">导出</a>
		        </shiro:hasPermission>
		    	<ks:queryTag table="BO_DEMO" column="REMARKS"/>
		    	<ks:queryTag table="BO_DEMO" column="TREE_ID"/>
		    	<ks:queryTag table="BO_DEMO" column="EXPRESSION"/>
		    	<ks:queryTag table="BO_DEMO" column="USER_NAME"/>
		    	<ks:queryTag table="BO_DEMO" column="USER_SEX"/>
		    	<ks:queryTag table="BO_DEMO" column="USER_AGE"/>
		    	<ks:queryTag table="BO_DEMO" column="USER_BIRTHDAY"/>
		    	<ks:queryTag table="BO_DEMO" column="USER_ICON"/>
		    	<ks:queryTag table="BO_DEMO" column="USER_SUMMARY"/>
		    	<ks:queryTag table="BO_DEMO" column="DEPARTMENT_ID"/>
		    	<ks:queryTag table="BO_DEMO" column="MANAGER_ID"/>
			    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="$.demo.selectData()">查询</a>
		    </div>
	    </div>
    </div>
    <script type="text/javascript">
    	var tableName = "BO_DEMO";
	    $(document).ready(function(){
	    });
	    
        var url, loadi, index;
        var iframeWin = null, iframeBody=null;
        $.demo = {
       		selectData : function () {
	        	$('#dataList').datagrid('load',{
			    	<ks:queryJsTag table="BO_DEMO" column="REMARKS"/>
			    	<ks:queryJsTag table="BO_DEMO" column="TREE_ID"/>
			    	<ks:queryJsTag table="BO_DEMO" column="EXPRESSION"/>
			    	<ks:queryJsTag table="BO_DEMO" column="USER_NAME"/>
			    	<ks:queryJsTag table="BO_DEMO" column="USER_SEX"/>
			    	<ks:queryJsTag table="BO_DEMO" column="USER_AGE"/>
			    	<ks:queryJsTag table="BO_DEMO" column="USER_BIRTHDAY"/>
			    	<ks:queryJsTag table="BO_DEMO" column="USER_ICON"/>
			    	<ks:queryJsTag table="BO_DEMO" column="USER_SUMMARY"/>
			    	<ks:queryJsTag table="BO_DEMO" column="DEPARTMENT_ID"/>
			    	<ks:queryJsTag table="BO_DEMO" column="MANAGER_ID"/>
	        	});
	        },
        	reloadData : function () {
        		$.demo.selectData();
	        },
	        newInfo : function (){
	        	$('#dataList').datagrid('clearSelections');
	        	$.demo.layerOpen(url);
				//layer.full(index);
	        },
	        editInfo : function (){
	            var row = $('#dataList').datagrid('getSelected');
	            //var row = $('#dataList').datagrid('getSelections');
	            if (row){
	            	//if(row.length != 1) {
	            		//layer.msg("只能选择一条记录修改！", {offset: 'rb',icon: 5,shift: 8,time: layerMsgTime});
	            	//} else {
		            $.demo.layerOpen(url);
					//layer.full(index);
	            	//}
	            } else {
					layer.msg("请先选择要修改的记录！", {offset: 'rb',icon: 5,shift: 8,time: layerMsgTime});
				}
	        },
	        exportExcel : function () {
	        	window.open("${ctx}/bussiness/demo/export");
	        },
	        layerOpen : function (url) {
	            url = '${ctx}/bussiness/demo/form';
	            index = layer.open({
	        	    type: 2,
	        	    title: '演示基本信息',
	        	    area: ['545px', '460px'],
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
							url: '${ctx}/bussiness/demo/delete',
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
			                        $.demo.reloadData();
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

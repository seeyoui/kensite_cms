<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/taglib/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>接口描述</title>
	<%@ include file="/WEB-INF/view/taglib/header.jsp" %>
	<%@ include file="/WEB-INF/view/taglib/easyui.jsp" %>
	<%@ include file="/WEB-INF/view/taglib/layer.jsp" %>
  </head>
  <body>
 	<div style="position:absolute;top:0px;left:0px;right:0px;bottom:0px;">
 		<div style="position:absolute;top:0px;left:0px;widht:410px;bottom:0px;">
		    <table id="dataList" title="接口目录列表" class="easyui-datagrid" style="width:100%;height:100%"
		    		url="${ctx}/oauth/interfaceCatalog/list/data"
		            toolbar="#toolbar" pagination="true"
		            rownumbers="true" fitColumns="true" singleSelect="true">
		        <thead>
		            <tr>
					    <th data-options="field:'id',hidden:true">ID</th>
				    	<ks:listTag table="BO_INTERFACE_CATALOG" column="NAME"/>
				    	<ks:listTag table="BO_INTERFACE_CATALOG" column="PARENT_ID"/>
				    	<ks:listTag table="BO_INTERFACE_CATALOG" column="SEQUENCE"/>
		            </tr>
		        </thead>
		    </table>
		    <div id="toolbar">
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="$.interfaceCatalog.newInfo()">新建</a>
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="$.interfaceCatalog.editInfo()">修改</a>
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="$.interfaceCatalog.destroyInfo()">删除</a>
		        <!-- <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-page_excel" plain="true" onclick="$.interfaceCatalog.exportExcel()">导出</a> -->
		    	<ks:queryTag table="BO_INTERFACE_CATALOG" column="NAME"/>
		    	<ks:queryTag table="BO_INTERFACE_CATALOG" column="PARENT_ID"/>
		    	<ks:queryTag table="BO_INTERFACE_CATALOG" column="SEQUENCE"/>
			    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="$.interfaceCatalog.selectData()">查询</a>
		    </div>
	    </div>
	    
		<div style="position:absolute;top:0px;left:410px;right:0px;bottom:0px;">
		    <table id="dataListSub" title="接口描述列表" class="easyui-datagrid" style="width:100%;height:100%"
		    		url="${ctx}/oauth/interfaceDesc/list/data"
		            toolbar="#toolbarSub" pagination="true"
		            rownumbers="true" fitColumns="true" singleSelect="true">
		        <thead>
		            <tr>
					    <th data-options="field:'id',hidden:true">ID</th>
				    	<ks:listTag table="BO_INTERFACE_DESC" column="NAME"/>
				    	<ks:listTag table="BO_INTERFACE_DESC" column="URL"/>
				    	<ks:listTag table="BO_INTERFACE_DESC" column="METHOD"/>
	    				<ks:listTag table="BO_INTERFACE_DESC" column="TYPE"/>	
				    	<ks:listTag table="BO_INTERFACE_DESC" column="PARAMETER"/>
				    	<ks:listTag table="BO_INTERFACE_DESC" column="RETURN_VALUE"/>
				    	<ks:listTag table="BO_INTERFACE_DESC" column="EXCLUDES"/>
				    	<ks:listTag table="BO_INTERFACE_DESC" column="CATALOG_ID"/>
	    				<ks:listTag table="BO_INTERFACE_DESC" column="SEQUENCE"/>	
		            </tr>
		        </thead>
		    </table>
		    <div id="toolbarSub">
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="$.interfaceDesc.newInfo()">新建</a>
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="$.interfaceDesc.editInfo()">修改</a>
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="$.interfaceDesc.destroyInfo()">删除</a>
		        <!-- <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-page_excel" plain="true" onclick="$.interfaceDesc.exportExcel()">导出</a> -->
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-5571" plain="true" onclick="$.interfaceDesc.tryout()">试用</a>
		    	<%-- <ks:queryTag table="BO_INTERFACE_DESC" column="NAME"/> --%>
		    	名称<input id="sel_desc_name" name="sel_desc_name" class="easyui-textbox" data-options="" style="width:100px"/>
		    	<ks:queryTag table="BO_INTERFACE_DESC" column="URL"/>
		    	<ks:queryTag table="BO_INTERFACE_DESC" column="METHOD"/>
		    	<ks:queryTag table="BO_INTERFACE_DESC" column="TYPE"/>
		    	<ks:queryTag table="BO_INTERFACE_DESC" column="PARAMETER"/>
		    	<ks:queryTag table="BO_INTERFACE_DESC" column="RETURN_VALUE"/>
		    	<ks:queryTag table="BO_INTERFACE_DESC" column="EXCLUDES"/>
		    	<%-- <ks:queryTag table="BO_INTERFACE_DESC" column="CATALOG_ID"/> --%>
   				<ks:queryTag table="BO_INTERFACE_DESC" column="SEQUENCE"/>	
		    	<input id="sel_catalogId" name="catalogId" type="hidden"/>
			    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="$.interfaceDesc.selectData()">查询</a>
		    </div>
	    </div>
    </div>
    <script type="text/javascript">
	    $(document).ready(function(){
	    	$('#dataList').datagrid({
	    		onDblClickRow: function(index,row){
	    			$('#sel_catalogId').val(row.id);
	    			catalogId = row.id;
	    			$.interfaceDesc.selectData();
	    		}
	    	});
	    });
        var url, loadi, catalogId;
        var iframeWin = null, iframeBody=null;
        $.interfaceCatalog = {
           		selectData : function () {
    	        	$('#dataList').datagrid('load',{
    			    	<ks:queryJsTag table="BO_INTERFACE_CATALOG" column="NAME"/>
    			    	<ks:queryJsTag table="BO_INTERFACE_CATALOG" column="PARENT_ID"/>
    	   				<ks:queryJsTag table="BO_INTERFACE_CATALOG" column="SEQUENCE"/>	
    	        	});
    	        },
            	reloadData : function () {
            		$.interfaceCatalog.selectData();
    	        },    		    
    	        newInfo : function (){
    	        	$('#dataList').datagrid('clearSelections');
    	        	$.interfaceCatalog.layerOpen(url);
    	        },
    	        editInfo : function (){
    	            var row = $('#dataList').datagrid('getSelected');
    	            if (row){
    	            	$.interfaceCatalog.layerOpen(url);
    	            }    	
    	        },
    	        exportExcel : function () {
    	        	window.open("${ctx}/oauth/interfaceCatalog/export");
    	        },
    	        layerOpen : function (url) {
    	            url = '${ctx}/oauth/interfaceCatalog/form';
    	        	layer.open({
    	        	    type: 2,
    	        	    title: '接口目录基本信息',
    	        	    area: ['310px', '180px'],
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
    	            var row = $('#dataList').datagrid('getSelected');
    	            if (row){
    	            	layer.confirm('是否确认删除？', {
    	            	    btn: ['确定','取消'] //按钮
    	            	}, function(){
    	            		$.ajax({
    							type: "post",
    							url: '${ctx}/oauth/interfaceCatalog/delete',
    							data: {id:row.id},
    							dataType: 'json',
    							beforeSend: function(XMLHttpRequest){
    								loadi = layer.load(2, {shade: layerLoadShade,time: layerLoadMaxTime});
    							},
    							success: function(data, textStatus){
    								layer.close(loadi);
    								if (data.success==TRUE){
    			                        layer.msg("操作成功！", {offset: 'rb',icon: 6,shift: 8,time: layerMsgTime});
    			                        $.interfaceCatalog.reloadData();
    			                    } else {
    				                    layer.msg("操作失败！", {offset: 'rb',icon: 5,shift: 8,time: layerMsgTime});
    			                    }
    							}
    						});
    	            	}, function(){
    	            	});
    	            }
    	        }
            }
        $.interfaceDesc = {
       		selectData : function () {
	        	$('#dataListSub').datagrid('load',{
			    	/* <ks:queryJsTag table="BO_INTERFACE_DESC" column="NAME"/> */
			    	name : $('#sel_desc_name').textbox('getValue'),
			    	<ks:queryJsTag table="BO_INTERFACE_DESC" column="URL"/>
			    	<ks:queryJsTag table="BO_INTERFACE_DESC" column="METHOD"/>
			    	<ks:queryJsTag table="BO_INTERFACE_DESC" column="TYPE"/>
			    	<ks:queryJsTag table="BO_INTERFACE_DESC" column="PARAMETER"/>
			    	<ks:queryJsTag table="BO_INTERFACE_DESC" column="RETURN_VALUE"/>
			    	<ks:queryJsTag table="BO_INTERFACE_DESC" column="EXCLUDES"/>
			    	<ks:queryJsTag table="BO_INTERFACE_DESC" column="CATALOG_ID"/>
	   				<ks:queryJsTag table="BO_INTERFACE_DESC" column="SEQUENCE"/>	
			    	catalogId : $('#sel_catalogId').val()
	        	});
	        },
        	reloadData : function () {
        		$.interfaceDesc.selectData();
	        },		    
	        newInfo : function (){
	        	if(catalogId==null || catalogId=="") {
                    layer.msg("请选择接口目录！", {offset: 'rb',icon: 5,shift: 8,time: layerMsgTime});
	        		return;
	        	}
	        	$('#dataListSub').datagrid('clearSelections');
	        	$.interfaceDesc.layerOpen(url,'','', ['530px', '600px']);
	        },
	        editInfo : function (){
	            var row = $('#dataListSub').datagrid('getSelected');
	            if (row){
	            	$.interfaceDesc.layerOpen(url,'','', ['530px', '600px']);
	            }
	        },
	        exportExcel : function () {
	        	window.open("${ctx}/oauth/interfaceDesc/export");
	        },
	        layerOpen : function (url, title, btnText, area) {
	        	var btn = [btnText, '取消'];
	        	if(url == null || url == '') {
		            url = '${ctx}/oauth/interfaceDesc/form';
	        	}
	        	if(btnText == null || btnText == '') {
	        		btnText = '保存';
	        		btn = [btnText, '取消', '试用'];
	        	}
	        	if(title == null || title == '') {
	        		title = '接口描述基本信息';
	        	}
	        	if(area == null || area == '') {
	        		area = ['530px', '400px'];
	        	}
	        	layer.open({
	        	    type: 2,
	        	    title: title,
	        	    area: area,
	        	    fix: false, //不固定
	        	    maxmin: false,
	        	    content: url,
	        	    btn: btn,
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
	        	    },btn3: function(index, layero){
	        	    	$.interfaceDesc.tryout();
	        	    }
	        	});
	        },
	        destroyInfo : function (){
	            var row = $('#dataListSub').datagrid('getSelected');
	            if (row){
	            	layer.confirm('是否确认删除？', {
	            	    btn: ['确定','取消'] //按钮
	            	}, function(){
	            		$.ajax({
							type: "post",
							url: '${ctx}/oauth/interfaceDesc/delete',
							data: {id:row.id},
							dataType: 'json',
							beforeSend: function(XMLHttpRequest){
								loadi = layer.load(2, {shade: layerLoadShade,time: layerLoadMaxTime});
							},
							success: function(data, textStatus){
								layer.close(loadi);
								if (data.success==TRUE){
			                        layer.msg("操作成功！", {offset: 'rb',icon: 6,shift: 8,time: layerMsgTime});
			                        $.interfaceDesc.reloadData();
			                    } else {
				                    layer.msg("操作失败！", {offset: 'rb',icon: 5,shift: 8,time: layerMsgTime});
			                    }
							}
						});
	            	}, function(){
	            	});
	            }
	        },
	        tryout : function () {
	            var row = $('#dataListSub').datagrid('getSelected');
	            var url = '${ctx}/oauth/interfaceManager/form';
	            if (row){
	            	if(row.type == 'JSON') {
	            		url = '${ctx}/oauth/interfaceManager/json';
	            	}
		        	$.interfaceDesc.layerOpen(url, '接口试用', '试用');
	            }
		    }
        }
    </script>
  </body>
</html>

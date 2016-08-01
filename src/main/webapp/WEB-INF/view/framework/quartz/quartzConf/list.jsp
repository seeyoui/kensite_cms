<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/taglib/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>定时任务计划配置</title>
	<%@ include file="/WEB-INF/view/taglib/header.jsp" %>
	<%@ include file="/WEB-INF/view/taglib/easyui.jsp" %>
	<%@ include file="/WEB-INF/view/taglib/layer.jsp" %>
  </head>
  <body>
 	<div style="position:absolute;top:0px;left:0px;right:0px;bottom:0px;">
		<div style="position:absolute;top:0px;left:0px;right:0px;bottom:0px;">
		    <table id="dataList" title="定时任务计划配置列表" class="easyui-datagrid" style="width:100%;height:100%"
		    		url="${ctx}/quartz/quartzConf/list/data"
		            toolbar="#toolbar" pagination="true"
		            rownumbers="true" fitColumns="true" singleSelect="true">
		        <thead>
		            <tr>
					    <th data-options="field:'id',hidden:true">ID</th>
				    	<ks:listTag table="JOB_QUARTZ_CONF" column="REMARKS"/>
				    	<ks:listTag table="JOB_QUARTZ_CONF" column="JOB_NAME"/>
				    	<ks:listTag table="JOB_QUARTZ_CONF" column="ALIAS_NAME"/>
				    	<ks:listTag table="JOB_QUARTZ_CONF" column="JOB_GROUP"/>
				    	<ks:listTag table="JOB_QUARTZ_CONF" column="JOB_TRIGGER"/>
				    	<ks:listTag table="JOB_QUARTZ_CONF" column="JOB_CLASS"/>
				    	<ks:listTag table="JOB_QUARTZ_CONF" column="CRON_EXPRESSION"/>
				    	<ks:listTag table="JOB_QUARTZ_CONF" column="IS_SYNC"/>
				    	<ks:listTag table="JOB_QUARTZ_CONF" column="DESCRIPTION"/>
				    	<ks:listTag table="JOB_QUARTZ_CONF" column="STATUS"/>
				    	<ks:listTag table="JOB_QUARTZ_CONF" column="START_AT"/>
				    	<ks:listTag table="JOB_QUARTZ_CONF" column="END_AT"/>
						<th field="operate" width="170px" data-options="formatter:rowformater">操作</th>
		            </tr>
		        </thead>
		    </table>
		    <div id="toolbar">
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="$.quartzConf.newInfo()">新建</a>
		    	<shiro:hasPermission name="quartz:quartzConf:insert">
		        </shiro:hasPermission>
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="$.quartzConf.editInfo()">修改</a>
		        <shiro:hasPermission name="quartz:quartzConf:update">
		        </shiro:hasPermission>
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="$.quartzConf.destroyInfo()">删除</a>
		        <shiro:hasPermission name="quartz:quartzConf:delete">
		        </shiro:hasPermission>
		        <shiro:hasPermission name="quartz:quartzConf:export">
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-page_excel" plain="true" onclick="$.quartzConf.exportExcel()">导出</a>
		        </shiro:hasPermission>
		    	<ks:queryTag table="JOB_QUARTZ_CONF" column="REMARKS"/>
		    	<ks:queryTag table="JOB_QUARTZ_CONF" column="JOB_NAME"/>
		    	<ks:queryTag table="JOB_QUARTZ_CONF" column="ALIAS_NAME"/>
		    	<ks:queryTag table="JOB_QUARTZ_CONF" column="JOB_GROUP"/>
		    	<ks:queryTag table="JOB_QUARTZ_CONF" column="JOB_TRIGGER"/>
		    	<ks:queryTag table="JOB_QUARTZ_CONF" column="CRON_EXPRESSION"/>
		    	<ks:queryTag table="JOB_QUARTZ_CONF" column="IS_SYNC"/>
		    	<ks:queryTag table="JOB_QUARTZ_CONF" column="DESCRIPTION"/>
		    	<ks:queryTag table="JOB_QUARTZ_CONF" column="STATUS"/>
		    	<ks:queryTag table="JOB_QUARTZ_CONF" column="JOB_CLASS"/>
		    	<ks:queryTag table="JOB_QUARTZ_CONF" column="START_AT"/>
		    	<ks:queryTag table="JOB_QUARTZ_CONF" column="END_AT"/>
			    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="$.quartzConf.selectData()">查询</a>
		    </div>
	    </div>
    </div>
    <script type="text/javascript">
	    $(document).ready(function(){
	    });
        var url, loadi;
        var iframeWin = null, iframeBody=null;
        $.quartzConf = {
       		selectData : function () {
	        	$('#dataList').datagrid('load',{
			    	<ks:queryJsTag table="JOB_QUARTZ_CONF" column="REMARKS"/>
			    	<ks:queryJsTag table="JOB_QUARTZ_CONF" column="JOB_NAME"/>
			    	<ks:queryJsTag table="JOB_QUARTZ_CONF" column="ALIAS_NAME"/>
			    	<ks:queryJsTag table="JOB_QUARTZ_CONF" column="JOB_GROUP"/>
			    	<ks:queryJsTag table="JOB_QUARTZ_CONF" column="JOB_TRIGGER"/>
			    	<ks:queryJsTag table="JOB_QUARTZ_CONF" column="CRON_EXPRESSION"/>
			    	<ks:queryJsTag table="JOB_QUARTZ_CONF" column="IS_SYNC"/>
			    	<ks:queryJsTag table="JOB_QUARTZ_CONF" column="DESCRIPTION"/>
			    	<ks:queryJsTag table="JOB_QUARTZ_CONF" column="STATUS"/>
			    	<ks:queryJsTag table="JOB_QUARTZ_CONF" column="JOB_CLASS"/>
			    	<ks:queryJsTag table="JOB_QUARTZ_CONF" column="START_AT"/>
			    	<ks:queryJsTag table="JOB_QUARTZ_CONF" column="END_AT"/>
	        	});
	        },
        	reloadData : function () {
        		$.quartzConf.selectData();
	        },
	        newInfo : function (){
	        	$('#dataList').datagrid('clearSelections');
	        	$.quartzConf.layerOpen(url);
	        },
	        editInfo : function (){
	            var row = $('#dataList').datagrid('getSelected');
	            //var row = $('#dataList').datagrid('getSelections');
	            if (row){
            		$.quartzConf.layerOpen(url);
	            } else {
					layer.msg("请先选择要修改的记录！", {offset: 'rb',icon: 5,shift: 8,time: layerMsgTime});
				}
	        },
	        exportExcel : function () {
	        	window.open("${ctx}/quartz/quartzConf/export");
	        },
	        layerOpen : function (url) {
	            url = '${ctx}/quartz/quartzConf/form';
	        	layer.open({
	        	    type: 2,
	        	    title: '定时任务计划配置基本信息',
	        	    area: ['550px', '350px'],
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
	            //var row = $('#dataList').datagrid('getSelections');
	            if (row){
	            	layer.confirm('是否确认删除？', {
	            	    btn: ['确定','取消'] //按钮
	            	}, function(){
	            		$.ajax({
							type: "post",
							url: '${ctx}/quartz/quartzConf/delete',
							data: {id:row.id},
							dataType: 'json',
							timeout: layerLoadMaxTime,
							beforeSend: function(XMLHttpRequest){
								loadi = layer.load(2, {shade: layerLoadShade,time: layerLoadMaxTime});
							},
							success: function(data, textStatus){
								layer.close(loadi);
								if (data.success==TRUE){
			                        layer.msg("操作成功！", {offset: 'rb',icon: 6,shift: 8,time: layerMsgTime});
			                        $.quartzConf.reloadData();
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
        

	    //生成操作栏
	    function rowformater(value, row, index) {
			var button = "";
			if (row.status!='PAUSED') {
				button = button
						+ '<a href="javascript:void(0)" class="easyui-linkbutton" plain="true" onclick="pause(\''
						+ row.id + '\')">暂停</a> |';	
			} else {
				button = button
						+ '<a href="javascript:void(0)" class="easyui-linkbutton" plain="true" onclick="resume(\''
						+ row.id + '\')">恢复</a> |';	
			}
			button = button
			+ '<a href="javascript:void(0)" class="easyui-linkbutton" plain="true" onclick="runOnce(\''
			+ row.id + '\')">立即运行一次</a> |';	
			return button;
		}
	    
	    //暂停
	    function pause(id){
            if (id){
            	layer.confirm('确定停止该任务？', {
            	    btn: ['确定','取消'] //按钮
            	}, function(){
            		$.ajax({
						type: "post",
						url: '${ctx}/quartz/quartzConf/pause',
						data: {id:id},
						dataType: 'json',
						beforeSend: function(XMLHttpRequest){
							loadi = layer.load(2, {shade: layerLoadShade,time: layerLoadMaxTime});
						},
						success: function(data, textStatus){	
							layer.close(loadi);
							if (data.success==TRUE){
		                        layer.msg("操作成功！", {offset: 'rb',icon: 6,shift: 8,time: layerMsgTime});
		                        $.quartzConf.reloadData();
		                    } else {
			                    layer.msg("操作失败！"+data.message, {offset: 'rb',icon: 5,shift: 8,time: layerMsgTime});
		                    }
						}
					});
            	}, function(){
            	});
            }
	    }
	    
	    //恢复
	    function resume(id){
            if (id){
            	layer.confirm('确定恢复该任务？', {
            	    btn: ['确定','取消'] //按钮
            	}, function(){
            		$.ajax({
						type: "post",
						url: '${ctx}/quartz/quartzConf/resume',
						data: {id:id},
						dataType: 'json',
						beforeSend: function(XMLHttpRequest){
							loadi = layer.load(2, {shade: layerLoadShade,time: layerLoadMaxTime});
						},
						success: function(data, textStatus){	
							layer.close(loadi);
							if (data.success==TRUE){
		                        layer.msg("操作成功！", {offset: 'rb',icon: 6,shift: 8,time: layerMsgTime});
		                        $.quartzConf.reloadData();
		                    } else {
			                    layer.msg("操作失败！"+data.message, {offset: 'rb',icon: 5,shift: 8,time: layerMsgTime});
		                    }
						}
					});
            	}, function(){
            	});
            }
	    }
	    
	    //立即运行一次
	    function runOnce(id){
            if (id){
            	layer.confirm('确定立即运行一次该任务？', {
            	    btn: ['确定','取消'] //按钮
            	}, function(){
            		$.ajax({
						type: "post",
						url: '${ctx}/quartz/quartzConf/runOnce',
						data: {id:id},
						dataType: 'json',
						beforeSend: function(XMLHttpRequest){
							loadi = layer.load(2, {shade: layerLoadShade,time: layerLoadMaxTime});
						},
						success: function(data, textStatus){	
							layer.close(loadi);
							if (data.success==TRUE){
		                        layer.msg("操作成功！", {offset: 'rb',icon: 6,shift: 8,time: layerMsgTime});
		                        $.quartzConf.reloadData();
		                    } else {
			                    layer.msg("操作失败！"+data.message, {offset: 'rb',icon: 5,shift: 8,time: layerMsgTime});
		                    }
						}
					});
            	}, function(){
            	});
            }
	    }
    </script>
  </body>
</html>

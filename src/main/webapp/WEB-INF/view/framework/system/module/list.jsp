<%@ page import="com.seeyoui.kensite.common.constants.StringConstant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/taglib/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>    
    <title>系统模块</title>
	<%@ include file="/WEB-INF/view/taglib/header.jsp" %>
	<%@ include file="/WEB-INF/view/taglib/easyui.jsp" %>
	<%@ include file="/WEB-INF/view/taglib/layer.jsp" %>
  </head>
  <body>
  
  	<div style="position:absolute;top:0px;left:0px;right:0px;bottom:0px;">
		<div style="position:absolute;top:0px;left:0px;right:0px;bottom:0px;">
		    <table id="dataList" title="" class="easyui-datagrid" style="width:100%;height:100%"
		    		url="${ctx}/sysModule/list/data"
		            toolbar="#toolbar" pagination="true"
		            rownumbers="true" fitColumns="true" singleSelect="true">
		        <thead>
		            <tr>
					    <th field="id" width="100px" hidden>主键</th>
					    <th field="name" width="100px">模块名称</th>
					    <th field="shiro" width="100px">权限</th>
		            </tr>
		        </thead>
		    </table>
		    <div id="toolbar">
		    	<shiro:hasPermission name="sysModule:insert">
		        <a href="javascript:void(0)" class="easyui-linkbutton info" iconCls="icon-add" plain="true" onclick="newInfo()">新建</a>
		        </shiro:hasPermission>
		        <shiro:hasPermission name="sysModule:update">
		        <a href="javascript:void(0)" class="easyui-linkbutton warning" iconCls="icon-edit" plain="true" onclick="editInfo()">修改</a>
		        <a href="javascript:void(0)" class="easyui-linkbutton primary" iconCls="icon-chart_organisation" plain="true" onclick="permissionInfo()">模块操作</a>
		        </shiro:hasPermission>
		        <shiro:hasPermission name="sysModule:delete">
		        <a href="javascript:void(0)" class="easyui-linkbutton error" iconCls="icon-remove" plain="true" onclick="destroyInfo()">删除</a>
		        </shiro:hasPermission>
				<span class="toolbar-title">模块名称</span><input id="sel_name" name="sel_name" class="easyui-textbox" data-options=""/>
				<span class="toolbar-title">权限</span><input id="sel_shiro" name="sel_shiro" class="easyui-textbox" data-options=""/>
			    <a href="javascript:void(0)" class="easyui-linkbutton success" iconCls="icon-search" plain="true" onclick="selectData()">查询</a>
		    </div>
		    <div id="permissionWin" class="easyui-window" title="模块操作维护" data-options="modal:true,closed:true,iconCls:'icon-save',resizable:false" style="width:335px;height:420px;padding:10px;">
		        <form id="permissionDataForm" method="post">
					<div class="easyui-panel" title="操作" style="width:300px;height:310px;">
	            		<ul id="permissionTree" class="easyui-tree" data-options="animate:true,checkbox:true,cascadeCheck:false"></ul>
					</div>
				    <input id="roleid" name="roleid" type="hidden"/>
				</form>
				
			    <div id="dataWin-buttons" style="margin-top: 5px;">
			        <a href="javascript:void(0)" class="easyui-linkbutton default" iconCls="icon-ok" onclick="saveModulePermissionInfo()" style="width:90px">保存</a>
			        <a href="javascript:void(0)" class="easyui-linkbutton default" iconCls="icon-cancel" onclick="javascript:$('#permissionWin').window('close')" style="width:90px">取消</a>
			    </div>
		    </div>
	    </div>
    </div>
    <script type="text/javascript">
	    $(document).ready(function(){
	    });
	    
	    function selectData() {
		    var sel_name = $("#sel_name").val();
		    var sel_shiro = $("#sel_shiro").val();
        	$('#dataList').datagrid('load',{
    		    name:sel_name,
    		    shiro:sel_shiro
        	});
        }
        
        function reloadData() {
        	selectData();
        }
        
        function permissionInfo() {
        	getPermissionTreeJson();
        	$('#permissionWin').window('open');
        }
        
	    function getPermissionTreeJson() {
        	var row = $('#dataList').datagrid('getSelected');
            if (row){
            	var roleid = row.id;
	    		$.ajax({
					type: "POST",
					url: "${ctx}/sysModulePermission/tree",
					data: "moduleId="+roleid,
					dataType: "json",
					success: function(data){
						$("#permissionTree").tree("loadData",data);
					}
				});
			}
	    }
	    
	    function saveModulePermissionInfo() {
	    	var treeObj = $('#permissionTree');
	    	var permissionId = getChecked(treeObj);
	    	var row = $('#dataList').datagrid('getSelected');
	    	var moduleId = "";
	    	if(!row) {
	    		return;
	    	}
	    	moduleId = row.id;
	    	if (permissionId!=null){
				$.ajax({
					type: "post",
					url: "${ctx}/sysModulePermission/save",
					data: {moduleId:moduleId,permissionId:permissionId},
					dataType: 'json',
					beforeSend: function(XMLHttpRequest){
					},
					success: function(data, textStatus){
						if (data.success==TRUE){
							layer.msg("操作成功！", {offset: 'rb',icon: 6,shift: 8,time: layerMsgTime});
						} else {
							layer.msg("操作失败！", {offset: 'rb',icon: 5,shift: 8,time: layerMsgTime});
						}
						$('#permissionWin').window('close');
						reloadData();
					}
				});
			}
	    }
	    
	    function getChecked(treeObj){
            var nodes = treeObj.tree('getChecked');
            var s = '';
            for(var i=0; i<nodes.length; i++){
                if (i!=0 && i!=nodes.length) s += ',';
                s += nodes[i].id;
            }
            return s;
        }
	    
	    var url;
        function newInfo(){
        	$('#dataList').datagrid('clearSelections');
            layerOpen(url);
        }
        function editInfo(){
            var row = $('#dataList').datagrid('getSelected');
            if (row){
                 layerOpen(url);
            }	
        }
        
        var iframeWin = null, iframeBody=null;
        function layerOpen(url) {
            url = '${ctx}/sysModule/form';
        	layer.open({
        	    type: 2,
        	    title: '模块基本信息',
        	    area: ['300px', '220px'],
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
        }
        var loadi;
        function destroyInfo(){
            var row = $('#dataList').datagrid('getSelected');
            if (row){
            	layer.confirm('是否确认删除？', {
            	    btn: ['确定','取消'] //按钮
            	}, function(){
            		$.ajax({
						type: "post",
						url: '${ctx}/sysModule/delete',
						data: {id:row.id},
						dataType: 'json',
						beforeSend: function(XMLHttpRequest){
							loadi = layer.load(2, {shade: layerLoadShade,time: layerLoadMaxTime});
						},
						success: function(data, textStatus){
							layer.close(loadi);
							if (data.success==TRUE){
		                        layer.msg("操作成功！", {offset: 'rb',icon: 6,shift: 8,time: layerMsgTime});
								reloadData();
		                    } else {
			                    layer.msg("操作失败！", {offset: 'rb',icon: 5,shift: 8,time: layerMsgTime});
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

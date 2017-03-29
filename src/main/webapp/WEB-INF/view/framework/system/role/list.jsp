<%@ page import="com.seeyoui.kensite.common.constants.StringConstant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/taglib/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" style="overflow: hidden;">
  <head>    
    <title>角色</title>
	<%@ include file="/WEB-INF/view/taglib/header.jsp" %>
	<%@ include file="/WEB-INF/view/taglib/easyui.jsp" %>
	<%@ include file="/WEB-INF/view/taglib/layer.jsp" %>
  </head>
  <body>
  
  	<div style="position:absolute;top:0px;left:0px;right:0px;bottom:0px;">
		<div style="position:absolute;top:0px;left:0px;right:300px;bottom:0px;">
		    <table id="dataList" title="" class="easyui-datagrid" style="width:100%;height:100%"
		    		url="${ctx}/sysRole/list/data"
		            toolbar="#toolbar" pagination="true"
		            rownumbers="true" fitColumns="true" singleSelect="true">
		        <thead>
		            <tr>
					    <th field="id" width="100px" hidden>主键</th>
					    <th field="name" width="100px">角色名</th>
					    <th field="shiro" width="100px">权限</th>
		            </tr>
		        </thead>
		    </table>
		    <div id="toolbar">
		    	<shiro:hasPermission name="sysRole:insert">
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newInfo()">新建</a>
		        </shiro:hasPermission>
		        <shiro:hasPermission name="sysRole:update">
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editInfo()">修改</a>
		        <!-- <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-chart_organisation" plain="true" onclick="moduleShiro()">模块权限</a>
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-text_list_bullets" plain="true" onclick="menuShiro()">菜单权限</a> -->
		        </shiro:hasPermission>
		        <shiro:hasPermission name="sysRole:delete">
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyInfo()">删除</a>
		        </shiro:hasPermission>
				角色名<input id="sel_name" name="sel_name" class="easyui-textbox" data-options=""/>
				权限<input id="sel_shiro" name="sel_shiro" class="easyui-textbox" data-options=""/>
			    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="selectData()">查询</a>
		    </div>
		    <div id="moduleWin" class="easyui-window" title="模块权限维护" data-options="modal:true,closed:true,iconCls:'icon-save',resizable:false" style="width:335px;height:420px;padding:10px;">
		        <form id="moduleDataForm" method="post">
					<div class="easyui-panel" title="模块权限" style="width:300px;height:340px;">
	            		<ul id="moduleTree" class="easyui-tree" data-options="animate:true,checkbox:true,cascadeCheck:false"></ul>
					</div>
				</form>
				
			    <div id="dataWin-buttons">
			        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveRoleModuleInfo()" style="width:90px">保存</a>
			        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#moduleWin').window('close')" style="width:90px">取消</a>
			    </div>
		    </div>
	    </div>
	    <div style="position:absolute;top:0px;width:300px;right:0px;bottom:0px;">
	    	<form id="menuDataForm" method="post">
				<div id="menuDataPanel" class="easyui-panel" title="菜单权限" style="width:300px;height:100px;" data-options="tools:'#tt'">
            		<ul id="menuTree" class="easyui-tree" data-options="animate:true,checkbox:true,cascadeCheck:false"></ul>
				</div>
			</form>
			
		    <div id="tt" style="padding:5px;">
		    	<a href="javascript:saveRoleMenuInfo()" class="icon-save"></a>
		    </div>
	    </div>
    </div>
    <script type="text/javascript">
    	var loadi;
	    $(document).ready(function(){
	    	$('#menuDataPanel').panel('resize',{
	    		height: $(document).height()
	    	});
	    	$('#dataList').datagrid({
	    		onDblClickRow: function(index, row){
	    			getMenuTreeJson(row.id);
	    			$('#menuDataPanel').panel('setTitle', '菜单权限['+row.name+']');
	    		}
	    	});
	    });
	    $(window).resize(function() {
	    	$('#menuDataPanel').panel('resize',{
	    		height: $(this).height()
	    	});
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
            url = '${ctx}/sysRole/form';
        	layer.open({
        	    type: 2,
        	    title: '角色基本信息',
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
						url: '${ctx}/sysRole/delete',
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
        
	    function getMenuTreeJson(roleId) {
            if (roleId){
	    		$.ajax({
					type: "POST",
					url: "${ctx}/sysRoleMenu/tree",
					data: "roleId="+roleId,
					dataType: "json",
					success: function(data){
						$("#menuTree").tree("loadData",data);
					}
				});
			}
	    }
	    
	    function saveRoleMenuInfo() {
	    	var treeObj = $('#menuTree');
	    	var menuId = getChecked(treeObj);
	    	var row = $('#dataList').datagrid('getSelected');
	    	var roleId = row.id;
	    	if (menuId!=null){
				$.ajax({
					type: "post",
					url: "${ctx}/sysRoleMenu/save",
					data: {roleId:roleId,menuId:menuId},
					dataType: 'json',
					beforeSend: function(XMLHttpRequest){
						loadi = layer.load(2, {shade: layerLoadShade,time: layerLoadMaxTime});
					},
					success: function(data, textStatus){
						if (data.success==TRUE){
							layer.msg("操作成功！", {offset: 'rb',icon: 6,shift: 8,time: layerMsgTime});
						} else {
							layer.msg("操作失败！", {offset: 'rb',icon: 5,shift: 8,time: layerMsgTime});
						}
						layer.close(loadi);
					}
				});
			}
	    }
        
        function moduleShiro() {
        	getModuleTreeJson();
        	$('#moduleWin').window('open');
        }
        
        function getModuleTreeJson() {
        	var row = $('#dataList').datagrid('getSelected');
            if (row){
            	var roleId = row.id;
	    		$.ajax({
					type: "POST",
					url: "${ctx}/sysRoleModule/tree",
					data: "roleId="+roleId,
					dataType: "json",
					success: function(data){
						$("#moduleTree").tree("loadData",data);
					}
				});
			}
	    }
	    
	    function saveRoleModuleInfo() {
	    	var treeObj = $('#moduleTree');
	    	var moduleId = getChecked(treeObj);
	    	var row = $('#dataList').datagrid('getSelected');
	    	var roleId = row.id;
	    	if (moduleId!=null){
				$.ajax({
					type: "post",
					url: "${ctx}/sysRoleModule/save",
					data: {roleId:roleId,moduleId:moduleId},
					dataType: 'json',
					beforeSend: function(XMLHttpRequest){
						loadi = layer.load(2, {shade: layerLoadShade,time: layerLoadMaxTime});
					},
					success: function(data, textStatus){
						if (data.success==TRUE){
							layer.msg("操作成功！", {offset: 'rb',icon: 6,shift: 8,time: layerMsgTime});
						} else {
							layer.msg("操作失败！", {offset: 'rb',icon: 5,shift: 8,time: layerMsgTime});
						}
						layer.close(loadi);
						$('#moduleWin').window('close');
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
    </script>
  </body>
</html>

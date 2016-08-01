<%@ page import="com.seeyoui.kensite.common.constants.StringConstant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/taglib/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>    
    <title>用户信息</title>
	<%@ include file="/WEB-INF/view/taglib/header.jsp" %>
	<%@ include file="/WEB-INF/view/taglib/easyui.jsp" %>
	<%@ include file="/WEB-INF/view/taglib/layer.jsp" %>
  </head>
  <body>
  
  	<div style="position:absolute;top:0px;left:0px;right:0px;bottom:0px;">
		<div style="position:absolute;top:0px;left:0px;right:300px;bottom:0px;">
		    <table id="dataList" title="" class="easyui-datagrid" style="width:100%;height:100%"
		    		url="${ctx}/sysUser/list/data"
		            toolbar="#toolbar" pagination="true"
		            rownumbers="true" fitColumns="true" singleSelect="true">
		        <thead>
		            <tr>
					    <th field="id" width="100px" hidden>主键</th>
					    <th field="userName" width="100px">账号</th>
					    <th field="name" width="100px">用户名</th>
					    <th field="state" width="100px" formatter="formatState">状态</th>
		            </tr>
		        </thead>
		    </table>
		    <div id="toolbar">
		    	<shiro:hasPermission name="sysUser:insert">
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newInfo()">新建</a>
		        </shiro:hasPermission>
		        <shiro:hasPermission name="sysUser:update">
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editInfo()">修改</a>
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-arrow_refresh" plain="true" onclick="initPassword()">初始化密码</a>
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-lock" plain="true" onclick="updateState(0)">冻结账号</a>
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-key" plain="true" onclick="updateState(1)">激活账号</a>
		        </shiro:hasPermission>
		        <shiro:hasPermission name="sysUser:delete">
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyInfo()">删除</a>
		        </shiro:hasPermission>
				账号<input id="sel_userName" name="sel_userName" class="easyui-textbox" data-options=""/>
				用户名<input id="sel_name" name="sel_name" class="easyui-textbox" data-options=""/>
			    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="selectData()">查询</a>
		    </div>
	    </div>
	    <div style="position:absolute;top:0px;width:300px;right:0px;bottom:0px;">
	    	<form id="roleDataForm" method="post">
				<div id="roleDataPanel" class="easyui-panel" title="用户角色" style="width:300px;height:100px;" data-options="tools:'#tt'">
            		<ul id="roleTree" class="easyui-tree" data-options="animate:true,checkbox:true,cascadeCheck:false"></ul>
				</div>
			</form>
			
		    <div id="tt" style="padding:5px;">
		    	<a href="javascript:saveUserRoleInfo()" class="icon-save"></a>
		    </div>
	    </div>
    </div>
    <script type="text/javascript">
	    $(document).ready(function(){
	    	$('#roleDataPanel').panel('resize',{
	    		height: $(document).height()
	    	});
	    	$('#dataList').datagrid({
	    		onDblClickRow: function(index, row){
	    			getRoleTreeJson(row.id);
	    			$('#roleDataPanel').panel('setTitle', '用户角色['+row.name+']');
	    		}
	    	});
	    });
	    $(window).resize(function() {
	    	$('#roleDataPanel').panel('resize',{
	    		height: $(this).height()
	    	});
		});
	    
	    function selectData() {
		    var sel_userName = $("#sel_userName").val();
		    var sel_name = $("#sel_name").val();
        	$('#dataList').datagrid('load',{
    		    userName:sel_userName,
    		    name:sel_name
        	});
        }
        
        function formatState(val,row){
	    	if(val==1){
	    		return "<font color=\"green\">正常</font>";
	    	}
	    	if(val==0){
	    		return "<font color=\"red\">冻结</font>";
	    	}
	    	return "";
	    }
        
        function reloadData() {
        	selectData();
        }
        
        function getRoleTreeJson(userId) {
            if (userId){
	    		$.ajax({
					type: "POST",
					url: "${ctx}/sysUserRole/tree",
					data: "userId="+userId,
					dataType: "json",
					success: function(data){
						$("#roleTree").tree("loadData",data);
					}
				});
			}
	    }
        
        function saveUserRoleInfo() {
	    	var treeObj = $('#roleTree');
	    	var roleId = getChecked(treeObj);
	    	var row = $('#dataList').datagrid('getSelected');
	    	var userId = row.id;
	    	if (roleId!=null){
				$.ajax({
					type: "post",
					url: "${ctx}/sysUserRole/save",
					data: {userId:userId,roleId:roleId},
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
            url = '${ctx}/sysUser/form';
        	layer.open({
        	    type: 2,
        	    title: '用户基本信息',
        	    area: ['300px', '390px'],
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
						url: '${ctx}/sysUser/delete',
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
        
        function initPassword(){
            var row = $('#dataList').datagrid('getSelected');
            if (row){
                $.messager.confirm('确认','你确定初始化该记录的密码为123456吗？',function(r){
                    if (r){
                    	$.ajax({
							type: "post",
							url: "${ctx}/sysUser/initPassword",
							data: {id : row.id},
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
								reloadData();
							}
						});
                    }
                });
            }
        }
        
        function updateState(state){
            var row = $('#dataList').datagrid('getSelected');
            if (row){
                $.messager.confirm('确认','你确定改变该记录的登录状态吗？',function(r){
                    if (r){
                    	$.ajax({
							type: "post",
							url: "${ctx}/sysUser/updateState",
							data: {id : row.id, userName : row.userName, state : state},
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
								reloadData();
							}
						});
                    }
                });
            }
        }
    </script>
  </body>
</html>

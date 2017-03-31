<%@ page import="com.seeyoui.kensite.common.constants.StringConstant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/taglib/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>    
    <title>导航菜单</title>
    <link rel="stylesheet" href="${ctx_script}/css/fonts/linecons/css/linecons.css"/>
	<%@ include file="/WEB-INF/view/taglib/header.jsp" %>
	<%@ include file="/WEB-INF/view/taglib/easyui.jsp" %>
	<%@ include file="/WEB-INF/view/taglib/layer.jsp" %>
  </head>
  <body>
  
  	<div style="position:absolute;top:0px;left:0px;right:0px;bottom:0px;">
  		<div style="position:absolute;top:0px;bottom:0px;width:200px;overflow: auto;">
        	<ul id="menuTree" class="easyui-tree" url="${ctx}/sysMenu/tree"></ul>
        </div>
        <div style="position:absolute;top:0px;left:200px;right:0px;bottom:0px;">
		    <table id="dataList" title="" class="easyui-datagrid" style="width:100%;height:100%"
		            toolbar="#toolbar" pagination="true"
		            rownumbers="true" fitColumns="true" singleSelect="true">
		        <thead>
		            <tr>
					    <th field="id" width="100px" hidden>主键</th>
					    <th field="parentId" width="100px" hidden>外键</th>
					    <th field="name" width="100px">名称</th>
					    <th field="url" width="100px">URL</th>
					    <th field="sequence" width="50px" align="right">排序</th>
					    <th field="icon" width="100px">图标</th>
					    <th field="target" width="100px">打开方式</th>
		            </tr>
		        </thead>
		    </table>
		    <div id="toolbar">
		    	<shiro:hasPermission name="sysMenu:insert">
		        <a href="javascript:void(0)" class="easyui-linkbutton info" iconCls="icon-add" plain="true" onclick="newInfo()">新建</a>
		        </shiro:hasPermission>
		        <shiro:hasPermission name="sysMenu:update">
		        <a href="javascript:void(0)" class="easyui-linkbutton warning" iconCls="icon-edit" plain="true" onclick="editInfo()">修改</a>
		        </shiro:hasPermission>
		        <shiro:hasPermission name="sysMenu:delete">
		        <a href="javascript:void(0)" class="easyui-linkbutton error" iconCls="icon-remove" plain="true" onclick="destroyInfo()">删除</a>
		        </shiro:hasPermission>
				<input id="sel_parentId" name="sel_parentId" type="hidden" value='${ksfn:getConst("ROOT_ID_32")}'/>
				<span class="toolbar-title">名称</span><input id="sel_name" name="sel_name" class="easyui-textbox" data-options=""/>
				<span class="toolbar-title">URL</span><input id="sel_url" name="sel_url" class="easyui-textbox" data-options=""/>
			    <a href="javascript:void(0)" class="easyui-linkbutton success" iconCls="icon-search" plain="true" onclick="selectData()">查询</a>
		    </div>
	    </div>
    </div>
    <script type="text/javascript">
	    $(document).ready(function(){
	    	$("#menuTree").tree({
	    		onClick: function(node){
	    			$('#sel_parentId').val(node.id);
	    			selectData();
	    		}
	    	});
	    	$('#dataList').datagrid({
	    		url:'${ctx}/sysMenu/list/data',
	    		queryParams: {
	    			parentId:$('#sel_parentId').val()
	    		},
	    		onDblClickRow: function(index, row){
	    			var node = $('#menuTree').tree('find', row.id);
	    			$('#menuTree').tree('select', node.target);
	    			$('#menuTree').tree('scrollTo', node.target);
	    			$('#sel_parentId').val(row.id);
	    			selectData();
	    		}
	    	});
	    });
	    
	    function selectData() {
		    var sel_parentId = $("#sel_parentId").val();
		    var sel_name = $("#sel_name").val();
		    var sel_url = $("#sel_url").val();
        	$('#dataList').datagrid('load',{
        		parentId:sel_parentId,
    		    name:sel_name,
    		    url:sel_url
        	});
        }
        
        function reloadData() {
        	selectData();
        	$('#menuTree').tree('reload');
        	$('#parentId').combotree('reload');
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
        function exportExcel() {
        	window.open("${ctx}/sysMenu/export");
        }
        
        var iframeWin = null, iframeBody=null;
        function layerOpen(url) {
            url = '${ctx}/sysMenu/form';
        	layer.open({
        	    type: 2,
        	    title: '菜单基本信息',
        	    area: ['570px', '385px'],
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
						url: '${ctx}/sysMenu/delete',
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

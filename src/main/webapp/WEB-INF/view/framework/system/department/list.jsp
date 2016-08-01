<%@ page import="com.seeyoui.kensite.common.constants.StringConstant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/taglib/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>    
    <title>部门</title>
	<%@ include file="/WEB-INF/view/taglib/header.jsp" %>
	<%@ include file="/WEB-INF/view/taglib/easyui.jsp" %>
	<%@ include file="/WEB-INF/view/taglib/layer.jsp" %>
  </head>
  <body>
  
  	<div style="position:absolute;top:0px;left:0px;right:0px;bottom:0px;">
  		<div style="position:absolute;top:0px;bottom:0px;width:200px;">
        	<ul id="departmentTree" class="easyui-tree" url="${ctx}/sysDepartment/list/all" data-options="idFiled:'id',textField:'name',parentField:'parentId'"></ul>
        </div>
		<div style="position:absolute;top:0px;left:200px;right:0px;bottom:0px;">
		    <table id="dataList" title="" class="easyui-datagrid" style="width:100%;height:100%"
		            toolbar="#toolbar" pagination="true"
		            rownumbers="true" fitColumns="true" singleSelect="true">
		        <thead>
		            <tr>
					    <th field="id" width="100px" hidden>主键</th>
					    <th field="parentId" width="100px" hidden>上级部门</th>
					    <th field="name" width="100px">部门名称</th>
					    <th field="code" width="100px">部门编号</th>
					    <th field="sequence" width="50px" align="right">排序</th>
		            </tr>
		        </thead>
		    </table>
		    <div id="toolbar">
		    	<shiro:hasPermission name="sysDepartment:insert">
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newInfo()">新建</a>
		        </shiro:hasPermission>
		        <shiro:hasPermission name="sysDepartment:update">
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editInfo()">修改</a>
		        </shiro:hasPermission>
		        <shiro:hasPermission name="sysDepartment:delete">
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyInfo()">删除</a>
		        </shiro:hasPermission>
				<input id="sel_parentId" name="sel_parentId" type="hidden" value='${ksfn:getConst("ROOT_ID_32")}'/>
				部门名称<input id="sel_name" name="sel_name" class="easyui-textbox" data-options=""/>
				部门编号<input id="sel_code" name="sel_code" class="easyui-textbox" data-options=""/>
			    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="selectData()">查询</a>
		    </div>
	    </div>
    </div>
    <script type="text/javascript">
	    $(document).ready(function(){
	    	$("#departmentTree").tree({
	    		onClick: function(node){
	    			$('#sel_parentId').val(node.id);
	    			selectData();
	    		}
	    	});
	    	$('#dataList').datagrid({
	    		url:'${ctx}/sysDepartment/list/data',
	    		queryParams: {
	    			parentId:$('#sel_parentId').val()
	    		},
	    		onDblClickRow: function(index, row){
	    			var node = $('#departmentTree').tree('find', row.id);
	    			$('#departmentTree').tree('select', node.target);
	    			$('#departmentTree').tree('scrollTo', node.target);
	    			$('#sel_parentId').val(row.id);
	    			selectData();
	    		}
	    	});
	    });
	    
	    function selectData() {
	    	var sel_parentId = $("#sel_parentId").val();
		    var sel_name = $("#sel_name").val();
		    var sel_code = $("#sel_code").val();
        	$('#dataList').datagrid('load',{
        		parentId:sel_parentId,
    		    name:sel_name,
    		    code:sel_code
        	});
        }
        
        function reloadData() {
        	selectData();
        	$('#departmentTree').tree('reload');
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
        	window.open("${ctx}/sysDepartment/export");
        }
        
        var iframeWin = null, iframeBody=null;
        function layerOpen(url) {
            url = '${ctx}/sysDepartment/form';
        	layer.open({
        	    type: 2,
        	    title: '部门基本信息',
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
						url: '${ctx}/sysDepartment/delete',
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

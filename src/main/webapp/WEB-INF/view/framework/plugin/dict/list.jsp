<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/taglib/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>构造演示界面</title>
	<%@ include file="/WEB-INF/view/taglib/header.jsp" %>
	<%@ include file="/WEB-INF/view/taglib/easyui.jsp" %>
	<%@ include file="/WEB-INF/view/taglib/layer.jsp" %>
  </head>
  <body>
 	<div style="position:absolute;top:0px;left:0px;right:0px;bottom:0px;">
		<div style="position:absolute;top:0px;bottom:0px;width:200px; overflow:scroll;">
        	<ul id="dictTree" class="easyui-tree" url="${ctx}/sys/dict/tree"></ul>
        </div>
		<div style="position:absolute;top:0px;left:200px;right:0px;bottom:0px;">
		    <table id="dataList" title="构造演示界面列表" class="easyui-datagrid" style="width:100%;height:100%"
		            toolbar="#toolbar" pagination="true"
		            rownumbers="true" fitColumns="true" singleSelect="true">
		        <thead>
		            <tr>
				    	<th field="id" width="100px" hidden>主键</th>
					    <th field="value" width="100px">数据值</th>
					    <th field="label" width="100px">标签名</th>
					    <th field="category" width="100px">分类</th>
					    <th field="description" width="100px">描述</th>
					    <th field="sequence" width="50px" align="right">排序</th>
					    <th field="parentId" width="100px" hidden>父主键</th>
		            </tr>
		        </thead>
		    </table>
		    <div id="toolbar">
		        <a href="javascript:void(0)" class="easyui-linkbutton info" iconCls="icon-add" plain="true" onclick="newInfo()">新建</a>
		        <a href="javascript:void(0)" class="easyui-linkbutton warning" iconCls="icon-edit" plain="true" onclick="editInfo()">修改</a>
		        <a href="javascript:void(0)" class="easyui-linkbutton error" iconCls="icon-remove" plain="true" onclick="destroyInfo()">删除</a>

				<span class="toolbar-title">数据值</span><input id="sel_value" name="sel_value" class="easyui-textbox" data-options=""/>
				<span class="toolbar-title">标签名</span><input id="sel_label" name="sel_label" class="easyui-textbox" data-options=""/>
				<!-- <span class="toolbar-title">分类</span><input id="sel_category" name="sel_category" class="easyui-textbox" data-options=""/> -->
				<span class="toolbar-title">描述</span><input id="sel_description" name="sel_description" class="easyui-textbox" data-options=""/>
				<input id="sel_parentId" name="sel_parentId" type="hidden" value=""/>
			    <a href="javascript:void(0)" class="easyui-linkbutton success" iconCls="icon-search" plain="true" onclick="selectData()">查询</a>
		    </div>
	    </div>
    </div>
    <script type="text/javascript">
	    $(document).ready(function(){
	    	$("#dictTree").tree({
	    		onClick: function(node){
	    			$('#sel_parentId').val(node.id);
	    			for(var p in node.attributes){//遍历json对象的每个key/value对,p为key
    					console.info(p + " " + node.attributes[p]);
    				}
	    			selectData();
	    		}
	    	});
	    	$('#sel_parentId').val('<%=StringConstant.ROOT_ID_32%>');
	    	$('#dataList').datagrid({
	    		url:'${ctx}/sys/dict/list/data',
	    		queryParams: {
	    			parentId:'<%=StringConstant.ROOT_ID_32%>'
	    		}
	    	});
	    });
	    
	    function selectData() {
		    var sel_parentId = $("#sel_parentId").val();
		    var sel_value = $("#sel_value").val();
		    var sel_label = $("#sel_label").val();
		    var sel_category = $("#sel_category").val();
		    var sel_description = $("#sel_description").val();
        	$('#dataList').datagrid('load',{
        		parentId:sel_parentId,
    		    value:sel_value,
    		    label:sel_label,
    		    category:sel_category,
    		    description:sel_description
        	});
        }
	    function reloadData() {
        	selectData();
        	$('#dictTree').tree('reload');
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
            url = '${ctx}/sys/dict/form';
        	layer.open({
        	    type: 2,
        	    title: '系统字典基本信息',
        	    area: ['310px', '345px'],
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
						url: '${ctx}/sys/dict/delete',
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

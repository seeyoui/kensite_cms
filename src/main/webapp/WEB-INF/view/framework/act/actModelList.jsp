<%@ page import="com.seeyoui.kensite.common.constants.StringConstant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/taglib/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>    
    <title>流程模型</title>
	<%@ include file="/WEB-INF/view/taglib/header.jsp" %>
	<%@ include file="/WEB-INF/view/taglib/easyui.jsp" %>
	<%@ include file="/WEB-INF/view/taglib/layer.jsp" %>
  </head>
  <body>
  
  	<div style="position:absolute;top:0px;left:0px;right:0px;bottom:0px;">
		<div style="position:absolute;top:0px;left:0px;right:0px;bottom:0px;">
		    <table id="dataList" title="" class="easyui-datagrid" style="width:100%;height:100%"
		    		url="${ctx}/actModel/list/data"
		            toolbar="#toolbar" pagination="true"
		            rownumbers="true" fitColumns="true" singleSelect="true">
		        <thead>
		            <tr>
					    <th field="id" width="100px" hidden>模型ID</th>
					    <th field="category" width="100px">流程分类</th>
					    <th field="key" width="100px">模型标识</th>
					    <th field="name" width="100px">模型名称</th>
					    <th field="version" width="50px" align="right">版本号</th>
					    <th field="createTime" width="100px" formatter="formatDateTimeCol">创建时间</th>
					    <th field="lastUpdateTime" width="100px" formatter="formatDateTimeCol">最后更新时间</th>
		            </tr>
		        </thead>
		    </table>
		    <div id="toolbar">
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newInfo()">新建</a>
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editInfo()">修改</a>
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyInfo()">删除</a>
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="deploy()">部署</a>
		    </div>
		    <div id="dataWin" class="easyui-window" title="流程模型信息维护" data-options="modal:true,closed:true,iconCls:'icon-save',resizable:false" style="width:400px;height:260px;padding:10px;">
		        <div class="ftitle">流程模型信息维护</div>
		        <form id="dataForm" method="post" enctype="multipart/form-data">
					<div class="fitem">
		                <label>流程分类</label>
		                <input id="category" name="category" class="easyui-textbox" data-options="required:true"/>
		            </div>
					<div class="fitem">
		                <label>模型标识</label>
		                <input id="key" name="key" class="easyui-textbox" data-options="required:true"/>
		            </div>
					<div class="fitem">
		                <label>模型名称</label>
		                <input id="name" name="name" class="easyui-textbox" data-options="required:true"/>
		            </div>
					<div class="fitem">
		                <label>描述</label>
		                <input id="description" name="description" class="easyui-textbox" data-options="required:true"/>
		            </div>
				</form>
				
			    <div id="dataWin-buttons">
			        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveInfo()" style="width:90px">保存</a>
			        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dataWin').window('close')" style="width:90px">取消</a>
			    </div>
		    </div>
	    </div>
    </div>
    <script type="text/javascript">
	    $(document).ready(function() {
	    });
	    
	    function selectData() {
		    var sel_name = $("#sel_name").val();
        	$('#dataList').datagrid('load',{
    		    name:sel_name
        	});
        }
	    
	    function reloadData() {
        	selectData();
        }
        var url;
        function newInfo() {
            $('#dataWin').window('open');
            $('#dataForm').form('clear');
            url = '${ctx}/actModel/save';
        }
        function editInfo() {
            var row = $('#dataList').datagrid('getSelected');
            if (row) {
                window.open('${ctx}/act/modeler.jsp?modelId='+row.id);
            }    	
        }
        var loadi;
        function saveInfo() {
            $('#dataForm').form('submit',{
                url: url,
                onSubmit: function(param) {
                	if($(this).form('validate')) {
                		loadi = layer.load(2, {shade: layerLoadShade,time: layerLoadMaxTime});
                	}
                    return $(this).form('validate');
                },
                success: function(info) {
                    if (info==TRUE) {
                        layer.msg("操作成功！", {offset: 'rb',icon: 6,shift: 8,time: layerMsgTime});
                    } else {
	                    layer.msg("操作失败！", {offset: 'rb',icon: 5,shift: 8,time: layerMsgTime});
                    }
                	layer.close(loadi);
                	$('#dataWin').window('close'); 
                	reloadData();
                }
            });
        }
        function deploy() {
        	var row = $('#dataList').datagrid('getSelected');
            if (row) {
            	layer.confirm('你确定部署该记录吗？', {
					btn: ['确定','取消'] //按钮
				}, function() {
					$.ajax({
						type: "post",
						url: "${ctx}/actModel/deploy",
						data: {id:row.id},
						dataType: 'text',
						beforeSend: function(XMLHttpRequest) {
							loadi = layer.load(2, {shade: layerLoadShade,time: layerLoadMaxTime});
						},
						success: function(data, textStatus) {
		                	layer.close(loadi);
		                    layer.msg(data, {time: layerMsgTime});
							reloadData();
						}
					});
				}, function() {
				});
            }
        }
        function destroyInfo() {
            var row = $('#dataList').datagrid('getSelected');
            if (row) {
            	layer.confirm('你确定删除该记录吗？', {
					btn: ['确定','取消'] //按钮
				}, function() {
					$.ajax({
						type: "post",
						url: "${ctx}/actModel/delete",
						data: {id : row.id},
						dataType: 'text',
						beforeSend: function(XMLHttpRequest) {
							loadi = layer.load(2, {shade: layerLoadShade,time: layerLoadMaxTime});
						},
						success: function(data, textStatus) {
		                	layer.close(loadi);
							if (data==TRUE) {
		                        layer.msg("操作成功！", {offset: 'rb',icon: 6,shift: 8,time: layerMsgTime});
		                    } else {
			                    layer.msg("操作失败！", {offset: 'rb',icon: 5,shift: 8,time: layerMsgTime});
		                    }
							reloadData();
						}
					});
				}, function() {
				});
            }
        }
    </script>
  </body>
</html>

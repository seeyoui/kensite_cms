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
					    <th field="category" width="100px" formatter="formatCategory">流程分类</th>
					    <th field="key" width="100px">模型标识</th>
					    <th field="name" width="100px">模型名称</th>
					    <th field="version" width="50px" align="right">版本号</th>
					    <th field="createTime" width="100px" formatter="formatDateTimeCol">创建时间</th>
					    <th field="lastUpdateTime" width="100px" formatter="formatDateTimeCol">最后更新时间</th>
		            </tr>
		        </thead>
		    </table>
		    <div id="toolbar">
		        <a href="javascript:void(0)" class="easyui-linkbutton info" iconCls="icon-add" plain="true" onclick="newInfo()">新建</a>
		        <a href="javascript:void(0)" class="easyui-linkbutton warning" iconCls="icon-edit" plain="true" onclick="editInfo()">在线设计</a>
		        <a href="javascript:void(0)" class="easyui-linkbutton error" iconCls="icon-remove" plain="true" onclick="destroyInfo()">删除</a>
		        <a href="javascript:void(0)" class="easyui-linkbutton primary" iconCls="icon-edit" plain="true" onclick="deploy()">部署</a>
		        <input id="sel_category" name="sel_category" class="easyui-combobox" data-options="valueField:'value',textField:'label',editable:false,panelHeight:'auto',icons: [{iconCls:'icon-clear',handler: function(e){$(e.data.target).combobox('clear');}}],url:'${ctx}/sys/dict/cache/json?category=act_type'">
		        <a href="javascript:void(0)" class="easyui-linkbutton success" iconCls="icon-search" plain="true" onclick="selectData()">查询</a>
		    </div>
	    </div>
    </div>
    <script type="text/javascript">
	    $(document).ready(function() {
	    });
	    
		var categoryDict = ${ksfn:toJson(ksfn:getDictList('act_type'))};
	    function formatCategory(value, row, index) {
    		for(var i=0; i<categoryDict.length; i++) {
    			if(value == categoryDict[i].value) {
    				return categoryDict[i].label;
    			}
    		}
	    	return '';
	    }
	    
	    function selectData() {
		    var sel_category = $('#sel_category').combobox('getValue');
        	$('#dataList').datagrid('load',{
    		    category: sel_category
        	});
        }
	    
	    function reloadData() {
        	selectData();
        }
		var url, loadi;
		var iframeWin = null, iframeBody=null;
        function newInfo() {
            layerOpen();
        }
        function editInfo() {
            var row = $('#dataList').datagrid('getSelected');
            if (row) {
                window.open('${ctx}/act/modeler.jsp?modelId='+row.id);
            }    	
        }
        function layerOpen() {
        	url = '${ctx}/actModel/form';
			layer.open({
				type: 2,
				title: '流程模型基本信息',
				area: ['400px', '260px'],
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
		                    layer.msg(data, {icon: 6,time: layerMsgTime});
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

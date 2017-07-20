<%@ page import="com.seeyoui.kensite.common.constants.StringConstant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/taglib/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>    
    <title>待办任务</title>
	<%@ include file="/WEB-INF/view/taglib/header.jsp" %>
	<%@ include file="/WEB-INF/view/taglib/easyui.jsp" %>
	<%@ include file="/WEB-INF/view/taglib/layer.jsp" %>
  </head>
  <body>
  
  	<div style="position:absolute;top:0px;left:0px;right:0px;bottom:0px;">
		<div style="position:absolute;top:0px;left:0px;right:0px;bottom:0px;">
		    <table id="dataList" title="" class="easyui-datagrid" style="width:100%;height:100%"
		    		url="${ctx}/actProcess/list/data"
		            toolbar="#toolbar" pagination="true"
		            rownumbers="true" fitColumns="true" singleSelect="true">
		        <thead>
		            <tr>
					    <th field="id" width="100px">执行ID</th>
					    <th field="category" width="100px" formatter="formatCategory">流程分类</th>
					    <th field="key" width="100px">流程标识</th>
					    <th field="name" width="100px">流程名称</th>
					    <th field="diagramResourceName" width="100px" formatter="formatProcessImg">流程图</th>
					    <th field="version" width="100px">流程版本</th>
					    <th field="deploymentTime" width="100px" formatter="formatDateTimeCol">发布时间</th>
					    <th field="operate" width="200px" formatter="formatOperate">操作</th>
		            </tr>
		        </thead>
		    </table>
		    <div id="toolbar">
		        <input id="sel_category" name="sel_category" class="easyui-combobox" data-options="valueField:'value',textField:'label',editable:false,panelHeight:'auto',icons: [{iconCls:'icon-clear',handler: function(e){$(e.data.target).combobox('clear');}}],url:'${ctx}/sys/dict/cache/json?category=act_type'">
		        <a href="javascript:void(0)" class="easyui-linkbutton success" iconCls="icon-search" plain="true" onclick="selectData()">查询</a>
		    </div>
	    </div>
    </div>
    <script type="text/javascript">
        function formatOperate(val, row){
        	return '<a class="start" href="${ctx}/actTask/form?procDefId=' + row.id + '">【启动流程】</a>';
        }
		var categoryDict = ${ksfn:toJson(ksfn:getDictList('act_type'))};
	    function formatCategory(value, row, index) {
    		for(var i=0; i<categoryDict.length; i++) {
    			if(value == categoryDict[i].value) {
    				return categoryDict[i].label;
    			}
    		}
	    	return '';
	    }
	    function formatProcessImg(val, row){
    		var imgstr = '<a target="_blank" href="${ctx}/actProcess/resource?procDefId='+row.id+'&resType=image">'+val+'</a>';
            return imgstr;
        }
    	
	    $(document).ready(function(){
	    });
	    
	    function selectData() {
		    var sel_category = $('#sel_category').combobox('getValue');
        	$('#dataList').datagrid('load',{
    		    category: sel_category
        	});
        }
	    
	    function reloadData() {
        	selectData();
        }
	    
    </script>
  </body>
</html>

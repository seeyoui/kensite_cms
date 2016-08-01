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
	<%@ include file="/WEB-INF/view/taglib/uedit.jsp" %>
  </head>
  <body>
 	<div style="position:absolute;top:10px;left:20px;right:20px;bottom:10px;">
        <form id="dataForm" method="post">
			<div class="fitem">
                <label>部门名称</label>
                <input id="name" name="name" class="easyui-textbox" data-options="required:true,tipPosition:'top'"/>
            </div>
			<div class="fitem">
                <label>部门编号</label>
                <input id="code" name="code" class="easyui-textbox" data-options="required:true,tipPosition:'top'"/>
                <span id="msg-code" class="err-msg"></span>
            </div>
            <div class="fitem">
                <label>上级部门</label>
                <input id="parentId" name="parentId" class="easyui-combotree" data-options="required:true,tipPosition:'top',idFiled:'id',textField:'name',parentField:'parentId'" style="width:160px;" url="${ctx}/sysDepartment/list/all"/>
            </div>
            <div class="fitem">
                <label>排序</label>
                <input id="sequence" name="sequence" class="easyui-numberbox textbox" data-options="min:0,max:999999,precision:0,required:true,tipPosition:'top'"/>
                <span id="msg-sequence" class="err-msg"></span>
            </div>
            <input id="id" name="id" type="hidden"/>
		</form>
    </div>
    <script type="text/javascript">
	    var loadi,url,index = parent.layer.getFrameIndex(window.name);
	    $(document).ready(function(){
	        var row = parent.$('#dataList').datagrid('getSelected');
	        url = '${ctx}/sysDepartment/save';
	        $('#parentId').combotree({
	        	onLoadSuccess: function() {
			        $('#parentId').combotree('setValue', parent.$('#sel_parentId').val());
	        	}
	        });
	        if(row != null) {
	            $('#dataForm').form('load', row);
	    		url = '${ctx}/sysDepartment/update';
	        }
	    });
	
	    function submitInfo(){
	        $('#dataForm').form('submit',{
	            url: url,
	            onSubmit: function(param){
	            	if($(this).form('validate')) {
	            		loadi = parent.layer.load(2, {shade: layerLoadShade,time: layerLoadMaxTime});
	            	}
	                return $(this).form('validate');
	            },
	            success: function(data){
	            	parent.layer.close(loadi);
	                cleanErrMsg();
	                var data = eval('(' + data + ')');
	                if (data.success==TRUE){
	            		parent.reloadData();
	                	parent.layer.msg("操作成功！", {offset: 'rb',icon: 6,shift: 8,time: layerMsgTime});
	            		parent.layer.close(index);
	                } else {
	                    renderErrMsg(data.message);
	                }
	            }
	        });
	    }
    </script>
  </body>
</html>

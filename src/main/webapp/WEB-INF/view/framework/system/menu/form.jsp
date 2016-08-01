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
                <label>名称</label>
                <input id="name" name="name" class="easyui-textbox" data-options="required:true,tipPosition:'top'"/>
                <label>上级菜单</label>
                <input id="parentId" name="parentId" class="easyui-combotree" data-options="required:true,tipPosition:'top'" url="${ctx}/sysMenu/tree"/>
            </div>
			<div class="fitem">
                <label>URL</label>
                <input id="url" name="url" class="easyui-textbox" data-options="required:true,tipPosition:'top'"/>
                <label>排序</label>
                <input id="sequence" name="sequence" class="easyui-numberbox" data-options="min:0,max:999999,precision:0,required:true,tipPosition:'top'"/>
            </div>
			<div class="fitem">
                <label>打开方式</label>
                <input id="target" name="target" class="easyui-combobox" data-options="required:true,tipPosition:'top',editable:false,valueField: 'value',textField: 'label',data: [{value: '_blank',label: 'BLANK'},{value: '_parent',label: 'PARENT'},{value: '_self',label: 'SELF'},{value: '_top',label: 'TOP'}],panelHeight:'auto'"/>
                <label>图标</label>
                <input id="icon" name="icon" class="easyui-textbox" data-options=""/>
            </div>
			<%@ include file="/WEB-INF/view/skins/bootstrap/icon.jsp" %>
            <input id="id" name="id" type="hidden"/>
		</form>
    </div>
    <script type="text/javascript">
	    var loadi,url,index = parent.layer.getFrameIndex(window.name);
	    $(document).ready(function(){
	        var row = parent.$('#dataList').datagrid('getSelected');
	        url = '${ctx}/sysMenu/save';
	        $('#parentId').combotree({
	        	onLoadSuccess: function() {
			        $('#parentId').combotree('setValue', parent.$('#sel_parentId').val());
	        	}
	        });
	        if(row != null) {
	            $('#dataForm').form('load', row);
	    		url = '${ctx}/sysMenu/update';
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

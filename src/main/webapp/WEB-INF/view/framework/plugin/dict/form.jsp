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
                <label>父级字典</label>
                <input id="parentId" name="parentId" class="easyui-combotree" data-options="required:true,tipPosition:'top'" style="width:160px;" url="${ctx}/sys/dict/tree"/>
                <span id="msg-parentId" class="err-msg"></span>
            </div>
		    <div class="fitem">
                <label>数据值</label>
                <input id="value" name="value" class="easyui-textbox" data-options="required:true,tipPosition:'top'"/>
                <span id="msg-value" class="err-msg"></span>
            </div>
			<div class="fitem">
                <label>标签名</label>
                <input id="label" name="label" class="easyui-textbox" data-options="required:true,tipPosition:'top'"/>
                <span id="msg-label" class="err-msg"></span>
            </div>
			<div class="fitem">
                <label>分类</label>
                <input id="category" name="category" class="easyui-textbox" data-options="required:true,tipPosition:'top'"/>
                <span id="msg-category" class="err-msg"></span>
            </div>
			<div class="fitem">
                <label>描述</label>
                <input id="description" name="description" class="easyui-textbox" data-options=""/>
                <span id="msg-description" class="err-msg"></span>
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
	        url = '${ctx}/sys/dict/save';
	        $('#parentId').combotree({
	        	onLoadSuccess: function() {
			        $('#parentId').combotree('setValue', parent.$('#sel_parentId').val());
	        	}
	        });
	        var row = parent.$('#dataList').datagrid('getSelected');
	        if(row != null) {
	            $('#dataForm').form('load', row);
	    		url = '${ctx}/sys/dict/update';
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

<%@ page import="com.seeyoui.kensite.common.constants.StringConstant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/taglib/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>    
    <title>接口试用</title>
	<%@ include file="/WEB-INF/view/taglib/header.jsp" %>
	<%@ include file="/WEB-INF/view/taglib/easyui.jsp" %>
	<%@ include file="/WEB-INF/view/taglib/layer.jsp" %>
  </head>
<body>
	<div style="position:absolute;top:10px;left:20px;right:20px;bottom:10px;">
		<form id="dataForm" method="post">
		    <div class="fitem">
                <label>接口URL</label>
                <input id="url" name="url" class="easyui-textbox" data-options="readonly:true" style="width:400px;"/>
            </div>
		    <div class="fitem">
                <label>接口METHOD</label>
                <input id="method" name="method" class="easyui-textbox" data-options="readonly:true" style="width:70px;"/>
            </div>
            <div id="paramPanel" class="easyui-panel" title="参数" style="width:485px;height:230px;padding:10px;">
            	<input id="param" name="param" class="easyui-textbox" data-options="multiline:true,prompt:'请输入符合json格式的数据'" value="" style="width:462px;height:170px"/>
			</div>
		</form>
	</div>
	<script type="text/javascript">
    var loadi,url,index = parent.layer.getFrameIndex(window.name);
    var paramRows = null;
    $(document).ready(function(){
        var row = parent.$('#dataListSub').datagrid('getSelected');
        if(row != null) {
			$('#url').textbox('setValue', row.url);
			$('#method').textbox('setValue', row.method);
        }
    });
	
    function submitInfo(){
    	var url = $('#url').textbox('getValue');
    	var method = $('#method').textbox('getValue');
		var jsonStr = $('#param').textbox('getValue');
		try {
			JSON.parse(jsonStr);
		} catch (err) {
			showMessage('JSON格式不正确');
			return;
        }
    	$.ajax({
			type: method,
			url: '${ctx}'+url,
			data: jsonStr,
			dataType: 'text',
			timeout: layerLoadMaxTime,
		    contentType : 'application/json;charset=utf-8', //设置请求头信息
			beforeSend: function(XMLHttpRequest){
				loadi = parent.layer.load(2, {shade: layerLoadShade,time: layerLoadMaxTime});
			},
			success: function(data, textStatus){
				parent.layer.close(loadi);
				if(data.indexOf('"') == 0) {
					data = data.substring(1);
					data = data.substring(0, data.length-1);
					while(data.indexOf('\\') != -1) {
						data = data.replace('\\', '');
					}
				}
				showMessage(do_json_beautify(data, 'html'));
			},
			error: function(request, errType) {
				parent.layer.close(loadi);
				//"timeout", "error", "notmodified" 和 "parsererror"
				if(errType == 'timeout') {
					showMessage('请求超时，请重新发送');
				}
				if(errType == 'error') {
					showMessage('系统错误，请联系管理员');
				}
			}
		});
    }
    
    function showMessage(message) {
    	parent.layer.open({
    		title: '返回值',
    	    type: 1,
    	    skin: 'layui-layer-demo', //样式类名
    	    closeBtn: 0, //不显示关闭按钮
    	    shift: 2,
    	    area: ['550px', '550px'], //宽高
    	    shadeClose: true, //开启遮罩关闭
    	    content: message
    	});
    }
	</script>
</body>
</html>
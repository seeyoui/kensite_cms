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
			</div>
		</form>
	</div>
	<script type="text/javascript">
    var loadi,url,index = parent.layer.getFrameIndex(window.name);
    var paramRows = null;
    $(document).ready(function(){
    	var escapeVal = encodeURI("中国");
        var UnescapeVal = decodeURI(escapeVal);
        console.info(escapeVal);
        console.info(UnescapeVal);
        var row = parent.$('#dataListSub').datagrid('getSelected');
        if(row != null) {
			$('#url').textbox('setValue', row.url);
			$('#method').textbox('setValue', row.method);
			if(row.parameter != null) {
				var paramJson = JSON.parse(row.parameter);
				paramRows = paramJson.rows;
				for(var i=0; i<paramRows.length; i++) {
					var pr = paramRows[i];
					var htmlContent = '<div class="fitem"><label>'+pr.describ+'</label><input id="'+pr.param+i+'" class="easyui-textbox"/>&nbsp;&nbsp;<span>'+pr.remarks+'</span></div>';
					$('#paramPanel').append(htmlContent);
					$('#'+pr.param+i).textbox({});
				}
			}
        }
    });
	
    function submitInfo(){
    	var url = $('#url').textbox('getValue');
    	var method = $('#method').textbox('getValue');
		var jsonStr = '{';
		for(var i=0; i<paramRows.length; i++) {
			var pr = paramRows[i];
			var key = pr.param;
			var value = $('#'+pr.param+i).textbox('getValue');
			if(value == null || value == '') {
				continue;
			}
			jsonStr += ('"'+key+'":"'+value+'",');
			url = url.replace('{'+key+'}', value);
		}
		if(jsonStr.indexOf(',') != -1) {
			jsonStr = jsonStr.substring(0, jsonStr.length-1)
		}
		jsonStr += '}';
    	$.ajax({
			type: method,
			url: '${ctx}'+url,
			data: JSON.parse(jsonStr),
			dataType: 'text',
			timeout: layerLoadMaxTime,
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
				if(data.indexOf('{') != -1) {
					showMessage(do_json_beautify(data, 'html'));
				} else {
					var message = decodeURI(data);
					while(message.indexOf('%2C') != -1) {
						message = message.replace('%2C', ',');
					}
					while(message.indexOf('%3A') != -1) {
						message = message.replace('%3A', ':');
					}
					showMessage(do_json_beautify(message, 'html'));
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
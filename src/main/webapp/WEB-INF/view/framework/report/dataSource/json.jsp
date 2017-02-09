<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/taglib/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>数据源</title>
		<%@ include file="/WEB-INF/view/taglib/header.jsp" %>
		<%@ include file="/WEB-INF/view/taglib/easyui.jsp" %>
		<%@ include file="/WEB-INF/view/taglib/layer.jsp" %>
		<%@ include file="/WEB-INF/view/taglib/uedit.jsp" %>
		<%@ include file="/WEB-INF/view/taglib/codemirror.jsp" %>
	</head>
	<body>
	 	<div style="position:absolute;top:10px;left:20px;right:20px;bottom:10px;">
			<form id="dataForm" method="post">
				<div class="fitem">
					<ks:formTag table="KS_DATA_SOURCE" column="REMARKS"/>
				</div>
				<div class="fitem">
					<ks:formTag table="KS_DATA_SOURCE" column="NAME"/>
					<ks:formTag table="KS_DATA_SOURCE" column="CODE_NUM"/>
					<ks:formTag table="KS_DATA_SOURCE" column="TYPE"/>
				</div>
				<div class="fitem">
					<ks:formTag table="KS_DATA_SOURCE" column="DESCRIBE"/>
				</div>
				<div class="fitem">
					<ks:formTag table="KS_DATA_SOURCE" column="CONTENT"/>
					<input id="content" name="content" type="hidden"/>
				</div>
				<div class="fitem">
					<ks:formTag table="KS_DATA_SOURCE" column="VIEW_SQL"/>
				</div>
<div class="fitem" id="sql_div" style="height: 150px;">
<textarea id="sql" style="display:none;">
[
{"id":123,"name":"张三"},
{"id":321,"name":"李四"},
{"id":456,"name":"王五"}
]
</textarea>
</div>
				<input id="id" name="id" type="hidden"/>
			</form>
		</div>
		<script type="text/javascript">
			var loadi,url,index = parent.layer.getFrameIndex(window.name);
			var editor_sql;
			$(document).ready(function(){
				editor_sql = CodeMirror.fromTextArea(
	   				document.getElementById('sql'),
		   			{
		   				lineNumbers : true,
		   				matchBrackets : true,
		   				theme : 'ambiance',
		   				indentUnit : 4,
		   				styleActiveLine : true,
		   				lineWrapping : true,
		   				mode : 'application/json'
		   			});
				editor_sql.setSize('auto', '100%');
				var row = parent.$('#dataList').datagrid('getSelected');
				url = '${ctx}/ks/dataSource/save';
				if(row != null) {
					$('#dataForm').form('load', row);
					if(row.content != null) {
					}
					url = '${ctx}/ks/dataSource/update';
				} else {
					$('#dataForm').form('load', {'type':'st'});
				}
			});
			
			function submitInfo(){
				var content = editor_sql.getValue();
				if(content == null || content == '') {
					return;
				} else {
					$('#content').val(content);
				}
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
							parent.$.dataSource.reloadData();
							parent.layer.msg("操作成功！", {offset: layerMsgOffset,icon: 6,shift: 8,time: layerMsgTime});
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

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/taglib/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>统计图表</title>
		<%@ include file="/WEB-INF/view/taglib/header.jsp" %>
		<%@ include file="/WEB-INF/view/taglib/easyui.jsp" %>
		<%@ include file="/WEB-INF/view/taglib/layer.jsp" %>
		<%@ include file="/WEB-INF/view/taglib/uedit.jsp" %>
	</head>
	<body>
	 	<div style="position:absolute;top:10px;left:20px;right:20px;bottom:10px;">
			<form id="dataForm" method="post">
				<div class="fitem">
					<ks:formTag table="KS_CHART_ENGINE" column="REMARKS"/>
				</div>
				<div class="fitem">
					<ks:formTag table="KS_CHART_ENGINE" column="DESCRIBE"/>
				</div>
				<div class="fitem">
					<ks:formTag table="KS_CHART_ENGINE" column="TYPE"/>
				</div>
				<div class="fitem">
					<ks:formTag table="KS_CHART_ENGINE" column="SET_OPTION"/>
				</div>
				<div class="fitem">
					<ks:formTag table="KS_CHART_ENGINE" column="Z_SOURCE"/>
				</div>
				<div class="fitem">
					<ks:formTag table="KS_CHART_ENGINE" column="Z_KEY"/>
				</div>
				<div class="fitem">
					<ks:formTag table="KS_CHART_ENGINE" column="Z_VALUE"/>
				</div>
				<div class="fitem">
					<ks:formTag table="KS_CHART_ENGINE" column="X_SOURCE"/>
				</div>
				<div class="fitem">
					<ks:formTag table="KS_CHART_ENGINE" column="X_KEY"/>
				</div>
				<div class="fitem">
					<ks:formTag table="KS_CHART_ENGINE" column="X_Z_KEY"/>
				</div>
				<div class="fitem">
					<ks:formTag table="KS_CHART_ENGINE" column="X_VALUE"/>
				</div>
				<div class="fitem">
					<ks:formTag table="KS_CHART_ENGINE" column="Y_SOURCE"/>
				</div>
				<div class="fitem">
					<ks:formTag table="KS_CHART_ENGINE" column="Y_Z_KEY"/>
				</div>
				<div class="fitem">
					<ks:formTag table="KS_CHART_ENGINE" column="Y_X_KEY"/>
				</div>
				<div class="fitem">
					<ks:formTag table="KS_CHART_ENGINE" column="Y_VALUE"/>
				</div>
				<div class="fitem">
					<ks:formTag table="KS_CHART_ENGINE" column="FUNC"/>
				</div>
				<input id="id" name="id" type="hidden"/>
			</form>
		</div>
		<script type="text/javascript">
			var loadi,url,index = parent.layer.getFrameIndex(window.name);
			$(document).ready(function(){
				var row = parent.$('#dataList').datagrid('getSelected');
				url = '${ctx}/ks/chartEngine/save';
				if(row != null) {
					$('#dataForm').form('load', row);
					url = '${ctx}/ks/chartEngine/update';
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
							parent.$.chartEngine.reloadData();
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

<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/taglib/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>Kensite开发平台</title>
	<%@ include file="/WEB-INF/view/taglib/header.jsp" %>
	<%@ include file="/WEB-INF/view/taglib/layer.jsp" %>
	<%@ include file="/WEB-INF/view/taglib/echarts.jsp"%>
	<%@ include file="/WEB-INF/view/taglib/easyui.jsp"%>
</head>

<body>
	<div>
		<input id="idStr" class="easyui-textbox" data-options="iconCls:'icon-search',value:'00000000000000000000000000000000'" style="width:300px"/>
		<a href="javascript:dept_user_state_lineQueryParam();" class="easyui-linkbutton">查询</a>
	</div>
	<div style="width:500px;height:300px;">
	<ks:chart codeNum="dept_user_state_line"/>
	</div>
	
	<script type="text/javascript">
	$(document).ready(function() {
		dept_user_state_lineQueryParam();
	});
	function getOpt() {
		console.info(dept_user_state_lineChart.getOption());
		console.info($("#idStr").textbox("getValue"));
		dept_user_state_lineChart.setOption({
			series: [
		     	{
		     	},
		     	{
		     		data:[3, 4]
		     	}
		     ]
		});
	}
	</script>
</body>
</html>

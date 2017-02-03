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
		<input id="num" class="easyui-textbox" data-options="iconCls:'icon-search',value:'0'"/>
		<a href="javascript:test();" class="easyui-linkbutton">查询</a>
	</div>
	<div style="width:500px;height:300px;">
	<ks:chart uuid="b0022b5378434294a2584181deaf460b"/>
	</div>
	
	<script type="text/javascript">
	$(document).ready(function() {
		renderChart_b0022b5378434294a2584181deaf460b();
	});
	function test() {
		var num = $('#num').textbox('getValue');
		var xwhere = '';
		var ywhere = ' and hits>'+num;
		var zwhere = '';
		renderChart_b0022b5378434294a2584181deaf460b(xwhere, ywhere, zwhere);
	}
	</script>
</body>
</html>

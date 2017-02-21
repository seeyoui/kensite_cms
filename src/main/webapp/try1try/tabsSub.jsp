<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/taglib/common.jsp"%>
<html>
<head>
<title>Kensite开发平台</title>
<%@ include file="/WEB-INF/view/taglib/header.jsp"%>
<%@ include file="/WEB-INF/view/taglib/easyui.jsp"%>
<%@ include file="/WEB-INF/view/taglib/layer.jsp"%>
<style type="text/css">
</style>
</head>
<body>
<div style="position:absolute;top:0px;left:0px;right:0px;bottom:0px;">
	<a href="javascript:addTab()" class="easyui-linkbutton" data-options="iconCls:'icon-add'">打开新页签</a>
</div>
</body>
<script type="text/javascript">
	$(document).ready( $(function () {
	}));
	
	function addTab() {
		parent.addTab('789', '456', '${ctx }/try1try/tabsSub.jsp');
	}
</script>
</html>

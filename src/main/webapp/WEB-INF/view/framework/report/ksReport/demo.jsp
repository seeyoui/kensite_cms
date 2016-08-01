<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/taglib/common.jsp"%>
<html>
<head>
<title>Kensite开发平台</title>
<%@ include file="/WEB-INF/view/taglib/header.jsp"%>
<%@ include file="/WEB-INF/view/taglib/easyui.jsp"%>
<%@ include file="/WEB-INF/view/taglib/layer.jsp"%>
<%@ include file="/WEB-INF/view/taglib/ksReport.jsp"%>
<style type="text/css">
</style>
</head>
<body>
	<div id="KSreport"
		style="position: absolute; top: 0px; bottom: 0px; left: 0px; right: 0px; border: 1px solid gray"></div>
</body>
<script type="text/javascript">
	$(document).ready( $(function () {
		// SpreadJS 初始化
		$("#KSreport").wijspread({
			sheetCount: 1, //初始化为1个标签页
			newTabVisible: false, //隐藏新建标签页按钮
			AllowDragFill: false, //单元格拖动填充
			tabEditable: false
		});
		if ($.browser.msie && parseInt($.browser.version, 10) < 9) {
			//run for ie7/8
			var spread = $("#KSreport").wijspread("spread");
			spread.bind("SpreadsheetObjectLoaded", function () {
				initSpread();
			});
		} else {
			initSpread();
		}
	})
	);
	
	function initSpread() {
		var spread = $("#KSreport").wijspread("spread");
		var sheet = spread.getActiveSheet();
		sheet.setName("KSReport");
		sheet.setRowCount(30, $.wijmo.wijspread.SheetArea.viewport);
		sheet.setColumnCount(20, $.wijmo.wijspread.SheetArea.viewport);
        sheet.isPaintSuspended(true); 
        var source = [
		{
			LastName : "Freehafer",
			FirstName : "Nancy",
			Title : "Sales Representative",
			Phone : "(123)555-0100"
		}, {
			LastName : "Cencini",
			FirstName : "Andrew",
			Title : "Vice President, Sales",
			Phone : "(123)555-0101"
		}, {
			LastName : "Kotas",
			FirstName : "Jan",
			Title : "Sales Representative",
			Phone : "(123)555-0102"
		}, {
			LastName : "Sergienko",
			FirstName : "Mariya",
			Title : "Sales Representative",
			Phone : "(123)555-0103"
		} ];

        sheet.addTableByDataSource("Table1", 1, 1, source);
		sheet.isPaintSuspended(false);
	}
</script>
</html>

<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/taglib/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>Kensite开发平台</title>
	<%@ include file="/WEB-INF/view/taglib/header.jsp" %>
	<%@ include file="/WEB-INF/view/taglib/layer.jsp" %>
	<%@ include file="/WEB-INF/view/taglib/bootstrap.jsp" %>
	<%@ include file="/WEB-INF/view/taglib/codemirror.jsp" %>
	<%@ include file="/WEB-INF/view/taglib/echarts.jsp"%>
	<%@ include file="/WEB-INF/view/taglib/easyui.jsp"%>
	<link rel="stylesheet" href="${ctx_bootstrap}/load/blueHeart.css" type="text/css"/>
	<style type="text/css">
		.btn-default {
			background-color: #3fa7dc;
			border: medium none;
			border-radius: 0;
			color: #fff;
			height: 30px;
			margin-left: 10px;
			margin-top: 5px;
			width: 50px;
		}
		.btn-default:hover {
			background-color: #277eab;
		}
		
		.btn-primary {
			background-color: #18a689;
			border: medium none;
			border-radius: 0;
			color: #fff;
			height: 30px;
			margin-left: 10px;
			margin-top: 5px;
			width: 50px;
		}
		.btn-primary:hover {
			background-color: #339966;
		}
	</style>
</head>

<body>
<!-- 加载遮罩开始 -->
<div id="cssload-loader">
	<div class="cssload-dot"></div>
	<div class="cssload-dot"></div>
	<div class="cssload-dot"></div>
	<div class="cssload-dot"></div>
	<div class="cssload-dot"></div>
	<div class="cssload-dot"></div>
	<div class="cssload-dot"></div>
	<div class="cssload-dot"></div>
</div>
<div class="cssload-loader-shadow"></div>
<!-- 加载遮罩结束 -->
<div style="position: absolute;top: 0px;bottom: 0px;right: 0px;left: 0px;">
<div style="position: absolute;top: 0px;height: 40px;right: 0px;left: 0px;background-color: #f3f3f3;">
<a class="btn btn-primary btn-sm" onclick="saveData()" href="javascript:;">保存</a>
<a class="btn btn-default btn-sm" onclick="disposeAndRun()" href="javascript:;">运行</a>
<a class="btn btn-default btn-sm" onclick="resetData()" href="javascript:;">重置</a>
</div>
<div style="position: absolute;top: 40px;bottom: 0px;width: 300px;left: 0px;">
<textarea id="option">
${chartConfig.chartOption }
</textarea>
</div>

<div style="position: absolute;top: 40px;bottom: 0px;width: 300px;left: 300px;">
<textarea id="series">
${chartConfig.seriesOption }
</textarea>
</div>
<div style="position: absolute;top: 40px;bottom: 300px;right: 400px;left: 600px;">
<textarea id="xsql">
${chartConfig.sqlx }
</textarea>
<textarea id="zsql">
${chartConfig.sqlz }
</textarea>
<textarea id="ysql">
${chartConfig.sqly }
</textarea>
</div>
<div style="position: absolute;top: 40px;bottom: 300px;right: 0px;width: 400px;">
<textarea id="func">
${chartConfig.func }
</textarea>
</div>
<div style="position: absolute;height: 300px;bottom: 0px;right: 0px;left: 600px;">
<div id="chartMain" style="height: 100%"></div>
</div>
</div>
	<script type="text/javascript">
	var myChart = null;
	var echartObj = null;
	var option = null;
	var seriesOption = null;
	var editor_option,editor_option,editor_xsql,editor_zsql,editor_ysql;
	$(document).ready(function(){
		editor_option = CodeMirror.fromTextArea(
   			document.getElementById("option"),
   			{
   				lineNumbers : true,
   				matchBrackets : true,
   				theme : "ambiance",
   				indentUnit : 4,
   				styleActiveLine : true,
   				lineWrapping : true
   			});
		editor_series = CodeMirror.fromTextArea(
	   			document.getElementById("series"),
	   			{
	   				lineNumbers : true,
	   				matchBrackets : true,
	   				theme : "ambiance",
	   				indentUnit : 4,
	   				styleActiveLine : true,
	   				lineWrapping : true
	   			});
		editor_xsql = CodeMirror.fromTextArea(
	   			document.getElementById("xsql"),
	   			{
	   				lineNumbers : true,
	   				matchBrackets : true,
	   				extraKeys: {"Ctrl": "autocomplete"},
	   				theme : "ambiance",
	   				indentUnit : 4,
	   				mode: {name: "text/x-mysql"},
	   				styleActiveLine : true,
	   				lineWrapping : true
	   			});
		editor_zsql = CodeMirror.fromTextArea(
	   			document.getElementById("zsql"),
	   			{
	   				lineNumbers : true,
	   				matchBrackets : true,
	   				extraKeys: {"Ctrl": "autocomplete"},
	   				theme : "ambiance",
	   				indentUnit : 4,
	   				mode: {name: "text/x-mysql"},
	   				styleActiveLine : true,
	   				lineWrapping : true
	   			});
		editor_ysql = CodeMirror.fromTextArea(
	   			document.getElementById("ysql"),
	   			{
	   				lineNumbers : true,
	   				matchBrackets : true,
	   				extraKeys: {"Ctrl": "autocomplete"},
	   				theme : "ambiance",
	   				indentUnit : 4,
	   				mode: {name: "text/x-mysql"},
	   				styleActiveLine : true,
	   				lineWrapping : true
	   			});
		editor_func = CodeMirror.fromTextArea(
	   			document.getElementById("func"),
	   			{
	   				lineNumbers : true,
	   				matchBrackets : true,
	   				theme : "ambiance",
	   				indentUnit : 4,
	   				styleActiveLine : true,
	   				lineWrapping : true
	   			});
		editor_option.setSize('auto', '100%');
		editor_series.setSize('auto', '100%');
		
		editor_xsql.setSize('auto', '30%');
		editor_zsql.setSize('auto', '30%');
		editor_ysql.setSize('auto', '40%');

		editor_func.setSize('auto', '100%');
		setTimeout("hideLoad()", 500);
	});
	
	function resetData() {
		str = $.ajax({url:"${ctx_static}/report/chart/${chartConfig.type }.ks",async:false});
		eval(str.responseText);
		return;
	}
	
	function hideLoad() {
		$('#cssload-loader').hide();
		$('.cssload-loader-shadow').hide();
	}
	
	function disposeAndRun() {
		eval(editor_option.getValue());
		eval(editor_series.getValue());
		
		myChart = echarts.init(document.getElementById('chartMain'), 'macarons');
		getChartData();
	}
	
	function getChartData() {
		var sqlx = editor_xsql.getValue();
		var sqly = editor_ysql.getValue();
		var sqlz = editor_zsql.getValue();
		if(option==null || sqlx==null || sqlx=='' || sqly==null || sqly=='' || sqlz==null || sqlz=='') {
			layer.msg('请检查数据项', {offset: 'rb',icon: 6,shift: 8,time: layerMsgTime});
			return;
		}
		var func = editor_func.getValue();
		eval(func);
		$.ajax({
			type: "post",
			url: '${ctx}/ks/chart/${chartConfig.type }',
			data: {
				sqlx : sqlx,
				sqly : sqly,
				sqlz : sqlz
			},
			dataType: 'json',
			beforeSend: function(XMLHttpRequest){
				loadi = layer.load(2, {shade: layerLoadShade,time: layerLoadMaxTime});
			},
			success: function(data, textStatus){
				layer.close(loadi);
				if(seriesOption) {
					for(var i=0; i<seriesOption.length; i++) {
						for(var j=0; j<data.series.length; j++) {
							if(seriesOption[i].zkey == data.series[j].zkey) {
								$.extend(data.series[j], seriesOption[i]);
							}
						}
					}
				}
				$.extend(option, data);
				myChart.setOption(option);
			}
		});
	}
	
	function saveData() {
		var chartOption = editor_option.getValue();
		var seriesOption = editor_series.getValue();
		
		var sqlx = editor_xsql.getValue();
		var sqly = editor_ysql.getValue();
		var sqlz = editor_zsql.getValue();

		var func = editor_func.getValue();
		if(chartOption==null || chartOption=='' || seriesOption==null || seriesOption=='' || sqlx==null || sqlx=='' || sqly==null || sqly=='' || sqlz==null || sqlz=='' || func==null || func=='') {
			layer.msg('请检查数据项', {offset: 'rb',icon: 6,shift: 8,time: layerMsgTime});
			return;
		}
		$.ajax({
			type: "post",
			url: '${ctx}/ks/chartConfig/update',
			data: {
				id : '${chartConfig.id }',
				codeNum : '${chartConfig.codeNum }',
				chartOption : chartOption,
				seriesOption : seriesOption,
				sqlx : sqlx,
				sqly : sqly,
				sqlz : sqlz,
				func : func
			},
			dataType: 'json',
			beforeSend: function(XMLHttpRequest){
				loadi = layer.load(2, {shade: layerLoadShade,time: layerLoadMaxTime});
			},
			success: function(data, textStatus){
				layer.close(loadi);
				if (data.success==TRUE){
					layer.msg("操作成功！", {offset: 'rb',icon: 6,shift: 8,time: layerMsgTime});
				} else {
					layer.msg("操作失败！", {offset: 'rb',icon: 5,shift: 8,time: layerMsgTime});
				}
			}
		});
	}
	</script>
</body>
</html>

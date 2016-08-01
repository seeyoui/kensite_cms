<%@ page import="com.seeyoui.kensite.common.constants.StringConstant"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="author" content="seeyoui" />
	<meta name="keywords" content="seeyoui,kensite,ken,site">
	<meta name="description" content="kensite" />
    <link rel='canonical' href='http://www.seeyoui.com'>
	<link rel="shortcut icon" type="image/x-icon" href="${ctx_common}/img/favicon.ico" />
	<script type="text/javascript" src="${ctx_static}/jquery-1.11.1.min.js"></script>
	<script src="${ctx_static}/cookieUtil.js"></script>
	<script src="${ctx_static}/json2.js"></script>
	<script type="text/javascript" src="${ctx_static}/kensite.sys.js"></script>
	<script type="text/javascript" src="${ctx_static}/kensite.info.js"></script>

	<script type="text/javascript" >
		var ctx="${ctx }";
		var TRUE = "<%=StringConstant.TRUE%>";
		var FALSE = "<%=StringConstant.FALSE%>";
		var layerMsgOffset = 'rb';//右下角
		var layerMsgTime = 1500;//消息提示时间
		var layerLoadMaxTime = 10*1000;//加载遮罩最大时间
		var layerLoadShade = [0.5,'#fff'];//加载遮罩背景色和透明度
	</script>
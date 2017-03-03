<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/taglib/common.jsp"%>
<html>
<head>
<title>Kensite开发平台</title>
<%@ include file="/WEB-INF/view/taglib/header.jsp"%>
<%@ include file="/WEB-INF/view/taglib/layui.jsp"%>
<%@ include file="/WEB-INF/view/taglib/layer.jsp"%>
<style type="text/css">
</style>
</head>
<body>
</body>
<script type="text/javascript">
	$(document).ready( $(function () {
		openWin();
	}));
	
	function openWin() {
		index = layer.open({
            type: 2,
            title: '图库',
            area: ['750px', '470px'],
            fix: false, //不固定
            maxmin: false,
            content: '${ctx}/try1try/image.jsp',
            btn: ['保存', '取消'],
            success: function(layero, index){
                iframeBody = layer.getChildFrame('body', index);
                iframeWin = window[layero.find('iframe')[0]['name']];
            },
            yes: function(index, layero) {
                if(iframeWin != null) {
                    iframeWin.submitInfo();
                }
            },
            cancel: function(index){
                layer.close(index);
            }
        });
	}
</script>
</html>

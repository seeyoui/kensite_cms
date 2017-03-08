<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/taglib/common.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<%@ include file="/WEB-INF/view/taglib/header.jsp" %>
	<%@ include file="/WEB-INF/view/taglib/layer.jsp" %>
	<script type="text/javascript">
		//window.location.href="${ctx}/sys/chat/chatRoom";
		/*$(document).ready( $(function () {
			layer.open({
	            type: 2,
	            title: '图库',
	            area: ['750px', '470px'],
	            fix: false, //不固定
	            maxmin: false,
	            content: '${ctx}/cms/filer/image?alowPath=upload/cms',
	            btn: ['保存', '取消'],
	            success: function(layero, index) {
	                iframeBody = layer.getChildFrame('body', index);
	                iframeWin = window[layero.find('iframe')[0]['name']];
	            },
	            yes: function(index, layero) {
	                if(iframeWin != null) {
	                    var file = iframeWin.getSelectFile();
	                    console.info(file);
	                }
	            },
	            cancel: function(index) {
	                layer.close(index);
	            }
	        });
		}));*/
	</script>
</head>
<body>
</body>
</html>
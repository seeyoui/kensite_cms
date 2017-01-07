<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/taglib/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>内容发布文章</title>
		<%@ include file="/WEB-INF/view/taglib/header.jsp" %>
		<%@ include file="/WEB-INF/view/taglib/easyui.jsp" %>
		<%@ include file="/WEB-INF/view/taglib/layer.jsp" %>
		<%@ include file="/WEB-INF/view/taglib/uedit.jsp" %>
	</head>
	<body>
	 	<div style="position:absolute;top:10px;left:20px;right:20px;bottom:10px;">
			<form id="dataForm" method="post">
				<div class="fitem">
					<script id="content" name="content" type="text/plain"></script>
				</div>
				<input id="id" name="id" type="hidden" value="${article.id}"/>
			</form>
		</div>
		<script type="text/javascript">
			var loadi,url,index = parent.layer.getFrameIndex(window.name);
			var content;
			$(document).ready(function(){
				url = '${ctx}/cms/article/updateContent';
				contentEditor = UE.getEditor('content', {
					//autoHeight: true,
					//initialFrameWidth :800,//设置编辑器宽度
					initialFrameHeight: 270,//设置编辑器高度
					scaleEnabled: true
				});
				//设置html编辑器渲染完成后事件，主要是给组件赋值，赋相应数据列值
		        contentEditor.ready(function() {
		        	if('${article.customContentView}' == 'ueditor') {
			        	contentEditor.setContent('${article.content}');
		        	}
		        });
			});
		    
			function submitInfo(){
				var id = $('#id').val();
				var content = contentEditor.getContent();
				$.ajax({
					type: "post",
					url: url,
					data: {
						id: id,
						content: content,
						customContentView: 'ueditor'
					},
					dataType: 'json',
					timeout: layerLoadMaxTime,
					beforeSend: function(XMLHttpRequest){
						loadi = parent.layer.load(2, {shade: layerLoadShade,time: layerLoadMaxTime});
					},
					success: function(data, textStatus){
						parent.layer.close(loadi);
						if (data.success==TRUE){
							parent.$.article.reloadData();
							parent.layer.msg("操作成功！", {offset: layerMsgOffset,icon: 6,shift: 8,time: layerMsgTime});
							parent.layer.close(index);
						} else {
							parent.layer.msg("操作失败！", {offset: 'rb',icon: 5,shift: 8,time: layerMsgTime});
						}
					},
					error: function(request, errType) {
						parent.layer.close(loadi);
						//"timeout", "error", "notmodified" 和 "parsererror"
						if(errType == 'timeout') {
							parent.layer.msg('请求超时', {offset: 'rb',icon: 6,shift: 8,time: layerMsgTime});
						}
						if(errType == 'error') {
							parent.layer.msg('系统错误，请联系管理员', {offset: 'rb',icon: 6,shift: 8,time: layerMsgTime});
						}
					}
				})
			}
		</script>
	</body>
</html>

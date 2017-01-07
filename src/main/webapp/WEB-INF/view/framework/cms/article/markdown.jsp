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
		<%@ include file="/WEB-INF/view/taglib/editormd.jsp" %>
	</head>
	<body>
	 	<div style="position:absolute;top:10px;left:20px;right:20px;bottom:10px;">
			<form id="dataForm" method="post">
				<div class="fitem">
					<div id="content"><textarea style="display:none;">${article.content}</textarea></div>
				</div>
				<input id="id" name="id" type="hidden" value="${article.id}"/>
			</form>
		</div>
		<script type="text/javascript">
			var loadi,url,index = parent.layer.getFrameIndex(window.name);
			var contentEditor;
			$(document).ready(function(){
				url = '${ctx}/cms/article/updateContent';
				contentEditor = editormd("content", {
					width: "100%",
					height: 365,
					path : '${ctx_script}/editormd/lib/',
					theme : "dark",
					previewTheme : "dark",
					editorTheme : "pastel-on-dark",
					codeFold : true,
					//syncScrolling : false,
					saveHTMLToTextarea : true,    // 保存 HTML 到 Textarea
					searchReplace : true,
					//watch : false,                // 关闭实时预览
					htmlDecode : "style,script,iframe|on*",            // 开启 HTML 标签解析，为了安全性，默认不开启    
					//toolbar  : false,             //关闭工具栏
					//previewCodeHighlight : false, // 关闭预览 HTML 的代码块高亮，默认开启
					emoji : true,
					taskList : true,
					tocm            : true,         // Using [TOCM]
					tex : true,                   // 开启科学公式TeX语言支持，默认关闭
					flowChart : true,             // 开启流程图支持，默认关闭
					sequenceDiagram : true,       // 开启时序/序列图支持，默认关闭,
					//dialogLockScreen : false,   // 设置弹出层对话框不锁屏，全局通用，默认为true
					//dialogShowMask : false,     // 设置弹出层对话框显示透明遮罩层，全局通用，默认为true
					//dialogDraggable : false,    // 设置弹出层对话框不可拖动，全局通用，默认为true
					//dialogMaskOpacity : 0.4,    // 设置透明遮罩层的透明度，全局通用，默认值为0.1
					//dialogMaskBgColor : "#000", // 设置透明遮罩层的背景颜色，全局通用，默认为#fff
					imageUpload : true,
					imageFormats : ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
					imageUploadURL : "${ctx}/sys/uploadfile/uploadMarkdown",
					onload : function() {
						//console.log('onload', this);
						//this.fullscreen();
						//this.unwatch();
						//this.watch().fullscreen();

						//this.setMarkdown("#PHP");
						//this.width("100%");
						//this.height(480);
						//this.resize("100%", 640);
					}
				});
			});
		    
			function submitInfo(){
				var id = $('#id').val();
				var content = contentEditor.getMarkdown();
				//var viewConfig = contentEditor.getHTML();
				$.ajax({
					type: "post",
					url: url,
					data: {
						id: id,
						content: content,
						//viewConfig: viewConfig,
						customContentView: 'markdown'
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

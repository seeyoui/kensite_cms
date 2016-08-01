<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/taglib/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>内容发布站点</title>
		<%@ include file="/WEB-INF/view/taglib/header.jsp" %>
		<%@ include file="/WEB-INF/view/taglib/easyui.jsp" %>
		<%@ include file="/WEB-INF/view/taglib/layer.jsp" %>
		<%@ include file="/WEB-INF/view/taglib/uedit.jsp" %>
		<%@ include file="/WEB-INF/view/taglib/uploadify.jsp" %>
		<style type="text/css">
			#uploadify {
				position: absolute;
				top: 85px;
				left: 244px;
			}
			#uploadfileQueue{
				left: 326px;
			    position: absolute;
			    top: 0;
			    width: 160px;
			    z-index: 9999999;
			}
		</style>
	</head>
	<body>
	 	<div style="position:absolute;top:10px;left:20px;right:20px;bottom:10px;">
			<form id="dataForm" method="post">
				<div class="fitem">
					<ks:formTag table="CMS_SITE" column="REMARKS"/>
				</div>
				<div class="fitem">
					<ks:formTag table="CMS_SITE" column="NAME"/>
					<%-- <ks:formTag table="CMS_SITE" column="LOGO"/> --%>
					<label>站点LOGO</label>
	                <input id="logo" type="hidden" name="logo"/>
	                <!-- 自行编写上传附件模块 -->
					<div id="uploadfileQueue"></div>
					<input type="file" id="uploadify" name="uploadify" 
					data-queueid="uploadfileQueue" 
					data-filetypeexts="*.jpg;*.jpge;*.gif;*.png" 
					data-buttontext="选择LOGO" data-auto="true" 
					data-multi="false" data-queuesizelimit="5" 
					data-filesizelimit="5000KB" data-method="POST"
					data-url="cms\logo\" />
	                <img id="logoShow" src="${ctx }/upload/none.jpg"  style="width:162px;height:78px;position:absolute;top:0px;left:325px;"/>
	                <span id="msg-logo" class="err-msg"></span>
				</div>
				<div class="fitem">
					<ks:formTag table="CMS_SITE" column="TITLE"/>
				</div>
				<div class="fitem">
					<ks:formTag table="CMS_SITE" column="DOMAIN"/>
				</div>
				<div class="fitem">
					<ks:formTag table="CMS_SITE" column="THEME"/>
				</div>
				<div class="fitem">
					<ks:formTag table="CMS_SITE" column="DESCRIPTION"/>
				</div>
				<div class="fitem">
					<ks:formTag table="CMS_SITE" column="KEYWORDS"/>
				</div>
				<div class="fitem">
					<ks:formTag table="CMS_SITE" column="COPYRIGHT"/>
				</div>
				<div class="fitem">
					<ks:formTag table="CMS_SITE" column="CUSTOM_VIEW"/>
				</div>
				<input id="id" name="id" type="hidden"/>
			</form>
		</div>
		<script type="text/javascript">
			var loadi,url,index = parent.layer.getFrameIndex(window.name);
			$(document).ready(function(){
				var row = parent.$('#dataList').datagrid('getSelected');
				url = '${ctx}/cms/site/save';
				//添加上传附件组件渲染及设置回调函数
				mineUpload($("#uploadify"), "${ctx}", "afterMineUpload");
				if(row != null) {
					$('#dataForm').form('load', row);
					$("#logoShow").attr("src","${ctx}/"+row.logo);
					url = '${ctx}/cms/site/update';
				}
			});
		    
			//上传附件成功回调函数
		    function afterMineUpload(file, uf, message) {
		    	var fileName =uf.url+ uf.realname;//拼接图片在服务器所在路径字符串
		    	$("#logo").val(fileName);//给隐藏输入框赋值
		    	$("#logoShow").attr("src","${ctx}/"+fileName);//给界面img标签赋值用于显示图片
			}
			
			function submitInfo(){
				$('#dataForm').form('submit',{
					url: url,
					onSubmit: function(param){
						if($(this).form('validate')) {
							loadi = parent.layer.load(2, {shade: layerLoadShade,time: layerLoadMaxTime});
						}
						return $(this).form('validate');
					},
					success: function(data){
						parent.layer.close(loadi);
						cleanErrMsg();
						var data = eval('(' + data + ')');
						if (data.success==TRUE){
							parent.$.site.reloadData();
							parent.layer.msg("操作成功！", {offset: layerMsgOffset,icon: 6,shift: 8,time: layerMsgTime});
							parent.layer.close(index);
						} else {
							renderErrMsg(data.message);
						}
					}
				});
			}
		</script>
	</body>
</html>

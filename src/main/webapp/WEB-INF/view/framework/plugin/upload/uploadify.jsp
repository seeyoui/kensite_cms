<%@ page import="com.seeyoui.kensite.common.constants.StringConstant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/taglib/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>    
    <title>系统附件表</title>
	<%@ include file="/WEB-INF/view/taglib/header.jsp" %>
	<%@ include file="/WEB-INF/view/taglib/easyui.jsp" %>
	<%@ include file="/WEB-INF/view/taglib/layer.jsp" %>
	<%@ include file="/WEB-INF/view/taglib/uploadify.jsp" %>
	<script type="text/javascript">
		//回调函数(文件对象,提示信息)
		function afterMineUpload(file, uf, message) {
			//文件对象为空上传失败，不为空上传成功
			if(file!=null) {
				alert(file.id);
	    		alert(file.type);//文件扩展名
	    		alert(file.size);//文件大小
	    		alert(file.name);//文件名称
			}
			//上传成功且保存数据库则返回主键UUID
			if(uf!=null && uf.id!="") {
				alert(uf.id);
			}
			alert(message);
		}
		function deleteFile() {
			$.ajax({
				type: "post",
				url: '${ctx}/sys/uploadfile/delete.do',
				data: {delDataId:$("#delDataId").val()},
				dataType: 'text',
				beforeSend: function(XMLHttpRequest){
				},
				success: function(data, textStatus){
					if (data=="<%=StringConstant.TRUE%>"){
				    	layer.msg("操作成功！", {offset: 'rb',icon: 6,shift: 8,time: layerMsgTime});
					} else {
						layer.msg("操作失败！", {offset: 'rb',icon: 5,shift: 8,time: layerMsgTime});
					}
					reloadData();
				}
			});
		}
		$(document).ready(function() {
			//上传函数(file input对象,项目根目录,回调函数名默认"afterMineUpload")
			mineUpload($("#uploadify"), "${ctx}", "afterMineUpload");
		});
		</script>
  </head>
  <body>
  	<!--
  	filetypeexts：默认所有文件类型
  	buttontext：默认选择附件
  	auto：默认false
  	multi：默认true
  	queuesizelimit：默认999
  	filesizelimit：默认5M
  	method：默认POST
  	url：temp(服务器保存文件但不存数据库)
  	-->
  	<div id="uploadfileQueue"></div>
	<input type="file" id="uploadify" name="uploadify" data-queueid="uploadfileQueue" 
	data-filetypeexts="*.jpg;*.jpge;*.gif;*.png" 
	data-buttontext="选择附件" data-auto="false" 
	data-multi="false" data-queuesizelimit="5" 
	data-filesizelimit="5000KB" data-method="POST"
	data-url="image\" />
	<a href="javascript:$('#uploadify').uploadify('upload','*')">上传</a>|
	<a href="javascript:$('#uploadify').uploadify('cancel','*')">取消上传</a>
	<br/><br/><br/><br/><br/>
	<input type="text" id="delDataId"/><input type="button" value="delete" onclick="deleteFile()"/>
  </body>
  </html>
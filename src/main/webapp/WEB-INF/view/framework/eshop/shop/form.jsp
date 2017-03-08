<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/taglib/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>商铺</title>
		<%@ include file="/WEB-INF/view/taglib/header.jsp" %>
		<%@ include file="/WEB-INF/view/taglib/layui.jsp" %>
		<%@ include file="/WEB-INF/view/taglib/uedit.jsp" %>
		<%@ include file="/WEB-INF/view/taglib/uploadify.jsp" %>
		<style type="text/css">
			#uploadify {
				position: absolute;
				top: 0px;
				left: 300px;
			}
			#uploadfileQueue{
				left: 300px;
			    position: absolute;
			    top: 25px;
			    width: 295px;
			    z-index: 9999999;
			}
			#logoShow {
				width: 150px;
				height: 150px;
				position: absolute;
				top: 0px;
				left: 410px;
			}
		</style>
	</head>
	<body>
	 	<div style="position:absolute;top:10px;left:20px;right:20px;bottom:10px;">
			<form id="dataForm" method="post" class="layui-form">
				<div class="layui-form-item">
					<div class="layui-inline">
						<ks:formTag table="ES_SHOP" column="NAME" theme="layer"/>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-inline">
						<ks:formTag table="ES_SHOP" column="USER_NAME" theme="layer"/>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-inline">
						<ks:formTag table="ES_SHOP" column="PASS_WORD" theme="layer"/>
					</div>
				</div>
				<div class="layui-form-item">
					<ks:formTag table="ES_SHOP" column="LOGO" theme="layer"/>
					<input id="logo" type="hidden" name="logo"/>
	                <!-- 自行编写上传附件模块 -->
					<div id="uploadfileQueue"></div>
					<input type="file" id="uploadify" name="uploadify" 
					data-queueid="uploadfileQueue" 
					data-filetypeexts="*.jpg;*.jpge;*.gif;*.png" 
					data-buttontext="选择封面" data-auto="true" 
					data-multi="false" data-queuesizelimit="5" 
					data-filesizelimit="5000KB" data-method="POST"
					data-url="eshop\admin\logo\" />
	                <img id="logoShow" src="${ctx }/upload/none.png"/>
				</div>
				<div class="layui-form-item">
					<ks:formTag table="ES_SHOP" column="DESCRIBE" theme="layer"/>
				</div>
				<div class="layui-form-item">
					<ks:formTag table="ES_SHOP" column="ADDRESS" theme="layer"/>
				</div>
				<div class="layui-form-item">
					<ks:formTag table="ES_SHOP" column="CONFIG" theme="layer"/>
				</div>
				<!-- <div class="layui-inline"> -->
				<input id="id" name="id" type="hidden"/>
			</form>
		</div>
		<script type="text/javascript">
			var loadi,url,index = parent.layer.getFrameIndex(window.name);
			$(document).ready(function(){
				layui.use(['form', 'layedit', 'laydate'], function(){
				    var form = layui.form()
				    ,layer = layui.layer
				    ,layedit = layui.layedit
				    ,laydate = layui.laydate;
				});
				var row = parent.$('#dataList').datagrid('getSelected');
				url = '${ctx}/es/shop/save';
				if(row != null) {//row对象不为空可认定是修改
					$('#userName').attr('readonly', true);
					if(row.logo!=null && row.logo!='') {
						$("#logoShow").attr("src","${ctx}/"+row.logo);
					}
					loadData(row.id);
					url = '${ctx}/es/shop/update';
				} else {//反之则为新建
					//新建初始化字段示例
				}
				//添加上传附件组件渲染及设置回调函数
				mineUpload($("#uploadify"), "${ctx}", "afterMineUpload");
			});
		    
			//上传附件成功回调函数
		    function afterMineUpload(file, uf, message) {
		    	var fileName =uf.url+ uf.realname;//拼接图片在服务器所在路径字符串
		    	$("#logo").val(fileName);//给隐藏输入框赋值
		    	$("#logoShow").attr("src","${ctx}/"+fileName);//给界面img标签赋值用于显示图片
			}
			
			function submitInfo(){
				$.ajax({
					type: "post",
					url: url,
					data: $('#dataForm').serialize(),
					dataType: 'json',
					timeout: layerLoadMaxTime,
					beforeSend: function(XMLHttpRequest){
						loadi = parent.layer.load(2, {shade: layerLoadShade,time: layerLoadMaxTime});
					},
					success: function(data, textStatus){
						parent.layer.close(loadi);
						if (data.success==TRUE){
							parent.$.shop.reloadData();
							parent.layer.msg("操作成功！", {offset: layerMsgOffset,icon: 6,shift: 8,time: layerMsgTime});
							parent.layer.close(index);
	                    } else {
	                    	renderErrMsg(data.message);
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
				});
			}
			
			function loadData(id) {
				$.ajax({
					type: "post",
					url: '${ctx}/es/shop/data/'+id,
					data: {},
					dataType: 'json',
					timeout: layerLoadMaxTime,
					beforeSend: function(XMLHttpRequest){
						loadi = parent.layer.load(2, {shade: layerLoadShade,time: layerLoadMaxTime});
					},
					success: function(data, textStatus){
						parent.layer.close(loadi);
						renderFormData(data);
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
				});
			}
		</script>
	</body>
</html>

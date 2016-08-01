<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/taglib/common.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>平台</title>
<%@ include file="/WEB-INF/view/taglib/header.jsp"%>
<%@ include file="/WEB-INF/view/taglib/layer.jsp"%>
<%@ include file="/WEB-INF/view/taglib/bootstrap.jsp"%>
<%@ include file="/WEB-INF/view/taglib/validate.jsp"%>
<style type="text/css">
</style>
<script type="text/javascript">
	$(document).ready(function() {
		var e = "<i class='fa fa-times-circle'></i> ";
		$("#signupForm").validate({
			rules : {
				pwd : {
					required : !0,
					remote : {
						url : "${ctx}/sysUser/validatePassWord", //后台处理程序
						type : "post", //数据发送方式
						dataType : "json", //接受数据格式   
						data : { //要传递的数据
							passWord : function() {
								return $("#pwd").val();
							},
							userName : '${currentUser.userName}'
						}
					}
				},
				password : {
					required : !0,
					minlength : 6
				},
				confirm_password : {
					required : !0,
					minlength : 6,
					equalTo : "#password"
				}
			},
			messages : {
				pwd : e + "请输入正确的原密码",
				password : {
					required : e + "请输入您的密码",
					minlength : e + "密码必须6个字符以上"
				},
				confirm_password : {
					required : e + "请再次输入密码",
					minlength : e + "密码必须6个字符以上",
					equalTo : e + "两次输入的密码不一致"
				}
			}
		});
	});
	var loadi,index = parent.layer.getFrameIndex(window.name);
	function submitInfo() {
		if($("#signupForm").validate().form()) {
			loadi = parent.layer.load('正在修改，请稍后...');
			$.ajax({
	   			type:'POST',
	   			url :'${ctx}/sysUser/updatePassword?id=${currentUser.id}&password='+password+'&userName=${currentUser.userName}',
	   			dataType : "json",
	   			success : function(result) {
	   				parent.layer.close(loadi);
	   				if (result.success==TRUE){
						parent.layer.msg("操作成功！", {offset: layerMsgOffset,icon: 6,shift: 8,time: layerMsgTime});
						parent.layer.close(index);
	   				}else{
	   					$.messager.alert('警告','密码修改失败！','warning');
	   				}
	   			}
	   		});
		}
	}
</script>
</head>
<body>
	<div class="row">
		<div class="col-md-10">
			<div class="ibox float-e-margins">
				<div class="ibox-content">
					<form class="form-horizontal m-t" id="signupForm">
						<div class="form-group">
							<label class="col-md-3 control-label">原密码：</label>
							<div class="col-md-8">
								<input id="pwd" name="pwd" class="form-control" type="password">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">新密码：</label>
							<div class="col-md-8">
								<input id="password" name="password" class="form-control"
									type="password">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">确认密码：</label>
							<div class="col-md-8">
								<input id="confirm_password" name="confirm_password"
									class="form-control" type="password"> <span
									class="help-block m-b-none"><i class="fa fa-info-circle"></i>
									请再次输入您的密码</span>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
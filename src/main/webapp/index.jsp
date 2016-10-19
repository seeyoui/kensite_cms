<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/taglib/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Kensite开发平台</title>
<%@ include file="/WEB-INF/view/taglib/header.jsp"%>
<%@ include file="/WEB-INF/view/taglib/layer.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx_login}/login_particle/css/style.css" />
<script type="text/javascript" src="${ctx_login}/login_particle/js/Particleground.js"></script>
<style type="text/css">
body {
	height: 100%;
	background: #16a085;
	overflow: hidden;
}
canvas {
	z-index: -1;
	position: absolute;
}
</style>
</head>

<body>
	<form class="form-1" method="post" id="loginForm" name="loginForm" action="${ctx}/login">
		<dl class="admin_login">
			<dt>
				<strong>站点后台管理系统</strong> <em>Management System</em>
			</dt>
			<dd class="user_icon">
				<input id="userName" name="userName" type="text" placeholder="账号" class="login_txtbx" />
			</dd>
			<dd class="pwd_icon">
				<input id="password" name="password" type="password" placeholder="密码" class="login_txtbx" />
			</dd>
			<dd>
			</dd>
			<dd>
				<input type="button" value="立即登陆" class="submit_btn" />
			</dd>
			<dd>
				<p>© 2014-2016 kensite 版权所有</p>
				<p>鲁B2-8998988-1</p>
			</dd>
		</dl>
	</form>
	<script type="text/javascript">
		$(document).ready(function() {
			$('body').particleground({
				dotColor : '#5cbdaa',
				lineColor : '#5cbdaa'
			});

			$('.submit_btn').click(function() {
				$('#loginForm').submit();
			});
			//添加“回车”事件
			$(document).keydown(function(e) {
				if (e.keyCode === 13) {
					$('.submit_btn').click();
				}
			});
			<c:if test="${not empty info}">
			layer.msg('${info}', {
				offset : 'rb',
				icon : 5,
				shift : 8
			});
			</c:if>
		});
	</script>
</body>
</html>

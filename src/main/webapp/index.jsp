<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/taglib/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>Kensite开发平台</title>
	<%@ include file="/WEB-INF/view/taglib/header.jsp" %>
	<%@ include file="/WEB-INF/view/taglib/layer.jsp" %>
	<link rel="stylesheet" type="text/css" href="${ctx_login}/login_index/css/style.css" />
	<!--[if lte IE 7]><style>.main{display:none;} .support-note .note-ie{display:block;}</style><![endif]-->
	<style type="text/css">
	form {
		position: absolute;
		height: 110px;
		width: 360px;
	}
	
	#loginForm {
		position:absolute;
		top:50%;
		left:50%;
		margin:-55px 0 0 -163px;
	}
	
	#bg_div{ 
		position:fixed; 
		top:0; 
		left:0; 
		bottom:0; 
		right:0; 
		z-index:-1; 
	} 
	
	#bg_div img { 
		height:100%; 
		width:100%; 
		border:0; 
	} 
	</style>
</head>

<body>
	<div id="bg_div"><img src="${ctx_login}/login_index/img/login_bg.jpg" /></div>
	<form class="form-1" method="post" id="loginForm" name="loginForm" action="${ctx}/login">
		<p class="field">
			<input type="text" id="userName" name="userName" placeholder="帐号"/>
			<i class="icon-user icon-large"></i>
		</p>
			<p class="field">
				<input type="password" id="password" name="password" placeholder="密码"/>
				<i class="icon-lock icon-large"></i>
		</p>
		<input id="rememberMe" name="rememberMe" type="hidden" value="" />
		<p class="submit">
			<button type="submit" name="submit"><i class="icon-arrow-right icon-large"></i></button>
		</p>
	</form>
	<script type="text/javascript">
		$(document).ready(function() {
			$('.submit').click(function() {
				$('#loginForm').submit();
			});
			//添加“回车”事件
			$(document).keydown(function(e) {
				if (e.keyCode === 13) {
					$('.submit').click();
				}
			});
			<c:if test="${not empty info}">
				layer.msg('${info}', {
				    offset: 'rb',
					icon: 5,
				    shift: 8
				});
			</c:if>
		});
	</script>
</body>
</html>

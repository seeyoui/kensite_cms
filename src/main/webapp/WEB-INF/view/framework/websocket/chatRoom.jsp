<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/taglib/common.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Kensite</title>
<%@ include file="/WEB-INF/view/taglib/header.jsp"%>
<%@ include file="/WEB-INF/view/taglib/layer.jsp"%>
<%@ include file="/WEB-INF/view/taglib/bootstrap.jsp"%>
</head>
<body class="fixed-sidebar full-height-layout gray-bg"
	style="overflow: hidden">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox chat-view">
					<div class="ibox-title">
						<small class="pull-right text-muted">最新消息：2015-02-02 18:39:23</small> 聊天窗口
					</div>
					<div class="ibox-content">
						<div class="row">
							<div class="col-md-9 ">
								<div class="chat-discussion">
									<div class="chat-message">
										<img class="message-avatar" src="${ctx_skins}/bootstrap/img/a1.jpg" alt="">
										<div class="message">
											<a class="message-author" href="#"> 颜文字君</a> <span
												class="message-date"> 2015-02-02 18:39:23 </span> <span
												class="message-content"> H+ 是个好框架 </span>
										</div>
									</div>
									<div class="chat-message">
										<img class="message-avatar" src="${ctx_skins}/bootstrap/img/a4.jpg" alt="">
										<div class="message">
											<a class="message-author" href="#"> 林依晨Ariel </a> <span
												class="message-date"> 2015-02-02 11:12:36 </span> <span
												class="message-content"> jQuery表单验证插件 - 让表单验证变得更容易 </span>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-3">
								<div class="chat-users">
									<div class="users-list">
										<div class="chat-user">
											<img class="chat-avatar" src="${ctx_skins}/bootstrap/img/a4.jpg" alt="">
											<div class="chat-user-name">
												伤城Simple
											</div>
										</div>
										<div class="chat-user">
											<span class="pull-right label label-primary">在线</span>
											<img class="chat-avatar" src="${ctx_skins}/bootstrap/img/a1.jpg" alt="">
											<div class="chat-user-name">
												<a href="#">从未出现过的风景__</a>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<div class="chat-message-form">
									<div class="form-group">
										<textarea class="form-control message-input" name="message"
											placeholder="输入消息内容，按回车键发送"></textarea>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	
</script>
</html>
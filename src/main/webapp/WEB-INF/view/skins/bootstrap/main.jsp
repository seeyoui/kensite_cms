<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/taglib/common.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Kensite</title>
<%@ include file="/WEB-INF/view/taglib/header.jsp"%>
<%@ include file="/WEB-INF/view/taglib/layer.jsp" %>
<%@ include file="/WEB-INF/view/taglib/bootstrap.jsp"%>
<%@ include file="/WEB-INF/view/taglib/moment.jsp"%>
<style type="text/css">
.content img {
	width:32px;
	height:32px;
}
</style>
</head>
<body class="fixed-sidebar full-height-layout gray-bg"
	style="overflow: hidden">
	<div id="wrapper">
		<!--左侧导航开始-->
		<nav class="navbar-default navbar-static-side" role="navigation">
		<div class="nav-close">
			<i class="fa fa-times-circle"></i>
		</div>
		<div class="sidebar-collapse">
			<ul class="nav" id="side-menu">
				<li class="nav-header">
					<div class="dropdown profile-element">
						<span>
						<c:if test="${not empty currentUser.headIcon}">
							<img id="headIcon" alt="image" class="img-circle" src="${ctx}/upload/headIcon/${currentUser.headIcon}" style="width: 60px;"/>
						</c:if>
						<c:if test="${empty currentUser.headIcon}">
							<img id="headIcon" alt="image" class="img-circle" src="${ctx}/upload/headerIcon.png" style="width: 60px;"/>
						</c:if>
						</span>
						<a data-toggle="dropdown" class="dropdown-toggle" href="#">
							<span class="clear" style="position: absolute; left: 80px;top: 10px;">
							<span class="block m-t-xs"><strong class="font-bold">${currentUserName}</strong></span>
							<span class="text-muted text-xs block">${currentUser.name}<b class="caret"></b></span>
							</span>
						</a>
						<ul class="dropdown-menu animated fadeInRight m-t-xs">
							<!-- <li><a href="javascript:headIcon();">修改头像</a></li> -->
							<li><a class="J_menuItem" href="${ctx }/login/skinsPage/common/headIcon">修改头像</a></li>
							<li><a class="J_menuItem" href="profile.html">个人资料</a></li>
							<li><a href="javascript:updatePassword()">修改密码</a></li>
							<li class="divider"></li>
							<li><a href="javascript:logout();">安全退出</a></li>
						</ul>
					</div>
					<div class="logo-element">Ks</div>
				</li>
				<!-- 生成菜单导航 -->
				<c:forEach var="tree" items="${menuList}" varStatus="status">
				<li>
					<c:if test="${tree.attributes.url=='/'}">
					<a href="javasctipt:void(0)">
					</c:if>
					<c:if test="${tree.attributes.url!='/'}">
					<a class="J_menuItem" href="${ctx }${tree.attributes.url}">
					</c:if>
						<c:if test="${not empty tree.attributes.icon}">
						<i class="${tree.attributes.icon}"></i>
						</c:if>
						<span class="nav-label">${tree.text}</span>
						<c:if test="${not empty tree.children}">
						<span class="fa arrow"></span>
						</c:if>
					</a>
					<c:if test="${tree.children!=null}">
					<ul class="nav nav-second-level">
					<c:forEach var="tree1" items="${tree.children}" varStatus="status">
						<li>
						<c:if test="${tree1.attributes.url=='/'}">
						<a href="javasctipt:void(0)">
						</c:if>
						<c:if test="${tree1.attributes.url!='/'}">
						<a class="J_menuItem" href="${ctx }${tree1.attributes.url}">
						</c:if>
						<c:if test="${not empty tree1.attributes.icon}">
						<i class="${tree1.attributes.icon}"></i>
						</c:if>
						${tree1.text}
						<c:if test="${not empty tree1.children}">
						<span class="fa arrow"></span>
						</c:if>
						</a>
						<c:if test="${not empty tree1.children}">
						<ul class="nav nav-third-level">
						<c:forEach var="tree2" items="${tree1.children}" varStatus="status">
                            <li><c:if test="${tree2.attributes.url=='/'}">
							<a href="javasctipt:void(0)">
							</c:if>
							<c:if test="${tree2.attributes.url!='/'}">
							<a class="J_menuItem" href="${ctx }${tree2.attributes.url}">
							</c:if>
							<c:if test="${not empty tree2.attributes.icon}">
							<i class="${tree2.attributes.icon}"></i>
							</c:if>
                            ${tree2.text}</a>
                            </li>
						</c:forEach>
						</ul>
						</c:if>
						</li>
					</c:forEach>
					</ul>
					</c:if>
				</li>
				</c:forEach>
			</ul>
		</div>
		</nav>
		<!--左侧导航结束-->
		<!--右侧部分开始-->
		<div id="page-wrapper" class="gray-bg dashbard-1">
			<div class="row border-bottom">
				<nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
					<div class="navbar-header">
					</div>
					<ul class="nav navbar-top-links navbar-right">
						<li class="hidden-xs">
							<a href="javascript:logout();">
								<i class="fa fa fa-sign-out"></i>退出
							</a>
						</li>
					</ul>
				</nav>
			</div>
			<div class="row content-tabs">
				<a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#" style="position: absolute; left: -19px;top: -5px;">
					<i class="fa fa-bars"></i>
				</a>
				<button class="roll-nav roll-left J_tabLeft">
					<i class="fa fa-backward"></i>
				</button>
				<nav class="page-tabs J_menuTabs">
					<div class="page-tabs-content">
						<a href="javascript:;" class="active J_menuTab" data-id="${ctx }/login/skinsPage/welcome">首页</a>
					</div>
				</nav>
				<button class="roll-nav roll-right J_tabRight">
					<i class="fa fa-forward"></i>
				</button>
				<div class="btn-group roll-nav roll-right">
					<button class="dropdown J_tabClose" data-toggle="dropdown">
						关闭操作<span class="caret"></span>
					</button>
					<ul role="menu" class="dropdown-menu dropdown-menu-right">
						<li class="J_tabShowActive"><a>定位当前选项卡</a></li>
						<li class="divider"></li>
						<li class="J_tabCloseAll"><a>关闭全部选项卡</a></li>
						<li class="J_tabCloseOther"><a>关闭其他选项卡</a></li>
					</ul>
				</div>
			</div>
			<div class="row J_mainContent" id="content-main">
				<iframe class="J_iframe" name="iframe0" width="100%" height="100%"
					src="${ctx }/login/skinsPage/welcome" frameborder="0" data-id="${ctx }/login/skinsPage/welcome" seamless></iframe>
			</div>
			<div class="footer">
				<div class="pull-right">
					&copy; 2014-2016 <a href="http://www.seeyoui.com/" target="_blank">kensite</a>
				</div>
			</div>
		</div>
		<!--右侧部分结束-->
		<!--mini聊天窗口开始-->
		<div class="small-chat-box fadeInRight animated">
			<div class="heading" draggable="true">
				<small class="chat-date pull-right" id="headDate">
				</small>
				K信
			</div>
			<div id="console" class="content">
				
			</div>
			<div class="form-chat">
				<div class="input-group input-group-sm">
					<input id="message" type="text" class="form-control"/>
					<span class="input-group-btn">
						<button class="btn btn-primary" type="button" onclick="echo();">发送</button>
					</span>
				</div>
			</div>
		</div>
		<div id="small-chat" class="animated">
			<span class="badge badge-warning pull-right"></span>
			<a class="open-small-chat"><i class="fa fa-comments"></i></a>
		</div>
	</div>
</body>
<script type="text/javascript">
$(document).ready(function() {
	connect();
	$(document).keydown(function(e) {
		if (e.keyCode === 13) {
			echo();
		}
	});
	$('.small-chat-box .heading #headDate').html(moment().format('LL'));
});
//退出当前登录
function logout() {
	layer.confirm('你确定要退出系统么？',function(index){
		window.location.href="${ctx}/login/logout";
	});
}

function updatePassword() {
	var url = "${ctx }/login/skinsPage/common/updatePassword";
	var title = "修改密码";
	var area = ['350px', '450px'];
	layerOpen(url, title, area);
}

function layerOpen(url, title, area) {
	if(area == null) {
		area = ['310px', '350px'];
	}
   	layer.open({
   	    type: 2,
   	    title: title,
   	    area: area,
   	    fix: false, //不固定
   	    maxmin: false,
   	    content: url,
   	    btn: ['保存', '取消'],
           success: function(layero, index){
               iframeBody = layer.getChildFrame('body', index);
               iframeWin = window[layero.find('iframe')[0]['name']];
           },
   	    yes: function(index, layero) {
   	    	if(iframeWin != null) {
   	    		iframeWin.submitInfo();
   	    	}
   	    },
   	    cancel: function(index){
   	    	layer.close(index);
   	    }
   	});
}
</script>

<script src="http://cdn.sockjs.org/sockjs-0.3.min.js"></script>

<script type="text/javascript">
	var ws = null;
	var url = null;
	var transports = [];

	function connect() {
		if(ws) {
			return;
		}
		if (!url) {
			url = '${ctx}/websck';
		}
		if ('WebSocket' in window) {
			ws= new WebSocket("ws://"+window.location.host+"${ctx}/websck");
		} else {
			ws = new SockJS("http://"+window.location.host+"${ctx}/sockjs/websck");
		}
		ws.onopen = function () {
			console.info('open');
		};
		ws.onmessage = function (event) {
			log(event.data);
		};
		ws.onclose = function (event) {
			log('Info: connection closed.');
			log(event);
		};
	}

	function disconnect() {
		if (ws != null) {
			ws.close();
			ws = null;
		}
	}

	function echo() {
		if (ws != null) {
			var message = $('#message').val();
			if(message == null || message == '') {
				return;
			}
			var sendMsg = '{"userName":"${currentUser.userName}",'+
				'"name":"${currentUser.name}",'+
				'"headIcon":"${currentUser.headIcon}",'+
				'"sendType":"msg",'+
				'"sendTo":"all",'+
				'"message":"'+message+'"}';
			ws.send(sendMsg);
			$('#message').val('');
		} else {
		}
	}
	
	function log(message) {
		//$("#small-chat").removeAttr("class").attr("class","");
		//$("#small-chat").addClass("animated");
		//$("#small-chat").addClass("pulse");
		message = JSON.parse(message);
		var p = '';
		if(message.userName=='${currentUserName}') {
			p = '<div class="right">'+
				'<div class="author-name">'+
				message.name+' <small class="chat-date"> '+moment().calendar()+' </small>'+
				'<img class="message-avatar" src="${ctx}/upload/headIcon/'+message.headIcon+'" alt="">'+
				'</div>'+
				'<div class="chat-message">'+message.message+'</div>'+
				'</div>';
		} else {
			p = '<div class="left">'+
				'<div class="author-name">'+
				'<img class="message-avatar" src="${ctx}/upload/headIcon/'+message.headIcon+'" alt="">'+
				message.name+' <small class="chat-date"> '+moment().calendar()+' </small>'+
				'</div>'+
				'<div class="chat-message active">'+message.message+'</div>'+
				'</div>';
			if($('.small-chat-box').attr('class').indexOf('active') == -1) {
				var messageCount = $('#small-chat span').html();
				if(messageCount == null || messageCount == '') {
					messageCount = 0;
				}
				$('#small-chat span').html(parseInt(messageCount)+1);
			}
		}
		$('#console').append(p);
		var console = document.getElementById('console');
		while (console.childNodes.length > 25) {
			console.removeChild(console.firstChild);
		}
		console.scrollTop = console.scrollHeight;
	}
</script>
</html>
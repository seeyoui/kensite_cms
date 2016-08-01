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
<link href="${ctx_skins}/bootstrap/css/style.min.css?v=4.0.0"
	rel="stylesheet">
<script type="text/javascript" src="${ctx_bootstrap}/js/jquery-ui-1.10.4.min.js"></script>
<script type="text/javascript" src="${ctx_skins}/bootstrap/js/content.min.js"></script>
<style type="text/css">
</style>
<script type="text/javascript">
	$(document).ready(function() {
		WinMove();
	});
</script>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content">
		<div class="row">
			<div class="col-sm-4">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>最新动态</h5>
						<div class="ibox-tools">
							<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
							</a> <a class="dropdown-toggle" data-toggle="dropdown"
								href="index.html#"> <i class="fa fa-wrench"></i>
							</a>
							<ul class="dropdown-menu dropdown-user">
								<li><a href="index.html#">选项1</a></li>
								<li><a href="index.html#">选项2</a></li>
							</ul>
							<a class="close-link"> <i class="fa fa-times"></i>
							</a>
						</div>
					</div>
					<div class="ibox-content no-padding">
						<ul class="list-group">
							<li class="list-group-item">
								<p>
									<a class="text-info" href="index.html#">#感谢有你#</a>
									感谢你们一路的相伴，未来也请让我为你们撑腰，我们会更好
								</p> <small class="block text-muted"><i
									class="fa fa-clock-o"></i> 1分钟前</small>
							</li>
							<li class="list-group-item">
								<p>
									<a class="text-info" href="index.html#">@颜文字君</a>
									女生身高×1.09后，就是最适合你的男生身高；相反，男生是÷1.09就可以了..小伙伴们可以试着算下..【图是我的..(*/ω＼*)
								</p>
								<div class="text-center m">
									<span id="sparkline8"></span>
								</div> <small class="block text-muted"><i
									class="fa fa-clock-o"></i> 2小时前</small>
							</li>

							<li class="list-group-item">
								<p>
									<a class="text-info" href="index.html#">#发型师#</a>
									刚才剪发，顾客在看这个视频，妈蛋，这舞姿太销魂了，笑得手颤抖。。。
								</p> <small class="block text-muted"><i
									class="fa fa-clock-o"></i> 1分钟前</small>
							</li>
							<li class="list-group-item">
								<p>
									<a class="text-info" href="index.html#">#一年级#</a> ——#陈氏父子#
									cut：“他是我的陈爸爸”[心]“我叫陈思成，陈老师的陈” [心]“不再见就是，你也好，爸爸也好，妈妈也好，都永远不要说再见
								</p> <small class="block text-muted"><i
									class="fa fa-clock-o"></i> 2分钟前</small>
							</li>
						</ul>
					</div>
				</div>
				<div class="ibox float-e-margins">
				</div>
			</div>
			<div class="col-sm-4">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>最新消息</h5>
						<div class="ibox-tools">
							<span class="label label-warning-light">10条未读</span>
						</div>
					</div>
					<div class="ibox-content">

						<div>
							<div class="feed-activity-list">

								<div class="feed-element">
									<a href="profile.html" class="pull-left"> <img alt="image"
										class="img-circle" src="${ctx_skins}/bootstrap/img/profile.jpg">
									</a>
									<div class="media-body ">
										<small class="pull-right">5分钟前</small> <strong>谨斯里</strong>
										上传了一个文件 <br> <small class="text-muted">2014.11.8
											12:22</small>

									</div>
								</div>

								<div class="feed-element">
									<a href="profile.html" class="pull-left"> <img alt="image"
										class="img-circle" src="${ctx_skins}/bootstrap/img/a2.jpg">
									</a>
									<div class="media-body ">
										<small class="pull-right">2个月前</small> <strong>田亮</strong> 参加了<strong>《粑粑去哪儿》</strong>
										<br> <small class="text-muted">2014.11.8 12:22</small>
									</div>
								</div>
								<div class="feed-element">
									<a href="profile.html" class="pull-left"> <img alt="image"
										class="img-circle" src="${ctx_skins}/bootstrap/img/a3.jpg">
									</a>
									<div class="media-body ">
										<small class="pull-right">2小时前</small> <strong>林依晨Ariel</strong>
										刚刚起床 <br> <small class="text-muted">2014.11.8
											12:22</small>
									</div>
								</div>
								<div class="feed-element">
									<a href="profile.html" class="pull-left"> <img alt="image"
										class="img-circle" src="${ctx_skins}/bootstrap/img/a5.jpg">
									</a>
									<div class="media-body ">
										<small class="pull-right">32分钟前</small> <strong>颜文字君</strong>
										评论了 <br> <small class="text-muted">2014.11.8
											12:22</small>
										<div class="well">
											【九部令人拍案叫绝的惊悚悬疑剧情佳作】如果你喜欢《迷雾》《致命ID》《电锯惊魂》《孤儿》《恐怖游轮》这些好片，那么接下来推荐的9部同类题材并同样出色的的电影，绝对不可错过哦~
										</div>
										<div class="pull-right">
											<a class="btn btn-xs btn-white"><i
												class="fa fa-thumbs-up"></i> 喜欢 </a>
										</div>
									</div>
								</div>
							</div>

							<button class="btn btn-primary btn-block m-t">
								<i class="fa fa-arrow-down"></i> 加载更多
							</button>

						</div>

					</div>
				</div>

				<div class="ibox float-e-margins">
				</div>
			</div>
			<div class="col-sm-4">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>项目进度</h5>
						<div class="ibox-tools">
							<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
							</a> <a class="dropdown-toggle" data-toggle="dropdown"
								href="index.html#"> <i class="fa fa-wrench"></i>
							</a>
							<ul class="dropdown-menu dropdown-user">
								<li><a href="index.html#">选项1</a></li>
								<li><a href="index.html#">选项2</a></li>
							</ul>
							<a class="close-link"> <i class="fa fa-times"></i>
							</a>
						</div>
					</div>
					<div class="ibox-content timeline">
						<div class="chat-users">
							<div class="users-list">
								<div class="chat-user">
									<img class="chat-avatar" src="${ctx_skins}/bootstrap/img/a4.jpg" alt="">
									<div class="chat-user-name">
										<a href="#">伤城Simple</a>
									</div>
								</div>
								<div class="chat-user">
									<img class="chat-avatar" src="${ctx_skins}/bootstrap/img/a1.jpg" alt="">
									<div class="chat-user-name">
										<a href="#">从未出现过的风景__</a>
									</div>
								</div>
								<div class="chat-user">
									<span class="pull-right label label-primary">在线</span> <img
										class="chat-avatar" src="${ctx_skins}/bootstrap/img/a2.jpg" alt="">
									<div class="chat-user-name">
										<a href="#">冬伴花暖</a>
									</div>
								</div>
								<div class="chat-user">
									<span class="pull-right label label-primary">在线</span> <img
										class="chat-avatar" src="${ctx_skins}/bootstrap/img/a3.jpg" alt="">
									<div class="chat-user-name">
										<a href="#">ZM敏姑娘 </a>
									</div>
								</div>
								<div class="chat-user">
									<img class="chat-avatar" src="${ctx_skins}/bootstrap/img/a5.jpg" alt="">
									<div class="chat-user-name">
										<a href="#">才越越</a>
									</div>
								</div>
								<div class="chat-user">
									<img class="chat-avatar" src="${ctx_skins}/bootstrap/img/a6.jpg" alt="">
									<div class="chat-user-name">
										<a href="#">时光十年TENSHI</a>
									</div>
								</div>
								<div class="chat-user">
									<span class="pull-right label label-primary">在线</span> <img
										class="chat-avatar" src="${ctx_skins}/bootstrap/img/a3.jpg" alt="">
									<div class="chat-user-name">
										<a href="#">陈泳儿SccBaby</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="ibox float-e-margins">
				</div>
			</div>

		</div>
	</div>
</body>
</html>
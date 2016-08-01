	<%@ page language="java" pageEncoding="UTF-8"%>
	<header>
		<div class="hidden-xs header">
			<!--超小屏幕不显示-->
			<h1 class="logo"> <a href="index.jsp" title="博客"><font>${site.name}</font></a> </h1>
			<ul class="nav hidden-xs-nav">
				<li class="<c:if test="${fn:trim(categoryId) eq ''}">active</c:if>">
					<a href="index.jsp"><span class="glyphicon glyphicon-home"></span>网站首页</a>
				</li>
				<cms:category var="category" module="CD" site="${site.domain}">
				<li class="<c:if test="${fn:trim(categoryId) eq category.id}">active</c:if>">
					<a href="list.jsp?categoryId=${category.id}"><span class="${category.extendsInfo }"></span>${category.name }</a>
				</li>
				</cms:category>
				<li class="<c:if test="${fn:trim(categoryId) eq 'about'}">active</c:if>">
					<a href="about.jsp?categoryId=about"><span class="glyphicon glyphicon-user"></span>关于我们</a>
				</li>
				<!-- <li>
					<a href="./friendly.html"><span class="glyphicon glyphicon-tags"></span>友情链接</a>
				</li> -->
			</ul>
			<div class="feeds">
				<a class="feed feed-xlweibo" href="https://github.com/seeyoui/kensite_base_full" target="_blank"><i></i>开源中国</a>
				<a class="feed feed-txweibo" href="https://github.com/seeyoui/kensite_base_full" target="_blank"><i></i>github</a>
				<a class="feed feed-rss" data-toggle="popover" data-trigger="hover" title="" data-html="true" data-content="&lt;img src=&#39;img/weixin.png&#39; alt=&#39;&#39;&gt;" href="javascript:;" target="_blank" data-original-title="微信扫一扫"><i></i>微信支付</a>
				<a class="feed feed-weixin" data-toggle="popover" data-trigger="hover" title="" data-html="true" data-content="&lt;img src=&#39;img/zhifubao.png&#39; alt=&#39;&#39;&gt;" href="javascript:;" target="_blank" data-original-title="支付宝扫一扫"><i></i>支付宝</a>
			</div>
			<div class="wall">
				<a href="guestbook.jsp?categoryId=guestbook">留言板</a> |
				<a href="tagcloud.jsp?categoryId=tagcloud">标签云</a>
			</div>
		</div>
		<!--/超小屏幕不显示-->
		<div class="visible-xs header-xs">
			<!--超小屏幕可见-->
			<div class="navbar-header header-xs-logo">
				<font>${site.name }</font>
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#header-xs-menu" aria-expanded="false" aria-controls="navbar"><span class="glyphicon glyphicon-menu-hamburger"></span></button>
			</div>
			<div id="header-xs-menu" class="navbar-collapse collapse">
				<ul class="nav navbar-nav header-xs-nav">
					<li class="<c:if test="${fn:trim(categoryId) eq ''}">active</c:if>">
						<a href="index.jsp"><span class="glyphicon glyphicon-home"></span>网站首页</a>
					</li>
					<cms:category var="category" module="CD" site="${site.domain}">
					<li class="<c:if test="${fn:trim(categoryId) eq category.id}">active</c:if>">
						<a href="list.jsp?categoryId=${category.id}"><span class="${category.extendsInfo }"></span>${category.name }</a>
					</li>
					</cms:category>
					<li class="<c:if test="${fn:trim(categoryId) eq 'about'}">active</c:if>">
						<a href="about.jsp?categoryId=about"><span class="glyphicon glyphicon-user"></span>关于我们</a>
					</li>
					<!-- <li>
						<a href="./friendly.html"><span class="glyphicon glyphicon-tags"></span>友情链接</a>
					</li> -->
				</ul>
				<form class="navbar-form" action="" method="post" style="padding:0 25px;">
					<div class="input-group">
						<input id="searchHeader" type="text" class="form-control" placeholder="请输入关键字" value="">
						<span class="input-group-btn">
          					<button class="btn btn-default btn-search" type="button" onclick="headerSearch()">搜索</button>
          				</span>
          			</div>
				</form>
			</div>
		</div>
	</header>
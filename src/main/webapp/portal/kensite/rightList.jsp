<%@ page language="java" pageEncoding="UTF-8"%>
		<aside class="sidebar visible-lg"> <!--右侧>992px显示-->
			<div class="sentence">
				<strong>每日一句</strong>
				<cms:motto var="motto">
				<img src="${motto.picture2 }"/>
				<h2 id="day-content-date">${motto.dateline }</h2>
				<p id="day-content-en">${motto.content }</p>
				<p id="day-content-cn">${motto.note }</p>
				</cms:motto>
			</div>
			<div id="search" class="sidebar-block search" role="search">
				<h2 class="title">
					<strong>搜索</strong>
				</h2>
				<form class="navbar-form" action="" method="post">
					<div class="input-group">
						<input id="searchRight" type="text" class="form-control" size="35" placeholder="请输入关键字" value="123"/>
						<span class="input-group-btn">
								<button class="btn btn-default btn-search" type="button" onclick="rightSearch()">搜索</button>
						</span>
					</div>
				</form>
			</div>
			<div class="sidebar-block recommend">
				<h2 class="title">
					<strong>热门推荐</strong>
				</h2>
				<ul>
					<cms:article var="hotArticle" site="${site.domain}" isPage="Y" page="1" rows="5" sort="hits" order="desc">
					<li>
						<a href="view.jsp?articleId=${hotArticle.id}">
							<span class="thumb">
								<img src="${ctx }/${hotArticle.poster}" alt=""/>
							</span>
							<span class="text">${hotArticle.title }</span>
							<span class="text-muted">阅读(${hotArticle.praiseNum })</span>
						</a>
					</li>
					</cms:article>
				</ul>
			</div>
			<div class="sidebar-block comment">
				<h2 class="title">
					<strong>最新评论</strong>
				</h2>
				<ul>
					<cms:comment var="comment" site="${site.domain}" auditState="Y" isPage="Y" page="1" rows="5">
					<li data-toggle="tooltip" data-placement="top" title=""
						data-original-title="${comment.commentator.name }的评论">
						<a href="view.jsp?articleId=${comment.basicId}">
							<span class="face">
								<img src="${ctx }/upload/headIcon/${comment.commentator.headIcon}" alt="">
							</span>
							<span class="text">
								<strong>${comment.commentator.name }</strong> (${ksfn:formatDateTime(comment.createDate)}) 说：<br>
								${comment.content }
							</span>
						</a>
					</li>
					</cms:comment>
				</ul>
			</div>
		</aside>
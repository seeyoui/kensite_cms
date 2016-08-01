<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/taglib/common.jsp"%>
<%@ taglib prefix="cms" uri="/WEB-INF/taglib/cmsTag.tld"%>
<%@ taglib prefix="ksfn" uri="/WEB-INF/tlds/fns.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="title.jsp"%>
<%@include file="style.jsp"%>
<%
	String currentPage = request.getParameter("currentPage");
	int currentPageNo = 1;
	if(currentPage != null) {
		currentPageNo = Integer.parseInt(currentPage);
	}
	request.setAttribute("currentPageNo",currentPageNo);
%>
</head>
<body>
	<section class="container user-select">
		<%@include file="header.jsp"%>
		<!--/超小屏幕可见-->
		<div class="content-wrap">
			<!--内容-->
			<div class="content">
				<div id="carousel-example-generic" class="carousel slide"
					data-ride="carousel">
					<!--banner-->
					<ol class="carousel-indicators">
						<c:set var="articleIndex" value="0"/>
						<c:set var="active" value="active"/>
						<cms:article var="article" site="${site.domain}" isPage="Y" rows="3">
						<li data-target="#carousel-example-generic" data-slide-to="${articleIndex }" class="${active }"></li>
						<c:set var="articleIndex" value="${articleIndex+1 }"/>
						<c:set var="active" value=""/>
						</cms:article>
					</ol>
					<div class="carousel-inner" role="listbox">
						<c:set var="active" value="active"/>
						<cms:article var="article" site="${site.domain}" isPage="Y" rows="3">
						<div class="item ${active }">
							<a href="view.jsp?articleId=${article.id}">
								<img src="${ctx }/${article.poster}" alt="" style="height: 272px;width:100%;">
							</a>
							<div class="carousel-caption">
								${article.description }
							</div>
							<span class="carousel-bg"></span>
						</div>
						<c:set var="active" value=""/>
						</cms:article>
					</div>
					<a class="left carousel-control"
						href="http://www.seeyoui.com/#carousel-example-generic"
						role="button" data-slide="prev" style="display: none;">
						<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
						<span class="sr-only">Previous</span>
					</a>
					<a class="right carousel-control"
						href="http://www.seeyoui.com/#carousel-example-generic"
						role="button" data-slide="next" style="display: none;">
						<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
						<span class="sr-only">Next</span>
					</a>
				</div>
				<!--/banner-->
				<div class="content-block hot-content hidden-xs">
					<h2 class="title">
						<strong>本周热门排行</strong>
					</h2>
					<ul>
						<c:set var="active" value="large"/>
						<cms:article var="article" site="${site.domain}" isPage="Y" rows="5" sort="hits" order="desc">
						<li class="${active }">
							<a href="view.jsp?articleId=${article.id}">
								<img src="${ctx }/${article.poster}" alt="">
								<h3 style="display: none;">${article.title }</h3>
							</a>
						</li>
						<c:set var="active" value=""/>
						</cms:article>
					</ul>
				</div>
				<div class="content-block new-content">
					<h2 class="title">
						<strong>最新文章</strong>
						<!--
	                           	作者：cuichen13145@163.com
	                           	时间：2016-07-26
	                           	描述：
							<span style="font-size: 12px; color: #777; margin-left: 10px;">(找到 1 条符合 禁用 的数据)</span>
	                           -->
					</h2>
					<div class="row">
						<cms:article var="article" site="${site.domain}" isPage="Y" page="${currentPageNo }" rows="5">
						<div class="news-list">
							<div class="news-img col-xs-5 col-sm-5 col-md-4">
								<a href="view.jsp?articleId=${article.id}"><img src="${ctx }/${article.poster}" alt=""></a>
							</div>
							<div class="news-info col-xs-7 col-sm-7 col-md-8">
								<dl>
									<dt>
										<a href="view.jsp?articleId=${article.id}">${article.title } </a>
									</dt>
									<dd>
										<span class="name">
											<a href="javascript:void(0);" title="由 ${article.author.name} 发布" rel="author">${article.author.name}</a>
										</span>
										<span class="identity"></span>
										<span class="time">${ksfn:formatDate(article.createDate,'')}</span>
									</dd>
									<dd class="text">${article.description }</dd>
								</dl>
								<div class="news_bot col-sm-7 col-md-8">
									<span class="tags visible-lg visible-md">
										<c:forEach items="${fn:split(article.tag,',')}" var="tagcloud" >
										<c:if test="${tagcloud ne ''}">
										<a href="list.jsp?tagId=${fn:split(tagcloud,':')[0]}">${fn:split(tagcloud,':')[1]}</a>
										</c:if>
										</c:forEach>
									</span>
									<span class="look">
										共<strong>${article.hits }</strong>人围观， <strong>${article.praiseNum }</strong>人点赞<%-- ， <strong>${article.collectNum }</strong>人收藏 --%>
									</span>
								</div>
							</div>
						</div>
						</cms:article>
					</div>
					<!--<div class="news-more" id="pagination">
				       	<a href="">查看更多</a>
				    </div>-->
					<div class="quotes" style="margin-top: 15px">
						<c:if test="${articlePage.firstPage }">
						<span class="disabled">首页</span>
	                    <span class="disabled">上一页</span>
	                    </c:if>
	                    <c:if test="${!articlePage.firstPage }">
	                    <a href="${ctx }/portal/kensite/index.jsp?currentPage=${articlePage.first }">首页</a>
	                    <a href="${ctx }/portal/kensite/index.jsp?currentPage=${articlePage.prev }">上一页</a>
	                    </c:if>
	                    
	                    <c:forEach var="i" begin="${articlePage.first }" end="${articlePage.last }">
	                    <c:if test="${articlePage.page == i}">
	                    <span class="current">${articlePage.page }</span>
	                    </c:if>
	                    <c:if test="${articlePage.page != i}">
	                    <a href="${ctx }/portal/kensite/index.jsp?currentPage=${i }">${i }</a>
	                    </c:if>
	                    </c:forEach>
						
	                    <c:if test="${articlePage.lastPage }">
	                    <span class="disabled">下一页</span>
						<span class="disabled">尾页</span>
	                    </c:if>
	                    <c:if test="${!articlePage.lastPage }">
	                    <a href="${ctx }/portal/kensite/index.jsp?currentPage=${articlePage.next }">下一页</a>
	                    <a href="${ctx }/portal/kensite/index.jsp?currentPage=${articlePage.last }">尾页</a>
	                    </c:if>
					</div>
				</div>
			</div>
		</div>
		<!--/内容-->
		<%@include file="rightList.jsp"%>
		<!--/右侧>992px显示-->
		<%@include file="footer.jsp"%>
	</section>
	<div>
		<a href="javascript:;" class="gotop" style="display: none;"></a>
	</div>
	<%@include file="script.jsp"%>
</body>
</html>
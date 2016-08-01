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
	String categoryId = request.getParameter("categoryId");
	String tagId = request.getParameter("tagId");
	String currentPage = request.getParameter("currentPage");
	String title = request.getParameter("title");
	int currentPageNo = 1;
	if(currentPage != null) {
		currentPageNo = Integer.parseInt(currentPage);
	}
	request.setAttribute("categoryId",categoryId);
	request.setAttribute("tagId",tagId);
	request.setAttribute("currentPageNo",currentPageNo);
	request.setAttribute("title",title);
%>
</head>
<body>
	<section class="container user-select">
		<%@include file="header.jsp"%>
		<!--/超小屏幕可见-->
		<div class="content-wrap">
			<!--内容-->
			<div class="content">
				<div class="content-block new-content">
					<h2 class="title">
						<c:if test="${categoryId != null and categoryId ne '' and categoryId ne 'null'}">
						<strong><cms:category var="category" id="${categoryId }">${category.name }</cms:category></strong>
						</c:if>
						<c:if test="${title != null and title ne '' and title ne 'null'}">
						<strong>搜索结果</strong><span style="font-size: 12px; color: #777; margin-left: 10px;">(找到符合 ${title } 的数据)</span>
						</c:if>
						<c:if test="${tagId != null and tagId ne '' and tagId ne 'null'}">
						<strong>搜索结果</strong><span style="font-size: 12px; color: #777; margin-left: 10px;"></span>
						</c:if>
						<!--
                         	作者：cuichen13145@163.com
                         	时间：2016-07-26
                         	描述：
						<span style="font-size: 12px; color: #777; margin-left: 10px;">(找到 1 条符合 禁用 的数据)</span>
                         -->
					</h2>
					<div class="row">
						<cms:article var="article" site="${site.domain}" categoryId="${categoryId }" tagId="${tagId }" title="${title }" isPage="Y" page="${currentPageNo }" rows="5">
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
										共<strong>${article.hits }</strong>人围观， <strong>${article.praiseNum }</strong>人点赞， <strong>${article.collectNum }</strong>人收藏
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
	                    <a href="list.jsp?currentPage=${articlePage.first }">首页</a>
	                    <a href="list.jsp?currentPage=${articlePage.prev }">上一页</a>
	                    </c:if>
	                    
	                    <c:forEach var="i" begin="${articlePage.first }" end="${articlePage.last }">
	                    <c:if test="${articlePage.page == i}">
	                    <span class="current">${articlePage.page }</span>
	                    </c:if>
	                    <c:if test="${articlePage.page != i}">
	                    <a href="list.jsp?currentPage=${i }">${i }</a>
	                    </c:if>
	                    </c:forEach>
						
	                    <c:if test="${articlePage.lastPage }">
	                    <span class="disabled">下一页</span>
						<span class="disabled">尾页</span>
	                    </c:if>
	                    <c:if test="${!articlePage.lastPage }">
	                    <a href="list.jsp?currentPage=${articlePage.next }">下一页</a>
	                    <a href="list.jsp?currentPage=${articlePage.last }">尾页</a>
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
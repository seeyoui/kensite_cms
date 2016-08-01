<%@page import="com.seeyoui.kensite.common.util.StringUtils"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/taglib/common.jsp" %>
<%@ taglib prefix="cms" uri="/WEB-INF/taglib/cmsTag.tld" %>
<%@ taglib prefix="ksfn" uri="/WEB-INF/tlds/fns.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@include file="title.jsp" %>
	<%@include file="style.jsp" %>
	<%
	String categoryId = request.getParameter("categoryId");
	String currentPage = request.getParameter("currentPage");
	int currentPageNo = 1;
	if(StringUtils.isNotBlank(currentPage)) {
		currentPageNo = Integer.parseInt(currentPage);
	}
	request.setAttribute("categoryId",categoryId);
	request.setAttribute("currentPageNo",currentPageNo);
	%>
</head>
<body>
	<%@include file="header.jsp" %>
	<section class="view-header">
	    <div class="container">
	        <div class="row">
	        	<cms:article var="article" isPage="Y" page="1" rows="1" categoryId="${categoryId }" sort="nvl(vcacs.cou,0)" order="desc">
	            <div class="col-sm-8" style="margin: 0;padding: 0px 0px 0px 15px;">
				    <a href="view.jsp?articleId=${article.id}&categoryId=${article.categoryId }">
				    <div class="gallery-item" style="height: 400px; overflow: hidden;">
				        <img src="${ctx }/${article.poster}" class="img-responsive"/>
				        <div class="info">
				            <div class="info-con">
				                <h3>${article.title }</h3>
				                <p>
				                    ${article.description }
				                </p>
				            </div>
				        </div>
				        <div class="img-caption">
				            <h3>${article.title }</h3>
				            <p>
				                ${ksfn:formatDate(article.createDate,'')}
				            </p>
				        </div>
				    </div>
				    </a>
				</div>
				</cms:article>
	            <div class="col-sm-4" style="margin: 0;padding: 0px 15px 0px 0px;">
	            	<cms:article var="article" isPage="Y" page="2" rows="2" categoryId="${categoryId }" sort="nvl(vcacs.cou,0)" order="desc">
	                <div class="col-sm-12" style="margin: 0;padding: 0px;">
	                    <a href="view.jsp?articleId=${article.id}&categoryId=${article.categoryId }">
	                    <div class="gallery-item" style="height: 200px; overflow: hidden;">
	                        <img src="${ctx }/${article.poster}" class="img-responsive"/>
	                        <div class="info">
	                            <div class="info-con">
	                                <h3>${article.title }</h3>
	                                <p>
	                                    ${article.description }
	                                </p>
	                            </div>
	                        </div>
	                        <div class="img-caption">
	                            <h3>${article.title }</h3>
	                            <p>
	                                ${ksfn:formatDate(article.createDate,'')}
	                            </p>
	                        </div>
	                    </div>
	                    </a>
	                </div>
	                </cms:article>
	            </div>
	        </div>
	    </div>
	</section>
	<section class="view-content">
	    <div class="container">
	        <div class="row">
	            <div class="col-sm-8">
	            	<cms:category var="category" id="${categoryId }">
	                <h1>${category.name }</h1>
	                <cms:article var="article" categoryId="${categoryId }" isPage="Y" page="${currentPageNo }" rows="5">
	                <div class="view-content-left animated" data-animation="bounceIn" data-delay="0">
	                    <a href="view.jsp?articleId=${article.id}&categoryId=${article.categoryId }">
	                        <div class="gallery-item">
	                            <img src="${ctx }/${article.poster}" class="img-responsive">
	                            <div class="info">
	                                <div class="info-con">
	                                    ${article.keywords }
	                                </div>
	                            </div>
	                        </div>
	                    </a>
	                    <div class="content1">
	                        <h4><a href="view.jsp?articleId=${article.id}&categoryId=${article.categoryId }">${article.title }</a></h4>
	                        <p>${article.description }</p>
	                        <span class="pull-left"><i class="fa fa-clock-o"></i>${ksfn:formatDate(article.createDate,'')}</span>
	                        <span class="pull-right">
	                        	被赞${article.praiseNum }次
	                        </span>
	                    </div>
	                    <div class="clearfix"></div>
	                </div>
	                </cms:article>
	                </cms:category>
	                <ul class="pager">
	                	<c:if test="${articlePage.firstPage }">
	                    <li class="previous disabled"><a href="javascript:void(0)">&larr; 上一页</a></li>
	                    </c:if>
	                    <c:if test="${!articlePage.firstPage }">
	                    <li class="previous"><a href="list.jsp?categoryId=${category.id }&currentPage=${articlePage.prev}">&larr; 上一页</a></li>
	                    </c:if>
	                    <c:if test="${articlePage.lastPage }">
	                    <li class="next disabled"><a href="javascript:void(0)">下一页 &rarr;</a></li>
	                    </c:if>
	                    <c:if test="${!articlePage.lastPage }">
	                    <li class="next"><a href="list.jsp?categoryId=${category.id }&currentPage=${articlePage.next}">下一页 &rarr;</a></li>
	                    </c:if>
	                </ul>
	            </div>
	            <div class="col-sm-4">
                	<%@include file="rightList.jsp" %>
	            </div>
	        </div>
	    </div>
	</section>
	<%@include file="footer.jsp" %>
	<%@include file="script.jsp" %>
</body>
</html>
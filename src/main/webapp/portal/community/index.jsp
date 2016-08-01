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
	request.setAttribute("categoryId", "");
	%>
</head>
<body>
	<%@include file="header.jsp" %>
	<section id="container">
        <div id="skippr">
        	<cms:article var="article" categoryId="801e9fbdfb0f40a2859c9f5f4a3588f6" isPage="Y" rows="5">
            <div style="background-image: url(${ctx }/${article.poster});"></div>
            </cms:article>
        </div>
	</section>
	<cms:category var="category" id="ad361ae87c034a20ae0985b761919289">
	<section style="background-color: #f7f7f7;padding: 0px 0px 30px 0px;">
	    <div class="container">
	        <div class="page-header">
	            <div class="row">
	                <div class="col-lg-12">
	                    <h1 class="pull-left">${category.name }</h1>
	                    <span class="pull-right"><a href="list.jsp?categoryId=${category.id }">更多</a> </span>
	                </div>
	            </div>
	        </div>
	        <div class="row">
	            <cms:article var="article" categoryId="${category.id }" isPage="Y" rows="3">
	            <div class="col-sm-4 animated" data-animation="bounceIn" data-delay="0">
	                <a href="view.jsp?articleId=${article.id}&categoryId=${category.id}">
	                    <div class="walkhi">
	                        <img src="${ctx }/${article.poster}" class="img-responsive"/>
	                        <h4>${article.title }</h4>
	                        <p>
	                            ${ksfn:formatDate(article.createDate,'')}
	                        </p>
	                    </div>
	                </a>
	            </div>
	            </cms:article>
	        </div>
	    </div>
	</section>
	</cms:category>
	<cms:category var="category" id="801e9fbdfb0f40a2859c9f5f4a3588f6">
	<section class="uphi">
	    <div class="container">
	        <div class="uphi-header">
	            <div class="row">
	                <div class="col-lg-12">
	                    <h1 class="pull-left">${category.name }</h1>
	                    <span class="pull-right"><a href="list.jsp?categoryId=${category.id }">更多</a> </span>
	                </div>
	            </div>
	        </div>
	        <div class="row">
	        	<cms:article var="article" categoryId="${category.id }" isPage="Y" rows="1">
	            <div class="col-sm-8 gallery-item animated" data-animation="bounceIn" data-delay="0">
	                <a href="view.jsp?articleId=${article.id}&categoryId=${category.id}">
	                    <div class="gallery-item">
	                        <img src="${ctx }/${article.poster}" class="img-responsive">
	                        <div class="img-caption">
	                            <h4>${article.title }</h4>
	                            <p>
	                                ${ksfn:formatDate(article.createDate,'')}
	                            </p>
	                        </div>
	                    </div>
	                </a>
	            </div>
	            </cms:article>
	            <cms:article var="article" categoryId="${category.id }" isPage="Y" page="2" rows="2">
	            <div class="col-sm-4 animated" data-animation="bounceIn" data-delay="400">
	                <div class="col-sm-12" style="margin: 0px; padding: 0px;">
	                    <a href="view.jsp?articleId=${article.id}&categoryId=${category.id }">
	                        <div class="gallery-item">
	                            <img src="${ctx }/${article.poster}" class="img-responsive">
	                            <div class="img-caption">
	                                <h4>${article.title }</h4>
	                                <p>
	                                    ${ksfn:formatDate(article.createDate,'')}
	                                </p>
	                            </div>
	                        </div>
	                    </a>
	                </div>
	            </div>
	            </cms:article>
	        </div>
	    </div>
	</section>
	</cms:category>
	<cms:category var="category" id="3d37818ae48748e19c9f4b3af3d0834d">
	<section class="hithing">
	    <div class="container">
	        <div class="hithing-header">
	            <div class="row">
	                <div class="col-lg-12">
	                    <h1 class="pull-left">${category.name }</h1>
	                    <span class="pull-right"><a href="list.jsp?categoryId=${category.id }">更多</a> </span>
	                </div>
	            </div>
	        </div>
	        <div class="row" style="padding:0px 15px;">
	            <cms:article var="article" categoryId="${category.id }" isPage="Y" page="5" rows="1">
	            <div class="col-sm-8 clear-lr gallery-item animated" data-animation="bounceIn" data-delay="0">
	                <a href="view.jsp?articleId=${article.id}&categoryId=${category.id}">
	                    <div class="gallery-item" style="height: 253px; overflow: hidden;">
	                        <img src="${ctx }/${article.poster}" class="img-responsive">
	                        <div class="img-caption">
	                            <h4>${article.title }</h4>
	                            <p>
	                                ${ksfn:formatDate(article.createDate,'')}
	                            </p>
	                        </div>
	                    </div>
	                </a>
	            </div>
	            </cms:article>
	            <cms:article var="article" categoryId="${category.id }" isPage="Y" page="6" rows="1">
	            <div class="col-sm-4 clear-lr animated" data-animation="bounceIn" data-delay="400">
	                <a href="view.jsp?articleId=${article.id}&categoryId=${category.id}">
	                    <div class="gallery-item">
	                        <img src="${ctx }/${article.poster}" class="img-responsive">
	                        <div class="img-caption">
	                            <h4>${article.title }</h4>
	                            <p>
	                                ${ksfn:formatDate(article.createDate,'')}
	                            </p>
	                        </div>
	                    </div>
	                </a>
	            </div>
	            </cms:article>
	        </div>
	        <div class="row" style="padding:0px 15px;">
	        	<cms:article var="article" categoryId="${category.id }" isPage="Y" page="1" rows="4">
	            <div class="col-sm-3 clear-lr animated" data-animation="bounceIn" data-delay="400" style="margin: 0px;">
	                <a href="view.jsp?articleId=${article.id}&categoryId=${category.id}">
	                    <div class="gallery-item" >
	                        <img src="${ctx }/${article.poster}" class="img-responsive">
	                        <div class="img-caption">
	                            <h4>${article.title }</h4>
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
	</section>
	</cms:category>
	<cms:category var="category" id="afcecbdc21c144e399d393672321adaa">
	<section class="higuestsay">
	    <div class="container">
	        <div class="hgs-header">
	            <div class="row">
	                <div class="col-lg-12">
	                    <h1 class="pull-left">${category.name }</h1>
	                    <span class="pull-right"><a href="list.jsp?categoryId=${category.id }">更多</a> </span>
	                </div>
	            </div>
	        </div>
	        <div class="row">
	        	<cms:article var="article" categoryId="${category.id }" isPage="Y" rows="4">
	            <div class="col-sm-3 animated" data-animation="bounceIn" data-delay="0" style="text-align: center;">
	                <img src="${ctx }/${article.poster}" >
	                <div class="hgs-content">
	                    <h4>
	                        ${article.title }
	                    </h4>
	                    <p>
	                        ${article.description }
	                    </p>
	                    <p style="text-align: right;">---${article.keywords }</p>
	                </div>
	            </div>
	            </cms:article>
	        </div>
	    </div>
	</section>
	</cms:category>
	<cms:category var="category" id="ad361ae87c034a20ae0985b761919289">
	<section class="hiview">
	    <div class="container">
	        <div class="hiview-header">
	            <div class="row">
	                <div class="col-lg-12">
	                    <h1 class="pull-left">${category.name }</h1>
	                    <span class="pull-right"><a href="list.jsp?categoryId=${category.id }">更多</a> </span>
	                </div>
	            </div>
	        </div>
	        <div class="row">
	        	<cms:article var="article" categoryId="${category.id }" isPage="Y" rows="3">
	            <div class="col-sm-4 animated" data-animation="bounceIn" data-delay="0">
	                <a href="view.jsp?articleId=${article.id}&categoryId=${category.id}">
	                    <div class="walkhi2">
	                        <img src="${ctx }/${article.poster}" class="img-responsive"/>
	                        <h4>${article.title }</h4>
	                        <p>
	                            ${ksfn:formatDate(article.createDate,'')}
	                        </p>
	                    </div>
	                </a>
	            </div>
	            </cms:article>
	        </div>
	    </div>
	</section>
	</cms:category>
	<%@include file="footer.jsp" %>
	<%@include file="script.jsp" %>
</body>
</html>
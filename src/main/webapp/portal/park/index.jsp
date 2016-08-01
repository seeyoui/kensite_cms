<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/taglib/common.jsp" %>
<%@ taglib prefix="cms" uri="/WEB-INF/taglib/cmsTag.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><cms:site var="site" site="park">${site.title }</cms:site></title>
	<%@ include file="/WEB-INF/view/taglib/header.jsp" %>
	<%@ include file="/WEB-INF/view/taglib/layer.jsp" %>
</head>
<body>
	<cms:site var="site" site="park">${site.name }</cms:site>
	<img src="${ctx }/<cms:site var="site" site="park">${site.logo }</cms:site>"/>
	<cms:category var="category" isPage="false" rows="1">
		<h1>${category.name }</h1><br/>
		<cms:article var="article" categoryId="${category.id }">
		<h3><a href="article.jsp?articleId=${article.id }">${article.title }</a>${article.commentNum }</h3><br/>
		</cms:article>
	</cms:category>
</body>
</html>
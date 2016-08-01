<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/taglib/common.jsp" %>
<%@ taglib prefix="cms" uri="/WEB-INF/taglib/cmsTag.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><cms:site var="site" site="park">${site.title }</cms:site></title>
	<%@ include file="/WEB-INF/view/taglib/header.jsp" %>
	<%@ include file="/WEB-INF/view/taglib/layer.jsp" %>
	<%
	String articleId = request.getParameter("articleId");
	request.setAttribute("articleId",articleId);
	%>
</head>
<body>
	<cms:site var="site" site="park">${site.name }</cms:site>
	<img src="${ctx }/<cms:site var="site" site="park">${site.logo }</cms:site>"/>
	<cms:article var="article" id="${articleId}">
	${article.title}
	<cms:comment var="comment" basicId="${article.id}">${comment.content }--${comment.userName }--${comment.score }--</cms:comment>
	</cms:article>
</body>
</html>
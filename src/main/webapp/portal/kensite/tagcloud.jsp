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
	request.setAttribute("categoryId", categoryId);
%>
</head>
<body>
	<section class="container user-select">
		<%@include file="header.jsp"%> <!--/超小屏幕可见-->
		<div class="content-wrap">
			<!--内容-->
			<div class="content">
				<div class="row tags-content content-block">
					<h2 class="title">
						<strong>本站标签</strong>
					</h2>
					<cms:tagcloud var="tagcloud" site="${site.domain}">
					<div class="col-xs-3">
						<span class="tags" data-toggle="tooltip" data-placement="bottom"
							title="" data-original-title="查看关于 ${tagcloud.name } 的文章">
							<a href="list.jsp?tagId=${tagcloud.id }">${tagcloud.name }</a>
						</span>
					</div>
					</cms:tagcloud>
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
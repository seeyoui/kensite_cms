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
	request.setAttribute("categoryId",categoryId);
%>
</head>
<body>
	<section class="container user-select">
		<%@include file="header.jsp"%>
		<!--/超小屏幕可见-->
		<div class="content-wrap">
			<!--内容-->
			<div class="content">
				<div class="content-block about-content">
					<h2 class="title">
						<strong>关于${site.name}</strong>
					</h2>
					<p class="line-title">
						想要深入了解<span>${site.name}？</span>
					</p>
					<p>${site.name}是基于kensite开发平台开发的一款开源的内容发布系统，当然kensite的强大之处并不仅限于此，她可以快速的开发任意类型的系统后台。</p>
					<p>大家如果有好的建议，欢迎赐教！</p>
					<p>如果有什么疑问请使用下方联系方式。</p>
				</div>
				<div class="content-block contact-content">
					<h2 class="title">
						<strong>联系站长</strong>
					</h2>
					<p>
						<span>站长QQ：</span><a href="tencent://message/?uin=2624030701\">2624030701</a>
					</p>
					<p>
						<span>站长信箱：</span><a href="mailto:seeyouiken@163.com">seeyouiken@163.com</a>
					</p>
					<p>
						<span>github：</span><a href="https://github.com/seeyoui">https://github.com/seeyoui</a>&nbsp;&nbsp;&nbsp;&nbsp;(欢迎Fork)
					</p>
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
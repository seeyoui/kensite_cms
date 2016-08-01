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
	String articleId = request.getParameter("articleId");
	String categoryId = request.getParameter("categoryId");
	request.setAttribute("articleId",articleId);
	request.setAttribute("categoryId",categoryId);
	%>
</head>
<body>
	<%@include file="header.jsp" %>
	<section class="view-header">
	    <div class="container">
	        <div class="row">
	            <div class="col-sm-12">
	                <ul class="breadcrumb">
	                    <li><a href="index.jsp">首页</a></li>
	                    <cms:category var="category" id="${categoryId }">
	                    <li><a href="list.jsp?categoryId=${category.id }">${category.name }</a></li>
	                    </cms:category>
	                    <li class="active">详细内容</li>
	                </ul>
	            </div>
	        </div>
	    </div>
	</section>
	<cms:article var="article" id="${articleId}">
	<section class="view-content-info">
	    <div class="container">
	        <div class="row">
	            <div class="col-sm-9">
	                <div class="view-content-left">
	                    <div class="content-header">
	                        <h2>${article.title }</h2>
	                        <span>
	                        	<!-- <a href="hiview-info.html"></a> -->
	                        	<c:if test="${not empty article.author.headIcon}">
									<img src="${ctx }/upload/headIcon/${article.author.headIcon}" style="float:left;height: 25px;width: 25px;border-radius: 50%;margin-right: 5px"/>
								</c:if>
								<c:if test="${empty article.author.headIcon}">
									<img src="${ctx }/upload/headIcon/headerIcon.png" style="float:left;height: 25px;width: 25px;border-radius: 50%;margin-right: 5px"/>
								</c:if>
	                        	${article.author.name}
								<i class="fa fa-clock-o"></i>${ksfn:formatDate(article.createDate,'')}
							</span>
	                    </div>
	                    <p></p>
	                    <img src="${ctx }/${article.poster}" class="img-responsive"/>
	                    <section class="article">
	                    	${article.content}
	                    </section>
	                    <hr />
	                    <span class="pull-left">点赞：
	                    <button id="praiseBtn" type="button" class="btn btn-success" onclick="praise('${categoryId }','${article.id}')">
	                    <i class="glyphicon glyphicon-thumbs-up"></i><span id="praiseNum" class="badge">${article.praiseNum}</span>
	                   	</button>
	                   	</span>
	                    <div class="author-socials pull-right">
	                        <!--<span>分享到：</span>-->
	                        <!-- <div class="bdsharebuttonbox"><a href="#" class="bds_more" data-cmd="more"></a><a href="#" class="bds_qzone" data-cmd="qzone" title="分享到QQ空间"></a><a href="#" class="bds_tsina" data-cmd="tsina" title="分享到新浪微博"></a><a href="#" class="bds_tqq" data-cmd="tqq" title="分享到腾讯微博"></a><a href="#" class="bds_renren" data-cmd="renren" title="分享到人人网"></a><a href="#" class="bds_weixin" data-cmd="weixin" title="分享到微信"></a></div>
	                        <script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdMiniList":false,"bdPic":"","bdStyle":"1","bdSize":"24"},"share":{},"image":{"viewList":["qzone","tsina","tqq","renren","weixin"],"viewText":"分享到：","viewSize":"16"},"selectShare":{"bdContainerClass":null,"bdSelectMiniList":["qzone","tsina","tqq","renren","weixin"]}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script> -->
	                        <!--<a href="#"><i class="fa fa-weixin"></i></a>-->
	                        <!--<a href="#"><i class="fa fa-weibo"></i></a>-->
	                        <!--<a href="#"><i class="fa fa-qq"></i></a>-->
	                    </div>
	                    <div class="clearfix"></div>
	                </div>
	            </div>
	            <div class="col-sm-3">
	                <%@include file="rightList.jsp" %>
	            </div>
	        </div>
	    </div>
	</section>
	</cms:article>
	<%@include file="footer.jsp" %>
	<%@include file="script.jsp" %>
	<%@include file="/WEB-INF/view/taglib/layer.jsp" %>
	<script type="text/javascript">
		function praise(categoryId, articleId) {
			$.ajax({
				type: "post",
				url: '${ctx}/cms/collections/save',
				data: {
					categoryId : categoryId,
					basicId : articleId,
					type : 2,
					category : 'article'
				},
				dataType: 'json',
				beforeSend: function(XMLHttpRequest){
					$('#praiseBtn').attr("disabled", "disabled");
				},
				success: function(data, textStatus){
					if (data.success=='T'){
						var praiseNum = $('#praiseNum').html();
						$('#praiseNum').html(parseInt(praiseNum)+1);
                        layer.msg("操作成功！", {offset: 'rb',icon: 6,shift: 8,time: 1500});
                    } else {
	                    layer.msg("操作失败！", {offset: 'rb',icon: 5,shift: 8,time: 1500});
                    }
				},
				error: function(request, errType) {
					//"timeout", "error", "notmodified" 和 "parsererror"
					if(errType == 'timeout') {
						layer.msg('请求超时', {offset: 'rb',icon: 6,shift: 8,time: 1500});
					}
					if(errType == 'error') {
						layer.msg('系统错误，请联系管理员', {offset: 'rb',icon: 6,shift: 8,time: 1500});
					}
				}
			});
		}
	</script>
</body>
</html>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/taglib/common.jsp" %>
<%@ taglib prefix="cms" uri="/WEB-INF/taglib/cmsTag.tld" %>
<%@ taglib prefix="ksfn" uri="/WEB-INF/tlds/fns.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@include file="title.jsp" %>
	<%@include file="style.jsp" %>
	<link rel="stylesheet" type="text/css" href="${ctx_script}/editormd/css/editormd.preview.min.css"/>
	<%
	String articleId = request.getParameter("articleId");
	request.setAttribute("articleId",articleId);
	%>
</head>
<body>
	<section class="container"><!-- user-select -->
		<%@include file="header.jsp"%>
		<cms:article var="article" id="${articleId}">
		<c:set var="article" value="${article }"/>
		</cms:article>
		<cms:category var="category" id="${article.categoryId }">
		<c:set var="category" value="${category }"/>
		</cms:category>
		<!--/超小屏幕可见-->
		<div class="content-wrap">
			<!--内容-->
			<div class="content">
				<header class="news_header">
					<h2>${article.title }</h2>
					<ul>
						<li>作者：${article.author.name}</li>
						<li>发布于 ${ksfn:formatDate(article.createDate,'')}</li>
						<li>栏目：
							<a href="list.jsp?categoryId=${category.id}" title="${category.name}" target="_blank">${category.name}</a>
						</li>
						<li>共 <strong>${article.hits }</strong> 人围观 </li>
					</ul>
				</header>
				<article id="content" class="news_content">
				<c:if test="${article.customContentView eq 'markdown'}">
					<textarea id="append-test" style="display:none;">${article.content}</textarea>
				</c:if>
				<c:if test="${article.customContentView eq 'ueditor'}">
					${article.content}
				</c:if>
				</article>
				<!-- <div class="reprint">转载请说明出处：
					<a href="" title="" target="_blank"></a> »
					<a href="" title="" target="_blank"></a>
				</div> -->
				<div class="zambia">
					<a id="praiseBtn" href="javascript:praise('${category.id }','${article.id}');" rel="12"><span class="glyphicon glyphicon-thumbs-up"></span> 赞（<span id="praiseNum">${article.praiseNum}</span>）</a>
				</div>
				<div class="tags news_tags">
					标签： 
					<c:forEach items="${fn:split(article.tag,',')}" var="tagcloud" >
					<c:if test="${tagcloud ne ''}">
					<span data-toggle="tooltip" data-placement="bottom" title="" data-original-title="查看关于${fn:split(tagcloud,':')[1]} 的文章">
						<a href="${fn:split(tagcloud,':')[0]}">${fn:split(tagcloud,':')[1]}</a>
					</span>
					</c:if>
					</c:forEach>
				</div>
				<%-- <nav class="page-nav">
					<span class="page-nav-prev">上一篇<br>
						<a href="http://www.ylsat.com/index.php?module=Content&cid=2&id=13" rel="prev">WordPress里的所有url rewrite规则清单</a>
					</span>
					<span class="page-nav-next">下一篇<br>
						<a href="http://www.ylsat.com/index.php?module=Content&cid=2&id=11" rel="next">Linux Netcat 命令：网络工具中的瑞士军刀</a>
					</span>
				</nav> --%>
				<div class="content-block related-content visible-lg visible-md">
					<h2 class="title"><strong>相关推荐</strong></h2>
					<ul>
						<cms:article var="art" site="${site.domain}" categoryId="${category.id }" isPage="Y" rows="8" sort="hits" order="desc">
						<li>
							<a href="view.jsp?articleId=${art.id}&categoryId=${art.categoryId}" title="${art.title}">
								<img src="${ctx }/${art.poster}" alt="${art.title}"/>
								<h3>${art.title}</h3>
							</a>
						</li>
						</cms:article>
					</ul>
				</div>
				<div class="content-block comment">
					<h2 class="title"><strong>评论</strong></h2>
					<!--
                       	作者：cuichen13145@163.com
                       	时间：2016-07-26
                       	描述：
                       -->
					<c:if test="${category.isComment ne 'Y'}">
					<div style="padding: 30px 0;text-align: center;font-size: 16px;color: #777;background: #EEE;border-radius: 5px;">评论已经关闭</div>
					</c:if>
					<c:if test="${category.isComment eq 'Y'}">
					<form action="index.php?module=Content&action=comment" method="post" class="form-inline" id="comment-form">
						<div class="comment-title">
							<!-- <div class="form-group">
								<label for="commentName">昵称：</label>
								<input type="text" name="commentName" required class="form-control" id="commentName" placeholder="匿名">
							</div> -->
							<div class="form-group">
								<label for="commentEmail">邮箱：</label>
								<input type="email" name="commentEmail" class="form-control" id="commentEmail" placeholder="seeyouiken@163.com"/>
							</div>
						</div>
						<div class="comment-form">
							<textarea placeholder="写下你犀利的评论" name="commentContent" id="commentContent"></textarea>
							<div class="comment-form-footer">
								<!--
                                   	作者：cuichen13145@163.com
                                   	时间：2016-07-26
                                   	描述：
								<div class="comment-form-text">请先
									<a href="javascript:;">登录</a> 或
									<a href="javascript:;">注册</a>，也可匿名评论 </div>
                                   -->
								<div class="comment-form-btn">
									<input type="hidden" name="news_id" value="12"/>
									<button type="button" name="comment_submit" class="btn btn-default btn-comment" onclick="submitComment()">提交评论</button>
								</div>
							</div>
						</div>
					</form>
					</c:if>
					<div class="comment-content">
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
	<%@include file="/WEB-INF/view/taglib/layer.jsp" %>
	
	<c:if test="${article.customContentView eq 'markdown'}">
	<script src="${ctx_script}/editormd/lib/marked.min.js"></script>
	<script src="${ctx_script}/editormd/lib/prettify.min.js"></script>
	<script src="${ctx_script}/editormd/lib/raphael.min.js"></script>
	<script src="${ctx_script}/editormd/lib/underscore.min.js"></script>
	<script src="${ctx_script}/editormd/lib/sequence-diagram.min.js"></script>
	<script src="${ctx_script}/editormd/lib/flowchart.min.js"></script>
	<script src="${ctx_script}/editormd/lib/jquery.flowchart.min.js"></script>
	<script src="${ctx_script}/editormd/editormd.min.js"></script>
	</c:if>
	<script type="text/javascript">
		var pageRows = 5;
		$(function() {
			<c:if test="${article.customContentView eq 'markdown'}">
			editormd.markdownToHTML("content", {
				htmlDecode      : "style,script,iframe",  // you can filter tags decode
				emoji           : true,
				taskList        : true,
				tex             : true,  // 默认不解析
				flowChart       : true,  // 默认不解析
				sequenceDiagram : true  // 默认不解析
			});
			</c:if>
			$(".related-content ul li").hover(function() {
				$(this).find("h3").show();
			}, function() {
				$(this).find("h3").hide();
			});
			loadComment(1, pageRows);
		});
		
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
					$('#praiseBtn').attr("href", "javascript:void(0);");
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
		
		function loadComment(page, rows) {
			$.ajax({
				type: "post",
				url: '${ctx}/cms/comment/list/page',
				data: {
					basicId : '${article.id}',
					auditState : 'Y',
					page : page,
					rows : rows
				},
				dataType: 'json',
				beforeSend: function(XMLHttpRequest){
					var obj = $(".comment-content");
					initLoader(obj);
				},
				success: function(data, textStatus){
					var commentContent = '';
					if(data) {
						commentContent += '<ul>';
						for(var i=0; i<data.rows.length; i++) {
							var comment = data.rows[i];
							commentContent += '<li>';
							commentContent += '<span class="face"><img src="${ctx }/upload/headIcon/'+comment.commentator.headIcon+'"/></span>';
							commentContent += '<span class="text">';
							commentContent += '<strong>'+comment.commentator.name +'</strong>('+comment.createDate+') 说：<br>';
							commentContent += '<p>'+comment.content +'</p>';
							commentContent += '</span>';
							commentContent += '</li>';
						}
						commentContent += '</ul>';
						var dataPage = data.page;
						commentContent += '<div class="quotes" style="margin-top:15px;">';
						if(dataPage.firstPage) {
							commentContent += '<span class="disabled">首页</span>';
							commentContent += '<span class="disabled">上一页</span>';
						} else {
							commentContent += '<a href="javascript:loadComment('+dataPage.first+','+rows+');">首页</a>';
							commentContent += '<a href="javascript:loadComment('+dataPage.prev+','+rows+');">上一页</a>';
						}
						for(var i=dataPage.first; i<=dataPage.last; i++) {
							if(i == dataPage.page) {
								commentContent += '<span class="current">'+i+'</span>';
							} else {
								commentContent += '<a href="javascript:loadComment('+i+','+rows+');">'+i+'</a>';
							}
						}
						if(dataPage.lastPage) {
							commentContent += '<span class="disabled">下一页</span>';
							commentContent += '<span class="disabled">尾页</span>';
						} else {
							commentContent += '<a href="javascript:loadComment('+dataPage.next+','+rows+');">下一页</a>';
							commentContent += '<a href="javascript:loadComment('+dataPage.last+','+rows+');">尾页</a>';
						}
						commentContent += '</div>';
					}
					$(".comment-content").html(commentContent);
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
		
		function submitComment() {
			var commentEmail = $('#commentEmail').val();
			var commentContent = $('#commentContent').val();
			if(commentEmail && commentContent) {
				$.ajax({
					type: "post",
					url: '${ctx}/cms/comment/save',
					data: {
						siteId : '${site.id}',
						categoryId : '${category.id}',
						basicId : '${article.id}',
						score : 5,
						auditState : '${category.isAudit}',
						content : commentContent,
						userName : commentEmail
					},
					dataType: 'json',
					beforeSend: function(XMLHttpRequest){
						var obj = $(".comment-content");
						initLoader(obj);
					},
					success: function(data, textStatus){
						if (data.success=='T'){
							loadComment(1, pageRows);
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
		}
	</script>
</body>
</html>

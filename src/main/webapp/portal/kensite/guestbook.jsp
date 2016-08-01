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
				<div class="content-block comment">
					<h2 class="title"><strong>留言</strong></h2>
					<!--
                       	作者：cuichen13145@163.com
                       	时间：2016-07-26
                       	描述：
                       -->
					<div class="comment-content">
					</div>
					<form action="index.php?module=Content&action=comment" method="post" class="form-inline" id="comment-form">
						<div class="comment-title">
							<div class="form-group">
								<label for="guestbookName">昵称：</label>
								<input type="text" name="guestbookName" required class="form-control" id="guestbookName" placeholder="匿名">
							</div>
							<div class="form-group">
								<label for="guestbookWorkunit">单位：</label>
								<input type="email" name="guestbookWorkunit" class="form-control" id="guestbookWorkunit" placeholder="才华有限公司"/>
							</div>
						</div>
						<div class="comment-title">
							<div class="form-group">
								<label for="guestbookPhone">手机：</label>
								<input type="text" name="guestbookPhone" required class="form-control" id="guestbookPhone" placeholder="15123456789">
							</div>
							<div class="form-group">
								<label for="guestbookEmail">邮箱：</label>
								<input type="email" name="guestbookEmail" class="form-control" id="guestbookEmail" placeholder="seeyouiken@163.com"/>
							</div>
						</div>
						<div class="comment-form">
							<textarea placeholder="写下你精妙的留言" name="guestbookContent" id="guestbookContent"></textarea>
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
									<button type="button" name="comment_submit" class="btn btn-default btn-comment" onclick="submitGuestbook()">提交评论</button>
								</div>
							</div>
						</div>
					</form>
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
	<script type="text/javascript">
		var pageRows = 5;
		$(function() {
			$(".related-content ul li").hover(function() {
				$(this).find("h3").show();
			}, function() {
				$(this).find("h3").hide();
			});
			loadGuestbook(1, pageRows);
		});
		
		function loadGuestbook(page, rows) {
			$.ajax({
				type: "post",
				url: '${ctx}/cms/guestbook/list/page',
				data: {
					siteId : '${site.id}',
					page : page,
					rows : rows
				},
				dataType: 'json',
				beforeSend: function(XMLHttpRequest){
					var obj = $(".comment-content");
					initLoader(obj);
				},
				success: function(data, textStatus){
					var guestbookContent = '';
					if(data) {
						guestbookContent += '<ul>';
						for(var i=0; i<data.rows.length; i++) {
							var guestbook = data.rows[i];
							guestbookContent += '<li>';
							guestbookContent += '<span class="face"><img src="${ctx }/upload/headIcon/headerIcon.png"/></span>';
							guestbookContent += '<span class="text">';
							guestbookContent += '<strong>'+guestbook.name +'</strong>('+guestbook.createDate+') 留言：<br>';
							guestbookContent += '<p>'+guestbook.content +'</p>';
							guestbookContent += '</span>';
							
							if(guestbook.reuserid) {
								guestbookContent += '<ul>';
								guestbookContent += '<li>';
								guestbookContent += '<span class="face"><img src="${ctx }/upload/headIcon/'+guestbook.reuser.headIcon+'"/></span>';
								guestbookContent += '<span class="text">';
								guestbookContent += '<strong>'+guestbook.reuser.name +'</strong>('+guestbook.redate+') 回复：<br>';
								guestbookContent += '<p>'+guestbook.recontent +'</p>';
								guestbookContent += '</span>';
								guestbookContent += '</li>';
								guestbookContent += '</ul>';
							}
							
							guestbookContent += '</li>';
						}
						guestbookContent += '</ul>';
						var dataPage = data.page;
						guestbookContent += '<div class="quotes" style="margin-top:15px;">';
						if(dataPage.firstPage) {
							guestbookContent += '<span class="disabled">首页</span>';
							guestbookContent += '<span class="disabled">上一页</span>';
						} else {
							guestbookContent += '<a href="javascript:loadGuestbook('+dataPage.first+','+rows+');">首页</a>';
							guestbookContent += '<a href="javascript:loadGuestbook('+dataPage.prev+','+rows+');">上一页</a>';
						}
						for(var i=dataPage.first; i<=dataPage.last; i++) {
							if(i == dataPage.page) {
								guestbookContent += '<span class="current">'+i+'</span>';
							} else {
								guestbookContent += '<a href="javascript:loadGuestbook('+i+','+rows+');">'+i+'</a>';
							}
						}
						if(dataPage.lastPage) {
							guestbookContent += '<span class="disabled">下一页</span>';
							guestbookContent += '<span class="disabled">尾页</span>';
						} else {
							guestbookContent += '<a href="javascript:loadGuestbook('+dataPage.next+','+rows+');">下一页</a>';
							guestbookContent += '<a href="javascript:loadGuestbook('+dataPage.last+','+rows+');">尾页</a>';
						}
						guestbookContent += '</div>';
					}
					$(".comment-content").html(guestbookContent);
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
		
		function submitGuestbook() {
			var guestbookName = $('#guestbookName').val();
			var guestbookWorkunit = $('#guestbookWorkunit').val();
			var guestbookPhone = $('#guestbookPhone').val();
			var guestbookEmail = $('#guestbookEmail').val();
			var guestbookContent = $('#guestbookContent').val();
			if(guestbookEmail && guestbookContent) {
				$.ajax({
					type: "post",
					url: '${ctx}/cms/guestbook/save',
					data: {
						siteId : '${site.id}',
						content : guestbookContent,
						phone : guestbookPhone,
						email : guestbookEmail,
						name : guestbookName,
						workunit : guestbookWorkunit
					},
					dataType: 'json',
					beforeSend: function(XMLHttpRequest){
						var obj = $(".comment-content");
						initLoader(obj);
					},
					success: function(data, textStatus){
						if (data.success=='T'){
							loadGuestbook(1, pageRows);
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
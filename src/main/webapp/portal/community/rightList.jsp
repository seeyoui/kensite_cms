					<%@ page language="java" pageEncoding="UTF-8"%>
					<!-- <div class="view-content-right">
	                    <img src="img/img3.jpg" class="img-responsive" />
	                </div> -->
	                <div class="view-content-right">
	                    <h1>热门文章</h1>
	                    <div class="aside-top">
	                    	<cms:article var="article" site="community" isPage="Y" page="1" rows="5" sort="nvl(vcacd.cou,0)" order="desc">
	                        <a href="view.jsp?articleId=${article.id}&categoryId=${article.categoryId }"><img src="${ctx }/${article.poster}" style="float:left;height: 40px;width: 40px;border-radius: 50%;margin-right: 15px">${article.title}</a>
	                        </cms:article>
	                    </div>
	                </div>
	                <!-- <div class="view-content-right" style="margin: 20px 0;">
	                    <img src="img/img5.jpg" class="img-responsive" />
	                </div> -->
	                <div class="view-content-right">
	                    <h1>其他文章</h1>
	                    <div class="aside-top">
	                    	<cms:article var="article" site="community" isPage="Y" page="1" rows="5" sort="nvl(t.hits,0)" order="desc">
	                        <a href="view.jsp?articleId=${article.id}&categoryId=${article.categoryId }"><img src="${ctx }/${article.poster}" style="height: 40px;width: 40px;border-radius: 50%;margin-right: 15px">${article.title}</a>
	                        </cms:article>
	                    </div>
	                </div>
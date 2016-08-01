	<%@ page language="java" pageEncoding="UTF-8"%>
	<header>
        <nav class="navbar navbar-default navbar-fixed-top">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-responsive-collapse">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="${ctx }/portal/community/index.jsp"><cms:site var="site" site="community">${site.name }</cms:site></a>
            </div>
            <div class="navbar-collapse collapse navbar-responsive-collapse">
                <ul class="nav navbar-nav">
                	<c:if test="${categoryId eq ''}">
                    <li class="active">
                    </c:if>
                    <c:if test="${categoryId ne ''}">
                    <li>
                    </c:if>
                    <a href="${ctx }/portal/community/index.jsp">首页</a></li>
                    <cms:category var="category" site="community" module="CD">
                	<c:if test="${categoryId eq '' or categoryId ne category.id}">
                    <li>
                    </c:if>
                    <c:if test="${categoryId ne '' and categoryId eq category.id}">
                    <li class="active">
                    </c:if>
                    <a href="list.jsp?categoryId=${category.id }">${category.name }</a></li>
                    </cms:category>
                </ul>
                <!-- <form class="navbar-form navbar-left">
                    <input type="text" class="form-control col-lg-8" placeholder="搜索"/>
                </form>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="#">登陆</a></li>
                    <li><a href="#">注册</a></li>
                </ul> -->
            </div>
        </nav>
	</header>
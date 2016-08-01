	<%@ page language="java" pageEncoding="UTF-8"%>
	<%@ taglib prefix="cms" uri="/WEB-INF/taglib/cmsTag.tld" %>
	<%@ taglib prefix="ksfn" uri="/WEB-INF/tlds/fns.tld" %>
	<footer class="hi-footer">
	    <div class="footertop">
	        <div class="container">
	            <div class="row">
	                <div class="col-sm-4">
	                    <h4>
	                        嗨社区
	                    </h4>
	                    <ul>
	                        <li><a href="${ctx }/portal/community/index.jsp">关于嗨社区</a></li>
	                        <li><a>加入我们</a></li>
	                    </ul>
	                </div>
	                <div class="col-sm-4">
	                    <h4>
	                        合作伙伴
	                    </h4>
	                </div>
	                <div class="col-sm-4">
	                    <i class="fa fa-weibo"></i>
	                    <i class="fa fa-qq"></i>
	                    <i class="fa fa-weixin"></i>
	                </div>
	            </div>
	
	        </div>
	    </div>
	    <div class="footerbot">
	        <cms:site var="site" site="community">${site.copyright }</cms:site> | 鲁ICP备12012936号
	    </div>
	</footer>
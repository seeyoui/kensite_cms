<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="ks" uri="/WEB-INF/taglib/componentTag.tld" %>
<%@ taglib prefix="ksfn" uri="/WEB-INF/tlds/fns.tld" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="ctx_bootstrap" value="${pageContext.request.contextPath}/bootstrap"/>
<c:set var="ctx_script" value="${pageContext.request.contextPath}/script"/>
<c:set var="ctx_activiti" value="${pageContext.request.contextPath}/act"/>
<c:set var="ctx_static" value="${pageContext.request.contextPath}/static"/>
<c:set var="ctx_common" value="${pageContext.request.contextPath}/static/common"/>
<c:set var="ctx_web" value="${pageContext.request.contextPath}/static/web"/>
<c:set var="ctx_skins" value="${pageContext.request.contextPath}/static/skins"/>
<c:set var="ctx_login" value="${pageContext.request.contextPath}/static/login"/>
<c:set var="ctx_assets" value="${pageContext.request.contextPath}/assets"/>
<c:set var="ctx_weixin" value="${pageContext.request.contextPath}/static/weixin"/>
<!-- Form Skins Url -->
<c:set var="ctx_assets_skins" value="${pageContext.request.contextPath}/assets/chubby-stacks/chubby-stacks-css"/>
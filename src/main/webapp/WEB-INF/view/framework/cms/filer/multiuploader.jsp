<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/taglib/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Kensite开发平台</title>
<%@ include file="/WEB-INF/view/taglib/header.jsp"%>
<%@ include file="/WEB-INF/view/taglib/layer.jsp"%>
<%@ include file="/WEB-INF/view/taglib/webuploader.jsp"%>
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
<![endif]-->
<script type="text/javascript">
	// 添加全局站点信息
	var BASE_URL = '${ctx_script}/webuploader';
</script>
<link rel="stylesheet" type="text/css" href="${ctx_static }/plugin/webuploader-multi/css/multi.css" />
<script type="text/javascript" src="${ctx_static }/plugin/webuploader-multi/js/multi.js"></script>
</head>

<body>
	<div id="wrapper">
		<div class="page-body">
			<div id="post-container" class="container">
				<div class="page-container">
					<div id="uploader" class="wu-example">
						<div class="queueList" data-url="${alowPath}">
							<div id="dndArea" class="placeholder">
								<div id="filePicker"></div>
								<p>或将照片拖到这里，单次最多可选10张</p>
							</div>
						</div>
						<div class="statusBar" style="display: none;">
							<div class="progress">
								<span class="text">0%</span> <span class="percentage"></span>
							</div>
							<div class="info"></div>
							<div class="btns">
								<div id="filePicker2"></div>
								<div class="uploadBtn">开始上传</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		$(document).ready(function() {
		});
	</script>
</body>
</html>

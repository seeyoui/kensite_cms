<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/taglib/common.jsp"%>
<html>
<head>
<title>Kensite开发平台</title>
<%@ include file="/WEB-INF/view/taglib/header.jsp"%>
<%@ include file="/WEB-INF/view/taglib/layui.jsp"%>
<%@ include file="/WEB-INF/view/taglib/zTree.jsp"%>

<style type="text/css">
	#img_explorer {
		position: absolute;
		top: 0px;
		left: 0px;
		right: 0px;
		bottom: 0px;
		border-bottom: 1px solid #eee;
	}
	#explorer_left {
		position: absolute;
		top: 0px;
		left: 0px;
		width: 200px;
		bottom: 0px;
		overflow: auto;
		border-right: 1px solid #eee;
	}
	#explorer_right_top {
		position: absolute;
		top: 0px;
		left: 200px;
		right: 0px;
		height: 23px;
		padding-left: 5px;
		border-bottom: 1px solid #eee;
	}
	#explorer_right_top .top_bar {
		position: relative;
	}
	#explorer_right_top .top_left_bar {
		display: inline-block;
	    margin-left: 10px;
	    position: relative;
	    vertical-align: top;
	}
	#explorer_right_top_menu {
		position: absolute;
		top: 23px;
		left: 200px;
		right: 0px;
		height: 22px;
		padding: 5px;
		border-bottom: 1px solid #eee;
	}
	#explorer_right_top_menu .top_bar {
		position: relative;
	}
	#explorer_right_top_menu .top_left_bar {
		display: inline-block;
	    position: relative;
	    vertical-align: top;
	}
	#explorer_right_top_menu .top_right_bar {
		display: inline-block;
	    position: relative;
	    vertical-align: middle;
	}
	#explorer_right_top_menu .top_right_bar input {
		height: 21px;
		line-height: 21px;
		font-size: 12px;
		margin-top: 1px;
	}
	#explorer_right_top_menu .icon-sousuo {
		color: #999;
	    cursor: pointer;
	    position: absolute;
	    right: 5px;
	    top: 5px;
	    font-size: 12px;
	}
	#explorer_right_bottom {
		position: absolute;
		top: 62px;
		left: 205px;
		right: 0px;
		bottom: 0px;
		overflow: auto;
	}
	.img_div {
		display: inline-block;
		float: none;
		margin-bottom: 5px;
		margin-left: 1px;
		margin-right: 1px;
		vertical-align: top;
	}
	.img_div:hover {
		background: #cce8ff none repeat scroll 0 0;
		border-color: #99d1ff;
		border-radius: 1px;
		color: #335;
		filter: none;
		transition: transform 0.2s ease 0s;
	}
	.select {
		background: #cce8ff none repeat scroll 0 0;
		border-color: #99d1ff;
		border-radius: 1px;
		color: #335;
		filter: none;
		transition: transform 0.2s ease 0s;
	}
	.img_div .file_img {
		text-align: center;
		margin: 5px auto;
	}
	.img_div .file_img img {
		width: 90px;
		height: 60px;
	}
	.img_div .file_name {
		cursor: default;
		font-size: 1em;
		line-height: 1.5em;
		margin: 0 auto;
		padding-bottom: 5px;
		text-align: center;
		width: 100px;
		word-break: break-all;
	}
	.img_div .title {
		font-size: 11px;
		color: #335;
	}
</style>
</head>
<body>
<div id="img_explorer">
	<div id="explorer_left">
		<ul id="treeDemo" class="ztree"></ul>
	</div>
	<div id="explorer_right">
		<div id="explorer_right_top">
			<div class="top_bar">
				<div class="top_left_bar">
					<span id="folder_menu" class="layui-breadcrumb">
						<a><cite style="font-size: 12px;">我的图库</cite></a>
					</span>
				</div>
				<div class="top_right_bar">
				</div>
			</div>
		</div>
		<div id="explorer_right_top_menu">
			<div class="top_bar">
				<div class="top_left_bar">
					<div class="layui-btn-group">
						<button class="layui-btn layui-btn-primary layui-btn-mini">
							<i class="layui-icon">&#xe654;</i>新建文件夹
						</button>
						<button class="layui-btn layui-btn-primary layui-btn-mini">
							<i class="layui-icon">&#xe619;</i>上传图片
						</button>
						<button class="layui-btn layui-btn-primary layui-btn-mini">
							<i class="layui-icon">&#x1006;</i>删除图片
						</button>
						<button class="layui-btn layui-btn-primary layui-btn-mini">
							<i class="layui-icon">&#xe603;</i>移动图片
						</button>
						<button class="layui-btn layui-btn-primary layui-btn-mini">
							<i class="layui-icon">&#xe642;</i>重命名
						</button>
					</div>
				</div>
				<div class="top_right_bar">
					<i class="layui-icon icon-sousuo">&#xe615;</i>
					<input type="text" id="query" placeholder="搜索内容，回车跳转" autocomplete="off" class="layui-input">
				</div>
			</div>
		</div>
		<div id="explorer_right_bottom">
		</div>
	</div>
</div>
</body>
<script type="text/javascript">

	var folder = [{"isParent":true,"open":true,"size":0,"name":"我的图库","pId":0,"id":1},{"isParent":true,"size":0,"name":"cms","pId":1,"id":2},{"isParent":true,"size":0,"name":"article","pId":2,"id":3},{"isParent":true,"size":0,"name":"logo","pId":2,"id":18},{"isParent":true,"size":0,"name":"headIcon","pId":1,"id":22},{"isParent":true,"size":0,"name":"image","pId":1,"id":26},{"isParent":true,"size":0,"name":"20160722","pId":26,"id":27},{"isParent":true,"size":0,"name":"temp","pId":1,"id":32},{"isParent":true,"size":0,"name":"tempMarkdown","pId":1,"id":33}];
	var file = [{"isParent":true,"size":0,"name":"cms","pId":1,"id":2},{"isParent":true,"size":0,"name":"article","pId":2,"id":3},{"path":"upload/cms/article/1a4faca2c9f94adfb3e180b1a66ca827.jpg","size":305325,"name":"1a4faca2c9f94adfb3e180b1a66ca827.jpg","pId":3,"id":4},{"path":"upload/cms/article/21571b59b4ef46ed9f38580e8cb20fb9.jpg","size":346402,"name":"21571b59b4ef46ed9f38580e8cb20fb9.jpg","pId":3,"id":5},{"path":"upload/cms/article/3bfc3ba02d7349cfbffd353715f92bd4.png","size":131191,"name":"3bfc3ba02d7349cfbffd353715f92bd4.png","pId":3,"id":6},{"path":"upload/cms/article/5ba8c6475e3841d2bc7a2dd023f189f8.jpg","size":379189,"name":"5ba8c6475e3841d2bc7a2dd023f189f8.jpg","pId":3,"id":7},{"path":"upload/cms/article/6b15c43435e1425ab0c8b64f59cc6fab.png","size":78647,"name":"6b15c43435e1425ab0c8b64f59cc6fab.png","pId":3,"id":8},{"path":"upload/cms/article/7dbfe328721b42689bc12eb780d5f735.png","size":61148,"name":"7dbfe328721b42689bc12eb780d5f735.png","pId":3,"id":9},{"path":"upload/cms/article/86260056f19549b0bfd68120b246c461.jpg","size":49245,"name":"86260056f19549b0bfd68120b246c461.jpg","pId":3,"id":10},{"path":"upload/cms/article/9455a57ca88043849dce4081dbca465b.png","size":153952,"name":"9455a57ca88043849dce4081dbca465b.png","pId":3,"id":11},{"path":"upload/cms/article/a168433701c24e149cab8511a3d2cdbc.jpg","size":595284,"name":"a168433701c24e149cab8511a3d2cdbc.jpg","pId":3,"id":12},{"path":"upload/cms/article/a41c598d9a7a4d52b974499751999af1.png","size":52559,"name":"a41c598d9a7a4d52b974499751999af1.png","pId":3,"id":13},{"path":"upload/cms/article/ac2cabafddf14f17ba7db961faf673c4.png","size":56505,"name":"ac2cabafddf14f17ba7db961faf673c4.png","pId":3,"id":14},{"path":"upload/cms/article/b648ea991b3a4fbca781e871b41a2396.png","size":131191,"name":"b648ea991b3a4fbca781e871b41a2396.png","pId":3,"id":15},{"path":"upload/cms/article/ea534eb327b74ad996cb28602812bc93.jpg","size":218135,"name":"ea534eb327b74ad996cb28602812bc93.jpg","pId":3,"id":16},{"path":"upload/cms/article/fb6d4cf07ab445ee81a0c675e2c96e44.jpg","size":249914,"name":"fb6d4cf07ab445ee81a0c675e2c96e44.jpg","pId":3,"id":17},{"isParent":true,"size":0,"name":"logo","pId":2,"id":18},{"path":"upload/cms/logo/975df8ba43b841f3b312af1287b44ddb.png","size":15109,"name":"975df8ba43b841f3b312af1287b44ddb.png","pId":18,"id":19},{"path":"upload/folder.png","size":10737,"name":"folder.png","pId":1,"id":20},{"path":"upload/headerIcon.png","size":7304,"name":"headerIcon.png","pId":1,"id":21},{"isParent":true,"size":0,"name":"headIcon","pId":1,"id":22},{"path":"upload/headIcon/355222f869db4f4fb8a22e6888aabe48.png","size":113464,"name":"355222f869db4f4fb8a22e6888aabe48.png","pId":22,"id":23},{"path":"upload/headIcon/3a657ea8ddc745a698d51aeea2183f4d.png","size":105854,"name":"3a657ea8ddc745a698d51aeea2183f4d.png","pId":22,"id":24},{"path":"upload/headIcon/headerIcon.png","size":7304,"name":"headerIcon.png","pId":22,"id":25},{"isParent":true,"size":0,"name":"image","pId":1,"id":26},{"isParent":true,"size":0,"name":"20160722","pId":26,"id":27},{"path":"upload/image/20160722/1469151202703000154.jpg","size":125401,"name":"1469151202703000154.jpg","pId":27,"id":28},{"path":"upload/image/20160722/1469151203035032512.jpg","size":66647,"name":"1469151203035032512.jpg","pId":27,"id":29},{"path":"upload/none.jpg","size":6932,"name":"none.jpg","pId":1,"id":30},{"path":"upload/none.png","size":2386,"name":"none.png","pId":1,"id":31},{"isParent":true,"size":0,"name":"temp","pId":1,"id":32},{"isParent":true,"size":0,"name":"tempMarkdown","pId":1,"id":33}];
	var folderId = 0;
	var rootId = 1;
	
	var layer, element;
	$(document).ready( $(function () {
		$.fn.zTree.init($("#treeDemo"), setting, folder);
		layui.use(['element', 'layer'], function() {
			element = layui.element();
			layer = layui.layer;
		});
		$(document).keydown(function(e) {
			if (e.keyCode === 13) {
				renderFile(folderId, $('#query').val());
			}
		});
		sortFile();
		folderId = rootId;
		renderFile(folderId, '');
	}));
	
	function sortFile(sortBy) {
		file.sort(function(a, b){
			if(a.size == b.size) {
				if(a.name > b.name) {
					return 1;
				} else {
					return -1;
				}
			}
			return a.size-b.size
		});
	}
	
	var setting = {
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			onClick: clickFolder
		}
	};
	
	function clickFolder(event, treeId, treeNode) {
		renderFile(treeNode.id);
	}

	function renderFile(id, name) {
		menuBar(id);
		folderId = id;
		var folderImg = 'upload/folder.png';
		var domHtml = '<div class="img_div" data-id="$$ID$$" data-pid="$$PID$$" data-name="$$NAME$$" data-type="$$TYPE$$">';
		domHtml += '<div class="file_img">';
		domHtml += '<img alt="" src="${ctx }/$$IMG$$">';
		domHtml += '</div>';
		domHtml += '<div class="file_name">';
		domHtml += '<span class="title">$$FILE$$</span>';
		domHtml += '</div>';
		domHtml += '</div>';
		$('#explorer_right_bottom').html('');
		for(var i=0; i<file.length; i++) {
			if(file[i].pId == id) {
				if(name != null && name != '') {
					if(file[i].name.indexOf(name) != -1) {
						var innerHtmlStr = '';
						if(file[i].isParent) {
							innerHtmlStr = domHtml;
							innerHtmlStr = innerHtmlStr.replace('$$TYPE$$', 'folder');
							innerHtmlStr = innerHtmlStr.replace('$$ID$$', file[i].id);
							innerHtmlStr = innerHtmlStr.replace('$$PID$$', file[i].pId);
							innerHtmlStr = innerHtmlStr.replace('$$NAME$$', file[i].name);
							innerHtmlStr = innerHtmlStr.replace('$$IMG$$', folderImg);
							innerHtmlStr = innerHtmlStr.replace('$$FILE$$', file[i].name);
						} else {
							innerHtmlStr = domHtml;
							innerHtmlStr = innerHtmlStr.replace('$$TYPE$$', 'file');
							innerHtmlStr = innerHtmlStr.replace('$$ID$$', file[i].id);
							innerHtmlStr = innerHtmlStr.replace('$$PID$$', file[i].pId);
							innerHtmlStr = innerHtmlStr.replace('$$NAME$$', file[i].name);
							innerHtmlStr = innerHtmlStr.replace('$$IMG$$', file[i].path);
							innerHtmlStr = innerHtmlStr.replace('$$FILE$$', formatName(file[i].name));
						}
						$('#explorer_right_bottom').append(innerHtmlStr);
					}
				} else {
					var innerHtmlStr = '';
					if(file[i].isParent) {
						innerHtmlStr = domHtml;
						innerHtmlStr = innerHtmlStr.replace('$$TYPE$$', 'folder');
						innerHtmlStr = innerHtmlStr.replace('$$ID$$', file[i].id);
						innerHtmlStr = innerHtmlStr.replace('$$PID$$', file[i].pId);
						innerHtmlStr = innerHtmlStr.replace('$$NAME$$', file[i].name);
						innerHtmlStr = innerHtmlStr.replace('$$IMG$$', folderImg);
						innerHtmlStr = innerHtmlStr.replace('$$FILE$$', file[i].name);
					} else {
						innerHtmlStr = domHtml;
						innerHtmlStr = innerHtmlStr.replace('$$TYPE$$', 'file');
						innerHtmlStr = innerHtmlStr.replace('$$ID$$', file[i].id);
						innerHtmlStr = innerHtmlStr.replace('$$PID$$', file[i].pId);
						innerHtmlStr = innerHtmlStr.replace('$$NAME$$', file[i].name);
						innerHtmlStr = innerHtmlStr.replace('$$IMG$$', file[i].path);
						innerHtmlStr = innerHtmlStr.replace('$$FILE$$', formatName(file[i].name));
					}
					$('#explorer_right_bottom').append(innerHtmlStr);
				}
			}
		}
		bindFileEventC();
		bindFileEventDC();
	}
	
	function bindFileEventC() {
		$('.img_div').bind("click", function() {
			$('.img_div').removeClass('select');
			$(this).addClass('select');
        });
	}
	
	function bindFileEventDC() {
		$('.img_div').bind("dblclick", function() {
			$('.img_div').removeClass('select');
			$(this).addClass('select');
			if($(this).data('type') == 'file') {
				$('#img_view').find('img').attr('src', $(this).find('img').attr('src'));
				layer.open({
					type: 1,
					closeBtn: 0,
					shade: [0.8, '#393D49'],
					shadeClose: true,
					title: false,
					content: $('.img_view')
				});
				$('.layui-layer-content').height('100%');
				var imgH = $('#img_view').find('img').height();
				var top = '0px';
				if(imgH < 360) {
					top = (360-imgH)/2;
				}
				$('.layui-layer-content').parent().css('top', top+'px');
			}
			if($(this).data('type') == 'folder') {
				var id = $(this).data('id');
				renderFile(id, '');
			}
        });
	}
	
	var menuFolder = [];
	function menuBar(id) {
		selectTreeNode(id);
		if(id == rootId) {
			return;
		}
		menuFolder = [];
		menuFolderLoop(id);
		menuFolder.sort(function(a, b){
			return a.id-b.id
		});
		$('#folder_menu').html('');
		for(var i=0; i<menuFolder.length; i++) {
			if(i == menuFolder.length-1) {
				$('#folder_menu').append('<a style="font-size: 12px;"><cite>'+menuFolder[i].name+'</site?</a>');
			} else {
				$('#folder_menu').append('<a href="javascript:renderFile('+menuFolder[i].id+');" style="font-size: 12px;">'+menuFolder[i].name+'</a>');
			}
		}
		element.init();
	}
	
	function menuFolderLoop(id) {
		for(var i=0; i<folder.length; i++) {
			if(folder[i].id == id) {
				menuFolder.push(folder[i]);
				menuFolderLoop(folder[i].pId);
			}
		}
	}
	
	function selectTreeNode(id) {
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
		var node = treeObj.getNodeByParam("id", id, null);
		treeObj.selectNode(node);
	}
	
	function formatName(name) {
		var length = 14;
		var pointLength = 3;
		if(name.length > length) {
			var names = name.split('.');
			var subLength = parseInt((length - pointLength - names[1].length) / 2);
			var str1 = name.substr(0, subLength);
			var str2 = name.substr(name.length-subLength-pointLength);
			name = str1 + '...' + str2;
		}
		return name;
	}
</script>
<div id="img_view" class="img_view" style="display: none;">
	<img style="width: 350px;"/>
</div>
</html>

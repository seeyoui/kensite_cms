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
<body style="height:100%;">
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
						<button class="layui-btn layui-btn-primary layui-btn-mini" onclick="createFolder()">
							<i class="layui-icon">&#xe654;</i>新建文件夹
						</button>
						<button class="layui-btn layui-btn-primary layui-btn-mini">
							<i class="layui-icon">&#xe619;</i>上传
						</button>
						<button class="layui-btn layui-btn-primary layui-btn-mini" onclick="deleteFile()">
							<i class="layui-icon">&#x1006;</i>删除
						</button>
						<button class="layui-btn layui-btn-primary layui-btn-mini">
							<i class="layui-icon">&#xe621;</i>复制
						</button>
						<button class="layui-btn layui-btn-primary layui-btn-mini">
							<i class="layui-icon">&#xe630;</i>剪切
						</button>
						<button class="layui-btn layui-btn-primary layui-btn-mini" onclick="rename()">
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

	var folder = ${folderList};
	var file = ${fileList};
	var folderId = 0;
	var rootId = 1;
	
	var layer, element;
	var treeObj;
	$(document).ready( $(function () {
		treeObj = $.fn.zTree.init($("#treeDemo"), setting, folder);
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
		if(element)
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
	
	function getSelectFile() {
		var fileId = $('div.select').data('id');
		for(var i=0; i<file.length; i++) {
			if(fileId == file[i].id) {
				return file[i];
			}
		}
	}
	
	function getCurrentFolder() {
		for(var i=0; i<folder.length; i++) {
			if(folderId == folder[i].id) {
				return folder[i];
			}
		}
	}
	
	var largeNum = 99999;
	function createFolder() {
		var currentFolder = getCurrentFolder();
		layer.prompt(
			{title: '请输入文件夹名', formType: 0},
			function(folderName, index) {
				$.ajax({
					type: 'post',
					url: '${ctx}/cms/filer/createFolder',
					data: {
						path: currentFolder.path,
						name: folderName
					},
					dataType: 'json',
					timeout: layerLoadMaxTime,
					beforeSend: function(XMLHttpRequest){
						loadi = layer.load(2, {shade: layerLoadShade,time: layerLoadMaxTime});
					},
					success: function(data, textStatus){
						if (data.success==TRUE){
							var f = {"isParent":true,"size":0,"name":folderName,"path":currentFolder.path+"/"+folderName,"pId":folderId,"id":largeNum--};
							folder[folder.length] = f;
							file[file.length] = f;
							renderFile(folderId);
							var node = treeObj.getNodeByParam("id", folderId, null);
							treeObj.addNodes(node, -1, f);
							selectTreeNode(folderId);
							layer.close(index);
							layer.msg("操作成功！", {offset: layerMsgOffset,icon: 6,shift: 8,time: layerMsgTime});
						} else {
	                    	layer.msg(data.message, {offset: layerMsgOffset,icon: 5,shift: 8,time: layerMsgTime});
	                    }
						layer.close(loadi);
					},
					error: function(request, errType) {
						layer.close(loadi);
						//"timeout", "error", "notmodified" 和 "parsererror"
						if(errType == 'timeout') {
							layer.msg('请求超时', {offset: 'rb',icon: 6,shift: 8,time: layerMsgTime});
						}
						if(errType == 'error') {
							layer.msg('系统错误，请联系管理员', {offset: 'rb',icon: 6,shift: 8,time: layerMsgTime});
						}
					}
				});
			}
		);
	}
	function deleteFile() {
		var currentFile = getSelectFile();
		var type = '';
		if(currentFile.isParent) {
			type = 'folder';
		} else {
			type = 'file';
		}
		var msg = '文件删除后不可恢复，是否确认删除？';
		if(type == 'folder') {
			msg = '删除文件夹将一并删除该文件夹下的所有文件，是否确定删除？';
		}
		layer.confirm(msg, {
			btn: ['确定','取消'] //按钮
		}, function(){
			$.ajax({
				type: 'post',
				url: '${ctx}/cms/filer/delete',
				data: {
					path: currentFile.path,
					type: type
				},
				dataType: 'json',
				timeout: layerLoadMaxTime,
				beforeSend: function(XMLHttpRequest){
					loadi = layer.load(2, {shade: layerLoadShade,time: layerLoadMaxTime});
				},
				success: function(data, textStatus){
					if (data.success==TRUE){
						folder = deleteArrayItem(folder, currentFile.id);
						file = deleteArrayItem(file, currentFile.id);
						renderFile(folderId);
						if(currentFile.isParent) {
							var node = treeObj.getNodeByParam("id", currentFile.id, null);
							treeObj.removeNode(node);
						}
						layer.msg("操作成功！", {offset: layerMsgOffset,icon: 6,shift: 8,time: layerMsgTime});
					} else {
                    	layer.msg(data.message, {offset: layerMsgOffset,icon: 5,shift: 8,time: layerMsgTime});
                    }
					layer.close(loadi);
				},
				error: function(request, errType) {
					layer.close(loadi);
					//"timeout", "error", "notmodified" 和 "parsererror"
					if(errType == 'timeout') {
						layer.msg('请求超时', {offset: 'rb',icon: 6,shift: 8,time: layerMsgTime});
					}
					if(errType == 'error') {
						layer.msg('系统错误，请联系管理员', {offset: 'rb',icon: 6,shift: 8,time: layerMsgTime});
					}
				}
			});
		}, function(){
		});
	}
	
	function rename() {
		var currentFile = getSelectFile();
		if(currentFile == null || currentFile.id == null || currentFile.id == '') {
			layer.msg("请选择文件！", {offset: layerMsgOffset,icon: 5,shift: 8,time: layerMsgTime});
			return;
		}
		layer.prompt(
			{title: '请输入', formType: 0, value: currentFile.name},
			function(fileName, index) {
				$.ajax({
					type: 'post',
					url: '${ctx}/cms/filer/rename',
					data: {
						path: currentFile.path,
						name: fileName
					},
					dataType: 'json',
					timeout: layerLoadMaxTime,
					beforeSend: function(XMLHttpRequest){
						loadi = layer.load(2, {shade: layerLoadShade,time: layerLoadMaxTime});
					},
					success: function(data, textStatus){
						if (data.success==TRUE){
							for(var i=0; i<folder.length; i++) {
								if(folder[i].id == currentFile.id) {
									folder[i].name = fileName;
								}
							}
							for(var i=0; i<file.length; i++) {
								if(file[i].id == currentFile.id) {
									file[i].name = fileName;
								}
							}
							renderFile(folderId);
							if(currentFile.isParent) {
								var node = treeObj.getNodeByParam("id", currentFile.id, null);
								node.name = fileName;
								treeObj.updateNode(node);
							}
							layer.msg("操作成功！", {offset: layerMsgOffset,icon: 6,shift: 8,time: layerMsgTime});
							layer.close(index);
						} else {
	                    	layer.msg(data.message, {offset: layerMsgOffset,icon: 5,shift: 8,time: layerMsgTime});
	                    }
						layer.close(loadi);
					},
					error: function(request, errType) {
						layer.close(loadi);
						//"timeout", "error", "notmodified" 和 "parsererror"
						if(errType == 'timeout') {
							layer.msg('请求超时', {offset: 'rb',icon: 6,shift: 8,time: layerMsgTime});
						}
						if(errType == 'error') {
							layer.msg('系统错误，请联系管理员', {offset: 'rb',icon: 6,shift: 8,time: layerMsgTime});
						}
					}
				});
			}
		);
	}
	
	function deleteArrayItem(array, id) {
		var result = [];
		for(var i=0; i<array.length; i++) {
			if(array[i].id != id) {
				result.push(array[i]);
			}
		}
		return result;
	}
	
	function test() {
		var f = {"isParent":true,"size":0,"name":"123","path":"","pId":1,"id":99};
	}
</script>
<div id="img_view" class="img_view" style="display: none;">
	<img style="width: 350px;"/>
</div>
</html>

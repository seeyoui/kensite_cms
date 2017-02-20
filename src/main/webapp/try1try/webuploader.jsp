<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/taglib/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Kensite开发平台</title>
<%@ include file="/WEB-INF/view/taglib/header.jsp"%>
<%@ include file="/WEB-INF/view/taglib/layer.jsp"%>
<%@ include file="/WEB-INF/view/taglib/webuploader.jsp"%>
</head>

<body>
	<div id="uploader" class="wu-example">
		<div id="fileList" class="uploader-list"></div>
		<div id="filePicker">选择视频</div>
	</div>
	<script type="text/javascript">
		var server_url = '${ctx}/sys/uploadfile/uploadChunk';
		var check_url = '${ctx}/sys/uploadfile/checkChunk';
		var url = 'video\\video';
		var chunkSize = 1048576;//5242880
		
		var fileName = '';
		var fileSize = '';
		var fileExt = '';
		$(document).ready(function() {
			/** 实现webupload hook，触发上传前，中，后的调用关键 **/
			WebUploader.Uploader.register({
				"before-send-file" : "beforeSendFile", // 整个文件上传前
				"before-send" : "beforeSend", // 每个分片上传前
				"after-send-file" : "afterSendFile" // 分片上传完毕
			}, {
				beforeSendFile : function(file) {
					var task = $.Deferred();
					var me = this,
			            owner = this.owner,
			            server = me.options.server;
					//拿到上传文件的唯一名称，用于断点续传
					//realname = $.md5(file.name + file.size);
					fileName = file.name;
					fileSize = file.size;
					fileExt = file.ext;
					$.ajax({
						type : "POST",
						url : check_url, // 后台url地址
						data : {
							type : "init",
							url : url,
							fileName : fileName,
							fileExt : fileExt,
							fileSize : fileSize
						},
						cache : false,
						async : false, // 同步
						timeout : 1000, //todo 超时的话，只能认为该文件不曾上传过
						dataType : "text"
					}).then(function(data, textStatus, jqXHR) {
						console.info(data);
						if (data == TRUE) { //若存在，这返回失败给WebUploader，表明该文件不需要上传
							owner.skipFile( file );
							//task.reject();
							// 业务逻辑...
						} else {
							task.resolve();
						}
					}, function(jqXHR, textStatus, errorThrown) { //任何形式的验证失败，都触发重新上传
						task.resolve();
					});

					return $.when(task);
				},
				beforeSend : function(block) {
					console.info('before-send='+block.chunk);
					//分片验证是否已传过，用于断点续传
					var task = $.Deferred();
					$.ajax({
						type : "POST",
						url : check_url,
						data : {
							type : "block",
							url : url,
							fileName : fileName,
							fileExt : fileExt,
							fileSize : fileSize,
							chunk : block.chunk,
							chunkSize : block.end - block.start
						},
						cache : false,
						async : false, // 同步
						timeout : 1000, //todo 超时的话，只能认为该分片未上传过
						dataType : "text"
					}).then(function(data, textStatus, jqXHR) {
						console.info(data);
						if (data == TRUE) { //若存在，返回失败给WebUploader，表明该分块不需要上传
							console.info('文件存在不用上传');
							task.reject();
						} else {
							console.info('文件不存在要上传');
							task.resolve();
						}
					}, function(jqXHR, textStatus, errorThrown) { //任何形式的验证失败，都触发重新上传
						console.info('请求失败上传文件');
						task.resolve();
					});
					return $.when(task);
				},
				afterSendFile : function(file) {
					console.info('after-send-file');
					var chunksTotal = Math.ceil(file.size / chunkSize);
					if (chunksTotal > 1) {
						//合并请求
						var task = $.Deferred();
						$.ajax({
							type : "POST",
							url : check_url,
							data : {
								type : "merge",
								url : url,
								fileName : fileName,
								fileExt : fileExt,
								fileSize : fileSize,
								chunks : chunksTotal
							},
							cache : false,
							async : false, // 同步
							dataType : "text"
						}).then(function(data, textStatus, jqXHR) {
							// 业务逻辑...
							console.info(data);
							task.resolve();
						}, function(jqXHR, textStatus, errorThrown) {
							task.reject();
						});
					} else {
						task.resolve();
		            }
					return $.when(task);
				}
			});
			var uploader = new WebUploader.Uploader({
				// 选完文件后，是否自动上传。
				auto: true,
				//runtimeOrder: flash,html5,  // 优先使用flash上传
				// swf文件路径
				swf : '${ctx_script}/webuploader/Uploader.swf',
				//是否要分片处理大文件上传。
				chunked : true,
				// 如果要分片，分多大一片？ 默认大小为5M 5242880.
				chunkSize : chunkSize,
				// 如果某个分片由于网络问题出错，允许自动重传多少次？
				chunkRetry : 3,
				// 上传并发数。允许同时最大上传进程数[默认值：3]
				threads : 1,
				// 去重
				duplicate : true,
				// 文件接收服务端。
				server : server_url,
				// 选择文件的按钮。可选。
				// 内部根据当前运行是创建，可能是input元素，也可能是flash.
				pick : {
					id : '#filePicker',
					multiple : false
				},
				accept: {
					title: 'Images',
					extensions: 'avi,rmvb,rm,mp4,mkv,wmv,mpg,mpeg,mov',
					mimeTypes: 'video/*'
				},
				//fileNumLimit: 1,
				//fileSizeLimit: 1024*1024,
				//fileSingleSizeLimit: 500*1024,
				// 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
				resize : false,
				// 上传本分片时预处理下一分片
				prepareNextFile : true,
				//formData: function(){return {realname: '333'};}
				//
				formData : {
					url : url
				}
			});
			// 文件上传过程中创建进度条实时显示。
			uploader.on('uploadProgress', function(file, percentage) {
				// 具体逻辑... 
				console.info('已上传=='+percentage+'%');
			});

			// 文件上传成功处理。
			uploader.on('uploadSuccess', function(file, response) {
				// 具体逻辑...
				console.info('上传成功');
			});
			// 文件上传失败处理。
			uploader.on('uploadError', function(file) {
				// 具体逻辑...
				console.info('上传失败');
			});
			// 长传完毕，不管成功失败都会调用该事件，主要用于关闭进度条
			uploader.on('uploadComplete', function(file) {
				// 具体逻辑...
				console.info('上传完毕');
			});
			
		});
	</script>
</body>
</html>

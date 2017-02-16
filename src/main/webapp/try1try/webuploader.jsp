<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/taglib/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>Kensite开发平台</title>
	<%@ include file="/WEB-INF/view/taglib/header.jsp" %>
	<%@ include file="/WEB-INF/view/taglib/layer.jsp" %>
	<%@ include file="/WEB-INF/view/taglib/webuploader.jsp"%>
</head>

<body>
	<div id="uploader" class="wu-example">
		<img id="img" src="${ctx }/upload/none.jpg"/>
		<div id="fileList" class="uploader-list"></div>
		<div id="filePicker" style="display: none;">选择图片</div>
	</div>
	<script type="text/javascript">
	$(document).ready(function() {
		var $list = $('#fileList'),
		// 优化retina, 在retina下这个值是2
		ratio = window.devicePixelRatio || 1,

		// 缩略图大小
		thumbnailWidth = 300 * ratio,
		thumbnailHeight = 300 * ratio,

		// Web Uploader实例
		uploader;
	
		// 初始化Web Uploader
		uploader = WebUploader.create({
			auto: false,
			swf: '${ctx_script}/webuploader/Uploader.swf',
			server: '${ctx}/sys/uploadfile/uploadFile',
			// 选择文件的按钮。可选。
			// 内部根据当前运行是创建，可能是input元素，也可能是flash.
			pick: {
				id: '#filePicker',
				innerHTML: '你好',
				multiple: true
			},
			accept: {
				title: 'Images',
				extensions: 'gif,jpg,jpeg,bmp,png',
				mimeTypes: 'image/*'
			},
			fileNumLimit: 3,
			fileSizeLimit: 1024*1024,
			fileSingleSizeLimit: 500*1024
		});
	
		// 当有文件添加进来的时候
		uploader.on( 'fileQueued', function( file ) {
			var $img = $('#img');
			// 创建缩略图
			uploader.makeThumb( file, function( error, src ) {
				if ( error ) {
					$img.replaceWith('<span>不能预览</span>');
					return;
				}
				$img.attr( 'src', src );
			}, thumbnailWidth, thumbnailHeight );
			return;

	        var $li = $(
	                '<div id="' + file.id + '" class="file-item thumbnail">' +
	                    '<img>' +
	                    '<div class="info">' + file.name + '</div>' +
	                '</div>'
	                ),
	            $img = $li.find('img');
	        $list.append( $li );
	        // 创建缩略图
	        uploader.makeThumb( file, function( error, src ) {
	            if ( error ) {
	                $img.replaceWith('<span>不能预览</span>');
	                return;
	            }
	            $img.attr( 'src', src );
	        }, thumbnailWidth, thumbnailHeight );
		});
	
		// 文件上传过程中创建进度条实时显示。
		uploader.on( 'uploadProgress', function( file, percentage ) {
			var $li = $( '#'+file.id ),
				$percent = $li.find('.progress span');
			// 避免重复创建
			if ( !$percent.length ) {
				$percent = $('<p class="progress"><span></span></p>')
						.appendTo( $li )
						.find('span');
			}
			$percent.css( 'width', percentage * 100 + '%' );
		});
	
		// 文件上传成功，给item添加成功class, 用样式标记上传成功。
		uploader.on( 'uploadSuccess', function( file,response ) {
			console.info(response);
			$( '#'+file.id ).addClass('upload-state-done');
		});
	
		// 文件上传失败，现实上传出错。
		uploader.on( 'uploadError', function( file ) {
			var $li = $( '#'+file.id ),
				$error = $li.find('div.error');
			// 避免重复创建
			if ( !$error.length ) {
				$error = $('<div class="error"></div>').appendTo( $li );
			}
			$error.text('上传失败');
		});
		// 完成上传完了，成功或者失败，先删除进度条。
		uploader.on( 'uploadComplete', function( file ) {
			$( '#'+file.id ).find('.progress').remove();
		});

		$('#img').css({"width":"300px","heigth":"300px"});
		$('#img').on('click', function() {
			$('.webuploader-element-invisible').click();
		});
	});
	</script>
</body>
</html>

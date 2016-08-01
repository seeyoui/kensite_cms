<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/taglib/common.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK">
	<title>平台</title>
	<%@ include file="/WEB-INF/view/taglib/header.jsp"%>
	<%@ include file="/WEB-INF/view/taglib/layer.jsp" %>
	<%@ include file="/WEB-INF/view/taglib/bootstrap.jsp"%>
	<%@ include file="/WEB-INF/view/taglib/cropper.jsp" %>
	<style type="text/css">
	</style>
	<script type="text/javascript">
	$(document).ready(function(){
	    var o = $(".image-crop > img");
	    $(o).cropper({
	        aspectRatio: 1,
	        preview: ".img-preview",
	        //minCanvasWidth: 150,
	        minCropBoxWidth: 150
	    });
		var r = $("#inputImage");
		window.FileReader ? r.change(function() {
			var e, i = new FileReader,
			t = this.files;
			t.length && (e = t[0], /^image\/\w+$/.test(e.type) ? (i.readAsDataURL(e), i.onload = function() {
				r.val(""),
				o.cropper("reset", !0).cropper("replace", this.result)
			}) : showMessage("请选择图片文件"))
		}) : r.addClass("hide"),
		$("#download").click(function() {
			window.open(o.cropper("getDataURL"))
		}),
		$("#zoomIn").click(function() {
			o.cropper("zoom", .1)
		}),
		$("#zoomOut").click(function() {
			o.cropper("zoom", -.1)
		}),
		$("#rotateLeft").click(function() {
			o.cropper("rotate", 90)
		}),
		$("#rotateRight").click(function() {
			o.cropper("rotate", -90)
		}),
		$("#setDrag").click(function() {
			//updateCoords(o.cropper("getData"));
			o.cropper("setDragMode", "crop");
			save();
		});
	});
	
	function updateCoords(c) {
		$('#x').val(c.x);
		$('#y').val(c.y);
		$('#width').val(c.width);
		$('#height').val(c.height);
		$('#rotate').val(c.rotate);
		$('#scaleX').val(c.scaleX);
		$('#scaleY').val(c.scaleY);
	};
	
	function save() {
	    var o = $(".image-crop > img");
	    var result = o.cropper('getCroppedCanvas', {
			width: 200,
			height: 200
		});
		var data=result.toDataURL(); //转成base64
		$.ajax({
			url:'${ctx}/sys/uploadfile/imageCropper/headIcon',
			dataType:'json',
			type: "POST",
			data: {"image":data.toString()},
			success: function (result) {
				var imgSrc = '${ctx}/'+result.fileUrl+'?'+Math.random();
				$(window.parent.document).find('#headIcon').attr('src', imgSrc);
			},
			error: function () {
			}
		});
	}
	</script>
</head>
<body class="gray-bg" style="overflow-x: hidden;">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-content">
						<div class="row">
							<div class="col-md-6">
								<div class="image-crop">
									<c:if test="${not empty currentUser.headIcon}">
										<img src="${ctx}/upload/headIcon/${currentUser.headIcon}" id="headIcon" style="" onerror="javascript:this.src='${ctx }/upload/headerIcon.png'"/>
									</c:if>
									<c:if test="${empty currentUser.headIcon}">
										<img src="${ctx }/upload/headerIcon.png" id="headIcon" style=""/>
									</c:if>
								</div>
							</div>
							<div class="col-md-6">
								<h4>图片预览：</h4>
								<div class="img-preview img-preview-sm" style="width:130px;heigth:130px;"></div>
								<h4>说明：</h4>
								<p>
									你可以选择新图片上传，然后下载裁剪后的图片
									<code>图片大小不能超过2M</code>
								</p>
								<div class="btn-group">
									<label title="上传图片" for="inputImage" class="btn btn-primary">
										<input type="file" accept="image/*" name="file" id="inputImage" class="hide"> 上传新图片
									</label>
									<!-- <label title="下载图片" id="download" class="btn btn-primary">下载</label> -->
								</div>
								<div class="btn-group">
									<button class="btn btn-white" id="zoomIn" type="button">放大</button>
									<button class="btn btn-white" id="zoomOut" type="button">缩小</button>
									<button class="btn btn-white" id="rotateLeft" type="button">左旋转</button>
									<button class="btn btn-white" id="rotateRight" type="button">右旋转</button>
									<button class="btn btn-warning" id="setDrag" type="button">保存</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<input type="hidden" id="x" name="x" />
	<input type="hidden" id="y" name="y" />
	<input type="hidden" id="width" name="width" />
	<input type="hidden" id="height" name="height" />
	<input type="hidden" id="rotate" name="rotate" />
	<input type="hidden" id="scaleX" name="scaleX" />
	<input type="hidden" id="scaleY" name="scaleY" />
</body>
</html>
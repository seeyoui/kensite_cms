<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/taglib/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>内容发布文章</title>
		<%@ include file="/WEB-INF/view/taglib/header.jsp" %>
		<%@ include file="/WEB-INF/view/taglib/easyui.jsp" %>
		<%@ include file="/WEB-INF/view/taglib/layer.jsp" %>
		<%@ include file="/WEB-INF/view/taglib/uedit.jsp" %>
		<%@ include file="/WEB-INF/view/taglib/uploadify.jsp" %>
		<style type="text/css">
			#uploadify {
				position: absolute;
				top: 115px;
				left: 415px;
			}
			#uploadfileQueue{
				left: 250px;
			    position: absolute;
			    top: 60px;
			    width: 235px;
			    z-index: 9999999;
			}
		</style>
	</head>
	<body>
	 	<div style="position:absolute;top:10px;left:20px;right:20px;bottom:10px;">
			<form id="dataForm" method="post">
				<div class="fitem">
					<ks:formTag table="CMS_ARTICLE" column="REMARKS"/>
				</div>
				<div class="fitem">
					<ks:formTag table="CMS_ARTICLE" column="TITLE"/>
					<%-- <ks:formTag table="CMS_ARTICLE" column="POSTER"/> --%>
					<label>文章封面</label>
	                <input id="poster" type="hidden" name="poster"/>
	                <!-- 自行编写上传附件模块 -->
					<div id="uploadfileQueue"></div>
					<input type="file" id="uploadify" name="uploadify" 
					data-queueid="uploadfileQueue" 
					data-filetypeexts="*.jpg;*.jpge;*.gif;*.png" 
					data-buttontext="选择封面" data-auto="true" 
					data-multi="false" data-queuesizelimit="5" 
					data-filesizelimit="5000KB" data-method="POST"
					data-url="cms\article\" />
	                <img id="posterShow" src="${ctx }/upload/none.jpg"  style="width:185px;height:110px;position:absolute;top:0px;left:300px;"/>
	                <span id="msg-poster" class="err-msg"></span>
				</div>
				<div class="fitem">
					<ks:formTag table="CMS_ARTICLE" column="SUB_TITLE"/>
				</div>
				<div class="fitem">
					<ks:formTag table="CMS_ARTICLE" column="KEYWORDS"/>
				</div>
				<div class="fitem">
					<label>所属栏目</label>
					<input id="categoryId" name="categoryId" value=""/>
				</div>
				<div class="fitem">
					<label>标签</label>
					<input id="tagId" name="tagId" value=""/>
				</div>
				<div class="fitem">
					<ks:formTag table="CMS_ARTICLE" column="DESCRIPTION"/>
				</div>
				<div class="fitem">
					<ks:formTag table="CMS_ARTICLE" column="SEQ"/>
					<ks:formTag table="CMS_ARTICLE" column="HITS"/>
					<ks:formTag table="CMS_ARTICLE" column="COPYFROM"/>
				</div>
				<div class="fitem">
					<ks:formTag table="CMS_ARTICLE" column="CONTENT"/>
				</div>
				<div class="fitem">
					<ks:formTag table="CMS_ARTICLE" column="CUSTOM_CONTENT_VIEW"/>
				</div>
				<div class="fitem">
					<ks:formTag table="CMS_ARTICLE" column="VIEW_CONFIG"/>
				</div>
				<div class="fitem">
					<%-- <ks:formTag table="CMS_ARTICLE" column="SITE_ID"/> --%>
					<input id="siteId" name="siteId" type="hidden"/>
				</div>
				<input id="id" name="id" type="hidden"/>
			</form>
		</div>
		<script type="text/javascript">
			var loadi,url,index = parent.layer.getFrameIndex(window.name);
			$(document).ready(function(){
				var row = parent.$('#dataList').datagrid('getSelected');
				url = '${ctx}/cms/article/save';
				$('#categoryId').val(parent.$('#sel_categoryId').val());
				$('#siteId').val(parent.$('#sel_siteId').val());
				
				if(row != null) {
					$('#dataForm').form('load', row);
					$("#posterShow").attr("src","${ctx}/"+row.poster);
					url = '${ctx}/cms/article/update';
				}
				//添加上传附件组件渲染及设置回调函数
				mineUpload($("#uploadify"), "${ctx}", "afterMineUpload");
				$('#categoryId').combotree({
					tipPosition: 'top',
					editable: false,
					icons: [{iconCls:'icon-clear',handler: function(e){$(e.data.target).combobox('clear');}}],
					required: true,
					idField: 'id',
					textField: 'name',
					parentField: 'parentId',
					url: '${ctx}/cms/category/list/tree?siteId='+parent.$('#sel_siteId').val(),
					panelHeight: 'auto'
				});

				$('#tagId').combobox({
					tipPosition: 'top',
					icons: [{iconCls:'icon-clear',handler: function(e){$(e.data.target).combobox('clear');}}],
					required: false,
					valueField: 'id',
					textField: 'name',
					multiple: true,
					url: '${ctx}/cms/tagcloud/list/all?siteId='+parent.$('#sel_siteId').val(),
					panelHeight: 'auto'
				});
				//设置html编辑器渲染完成后事件，主要是给组件赋值，赋相应数据列值
		        content.ready(function() {
			        if(row != null && row.content != null) {
			        	content.setContent(row.content);
			        }
		        });
				//设置html编辑器事件监听，全屏与退出全屏事件，控制外层弹出框对应全屏与退出全屏
				content.addListener("fullScreenChanged",function(type,mode){
	                if(mode) {
	                	parent.layer.full(index);
	                } else {
	                	parent.layer.restore(index);
	                }
	            });
			});
		    
			//上传附件成功回调函数
		    function afterMineUpload(file, uf, message) {
		    	var fileName =uf.url+ uf.realname;//拼接图片在服务器所在路径字符串
		    	$("#poster").val(fileName);//给隐藏输入框赋值
		    	$("#posterShow").attr("src","${ctx}/"+fileName);//给界面img标签赋值用于显示图片
			}
			
			function submitInfo(){
				$('#dataForm').form('submit',{
					url: url,
					onSubmit: function(param){
						if($(this).form('validate')) {
							loadi = parent.layer.load(2, {shade: layerLoadShade,time: layerLoadMaxTime});
						}
						return $(this).form('validate');
					},
					success: function(data){
						parent.layer.close(loadi);
						cleanErrMsg();
						var data = eval('(' + data + ')');
						if (data.success==TRUE){
							parent.$.article.reloadData();
							parent.layer.msg("操作成功！", {offset: layerMsgOffset,icon: 6,shift: 8,time: layerMsgTime});
							parent.layer.close(index);
						} else {
							renderErrMsg(data.message);
						}
					}
				});
			}
		</script>
	</body>
</html>

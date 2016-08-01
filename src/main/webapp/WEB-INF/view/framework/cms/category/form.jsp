<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/taglib/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>内容发布栏目</title>
		<%@ include file="/WEB-INF/view/taglib/header.jsp" %>
		<%@ include file="/WEB-INF/view/taglib/easyui.jsp" %>
		<%@ include file="/WEB-INF/view/taglib/layer.jsp" %>
		<%@ include file="/WEB-INF/view/taglib/uedit.jsp" %>
	</head>
	<body>
	 	<div style="position:absolute;top:10px;left:20px;right:20px;bottom:10px;">
			<form id="dataForm" method="post">
				<div class="fitem">
					<ks:formTag table="CMS_CATEGORY" column="REMARKS"/>
				</div>
				<div class="fitem">
					<ks:formTag table="CMS_CATEGORY" column="SITE_ID"/>
					<label>所属栏目</label>
					<input id="parentId" name="parentId" value=""/>
					<!-- <input id="parentId" name="parentId" class="easyui-combotree" data-options="tipPosition:'top',editable:false,icons: [{iconCls:'icon-clear',handler: function(e){$(e.data.target).combobox('clear');}}],required:true,idField: 'id',textField: 'name',parentField: 'parentId',url:'/kensite/cms/category/list/tree',panelHeight:'auto'"/> -->
				</div>
				<div class="fitem">
					<ks:formTag table="CMS_CATEGORY" column="MODULE"/>
					<ks:formTag table="CMS_CATEGORY" column="NAME"/>
				</div>
				<div class="fitem">
					<ks:formTag table="CMS_CATEGORY" column="HREF"/>
					<ks:formTag table="CMS_CATEGORY" column="EXTENDS_INFO"/>
				</div>
				<div class="fitem">
					<ks:formTag table="CMS_CATEGORY" column="TARGET"/>
					<ks:formTag table="CMS_CATEGORY" column="SEQ"/>
				</div>
				<div class="fitem">
					<ks:formTag table="CMS_CATEGORY" column="DESCRIPTION"/>
				</div>
				<div class="fitem">
					<ks:formTag table="CMS_CATEGORY" column="KEYWORDS"/>
				</div>
				<div class="fitem">
					<ks:formTag table="CMS_CATEGORY" column="IN_MENU"/>
					<ks:formTag table="CMS_CATEGORY" column="IN_LIST"/>
				</div>
				<div class="fitem">
					<ks:formTag table="CMS_CATEGORY" column="IS_COMMENT"/>
					<ks:formTag table="CMS_CATEGORY" column="IS_AUDIT"/>
				</div>
				<div class="fitem">
					<ks:formTag table="CMS_CATEGORY" column="CUSTOM_LIST_VIEW"/>
				</div>
				<div class="fitem">
					<ks:formTag table="CMS_CATEGORY" column="CUSTOM_CONTENT_VIEW"/>
				</div>
				<div class="fitem">
					<ks:formTag table="CMS_CATEGORY" column="VIEW_CONFIG"/>
				</div>
				<input id="id" name="id" type="hidden"/>
			</form>
		</div>
		<script type="text/javascript">
			var loadi,url,index = parent.layer.getFrameIndex(window.name);
			$(document).ready(function(){
				var row = parent.$('#dataList').datagrid('getSelected');
				url = '${ctx}/cms/category/save';
				$('#parentId').val(parent.$('#sel_parentId').val());
				$('#siteId').combobox('setValue', parent.$('#sel_siteId').val());
				
				$('#parentId').combotree({
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
				if(row != null) {
					$('#dataForm').form('load', row);
					url = '${ctx}/cms/category/update';
				}
			});
			
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
							parent.$.category.reloadData();
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

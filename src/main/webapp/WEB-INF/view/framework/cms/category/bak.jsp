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
		<%@ include file="/WEB-INF/view/taglib/zTree.jsp" %>
		<style type="text/css">
			.ztree li span.button.add {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
		</style>
	</head>
	<body>
	 	<div style="position:absolute;top:0px;left:0px;right:0px;bottom:0px;">
			<div style="position:absolute;top:0px;bottom:0px;width:200px;">
	        	<ul id="categoryTree" class="ztree"></ul>
	        </div>
			<div style="position:absolute;top:0px;left:200px;right:0px;bottom:0px;">
				<table id="dataList" title="内容发布栏目列表<c:if test="${ksfn:getConfig('debug')=='T'}"><a href='javascript:void(0)' onclick='modDebug()'>debug</a></c:if>" 
						class="easyui-datagrid" style="width:100%;height:100%"
						toolbar="#toolbar" pagination="true"
						rownumbers="true" fitColumns="true" singleSelect="false">
					<thead>
						<tr>
							<th data-options="field:'id',hidden:true">ID</th>
							<th data-options="field:'ck',checkbox:true"></th>
							<ks:listTag table="CMS_CATEGORY" column="REMARKS"/>
							<ks:listTag table="CMS_CATEGORY" column="SITE_ID"/>
							<ks:listTag table="CMS_CATEGORY" column="MODULE"/>
							<ks:listTag table="CMS_CATEGORY" column="NAME"/>
							<ks:listTag table="CMS_CATEGORY" column="PARENT_ID"/>
							<ks:listTag table="CMS_CATEGORY" column="HREF"/>
							<ks:listTag table="CMS_CATEGORY" column="TARGET"/>
							<ks:listTag table="CMS_CATEGORY" column="DESCRIPTION"/>
							<ks:listTag table="CMS_CATEGORY" column="KEYWORDS"/>
							<ks:listTag table="CMS_CATEGORY" column="SEQ"/>
							<ks:listTag table="CMS_CATEGORY" column="IN_MENU"/>
							<ks:listTag table="CMS_CATEGORY" column="IN_LIST"/>
							<ks:listTag table="CMS_CATEGORY" column="IS_COMMENT"/>
							<ks:listTag table="CMS_CATEGORY" column="IS_AUDIT"/>
							<ks:listTag table="CMS_CATEGORY" column="CUSTOM_LIST_VIEW"/>
							<ks:listTag table="CMS_CATEGORY" column="CUSTOM_CONTENT_VIEW"/>
							<ks:listTag table="CMS_CATEGORY" column="VIEW_CONFIG"/>
						</tr>
					</thead>
				</table>
				<div id="toolbar">
					<a href="javascript:void(0)" class="easyui-linkbutton info" iconCls="icon-add" plain="true" onclick="$.category.newInfo()">新建</a>
					<a href="javascript:void(0)" class="easyui-linkbutton warning" iconCls="icon-edit" plain="true" onclick="$.category.editInfo()">修改</a>
					<a href="javascript:void(0)" class="easyui-linkbutton error" iconCls="icon-remove" plain="true" onclick="$.category.destroyInfo()">删除</a>
					<!-- <a href="javascript:void(0)" class="easyui-linkbutton primary" iconCls="icon-export" plain="true" onclick="$.category.exportExcel()">导出</a> -->
					<ks:queryTag table="CMS_CATEGORY" column="REMARKS"/>
					<ks:queryTag table="CMS_CATEGORY" column="SITE_ID"/>
					<ks:queryTag table="CMS_CATEGORY" column="MODULE"/>
					<ks:queryTag table="CMS_CATEGORY" column="NAME"/>
					<ks:queryTag table="CMS_CATEGORY" column="HREF"/>
					<ks:queryTag table="CMS_CATEGORY" column="TARGET"/>
					<ks:queryTag table="CMS_CATEGORY" column="DESCRIPTION"/>
					<ks:queryTag table="CMS_CATEGORY" column="KEYWORDS"/>
					<ks:queryTag table="CMS_CATEGORY" column="SEQ"/>
					<ks:queryTag table="CMS_CATEGORY" column="IN_MENU"/>
					<ks:queryTag table="CMS_CATEGORY" column="IN_LIST"/>
					<ks:queryTag table="CMS_CATEGORY" column="IS_COMMENT"/>
					<ks:queryTag table="CMS_CATEGORY" column="IS_AUDIT"/>
					<ks:queryTag table="CMS_CATEGORY" column="CUSTOM_LIST_VIEW"/>
					<ks:queryTag table="CMS_CATEGORY" column="CUSTOM_CONTENT_VIEW"/>
					<ks:queryTag table="CMS_CATEGORY" column="VIEW_CONFIG"/>
					<input id="sel_parentId" name="sel_parentId" type="hidden" value='${ksfn:getConst("ROOT_ID_32")}'/>
					<input id="sel_nodeTId" name="sel_nodeTId" type="hidden" value=''/>
					<a href="javascript:void(0)" class="easyui-linkbutton success" iconCls="icon-search" plain="true" onclick="$.category.selectData()">查询</a>
				</div>
			</div>
		</div>
		<script type="text/javascript">
			var tableName = "CMS_CATEGORY";
			$(document).ready(function(){
				$.fn.zTree.init($("#categoryTree"), setting);
				$('#dataList').datagrid({
		    		url:'${ctx}/cms/category/list/data',
		    		queryParams: {
		    			parentId:$('#sel_parentId').val()
		    		}
		    	});
			});
			var url, loadi;
			var iframeWin = null, iframeBody=null;
			$.category = {
		   		selectData : function () {
					$('#dataList').datagrid('load',{
						<ks:queryJsTag table="CMS_CATEGORY" column="REMARKS"/>
						<ks:queryJsTag table="CMS_CATEGORY" column="SITE_ID"/>
						<ks:queryJsTag table="CMS_CATEGORY" column="MODULE"/>
						<ks:queryJsTag table="CMS_CATEGORY" column="NAME"/>
						<ks:queryJsTag table="CMS_CATEGORY" column="HREF"/>
						<ks:queryJsTag table="CMS_CATEGORY" column="TARGET"/>
						<ks:queryJsTag table="CMS_CATEGORY" column="DESCRIPTION"/>
						<ks:queryJsTag table="CMS_CATEGORY" column="KEYWORDS"/>
						<ks:queryJsTag table="CMS_CATEGORY" column="SEQ"/>
						<ks:queryJsTag table="CMS_CATEGORY" column="IN_MENU"/>
						<ks:queryJsTag table="CMS_CATEGORY" column="IN_LIST"/>
						<ks:queryJsTag table="CMS_CATEGORY" column="IS_COMMENT"/>
						<ks:queryJsTag table="CMS_CATEGORY" column="IS_AUDIT"/>
						<ks:queryJsTag table="CMS_CATEGORY" column="CUSTOM_LIST_VIEW"/>
						<ks:queryJsTag table="CMS_CATEGORY" column="CUSTOM_CONTENT_VIEW"/>
						<ks:queryJsTag table="CMS_CATEGORY" column="VIEW_CONFIG"/>
						parentId: $("#sel_parentId").val()
					});
				},
				reloadData : function () {
					$.category.selectData();
					$.fn.zTree.getZTreeObj("categoryTree").reAsyncChildNodes(null, "refresh");
					console.info($("#sel_nodeTId").val());
					var node = $.fn.zTree.getZTreeObj("categoryTree").getNodeByTId($("#sel_nodeTId").val());
					console.info(node);
					$.fn.zTree.getZTreeObj("categoryTree").selectNode(node);
				},
				newInfo : function (){
					$('#dataList').datagrid('clearSelections');
					$.category.layerOpen(url);
				},
				editInfo : function (){
					var row = $('#dataList').datagrid('getSelected');
					if (row){
						$.category.layerOpen(url);
					} else {
						layer.msg("请先选择要修改的记录！", {offset: 'rb',icon: 5,shift: 8,time: layerMsgTime});
					}
				},
				exportExcel : function () {
					window.open("${ctx}/cms/category/export");
				},
				layerOpen : function (url) {
					url = '${ctx}/cms/category/form';
					layer.open({
						type: 2,
						title: '内容发布栏目基本信息',
						area: ['570px', '470px'],
						fix: false, //不固定
						maxmin: false,
						content: url,
						btn: ['保存', '取消'],
						success: function(layero, index){
							iframeBody = layer.getChildFrame('body', index);
							iframeWin = window[layero.find('iframe')[0]['name']];
						},
						yes: function(index, layero) {
							if(iframeWin != null) {
								iframeWin.submitInfo();
							}
						},
						cancel: function(index){
							layer.close(index);
						}
					});
				},
				destroyInfo : function (){
					//var row = $('#dataList').datagrid('getSelected');
					var rows = $('#dataList').datagrid('getSelections');
					if (rows){
						layer.confirm('是否确认删除？', {
							btn: ['确定','取消'] //按钮
						}, function(){
							var id = "";
							//id = row.id;
							for(var i=0; i<rows.length; i++) {
								id += rows[i].id+",";
							}
							$.ajax({
								type: "post",
								url: '${ctx}/cms/category/delete',
								data: {id:id},
								dataType: 'json',
								timeout: layerLoadMaxTime,
								beforeSend: function(XMLHttpRequest){
									loadi = layer.load(2, {shade: layerLoadShade,time: layerLoadMaxTime});
								},
								success: function(data, textStatus){
									layer.close(loadi);
									if (data.success==TRUE){
										layer.msg("操作成功！", {offset: 'rb',icon: 6,shift: 8,time: layerMsgTime});
										$.category.reloadData();
									} else {
										layer.msg("操作失败！", {offset: 'rb',icon: 5,shift: 8,time: layerMsgTime});
									}
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
					} else {
						layer.msg("请先选择要删除的记录！", {offset: 'rb',icon: 5,shift: 8,time: layerMsgTime});
					}
				}
			}
		</script>
		<script type="text/javascript">
			var setting = {
				async: {
					enable: true,
					url:"${ctx}/cms/category/list/tree",
					otherParam:{"keywords":""}
				},
				view: {
					dblClickExpand: dblClickExpand,
					selectedMulti: false
				},
				edit: {
					drag: {
						autoExpandTrigger: true,
						prev: dropPrev,
						inner: dropInner,
						next: dropNext
					},
					enable: true,
					editNameSelectAll: true,
					showRemoveBtn: showRemoveBtn,
					removeTitle: "删除节点",
					showRenameBtn: showRenameBtn,
					renameTitle: "编辑节点"
				},
				data: {
					simpleData: {
						enable: true,
						idKey: "id",
						pIdKey: "parentId",
						rootPId: "${ksfn:getConst('ROOT_ID_32')}"
					}
				},
				callback: {
					beforeDrag: beforeDrag,
					beforeDrop: beforeDrop,
					beforeDragOpen: beforeDragOpen,
					beforeEditName: beforeEditName,
					beforeRemove: beforeRemove,
					beforeRename: beforeRename,
					onRemove: onRemove,
					onRename: onRename,
					onDrag: onDrag,
					onDrop: onDrop,
					onExpand: onExpand,
					onClick: onClick
				}
			};
			var log, className = "dark";
			function beforeEditName(treeId, treeNode) {
				className = (className === "dark" ? "":"dark");
				showLog("[ "+getTime()+" beforeEditName ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
				var zTree = $.fn.zTree.getZTreeObj("categoryTree");
				zTree.selectNode(treeNode);
				return confirm("进入节点 -- " + treeNode.name + " 的编辑状态吗？");
			}
			function beforeRemove(treeId, treeNode) {
				className = (className === "dark" ? "":"dark");
				showLog("[ "+getTime()+" beforeRemove ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
				var zTree = $.fn.zTree.getZTreeObj("categoryTree");
				zTree.selectNode(treeNode);
				return confirm("确认删除 节点 -- " + treeNode.name + " 吗？");
			}
			function onRemove(e, treeId, treeNode) {
				showLog("[ "+getTime()+" onRemove ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
			}
			function beforeRename(treeId, treeNode, newName, isCancel) {
				className = (className === "dark" ? "":"dark");
				showLog((isCancel ? "<span style='color:red'>":"") + "[ "+getTime()+" beforeRename ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name + (isCancel ? "</span>":""));
				if (newName.length == 0) {
					alert("节点名称不能为空.");
					var zTree = $.fn.zTree.getZTreeObj("categoryTree");
					setTimeout(function(){zTree.editName(treeNode)}, 10);
					return false;
				}
				return true;
			}
			function onRename(e, treeId, treeNode, isCancel) {
				showLog((isCancel ? "<span style='color:red'>":"") + "[ "+getTime()+" onRename ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name + (isCancel ? "</span>":""));
			}
			function showRemoveBtn(treeId, treeNode) {
				return treeNode.level > 0;
			}
			function showRenameBtn(treeId, treeNode) {
				return treeNode.level > 0;
			}
			function dblClickExpand(treeId, treeNode) {
				return treeNode.level > 0;
			}
			function dropPrev(treeId, nodes, targetNode) {
				var pNode = targetNode.getParentNode();
				if (pNode && pNode.dropInner === false) {
					return false;
				} else {
					for (var i=0,l=curDragNodes.length; i<l; i++) {
						var curPNode = curDragNodes[i].getParentNode();
						if (curPNode && curPNode !== targetNode.getParentNode() && curPNode.childOuter === false) {
							return false;
						}
					}
				}
				return true;
			}
			function dropInner(treeId, nodes, targetNode) {
				if (targetNode && targetNode.dropInner === false) {
					return false;
				} else {
					for (var i=0,l=curDragNodes.length; i<l; i++) {
						if (!targetNode && curDragNodes[i].dropRoot === false) {
							return false;
						} else if (curDragNodes[i].parentTId && curDragNodes[i].getParentNode() !== targetNode && curDragNodes[i].getParentNode().childOuter === false) {
							return false;
						}
					}
				}
				return true;
			}
			function dropNext(treeId, nodes, targetNode) {
				var pNode = targetNode.getParentNode();
				if (pNode && pNode.dropInner === false) {
					return false;
				} else {
					for (var i=0,l=curDragNodes.length; i<l; i++) {
						var curPNode = curDragNodes[i].getParentNode();
						if (curPNode && curPNode !== targetNode.getParentNode() && curPNode.childOuter === false) {
							return false;
						}
					}
				}
				return true;
			}

			var log, className = "dark", curDragNodes, autoExpandNode;
			function beforeDrag(treeId, treeNodes) {
				className = (className === "dark" ? "":"dark");
				showLog("[ "+getTime()+" beforeDrag ]&nbsp;&nbsp;&nbsp;&nbsp; drag: " + treeNodes.length + " nodes." );
				for (var i=0,l=treeNodes.length; i<l; i++) {
					if (treeNodes[i].drag === false) {
						curDragNodes = null;
						return false;
					} else if (treeNodes[i].parentTId && treeNodes[i].getParentNode().childDrag === false) {
						curDragNodes = null;
						return false;
					}
				}
				curDragNodes = treeNodes;
				return true;
			}
			function beforeDragOpen(treeId, treeNode) {
				autoExpandNode = treeNode;
				return true;
			}
			function beforeDrop(treeId, treeNodes, targetNode, moveType, isCopy) {
				className = (className === "dark" ? "":"dark");
				showLog("[ "+getTime()+" beforeDrop ]&nbsp;&nbsp;&nbsp;&nbsp; moveType:" + moveType);
				showLog("target: " + (targetNode ? targetNode.name : "root") + "  -- is "+ (isCopy==null? "cancel" : isCopy ? "copy" : "move"));
				return true;
			}
			function onDrag(event, treeId, treeNodes) {
				className = (className === "dark" ? "":"dark");
				showLog("[ "+getTime()+" onDrag ]&nbsp;&nbsp;&nbsp;&nbsp; drag: " + treeNodes.length + " nodes." );
			}
			function onDrop(event, treeId, treeNodes, targetNode, moveType, isCopy) {
				className = (className === "dark" ? "":"dark");
				showLog("[ "+getTime()+" onDrop ]&nbsp;&nbsp;&nbsp;&nbsp; moveType:" + moveType);
				showLog("target: " + (targetNode ? targetNode.name : "root") + "  -- is "+ (isCopy==null? "cancel" : isCopy ? "copy" : "move"))
			}
			function onExpand(event, treeId, treeNode) {
				if (treeNode === autoExpandNode) {
					className = (className === "dark" ? "":"dark");
					showLog("[ "+getTime()+" onExpand ]&nbsp;&nbsp;&nbsp;&nbsp;" + treeNode.name);
				}
			}
			function onClick(event, treeId, treeNode) {
				$('#sel_parentId').val(treeNode.id);
				$.category.selectData();
				$('#sel_nodeTId').val(treeNode.tId);
			}
			function showLog(str) {
				console.info(str);
			}
			function getTime() {
				var now= new Date(),
				h=now.getHours(),
				m=now.getMinutes(),
				s=now.getSeconds(),
				ms=now.getMilliseconds();
				return (h+":"+m+":"+s+ " " +ms);
			}
		</script>
	</body>
</html>

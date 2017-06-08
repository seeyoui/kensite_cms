<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/taglib/common.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<%@ include file="/WEB-INF/view/taglib/header.jsp" %>
	<%@ include file="/WEB-INF/view/taglib/easyui.jsp" %>
	<%@ include file="/WEB-INF/view/taglib/layer.jsp" %>
</head>

  <body>
 	<div style="position:absolute;top:0px;left:0px;right:0px;bottom:0px;">
		<div style="position:absolute;top:0px;left:0px;right:0px;bottom:0px;">
		    <table id="dataList" title="请假流程任务列表" class="easyui-datagrid" style="width:100%;height:100%"
		            toolbar="#toolbar" pagination="true"
		            rownumbers="true" fitColumns="true" singleSelect="true">
		        <thead>
		            <tr>
					    <th field="id" width="100px">假种</th>
					    <th field="value" width="100px">申请人</th>
					    <th field="label" width="100px">申请时间</th>
					    <th field="category" width="100px">开始时间</th>
					    <th field="description" width="100px">结束时间</th>
					    <th field="sequence" width="50px" align="right">当前节点</th>
					    <!-- 
						<th class="no-sorting">任务创建时间</th>
						<th class="no-sorting">流程状态</th>
						 -->
					    <th field="parentId" width="100px">操作</th>
		            </tr>
		        </thead>
		        <tbody>
		        	<c:forEach items="${leaveList}" var="leave">
						<c:set var="task" value="${leave.task}" />
						<c:set var="pi" value="${leave.processInstance}" />
						<tr id="${leave.id }" tid="${task.id}">
							<td>${leave.leaveType}</td>
							<td>${leave.createUser.name}</td>
							<td><fmt:formatDate value="${leave.createDate}" type="both"/></td>
							<td><fmt:formatDate value="${leave.startTime}" type="both"/></td>
							<td><fmt:formatDate value="${leave.endTime}" type="both"/></td>
							<td>${task.name}</td>
							<!--
							<td><fmt:formatDate value="${task.createTime}" type="both"/></td>
							<td>${pi.suspended ? "已挂起" : "正常" }；<b title='流程版本号'>V: ${leave.processDefinition.version}</b></td>
							-->
							<td>
								<a href="javascript:void(0)" onclick="trace('${task.processDefinitionId}', '${task.executionId}')">跟踪</a>
								<c:if test="${empty task.assignee}">
									<a class="claim" href="javascript:void(0)" onclick="javescript:claim('${task.id}');">签收</a>
								</c:if>
								<c:if test="${not empty task.assignee}">
									<%-- 此处用tkey记录当前节点的名称 --%>
									<a class="handle" href="javascript:void(0)" data-pdid="${task.processDefinitionId}" data-tkey="${task.taskDefinitionKey}" data-tname="${task.name}"  data-id="${leave.id}"  data-tid="${task.id}">办理</a>
								</c:if>
							</td>
						</tr>
					</c:forEach>
		        </tbody>
		    </table>
		    <div id="toolbar">
		        <a href="javascript:void(0)" class="easyui-linkbutton info" iconCls="icon-add" plain="true" onclick="start()">创建申请</a>
		    </div>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			$(".handle").click(function(){
				var obj = $(this);
				var taskId = obj.data("tid");
				var leaveId = obj.data("id");
				var processDefinitionId = obj.data("pdid");
				var tkey=obj.data("tkey");
				//jQuery("#"+tkey+"Win").modal("show", {backdrop: "static"});
				var state = "write";
				if(tkey=="deptLeaderAudit") {
					state = "read";
				}
				window.location.href = "${ctx}/oa/leave/"+state+"/form?id="+leaveId+"&pdid="+processDefinitionId+"&tdkey="+tkey;
			});
		});
		
		function start() {
			$.ajax({
				type: "post",
				url: "${ctx}/oa/leave/start",
				data: {},
				dataType: 'text',
				beforeSend: function(XMLHttpRequest){
				},
				success: function(data, textStatus){
					location.reload();
				}
			});
		}
		
		/**
		 * 签收任务
		 * @param {Object} taskId
		 */
		function claim(taskId) {
			$.get('${ctx}/act/task/claim' ,{taskId: taskId}, function(data) {
	        	layer.msg("签收成功！", {time: layerMsgTime});
	        	location.reload();
		    });
		}
		 
		 function trace(processDefinitionId, executionId) {
			 var url = "${ctx}/act/task/trace/photo/"+processDefinitionId+"/"+executionId;
			 layer.open({
				    type: 2,
				    title: false,
				    closeBtn: false,
				    shadeClose: true,
				    shade: 0.8,
				    area: ['80%', '90%'],
				    content: url //iframe的url
				}); 
				 /* $.layer({
					    type: 2,
					    shadeClose: true,
					    title: false,
					    closeBtn: [0, false],
					    shade: [0.8, '#000'],
					    border: [0],
					    offset: ['20px',''],
					    area: ['1000px', ($(window).height() - 50) +'px'],
					    iframe: {src: url}
					});  */
		 }
	</script>
</body>
</html>

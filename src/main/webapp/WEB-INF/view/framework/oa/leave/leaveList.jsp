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
					    <th field="id" width="100px">请假编号</th>
					    <th field="value" width="100px">创建人</th>
					    <th field="label" width="100px">创建时间</th>
					    <th field="category" width="100px">请假原因</th>
					    <th field="description" width="100px">当前节点</th>
					    <th field="sequence" width="50px" align="right">操作</th>
		            </tr>
		        </thead>
				<tbody>
					<c:forEach items="${leaveList}" var="leave">
					<c:set var="task" value="${leave.task }" />
					<c:set var="pi" value="${leave.processInstance }" />
					<c:set var="hpi" value="${leave.historicProcessInstance }" />
					<tr>
						<td>${leave.id}</td>
						<td>${leave.createUser.name}</td>
						<td><fmt:formatDate value="${leave.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td>${leave.reason}</td>
						<c:if test="${not empty task}">
							<td>${task.name}</td>
							<td><a href="javascript:void(0)" onclick="trace('${task.processDefinitionId}', '${task.executionId}')">跟踪</a></td>
						</c:if>
						<c:if test="${empty task}">
							<td>已结束</td>
							<td>&nbsp;</td>
						</c:if>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

	<script type="text/javascript">
		jQuery(document).ready(function($) {
		});
		 
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

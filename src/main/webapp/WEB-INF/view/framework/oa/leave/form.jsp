<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/taglib/common.jsp" %>
<!doctype html>
<!--[if lt IE 7 ]><html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]><html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]><html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]><html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--><html lang="en" class="no-js"> <!--<![endif]-->
<head>
	<%@ include file="/WEB-INF/view/taglib/header.jsp" %>
	<%@ include file="/WEB-INF/view/taglib/layer.jsp" %>
	<%@ include file="/WEB-INF/view/taglib/chubby-stacks.jsp" %>
	<script type="text/javascript" src="${ctx_script}/My97DatePicker/WdatePicker.js"></script>
</head>
	<body>
 		<div class="body-wrap">
	 		<div class="content">
			    <!--container-->
			    <div class="container">
			    	<div class="row">
                		<div class="col-md-10 col-md-offset-1 col-sm-12">
                			<!-- row -->
		                    <div class="row">
		                        <div class="col-sm-12">
                					<div class="add-comment styled boxed" id="addcomments">
			                        	<a href="javascript:goBackToList();"><div class="ribbon" style="margin-left:-33px;"><span>返回</span></div></a>
										<div class="add-comment-title"><br/>请假申请单</div>
										<div class="comment-form">
											<form action="#" method="post" id="dataForm">
												<c:if test="${leave.act.finishTask==true || leave.task.taskDefinitionKey=='deptLeaderAudit' || leave.task.taskDefinitionKey=='hrAudit'}">
												<c:set var="state" value="disabled=\"disabled\""/>
												</c:if>
												<div class="form-inner">
													<div class="field_select">
														<label for="applyUser" class="label_title">姓名:</label>
														<input type="text" id="applyUser" name="applyUser" value="${currentUser.name}" ${state}/>
													</div>
													<div class="field_text lightPlaceholder">
														<label for="leaveType" class="label_title">请假类型:</label>
														<%-- <select name="leaveType" id="leaveType" multiple data-placeholder="请选择请假类型" ${state}>
									                        <option value="事假" ${leave.leaveType=='事假'?'selected':''}>事假</option>
															<option value="病假" ${leave.leaveType=='病假'?'selected':''}>病假</option>
															<option value="婚假" ${leave.leaveType=='婚假'?'selected':''}>婚假</option>
															<option value="产假" ${leave.leaveType=='产假'?'selected':''}>产假</option>
									                    </select> --%>
									                    <div class="input_styled radiolist inline">
														    <div class="rowRadio">
														        <input type="radio" name="leaveType" value="事假" id="radio1" ${leave.leaveType=='事假'?'checked':''} ${state}/>
														        <label for="radio1">事假</label>
														    </div>
														    <div class="rowRadio">
														        <input type="radio" name="leaveType" value="病假" id="radio2" ${leave.leaveType=='病假'?'checked':''} ${state}/>
														        <label for="radio2">病假</label>
														    </div>
														    <div class="rowRadio">
														        <input type="radio" name="leaveType" value="婚假" id="radio3" ${leave.leaveType=='婚假'?'checked':''} ${state}/>
														        <label for="radio3">婚假</label>
														    </div>
														    <div class="rowRadio">
														        <input type="radio" name="leaveType" value="产假" id="radio4" ${leave.leaveType=='产假'?'checked':''} ${state}/>
														        <label for="radio4">产假</label>
														    </div>
														</div>
													</div>
													<div class="field_text lightPlaceholder">
														<label for="hobbies" class="label_title">开始时间:</label>
														<input type="text" id="startTime_id" name="startTime" value="<fmt:formatDate value="${leave.startTime}" type="both"/>" placeholder="" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" ${state}/>
													</div>
													<div class="field_text lightPlaceholder">
														<label for="hobbies" class="label_title">结束时间:</label>
														<input type="text" id="endTime_id" name="endTime" value="<fmt:formatDate value="${leave.endTime}" type="both"/>" placeholder="" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" ${state}/>
													</div>
													<div class="field_text lightPlaceholder">
														<label for="hobbies" class="label_title">原因说明:</label>
														<div class="field_text field_textarea">
														    <textarea id="reason" name="reason" placeholder="请简洁明确的填写请假原因" style="height:125px;" ${state}>${leave.reason}</textarea>
														</div>
													</div>
													<c:if test="${leave.act.finishTask != true}">
													<c:if test="${leave.task.taskDefinitionKey=='deptLeaderAudit' || leave.task.taskDefinitionKey=='hrAudit'}">
													<div class="field_text lightPlaceholder">
														<label for="hobbies" class="label_title">审批意见:</label>
														<div class="field_text field_textarea">
														    <textarea id="comment" name="comment" placeholder="请简洁明确的填写审批意见" style="height:125px;"></textarea>
														</div>
													</div>
													</c:if>
													</c:if>
													<c:if test="${not empty leave.task}">
													<act:histoicFlow procInsId="${leave.act.procInsId}"/>
													</c:if>
												</div>
												<c:if test="${leave.act.finishTask != true}">
												<div class="rowSubmit">
													<c:if test="${empty leave.task}">
													<a href="javascript:void(0)" class="btn btn-alt" onclick="start()"><span>提交申请</span></a>
													</c:if>
													<c:if test="${leave.task.taskDefinitionKey=='deptLeaderAudit'}">
													<a href="javascript:void(0)" onclick="complete('${leave.task.id}', '${leave.task.processInstanceId}', [{key: 'deptLeaderPass',value: true,type: 'B'}])" class="btn btn-alt btn-blue"><span>同意</span></a>
													<a href="javascript:void(0)" onclick="complete('${leave.task.id}', '${leave.task.processInstanceId}', [{key: 'deptLeaderPass',value: false,type: 'B'}])" class="btn btn-alt btn-yellow"><span>不同意</span></a>
													</c:if>
													<c:if test="${leave.task.taskDefinitionKey=='hrAudit'}">
													<a href="javascript:void(0)" onclick="complete('${leave.task.id}', '${leave.task.processInstanceId}', [{key: 'hrPass',value: true,type: 'B'}])" class="btn btn-alt btn-blue"><span>同意</span></a>
													<a href="javascript:void(0)" onclick="complete('${leave.task.id}', '${leave.task.processInstanceId}', [{key: 'hrPass',value: false,type: 'B'}])" class="btn btn-alt btn-yellow"><span>不同意</span></a>
													</c:if>
													<c:if test="${leave.task.taskDefinitionKey=='modifyApply'}">
													<a href="javascript:void(0)" onclick="saveOrUpdate('${leave.task.id}', '${leave.task.processInstanceId}', [{key: 'reApply',value: true,type: 'B'}])" class="btn btn-alt btn-blue"><span>再试试</span></a>
													<a href="javascript:void(0)" onclick="complete('${leave.task.id}', '${leave.task.processInstanceId}', [{key: 'reApply',value: false,type: 'B'}])" class="btn btn-alt btn-yellow"><span>不请啦</span></a>
													</c:if>
												</div>
												</c:if>
												<input type="hidden" id="id" name="id" value="${leave.id}">
											</form>
										</div>
									</div>
                				</div>
                			</div>
                		</div>
                	</div>
			    </div>
			    <!--/ container -->
			</div>
		</div>
		<script type="text/javascript">
			var layer;
	        var iframeWin = null, iframeBody=null;

			function startByUser() {
				var reason = $("#reason").val();
				 if((typeof(reason)=="undefined") || reason == null || reason== ''){
                        alert("请填写任务说明!");
                        return;
                }
				url = '${ctx}/sysComp/selDeptUser?single=false';
				layer.open({
					type: 2,
					title: '选择办理者',
					area: ['700px', '390px'],
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
							var userName = iframeWin.submitInfo(index);
							if(userName) {
								console.info(userName.replace(/,/g,'|'));
								var variables = [];
								variables.push({key: 'hr',value: userName.replace(/,/g,'|'),type: 'SA'});
								console.info(variables);
								start(variables);
							}
						}
					},
					cancel: function(index){
						layer.close(index);
					}
				});
			}
	        function start(variables){
        		loadi = layer.load(2, {shade: layerLoadShade,time: layerLoadMaxTime});
        		var keys = "", values = "", types = "";
				if (variables) {
					$.each(variables, function(idx) {
						if (keys != "") {
							keys += ",";
							values += ",";
							types += ",";
						}
						keys += this.key;
						values += this.value;
						types += this.type;
					});
					$('#keys').val(keys);
					$('#values').val(values);
					$('#types').val(types);
				}
	        	var url = "${ctx}/oa/leave/start";
	        	$.ajax({
	                cache: true,
	                type: "POST",
	                url: url,
	                data: $('#dataForm').serialize(),
	                async: false,
	                error: function(request) {
	                	layer.close(loadi);
	                },
	                success: function(data) {
	                	layer.close(loadi);
	                	layer.msg("操作成功！", {offset: 'rb',icon: 6,shift: 8,time: layerMsgTime});
	                	goBackToList();
	                }
	            });
	        }

			function completeByUser(taskId, procInsId, variables) {
				url = '${ctx}/sysComp/selDeptUser?single=false';
				layer.open({
					type: 2,
					title: '选择办理者',
					area: ['700px', '390px'],
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
							var userName = iframeWin.submitInfo(index);
							if(userName) {
								variables.push({key: 'hr',value: userName,type: 'S'});
								console.info(variables);
								complete(taskId, procInsId, variables);
							}
						}
					},
					cancel: function(index){
						layer.close(index);
					}
				});
			}
	        
	        /**
			 * 完成任务
			 * @param {Object} taskId
			 */
			function complete(taskId, procInsId, variables) {
				// 转换JSON为字符串
			    var keys = "", values = "", types = "";
				if (variables) {
					$.each(variables, function(idx) {
						if (keys != "") {
							keys += ",";
							values += ",";
							types += ",";
						}
						keys += this.key;
						values += this.value;
						types += this.type;
					});
				}
				// 发送任务完成请求
        		loadi = layer.load(2, {shade: layerLoadShade,time: layerLoadMaxTime});
			    $.post('${ctx}/actTask/complete/', {
			    	taskId: taskId,
			    	procInsId: procInsId,
			    	"comment": $('#comment').val(),
			        "vars.keys": keys,
			        "vars.values": values,
			        "vars.types": types
			    }, function(data) {
                	layer.close(loadi);
			        layer.msg("发送成功！", {icon: 6,time: layerMsgTime});
			        goBackToList();
			    });
			}
		        
		        function saveOrUpdate(taskId, procInsId, variables){
	        		loadi = layer.load(2, {shade: layerLoadShade,time: layerLoadMaxTime});
		        	var url = "${ctx}/oa/leave/saveOrUpdate";
		        	$.ajax({
		                cache: true,
		                type: "POST",
		                url: url,
		                data: $('#dataForm').serialize(),
		                async: false,
		                error: function(request) {
		                	layer.close(loadi);
		                },
		                success: function(data) {
		                	layer.close(loadi);
		                	complete(taskId, procInsId, variables);
		                }
		            });
		        }
			
			function goBackToList() {
				history.go(-1);
			}
		</script>
	</body>
</html>

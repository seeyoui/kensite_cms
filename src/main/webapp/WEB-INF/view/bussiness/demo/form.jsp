<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/taglib/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>演示</title>
		<%@ include file="/WEB-INF/view/taglib/header.jsp" %>
		<%@ include file="/WEB-INF/view/taglib/easyui.jsp" %>
		<%@ include file="/WEB-INF/view/taglib/layer.jsp" %>
		<%@ include file="/WEB-INF/view/taglib/uedit.jsp" %>
		<%@ include file="/WEB-INF/view/taglib/uploadify.jsp" %>
		<style type="text/css">
			#uploadify {
				position: absolute;
				top: 85px;
				left: 244px;
			}
			#uploadfileQueue{
				left: 326px;
			    position: absolute;
			    top: 0;
			    width: 160px;
			    z-index: 9999999;
			}
		</style>
	</head>
	<body>
	 	<div style="position:absolute;top:10px;left:20px;right:20px;bottom:10px;">
			<form id="dataForm" method="post">
				<div class="fitem">
					<ks:formTag table="BO_DEMO" column="USER_NAME"/>
					<!-- 注释生成的ks标签，自己写隐藏输入框接受赋值并像后台传递值，id和name与domain参数相对应 -->
					<%-- <ks:formTag table="BO_DEMO" column="USER_ICON"/> --%>
	                <label>头像</label>
	                <input id="userIcon" type="hidden" name="userIcon"/>
	                <!-- 自行编写上传附件模块 -->
					<div id="uploadfileQueue"></div>
					<input type="file" id="uploadify" name="uploadify" 
					data-queueid="uploadfileQueue" 
					data-filetypeexts="*.jpg;*.jpge;*.gif;*.png" 
					data-buttontext="选择头像" data-auto="true" 
					data-multi="false" data-queuesizelimit="5" 
					data-filesizelimit="5000KB" data-method="POST"
					data-url="userIcon\" />
	                <img id="userIconShow" src="${ctx }/upload/none.jpg"  style="width:162px;height:78px;position:absolute;top:0px;left:325px;"/>
	                <span id="msg-userIcon" class="err-msg"></span>
				</div>
				<div class="fitem">
					<ks:formTag table="BO_DEMO" column="USER_SEX"/>
				</div>
				<div class="fitem">
					<%-- <ks:formTag table="BO_DEMO" column="USER_BIRTHDAY"/> --%>
					<!-- 日期控件添加事件 -->
					<label>出生日期</label>
					<input onclick="WdatePicker({dateFmt:'yyyy-MM-dd',onpicked:pickedFunc})" data-options="tipPosition:'bottom'" name="userBirthday" id="userBirthday" class="date-input easyui-validatebox validatebox-text"/>
				</div>
				<div class="fitem">
					<ks:formTag table="BO_DEMO" column="USER_AGE"/>
				</div>
				<div class="fitem">
					<ks:formTag table="BO_DEMO" column="DEPARTMENT_ID"/>
					<ks:formTag table="BO_DEMO" column="MANAGER_ID"/>
				</div>
				<div class="fitem">
					<ks:formTag table="BO_DEMO" column="USER_SUMMARY"/>
				</div>
				<div class="fitem">
					<ks:formTag table="BO_DEMO" column="EXPRESSION"/>
				</div>
				<div class="fitem">
					<ks:formTag table="BO_DEMO" column="USER_INFO"/>
				</div>
				<div class="fitem">
					<ks:formTag table="BO_DEMO" column="REMARKS"/>
				</div>
				<div class="fitem">
					<ks:formTag table="BO_DEMO" column="TREE_ID"/>
				</div>
				<input id="id" name="id" type="hidden"/>
			</form>
		</div>
		<script type="text/javascript">
			var loadi,url,index = parent.layer.getFrameIndex(window.name);
			$(document).ready(function(){
				var row = parent.$('#dataList').datagrid('getSelected');
				url = '${ctx}/bussiness/demo/save';
				//添加上传附件组件渲染及设置回调函数
				mineUpload($("#uploadify"), "${ctx}", "afterMineUpload");
				if(row != null) {//row对象不为空可认定是修改
					$('#dataForm').form('load', row);
					url = '${ctx}/bussiness/demo/update';
				} else {//反之则为新建
					//新建初始化字段示例
					//输入框的取值与赋值
					var name = $('#userName').textbox('getValue');
					$('#userName').textbox('setValue', name+'示例');
					//下拉框的赋值
					$('#userSex').combobox('setValue', 'F');
					$('#managerId').combobox('loadData', []);
				}
				//表单组件添加事件
				$('#departmentId').combobox({
					onChange: function(newValue, oldValue){
						console.info(newValue+'<<>>'+oldValue);
						$('#managerId').combobox('loadData', []);
						getUserIdByDepartmentId(newValue);
					}
				});
				
				//设置html编辑器渲染完成后事件，主要是给组件赋值，赋相应数据列值
		        userSummary.ready(function() {
			        if(row != null) {
			        	userSummary.setContent(row.userSummary);
			        }
		        });
				//设置html编辑器事件监听，全屏与退出全屏事件，控制外层弹出框对应全屏与退出全屏
				userSummary.addListener("fullScreenChanged",function(type,mode){
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
		    	$("#userIcon").val(fileName);//给隐藏输入框赋值
		    	$("#userIconShow").attr("src","${ctx}/"+fileName);//给界面img标签赋值用于显示图片
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
							parent.$.demo.reloadData();
							parent.layer.msg("操作成功！", {offset: layerMsgOffset,icon: 6,shift: 8,time: layerMsgTime});
							parent.layer.close(index);
						} else {
							renderErrMsg(data.message);
						}
					}
				});
			}
			
			function getUserIdByDepartmentId(departmentId) {
				$.ajax({
					type: "post",
					url: '${ctx}/bussiness/demo/getUserIdByDepartmentId',
					data: {departmentId : departmentId},
					dataType: 'json',
					timeout: layerLoadMaxTime,
					beforeSend: function(XMLHttpRequest){
					},
					success: function(data, textStatus){
						console.info(data);
						$('#managerId').combobox('loadData', data);
					}
				});
			}
			
			function pickedFunc() {
				var myDate = new Date();
				var year = myDate.getFullYear();
				var age = parseInt(year)-parseInt($dp.cal.getP('y'));
				$('#userAge').numberbox('setValue', age);
				console.info($dp.cal.getP('y'));
				console.info($dp.cal.getP('M'));
				console.info($dp.cal.getP('d'));
				console.info($dp.cal.getP('H'));
				console.info($dp.cal.getP('m'));
				console.info($dp.cal.getP('s'));
				/*
				var myDate = new Date();
				myDate.getYear();        //获取当前年份(2位)
				myDate.getFullYear();    //获取完整的年份(4位,1970-????)
				myDate.getMonth();       //获取当前月份(0-11,0代表1月)
				myDate.getDate();        //获取当前日(1-31)
				myDate.getDay();         //获取当前星期X(0-6,0代表星期天)
				myDate.getTime();        //获取当前时间(从1970.1.1开始的毫秒数)
				myDate.getHours();       //获取当前小时数(0-23)
				myDate.getMinutes();     //获取当前分钟数(0-59)
				myDate.getSeconds();     //获取当前秒数(0-59)
				myDate.getMilliseconds();    //获取当前毫秒数(0-999)
				myDate.toLocaleDateString();     //获取当前日期
				var mytime=myDate.toLocaleTimeString();     //获取当前时间
				myDate.toLocaleString( );        //获取日期与时间
				*/
			}
		</script>
	</body>
</html>

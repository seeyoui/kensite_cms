<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/taglib/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>数据源</title>
		<%@ include file="/WEB-INF/view/taglib/header.jsp" %>
		<%@ include file="/WEB-INF/view/taglib/easyui.jsp" %>
		<%@ include file="/WEB-INF/view/taglib/layer.jsp" %>
		<%@ include file="/WEB-INF/view/taglib/uedit.jsp" %>
		<%@ include file="/WEB-INF/view/taglib/codemirror.jsp" %>
	</head>
	<body>
	 	<div style="position:absolute;top:10px;left:20px;right:20px;bottom:10px;">
			<form id="dataForm" method="post">
				<div class="fitem">
					<ks:formTag table="KS_DATA_SOURCE" column="REMARKS"/>
				</div>
				<div class="fitem">
					<ks:formTag table="KS_DATA_SOURCE" column="NAME"/>
					<ks:formTag table="KS_DATA_SOURCE" column="CODE_NUM"/>
					<ks:formTag table="KS_DATA_SOURCE" column="TYPE"/>
				</div>
				<div class="fitem">
					<ks:formTag table="KS_DATA_SOURCE" column="DESCRIBE"/>
				</div>
				<div class="fitem">
					<ks:formTag table="KS_DATA_SOURCE" column="CONTENT"/>
					<input id="content" name="content" type="hidden"/>
				</div>
				<div class="fitem">
					<ks:formTag table="KS_DATA_SOURCE" column="VIEW_SQL"/>
				</div>
				<div class="fitem" id="sql_div" style="height: 160px;">
					<table id="dg_content" class="easyui-datagrid" title="" style="width:100%;height:100%"
			            data-options="fitColumns: true,singleSelect: true,toolbar: '#tb_content',onClickCell: onClickCellContent">
				        <thead>
				            <tr>
				                <th data-options="field:'key',width:150,editor:'textbox'">键</th>
				                <th data-options="field:'value',width:250,editor:'textbox'">值</th>
				            </tr>
				        </thead>
				    </table>
				    <div id="tb_content" style="height:auto">
				        <a href="javascript:void(0)" class="easyui-linkbutton default" data-options="iconCls:'icon-4131',plain:true" onclick="appendContent()"></a>
				        <a href="javascript:void(0)" class="easyui-linkbutton default" data-options="iconCls:'icon-4333',plain:true" onclick="removeContent()"></a>
				    </div>
				</div>
				<input id="id" name="id" type="hidden"/>
			</form>
		</div>
		<script type="text/javascript">
			var loadi,url,index = parent.layer.getFrameIndex(window.name);
			var editor_sql;
			$(document).ready(function(){
				var row = parent.$('#dataList').datagrid('getSelected');
				url = '${ctx}/ks/dataSource/save';
				if(row != null) {
					$('#dataForm').form('load', row);
					if(row.content != null) {
			            $('#dg_content').datagrid('loadData', JSON.parse(row.content));
					}
					url = '${ctx}/ks/dataSource/update';
				} else {
					$('#dataForm').form('load', {'type':'st'});
				}
			});
			
			function submitInfo(){
		    	endEditingContent();
		    	var dgRows = $('#dg_content').datagrid('getData');
				if(dgRows.rows.length == 0) {
					layer.tips('不允许为空', '#sql_div', {
					  tips: [1, '#cc0033']
					});
					return;
				}
		    	var arr = [];
		    	for(var i=0; i<dgRows.rows.length; i++) {
		    		if(dgRows.rows[i].key==null || dgRows.rows[i].key=='' 
		    				|| dgRows.rows[i].value==null || dgRows.rows[i].value=='') {
						layer.tips('不允许存在空数据', '#sql_div', {
						  tips: [1, '#cc0033']
						});
						return;
		    		}
		    		arr.push(dgRows.rows[i].key);
		    	}
		    	if(checkArrayRepeat(arr)) {
		    		layer.tips('键存在重复数据', '#sql_div', {
					  tips: [1, '#cc0033']
					});
					return;
		    	}
		    	$('#content').val(JSON.stringify(dgRows));
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
							parent.$.dataSource.reloadData();
							parent.layer.msg("操作成功！", {offset: layerMsgOffset,icon: 6,shift: 8,time: layerMsgTime});
							parent.layer.close(index);
						} else {
							renderErrMsg(data.message);
						}
					}
				});
			}
			
			var editIndex1 = undefined;
	        function endEditingContent(){
	            if (editIndex1 == undefined){return true}
	            if ($('#dg_content').datagrid('validateRow', editIndex1)){
	                $('#dg_content').datagrid('endEdit', editIndex1);
	                editIndex1 = undefined;
	                return true;
	            } else {
	                return false;
	            }
	        }
	        function onClickCellContent(index, field){
	            if (editIndex1 != index){
	                if (endEditingContent()){
	                    $('#dg_content').datagrid('selectRow', index)
	                            .datagrid('beginEdit', index);
	                    var ed = $('#dg_content').datagrid('getEditor', {index:index,field:field});
	                    if (ed){
	                        ($(ed.target).data('textbox') ? $(ed.target).textbox('textbox') : $(ed.target)).focus();
	                    }
	                    editIndex1 = index;
	                } else {
	                    $('#dg_content').datagrid('selectRow', editIndex);
	                }
	            }
	        }
	        function appendContent(){
	            if (endEditingContent()){
	                $('#dg_content').datagrid('appendRow',{});
	                editIndex1 = $('#dg_content').datagrid('getRows').length-1;
	                $('#dg_content').datagrid('selectRow', editIndex1)
	                        .datagrid('beginEdit', editIndex1);
	            }
	        }
	        function removeContent(){
	            if (editIndex1 == undefined){return}
	            $('#dg_content').datagrid('cancelEdit', editIndex1)
	                    .datagrid('deleteRow', editIndex1);
	            editIndex1 = undefined;
	        }
	        
	        function checkArrayRepeat(arr) {
	        	console.info(arr);
	            return /(\x0f[^\x0f]+)\x0f[\s\S]*\1/.test("\x0f"+ arr.join("\x0f\x0f") +"\x0f");
	        }

		</script>
	</body>
</html>

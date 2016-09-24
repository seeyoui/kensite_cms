<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/taglib/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>标签描述</title>
		<%@ include file="/WEB-INF/view/taglib/header.jsp" %>
		<%@ include file="/WEB-INF/view/taglib/easyui.jsp" %>
		<%@ include file="/WEB-INF/view/taglib/layer.jsp" %>
	</head>
	<body>
	 	<div style="position:absolute;top:10px;left:20px;right:20px;bottom:10px;">
			<form id="dataForm" method="post">
				<div class="fitem">
					<ks:formTag table="CMS_TAG_DESC" column="REMARKS"/>
				</div>
				<div class="fitem">
					<ks:formTag table="CMS_TAG_DESC" column="NAME"/>
					<ks:formTag table="CMS_TAG_DESC" column="TAG_NAME"/>
				</div>
				<div class="fitem">
					<ks:formTag table="CMS_TAG_DESC" column="CATEGORY"/>
				</div>
				<div class="fitem">
					<ks:formTag table="CMS_TAG_DESC" column="DESCRIBE"/>
				</div>
				<%-- <div class="fitem">
					<ks:formTag table="CMS_TAG_DESC" column="ATTRIBUTE"/>
				</div> --%>
				<div class="fitem">
			    	<div style="position:absolute;top:140px;left:0px;right:0px;height:200px">
		                <table id="dg_attr" class="easyui-datagrid" title="属性" style="width:100%;height:100%"
				            data-options="fitColumns: true,singleSelect: true,toolbar: '#tb_attr',onClickCell: onClickCellAttr">
					        <thead>
					            <tr>
					                <th data-options="field:'attrName',width:250,editor:'textbox'">名称</th>
					                <th data-options="field:'attrCate',width:250,editor:'textbox'">数据类型</th>
					                <th data-options="field:'attrDesc',width:300,editor:'textbox'">描述</th>
					                <th data-options="field:'attrValue',width:300,editor:'textbox'">允许的值</th>
					            </tr>
					        </thead>
					    </table>
				    </div>
	            </div>
				<input id="id" name="id" type="hidden"/>
            	<input id="attribute" name="attribute" type="hidden"/>
			</form>
		</div>
	    <div id="tb_attr" style="height:auto">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-4131',plain:true" onclick="appendAttr()"></a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-4333',plain:true" onclick="removeAttr()"></a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-28013',plain:true" onclick="appendInitAttr()"></a>
	    </div>
		<script type="text/javascript">
			var loadi,url,index = parent.layer.getFrameIndex(window.name);
			$(document).ready(function(){
				var row = parent.$('#dataList').datagrid('getSelected');
				url = '${ctx}/cms/tagDesc/save';
				if(row != null) {
					$('#dataForm').form('load', row);
					if(row.attribute!=null && row.attribute!='') {
			            $('#dg_attr').datagrid('loadData', JSON.parse(row.attribute));
					}
					url = '${ctx}/cms/tagDesc/update';
				}
			});
			
			function submitInfo(){
		    	endEditingAttr();
		    	$('#attribute').val(JSON.stringify($('#dg_attr').datagrid('getData')));
				$('#dataForm').form('submit',{
					url: url,
					onSubmit: function(attr){
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
							parent.$.tagDesc.reloadData();
							parent.layer.msg("操作成功！", {offset: layerMsgOffset,icon: 6,shift: 8,time: layerMsgTime});
							parent.layer.close(index);
						} else {
							renderErrMsg(data.message);
						}
					}
				});
			}
			
			var editIndex1 = undefined;
	        function endEditingAttr(){
	            if (editIndex1 == undefined){return true}
	            if ($('#dg_attr').datagrid('validateRow', editIndex1)){
	                $('#dg_attr').datagrid('endEdit', editIndex1);
	                editIndex1 = undefined;
	                return true;
	            } else {
	                return false;
	            }
	        }
	        function onClickCellAttr(index, field){
	            if (editIndex1 != index){
	                if (endEditingAttr()){
	                    $('#dg_attr').datagrid('selectRow', index)
	                            .datagrid('beginEdit', index);
	                    var ed = $('#dg_attr').datagrid('getEditor', {index:index,field:field});
	                    if (ed){
	                        ($(ed.target).data('textbox') ? $(ed.target).textbox('textbox') : $(ed.target)).focus();
	                    }
	                    editIndex1 = index;
	                } else {
	                    $('#dg_attr').datagrid('selectRow', editIndex);
	                }
	            }
	        }
	        function appendAttr(){
	            if (endEditingAttr()){
	                $('#dg_attr').datagrid('appendRow',{});
	                editIndex1 = $('#dg_attr').datagrid('getRows').length-1;
	                $('#dg_attr').datagrid('selectRow', editIndex1)
	                        .datagrid('beginEdit', editIndex1);
	            }
	        }
	        function removeAttr(){
	            if (editIndex1 == undefined){return}
	            $('#dg_attr').datagrid('cancelEdit', editIndex1)
	                    .datagrid('deleteRow', editIndex1);
	            editIndex1 = undefined;
	        }
	        function appendInitAttr(){
	            if (endEditingAttr()){
	                $('#dg_attr').datagrid('appendRow',{attrName: 'var', attrCate: 'String', attrDesc: '主体', attrValue: '*'});
	                $('#dg_attr').datagrid('appendRow',{attrName: 'isPage', attrCate: 'String', attrDesc: '是否分页', attrValue: 'T/F'});
	                $('#dg_attr').datagrid('appendRow',{attrName: 'page', attrCate: 'Integer', attrDesc: '第几页', attrValue: '1'});
	                $('#dg_attr').datagrid('appendRow',{attrName: 'rows', attrCate: 'Integer', attrDesc: '每页行数', attrValue: '20'});
	                $('#dg_attr').datagrid('appendRow',{attrName: 'sort', attrCate: 'String', attrDesc: '排序字段', attrValue: '*'});
	                $('#dg_attr').datagrid('appendRow',{attrName: 'order', attrCate: 'String', attrDesc: '排序方式', attrValue: 'asc/desc'});
	                editIndex1 = $('#dg_attr').datagrid('getRows').length-1;
	            }
	        }
		</script>
	</body>
</html>

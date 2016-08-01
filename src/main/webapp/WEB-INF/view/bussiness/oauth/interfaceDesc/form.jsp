<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/taglib/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>接口描述</title>
	<%@ include file="/WEB-INF/view/taglib/header.jsp" %>
	<%@ include file="/WEB-INF/view/taglib/easyui.jsp" %>
	<%@ include file="/WEB-INF/view/taglib/layer.jsp" %>
	<%@ include file="/WEB-INF/view/taglib/uedit.jsp" %>
  </head>
  <body>
 	<div style="position:absolute;top:10px;left:20px;right:20px;bottom:10px;">
        <form id="dataForm" method="post">
		    <div class="fitem">
                <ks:formTag table="BO_INTERFACE_DESC" column="NAME"/>
                <ks:formTag table="BO_INTERFACE_DESC" column="METHOD"/>
            </div>
		    <div class="fitem">
                <ks:formTag table="BO_INTERFACE_DESC" column="URL"/>
            </div>
		    <div class="fitem">
                <ks:formTag table="BO_INTERFACE_DESC" column="SEQUENCE"/>
                <ks:formTag table="BO_INTERFACE_DESC" column="TYPE"/>
            </div>
		    <div class="fitem">
		    	<div style="position:absolute;top:80px;left:0px;width:486px;height:200px">
	                <table id="dg_param" class="easyui-datagrid" title="参数" style="width:100%;height:100%"
			            data-options="fitColumns: true,singleSelect: true,toolbar: '#tb_param',onClickCell: onClickCellParam">
				        <thead>
				            <tr>
				                <th data-options="field:'param',width:250,editor:'textbox'">参数</th>
				                <th data-options="field:'describ',width:250,editor:'textbox'">说明</th>
				                <th data-options="field:'remarks',width:500,editor:'textbox'">备注</th>
				            </tr>
				        </thead>
				    </table>
			    </div>
            </div>
		    <div class="fitem">
		    	<div style="position:absolute;top:280px;left:0px;width:242px;height:215px">
	                <table id="dg_excludes" class="easyui-datagrid" title="不返回值" style="width:100%;height:100%"
			            data-options="fitColumns: true,singleSelect: true,toolbar: '#tb_excludes',onClickCell: onClickCellExcludes">
				        <thead>
				            <tr>
				                <th data-options="field:'param',width:250,editor:'textbox'">参数</th>
				            </tr>
				        </thead>
				    </table>
			    </div>
		    	<div style="position:absolute;top:280px;right:4px;width:242px;height:215px">
	                <table id="dg_return" class="easyui-datagrid" title="返回值" style="width:100%;height:100%"
			            data-options="fitColumns: true,singleSelect: true,toolbar: '#tb_return',onClickCell: onClickCellReturn">
				        <thead>
				            <tr>
				                <th data-options="field:'param',width:250,editor:'textbox'">参数</th>
				                <th data-options="field:'describ',width:250,editor:'textbox'">说明</th>
				            </tr>
				        </thead>
				    </table>
			    </div>
            </div>
		    <div class="fitem">
                <%-- <ks:formTag table="BO_INTERFACE_DESC" column="PARAMETER"/>
                <ks:formTag table="BO_INTERFACE_DESC" column="RETURN_VALUE"/>
                <ks:formTag table="BO_INTERFACE_DESC" column="EXCLUDES"/>
                <ks:formTag table="BO_INTERFACE_DESC" column="CATALOG_ID"/> --%>
            </div>
            <input id="excludes" name="excludes" type="hidden"/>
            <input id="parameter" name="parameter" type="hidden"/>
            <input id="returnValue" name="returnValue" type="hidden"/>
            <input id="catalogId" name="catalogId" type="hidden"/>
            <input id="id" name="id" type="hidden"/>
		</form>
    </div>
    <div id="tb_param" style="height:auto">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-4131',plain:true" onclick="appendParam()"></a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-4333',plain:true" onclick="removeParam()"></a>
    </div>
    <div id="tb_excludes" style="height:auto">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-4131',plain:true" onclick="appendExcludes()"></a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-4333',plain:true" onclick="removeExcludes()"></a>
    </div>
    <div id="tb_return" style="height:auto">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-4131',plain:true" onclick="appendReturn()"></a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-4333',plain:true" onclick="removeReturn()"></a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-28013',plain:true" onclick="appendInitReturn()"></a>
    </div>
    <script type="text/javascript">
	    var loadi,url,index = parent.layer.getFrameIndex(window.name);
	    $(document).ready(function(){
	    	$('#catalogId').val(parent.catalogId);
	        var row = parent.$('#dataListSub').datagrid('getSelected');
	        url = '${ctx}/oauth/interfaceDesc/save';
	        if(row != null) {
	            $('#dataForm').form('load', row);
	    		url = '${ctx}/oauth/interfaceDesc/update';
	            $('#dg_param').datagrid('loadData', JSON.parse(row.parameter));
	            $('#dg_return').datagrid('loadData', JSON.parse(row.returnValue));
	            $('#dg_excludes').datagrid('loadData', JSON.parse(row.excludes));
	        }
	    });
	
	    function submitInfo(){
	    	endEditingParam();
	    	endEditingReturn();
	    	endEditingExcludes();
	    	$('#parameter').val(JSON.stringify($('#dg_param').datagrid('getData')));
	    	$('#returnValue').val(JSON.stringify($('#dg_return').datagrid('getData')));
	    	$('#excludes').val(JSON.stringify($('#dg_excludes').datagrid('getData')));
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
	            		parent.$.interfaceDesc.reloadData();
	                	parent.layer.msg("操作成功！", {offset: 'rb',icon: 6,shift: 8,time: layerMsgTime});
	            		parent.layer.close(index);
	                } else {
	                    renderErrMsg(data.message);
	                }
	            }
	        });
	    }
	    var editIndex1 = undefined, editIndex2 = undefined, editIndex3 = undefined;
        function endEditingParam(){
            if (editIndex1 == undefined){return true}
            if ($('#dg_param').datagrid('validateRow', editIndex1)){
                $('#dg_param').datagrid('endEdit', editIndex1);
                editIndex1 = undefined;
                return true;
            } else {
                return false;
            }
        }
        function onClickCellParam(index, field){
            if (editIndex1 != index){
                if (endEditingParam()){
                    $('#dg_param').datagrid('selectRow', index)
                            .datagrid('beginEdit', index);
                    var ed = $('#dg_param').datagrid('getEditor', {index:index,field:field});
                    if (ed){
                        ($(ed.target).data('textbox') ? $(ed.target).textbox('textbox') : $(ed.target)).focus();
                    }
                    editIndex1 = index;
                } else {
                    $('#dg_param').datagrid('selectRow', editIndex);
                }
            }
        }
        function appendParam(){
            if (endEditingParam()){
                $('#dg_param').datagrid('appendRow',{});
                editIndex1 = $('#dg_param').datagrid('getRows').length-1;
                $('#dg_param').datagrid('selectRow', editIndex1)
                        .datagrid('beginEdit', editIndex1);
            }
        }
        function removeParam(){
            if (editIndex1 == undefined){return}
            $('#dg_param').datagrid('cancelEdit', editIndex1)
                    .datagrid('deleteRow', editIndex1);
            editIndex1 = undefined;
        }
        
        function endEditingReturn(){
        	if (editIndex2 == undefined){return true}
            if ($('#dg_return').datagrid('validateRow', editIndex2)){
                $('#dg_return').datagrid('endEdit', editIndex2);
                editIndex2 = undefined;
                return true;
            } else {
                return false;
            }
        }
        function onClickCellReturn(index, field){
            if (editIndex2 != index){
                if (endEditingReturn()){
                    $('#dg_return').datagrid('selectRow', index)
                            .datagrid('beginEdit', index);
                    var ed = $('#dg_return').datagrid('getEditor', {index:index,field:field});
                    if (ed){
                        ($(ed.target).data('textbox') ? $(ed.target).textbox('textbox') : $(ed.target)).focus();
                    }
                    editIndex2 = index;
                } else {
                    $('#dg_return').datagrid('selectRow', editIndex2);
                }
            }
        }
        function appendReturn(){
            if (endEditingReturn()){
                $('#dg_return').datagrid('appendRow',{});
                editIndex2 = $('#dg_return').datagrid('getRows').length-1;
                $('#dg_return').datagrid('selectRow', editIndex2)
                        .datagrid('beginEdit', editIndex2);
            }
        }
        function appendInitReturn(){
            if (endEditingReturn()){
                $('#dg_return').datagrid('appendRow',{param: 'success', describ: 'T/F'});
                $('#dg_return').datagrid('appendRow',{param: 'message', describ: 'message'});
                editIndex2 = $('#dg_return').datagrid('getRows').length-1;
            }
        }
        function removeReturn(){
            if (editIndex2 == undefined){return}
            $('#dg_return').datagrid('cancelEdit', editIndex2)
                    .datagrid('deleteRow', editIndex2);
            editIndex2 = undefined;
        }
        

        function endEditingExcludes(){
        	if (editIndex3 == undefined){return true}
            if ($('#dg_excludes').datagrid('validateRow', editIndex3)){
                $('#dg_excludes').datagrid('endEdit', editIndex3);
                editIndex3 = undefined;
                return true;
            } else {
                return false;
            }
        }
        function onClickCellExcludes(index, field){
            if (editIndex3 != index){
                if (endEditingExcludes()){
                    $('#dg_excludes').datagrid('selectRow', index)
                            .datagrid('beginEdit', index);
                    var ed = $('#dg_excludes').datagrid('getEditor', {index:index,field:field});
                    if (ed){
                        ($(ed.target).data('textbox') ? $(ed.target).textbox('textbox') : $(ed.target)).focus();
                    }
                    editIndex3 = index;
                } else {
                    $('#dg_excludes').datagrid('selectRow', editIndex3);
                }
            }
        }
        function appendExcludes(){
            if (endEditingExcludes()){
                $('#dg_excludes').datagrid('appendRow',{});
                editIndex3 = $('#dg_excludes').datagrid('getRows').length-1;
                $('#dg_excludes').datagrid('selectRow', editIndex3)
                        .datagrid('beginEdit', editIndex3);
            }
        }
        function appendInitExcludes(){
            if (endEditingExcludes()){
                $('#dg_excludes').datagrid('appendRow',{param: 'success', describ: 'T/F'});
                $('#dg_excludes').datagrid('appendRow',{param: 'message', describ: 'message'});
                editIndex3 = $('#dg_excludes').datagrid('getRows').length-1;
            }
        }
        function removeExcludes(){
            if (editIndex3 == undefined){return}
            $('#dg_excludes').datagrid('cancelEdit', editIndex3)
                    .datagrid('deleteRow', editIndex3);
            editIndex3 = undefined;
        }
    </script>
  </body>
</html>

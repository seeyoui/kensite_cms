<%@ page import="com.seeyoui.kensite.common.constants.StringConstant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/taglib/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>    
    <title>数据建模</title>
	<%@ include file="/WEB-INF/view/taglib/header.jsp" %>
	<%@ include file="/WEB-INF/view/taglib/easyui.jsp" %>
	<%@ include file="/WEB-INF/view/taglib/layer.jsp" %>
  </head>
  <body style="overflow:hidden">
 	<div style="position:absolute;top:0px;left:0px;right:0px;bottom:0px;">
		<div style="position:absolute;top:0px;right:0px;left:0px;bottom:0px;">
		    <table id="dataSubList" title="" class="easyui-datagrid" style="width:100%;height:100%"
		            toolbar="#subtoolbar" pagination="true"
		            rownumbers="true" fitColumns="false" singleSelect="true">
		        <thead>
		            <tr>
					    <th field="id" width="100px" hidden>主键</th>
					    <th field="tableName" width="100px" hidden>业务表</th>
					    <th field="name" width="200px">列名</th>
					    <th field="comments" width="150px">注释</th>
					    <th field="jdbcType" width="80px">类型</th>
					    <th field="jdbcLength" width="60px">长度</th>
					    <th field="isNull" width="60px" formatter="formatNullable">是否为空</th>
					    <th field="isEdit" width="60px" formatter="formatEditable">是否编辑</th>
					    <th field="isList" width="60px" formatter="formatListable">是否列表</th>
					    <th field="isQuery" width="60px" formatter="formatQueryable">是否查询</th>
					    <th field="isSort" width="60px" formatter="formatSortable">是否排序</th>
					    <th field="category" width="100px" hidden>生成方案</th>
					    <th field="defaultValue" width="100px" hidden>默认值</th>
					    <th field="validType" width="100px" hidden>校验类型</th>
					    <th field="settings" width="100px" hidden>扩展设置</th>
		            </tr>
		        </thead>
		    </table>
		    <div id="subtoolbar" class="toolbar">
		        <a href="javascript:void(0)" class="easyui-linkbutton info" iconCls="icon-add" plain="true" onclick="newSubInfo()">新建</a>
		        <a href="javascript:void(0)" class="easyui-linkbutton warning" iconCls="icon-edit" plain="true" onclick="editSubInfo()">修改</a>
		        <a href="javascript:void(0)" class="easyui-linkbutton error" iconCls="icon-remove" plain="true" onclick="destroySubInfo()">删除</a>
				<span class="toolbar-title">列名</span><input id="sel_sub_name" name="sel_name" class="easyui-textbox" data-options=""/>
				<span class="toolbar-title">注释</span><input id="sel_sub_comments" name="sel_comments" class="easyui-textbox" data-options=""/>
				<span class="toolbar-title">类型</span><input id="sel_jdbcType" name="sel_jdbcType" class="easyui-textbox" data-options=""/>
				<a href="javascript:void(0)" class="easyui-linkbutton success" iconCls="icon-search" plain="true" onclick="selectSubData()">查询</a>
		    </div>
	    </div>
    </div>
    <div id="dataSubWin" class="easyui-window" title="业务表字段信息维护" data-options="modal:true,closed:true,iconCls:'icon-save',resizable:false" style="width:800px;height:310px;padding:10px;">
        <form id="dataSubForm" method="post">
			<div class="fitem">
                <label>列名</label>
                <input id="name" name="name" class="easyui-textbox" data-options="required:true,validType:'jdbcType'"/>
                <span id="msg-name" class="err-msg"></span>
                <label>注释</label>
                <input id="comments" name="comments" class="easyui-textbox" data-options="required:true"/>
                <span id="msg-comments" class="err-msg"></span>
                ↓<a href="javascript:showMessage();">默认值支持@表达式</a>↓
            </div>
			<div class="fitem">
                <label>类型</label>
                <input id="jdbcType" name="jdbcType" class="easyui-combobox" data-options="editable:false,panelHeight: 'auto',required:true,valueField: 'value',textField: 'label'"/>
                <span id="msg-jdbctype" class="err-msg"></span>
                <label>长度</label>
                <input id="jdbcLength" name="jdbcLength" class="easyui-textbox" data-options="validType:'jdbcLength'"/>
                <span id="msg-ispk" class="err-msg"></span>
                <label>默认值</label>
                <input id="defaultValue" name="defaultValue" class="easyui-textbox" data-options=""/>
                <span id="msg-defaultvalue" class="err-msg"></span>
            </div>
			<div class="fitem">
                <label>是否为空</label>
                <input id="isNull" name="isNull" class="easyui-combobox" data-options="editable:false,panelHeight: 'auto',required:true,valueField: 'value',textField: 'label'"/>
                <span id="msg-isnull" class="err-msg"></span>
                <label>是否编辑</label>
                <input id="isEdit" name="isEdit" class="easyui-combobox" data-options="editable:false,panelHeight: 'auto',required:true,valueField: 'value',textField: 'label'"/>
                <span id="msg-isedit" class="err-msg"></span>
                <label>校验类型</label>
                <input id="validType" name="validType" class="easyui-combobox" data-options="valueField: 'value',textField: 'label',icons: [{iconCls:'icon-clear',handler: function(e){$(e.data.target).combobox('clear');}}]"/>
                <span id="msg-validtype" class="err-msg"></span>
            </div>
			<div class="fitem">
                <label>是否列表</label>
                <input id="isList" name="isList" class="easyui-combobox" data-options="editable:false,panelHeight: 'auto',required:true,valueField: 'value',textField: 'label'"/>
                <span id="msg-isList" class="err-msg"></span>
                <label>是否查询</label>
                <input id="isQuery" name="isQuery" class="easyui-combobox" data-options="editable:false,panelHeight: 'auto',required:true,valueField: 'value',textField: 'label'"/>
                <span id="msg-isQuery" class="err-msg"></span>
                <label>是否排序</label>
                <input id="isSort" name="isSort" class="easyui-combobox" data-options="editable:false,panelHeight: 'auto',required:true,valueField: 'value',textField: 'label'"/>
                <span id="msg-isSort" class="err-msg"></span>
            </div>
			<div class="fitem">
                <label>列表宽度</label>
                <input id="listWidth" name="listWidth" class="easyui-numberbox" data-options="min:0,precision:0,value:100,required:true"/>
                <span id="msg-listWidth" class="err-msg"></span>
                <label>查询宽度</label>
                <input id="queryWidth" name="queryWidth" class="easyui-numberbox" data-options="min:0,precision:0,value:100,required:true"/>
                <span id="msg-queryWidth" class="err-msg"></span>
            </div>
			<div class="fitem">
                <label>生成方案</label>
                <input id="category" name="category" class="easyui-combobox" data-options="readonly:true,editable:false,panelHeight: 'auto',required:true,valueField: 'value',textField: 'label'" style="width:105px;"/>
                <!-- <input id="category" name="category" class="easyui-textbox" data-options="readonly:true,required:true" style="width:105px;"/> --><!-- ,buttonText:'配置',buttonIcon:'icon-26422' -->
                <a id="config" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-26422'">配置</a>
                <span id="msg-category" class="err-msg"></span>
                <div style="display:none">
                <label>扩展设置</label>
                <input id="settings" name="settings" class="easyui-textbox" data-options="readonly:true"/><!-- style="width:410px;" -->
                <span id="msg-settings" class="err-msg"></span>
                </div>
                <label>扩展HTML</label>
                <input id="htmlInner" name="htmlInner" class="easyui-textbox" data-options="" style="width:410px;"/><!-- style="width:410px;" -->
                <span id="msg-settings" class="err-msg"></span>
            </div>
            
            <input id="tableName" name="tableName" type="hidden"/>
		</form>
		
	    <div id="dataWin-buttons">
	        <a href="javascript:void(0)" class="easyui-linkbutton default" iconCls="icon-ok" onclick="saveSubInfo()" style="width:90px">保存</a>
	        <a href="javascript:void(0)" class="easyui-linkbutton default" iconCls="icon-cancel" onclick="javascript:$('#dataSubWin').window('close')" style="width:90px">取消</a>
	    </div>
    </div>
    <script type="text/javascript">
    var nullableJson;
    var editableJson;
    var validTypeJson;
    var categoryJson;
    var jdbcTypeJson;
    
    var tableName = '${tableName}';
    $(document).ready(function(){
    	getValidTypeJson();
    	getNullableJson();
    	getEditableJson();
    	getCategoryJson();
    	getJdbcTypeJson();
    	changeTabCol(tableName);
    	$('#config').bind('click', function(){
    		layer.open({
				title: '字段配置',
			    type: 2,
			    area: ['420px', '385px'],
			    fix: false, //不固定
			    maxmin: false,
			    content: '${ctx_static}/form/mod/config.jsp'
			});
        });
    	$('#jdbcType').combobox({
    		onSelect: function(record){
    			changeJdbcLength(record.value);
    		}
    	});
    	$('#isEdit').combobox({
    		onSelect: function(record){
    			if(record.value == 'H') {
	    			$('#isList').combobox('setValue', 'N');
	    			$('#isQuery').combobox('setValue', 'N');
	    			$('#isSort').combobox('setValue', 'N');
    			} else {
	    			$('#isList').combobox('setValue', 'Y');
	    			$('#isQuery').combobox('setValue', 'Y');
	    			$('#isSort').combobox('setValue', 'Y');
    			}
    		}
    	});
		$('#jdbcLength').textbox('readonly', true);
    });
    
    function changeTabCol(tableName) {
    	//清空历史查询结果
    	$('#dataSubList').datagrid('loadData',{total:0,rows:[]});
    	$('#dataSubList').datagrid({url:'${ctx}/sys/tableColumn/list/data?tableName='+tableName});
    }
    
    function changeJdbcLength(jdbcType) {
    	if(jdbcType == 'CHAR') {
    		$('#jdbcLength').textbox('readonly', false);
    		$('#jdbcLength').textbox('setValue', '32');
    	}
    	if(jdbcType == 'VARCHAR2' || jdbcType == 'VARCHAR') {
    		$('#jdbcLength').textbox('readonly', false);
    		$('#jdbcLength').textbox('setValue', '100');
    	}
    	if(jdbcType == 'NUMBER' || jdbcType == 'DECIMAL') {
    		$('#jdbcLength').textbox('readonly', false);
    		$('#jdbcLength').textbox('setValue', '10,2');
    	}
    	if(jdbcType == 'DATE' || jdbcType == 'DATETIME') {
    		$('#jdbcLength').textbox('readonly', true);
    		$('#jdbcLength').textbox('setValue', '');
    	}
    	if(jdbcType == 'CLOB' || jdbcType == 'TEXT') {
    		$('#jdbcLength').textbox('readonly', true);
    		$('#jdbcLength').textbox('setValue', '');
    	}
    }
    
    function formatNullable(val,row) {
    	if(nullableJson == null) {
    		return "";
    	}
    	for(var obj in nullableJson) {
    		if(nullableJson[obj].value == val) {
    			return nullableJson[obj].label;
    		}
    	}
    }
    
    function formatEditable(val,row) {
    	if(editableJson == null) {
    		return "";
    	}
    	for(var obj in editableJson) {
    		if(editableJson[obj].value == val) {
    			if(val==null || val=="Y") {
	        		return editableJson[obj].label+"&nbsp;&nbsp;<a href='javascript:changeState(\""+row.id+"\",\""+row.tableName+"\",\""+row.name+"\",\"N\", \"edit\")'>否</a>";
	        	} else if(val=="N") {
	        		return editableJson[obj].label+"&nbsp;&nbsp;<a href='javascript:changeState(\""+row.id+"\",\""+row.tableName+"\",\""+row.name+"\",\"Y\", \"edit\")'>是</a>";
	        	} else {
	        		return editableJson[obj].label;
	        	}
    		}
    	}
    }
    
    function formatListable(val,row) {
    	if(nullableJson == null) {
    		return "";
    	}
    	for(var obj in nullableJson) {
    		if(nullableJson[obj].value == val) {
    			if(val==null || val=="Y") {
	        		return nullableJson[obj].label+"&nbsp;&nbsp;<a href='javascript:changeState(\""+row.id+"\",\""+row.tableName+"\",\""+row.name+"\",\"N\", \"list\")'>否</a>";
	        	} else if(val=="N") {
	        		return nullableJson[obj].label+"&nbsp;&nbsp;<a href='javascript:changeState(\""+row.id+"\",\""+row.tableName+"\",\""+row.name+"\",\"Y\", \"list\")'>是</a>";
	        	}
    		}
    	}
    }
    
    function formatQueryable(val,row) {
    	if(nullableJson == null) {
    		return "";
    	}
    	for(var obj in nullableJson) {
    		if(nullableJson[obj].value == val) {
    			if(val==null || val=="Y") {
	        		return nullableJson[obj].label+"&nbsp;&nbsp;<a href='javascript:changeState(\""+row.id+"\",\""+row.tableName+"\",\""+row.name+"\",\"N\", \"query\")'>否</a>";
	        	} else if(val=="N") {
	        		return nullableJson[obj].label+"&nbsp;&nbsp;<a href='javascript:changeState(\""+row.id+"\",\""+row.tableName+"\",\""+row.name+"\",\"Y\", \"query\")'>是</a>";
	        	}
    		}
    	}
    }
    
    function formatSortable(val,row) {
    	if(nullableJson == null) {
    		return "";
    	}
    	for(var obj in nullableJson) {
    		if(nullableJson[obj].value == val) {
    			if(val==null || val=="Y") {
	        		return nullableJson[obj].label+"&nbsp;&nbsp;<a href='javascript:changeState(\""+row.id+"\",\""+row.tableName+"\",\""+row.name+"\",\"N\", \"sort\")'>否</a>";
	        	} else if(val=="N") {
	        		return nullableJson[obj].label+"&nbsp;&nbsp;<a href='javascript:changeState(\""+row.id+"\",\""+row.tableName+"\",\""+row.name+"\",\"Y\", \"sort\")'>是</a>";
	        	}
    		}
    	}
    }
    
    function changeState(id, tableName, name, state, target) {
    	$.ajax({
			type: "post",
			url: '${ctx}/sys/tableColumn/changeState',
			data: {
				id : id,
				tableName : tableName,
				name : name,
				state : state,
				target : target
			},
			dataType: 'json',
			timeout: layerLoadMaxTime,
			beforeSend: function(XMLHttpRequest){
				loadi = layer.load(2, {shade: layerLoadShade,time: layerLoadMaxTime});
			},
			success: function(data, textStatus){
				layer.close(loadi);
				if (data.success==TRUE){
					layer.msg("操作成功！", {offset: 'rb',icon: 6,shift: 8,time: layerMsgTime});
					reloadSubData();
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
    }
    
    function selectSubData() {
    	var sel_name = $("#sel_sub_name").val();
	    var sel_comments = $("#sel_sub_comments").val();
	    var sel_jdbcType = $("#sel_jdbcType").val();
    	$('#dataSubList').datagrid('load',{
    		name:sel_name,
		    comments:sel_comments,
		    jdbcType:sel_jdbcType
    	});
    }
    function reloadSubData() {
    	selectSubData();
    }
    
    </script>
    <script type="text/javascript">
    var url;
    function newSubInfo(){
        cleanErrMsg();
        if(tableName==null || tableName=="") {
        	layer.msg("请选择数据表", {offset: 'rb',icon: 6,shift: 8,time: layerMsgTime});
        	return;
        }
        $('#dataSubForm').form('clear');
        $("#tableName").val(tableName);
		$('#listWidth').numberbox('setValue', 100);
		$('#queryWidth').numberbox('setValue', 100);
		
		$('#category').combobox('setValue', 'textbox');
		$('#settings').textbox('setValue', "prompt:''");
        $('#dataSubWin').window('open');
        url = '${ctx}/sys/tableColumn/save';
    }
    function editSubInfo(){
        var row = $('#dataSubList').datagrid('getSelected');
        if (row){
        	cleanErrMsg();
            $('#dataSubForm').form('load',row);
            $("#tableName").val(tableName);
            $('#dataSubWin').window('open');
            url = '${ctx}/sys/tableColumn/update?id='+row.id;
        }    	
    }
    var loadi;
    function saveSubInfo(){
        $('#dataSubForm').form('submit',{
            url: url,
            onSubmit: function(param){
            	if($(this).form('validate')) {
            		loadi = layer.load(2, {shade: layerLoadShade,time: layerLoadMaxTime});
            	}
                return $(this).form('validate');
            },
            success: function(info){
            	layer.close(loadi);
                cleanErrMsg();
            	data = eval('(' + info + ')');
                if (data.success==TRUE){
                    layer.msg("操作成功！", {offset: 'rb',icon: 6,shift: 8,time: layerMsgTime});
            		$('#dataSubWin').window('close'); 
            		reloadSubData();
                } else {
                    layer.msg("操作失败！", {offset: 'rb',icon: 5,shift: 8,time: layerMsgTime});
                    renderErrMsg(data.message);
                }
            }
        });
    }
    function destroySubInfo(){
        var row = $('#dataSubList').datagrid('getSelected');
        if (row){
            $.messager.confirm('确认','你确定删除该记录吗？',function(r){
                if (r){
                	$.ajax({
						type: "post",
						url: '${ctx}/sys/tableColumn/delete',
						data: {id:row.id},
						dataType: 'json',
						beforeSend: function(XMLHttpRequest){
							loadi = layer.load(2, {shade: layerLoadShade,time: layerLoadMaxTime});
						},
						success: function(data, textStatus){
							layer.close(loadi);
							if (data.success==TRUE){
		                        layer.msg("操作成功！", {offset: 'rb',icon: 6,shift: 8,time: layerMsgTime});
								reloadSubData();
		                    } else {
			                    layer.msg("操作失败！", {offset: 'rb',icon: 5,shift: 8,time: layerMsgTime});
		                    }
						}
					});
                }
            });
        }
    }
    
    function getJdbcTypeJson() {
    	$.ajax({
			type: "post",
			url: '${ctx}/sys/dict/list/all',
			data: {category:'jdbcType'},
			dataType: 'json',
			beforeSend: function(XMLHttpRequest){
			},
			success: function(data, textStatus){
				jdbcTypeJson = data;
				$('#jdbcType').combobox('loadData', jdbcTypeJson);
			}
		});
    }
    
    function getNullableJson() {
    	$.ajax({
			type: "post",
			url: '${ctx}/sys/dict/list/all',
			data: {category:'yes_no'},
			dataType: 'json',
			beforeSend: function(XMLHttpRequest){
			},
			success: function(data, textStatus){
				nullableJson = data;
				$('#isNull').combobox('loadData', nullableJson);
				$('#isInsert').combobox('loadData', nullableJson);
				$('#isList').combobox('loadData', nullableJson);
				$('#isQuery').combobox('loadData', nullableJson);
				$('#isSort').combobox('loadData', nullableJson);
			}
		});
    }
    
    function getEditableJson() {
    	$.ajax({
			type: "post",
			url: '${ctx}/sys/dict/list/all',
			data: {category:'yes_no_hidden'},
			dataType: 'json',
			beforeSend: function(XMLHttpRequest){
			},
			success: function(data, textStatus){
				editableJson = data;
				$('#isEdit').combobox('loadData', editableJson);
			}
		});
    }
    
    function getValidTypeJson() {
    	$.ajax({
			type: "post",
			url: '${ctx}/sys/dict/list/all',
			data: {category:'valid'},
			dataType: 'json',
			beforeSend: function(XMLHttpRequest){
			},
			success: function(data, textStatus){
				validTypeJson = data;
				$('#validType').combobox('loadData', validTypeJson);
			}
		});
    }
    
    function getCategoryJson() {
    	$.ajax({
			type: "post",
			url: '${ctx}/sys/dict/list/all',
			data: {category:'columnCategory'},
			dataType: 'json',
			beforeSend: function(XMLHttpRequest){
			},
			success: function(data, textStatus){
				categoryJson = data;
				$('#category').combobox('loadData', categoryJson);
			}
		});
    }
    
    function showMessage() {
    	parent.layer.open({
    		title: '@表达式',
    	    type: 1,
    	    skin: 'layui-layer-demo', //样式类名
    	    closeBtn: 0, //不显示关闭按钮
    	    shift: 2,
    	    area: ['300px', '250px'], //宽高
    	    shadeClose: true, //开启遮罩关闭
    	    content: ''+
    	    '@userId登录人ID</br>'+
    	    '@userName登录人用户名</br>'+
    	    '@name登录人名称</br>'+
    	    '@departmentId登录人部门ID</br>'+
    	    '@departmentName登录人部门名称</br>'+
    	    '@departmentCode登录人编号</br>'+
    	    '@dateTime当前时间2016-11-11 11:11:11</br>'+
    	    '@date当前时间2016-11-11</br>'+
    	    '@time当前时间11:11:11</br>'+
    	    '@year当前年</br>'+
    	    '@month当前月</br>'+
    	    '@day当前天</br>'
    	});
    }
    </script>
  </body>
</html>

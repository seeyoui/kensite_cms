<%@ page import="com.seeyoui.kensite.common.constants.StringConstant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/taglib/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>    
    <title>系统附件表</title>
	<%@ include file="/WEB-INF/view/taglib/header.jsp" %>
	<%@ include file="/WEB-INF/view/taglib/easyui.jsp" %>
	<%@ include file="/WEB-INF/view/taglib/layer.jsp" %>
  </head>
  <body>
  
  	<div id="divLayout" class="easyui-layout" style="width:auto;height:450px">
        <div id="divCenter" data-options="region:'center'">
		    <table id="dataList" title="系统附件表列表" class="easyui-datagrid" style="width:auto;height:auto"
		    		url="${ctx}/sys/uploadfile/getListData.do"
		            toolbar="#toolbar" pagination="true"
		            rownumbers="true" fitColumns="true" singleSelect="true">
		        <thead>
		            <tr>
					    <th field="id" width="100px" hidden>主键</th>
					    <th field="viewname" width="100px">文件原名</th>
					    <th field="realname" width="100px">文件存储名</th>
					    <th field="url" width="100px">WEB访问路径</th>
					    <th field="realurl" width="100px">服务器真实路径</th>
					    <th field="suffix" width="100px">后缀名</th>
					    <th field="filesize" width="100px">文件大小</th>
					    
		            </tr>
		        </thead>
		    </table>
		    <div id="toolbar">
		    	<shiro:hasPermission name="uploadfile:insert">
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newInfo()">新建</a>
		        </shiro:hasPermission>
		        <shiro:hasPermission name="uploadfile:update">
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editInfo()">修改</a>
		        </shiro:hasPermission>
		        <shiro:hasPermission name="uploadfile:delete">
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyInfo()">删除</a>
		        </shiro:hasPermission>

文件原名<input id="sel_viewname" name="sel_viewname" class="easyui-textbox" data-options=""/>
文件存储名<input id="sel_realname" name="sel_realname" class="easyui-textbox" data-options=""/>
WEB访问路径<input id="sel_url" name="sel_url" class="easyui-textbox" data-options=""/>
服务器真实路径<input id="sel_realurl" name="sel_realurl" class="easyui-textbox" data-options=""/>
后缀名<input id="sel_suffix" name="sel_suffix" class="easyui-textbox" data-options=""/>
文件大小<input id="sel_filesize" name="sel_filesize" class="easyui-textbox" data-options=""/>

			    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="selectData()">查询</a>
		    </div>
		    <div id="dataWin" class="easyui-window" title="系统附件表信息维护" data-options="modal:true,closed:true,iconCls:'icon-save',resizable:false" style="width:400px;height:260px;padding:10px;">
		        <div class="ftitle">系统附件表信息维护</div>
		        <form id="dataForm" method="post">
							<div class="fitem">
				                <label>文件原名</label>
				                <input id="viewname" name="viewname" class="easyui-textbox" data-options="required:true"/>
				            </div>
							<div class="fitem">
				                <label>文件存储名</label>
				                <input id="realname" name="realname" class="easyui-textbox" data-options="required:true"/>
				            </div>
							<div class="fitem">
				                <label>WEB访问路径</label>
				                <input id="url" name="url" class="easyui-textbox" data-options="required:true"/>
				            </div>
							<div class="fitem">
				                <label>服务器真实路径</label>
				                <input id="realurl" name="realurl" class="easyui-textbox" data-options="required:true"/>
				            </div>
							<div class="fitem">
				                <label>后缀名</label>
				                <input id="suffix" name="suffix" class="easyui-textbox" data-options="required:true"/>
				            </div>
							<div class="fitem">
				                <label>文件大小</label>
				                <input id="filesize" name="filesize" class="easyui-textbox" data-options="required:true"/>
				            </div>
				</form>
				
			    <div id="dataWin-buttons">
			        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveInfo()" style="width:90px">保存</a>
			        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dataWin').window('close')" style="width:90px">取消</a>
			    </div>
		    </div>
	    </div>
    </div>
    <form id="delForm" method="post" enctype="multipart/form-data">
    	<input type="hidden" id="delDataId" name="delDataId" value=""/>
    </form>
    <script type="text/javascript">
	    $(document).ready(function(){
	    	initSize();
	    });
	    
	    function initSize() {
	    	$("#divLayout").height($(window).height());
	    	$("#divCenter").height($(window).height());
	    	$("#dataList").datagrid('resize', {
	    		height:$(window).height()-1
	    	});
	    }
	    
	    function selectData() {
		    
		    var sel_viewname = $("#sel_viewname").val();
		    var sel_realname = $("#sel_realname").val();
		    var sel_url = $("#sel_url").val();
		    var sel_realurl = $("#sel_realurl").val();
		    var sel_suffix = $("#sel_suffix").val();
		    var sel_filesize = $("#sel_filesize").val();
		    
        	$('#dataList').datagrid('load',{
    		    
    		    viewname:sel_viewname,
    		    realname:sel_realname,
    		    url:sel_url,
    		    realurl:sel_realurl,
    		    suffix:sel_suffix,
    		    filesize:sel_filesize,
    		    
        	});
        }
	    function reloadData() {
        	selectData();
        }
	    
        var url;
        function newInfo(){
            $('#dataWin').window('open');
            $('#dataForm').form('clear');
            url = '${ctx}/sys/uploadfile/saveByAdd.do';
        }
        function editInfo(){
            var row = $('#dataList').datagrid('getSelected');
            if (row){
                $('#dataWin').window('open');
                $('#dataForm').form('load',row);
                url = '${ctx}/sys/uploadfile/saveByUpdate.do?id='+row.id;
            }    	
        }
        var loadi;
        function saveInfo(){
            $('#dataForm').form('submit',{
                url: url,
                onSubmit: function(param){
                	if($(this).form('validate')) {
                		loadi = layer.load(2, {shade: layerLoadShade,time: layerLoadMaxTime});
                	}
                    return $(this).form('validate');
                },
                success: function(info){
                    if (info==TRUE){
                        layer.msg("操作成功！", {offset: 'rb',icon: 6,shift: 8,time: layerMsgTime});
                    } else {
	                    layer.msg("操作失败！", {offset: 'rb',icon: 5,shift: 8,time: layerMsgTime});
                    }
                	layer.close(loadi);
                	$('#dataWin').window('close'); 
                	$('#dataList').datagrid('reload');
                }
            });
        }
        function destroyInfo(){
            var row = $('#dataList').datagrid('getSelected');
            if (row){
                $.messager.confirm('确认','你确定删除该记录吗？',function(r){
                    if (r){
                    	$.ajax({
							type: "post",
							url: '${ctx}/sys/uploadfile/delete.do',
							data: {delDataId:row.id},
							dataType: 'text',
							beforeSend: function(XMLHttpRequest){
							},
							success: function(data, textStatus){
								if (data=="<%=StringConstant.TRUE%>"){
			                        layer.msg("操作成功！", {offset: 'rb',icon: 6,shift: 8,time: layerMsgTime});
			                    } else {
				                    layer.msg("操作失败！", {offset: 'rb',icon: 5,shift: 8,time: layerMsgTime});
			                    }
								reloadData();
							}
						});
                    }
                });
            }
        }
    </script>
  </body>
</html>

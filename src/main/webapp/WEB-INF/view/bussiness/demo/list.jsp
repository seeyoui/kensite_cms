<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/taglib/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>演示</title>
    <%@ include file="/WEB-INF/view/taglib/header.jsp" %>
    <%@ include file="/WEB-INF/view/taglib/easyui.jsp" %>
    <link rel="stylesheet" type="text/css" href="${ctx_script}/easyui/themes/ks/easyui.css"/>
    <%@ include file="/WEB-INF/view/taglib/layer.jsp" %>
    <%@ include file="/WEB-INF/view/taglib/layui.jsp" %>
  </head>
  <body>
    <div style="position:absolute;top:0px;left:0px;right:0px;bottom:0px;">
        <div style="position:absolute;top:0px;left:0px;right:0px;bottom:0px;">
            <table id="dataList" title="" class="easyui-datagrid" style="width:100%;height:100%"
                    url="${ctx}/bussiness/demo/list/data"
                    toolbar="#toolbar" pagination="true"
                    rownumbers="true" fitColumns="true" singleSelect="false">
                <thead>
                    <tr>
                        <th data-options="field:'id',hidden:true">ID</th>
                        <th data-options="field:'ck',checkbox:true"></th>
                        <ks:listTag table="BO_DEMO" column="REMARKS"/>
                        <%-- <ks:listTag table="BO_DEMO" column="TREE_ID"/> --%>
                        <ks:listTag table="BO_DEMO" column="USER_NAME"/>
                        <ks:listTag table="BO_DEMO" column="USER_SEX"/>
                        <ks:listTag table="BO_DEMO" column="USER_AGE"/>
                        <ks:listTag table="BO_DEMO" column="USER_BIRTHDAY"/>
                        <ks:listTag table="BO_DEMO" column="USER_ICON"/>
                        <ks:listTag table="BO_DEMO" column="USER_SUMMARY"/>
                        <ks:listTag table="BO_DEMO" column="DEPARTMENT_ID"/>
                        <ks:listTag table="BO_DEMO" column="MANAGER_ID"/>
                        <ks:listTag table="BO_DEMO" column="EXPRESSION"/>
                    </tr>
                </thead>
            </table>
            <div id="toolbar" class="toolbar">
                <div class="toolbar-left">
                    <div class="toolbar-form">
	                	<form id="search-form" class="layui-form" action="">
	                        <div class="layui-form-item">
	                            <div class="layui-inline">
                        			<ks:queryTag table="BO_DEMO" column="USER_NAME" theme="layer"/>
	                            </div>
							</div>
							<div id="form-other" style="display:none;">
		                        <div class="layui-form-item">
		                            <div class="layui-inline">
		                                <ks:queryTag table="BO_DEMO" column="USER_SEX" theme="layer"/>
		                            </div>
								</div>
		                        <div class="layui-form-item">
		                            <div class="layui-inline">
										<ks:queryTag table="BO_DEMO" column="USER_BIRTHDAY" theme="layer"/>
		                            </div>
								</div>
		                        <div class="layui-form-item">
		                            <div class="layui-inline">
		                                <ks:queryTag table="BO_DEMO" column="DEPARTMENT_ID" theme="layer"/>
		                            </div>
								</div>
		                        <div class="layui-form-item">
		                            <div class="layui-inline">
		                                <ks:queryTag table="BO_DEMO" column="MANAGER_ID" theme="layer"/>
		                            </div>
								</div>
							</div>
	                    </form>
                        <div id="form-btn" style="display:none;" class="layui-form-item">
                            <div class="layui-input-block">
                                <button class="layui-btn" onclick="$.demo.selectData()"><i class="layui-icon">&#xe615;</i></button>
                                <button class="layui-btn layui-btn-warm" onclick="$('#search-form')[0].reset()"><i class="layui-icon">&#xe63d;</i></button>
                            </div>
                        </div>
	                </div>
                </div>
                <div class="toolbar-right">
                    <button class="layui-btn layui-btn-normal" onclick="$.demo.newInfo()"><i class="layui-icon">&#xe61f;</i></button>
                    <button class="layui-btn layui-btn-normal" onclick="$.demo.editInfo()"><i class="layui-icon">&#xe642;</i></button>
                    <button class="layui-btn layui-btn-normal" onclick="$.demo.destroyInfo()"><i class="layui-icon">&#xe640;</i></button>
                </div>
            </div>
        </div>
        <div class="debug">
            <c:if test="${ksfn:getConfig('debug')=='T'}"><a href='javascript:void(0)' onclick='modDebug()'>debug</a></c:if>
        </div>
    </div>
    <script type="text/javascript">
        var tableName = "BO_DEMO";
        $(document).ready(function(){
            layui.use(['form', 'layedit', 'laydate'], function(){
                var form = layui.form()
                ,layer = layui.layer
                ,layedit = layui.layedit
                ,laydate = layui.laydate;
            });
			$(".toolbar-left").hover(function() {
				$(".toolbar-form").addClass("toolbar-form-ex");
				$("#form-other").show();
				$("#form-btn").show();
			}, function() {
				$(".toolbar-form").removeClass("toolbar-form-ex");
				$("#form-other").hide();
				$("#form-btn").hide();
			});
        });
        
        var url, loadi, index;
        var iframeWin = null, iframeBody=null;
        $.demo = {
            selectData : function () {
                $('#dataList').datagrid('load', $('#search-form').parseForm());
            },
            reloadData : function () {
                $.demo.selectData();
            },
            newInfo : function (){
                $('#dataList').datagrid('clearSelections');
                url = '${ctx}/bussiness/demo/layui';
                $.demo.layerOpen(url);
                //layer.full(index);
            },
            editInfo : function (){
                var row = $('#dataList').datagrid('getSelected');
                //var row = $('#dataList').datagrid('getSelections');
                if (row){
                    //if(row.length != 1) {
                        //layer.msg("只能选择一条记录修改！", {offset: 'rb',icon: 5,shift: 8,time: layerMsgTime});
                    //} else {
                    //url = '${ctx}/bussiness/demo/form/'+row.id;
                    url = '${ctx}/bussiness/demo/layui';
                    $.demo.layerOpen(url);
                    //layer.full(index);
                    //}
                } else {
                    layer.msg("请先选择要修改的记录！", {offset: 'rb',icon: 5,shift: 8,time: layerMsgTime});
                }
            },
            exportExcel : function () {
                window.open("${ctx}/bussiness/demo/export");
            },
            layerOpen : function (url) {
                if(url == null || url == '') {
                    url = '${ctx}/bussiness/demo/form';
                }
                index = layer.open({
                    type: 2,
                    title: '演示基本信息',
                    area: ['635px', '460px'],
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
                if (rows && rows.length>0){
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
                            url: '${ctx}/bussiness/demo/delete',
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
                                    $.demo.reloadData();
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
  </body>
</html>

<%@ page import="com.seeyoui.kensite.common.constants.StringConstant"%>
<%@ page import="com.seeyoui.kensite.common.util.*"%>
<%@ page import="net.sf.json.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/taglib/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>    
    <title>Mapper</title>
	<%@ include file="/WEB-INF/view/taglib/header.jsp" %>
	<%@ include file="/WEB-INF/view/taglib/easyui.jsp" %>
	<%@ include file="/WEB-INF/view/taglib/layer.jsp" %>
	</head>
	<body>
	<%
	String sqlStr = new String(request.getParameter("sqlStr").getBytes("UTF-8"),"UTF-8");
	String mapperStr = new String(request.getParameter("mapperStr").getBytes("UTF-8"),"UTF-8");
	List<Map<Object, Object>> resultList = DBUtils.executeQuery(sqlStr, false);
	request.setAttribute("resultList", resultList);
	%>
	<div style="position:absolute;top:0px;left:0px;right:0px;bottom:0px;">
		<table id="dataList" class="easyui-datagrid" style="width:100%;height:100%"
    		pagination="false" rownumbers="true" fitColumns="true" singleSelect="true">
		    <thead>
		        <tr>
		        <%
		        JSONObject jsonobject = JSONObject.fromObject(mapperStr);
				try {
					// 获取一个json数组
					JSONArray array = jsonobject.getJSONArray("rows");
					for (int i = 0; i < array.size(); i++) {
						JSONObject object = (JSONObject)array.get(i);
						if("0".equals(object.get("width"))) {
							out.println("<th data-options=\"field:'"+object.get("fieldTo")+"',hidden:true\">"+object.get("title")+"</th>");
						} else {
							out.println("<th data-options=\"field:'"+object.get("fieldTo")+"',width:"+object.get("width")+"\">"+object.get("title")+"</th>");
						}
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
		        %>
		        </tr>
		    </thead>
		    <tbody>
	    	<%
	        JSONObject jsonobjectData = JSONObject.fromObject(mapperStr);
	    	for(int i=0; i<resultList.size(); i++) {
	    		out.println("<tr>");
				try {
					Map<Object, Object> resultMap = resultList.get(i);
					// 获取一个json数组
					JSONArray array = jsonobjectData.getJSONArray("rows");
					for (int j = 0; j < array.size(); j++) {
						JSONObject object = (JSONObject)array.get(j);
						out.println("<td>"+resultMap.get(object.get("field").toString().toUpperCase())+"</td>");
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				out.println("</tr>");
	    	}
	        %>
		    </tbody>
		</table>
	</div>
	<script type="text/javascript">
		$(document).ready(function(){
			var dg = $('#dataList').datagrid();
            dg.datagrid('enableFilter', [{
                field:'userAge',
                type:'numberbox',
                options:{precision:1},
                op:['equal','notequal','less','greater']
            }]);
		});
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
		function submitInfo() {
			var row = $('#dataList').datagrid('getSelected');
            if (row){
            	<%
		        JSONObject jsonobjectJs = JSONObject.fromObject(mapperStr);
				try {
					// 获取一个json数组
					JSONArray array = jsonobjectJs.getJSONArray("rows");
					for (int i = 0; i < array.size(); i++) {
						JSONObject object = (JSONObject)array.get(i);
						out.println("renderField('"+object.get("fieldTo")+"', row."+object.get("fieldTo")+");");
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
		        %>
            	parent.layer.close(index);
            } else {
            	parent.layer.msg('请选择行', {offset: 'rb',icon: 6,shift: 8,time: layerMsgTime});
            }
		}
		
		function renderField(fieldId, fieldData) {
			var fieldClass = parent.$('#'+fieldId).attr('class');
			if(fieldClass.indexOf('easyui-textbox') != -1) {
				parent.$('#'+fieldId).textbox('setValue', fieldData);
			}
			if(fieldClass.indexOf('easyui-combobox') != -1) {
				parent.$('#'+fieldId).combobox('setValue', fieldData);
			}
			if(fieldClass.indexOf('easyui-numberbox') != -1) {
				parent.$('#'+fieldId).numberbox('setValue', fieldData);
			}
			if(fieldClass.indexOf('easyui-combotree') != -1) {
				parent.$('#'+fieldId).combotree('setValue', fieldData);
			}
			if(fieldClass.indexOf('date-input') != -1) {
				parent.$('#'+fieldId).val(fieldData);
			}
		}
	</script>
	</body>
</html>
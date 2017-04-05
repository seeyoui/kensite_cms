<%@ page import="com.seeyoui.kensite.common.constants.StringConstant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/taglib/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>    
    <title>数据建模</title>
	<%@ include file="/WEB-INF/view/taglib/header.jsp" %>
	<%-- <%@ include file="/WEB-INF/view/taglib/easyui.jsp" %> --%>
	<%@ include file="/WEB-INF/view/taglib/layer.jsp" %>
	<link rel="stylesheet" type="text/css" href="${ctx_script}/easyui/themes/metro/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="${ctx_script}/easyui/themes/icon.css"/>
	<link rel="stylesheet" type="text/css" href="${ctx_script}/easyui/themes/icon-extends.css"/>
	<link rel="stylesheet" type="text/css" href="${ctx_script}/easyui/themes/form.css"/>
	<link rel="stylesheet" type="text/css" href="${ctx_script}/easyui/themes/page.css"/>
	<link rel="stylesheet" type="text/css" href="${ctx_script}/easyui/themes/extend.css"/>
	
	<script type="text/javascript" src="${ctx_script}/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${ctx_script}/easyui/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${ctx_script}/easyui/jquery.easyui.extend.js"></script>
	<script type="text/javascript" src="${ctx_script}/easyui/datagrid-groupview.js"></script>
	<script type="text/javascript" src="${ctx_script}/easyui/datagrid-filter.js"></script>
	<script type="text/javascript" src="${ctx_script}/My97DatePicker/WdatePicker.js"></script>
	<style>
		.fitem label {
		    display: inline-block;
		    width: 50px;
		}
	</style>
  </head>
<body>
	<div id="configTab" class="easyui-tabs" data-options="plain: true, narrow: false, pill: true, tabPosition: 'left', headerWidth: 120, tools:'#tab-tools'" style="width:100%;height:340px">
		<div id="textbox" title="单行" data-options="iconCls:'icon-uicomponent-text'" style="padding:10px">
			<form class="configForm">
				<div class="fitem">
					<label>背景字</label><input id="prompt" class="easyui-textbox" data-options="" style="width:200px;"/>
				</div>
			</form>
		</div>
		<div id="numberbox" title="数值" data-options="iconCls:'icon-uicomponent-number'" style="padding:10px">
			<div class="fitem">
				<label>上限</label><input id="max" class="easyui-numberbox" data-options="precision:0,value:999999" style="width:200px;"/>
			</div>
			<div class="fitem">
				<label>下限</label><input id="min"  class="easyui-numberbox" data-options="precision:0,value:-999999" style="width:200px;"/>
			</div>
			<div class="fitem">
				<label>精度</label><input id="precision" class="easyui-numberbox" data-options="min:0,max:2,value:0" style="width:200px;"/>
			</div>
		</div>
		<div id="combobox" title="列表" data-options="iconCls:'icon-uicomponent-combox'" style="padding:10px">
			<div id="comboboxTab" class="easyui-tabs" data-options="plain: false, narrow: false, pill: true" style="width:100%;height:120px">
				<div id="string" title="常量" data-options="iconCls:'icon-uicomponent-text'" style="padding:10px">
					<div class="fitem">
						<label>选项</label><input id="config" class="easyui-textbox" data-options="" style="width:200px;"/>
					</div>
					<span>&gt;&gt;value1[:label1]|value2[:label2]&lt;&lt;</span>
				</div>
				<div id="sql" title="SQL" data-options="iconCls:'icon-uicomponent-text'" style="padding:10px">
					<div class="fitem">
						<label>SQL语句</label><input id="sqlStr" class="easyui-textbox" data-options="" style="width:200px;"/>
					</div>
					<div class="fitem">
						<label>VALUE列</label><input id="valueStr" class="easyui-textbox" data-options="" style="width:73px;"/>
						<label>LABEL列</label><input id="labelStr" class="easyui-textbox" data-options="" style="width:73px;"/>
					</div>
				</div>
				<div id="dict" title="字典" data-options="iconCls:'icon-uicomponent-text'" style="padding:10px">
					<div class="fitem">
						<label>系统字典</label><input id="dictStr" class="easyui-combotree" style="width:200px;" url="${ctx}/sys/dict/tree"/>
					</div>
				</div>
				<div id="url" title="URL" data-options="iconCls:'icon-uicomponent-text'" style="padding:10px">
					<div class="fitem">
						<label>URL</label><input id="urlStr" class="easyui-textbox" data-options="prompt:'/xxx/list/all'" style="width:200px;"/>
					</div>
					<div class="fitem">
						<label>VALUE列</label><input id="valueStr" class="easyui-textbox" data-options="" style="width:73px;"/>
						<label>LABEL列</label><input id="labelStr" class="easyui-textbox" data-options="" style="width:73px;"/>
					</div>
				</div>
			</div>
		</div>
		<div id="radiobox" title="单选" data-options="iconCls:'icon-uicomponent-radio'" style="padding:10px">
			<div id="radioboxTab" class="easyui-tabs" data-options="plain: false, narrow: false, pill: true" style="width:100%;height:120px">
				<div id="string" title="常量" data-options="iconCls:'icon-uicomponent-text'" style="padding:10px">
					<div class="fitem">
						<label>选项</label><input id="config" class="easyui-textbox" data-options="" style="width:200px;"/>
					</div>
					<span>&gt;&gt;value1[:label1]|value2[:label2]&lt;&lt;</span>
				</div>
				<div id="sql" title="SQL" data-options="iconCls:'icon-uicomponent-text'" style="padding:10px">
					<div class="fitem">
						<label>SQL语句</label><input id="sqlStr" class="easyui-textbox" data-options="" style="width:200px;"/>
					</div>
					<div class="fitem">
						<label>VALUE列</label><input id="valueStr" class="easyui-textbox" data-options="" style="width:73px;"/>
						<label>LABEL列</label><input id="labelStr" class="easyui-textbox" data-options="" style="width:73px;"/>
					</div>
				</div>
				<div id="dict" title="字典" data-options="iconCls:'icon-uicomponent-text'" style="padding:10px">
					<div class="fitem">
						<label>系统字典</label><input id="dictStr" class="easyui-combotree" style="width:200px;" url="${ctx}/sys/dict/tree"/>
					</div>
				</div>
				<div id="url" title="URL" data-options="iconCls:'icon-uicomponent-text'" style="padding:10px">
					<div class="fitem">
						<label>URL</label><input id="urlStr" class="easyui-textbox" data-options="prompt:'/xxx/list/all'" style="width:200px;"/>
					</div>
					<div class="fitem">
						<label>VALUE列</label><input id="valueStr" class="easyui-textbox" data-options="" style="width:73px;"/>
						<label>LABEL列</label><input id="labelStr" class="easyui-textbox" data-options="" style="width:73px;"/>
					</div>
				</div>
			</div>
		</div>
		<div id="checkbox" title="复选" data-options="iconCls:'icon-uicomponent-check'" style="padding:10px">
			<div id="checkboxTab" class="easyui-tabs" data-options="plain: false, narrow: false, pill: true" style="width:100%;height:120px">
				<div id="string" title="常量" data-options="iconCls:'icon-uicomponent-text'" style="padding:10px">
					<div class="fitem">
						<label>选项</label><input id="config" class="easyui-textbox" data-options="" style="width:200px;"/>
					</div>
					<span>&gt;&gt;value1[:label1]|value2[:label2]&lt;&lt;</span>
				</div>
				<div id="sql" title="SQL" data-options="iconCls:'icon-uicomponent-text'" style="padding:10px">
					<div class="fitem">
						<label>SQL语句</label><input id="sqlStr" class="easyui-textbox" data-options="" style="width:200px;"/>
					</div>
					<div class="fitem">
						<label>VALUE列</label><input id="valueStr" class="easyui-textbox" data-options="" style="width:73px;"/>
						<label>LABEL列</label><input id="labelStr" class="easyui-textbox" data-options="" style="width:73px;"/>
					</div>
				</div>
				<div id="dict" title="字典" data-options="iconCls:'icon-uicomponent-text'" style="padding:10px">
					<div class="fitem">
						<label>系统字典</label><input id="dictStr" class="easyui-combotree" style="width:200px;" url="${ctx}/sys/dict/tree"/>
					</div>
				</div>
				<div id="url" title="URL" data-options="iconCls:'icon-uicomponent-text'" style="padding:10px">
					<div class="fitem">
						<label>URL</label><input id="urlStr" class="easyui-textbox" data-options="prompt:'/xxx/list/all'" style="width:200px;"/>
					</div>
					<div class="fitem">
						<label>VALUE列</label><input id="valueStr" class="easyui-textbox" data-options="" style="width:73px;"/>
						<label>LABEL列</label><input id="labelStr" class="easyui-textbox" data-options="" style="width:73px;"/>
					</div>
				</div>
			</div>
		</div>
		<div id="datebox" title="日期" data-options="iconCls:'icon-uicomponent-date'" style="padding:10px">
			<div class="fitem">
				<label>格式</label><input id="config" class="easyui-textbox" data-options="value:'yyyy-MM-dd HH:mm:ss'" style="width:200px;"/>
			</div>
			<div class="fitem">
				<label>最大日期</label><input id="maxDate" class="easyui-textbox" data-options="value:''" style="width:200px;"/>
			</div>
			<div class="fitem">
				<label>最小日期</label><input id="minDate" class="easyui-textbox" data-options="value:''" style="width:200px;"/>
			</div>
			<div class="fitem">
				<label>参考地址</label><a href="http://www.my97.net/dp/demo/index.htm" target="_blank">日期范围限制</a>(二. 功能及示例>>4. 日期范围限制)<br/>
				maxDate:'#F{$dp.$D(\'endDate\')||\'2050-10-01\'}',minDate:'%y-%M-%d'<br/>
				maxDate:'2050-01-01',minDate:'#F{$dp.$D(\'startDate\')}'
			</div>
		</div>
		<div id="textarea" title="多行" data-options="iconCls:'icon-uicomponent-textArea'" style="padding:10px">
			<div class="fitem">
				<label>背景字</label><input id="prompt" class="easyui-textbox" data-options="" style="width:200px;"/>
			</div>
		</div>
		<div id="combotree" title="下拉树" data-options="iconCls:'icon-uicomponent-tree'" style="padding:10px">
			<div id="combotreeTab" class="easyui-tabs" data-options="plain: false, narrow: false, pill: true" style="width:100%;height:120px">
				<div id="string" title="常量" data-options="iconCls:'icon-uicomponent-text'" style="padding:10px">
					<div class="fitem">
						<label>选项</label><input id="config" class="easyui-textbox" data-options="" style="width:200px;"/>
					</div>
					<span>&gt;&gt;id:text:pid|id:text:pid|id:text:pid&lt;&lt;</span>
				</div>
				<div id="sql" title="SQL" data-options="iconCls:'icon-uicomponent-text'" style="padding:10px">
					<div class="fitem">
						<label>SQL语句</label><input id="sqlStr" class="easyui-textbox" data-options="" style="width:200px;"/>
					</div>
					<div class="fitem">
						id&nbsp;<input id="idStr" class="easyui-textbox" data-options="" style="width:63px;"/>
						text&nbsp;<input id="textStr" class="easyui-textbox" data-options="" style="width:63px;"/>
						pid&nbsp;<input id="pidStr" class="easyui-textbox" data-options="" style="width:63px;"/>
					</div>
				</div>
				<div id="dict" title="字典" data-options="iconCls:'icon-uicomponent-text'" style="padding:10px">
					<div class="fitem">
						<label>系统字典</label><input id="dictStr" class="easyui-combotree" style="width:200px;" url="${ctx}/sys/dict/tree"/>
					</div>
				</div>
				<div id="url" title="URL" data-options="iconCls:'icon-uicomponent-text'" style="padding:10px">
					<div class="fitem">
						<label>URL</label><input id="urlStr" class="easyui-textbox" data-options="prompt:'/xxx/list/all'" style="width:200px;"/>
					</div>
					<div class="fitem">
						id&nbsp;<input id="idStr" class="easyui-textbox" data-options="" style="width:63px;"/>
						text&nbsp;<input id="textStr" class="easyui-textbox" data-options="" style="width:63px;"/>
						pid&nbsp;<input id="pidStr" class="easyui-textbox" data-options="" style="width:63px;"/>
					</div>
				</div>
			</div>
		</div>
		<div id="htmldesign" title="HTML" data-options="iconCls:'icon-uicomponent-htmlEditor'" style="padding:10px">
			<div class="fitem">
				<label>固定高度</label><input id="config" class="easyui-numberbox" data-options="min:0,value:100" style="width:200px;"/>
			</div>
		</div>
		<div id="selectButton" title="选择按钮" data-options="iconCls:'icon-uicomponent-gunter'" style="padding:10px">
			<div class="fitem">
				<label>SQL语句</label><input id="sqlStr" class="easyui-textbox" data-options="" style="width:220px;"/>
			</div>
			<div style="position:absolute;top:40px;left: 130px;width:273px;height:160px">
				<table id="dg_return" class="easyui-datagrid" title="映射关系" style="width:100%;height:100%"
		            data-options="fitColumns: true,singleSelect: true,toolbar: '#tb_return',onClickCell: onClickCellReturn">
			        <thead>
			            <tr>
			                <th data-options="field:'field',width:250,editor:'textbox'">源字段</th>
			                <th data-options="field:'title',width:250,editor:'textbox'">字段名</th>
			                <th data-options="field:'width',width:250,editor:'numberbox'">列宽</th>
			                <th data-options="field:'fieldTo',width:250,editor:'textbox'">目标字段</th>
			            </tr>
			        </thead>
			    </table>
			</div>
		    <div id="tb_return" style="height:auto">
		        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-4131',plain:true" onclick="appendReturn()"></a>
		        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-4333',plain:true" onclick="removeReturn()"></a>
		    </div>
		</div>
	</div>
	<div id="tab-tools">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-4336'" onclick="save()">确定</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-5571'" onclick="run()">试用</a>
	</div>
	<div style="position:absolute;left:130px;right:0px;bottom:0px;height:130px;">
		<div class="easyui-panel" title="展示区" style="width:100%;height:120px;padding:10px;">
			<div id="showComponent"></div>
		</div>
	</div>
	<script type="text/javascript">
		var componentType = "textbox";
		var componentConfig = "";
		$(document).ready(function(){
			initData();
			$('#configTab').tabs({
				onSelect: function(title,index){
					if(title == "单行") {
						componentType = "textbox";
					}
					if(title == "数值") {
						componentType = "numberbox";
					}
					if(title == "列表") {
						componentType = "combobox";
					}
					if(title == "单选") {
						componentType = "radiobox";
					}
					if(title == "复选") {
						componentType = "checkbox";
					}
					if(title == "下拉树") {
						componentType = "combotree";
					}
					if(title == "日期") {
						componentType = "datebox";
					}
					if(title == "多行") {
						componentType = "textarea";
					}
					if(title == "HTML") {
						componentType = "htmldesign";
					}
					if(title == "选择按钮") {
						componentType = "selectButton";
					}
				}
			});
		});
		
		function initData() {
			componentType = parent.$('#category').combobox('getValue');
			if(componentType==null || componentType=="") {
				componentType = "textbox";
			}
			var componentConfig = parent.$('#settings').textbox('getValue');
			if(componentType=="textbox") {
				$('#'+componentType+' #prompt').textbox('setValue', componentConfig.replace("prompt:'", "").replace("'",""));
				$('#configTab').tabs('select', 0);
			}
			if(componentType=="numberbox") {
				var numberArr = componentConfig.split(",");
				$('#'+componentType+' #max').numberbox('setValue', numberArr[0].replace("max:", "").replace("'",""));
				$('#'+componentType+' #min').numberbox('setValue', numberArr[1].replace("min:", "").replace("'",""));
				$('#'+componentType+' #precision').numberbox('setValue', numberArr[2].replace("precision:", "").replace("'",""));
				$('#configTab').tabs('select', 1);
			}
			if(componentType=="textarea") {
				$('#'+componentType+' #prompt').textbox('setValue', componentConfig.replace("multiline:true,prompt:'", "").replace("'",""));
				$('#configTab').tabs('select', 6);
			}
			if(componentType=="datebox") {
				var componentConfigArr = componentConfig.split(',');
				if(componentConfigArr.length > 0) {
					$('#'+componentType+' #config').textbox('setValue', componentConfigArr[0].replace("dateFmt:'", "").replace("'",""));
				}
				if(componentConfigArr.length > 1) {
					$('#'+componentType+' #maxDate').textbox('setValue', componentConfigArr[1].substr(0,componentConfigArr[1].length-1).replace("maxDate:'", ""));
				}
				if(componentConfigArr.length > 2) {
					$('#'+componentType+' #minDate').textbox('setValue', componentConfigArr[2].substr(0,componentConfigArr[2].length-1).replace("minDate:'", ""));
				}
				
				$('#configTab').tabs('select', 5);
			}
			if(componentType=="htmldesign") {
				$('#'+componentType+' #config').textbox('setValue', componentConfig);
				$('#configTab').tabs('select', 8);
			}
			if(componentType=="combobox") {
				$('#configTab').tabs('select', 2);
				if(componentConfig.indexOf("SQL>") != -1) {
					var sqlArr = componentConfig.split("|");
					$('#'+componentType+' #sql #sqlStr').textbox('setValue', sqlArr[0].replace("SQL>", ""));
					$('#'+componentType+' #sql #valueStr').textbox('setValue', sqlArr[1]);
					$('#'+componentType+' #sql #labelStr').textbox('setValue', sqlArr[2]);
					$('#'+componentType+'Tab').tabs('select', 1);
				} else if(componentConfig.indexOf("DICT>") != -1) {
					$('#'+componentType+' #dict #dictStr').combotree('setValue', componentConfig.replace("DICT>", ""));
					$('#'+componentType+'Tab').tabs('select', 2);
				} else if(componentConfig.indexOf("URL>") != -1) {
					var sqlArr = componentConfig.split("|");
					$('#'+componentType+' #url #urlStr').textbox('setValue', sqlArr[0].replace("URL>", ""));
					$('#'+componentType+' #url #valueStr').textbox('setValue', sqlArr[1]);
					$('#'+componentType+' #url #labelStr').textbox('setValue', sqlArr[2]);
					$('#'+componentType+'Tab').tabs('select', 3);
				} else {
					$('#'+componentType+' #string #config').textbox('setValue', componentConfig);
					$('#'+componentType+'Tab').tabs('select', 0);
				}
			}
			if(componentType=="radiobox") {
				$('#configTab').tabs('select', 3);
				if(componentConfig.indexOf("SQL>") != -1) {
					var sqlArr = componentConfig.split("|");
					$('#'+componentType+' #sql #sqlStr').textbox('setValue', sqlArr[0].replace("SQL>", ""));
					$('#'+componentType+' #sql #valueStr').textbox('setValue', sqlArr[1]);
					$('#'+componentType+' #sql #labelStr').textbox('setValue', sqlArr[2]);
					$('#'+componentType+'Tab').tabs('select', 1);
				} else if(componentConfig.indexOf("DICT>") != -1) {
					$('#'+componentType+' #dict #dictStr').combotree('setValue', componentConfig.replace("DICT>", ""));
					$('#'+componentType+'Tab').tabs('select', 2);
				} else if(componentConfig.indexOf("URL>") != -1) {
					var sqlArr = componentConfig.split("|");
					$('#'+componentType+' #url #urlStr').textbox('setValue', sqlArr[0].replace("URL>", ""));
					$('#'+componentType+' #url #valueStr').textbox('setValue', sqlArr[1]);
					$('#'+componentType+' #url #labelStr').textbox('setValue', sqlArr[2]);
					$('#'+componentType+'Tab').tabs('select', 3);
				} else {
					$('#'+componentType+' #string #config').textbox('setValue', componentConfig);
					$('#'+componentType+'Tab').tabs('select', 0);
				}
			}
			if(componentType=="checkbox") {
				$('#configTab').tabs('select', 4);
				if(componentConfig.indexOf("SQL>") != -1) {
					var sqlArr = componentConfig.split("|");
					$('#'+componentType+' #sql #sqlStr').textbox('setValue', sqlArr[0].replace("SQL>", ""));
					$('#'+componentType+' #sql #valueStr').textbox('setValue', sqlArr[1]);
					$('#'+componentType+' #sql #labelStr').textbox('setValue', sqlArr[2]);
					$('#'+componentType+'Tab').tabs('select', 1);
				} else if(componentConfig.indexOf("DICT>") != -1) {
					$('#'+componentType+' #dict #dictStr').combotree('setValue', componentConfig.replace("DICT>", ""));
					$('#'+componentType+'Tab').tabs('select', 2);
				} else if(componentConfig.indexOf("URL>") != -1) {
					var sqlArr = componentConfig.split("|");
					$('#'+componentType+' #url #urlStr').textbox('setValue', sqlArr[0].replace("URL>", ""));
					$('#'+componentType+' #url #valueStr').textbox('setValue', sqlArr[1]);
					$('#'+componentType+' #url #labelStr').textbox('setValue', sqlArr[2]);
					$('#'+componentType+'Tab').tabs('select', 3);
				} else {
					$('#'+componentType+' #string #config').textbox('setValue', componentConfig);
					$('#'+componentType+'Tab').tabs('select', 0);
				}
			}
			if(componentType=="combotree") {
				$('#configTab').tabs('select', 7);
				if(componentConfig.indexOf("SQL>") != -1) {
					var sqlArr = componentConfig.split("|");
					$('#'+componentType+' #sql #sqlStr').textbox('setValue', sqlArr[0].replace("SQL>", ""));
					$('#'+componentType+' #sql #idStr').textbox('setValue', sqlArr[1]);
					$('#'+componentType+' #sql #textStr').textbox('setValue', sqlArr[2]);
					$('#'+componentType+' #sql #pidStr').textbox('setValue', sqlArr[3]);
					$('#'+componentType+'Tab').tabs('select', 1);
				} else if(componentConfig.indexOf("DICT>") != -1) {
					$('#'+componentType+' #dict #dictStr').combotree('setValue', componentConfig.replace("DICT>", ""));
					$('#'+componentType+'Tab').tabs('select', 2);
				} else if(componentConfig.indexOf("URL>") != -1) {
					var sqlArr = componentConfig.split("|");
					$('#'+componentType+' #url #urlStr').textbox('setValue', sqlArr[0].replace("URL>", ""));
					$('#'+componentType+' #url #idStr').textbox('setValue', sqlArr[1]);
					$('#'+componentType+' #url #textStr').textbox('setValue', sqlArr[2]);
					$('#'+componentType+' #url #pidStr').textbox('setValue', sqlArr[3]);
					$('#'+componentType+'Tab').tabs('select', 3);
				} else {
					$('#'+componentType+' #string #config').textbox('setValue', componentConfig);
					$('#'+componentType+'Tab').tabs('select', 0);
				}
			}

			if(componentType=="selectButton") {
				$('#configTab').tabs('select', 9);
				if(componentConfig.indexOf("SQL>") != -1) {
					var sqlArr = componentConfig.split("|");
					$('#'+componentType+' #sqlStr').textbox('setValue', sqlArr[0].replace("SQL>", ""));
		            $('#dg_return').datagrid('loadData', JSON.parse(sqlArr[1]));
				}
			}
		}
		
		function save() {
			if(!$('#'+componentType+' .configForm').form('validate')) {
				return;
			}
			if(componentType=="textbox") {
				componentConfig = "prompt:'"+$('#'+componentType+' #prompt').val()+"'";
			}
			if(componentType=="textarea") {
				componentConfig = "multiline:true,prompt:'"+$('#'+componentType+' #prompt').val()+"'";
			}
			if(componentType=="datebox") {
				componentConfig += "dateFmt:'"+$('#'+componentType+' #config').val()+"',";
				componentConfig += "maxDate:'"+$('#'+componentType+' #maxDate').val()+"',";
				componentConfig += "minDate:'"+$('#'+componentType+' #minDate').val()+"'";
			}
			if(componentType=="numberbox") {
				var max = $('#'+componentType+' #max').val();
				if(max == null || max == "") {
					max = "99999999";
				}
				var min = $('#'+componentType+' #min').val();
				if(min == null || min == "") {
					min = "-99999999";
				}
				var precision = $('#'+componentType+' #precision').val();
				if(precision == null || precision == "") {
					precision = 0;
				}
				componentConfig = "max:"+parseInt(max)+",min:"+parseInt(min)+",precision:"+precision;
			}
			if(componentType=="htmldesign") {
				componentConfig = $('#'+componentType+' #config').val();
			}
			if(componentType=="combobox" || componentType=="radiobox" || componentType=="checkbox") {
				var tab = $('#'+componentType+'Tab').tabs('getSelected');
				var index = $('#'+componentType+'Tab').tabs('getTabIndex', tab);
				if(index == 0) {
					componentConfig = $('#'+componentType+' #string #config').val();
				}
				if(index == 1) {
					componentConfig = "SQL>"+$('#'+componentType+' #sql #sqlStr').val()+"|"+$('#'+componentType+' #sql #valueStr').val()+"|"+$('#'+componentType+' #sql #labelStr').val();
				}
				if(index == 2) {
					componentConfig = "DICT>"+$('#'+componentType+' #dict #dictStr').combotree('getValue');
				}
				if(index == 3) {
					componentConfig = "URL>"+$('#'+componentType+' #url #urlStr').val()+"|"+$('#'+componentType+' #url #valueStr').val()+"|"+$('#'+componentType+' #url #labelStr').val();
				}
			}
			if(componentType=="combotree") {
				var tab = $('#'+componentType+'Tab').tabs('getSelected');
				var index = $('#'+componentType+'Tab').tabs('getTabIndex', tab);
				if(index == 0) {
					componentConfig = $('#'+componentType+' #string #config').val();
				}
				if(index == 1) {
					componentConfig = "SQL>"+$('#'+componentType+' #sql #sqlStr').val()+"|"+$('#'+componentType+' #sql #idStr').val()+"|"+$('#'+componentType+' #sql #textStr').val()+"|"+$('#'+componentType+' #sql #pidStr').val();
				}
				if(index == 2) {
					var dictStr = $('#'+componentType+' #dict #dictStr').combotree('getValue');
					//if(dictStr == null || dictStr == '' || dictStr == '${ksfn:getConst("ROOT_ID_32")}') {}
					componentConfig = "DICT>"+$('#'+componentType+' #dict #dictStr').combotree('getValue');
				}
				if(index == 3) {
					componentConfig = "URL>"+$('#'+componentType+' #url #urlStr').val()+"|"+$('#'+componentType+' #url #idStr').val()+"|"+$('#'+componentType+' #url #textStr').val()+"|"+$('#'+componentType+' #url #pidStr').val();
				}
			}
			if(componentType=="selectButton") {
		    	endEditingReturn();
				var sqlStr = componentConfig = $('#'+componentType+' #sqlStr').val();
		    	var sqlMapper = JSON.stringify($('#dg_return').datagrid('getData'));
		    	componentConfig = "SQL>"+sqlStr+"|"+sqlMapper;
			}
			console.info(componentConfig);
			var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
			parent.$('#category').combobox('setValue', componentType);
			parent.$('#settings').textbox('setValue', componentConfig);
			parent.layer.close(index);
		}
		
		function run() {
			var componentObj = "";
			if(componentType=="textbox" || componentType=="textarea") {
				if(componentType=="textbox") {
					componentObj = "<input id=\"show\" class=\"easyui-textbox\">";
					$('#showComponent').html(componentObj);
					$('#show').textbox({
						prompt:$('#'+componentType+' #prompt').val()
					});
				}
				if(componentType=="textarea") {
					componentObj = "<input id=\"show\" class=\"easyui-textbox\" style=\"width:230px;height:90px\">";
					$('#showComponent').html(componentObj);
					$('#show').textbox({
						multiline:true,
						prompt:$('#'+componentType+' #prompt').val()
					});
				}
			}
			if(componentType=="datebox") {
				componentObj = "<input id=\"show\" class=\"easyui-textbox\" onClick=\"WdatePicker({dateFmt:'"+$('#'+componentType+' #config').val()+"',maxDate:'"+$('#'+componentType+' #maxDate').val()+"',minDate:'"+$('#'+componentType+' #minDate').val()+"'})\"/>";
				$('#showComponent').html(componentObj);
				/* $('#show').textbox({
					prompt:'请选择'
				}); */
			}
			if(componentType=="numberbox") {
				componentObj = "<input id=\"show\" class=\"easyui-numberbox\"/>";
				$('#showComponent').html(componentObj);
				var max = $('#'+componentType+' #max').val();
				if(max == null || max == "") {
					max = "99999999";
				}
				var min = $('#'+componentType+' #min').val();
				if(min == null || min == "") {
					min = "-99999999";
				}
				var precision = $('#'+componentType+' #precision').val();
				if(precision == null || precision == "") {
					precision = 0;
				}
				$('#show').numberbox({
					max: parseInt(max),
					min: parseInt(min),
					precision: precision
				});
			}
			if(componentType=="htmldesign") {
				
			}
			if(componentType=="combobox" || componentType=="radiobox" || componentType=="checkbox") {
				var tab = $('#'+componentType+'Tab').tabs('getSelected');
				var index = $('#'+componentType+'Tab').tabs('getTabIndex', tab);
				if(index == 0) {
					var config = $('#'+componentType+' #string #config').val();
					if(componentType=="combobox") {
						var configs = config.split("|");
						componentObj = "<select id=\"show\" class=\"easyui-combobox\" name=\"dept\" style=\"width:200px;\">";
						for(var i=0; i<configs.length; i++) {
							var con = configs[i];
							if(con.indexOf(":") != -1) {
								var cons = con.split(":");
								componentObj += "<option value=\""+cons[0]+"\">"+cons[1]+"</option>";
							} else {
								componentObj += "<option value=\""+con+"\">"+con+"</option>";
							}
						}
						componentObj += "</select>";
						$('#showComponent').html(componentObj);
						$('#show').combobox({
							panelHeight : 80
						});
					}
					if(componentType=="radiobox") {
						var configs = config.split("|");
						componentObj = "";
						for(var i=0; i<configs.length; i++) {
							var con = configs[i];
							if(con.indexOf(":") != -1) {
								var cons = con.split(":");
								componentObj += "<input name=\"show\" type=\"radio\" value=\""+cons[0]+"\"/>"+cons[1];
							} else {
								componentObj += "<input name=\"show\" type=\"radio\" value=\""+con+"\"/>"+con;
							}
						}
						$('#showComponent').html(componentObj);
					}
					if(componentType=="checkbox") {
						var configs = config.split("|");
						componentObj = "";
						for(var i=0; i<configs.length; i++) {
							var con = configs[i];
							if(con.indexOf(":") != -1) {
								var cons = con.split(":");
								componentObj += "<input name=\"show\" type=\"checkbox\" value=\""+cons[0]+"\"/>"+cons[1];
							} else {
								componentObj += "<input name=\"show\" type=\"checkbox\" value=\""+con+"\"/>"+con;
							}
						}
						$('#showComponent').html(componentObj);
					}
				}
				if(index == 1) {
					var sql = $('#'+componentType+' #sql #sqlStr').val();
					var value = $('#'+componentType+' #sql #valueStr').val();
					var label = $('#'+componentType+' #sql #labelStr').val();
					getComponentBySql(componentType, sql, value, label);
				}
				if(index == 2) {
					var sql = $('#'+componentType+' #sql #sqlStr').val();
					var value = $('#'+componentType+' #sql #valueStr').val();
					var label = $('#'+componentType+' #sql #labelStr').val();
					getComponentBySql(componentType, sql, value, label);
				}
			}
		}
		
		function getComponentBySql(type, sql, value, label) {
			
		}
		var editIndex2 = undefined;
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
        function removeReturn(){
            if (editIndex2 == undefined){return}
            $('#dg_return').datagrid('cancelEdit', editIndex2)
                    .datagrid('deleteRow', editIndex2);
            editIndex2 = undefined;
        }
	</script>
</body>
</html>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/taglib/common.jsp" %>
<html>
<head>
	<title>Kensite开发平台</title>
	<%@ include file="/WEB-INF/view/taglib/header.jsp" %>
	<%@ include file="/WEB-INF/view/taglib/easyui.jsp" %>
	<%@ include file="/WEB-INF/view/taglib/layer.jsp" %>
	<%@ include file="/WEB-INF/view/taglib/exceldesign.jsp" %>
	<%@ include file="/WEB-INF/view/taglib/jqueryui.jsp" %>
	<%@ include file="/WEB-INF/view/taglib/colorPicker.jsp" %>
	<%@ include file="/WEB-INF/view/taglib/cssloader.jsp" %>
	<style type="text/css">
	.splitTHead {
	    border-right: 1px solid #c1d3dc;
	    padding: 5px;
	}
	.splitTHead span {
	    font-size: 14px;
	}
	</style>
</head>
<body>
	<div id="reportSet" style="position:absolute; top:0px; height:72px; left:0px; right:0px; background-color: rgb(245,245,245)">
		<table cellpadding="0" cellspacing="0">
		<tbody>
			<tr>
				<td class="splitTHead">
					<select id="fontfamily" name="fontfamily" class="easyui-combobox" data-options="panelHeight:140,editable:false" style="width:92px;height:26px;" title="字体">
						<option value="SimSun">宋体</option>
						<option value="SimHei">黑体</option>
						<option value="Microsoft YaHei" selected="selected">微软雅黑</option>
						<option value="KaiTi">楷体</option>
						<option value="YouYuan">幼圆</option>
						<option value="FangSong">仿宋</option>
					</select>
					<select id="fontsize" name="fontsize" class="easyui-combobox" data-options="panelHeight:140,editable:false" style="width:50px;height:26px;">
						<option value="6pt">6</option>
						<option value="8pt">8</option>
						<option value="9pt" selected="selected">9</option>
						<option value="10pt">10</option>
						<option value="11pt">11</option>
						<option value="12pt">12</option>
						<option value="14pt">14</option>
						<option value="16pt">16</option>
						<option value="20pt">20</option>
						<option value="22pt">22</option>
						<option value="24pt">24</option>
						<option value="26pt">26</option>
						<option value="28pt">28</option>
						<option value="32pt">32</option>
						<option value="48pt">48</option>
						<option value="72pt">72</option>
					</select>
				</td>
				<td class="splitTHead">
					<a id="blod" title="粗体" href="javascript:setMjiStyle(null, 'bold', '')" class="easyui-linkbutton" data-options="iconCls:'icon-menuBtn-blod',iconAlign:'top',toggle:true,selected:false"></a>
					<a id="italic" title="斜体" href="javascript:setMjiStyle(null, 'italic', '')" class="easyui-linkbutton" data-options="iconCls:'icon-menuBtn-italic',iconAlign:'top',toggle:true,selected:false"></a>
				</td>
				<td class="splitTHead">
					<a id="topAlign" title="顶端对齐" href="javascript:alignBx({'a_valign':'0'}, $('#topAlign').linkbutton('options').selected)" class="easyui-linkbutton" data-options="iconCls:'icon-menuBtn-topAlign',iconAlign:'top',toggle:true,selected:true,group:'align'"></a>
					<a id="middelAlign" title="垂直居中" href="javascript:alignBx({'a_valign':'1'}, $('#middelAlign').linkbutton('options').selected)" class="easyui-linkbutton" data-options="iconCls:'icon-menuBtn-middelAlign',iconAlign:'top',toggle:true,selected:false,group:'align'"></a>
					<a id="bottomAlign" title="底部对齐" href="javascript:alignBx({'a_valign':'2'}, $('#bottomAlign').linkbutton('options').selected)" class="easyui-linkbutton" data-options="iconCls:'icon-menuBtn-bottomAlign',iconAlign:'top',toggle:true,selected:false,group:'align'"></a>
				</td>
				<td class="splitTHead">
					<a id="rightretract" title="增加缩进" href="javascript:cellRetract('left')" class="easyui-linkbutton" data-options="iconCls:'icon-menuBtn-rightretract',iconAlign:'top'"></a>
					<a id="leftretract" title="减少缩进" href="javascript:cellRetract('right')" class="easyui-linkbutton" data-options="iconCls:'icon-menuBtn-leftretract',iconAlign:'top'"></a>
				</td>
				<td class="splitTHead" colspan="2">
					<input id="whichborder" class="easyui-combobox" style="width:122px;height:26px" data-options="
						panelHeight:'auto',
						editable:false,
						showItemIcon: true,
						data: [
							{value:'bottom',text:'下框线',iconCls:'icon-menuBtn-BorderBottom'},
							{value:'top',text:'上框线',iconCls:'icon-menuBtn-BorderTop'},
							{value:'left',text:'左框线',iconCls:'icon-menuBtn-BorderLeft'},
							{value:'right',text:'右框线',iconCls:'icon-menuBtn-BorderRight'},
							{value:'none',text:'无框线',iconCls:'icon-menuBtn-BorderRemove',selected:true},
							{value:'all',text:'所有框线',iconCls:'icon-menuBtn-BorderAll'},
							{value:'outline',text:'外侧框线',iconCls:'icon-menuBtn-BorderOutline'},
							{value:'inside',text:'内侧框线',iconCls:'icon-menuBtn-BorderInside'},
							{value:'hinside',text:'水平内框线',iconCls:'icon-menuBtn-BorderHorizontal'},
							{value:'vinside',text:'垂直内框线',iconCls:'icon-menuBtn-BorderVertical'}
						]
					"/>
				</td>
				<td class="splitTHead">
					<a id="rowInsert" title="插入行" href="javascript:insertRow()" class="easyui-linkbutton" data-options="iconCls:'icon-menuBtn-rowInsert',iconAlign:'top'"></a>
					<a id="colInsert" title="插入列" href="javascript:insertCol()" class="easyui-linkbutton" data-options="iconCls:'icon-menuBtn-colInsert',iconAlign:'top'"></a>
					<a id="rowHeight" title="行高" href="javascript:setRChw('r')" class="easyui-linkbutton" data-options="iconCls:'icon-menuBtn-rowheight',iconAlign:'top'"></a>
				</td>
				<td class="splitTHead">
					<a id="cleanContent" title="清除内容" href="javascript:cleanCell(2)" class="easyui-linkbutton" data-options="iconCls:'icon-menuBtn-cleanContent',iconAlign:'top'"></a>
					<a id="cleanStyle" title="清除样式" href="javascript:cleanCell(1)" class="easyui-linkbutton" data-options="iconCls:'icon-menuBtn-cleanStyle',iconAlign:'top'"></a>
				</td>
				<td class="splitTHead">
					<a id="showgridline" title="显示网格线" href="javascript:showGridLine($('#showgridline').linkbutton('options').selected)" class="easyui-linkbutton" data-options="iconCls:'icon-menuBtn-showgridline',iconAlign:'top',toggle:true,selected:true"></a>
				</td>
			</tr>
			<tr>
				<td class="splitTHead">
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-menuBtn-BackgroundColor',iconAlign:'top'"></a>
					<div id="bgcolorSelector" style="background-color: #000000; height: 20px; position: absolute; width: 20px; top: 44px; left: 8px;"></div>
					<input id="bgcolorValue" type="hidden" value="#000000"/>
					<a id="bgcolor" title="设置背景色" href="javascript:setMjiStyle(null, 'bgcolor', $('#bgcolorValue').val())" class="easyui-linkbutton" data-options="iconCls:'icon-menuBtn-BackgroundColor',iconAlign:'top'"></a>
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-menuBtn-fontcolor',iconAlign:'top'"></a>
					<div id="fontcolorSelector" style="background-color: #000000; height: 20px; position: absolute; width: 20px; top: 44px; left: 68px;"></div>
					<input id="fontcolorValue" type="hidden" value="#000000"/>
					<a id="fontcolor" title="设置字体颜色" href="javascript:setMjiStyle(null, 'fontcolor', $('#fontcolorValue').val())" class="easyui-linkbutton" data-options="iconCls:'icon-menuBtn-fontcolor',iconAlign:'top'"></a>
					<a id="formatRush" title="格式刷" href="javascript:void()" class="easyui-linkbutton" data-options="iconCls:'icon-menuBtn-formatRush',iconAlign:'top'"></a>
				</td>
				<td class="splitTHead">
					<a id="autowarp" title="自动换行" href="javascript:autowrapBx($('#autowarp').linkbutton('options').selected)" class="easyui-linkbutton" data-options="iconCls:'icon-menuBtn-autowarp',iconAlign:'top',toggle:true,selected:false"></a>
				</td>
				<td class="splitTHead">
					<a id="leftAlign" title="左对齐" href="javascript:alignBx({'a_halign':'0'}, $('#leftAlign').linkbutton('options').selected)" class="easyui-linkbutton" data-options="iconCls:'icon-menuBtn-leftAlign',iconAlign:'top',toggle:true,selected:true,group:'valign'"></a>
					<a id="centerAlign" title="居中对齐" href="javascript:alignBx({'a_halign':'1'}, $('#centerAlign').linkbutton('options').selected)" class="easyui-linkbutton" data-options="iconCls:'icon-menuBtn-centerAlign',iconAlign:'top',toggle:true,selected:false,group:'valign'"></a>
					<a id="rightAlign" title="右对齐" href="javascript:alignBx({'a_halign':'2'}, $('#rightAlign').linkbutton('options').selected)" class="easyui-linkbutton" data-options="iconCls:'icon-menuBtn-rightAlign',iconAlign:'top',toggle:true,selected:false,group:'valign'"></a>
				</td>
				<td class="splitTHead">
					<a id="mergeCell" title="合并单元格" href="javascript:mergeCell()" class="easyui-linkbutton" data-options="iconCls:'icon-menuBtn-MergenBx',iconAlign:'top',selected:false"></a>
					<a id="unMergeCell" title="拆分单元格" href="javascript:unMergeCell()" class="easyui-linkbutton" data-options="iconCls:'icon-menuBtn-splitBx',iconAlign:'top',selected:false"></a>
				</td>
				<td class="splitTHead">
					<input id="borderstyle" class="easyui-combobox" style="width:60px;height:26px" data-options="
						panelHeight:'auto',
						editable:false,
						showItemIcon: true,
						data: [
							{value:'1',text:'',iconCls:'icon-menuBtn-thin',selected:true},
							{value:'2',text:'',iconCls:'icon-menuBtn-medium'},
							{value:'3',text:'',iconCls:'icon-menuBtn-dashed'},
							{value:'5',text:'',iconCls:'icon-menuBtn-thick'},
							{value:'6',text:'',iconCls:'icon-menuBtn-double'},
							{value:'7',text:'',iconCls:'icon-menuBtn-hair'},
							{value:'8',text:'',iconCls:'icon-menuBtn-mediumdashed'}
						]
					"/>
				</td>
				<!-- 
					{value:'3',text:'',iconCls:'icon-menuBtn-dotted'},
					{value:'9',text:'',iconCls:'icon-menuBtn-dashdot'},
					{value:'10',text:'',iconCls:'icon-menuBtn-mediumdashdot'},
					{value:'11',text:'',iconCls:'icon-menuBtn-dashdotdot'},
					{value:'12',text:'',iconCls:'icon-menuBtn-mediumdashdotdot'}
				 -->
				<td class="splitTHead">
					<a id="borderColor" href="javascript:borderColor()" class="easyui-linkbutton" data-options="iconCls:'icon-menuBtn-BorderColor',iconAlign:'top'"></a>
					<div id="borderColorSelector" style="background-color: #000000; height: 20px; position: absolute; width: 20px; top: 44px; left: 467px;"></div>
					<input id="borderColorValue" type="hidden" value="#000000"/>
					<a id="borderSet" title="设置表格边框" href="javascript:drawborder()" class="easyui-linkbutton" data-options="iconCls:'icon-menuBtn-edit',iconAlign:'top'"></a>
				</td>
				<td class="splitTHead">
					<a id="rowDelete" title="删除行" href="javascript:deleteRow()" class="easyui-linkbutton" data-options="iconCls:'icon-menuBtn-rowDelete',iconAlign:'top'"></a>
					<a id="colDelete" title="删除列" href="javascript:deleteCol()" class="easyui-linkbutton" data-options="iconCls:'icon-menuBtn-colDelete',iconAlign:'top'"></a>
					<a id="colWidth" title="列宽" href="javascript:setRChw('c')" class="easyui-linkbutton" data-options="iconCls:'icon-menuBtn-colwidth',iconAlign:'top'"></a>
				</td>
				<td class="splitTHead">
					<a id="cleanAll" title="清除所有" href="javascript:cleanCell(3)" class="easyui-linkbutton" data-options="iconCls:'icon-menuBtn-cleanAll',iconAlign:'top'"></a>
					<a id="setting" title="单元格设置" href="javascript:getSheetData()" class="easyui-linkbutton" data-options="iconCls:'icon-menuBtn-settingBx',iconAlign:'top'"></a>
				</td>
				<td class="splitTHead">
					<a id="showtitle" title="显示行列头" href="javascript:showExcelHead($('#showtitle').linkbutton('options').selected);" class="easyui-linkbutton" data-options="iconCls:'icon-menuBtn-showtitle',iconAlign:'top',toggle:true,selected:true"></a>
				</td>
			</tr>
		</tbody>
		</table>
	</div>
	<div style="position:absolute; top:0px; height:72px; left:730px; right:0px; background-color: rgb(245,245,245)">
		<table cellpadding="0" cellspacing="0" style="table-layout:fixed;word-break:break-all;width:100%">
		<tbody>
			<tr>
				<td class="splitTHead" style="width:120px;">
					<span>单元格</span>
					<input id="cellCode" name="cellCode" class="easyui-textbox" data-options="readonly:true,value:'A:1'" style="width:72px;height:26px;"/>
				</td>
				<td class="splitTHead" rowspan="2" style="width:100%;">
					<a id="name" title="name" href="javascript:dataColClick('name')" class="easyui-linkbutton" data-options="toggle:true,selected:true,group:'dataCol'">name</a>
					<a id="age" title="age" href="javascript:dataColClick('age')" class="easyui-linkbutton" data-options="toggle:true,group:'dataCol'">age</a>
				</td>
			</tr>
			<tr>
				<td class="splitTHead">
					<span>扩展</span>
					<a id="cellNone" title="不扩展" href="javascript:changeDirection('none')" class="easyui-linkbutton" data-options="iconCls:'icon-menuBtn-none',iconAlign:'top',toggle:true,selected:true,group:'direction'"></a>
					<a id="cellBottom" title="向下扩展" href="javascript:changeDirection('bottom')" class="easyui-linkbutton" data-options="iconCls:'icon-menuBtn-bottom',iconAlign:'top',toggle:true,group:'direction'"></a>
					<a id="cellRight" title="向右扩展" href="javascript:changeDirection('right')" class="easyui-linkbutton" data-options="iconCls:'icon-menuBtn-right',iconAlign:'top',toggle:true,group:'direction'"></a>
				</td>
				<!-- <td class="splitTHead">
					<span>数据</span>
					<select id="cellCol" name="cellCol" class="easyui-combobox" data-options="panelHeight:70,editable:false,readonly:true" style="width:100px;height:26px;">
						<option value=""></option>
						<option value="name">name</option>
						<option value="age">age</option>
					</select>
				</td> -->
			</tr>
		</tbody>
		</table>
	</div>
	<div id="formulaBar" contenteditable="true" spellcheck="false" style="position:absolute; top:72px; height:20px; left:0px; right:0px; border: 1px solid #808080;"></div>
	<div id="KSreport" style="position:absolute; top:92px; bottom:0px; left:0px; right:0px; border: 1px solid gray"></div>

</body>
<script type="text/javascript">
	var cellArray = new Array();
	$(document).ready( $(function () {
		// SpreadJS 初始化
		$("#KSreport").wijspread({
			sheetCount: 1, //初始化为1个标签页
			newTabVisible: false, //隐藏新建标签页按钮
			AllowDragFill: true, //单元格拖动填充
			tabEditable: true
		});
		if ($.browser.msie && parseInt($.browser.version, 10) < 9) {
			//run for ie7/8
			var spread = $("#KSreport").wijspread("spread");
			spread.bind("SpreadsheetObjectLoaded", function () {
				bindSpreadEvent();
				bindReportEvent()
				initSpread();
			});
		} else {
			bindSpreadEvent();
			bindReportEvent()
			initSpread();
		}
		$('#cellType').combobox({
			onChange: function(newValue, oldValue){
				if(newValue == 'db') {
				} else {
				}
			}
		});
		/*
		$('#cellCol').combobox({
			onChange: function(newValue, oldValue){
				var sheet = getCurrentSheet();
				var cellid = getSelectedCellid();
				var row = cellid.row;
				var col = cellid.col;
				newValue = '@{'+newValue+'}';
				sheet.setValue(row,col,newValue, $.wijmo.wijspread.SheetArea.viewport);
			}
		});
		*/
		hideLoader();
	})
	);
	
	function initSpread() {
		var spread = $("#KSreport").wijspread("spread");
        var fbx = new $.wijmo.wijspread.FormulaTextBox(document.getElementById('formulaBar'));
        fbx.spread(spread);
		var sheet = spread.getActiveSheet();
		sheet.setName("KS Report");
		sheet.setRowCount(30, $.wijmo.wijspread.SheetArea.viewport);
		sheet.setColumnCount(20, $.wijmo.wijspread.SheetArea.viewport);
		initCellArray(30, 20);
	}
	
	function getSheetData() {
		//setting();
		console.info($("#KSreport").data('spread'));
		console.info($("#KSreport").data('spread').toJSON( { includeBindingSource: true } ));
	}
	
	function bindReportEvent() {
		var sheet = getCurrentSheet();
		//单元格点击事件
		sheet.bind($.wijmo.wijspread.Events.CellClick, function (event, data) {
			var cellid = getSelectedCellid();
			var row = cellid.row;
			var col = cellid.col;
			var colCode = '';
			if(parseInt(col/26) == 0) {
				colCode = String.fromCharCode(("A".charCodeAt())+parseInt(col%26));
			} else {
				colCode = String.fromCharCode(("A".charCodeAt()-1)+parseInt(col/26))+String.fromCharCode(("A".charCodeAt())+parseInt(col%26));
			}
			$('#cellCode').textbox('setValue', colCode+':'+row);
			var cellValue = sheet.getValue(row,col);
			if(cellValue && cellValue.indexOf('@{')!=-1) {
				cellValue = cellValue.replace('@{', '').replace('}', '');
				$('#'+cellValue).linkbutton('select');
			}
			var direction = cellArray[row][col].direction;
			if(direction == 'none') {
				$('#cellNone').linkbutton('select');
			} else if(direction == 'bottom') {
				$('#cellBottom').linkbutton('select');
			} else if(direction == 'right') {
				$('#cellRight').linkbutton('select');
			}
		});
	}
	
	function dataColClick(colName) {
		var sheet = getCurrentSheet();
		var cellid = getSelectedCellid();
		var row = cellid.row;
		var col = cellid.col;
		colName = '@{'+colName+'}';
		sheet.setValue(row,col,colName, $.wijmo.wijspread.SheetArea.viewport);
	}
	
	function changeDirection(direction) {
		var sheet = getCurrentSheet();
		var cellid = getSelectedCellid();
		var row = cellid.row;
		var col = cellid.col;
		if(!direction) {
			direction = 'none';
		}
		cellArray[row][col] = {
			direction : direction
		};
	}
	
	function initCellArray(rows, cols) {
		for(var i=0; i<rows; i++) {
			cellArray[i] = new Array();
			for(var j=0; j<cols; j++) {
				cellArray[i][j] = {
					direction : 'none'
				};
			}
		}
	}
</script>
</html>

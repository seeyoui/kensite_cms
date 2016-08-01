$(document).ready(
	$(function () {
		context.init({preventDoubleContext: false});
		context.settings({compress: true});
		context.attach('#KSreport', [
			{text: '剪切', action: function(e){
				ccp('cut');
			}},
			{text: '复制', action: function(e){
				ccp('copy');
			}},
			{text: '粘贴', action: function(e){
				ccp('paste');
			}},
			{divider: true},
			{text: '合并单元格', action: function(e){
				mergeCell();
			}},
			{text: '拆分单元格', action: function(e){
				unMergeCell();
			}},
			{divider: true},
			{text: '行高', action: function(e){
				setRChw('r');
			}},
			{text: '列宽', action: function(e){
				setRChw('c');
			}},
			{text: '清除', subMenu: [
				{text: '清除内容', action: function(e){
					unMergeCell();
				}},
				{text: '清除格式', action: function(e){
					unMergeCell();
				}},
				{text: '清除格式及内容', action: function(e){
					unMergeCell();
				}}
			]},
			{divider: true},
			{text: '插入', subMenu: [
				{text: '插入行', action: function(e){
					insertRow();
				}},
				{text: '插入列', action: function(e){
					insertCol();
				}}
			]},
			{text: '删除', subMenu: [
				{text: '删除行', action: function(e){
					deleteRow();
				}},
				{text: '删除列', action: function(e){
					deleteCol();
				}}
			]},
			{divider: true},
			{text: '帮助', action: function(e){
				void(0);
			}}
		]);
		
		$('#fontfamily').combobox({
			onSelect: function(record){
				setMjiStyle(null, 'fontfamliy', record.value);
			}
		});
		
		$('#fontsize').combobox({
			onSelect: function(record){
				setMjiStyle(null, 'fontsize', record.value);
			}
		});
		/*
		var layerIndex = null;
		$('.splitTHead a').hover(function(){
			var title = $(this).attr('title');
			if(title != null && title != '') {
				layerIndex = layer.tips(title, $(this), {
					tips: [1, '#78BA32']
				});
			}
		},function(){
			layer.close(layerIndex);
		});
		*/
		$('#bgcolorSelector').ColorPicker({
    		color: '#000000',
    		onSubmit: function(hsb, hex, rgb, el) {
    			$(el).ColorPickerHide();
    		},
    		onShow: function (colpkr) {
    			$(colpkr).fadeIn(500);
    			return false;
    		},
    		onHide: function (colpkr) {
    			$(colpkr).fadeOut(500);
    			return false;
    		},
    		onChange: function (hsb, hex, rgb) {
    			$('#bgcolorSelector').css('backgroundColor', '#' + hex);
    			$('#bgcolorValue').val('#' + hex);
    		}
    	});
    	$('#fontcolorSelector').ColorPicker({
    		color: '#000000',
    		onSubmit: function(hsb, hex, rgb, el) {
    			$(el).ColorPickerHide();
    		},
    		onShow: function (colpkr) {
    			$(colpkr).fadeIn(500);
    			return false;
    		},
    		onHide: function (colpkr) {
    			$(colpkr).fadeOut(500);
    			return false;
    		},
    		onChange: function (hsb, hex, rgb) {
    			$('#fontcolorSelector').css('backgroundColor', '#' + hex);
    			$('#fontcolorValue').val('#' + hex);
    		}
    	});
    	$('#borderColorSelector').ColorPicker({
    		color: '#000000',
    		onSubmit: function(hsb, hex, rgb, el) {
    			$(el).ColorPickerHide();
    		},
    		onShow: function (colpkr) {
    			$(colpkr).fadeIn(500);
    			return false;
    		},
    		onHide: function (colpkr) {
    			$(colpkr).fadeOut(500);
    			return false;
    		},
    		onChange: function (hsb, hex, rgb) {
    			$('#borderColorSelector').css('backgroundColor', '#' + hex);
    			$('#borderColorValue').val('#' + hex);
    		}
    	});
	})
);

function getCurrentSheet() {
	var KSreport = $("#KSreport").wijspread("spread");
	var sheet = KSreport.getActiveSheet();
	return sheet;
}
/**
 * 获取表格 矩阵 并返回
 */
function getActualCellRange(cellRange, rowCount, columnCount) {
	if (cellRange.row == -1 && cellRange.col == -1) {
		return new $.wijmo.wijspread.Range(0, 0, rowCount, columnCount);
	}
	else if (cellRange.row == -1) {
		return new $.wijmo.wijspread.Range(0, cellRange.col, rowCount, cellRange.colCount);
	}
	else if (cellRange.col == -1) {
		return new $.wijmo.wijspread.Range(cellRange.row, 0, cellRange.rowCount, columnCount);
	}

	return cellRange;
}
	
/**
 * 设置 操作栏 按钮状态
 */
function setBtnStatus(btn){
}

/**
 * 是否只选中单一单元格
 */
function isChooseSingleCell(){
	var sheet = getCurrentSheet();
    var sels = sheet.getSelections();
    var isSingle = true;
    if(sels.length > 1){
    	isSingle = false;
    }else{
    	var signleCellLength = 0;
    	var range = getActualCellRange(sels[0], sheet.getRowCount(), sheet.getColumnCount());
    	for (var i = 0; i < range.rowCount; i++) {
    		for (var j = 0; j < range.colCount; j++) {
    			var r = range.row + i;
       	 		var c = range.col + j;
       	 		var spans = sheet.getSpan(r,c,$.wijmo.wijspread.SheetArea.viewport);
       	 		if(spans==null){
       	 			signleCellLength++;
       	 		}else{
       	 			if(spans.row==r && spans.col==c)
       	 				signleCellLength++;
       	 		}
    		}
    	}
    	if(signleCellLength>1)	isSingle = false;
    }
    return isSingle;
}
   	
/**
 * 获取Sheet第一个选中区域第一个单元格ID
 */
function getSelectedCellid(){
	var sheet = getCurrentSheet();
	var sels = sheet.getSelections();
	var range = getActualCellRange(sels[0], sheet.getRowCount(), sheet.getColumnCount());
	var cellobj = {};
	cellobj.row = range.row;
	cellobj.col = range.col;
	return cellobj;
}

function bindSpreadEvent() {
	var sheet = getCurrentSheet();
	//单元格点击事件
	sheet.bind($.wijmo.wijspread.Events.CellClick, function (event, data) {
		spreadCellClick();			//改变相关样式选中状态、相关操作权限
	});
}

/**
 * 设置样式、字体、背景色、加粗、斜体
 */
function setMjiStyle(o, styleWay, param1) {
    var sheet = getCurrentSheet();
    try {
        sheet.isPaintSuspended(true);
        var addBold = true;
        var addItalic = true;
        var selections = sheet.getSelections();
        for (var index=0;index<selections.length;index++) {
            var range = getActualCellRange(selections[index], sheet.getRowCount(), sheet.getColumnCount());
            var fs_style = sheet.getStyle(range.row, range.col, $.wijmo.wijspread.SheetArea.viewport);
            //根据第一个区域的第一个单元格判断是加粗还是取消加粗、是加斜体还是取消斜体
        	if(index==0){
        		if(fs_style != undefined && fs_style.font != undefined){
	            	if(styleWay == "bold" && fs_style.font.indexOf("bold") > -1){
	            		addBold = false;
	            	}
	            	if(styleWay == "italic" && fs_style.font.indexOf("italic") > -1){
	            		addItalic = false;
	            	}
        		}
        	}
            for (var i = 0; i < range.rowCount; i++) {
                for (var j = 0; j < range.colCount; j++) {
					var r = i + range.row;
					var c = j + range.col;
			           
					var cell = {};
			        cell.efont = {};//---
			        
                    var fontstr;
                    var _style = sheet.getStyle(i + range.row, j + range.col, $.wijmo.wijspread.SheetArea.viewport);
                    if (_style == undefined) {
                        _style = new $.wijmo.wijspread.Style();
                    }
                    if(styleWay == "bgcolor"){
                        _style.backColor = param1;
                        cell.ebgcolor = param1;
                        if(param1 === "transparents")param1 = null;
                        if(!param1){
                        	delete _style.backColor;
                        }
                    }else if (styleWay == "fontcolor"){
                        _style.foreColor = param1;
                        if(!cell.efont)cell.efont={};
                        cell.efont.f_color = param1;
                        if(!param1){
                        	delete _style.foreColor;
                        }
                    }else if (styleWay == "fontfamliy"){
                        if(_style.font == undefined)
                             _style.font = "9pt " + param1;
                        else{
                            if(_style.font.indexOf("SimSun") >=0){
                                _style.font = _style.font.replace("SimSun",param1);
                            }else if(_style.font.indexOf("SimHei") >=0){
                                _style.font = _style.font.replace("SimHei",param1);
                            }else if(_style.font.indexOf("Microsoft YaHei") >=0){
                                _style.font = _style.font.replace("Microsoft YaHei",param1);
                            }else if(_style.font.indexOf("KaiTi") >=0){
                                _style.font = _style.font.replace("KaiTi",param1);
                            }else if(_style.font.indexOf("YouYuan") >=0){
                                _style.font = _style.font.replace("YouYuan",param1);
                            }else if(_style.font.indexOf("FangSong") >=0){
                            	_style.font = _style.font.replace("FangSong",param1);
                            }else if(_style.font.indexOf("Arial") >=0){
                                _style.font = _style.font.replace("Arial",param1);
                            }else if(_style.font.indexOf("times New Roman") >=0){
                                _style.font = _style.font.replace("times New Roman",param1);
                            }else if(_style.font.indexOf("Verdana") >=0){
                                _style.font = _style.font.replace("Verdana",param1);
                            }
                        }
                        if(!cell.efont)		cell.efont={};
                        cell.efont.f_family = param1;
                    }else if (styleWay == "fontsize"){
                        if(_style.font == undefined)
                            _style.font = param1 + " Microsoft YaHei";
                        else{
                            _style.font = _style.font.replace((/\d+pt/g), param1);
                        }
                        if(!cell.efont)cell.efont={};
                        cell.efont.f_size = param1;
                    }else if(styleWay == "bold"){
                    	if(!cell.efont)		cell.efont={};
						if(_style == undefined || _style.font == undefined){
							if(addBold){
								_style.font = "bold 9pt Microsoft YaHei";
                                cell.efont.f_bold = true;
							}
						}else{
							if(addBold && _style.font.indexOf("bold") == -1){
								if(_style.font.indexOf("italic") > -1){
									_style.font = _style.font.replace("italic","italic bold");
								}else{
									_style.font = "bold " + _style.font;
								}
								cell.efont.f_bold = true;
							}
							if(!addBold && _style.font.indexOf("bold") > -1){
								_style.font = _style.font.replace("bold ","");
                                cell.efont.f_bold = false;
							}
						}
                    }else if(styleWay == "italic"){
                    	if(!cell.efont)		cell.efont={};
                    	if(_style == undefined || _style.font == undefined){
							if(addItalic){
								_style.font = "italic 9pt Microsoft YaHei";
                                cell.efont.f_italic = true;
							}
						}else{
							if(addItalic && _style.font.indexOf("italic") == -1){
								_style.font = "italic " + _style.font;
                                cell.efont.f_italic = true;
							}
							if(!addItalic && _style.font.indexOf("italic") > -1){
								_style.font = _style.font.replace("italic ","");
                                cell.efont.f_italic = false;
							}
						}
                    }
                    sheet.setStyle(i + range.row, j + range.col, _style, $.wijmo.wijspread.SheetArea.viewport);
                }
            }
        }
        sheet.isPaintSuspended(false);
    }
    catch (ex) {
        window.top.Dialog.alert(ex.message);
    }
}

/**
 * 对齐方式 封装
 */
function alignBx(alignJson, isdown){
	//垂直对齐
   	var a_valign = "";
   	a_valign = alignJson.a_valign;
	if(a_valign === "0")
		a_valign = $.wijmo.wijspread.VerticalAlign.top;
	else if(a_valign === "1"){
		a_valign = $.wijmo.wijspread.VerticalAlign.center;
		if(isdown == false)
			a_valign = $.wijmo.wijspread.VerticalAlign.top;
	}else if(a_valign === "2"){
		a_valign = $.wijmo.wijspread.VerticalAlign.bottom;
		if(isdown == false)
			a_valign = $.wijmo.wijspread.VerticalAlign.top;
	}else
		a_valign = null;
	//水平对齐	
	var a_halign = "";
	a_halign = alignJson.a_halign;
	if(a_halign === "0")
		a_halign = $.wijmo.wijspread.HorizontalAlign.left;
	else if(a_halign == "1"){
		a_halign = $.wijmo.wijspread.HorizontalAlign.center;
		if(isdown == false)
			a_halign = $.wijmo.wijspread.HorizontalAlign.left;
	}else if(a_halign == "2"){
		a_halign = $.wijmo.wijspread.HorizontalAlign.right;
		if(isdown == false)
			a_halign = $.wijmo.wijspread.HorizontalAlign.left;
	}else 
		a_halign = null;
    var sheet = getCurrentSheet();
    sheet.isPaintSuspended(true);
	var sels = sheet.getSelections();
 	for (var index = 0; index < sels.length; index++) {
   	 	var sel = getActualCellRange(sels[index], sheet.getRowCount(), sheet.getColumnCount());
   		if(a_valign != null)
   			sheet.getCells(sel.row, sel.col, sel.row + sel.rowCount - 1, sel.col + sel.colCount - 1, $.wijmo.wijspread.SheetArea.viewport).vAlign(a_valign);
       	if(a_halign != null)
       		sheet.getCells(sel.row, sel.col, sel.row + sel.rowCount - 1, sel.col + sel.colCount - 1, $.wijmo.wijspread.SheetArea.viewport).hAlign(a_halign);
    }
	sheet.isPaintSuspended(false);
}

/**
 * 增加/减少 缩进
 */
function cellRetract(type){
   	var sheet = getCurrentSheet();
   	sheet.isPaintSuspended(true);
   	var sels = sheet.getSelections();
	for (var n = 0; n < sels.length; n++) {
		var sel = getActualCellRange(sels[n], sheet.getRowCount(), sheet.getColumnCount());
		for (var i = 0; i < sel.rowCount; i++) {
			for (var j = 0; j < sel.colCount; j++) {
				var r = i + sel.row;
				var c = j + sel.col;
					
				var curTextIndent=sheet.getCell(r,c,$.wijmo.wijspread.SheetArea.viewport).textIndent();
				if(!curTextIndent) curTextIndent = 0;
				var cell = {};
				if(type === "left"){
					if(curTextIndent < 100){
						sheet.getCell(r,c,$.wijmo.wijspread.SheetArea.viewport).textIndent(curTextIndent+0.5);
						cell.etxtindent = curTextIndent + 2.5;
					}
				}else{	//减少缩进量
					if(curTextIndent > 0){
						if(curTextIndent > 2.5){
							sheet.getCell(r,c,$.wijmo.wijspread.SheetArea.viewport).textIndent(curTextIndent-0.5);
							cell.etxtindent = curTextIndent-0.5;
							cell.etxtindent = cell.etxtindent-2.5;
						}else{
							sheet.getCell(r,c,$.wijmo.wijspread.SheetArea.viewport).textIndent(curTextIndent-0.5);
							cell.etxtindent = curTextIndent-0.5;
						}
					}
				}
			}
		}
	}
	sheet.isPaintSuspended(false);
}

/**
 * 边框设置
 */
function drawborder(){
  	var sheet = getCurrentSheet();
	var whichborder = $("#whichborder").combobox("getValue");
	var bordercolor = $('#borderColorValue').val();
	var borderstyle = parseInt($("#borderstyle").combobox("getValue"));
    var lineBorder = new $.wijmo.wijspread.LineBorder(bordercolor, borderstyle);
    var sels = sheet.getSelections();
    sheet.isPaintSuspended(true);
    for (var n = 0; n < sels.length; n++) {
        var sel = getActualCellRange(sels[n], sheet.getRowCount(), sheet.getColumnCount());
        var haveborder = false;
        if(whichborder != "" && whichborder != "none"){
        	console.info(sheet);
        	console.info(sel);
        	console.info(whichborder);
        	console.info(lineBorder);
    		sheet.setBorder(sel, lineBorder, {
                  left: whichborder == "left",
                  right: whichborder == "right",
                  top: whichborder == "top",
                  bottom: whichborder == "bottom",
                  all: whichborder == "all",
                  outline: whichborder == "outline",
                  inside: whichborder == "inside",
                  innerHorizontal: whichborder == "hinside",
                  innerVertical: whichborder == "vinside"
            });
        }else{
            sheet.setBorder(sel, null, {all:true});
        }
    }
    sheet.isPaintSuspended(false);
}

/**
 * 自动换行封装
 */
function autowrapBx(wrap){
    var sheet = getCurrentSheet();
    sheet.isPaintSuspended(true);
    var sels = sheet.getSelections();
    for (var index = 0; index < sels.length; index++) {
        var sel = getActualCellRange(sels[index], sheet.getRowCount(), sheet.getColumnCount());
        sheet.getCells(sel.row, sel.col, sel.row + sel.rowCount - 1, sel.col + sel.colCount - 1, $.wijmo.wijspread.SheetArea.viewport).wordWrap(wrap);
    }
    sheet.isPaintSuspended(false);
}
//合并单元格
function mergeCell(event) {
	var KSreport = $("#KSreport").wijspread("spread");
	var sheet = KSreport.getActiveSheet();

	var sel = sheet.getSelections();
	if (sel.length > 0) {
		sel = getActualCellRange(sel[sel.length - 1], sheet.getRowCount(), sheet.getColumnCount());
		sheet.addSpan(sel.row, sel.col, sel.rowCount, sel.colCount);
	}
}
//拆分单元格
function unMergeCell(event) {
	var sheet = getCurrentSheet();

	var sel = sheet.getSelections();
	if (sel.length > 0) {
		sel = getActualCellRange(sel[sel.length - 1], sheet.getRowCount(), sheet.getColumnCount());
		sheet.isPaintSuspended(true);
		for (var i = 0; i < sel.rowCount; i++) {
			for (var j = 0; j < sel.colCount; j++) {
				sheet.removeSpan(i + sel.row, j + sel.col);
			}
		}
		sheet.isPaintSuspended(false);
	}
}
//插入行
function insertRow() {
	var sheet = getCurrentSheet();
	var sel = sheet.getSelections();
	if (sel.length > 0) {
		sel = getActualCellRange(sel[sel.length - 1], sheet.getRowCount(), sheet.getColumnCount());
	}
	if(sel.rowCount==0)sel.rowCount=1;
	sheet.addRows(sheet.getActiveRowIndex(), sel.rowCount);
}
//插入列
function insertCol() {
	var sheet = getCurrentSheet();
	var sel = sheet.getSelections();
	if (sel.length > 0) {
		sel = getActualCellRange(sel[sel.length - 1], sheet.getRowCount(), sheet.getColumnCount());
	}
	if(sel.rowCount==0)sel.rowCount=1;
	sheet.addColumns(sheet.getActiveColumnIndex(), sel.colCount);
}
//删除行
function deleteRow() {
	var sheet = getCurrentSheet();
	var sel = sheet.getSelections();
	if (sel.length > 0) {
		sel = getActualCellRange(sel[sel.length - 1], sheet.getRowCount(), sheet.getColumnCount());
	}
	if(sel.rowCount==0)sel.rowCount=1;
	sheet.deleteRows(sheet.getActiveRowIndex(), sel.rowCount);
}
//删除列
function deleteCol() {
	var sheet = getCurrentSheet();
	var sel = sheet.getSelections();
	if (sel.length > 0) {
		sel = getActualCellRange(sel[sel.length - 1], sheet.getRowCount(), sheet.getColumnCount());
	}
	if(sel.rowCount==0)sel.rowCount=1;
	sheet.deleteColumns(sheet.getActiveColumnIndex(), sel.colCount);
}

/**
 * 设置行高列宽
 */
function setRChw(rc){
   	var sheet = getCurrentSheet();
   	var sel = sheet.getSelections();
   	if (sel.length > 0) {
        sel = getActualCellRange(sel[sel.length - 1], sheet.getRowCount(), sheet.getColumnCount());
    }
    var defaultvar = 0;
    var iscolpercent = false;
    if(rc === "r"){
    	for(var i=0;i < sel.rowCount; i++){
    		if(i==0)defaultvar = sheet.getRowHeight(sel.row+i,$.wijmo.wijspread.SheetArea.viewport);
    		else{
    			if(sheet.getRowHeight(sel.row+i,$.wijmo.wijspread.SheetArea.viewport) != defaultvar){
    				defaultvar = -1;
    				break;
    			}
    		}
    	}
    }else if(rc === "c"){
    	for(var i=0;i < sel.colCount; i++){
    		var colheadtxt = sheet.getValue(0,sel.col+i,$.wijmo.wijspread.SheetArea.colHeader);
    		if(colheadtxt.indexOf("%") >0){
    			iscolpercent = true;
    			break;
   			}
    	}
    	if(iscolpercent){
    		for(var i=0;i < sel.colCount; i++){
        		var colheadtxt = sheet.getValue(0,sel.col+i,$.wijmo.wijspread.SheetArea.colHeader);
        		if(colheadtxt.indexOf("%") < 0){
        			defaultvar = -1;
        			break;
    			}
    			colheadtxt = colheadtxt.split(" ")[1];
        		if(i==0) defaultvar = parseInt(colheadtxt.substring(1,colheadtxt.lastIndexOf(")")));
        		else {
        			if(defaultvar != parseInt(colheadtxt.substring(1,colheadtxt.lastIndexOf(")")))){
        				defaultvar = -1;
        				break;
        			}
        		}
        	}
    	}else{
        	for(var i=0;i < sel.colCount; i++){
        		if(i==0)defaultvar = sheet.getColumnWidth(sel.col+i,$.wijmo.wijspread.SheetArea.viewport);
        		else{
        			if(sheet.getColumnWidth(sel.col+i,$.wijmo.wijspread.SheetArea.viewport) != defaultvar){	
        				defaultvar = -1;
        				break;
        			}
        		}
        	}
    	}
    }
    var title = "";
	if(rc==="r")
		title = "设置行高(px)";
	else
		title = "设置列宽(px)";
	var promptIndex = parent.layer.prompt({
		title: title,
		formType: 0 //prompt风格，支持0-2
	}, function(pass){
		if(!/^\+?[1-9][0-9]*$/.test(pass)){
			alert("请输入数字!");
		} else {
			if(rc === "r")
	        	for(var x=0;x<sel.rowCount;x++)
	        		sheet.setRowHeight(sel.row+x,parseInt(pass),$.wijmo.wijspread.SheetArea.viewport);
			else
				for(var y=0;y<sel.colCount;y++)
		            sheet.setColumnWidth(sel.col+y,parseInt(pass),$.wijmo.wijspread.SheetArea.viewport);
			parent.layer.close(promptIndex);
		}
	});
}

//剪切复制粘贴
function ccp(action){
	var sheet = getCurrentSheet();
	sheet.isPaintSuspended(true);
	if(action === "cut")
		$.wijmo.wijspread.SpreadActions.cut.call(sheet);
	else if(action === "copy")
		$.wijmo.wijspread.SpreadActions.copy.call(sheet);
	else if(action === "paste")
		$.wijmo.wijspread.SpreadActions.paste.call(sheet);
	sheet.isPaintSuspended(false);
}

/**
 * 清除单元格格式 1：样式:2：内容:3：全部
 */
function cleanCell(type){
	var sheet = getCurrentSheet();
	try{
      	var sels = sheet.getSelections();
      	sheet.isPaintSuspended(true);
		for (var n = 0; n < sels.length; n++) {
			var sel = getActualCellRange(sels[n], sheet.getRowCount(), sheet.getColumnCount());
			for (var i = 0; i < sel.rowCount; i++) {
				for (var j = 0; j < sel.colCount; j++) {
					var r = sel.row + i;
          			var c = sel.col + j;
					if(type == 1){
						sheet.setStyle(r, c, null, $.wijmo.wijspread.SheetArea.viewport);
                   		sheet.getCell(r,c,sheet.sheetArea).backgroundImage(null);
						sheet.getCell(r,c,sheet.sheetArea).backgroundImageLayout($.wijmo.wijspread.ImageLayout.None);
						sheet.getCell(r,c,sheet.sheetArea).textIndent(2.5);
						sheet.setIsProtected(false);
					}else if(type == 2){
						cleanCellText(sheet, r, c);
               			sheet.setIsProtected(false);
					}else if(type == 3){
						sheet.setStyle(r, c, null, $.wijmo.wijspread.SheetArea.viewport);
						sheet.getCell(r,c,$.wijmo.wijspread.SheetArea.viewport).value("");
						sheet.getCell(r,c,$.wijmo.wijspread.SheetArea.viewport).backgroundImage(null);
						sheet.getCell(r,c,$.wijmo.wijspread.SheetArea.viewport).textIndent(0);
						sheet.setIsProtected(false);
					}
				}
			}
		}
		sheet.isPaintSuspended(false);
	}catch(ex){
	}
}

/**
 * 清除指定单元格内容
 */
function cleanCellText(sheet, r, c){
	sheet.getCell(r,c,$.wijmo.wijspread.SheetArea.viewport).value("");
	sheet.getCell(r,c,$.wijmo.wijspread.SheetArea.viewport).backgroundImage(null);
	sheet.getCell(r,c,$.wijmo.wijspread.SheetArea.viewport).textIndent(0);
}

/**
 * 显示网格线
 */
function showGridLine(ishow){
	var sheet = getCurrentSheet();
	sheet.setGridlineOptions({ showHorizontalGridline: ishow, showVerticalGridline: ishow });
	sheet.invalidateLayout();
	sheet.repaint();
}

/**
 * 显示行、列头
 */
function showExcelHead(ishow){
	var sheet = getCurrentSheet();
	sheet.isPaintSuspended(true);
	sheet.setRowHeaderVisible(ishow);
	sheet.setColumnHeaderVisible(ishow);
	sheet.isPaintSuspended(false);
}

/***********************************************/
/**
 * 点击单元格---改变相关样式选中状态、相关操作权限
 */
function spreadCellClick(){
	var cellid = getSelectedCellid();
	var row = cellid.row;
	var col = cellid.col;
	
	var sheet = getCurrentSheet();
    var cell_style = sheet.getStyle(row, col, $.wijmo.wijspread.SheetArea.viewport);
    var isbold = 0;
   	var isitalic = 0;
   	var fontsize = "9pt";
   	var fontfamliy = "Microsoft YaHei";
   	var hAlign = -1;
   	var vAlign = -1;
   	var wordWrap = false;
    if(!!cell_style){    	
    	if(!!cell_style.hAlign)
    		hAlign = cell_style.hAlign;
    	else {
			//如果本身未设置居中方式 是数值类型 默认右对齐
			if(typeof sheet.getCell(row, col).value() == "number" || sheet.getCell(row, col).value() instanceof Number) {
				hAlign = 2;
			}
    	}
    	if(!!cell_style.vAlign)
    		vAlign = cell_style.vAlign;
   		var cell_font = cell_style.font;
    	if(!!cell_font){
    		if(cell_font.indexOf("bold")>=0)
    			isbold = 1;
    		if(cell_font.indexOf("italic")>=0)
    			isitalic = 1;
    		if(cell_font.indexOf("pt"))
				fontsize = cell_font.match(/\d+pt/g);
			else if(cell_font.indexOf("px"))
				fontsize = cell_font.match(/\d+px/g);
    		if(cell_font.indexOf("SimSun") >=0)fontfamliy = "SimSun";
            else if(cell_font.indexOf("SimHei") >=0)fontfamliy = "SimHei";
            else if(cell_font.indexOf("Microsoft YaHei") >=0)fontfamliy = "Microsoft YaHei";
            else if(cell_font.indexOf("KaiTi") >=0)fontfamliy = "KaiTi";
           	else if(cell_font.indexOf("YouYuan") >=0)fontfamliy = "YouYuan";
            else if(cell_font.indexOf("Arial") >=0)fontfamliy = "Arial";
            else if(cell_font.indexOf("FangSong") >=0)fontfamliy = "FangSong";
    	}
    	if(!!cell_style.wordWrap)
    		wordWrap = cell_style.wordWrap;
    }
    setHeadOperatPanelByCellStyle(isbold,isitalic,fontsize,fontfamliy,hAlign,vAlign,wordWrap);
}

//反向设置样式操作栏
function setHeadOperatPanelByCellStyle(isbold,isitalic,fontsize,fontfamliy,hAlign,vAlign,wordWrap){
	//字体设置
	if(isbold == 1) $('#blod').linkbutton('select');
	else $('#blod').linkbutton('unselect');
	if(isitalic == 1) $('#italic').linkbutton('select');
	else $('#italic').linkbutton('unselect');
	$('#fontsize').combobox('setValue', fontsize);
	$('#fontfamily').combobox('setValue', fontfamliy);
	//垂直对齐
	if(vAlign == 0){
		$('#topAlign').linkbutton('select');
	}else if(vAlign == 1){
		$('#middelAlign').linkbutton('select');
	}else if(vAlign == 2){
		$('#bottomAlign').linkbutton('select');
	}else{
		$('#topAlign').linkbutton('select');
	}
	//水平对齐
	if(hAlign == 0){
		$('#leftAlign').linkbutton('select');
	}else if(hAlign == 1){
		$('#centerAlign').linkbutton('select');
	}else if(hAlign == 2){
		$('#rightAlign').linkbutton('select');
	}else{
		$('#leftAlign').linkbutton('select');
	}
	//自动换行
	if(wordWrap) $('#autowarp').linkbutton('select');
	else $('#autowarp').linkbutton('unselect');
}

function setting() {
	var sheet = getCurrentSheet();
	var r=1,c=1;
	var imgsrc = "/kensite/static/exceldesign/img/field/sqlReport.png";//图的名称很重要
	sheet.getCell(r,c,sheet.sheetArea).backgroundImage(imgsrc);
	sheet.getCell(r,c,sheet.sheetArea).backgroundImageLayout($.wijmo.wijspread.ImageLayout.None);
	console.info($("#KSreport").data('spread').toJSON( { includeBindingSource: true } ).sheets["KS Report"].data.dataTable[r][c].style);
	console.info($("#KSreport").data('spread').toJSON( { includeBindingSource: true } ));
	console.info($("#KSreport").wijspread("spread").toJSON());
	var style = new $.wijmo.wijspread.Style();
	style.backColor = 'red';
	style.foreColor = 'green';
	sheet.setStyle(5, 5, style, $.wijmo.wijspread.SheetArea.viewport);
	//sheet.getCell(r,c,sheet.sheetArea).textIndent(2.5);
}
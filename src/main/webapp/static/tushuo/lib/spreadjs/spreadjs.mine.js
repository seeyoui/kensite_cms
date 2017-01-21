
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


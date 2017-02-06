window.onload = function () {
    var spread = new GcSpread.Sheets.Spread(document.getElementById('KSreport'), { sheetCount: 1 });
    var sheet = spread.getActiveSheet();
    sheet.isPaintSuspended(true);
    sheet.isPaintSuspended(false);
	var cellType2 = new GcSpread.Sheets.ComboBoxCellType();
	cellType2.items(["a","b","c"]);
	sheet.getCell(2, 2).cellType(cellType2);
};
var chartObj = null, chartOpt = null, editor_func = null;
define(function(require) {
	var menu = require('./menu');
	var chart = require('./chart');
	var layui = require('./layui');
	var btn = require('./btn');
	menu.init();
	chart.init();
	layui.init();
	btn.init();
});
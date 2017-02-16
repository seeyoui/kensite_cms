define(function(require, exports, module) {

    var $ = require('jquery');
    var layui = require('./layui.js');
    var chart = require('./chart.js');
    var loadi = null;
    module.exports = {
        init: function(data) {
            $('.dtm-close-btn').on('click', function(event) {
            	var formData = layui.formData();
            	if(!formData) {
            		return false;
            	}
            	if(chartOpt.series==null || chartOpt.series.length==0) {
            		layer.tips('请先配置参数', '.dtm-close-btn', {
        				tips: [2, '#ea5532']
        			});
            		return false;
            	}
            	chartOpt.legend.data = [];
            	chartOpt.xAxis[0].data = [];
            	for(var i=0; i<chartOpt.series.length; i++) {
            		chartOpt.series[i].data = [];
            		chartOpt.series[i].name = '';
            	}
            	formData['id'] = $(this).data('id');
            	formData['setOption'] = JSON.stringify(chartOpt);
            	$.ajax({
					type: "post",
					url: ctx+'/ks/chartEngine/update',
					data: formData,
					dataType: 'json',
					timeout: 10000,
					beforeSend: function(XMLHttpRequest){
						loadi = layer.load(2, {shade: layerLoadShade,time: 10000});
					},
					success: function(data, textStatus){
						layer.close(loadi);
						chart.loadData();
						if (data.success=='T'){
							layer.msg("操作成功！", {offset: 'rb',icon: 6,shift: 8,time: 1500});
						} else {
							layer.msg("操作失败！", {offset: 'rb',icon: 5,shift: 8,time: 1500});
						}
					},
					error: function(request, errType) {
						layer.close(loadi);
						//"timeout", "error", "notmodified" 和 "parsererror"
						if(errType == 'timeout') {
							layer.msg('请求超时', {offset: 'rb',icon: 6,shift: 8,time: 1500});
						}
						if(errType == 'error') {
							layer.msg('系统错误，请联系管理员', {offset: 'rb',icon: 6,shift: 8,time: 1500});
						}
					}
				});
            });
        }
    };
});
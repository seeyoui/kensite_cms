$(document).ready(function(){
	initChart();
	var parentObj = 'base';
	$('.dtm-tab0 li').on('click', function(event) {
		parentObj = $(this).data('cpt-tab0');
		$('.dtm-tab0 li').removeClass('dtm-tab0-hi-curr');
		$(this).addClass('dtm-tab0-hi-curr');
		$('div[data-cpt-con0]').hide();
		$('[data-cpt-con0="'+parentObj+'"]').show();
	});
	$('div[data-cpt-tab1]').on('click', function(event) {
		var childObj = $(this).data('cpt-tab1');
		$('[data-cpt-con0="'+parentObj+'"] div[data-cpt-tab1]').removeClass('dtm-tab1-hi-curr');
		$(this).addClass('dtm-tab1-hi-curr');
		$('[data-cpt-con0="'+parentObj+'"] div[data-cpt-con1]').hide();
		$('[data-cpt-con0="'+parentObj+'"] [data-cpt-con1="'+childObj+'"]').show();
	});
	$('.cpt-chkbtn-i').on('click', function(event) {
		if($(this).parent().hasClass('cpt-disabled')) return;
		var opt = $(this).data('opt');
		$(this).siblings('div').removeClass('cpt-chkbtn-i-active');
		$(this).addClass('cpt-chkbtn-i-active');
		console.info(opt);
		eval('chartOpt.'+opt+';');
		mainChart.setOption(chartOpt);
	});
	$('div[data-dsp]').on('click', function(event) {
		var dsp = $(this).data('dsp');
		if($(this).parent().hasClass('cpt-disabled')) {
			$(this).parent().removeClass('cpt-disabled');
			$(this).parent().removeClass('cpt-ggt-edtitm-disabled');
			$(this).parent().removeClass('dtm-edtitm-disabled');
			$(this).parent().find('div').removeClass('cpt-disabled');
			$(this).parent().find('div').removeClass('cpt-sltggt-disabled');
			$(this).parent().find('div').removeClass('dtm-edtitm-ggt-disabled');
			$(this).parent().find('div').removeClass('cpt-chkbtn-disabled');
			for(var i=0; i<$(this).parent().find('div[data-opt]').length; i++) {
				var obj = $(this).parent().find('div[data-opt]')[i];
				if($(obj).hasClass('cpt-chkbtn-i-active')) {
					var opt = $(obj).data('opt');
					eval('chartOpt.'+opt+';');
					mainChart.setOption(chartOpt);
				}
			}
		} else {
			$(this).parent().addClass('cpt-disabled');
			$(this).parent().addClass('cpt-ggt-edtitm-disabled');
			$(this).parent().addClass('dtm-edtitm-disabled');
			$(this).parent().find('div').addClass('cpt-disabled');
			$(this).parent().find('div').addClass('cpt-sltggt-disabled');
			$(this).parent().find('div').addClass('dtm-edtitm-ggt-disabled');
			$(this).parent().find('div').addClass('cpt-chkbtn-disabled');
			eval('chartOpt.'+dsp+'false;');
			mainChart.setOption(chartOpt);
		}
		console.info(dsp);
	});
	$(':input').on('change', function(event) {
		console.info($(this).val());
		console.info($(this).parents('div[data-dsp]').length);
	});
});

var mainChart = null;
var chartOpt = null;
function initChart() {
	// 基于准备好的dom，初始化echarts实例
	mainChart = echarts.init(document.getElementById('main'));

	// 指定图表的配置项和数据
	chartOpt = {
	    title : {
	        text: '某站点用户访问来源',
	        subtext: '纯属虚构',
	        x:'center'
	    },
	    tooltip : {
	        trigger: 'item',
	        formatter: "{a} <br/>{b} : {c} ({d}%)"
	    },
	    legend: {
	        orient: 'vertical',
	        left: 'left',
	        data: ['直接访问','邮件营销','联盟广告','视频广告','搜索引擎']
	    },
	    series : [
	        {
	            name: '访问来源',
	            type: 'pie',
	            radius : '55%',
	            center: ['50%', '60%'],
	            data:[
	                {value:335, name:'直接访问'},
	                {value:310, name:'邮件营销'},
	                {value:234, name:'联盟广告'},
	                {value:135, name:'视频广告'},
	                {value:154, name:'搜索引擎'}
	            ],
	            itemStyle: {
	                emphasis: {
	                    shadowBlur: 10,
	                    shadowOffsetX: 0,
	                    shadowColor: 'rgba(0, 0, 0, 0.5)'
	                }
	            }
	        }
	    ]
	};

	// 使用刚指定的配置项和数据显示图表。
	mainChart.setOption(chartOpt);
}
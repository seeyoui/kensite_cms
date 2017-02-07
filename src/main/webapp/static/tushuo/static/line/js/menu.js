define(function(require, exports, module) {

    var $ = require('jquery');
    var chart = require('./chart.js');

    module.exports = {
        init: function() {
            var parentObj = 'base';
            //顶部菜单点击事件
            $('.dtm-tabh-h li').unbind('click').bind('click', function(event) {
				tabh = $(this).data('cpt-tabh');
				if(tabh == 'chartConfigurer' && $(this).hasClass('dtm-tabh-hi-curr')) {
					return;
				}
				if(tabh == 'chartConfigurer') {
					if(!chart.loadData(module.exports)) {
						return;
					}
				}
				if(tabh == 'extendJs') {
					//chart.initCode();
					setTimeout(function(){chart.initCode();}, 10);
				}
				
				$('.dtm-tabh-h li').removeClass('dtm-tabh-hi-curr');
				$(this).addClass('dtm-tabh-hi-curr');
				$('div[data-cpt-conh]').hide();
				$('[data-cpt-conh="'+tabh+'"]').show();
			});
            //一级菜单点击事件
			$('.dtm-tab0 li').unbind('click').bind('click', function(event) {
				parentObj = $(this).data('cpt-tab0');
				$('.dtm-tab0 li').removeClass('dtm-tab0-hi-curr');
				$(this).addClass('dtm-tab0-hi-curr');
				$('div[data-cpt-con0]').hide();
				$('[data-cpt-con0="'+parentObj+'"]').show();
			});
			//二级菜单点击事件
			$('div[data-cpt-tab1]').unbind('click').bind('click', function(event) {
				var childObj = $(this).data('cpt-tab1');
				$('[data-cpt-con0="'+parentObj+'"] div[data-cpt-tab1]').removeClass('dtm-tab1-hi-curr');
				$(this).addClass('dtm-tab1-hi-curr');
				$('[data-cpt-con0="'+parentObj+'"] div[data-cpt-con1]').hide();
				$('[data-cpt-con0="'+parentObj+'"] [data-cpt-con1="'+childObj+'"]').show();
			});
			//表单标题点击事件，启动或者关闭项
			/*
			$('div[data-dsp]').unbind('click').bind('click', function(event) {
				if($(this).parent().hasClass('cpt-disabled')) {
					$(this).parent().removeClass('cpt-disabled');
					$(this).parent().removeClass('cpt-ggt-edtitm-disabled');
					$(this).parent().removeClass('dtm-edtitm-disabled');
					$(this).parent().find('div').removeClass('cpt-disabled');
					$(this).parent().find('div').removeClass('cpt-sltggt-disabled');
					$(this).parent().find('div').removeClass('dtm-edtitm-ggt-disabled');
					$(this).parent().find('div').removeClass('cpt-chkbtn-disabled');
					chart.setOption(this, true);
				} else {
					$(this).parent().addClass('cpt-disabled');
					$(this).parent().addClass('cpt-ggt-edtitm-disabled');
					$(this).parent().addClass('dtm-edtitm-disabled');
					$(this).parent().find('div').addClass('cpt-disabled');
					$(this).parent().find('div').addClass('cpt-sltggt-disabled');
					$(this).parent().find('div').addClass('dtm-edtitm-ggt-disabled');
					$(this).parent().find('div').addClass('cpt-chkbtn-disabled');
					chart.setOption(this, false);
				}
			});
			*/
			//单选按钮点击事件
			$('.cpt-chkbtn-i').unbind('click').bind('click', function(event) {
				if($(this).parent().hasClass('cpt-disabled')) return;
				$(this).siblings('div').removeClass('cpt-chkbtn-i-active');
				$(this).addClass('cpt-chkbtn-i-active');
				chart.setOption(this, false, 'btn');
			});
			//输入框改变事件
			$(':input').unbind('change').bind('change', function(event) {
				chart.setOption(this, false, 'ipt');
			});
        	module.exports.renderData();
        },
        createSeries: function(seriesArr) {
        	var index = $('#legend-div div[data-cpt-con1]').length;
        	var content = '';
        	$.get('/kensite/static/tushuo/static/line/tpl/legend.tpl').success(function(ctnt) {
                //$('#legend-div').append(content);
                content = ctnt;
            }).error(function (XMLHttpRequest, textStatus, errorThrown) {
                content = XMLHttpRequest.responseText;
            }).complete(function () {
            	var menu1 = ' dtm-tab1-hi-curr';
            	var display = 'block';
	        	for(var i=0; i<seriesArr.length; i++) {
	        		var series = seriesArr[i];
	        		var seriesKey = series.zkey;
	        		var seriesName = series.name;
	        		if(module.exports.checkSeries(seriesKey)) {
	        			continue;
	        		}
	        		var c = content;
		            $('#legend-menu').append('<div data-cpt-tab1="seriesTabKey'+seriesKey+'" class="cpt cpt-foreach-item dtm-tab1-hi'+menu1+'">系列'+(index++)+'</div>');
	            	while(c.indexOf('$$key$$') != -1) {
	            		c = c.replace('$$key$$', seriesKey);
	            	}
	            	c = c.replace('$$name$$', seriesName);
	            	c = c.replace('$$display$$', display);
	                $('#legend-div').append(c);
	            	menu1 = '';
	            	display = 'none';
	        	}
	        	module.exports.init();
            });
        },
        checkSeries: function(zkey) {
        	if($('#legend-div div[data-cpt-con1]').length == 0) {
        		return false;
        	}
        	var divArr = $('#legend-div div[data-cpt-con1]');
        	for(var i=0; i<divArr.length; i++) {
				if($(divArr[i]).data('cpt-con1') == ('seriesTabKey'+zkey)) {
					return true;
				}
			}
			return false;
        },
        renderData: function() {
        	if(chartOpt != null) {
        		this.renderSeries();
        		this.renderTitle();
        		this.renderLegend();
        		this.renderTooltip();
        		this.renderToolbox();
        	}
        },
        renderSeries: function() {
        	var seriesArr = chartOpt.series;
        	for(var i=0; i<seriesArr.length; i++) {
        		var series = seriesArr[i];
    			if(series.type != null) {
    				$('div[data-cpt-con1="seriesTabKey'+series.zkey+'"]').find('div[data-cate="type"]').removeClass('cpt-chkbtn-i-active');
    				$('div[data-cpt-con1="seriesTabKey'+series.zkey+'"]').find('div[data-type="type.'+series.type+'"]').addClass('cpt-chkbtn-i-active');
    			}
    			if(series.label.normal.show != null) {
    				$('div[data-cpt-con1="seriesTabKey'+series.zkey+'"]').find('div[data-cate="label.normal.show"]').removeClass('cpt-chkbtn-i-active');
    				$('div[data-cpt-con1="seriesTabKey'+series.zkey+'"]').find('div[data-type="label.normal.show.'+series.label.normal.show+'"]').addClass('cpt-chkbtn-i-active');
    			}
    			if(series.label.normal.position != null) {
    				$('div[data-cpt-con1="seriesTabKey'+series.zkey+'"]').find('div[data-cate="label.normal.position"]').removeClass('cpt-chkbtn-i-active');
    				$('div[data-cpt-con1="seriesTabKey'+series.zkey+'"]').find('div[data-type="label.normal.position.'+series.label.normal.position+'"]').addClass('cpt-chkbtn-i-active');
    			}
    			if(series.markPoint.data != null) {
    				var isT = true;
    				if(series.markPoint.data.length == 0) {
    					isT = false;
    				}
    				$('div[data-cpt-con1="seriesTabKey'+series.zkey+'"]').find('div[data-cate="markPoint.data"]').removeClass('cpt-chkbtn-i-active');
    				$('div[data-cpt-con1="seriesTabKey'+series.zkey+'"]').find('div[data-type="markPoint.data.'+isT+'"]').addClass('cpt-chkbtn-i-active');
    			}
    			if(series.markLine.data != null) {
    				var isT = true;
    				if(series.markLine.data.length == 0) {
    					isT = false;
    				}
    				$('div[data-cpt-con1="seriesTabKey'+series.zkey+'"]').find('div[data-cate="markLine.data"]').removeClass('cpt-chkbtn-i-active');
    				$('div[data-cpt-con1="seriesTabKey'+series.zkey+'"]').find('div[data-type="markLine.data.'+isT+'"]').addClass('cpt-chkbtn-i-active');
    			}
    			if(series.stack != null) {
    				$('div[data-cpt-con1="seriesTabKey'+series.zkey+'"]').find('input[data-type="stack"]').val(series.stack);
    			}
        	}
        },
        renderTitle: function() {
        	var title = chartOpt.title;
			if(title.text != null) {
				$('input[data-type="title.text"]').val(title.text);
			}
			if(title.link != null) {
				$('input[data-type="title.link"]').val(title.link);
			}
			if(title.subtext != null) {
				$('input[data-type="title.subtext"]').val(title.subtext);
			}
			if(title.sublink != null) {
				$('input[data-type="title.sublink"]').val(title.sublink);
			}
			if(title.x != null) {
				$('div[data-cate="title.x"]').removeClass('cpt-chkbtn-i-active');
				$('div[data-type="title.x.'+title.x+'"]').addClass('cpt-chkbtn-i-active');
			}
			if(title.y != null) {
				$('div[data-cate="title.y"]').removeClass('cpt-chkbtn-i-active');
				$('div[data-type="title.y.'+title.y+'"]').addClass('cpt-chkbtn-i-active');
			}
        },
        renderLegend: function() {
        	var legend = chartOpt.legend;
			if(legend.show != null) {
				$('div[data-cate="legend.show"]').removeClass('cpt-chkbtn-i-active');
				$('div[data-type="legend.show.'+legend.show+'"]').addClass('cpt-chkbtn-i-active');
			}
			if(legend.x != null) {
				$('div[data-cate="legend.x"]').removeClass('cpt-chkbtn-i-active');
				$('div[data-type="legend.x.'+legend.x+'"]').addClass('cpt-chkbtn-i-active');
			}
			if(legend.y != null) {
				$('div[data-cate="legend.y"]').removeClass('cpt-chkbtn-i-active');
				$('div[data-type="legend.y.'+legend.y+'"]').addClass('cpt-chkbtn-i-active');
			}
			if(legend.orient != null) {
				$('div[data-cate="legend.orient"]').removeClass('cpt-chkbtn-i-active');
				$('div[data-type="legend.orient.'+legend.orient+'"]').addClass('cpt-chkbtn-i-active');
			}
        },
        renderTooltip: function() {
        	var tooltip = chartOpt.tooltip;
			if(tooltip.formatter != null) {
				$('input[data-type="tooltip.formatter"]').val(tooltip.formatter);
			}
			if(tooltip.show != null) {
				$('div[data-cate="tooltip.show"]').removeClass('cpt-chkbtn-i-active');
				$('div[data-type="tooltip.show.'+tooltip.show+'"]').addClass('cpt-chkbtn-i-active');
			}
        },
        renderToolbox: function() {
        	var toolbox = chartOpt.toolbox;
			if(toolbox.show != null) {
				$('div[data-cate="toolbox.show"]').removeClass('cpt-chkbtn-i-active');
				$('div[data-type="toolbox.show.'+toolbox.show+'"]').addClass('cpt-chkbtn-i-active');
			}
			if(toolbox.x != null) {
				$('div[data-cate="toolbox.x"]').removeClass('cpt-chkbtn-i-active');
				$('div[data-type="toolbox.x.'+toolbox.x+'"]').addClass('cpt-chkbtn-i-active');
			}
			if(toolbox.y != null) {
				$('div[data-cate="toolbox.y"]').removeClass('cpt-chkbtn-i-active');
				$('div[data-type="toolbox.y.'+toolbox.y+'"]').addClass('cpt-chkbtn-i-active');
			}
			if(toolbox.orient != null) {
				$('div[data-cate="toolbox.orient"]').removeClass('cpt-chkbtn-i-active');
				$('div[data-type="toolbox.orient.'+toolbox.orient+'"]').addClass('cpt-chkbtn-i-active');
			}
			if(toolbox.feature.magicType.show != null) {
				$('div[data-cate="toolbox.feature.magicType.show"]').removeClass('cpt-chkbtn-i-active');
				$('div[data-type="toolbox.feature.magicType.show.'+toolbox.feature.magicType.show+'"]').addClass('cpt-chkbtn-i-active');
			}
			if(toolbox.feature.dataView.show != null) {
				$('div[data-cate="toolbox.feature.dataView.show"]').removeClass('cpt-chkbtn-i-active');
				$('div[data-type="toolbox.feature.dataView.show.'+toolbox.feature.dataView.show+'"]').addClass('cpt-chkbtn-i-active');
			}
			if(toolbox.feature.restore.show != null) {
				$('div[data-cate="toolbox.feature.restore.show"]').removeClass('cpt-chkbtn-i-active');
				$('div[data-type="toolbox.feature.restore.show.'+toolbox.feature.restore.show+'"]').addClass('cpt-chkbtn-i-active');
			}
			if(toolbox.feature.saveAsImage.show != null) {
				$('div[data-cate="toolbox.feature.saveAsImage.show"]').removeClass('cpt-chkbtn-i-active');
				$('div[data-type="toolbox.feature.saveAsImage.show.'+toolbox.feature.saveAsImage.show+'"]').addClass('cpt-chkbtn-i-active');
			}
        }
    };
});

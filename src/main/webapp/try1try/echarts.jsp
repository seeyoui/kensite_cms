<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/taglib/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Kensite开发平台</title>
<%@ include file="/WEB-INF/view/taglib/header.jsp"%>
<%@ include file="/WEB-INF/view/taglib/easyui.jsp"%>
<%@ include file="/WEB-INF/view/taglib/layer.jsp"%>
<%@ include file="/WEB-INF/view/taglib/echarts.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
	});
</script>
<script type="text/javascript">
	var myChart = null;
	var echartObj = null;
	// 使用
	require(
		[
			'echarts', 'echarts/chart/bar',// 使用柱状图就加载bar模块，按需加载
			'echarts', 'echarts/chart/line',// 使用柱状图就加载bar模块，按需加载
			'echarts', 'echarts/chart/pie'
		],
		function(ec) {
			// 基于准备好的dom，初始化echarts图表
			echartObj = ec;
		});
</script>
<script type="text/javascript">
var propertygrid_data = {"total":1000,"rows":[
	{"name":"title_show","value":"true","group":"标题设置","editor":{
		"type":"checkbox",
		"options":{
			"on":true,
			"off":false
		}
	}},
	{"name":"title_text","value":"","group":"标题设置","editor":"text"},
	{"name":"title_link","value":"","group":"标题设置","editor":{
		"type":"validatebox",
		"options":{
			"validType":"url"
		}
	}},
	{"name":"title_target","value":"blank","group":"标题设置","editor":{
        "type":"combobox",
        "options":{
            "valueField":"value",
            "textField":"text",
            "method":"get",
            "editable":false,
            "data":[{"value":"","text":"none"},{"value":"self","text":"self"},{"value":"blank","text":"blank"}],
            "panelHeight":"auto"
        }
    }},
	{"name":"title_subtext","value":"","group":"标题设置","editor":"text"},
	{"name":"title_sublink","value":"","group":"标题设置","editor":{
		"type":"validatebox",
		"options":{
			"validType":"url"
		}
	}},
	{"name":"title_subtarget","value":"blank","group":"标题设置","editor":{
        "type":"combobox",
        "options":{
            "valueField":"value",
            "textField":"text",
            "method":"get",
            "editable":false,
            "data":[{"value":"","text":"none"},{"value":"self","text":"self"},{"value":"blank","text":"blank"}],
            "panelHeight":"auto"
        }
    }},
	{"name":"title_x","value":"left","group":"标题设置","editor":{
        "type":"combobox",
        "options":{
            "valueField":"value",
            "textField":"text",
            "method":"get",
            //"editable":false,
            "data":[{"value":"center","text":"center"},{"value":"left","text":"left"},{"value":"right","text":"right"}],
            "panelHeight":"auto"
        }
    }},
	{"name":"title_y","value":"top","group":"标题设置","editor":{
        "type":"combobox",
        "options":{
            "valueField":"value",
            "textField":"text",
            "method":"get",
            //"editable":false,
            "data":[{"value":"top","text":"top"},{"value":"bottom","text":"bottom"},{"value":"center","text":"center"}],
            "panelHeight":"auto"
        }
    }},
    /*******************************/
	{"name":"toolbox_show","value":"true","group":"工具栏设置","editor":{
		"type":"checkbox",
		"options":{
			"on":true,
			"off":false
		}
	}},
	{"name":"toolbox_orient","value":"horizontal","group":"工具栏设置","editor":{
        "type":"combobox",
        "options":{
            "valueField":"value",
            "textField":"text",
            "method":"get",
            "editable":false,
            "data":[{"value":"horizontal","text":"horizontal"},{"value":"vertical","text":"vertical"}],
            "panelHeight":"auto"
        }
    }},
	{"name":"toolbox_showTitle","value":"true","group":"工具栏设置","editor":{
		"type":"checkbox",
		"options":{
			"on":true,
			"off":false
		}
	}},
	{"name":"toolbox_x","value":"right","group":"工具栏设置","editor":{
        "type":"combobox",
        "options":{
            "valueField":"value",
            "textField":"text",
            "method":"get",
            //"editable":false,
            "data":[{"value":"center","text":"center"},{"value":"left","text":"left"},{"value":"right","text":"right"}],
            "panelHeight":"auto"
        }
    }},
	{"name":"toolbox_y","value":"top","group":"工具栏设置","editor":{
        "type":"combobox",
        "options":{
            "valueField":"value",
            "textField":"text",
            "method":"get",
            //"editable":false,
            "data":[{"value":"top","text":"top"},{"value":"bottom","text":"bottom"},{"value":"center","text":"center"}],
            "panelHeight":"auto"
        }
    }},
	{"name":"toolbox_feature_mark","value":"true","group":"工具栏设置","editor":{
		"type":"checkbox",
		"options":{
			"on":true,
			"off":false
		}
	}},
	{"name":"toolbox_feature_dataZoom","value":"true","group":"工具栏设置","editor":{
		"type":"checkbox",
		"options":{
			"on":true,
			"off":false
		}
	}},
	{"name":"toolbox_feature_dataView","value":"true","group":"工具栏设置","editor":{
		"type":"checkbox",
		"options":{
			"on":true,
			"off":false
		}
	}},
	{"name":"toolbox_feature_magicType","value":"true","group":"工具栏设置","editor":{
		"type":"checkbox",
		"options":{
			"on":true,
			"off":false
		}
	}},
	{"name":"toolbox_feature_restore","value":"true","group":"工具栏设置","editor":{
		"type":"checkbox",
		"options":{
			"on":true,
			"off":false
		}
	}},
	{"name":"toolbox_feature_saveAsImage","value":"true","group":"工具栏设置","editor":{
		"type":"checkbox",
		"options":{
			"on":true,
			"off":false
		}
	}},
    /*******************************/
	{"name":"tooltip_show","value":"true","group":"提示框设置","editor":{
		"type":"checkbox",
		"options":{
			"on":true,
			"off":false
		}
	}},
	{"name":"tooltip_trigger","value":"item","group":"提示框设置","editor":{
        "type":"combobox",
        "options":{
            "valueField":"value",
            "textField":"text",
            "method":"get",
            "editable":false,
            "data":[{"value":"item","text":"item"},{"value":"axis","text":"axis"}],
            "panelHeight":"auto"
        }
    }},
    /*******************************/
	{"name":"legend_show","value":"true","group":"图例设置","editor":{
		"type":"checkbox",
		"options":{
			"on":true,
			"off":false
		}
	}},
	{"name":"legend_orient","value":"horizontal","group":"图例设置","editor":{
        "type":"combobox",
        "options":{
            "valueField":"value",
            "textField":"text",
            "method":"get",
            "editable":false,
            "data":[{"value":"horizontal","text":"horizontal"},{"value":"vertical","text":"vertical"}],
            "panelHeight":"auto"
        }
    }},
	{"name":"legend_x","value":"center","group":"图例设置","editor":{
        "type":"combobox",
        "options":{
            "valueField":"value",
            "textField":"text",
            "method":"get",
            //"editable":false,
            "data":[{"value":"center","text":"center"},{"value":"left","text":"left"},{"value":"right","text":"right"}],
            "panelHeight":"auto"
        }
    }},
	{"name":"legend_y","value":"top","group":"图例设置","editor":{
        "type":"combobox",
        "options":{
            "valueField":"value",
            "textField":"text",
            "method":"get",
            //"editable":false,
            "data":[{"value":"top","text":"top"},{"value":"bottom","text":"bottom"},{"value":"center","text":"center"}],
            "panelHeight":"auto"
        }
    }},
	{"name":"legend_data","value":"","group":"图例设置","editor":"text"},
	/*******************************/
	{"name":"yAxis_show_l","value":"true","group":"Y坐标轴设置","editor":{
		"type":"checkbox",
		"options":{
			"on":true,
			"off":false
		}
	}},
	{"name":"yAxis_axisLabel_l","value":"","group":"Y坐标轴设置","editor":"text"},
	{"name":"yAxis_show_r","value":"false","group":"Y坐标轴设置","editor":{
		"type":"checkbox",
		"options":{
			"on":true,
			"off":false
		}
	}},
	{"name":"yAxis_axisLabel_r","value":"","group":"Y坐标轴设置","editor":"text"},
    /*******************************/
	{"name":"xAxis_show_l","value":"true","group":"X坐标轴设置","editor":{
		"type":"checkbox",
		"options":{
			"on":true,
			"off":false
		}
	}},
	{"name":"xAxis_data_l","value":"","group":"X坐标轴设置","editor":"text"},
	{"name":"xAxis_axisLabel_l","value":"","group":"X坐标轴设置","editor":"text"},
	{"name":"xAxis_show_r","value":"false","group":"X坐标轴设置","editor":{
		"type":"checkbox",
		"options":{
			"on":true,
			"off":false
		}
	}},
	{"name":"xAxis_data_r","value":"","group":"X坐标轴设置","editor":"text"},
	{"name":"xAxis_axisLabel_r","value":"","group":"X坐标轴设置","editor":"text"},
    /*******************************/
	{"name":"series_data","value":"","group":"数据内容数组","editor":{
		"type":"textbox",
		"options":{
			"editable":false,
			"icons": [{
				"iconCls":"icon-chart_line",
				"handler": function(e){
					$(e.data.target).textbox("setValue", '[{"name": "蒸发量","type": "line","data": function() {var list = [];for (var i = 1; i <= 5; i++) {list.push(Math.round(Math.random() * 30));}return list;} ()}]');
				}
			}]
		}
	}},
	/*******************************/
	{"name":"dataZoom_show","value":"false","group":"数据区域缩放设置","editor":{
		"type":"checkbox",
		"options":{
			"on":true,
			"off":false
		}
	}},
	{"name":"dataZoom_orient","value":"horizontal","group":"数据区域缩放设置","editor":{
        "type":"combobox",
        "options":{
            "valueField":"value",
            "textField":"text",
            "method":"get",
            "editable":false,
            "data":[{"value":"horizontal","text":"horizontal"},{"value":"vertical","text":"vertical"}],
            "panelHeight":"auto"
        }
    }},
    {"name":"dataZoom_start","value":"40","group":"数据区域缩放设置","editor":{
		"type":"numberbox",
		"options":{
			"min":0,
			"max":0,
			"precision":0
		}
	}},
    {"name":"dataZoom_end","value":"60","group":"数据区域缩放设置","editor":{
		"type":"numberbox",
		"options":{
			"min":0,
			"max":0,
			"precision":0
		}
	}}
]}
</script>
<script type="text/javascript">
function runTitle() {
	var rows = $('#property').datagrid('getRows');
	var show = getRowValueByName('title_show');
	var text = getRowValueByName('title_text');
	var link  = getRowValueByName('title_link');
	var target = getRowValueByName('title_target');
	var subtext = getRowValueByName('title_subtext');
	var sublink = getRowValueByName('title_sublink');
	var subtarget = getRowValueByName('title_subtarget');
	var x = getRowValueByName('title_x');
	var y = getRowValueByName('title_y');
	myChart.setOption({
		title : {
			show : show=='true',
			text : text,
			link : link,
			target : target,
			subtext : subtext,
			sublink : sublink,
			subtarget : subtarget,
			x : x,
			y : y
		}
	});
}
function runToolbox() {
	var show = getRowValueByName('toolbox_show');
	var orient = getRowValueByName('toolbox_orient');
	var x = getRowValueByName('toolbox_x');
	var y = getRowValueByName('toolbox_y');
	var showTitle = getRowValueByName('toolbox_showTitle');
	var mark = getRowValueByName('toolbox_feature_mark');
	var dataZoom = getRowValueByName('toolbox_feature_dataZoom');
	var dataView = getRowValueByName('toolbox_feature_dataView');
	var magicType = getRowValueByName('toolbox_feature_magicType');
	var restore = getRowValueByName('toolbox_feature_restore');
	var saveAsImage = getRowValueByName('toolbox_feature_saveAsImage');
	//var show = getRowValueByName('toolbox_');
	myChart.setOption({
		toolbox: {
	        show: show=='true',
	        orient: orient,      // 布局方式，默认为水平布局，可选为：
	                                   // 'horizontal' ¦ 'vertical'
	        x: x,                // 水平安放位置，默认为全图右对齐，可选为：
	                                   // 'center' ¦ 'left' ¦ 'right'
	                                   // ¦ {number}（x坐标，单位px）
	        y: y,                  // 垂直安放位置，默认为全图顶端，可选为：
	                                   // 'top' ¦ 'bottom' ¦ 'center'
	                                   // ¦ {number}（y坐标，单位px）
	        color : ['#1e90ff','#22bb22','#4b0082','#d2691e'],
	        backgroundColor: 'rgba(0,0,0,0)', // 工具箱背景颜色
	        borderColor: '#ccc',       // 工具箱边框颜色
	        borderWidth: 0,            // 工具箱边框线宽，单位px，默认为0（无边框）
	        padding: 5,                // 工具箱内边距，单位px，默认各方向内边距为5，
	        showTitle: showTitle == 'true',
	        feature : {
	            mark : {
	                show : mark == 'true',
	                title : {
	                    mark : '辅助线-开关',
	                    markUndo : '辅助线-删除',
	                    markClear : '辅助线-清空'
	                },
	                lineStyle : {
	                    width : 1,
	                    color : '#1e90ff',
	                    type : 'dashed'
	                }
	            },
	            dataZoom : {
	                show : dataZoom == 'true',
	                title : {
	                    dataZoom : '区域缩放',
	                    dataZoomReset : '区域缩放-后退'
	                }
	            },
	            dataView : {
	                show : dataView == 'true',
	                title : '数据视图',
	                readOnly: true,
	                lang : ['数据视图', '关闭', '刷新'],
	                optionToContent: function(opt) {
						try {
							if (typeof(eval('optionToContent')) == "function") {
	    	                	var result = optionToContent(opt);
								return result;
							}
						} catch(e) {}
						return 'function optionToContent is not define';
	                }
	            },
	            magicType: {
	                show : magicType == 'true',
	                title : {
	                    line : '动态类型切换-折线图',
	                    bar : '动态类型切换-柱形图',
	                    stack : '动态类型切换-堆积',
	                    tiled : '动态类型切换-平铺'
	                },
	                type : ['line', 'bar', 'stack', 'tiled']
	            },
	            restore : {
	                show : restore == 'true',
	                title : '还原',
	                color : 'black'
	            },
	            saveAsImage : {
	                show : saveAsImage == 'true',
	                title : '保存为图片',
	                type : 'jpeg',
	                lang : ['点击本地保存'] 
	            }
	        }
	    }
	});
}
function runTooltip() {
	var show = getRowValueByName('tooltip_show');
	var trigger = getRowValueByName('tooltip_trigger');
	myChart.setOption({
		tooltip : {
			show: show == 'true',
	        trigger: trigger
		}
	});
}
function runLegend() {
	var show = getRowValueByName('legend_show');
	var orient = getRowValueByName('legend_orient');
	var x = getRowValueByName('legend_x');
	var y = getRowValueByName('legend_y');
	var data = getRowValueByName('legend_data');
	if(data==null||data=='') {
		data = new Array();
	} else {
		data = data.split(',');
	}
	myChart.setOption({
		legend: {
			show: show == 'true',
	        orient: orient,
	        x: x,
	        y: y,
	        data: data
		}
	});
}
function runYAxis() {
	var show_l = getRowValueByName('yAxis_show_l');
	var axisLabel_l = getRowValueByName('yAxis_axisLabel_l');
	var show_r = getRowValueByName('yAxis_show_r');
	var axisLabel_r = getRowValueByName('yAxis_axisLabel_r');
	myChart.setOption({
		yAxis: [{
			show : show_l == 'true',
			position : 'left',
			type : 'value',
			axisLabel : {
                formatter: '{value}'+axisLabel_l
            }
		},{
			show : show_r == 'true',
			position : 'right',
			type : 'value',
			axisLabel : {
                formatter: '{value}'+axisLabel_r
            }
		}]
	});
}
function runXAxis() {
	var show_l = getRowValueByName('xAxis_show_l');
	var axisLabel_l = getRowValueByName('xAxis_axisLabel_l');
	var data_l = getRowValueByName('xAxis_data_l');
	var show_r = getRowValueByName('xAxis_show_r');
	var axisLabel_r = getRowValueByName('xAxis_axisLabel_r');
	var data_r = getRowValueByName('xAxis_data_r');
	if(data_l==null||data_l=='') {
		data_l = new Array();
	} else {
		data_l = data_l.split(',');
	}
	if(data_r==null||data_r=='') {
		data_r = data_l;
	} else {
		data_r = data_r.split(',');
	}
	myChart.setOption({
		xAxis: [{
			show : show_l == 'true',
			position : 'bottom',
			type : 'category',
			axisLabel : {
                formatter: '{value}'+axisLabel_l
            },
            data : data_l
		},{
			show : show_r == 'true',
			position : 'top',
			type : 'category',
			axisLabel : {
                formatter: '{value}'+axisLabel_r
            },
            data : data_r
		}]
	});
}
function runSeries() {
	var serise = getRowValueByName('series_data');
	var series_data = eval("("+serise+")")
	myChart.setSeries(series_data);
}
function runDataZoom() {
	var show = getRowValueByName('dataZoom_show');
	var orient = getRowValueByName('dataZoom_orient');
	var start = getRowValueByName('dataZoom_start');
	var end = getRowValueByName('dataZoom_end');
	myChart.setOption({
		dataZoom : {
	        show : show == 'true',
	        orient: orient,
	        //realtime : true,
	        //x: 0,
	        //y: 36,
	        //width: 400,
	        //height: 20,
	        //backgroundColor: 'rgba(221,160,221,0.5)',
	        //dataBackgroundColor: 'rgba(138,43,226,0.5)',
	        //fillerColor: 'rgba(38,143,26,0.6)',
	        //handleColor: 'rgba(128,43,16,0.8)',
	        //xAxisIndex:[],
	        //yAxisIndex:[],
	        start : start,
	        end : end
	    }
	});
}

function run() {
	if(myChart) {
		myChart.dispose();
	}
	myChart = echartObj.init(document.getElementById('main'));
	myChart.setTheme('macarons');
	myChart.showLoading({
	    text: '正在努力的读取数据中...',    //loading话术
	    effect: 'bubble'	//loading效果，可选为：'spin' | 'bar' | 'ring' | 'whirling' | 'dynamicLine' | 'bubble'，支持外部装载
	});
	runTitle();
	runToolbox();
	runTooltip();
	runLegend();
	runYAxis();
	runXAxis();
	runSeries();
	runDataZoom();
	myChart.hideLoading();
}
function save() {
	showMessage(do_json_beautify(JSON.stringify(myChart.getOption()), 'html'));
}
function conf() {
	
}
function getRowValueByName(name) {
	var rows = $('#property').datagrid('getRows');
	for(var i=0; i<rows.length; i++) {
		if(rows[i].name == name) {
			return rows[i].value;
		}
	}
}
function showMessage(message) {
	layer.open({
		title: '返回值',
	    type: 1,
	    skin: 'layui-layer-demo', //样式类名
	    closeBtn: 0, //不显示关闭按钮
	    shift: 2,
	    area: ['550px', '550px'], //宽高
	    shadeClose: true, //开启遮罩关闭
	    content: message
	});
}
function optionToContent(opt) {
	var axisData = opt.xAxis[0].data;
    var series = opt.series;
    var table = '<table id="dg" style="width:98%;text-align:center"><thead><tr>'
                 + '<th data-options="field:\'time\'" style="color:red;">时间</th>'
                 + '<th data-options="field:\'name\'">' + series[0].name + '</th>'
                 + '</tr>';
    table += '</thead><tbody>';
    for (var i = 0, l = axisData.length; i < l; i++) {
        table += '<tr>'
                 + '<td>' + axisData[i] + '</td>'
                 + '<td>' + series[0].data[i] + '</td>'
                 + '</tr>';
    }
    table += '</tbody></table>';
    //table = '<table id="dg"><thead><tr><th data-options="field:\'code\'">Code</th><th data-options="field:\'name\'">Name</th><th data-options="field:\'price\'">Price</th></tr></thead><tbody><tr><td>001</td><td>name1</td><td>2323</td></tr><tr><td>002</td><td>name2</td><td>4612</td></tr></tbody></table>';
    setTimeout(function (){
    	$('#dg').datagrid();
    }, 200);
    return table;
}
</script>
</head>

<body>
	<div id="container" style="position: absolute; top: 0px; left: 0px; right: 0px; bottom: 0px;">
		<div id="content" style="position: absolute; top: 0px; left: 0px; width: 400px; bottom: 0px;">
			<table id="property" class="easyui-propertygrid" data-options="
					data: propertygrid_data,
					title: '参数设置',
					fit: true,
					showGroup: true,
					border: true,
					toolbar:'#ft'
				">
			</table>
			<div id="ft">
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-28013'" onclick="conf()">配置</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-5571'" onclick="run()">试用</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-4336'" onclick="save()">确定</a>
			</div>
		</div>
		<div id="main" style="position: absolute; top: 0px; left: 400px; right: 0px; bottom: 0px;"></div>
	</div>
</body>
</html>

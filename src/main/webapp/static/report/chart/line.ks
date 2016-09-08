var option = "option = {\n\
	title: {\n\
		text: '部门帐号状态统计'\n\
	},\n\
	tooltip: {\n\
		trigger: 'axis'\n\
	},\n\
	grid: {\n\
		left: '3%',\n\
		right: '4%',\n\
		bottom: '3%',\n\
		containLabel: true\n\
	},\n\
	toolbox: {\n\
		feature: {\n\
			saveAsImage: {}\n\
		}\n\
	},\n\
	xAxis: {\n\
		type: 'category'\n\
	},\n\
	yAxis: {\n\
		type: 'value'\n\
	}\n\
};";
var series = "seriesOption = [\n\
	{\n\
		zkey: '0',\n\
		type: 'line',\n\
		step: '',\n\
		stack: '',\n\
		markLine: {\n\
			data: [\n\
				{type: 'average', name: '平均值'}\n\
			]\n\
		},\n\
		markPoint: null\n\
	},\n\
	{\n\
		zkey: '1',\n\
		type: 'bar',\n\
		step: '',\n\
		stack: '',\n\
		markLine: null,\n\
		markPoint: {\n\
			data: [\n\
				{type : 'max', name: '最大值'},\n\
                {type : 'min', name: '最小值'}\n\
			]\n\
		}\n\
	}\n\
];";
var sqlx = "select id key,name value from sys_department where id!='00000000000000000000000000000000'";
var sqly = "select department_id xkey,state zkey,count(1) value from SYS_USER group by department_id,state";
var sqlz = "select 0 key,'冻结' name from dual union all select 1 key,'正常' name from dual";
editor_option.setValue(option);
editor_series.setValue(series);
editor_xsql.setValue(sqlx);
editor_ysql.setValue(sqly);
editor_zsql.setValue(sqlz);

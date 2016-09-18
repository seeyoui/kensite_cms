var option = "option = {\n\
    tooltip: {\n\
        trigger: 'item',\n\
        formatter: \"{a} <br/>{b}: {c} ({d}%)\"\n\
    },\n\
    legend: {\n\
        orient: 'vertical',\n\
        x: 'left'\n\
    }\n\
};";
var series = "seriesOption = [\n\
	{\n\
		zkey: '0',\n\
		selectedMode: 'single',\n\
		radius: [0, '30%'],\n\
		label: {\n\
			normal: {\n\
				position: 'inner'\n\
			}\n\
		},\n\
		labelLine: {\n\
			normal: {\n\
				show: false\n\
			}\n\
		}\n\
	},\n\
	{\n\
		zkey: '1',\n\
		center: ['50%', '50%'],\n\
		radius: ['40%', '55%']\n\
	}\n\
];";
var sqlx = "select id key,name value from sys_department where id!='00000000000000000000000000000000'";
var sqly = "select department_id xkey,state zkey,count(1) value from SYS_USER group by department_id,state";
var sqlz = "select 0 key,'冻结' name from dual union all select 1 key,'正常' name from dual";
var func = "//对sqlx,sqly,sqlz三个查询语句进行处理，可用于查询条件拼接\n\
//需自行调用${codeNum}QueryParam();\n\
//var name = $(\"#name\").textbox(\"getValue\");\n\
//if(name) {\n\
//	sqlx = sqlx.replace(\"@name\", name);\n\
//	sqly = sqly + \" where name = '\"+name+\"'\";\n\
//}";
editor_option.setValue(option);
editor_series.setValue(series);
editor_xsql.setValue(sqlx);
editor_ysql.setValue(sqly);
editor_zsql.setValue(sqlz);
editor_func.setValue(func);
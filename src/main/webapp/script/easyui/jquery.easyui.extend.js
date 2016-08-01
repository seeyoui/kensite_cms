function renderErrMsg(errMsg) {
   	for(var item in errMsg){
   		if($("#msg-"+item) != null) {
			//$("#msg-"+item).html(errMsg[item]);
			$("input[name='"+item+"']").prev().tooltip({
	            position: 'bottom',
	            content: '<span style="color:#000">'+errMsg[item]+'</span>',
	            onShow: function(){
	                $(this).tooltip('tip').css({
	                    backgroundColor: '#FFFFCC',
	                    borderColor: '#CC9933'
	                });
	            }
	        });
	        $("input[name='"+item+"']").prev().css("background-color","#fff3f3");
		}
	}
}
function cleanErrMsg() {
	$(".err-msg").html("");        	
}
function getTreeNodeLevel(tree, node) {
	var level = 0;
	while(node != null) {
		node = tree.tree('getParent', node.target);
		level++;
	}
	return level;
}
function getTreeChecked(tree, split) {
	var nodes = tree.tree('getChecked');
    var s = '';
    for(var i=0; i<nodes.length; i++){
        if (i != 0 && i != nodes.length-1) s += split;
        s += nodes[i].id;
    }
    return s;
}
function pagerFilter(data){
	if(data == null) {
		return;
	}
    var dg = $(this);
    var opts = dg.datagrid('options');
    var pager = dg.datagrid('getPager');
    pager.pagination({
        onSelectPage:function(pageNum, pageSize){
            opts.pageNumber = pageNum;
            opts.pageSize = pageSize;
            pager.pagination('refresh',{
                pageNumber:pageNum,
                pageSize:pageSize
            });
            dg.datagrid('loadData',data);
        }
    });
    if (!data.originalRows){
        data.originalRows = (data.rows);
    }
    var start = (opts.pageNumber-1)*parseInt(opts.pageSize);
    var end = start + parseInt(opts.pageSize);
    data.rows = (data.originalRows.slice(start, end));
    return data;
}
function formatDateTimeCol(val,row){
	if(val==null){
		return "";
	}
	var year = val.year+1900;
	var month = val.month+1;
	var date = val.date;
	var hours = val.hours;
	var minutes = val.minutes;
	var seconds = val.seconds;
	month = appendChar("l", month, 2, "0");
	date = appendChar("l", date, 2, "0");
	hours = appendChar("l", hours, 2, "0");
	minutes = appendChar("l", minutes, 2, "0");
	seconds = appendChar("l", seconds, 2, "0");
	return year+"-"+month+"-"+date+" "+hours+":"+minutes+":"+seconds;
}
function formatDateCol(val,row){
	if(val==null){
		return "";
	}
	var year = val.year+1900;
	var month = val.month+1;
	var date = val.date;
	return year+"-"+month+"-"+date;
}
function formatTimeCol(val,row){
	if(val==null){
		return "";
	}
	var hours = val.hours;
	var minutes = val.minutes;
	var seconds = val.seconds;
	return hours+":"+minutes+":"+seconds;
}

function appendChar(f, s, l, c) {
	s = ""+s;
	if(f = "l") {
		for(var i=s.length; i<l; i++) {
			s = c+s;
		}
	}
	if(f = "r") {
		for(var i=s.length; i<l; i++) {
			s = s+c;
		}
	}
	return s;
	
}
//扩展tree支持简单数据格式
$.fn.tree.defaults.loadFilter = function (data, parent) {
    var opt = $(this).data().tree.options;
    var idField,
    textField,
    parentField;
    if (opt.parentField) {
        idField = opt.idField || 'id';
        textField = opt.textField || 'text';
        parentField = opt.parentField;
         
        var i,
        l,
        treeData = [],
        tmpMap = [];
         
        for (i = 0, l = data.length; i < l; i++) {
            tmpMap[data[i][idField]] = data[i];
        }
         
        for (i = 0, l = data.length; i < l; i++) {
            if (tmpMap[data[i][parentField]] && data[i][idField] != data[i][parentField]) {
                if (!tmpMap[data[i][parentField]]['children'])
                    tmpMap[data[i][parentField]]['children'] = [];
                data[i]['text'] = data[i][textField];
                tmpMap[data[i][parentField]]['children'].push(data[i]);
            } else {
                data[i]['text'] = data[i][textField];
                treeData.push(data[i]);
            }
        }
        return treeData;
    }
    return data;
};
//扩展combotree支持简单数据格式
$.fn.combotree.defaults.loadFilter = function (data, parent) {
    var opt = $(this).data().tree.options;
    var idField,
    textField,
    parentField;
    if (opt.parentField) {
        idField = opt.idField || 'id';
        textField = opt.textField || 'text';
        parentField = opt.parentField;
         
        var i,
        l,
        treeData = [],
        tmpMap = [];
         
        for (i = 0, l = data.length; i < l; i++) {
            tmpMap[data[i][idField]] = data[i];
        }
         
        for (i = 0, l = data.length; i < l; i++) {
            if (tmpMap[data[i][parentField]] && data[i][idField] != data[i][parentField]) {
                if (!tmpMap[data[i][parentField]]['children'])
                    tmpMap[data[i][parentField]]['children'] = [];
                data[i]['text'] = data[i][textField];
                tmpMap[data[i][parentField]]['children'].push(data[i]);
            } else {
                data[i]['text'] = data[i][textField];
                treeData.push(data[i]);
            }
        }
        return treeData;
    }
    return data;
};
//扩展treegrid支持简单数据格式
$.fn.treegrid.defaults.loadFilter = function (data, parent) {
    var opt = $(this).data().treegrid.options;
    var idField,
    treeField,
    parentField;
    if (opt.parentField) {
        idField = opt.idField || 'id';
        treeField = opt.treeField || 'text';
        parentField = opt.parentField;
         
        var i,
        l,
        treeData = [],
        tmpMap = [];
         
        for (i = 0, l = data.length; i < l; i++) {
            tmpMap[data[i][idField]] = data[i];
        }
         
        for (i = 0, l = data.length; i < l; i++) {
            if (tmpMap[data[i][parentField]] && data[i][idField] != data[i][parentField]) {
                if (!tmpMap[data[i][parentField]]['children'])
                    tmpMap[data[i][parentField]]['children'] = [];
                data[i]['text'] = data[i][treeField];
                tmpMap[data[i][parentField]]['children'].push(data[i]);
            } else {
                data[i]['text'] = data[i][treeField];
                treeData.push(data[i]);
            }
        }
        return treeData;
    }
    return data;
};
//扩展校验
$.extend($.fn.validatebox.defaults.rules,{
	CHS:{
		validator:function(value,param){
			return/^[\u0391-\uFFE5]+$/.test(value);
		},
		message:'请输入汉字'
	},
	english:{//验证英语
		validator:function(value){
			return/^[A-Za-z]+$/i.test(value);
		},
		message:'请输入英文'
	},
	ChEn:{//验证中文或英文
		validator:function(value){
			return/^([A-Za-z]|[\u4E00-\u9FA5])+$/i.test(value);
		},
		message:'请输入中文或英文'
	},
	ip:{//验证IP地址
		validator:function(value){
			return/\d+\.\d+\.\d+\.\d+/.test(value);
		},
		message:'IP地址格式不正确'
	},
	ZIP:{
		validator:function(value,param){
			return/^[0-9]\d{5}$/.test(value);
		},
		message:'邮政编码不存在'
	},
	QQ:{
		validator:function(value,param){
			return/^[1-9]\d{4,10}$/.test(value);
		},
		message:'QQ号码不正确'
	},
	mobile:{
		validator:function(value,param){
			return/^(?:13\d|15\d|18\d)-?\d{5}(\d{3}|\*{3})$/.test(value);
		},
		message:'手机号码不正确'
	},
	tel:{
		validator:function(value,param){
			return/^(\d{3}-|\d{4}-)?(\d{8}|\d{7})?(-\d{1,6})?$/.test(value);
		},
		message:'电话号码不正确'
	},
	mobileAndTel:{
		validator:function(value,param){
			return/(^([0\+]\d{2,3})\d{3,4}\-\d{3,8}$)|(^([0\+]\d{2,3})\d{3,4}\d{3,8}$)|(^([0\+]\d{2,3}){0,1}13\d{9}$)|(^\d{3,4}\d{3,8}$)|(^\d{3,4}\-\d{3,8}$)/.test(value);
		},
		message:'请正确输入电话号码'
	},
	number:{
		validator:function(value,param){
			return/^[0-9]+.?[0-9]*$/.test(value);
		},
		message:'请输入数字'
	},
	money:{
		validator:function(value,param){
			return(/^(([1-9]\d*)|\d)(\.\d{1,2})?$/).test(value);
		},
		message:'请输入正确的金额'
	},
	mone:{
		validator:function(value,param){
			return(/^(([1-9]\d*)|\d)(\.\d{1,2})?$/).test(value);
		},
		message:'请输入整数或小数'
	},
	integer:{
		validator:function(value,param){
			return/^[+]?[1-9]\d*$/.test(value);
		},
		message:'请输入最小为1的整数'
	},
	integ:{
		validator:function(value,param){
			return/^[+]?[0-9]\d*$/.test(value);
		},
		message:'请输入正整数'
	},
	range:{
		validator:function(value,param){
			if(/^[1-9]\d*$/.test(value)){
				returnvalue>=param[0]&&value<=param[1]
			}else{
				returnfalse;
			}
		},
		message:'输入的数字在{0}到{1}之间'
	},
	minLength:{
		validator:function(value,param){
			returnvalue.length>=param[0]
		},
		message:'至少输入{0}个字'
	},
	maxLength:{
		validator:function(value,param){
			returnvalue.length<=param[0]
		},
		message:'最多{0}个字'
	},
	//select即选择框的验证
	selectValid:{
		validator:function(value,param){
			if(value==param[0]){
				returnfalse;
			}else{
				returntrue;
			}
		},
		message:'请选择'
	},
	idCode:{
		validator:function(value,param){
			return/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/.test(value);
		},
		message:'请输入正确的身份证号'
	},
	loginName:{
		validator:function(value,param){
			return/^[\u0391-\uFFE5\w]+$/.test(value);
		},
		message:'登录名称只允许汉字、英文字母、数字及下划线。'
	},
	equalTo:{
		validator:function(value,param){
			returnvalue==$(param[0]).val();
		},
		message:'两次输入的字符不一至'
	},
	englishOrNum:{//只能输入英文和数字
		validator:function(value){
			return/^[a-zA-Z0-9_]{1,}$/.test(value);
		},
		message:'请输入英文、数字、下划线'
	},
	jdbcType:{//数据库字段格式
		validator:function(value){
			return/^[a-zA-Z]+[a-zA-Z0-9_]*$/.test(value);
		},
		message:'请输入英文、数字、下划线且第一个必须为英文'
	},
	jdbcLength:{//数据库字段长度
		validator:function(value){
			return/^(([1-9][0-9]*)|([1-9][0-9]*\,[1-9]))$/.test(value);
		},
		message:'请输入正确的字段长度'
	},
	xiaoshu:{
		validator:function(value){
			return/^(([1-9]+)|([0-9]+\.[0-9]{1,2}))$/.test(value);
		},
		message:'最多保留两位小数！'		
	},
	ddPrice:{
		validator:function(value,param){
			if(/^[1-9]\d*$/.test(value)){
				returnvalue>=param[0]&&value<=param[1];
			}else{
				returnfalse;
			}
		},
		message:'请输入1到100之间正整数'
	},
	jretailUpperLimit:{
		validator:function(value,param){
			if(/^[0-9]+([.]{1}[0-9]{1,2})?$/.test(value)){
				returnparseFloat(value)>parseFloat(param[0])&&parseFloat(value)<=parseFloat(param[1]);
			}else{
				returnfalse;
			}
		},
		message:'请输入0到100之间的最多俩位小数的数字'
	},
	rateCheck:{
		validator:function(value,param){
			if(/^[0-9]+([.]{1}[0-9]{1,2})?$/.test(value)){
				returnparseFloat(value)>parseFloat(param[0])&&parseFloat(value)<=parseFloat(param[1]);
			}else{
				returnfalse;
			}
		},
		message:'请输入0到1000之间的最多俩位小数的数字'
	}
});
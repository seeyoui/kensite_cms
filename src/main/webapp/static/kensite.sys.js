//全局的AJAX访问，处理AJAX清求时SESSION超时
$.ajaxSetup({
	contentType:"application/x-www-form-urlencoded;charset=utf-8",
	complete:function(XMLHttpRequest,textStatus){
		var sessionstatus=XMLHttpRequest.getResponseHeader("sessionstatus");
		var loginPath=XMLHttpRequest.getResponseHeader("loginPath");
		if(sessionstatus=="timeOut"){
			//这里怎么处理在你，这里跳转的登录页面
			alert("会话过期,请重新登录");
			window.top.location.replace(loginPath);
		}
	}
});

//当前客户端时间
function curDateTime(formater) {
    var d = new Date();
    var year = d.getFullYear();
    var month = d.getMonth()+1;
    var date = d.getDate();
    var hours = d.getHours();
    var minutes = d.getMinutes();
    var seconds = d.getSeconds();
    var curDateTime= year;
    if(month>9)
        curDateTime = curDateTime +"-"+month;
    else  
        curDateTime = curDateTime +"-0"+month;  
    if(date>9)
        curDateTime = curDateTime +"-"+date;
    else
        curDateTime = curDateTime +"-0"+date;
    if(formater=="yyyy-MM-dd HH:mm:ss") {
    	curDateTime = curDateTime +" "+hours;
    	if(minutes>9)
            curDateTime = curDateTime +":"+minutes;
        else
            curDateTime = curDateTime +":0"+minutes;
    	if(seconds>9)
            curDateTime = curDateTime +":"+seconds;
        else
            curDateTime = curDateTime +":0"+seconds;
    }
    return curDateTime;
}

var iframeMapperWin = null, iframeMapperBody=null;
function layerOpenSqlMapper(url) {
	//var parentIndex = parent.layer.getFrameIndex(window.name);
	//parent.layer.full(parentIndex);
	//
	setTimeout(function(){
		layer.open({
		    type: 2,
		    title: '请选择',
		    fix: false, //不固定
		    area: ['800px', '370px'],
		    maxmin: false,
		    content: url,
		    btn: ['保存', '取消'],
	        success: function(layero, index){
	            iframeMapperBody = layer.getChildFrame('body', index);
	            iframeMapperWin = window[layero.find('iframe')[0]['name']];
	        },
		    yes: function(index, layero) {
		    	if(iframeMapperWin != null) {
		    		iframeMapperWin.submitInfo();
		    		//parent.layer.restore(parentIndex);
		    	}
		    },
		    cancel: function(index){
		    	layer.close(index);
		    	//parent.layer.restore(parentIndex);
		    }
		});
	},100);
}

function modDebug() {
	layer.open({
	    type: 2,
	    title: '数据列',
	    fix: false, //不固定
	    area: ['860px', '480px'],
	    maxmin: false,
	    content: getRootPath()+'/sys/mod/db/debug?tableName='+tableName,
        success: function(layero, index){
            iframeMapperBody = layer.getChildFrame('body', index);
            iframeMapperWin = window[layero.find('iframe')[0]['name']];
        }
	});
}

function getRootPath(){
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath=window.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPaht=curWwwPath.substring(0,pos);
    //获取带"/"的项目名，如：/uimcardprj
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    return (localhostPaht+projectName);
}
function getProjectName() {
	//获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName=window.document.location.pathname;
    //获取带"/"的项目名，如：/uimcardprj
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    return projectName;
}

function do_json_beautify(txt,type,compress/*是否为压缩模式*/){/* 格式化JSON源码(对象转换为JSON文本) */
    var indentChar = '    ', line=compress?'':'\n';
    if(type == null || type == '') {
    	indentChar = '    ';
    	line=compress?'':'\n';
    } else if(type == 'html') {
    	indentChar = '&nbsp;&nbsp;&nbsp;&nbsp;';
    	line=compress?'':'</br>';
    }
    if(/^\s*$/.test(txt)){
        alert('数据为空,无法格式化! ');
        return;
    }
    try{var data=eval('('+txt+')');}
    catch(e){
        alert('数据源语法错误,格式化失败! 错误信息: '+e.description,'err');
        return;
    };
    var draw=[],last=false,This=this,nodeCount=0,maxDepth=0;
    
    var notify=function(name,value,isLast,indent/*缩进*/,formObj){
        nodeCount++;/*节点计数*/
        for (var i=0,tab='';i<indent;i++ )tab+=indentChar;/* 缩进HTML */
        tab=compress?'':tab;/*压缩模式忽略缩进*/
        maxDepth=++indent;/*缩进递增并记录*/
        if(value&&value.constructor==Array){/*处理数组*/
            draw.push(tab+(formObj?('"'+name+'":'):'')+'['+line);/*缩进'[' 然后换行*/
            for (var i=0;i<value.length;i++)
                notify(i,value[i],i==value.length-1,indent,false);
            draw.push(tab+']'+(isLast?line:(','+line)));/*缩进']'换行,若非尾元素则添加逗号*/
        }else   if(value&&typeof value=='object'){/*处理对象*/
                draw.push(tab+(formObj?('"'+name+'":'):'')+'{'+line);/*缩进'{' 然后换行*/
                var len=0,i=0;
                for(var key in value)len++;
                for(var key in value)notify(key,value[key],++i==len,indent,true);
                draw.push(tab+'}'+(isLast?line:(','+line)));/*缩进'}'换行,若非尾元素则添加逗号*/
            }else{
                    if(typeof value=='string')value='"'+value+'"';
                    draw.push(tab+(formObj?('"'+name+'":'):'')+value+(isLast?'':',')+line);
            };
    };
    var isLast=true,indent=0;
    notify('',data,isLast,indent,false);
    return draw.join('');
}

function html_encode(str) {
	var s = "";
	if (str.length == 0) return "";
	s = str.replace(/&/g, "&gt;");
	s = s.replace(/</g, "&lt;");
	s = s.replace(/>/g, "&gt;");
	s = s.replace(/ /g, "&nbsp;");
	s = s.replace(/\'/g, "&#39;");
	s = s.replace(/\"/g, "&quot;");
	s = s.replace(/\n/g, "<br>");
	return s;
}

function html_decode(str) {
	var s = "";
	if (str.length == 0) return "";
	s = str.replace(/&gt;/g, "&");
	s = s.replace(/&lt;/g, "<");
	s = s.replace(/&gt;/g, ">");
	s = s.replace(/&nbsp;/g, " ");
	s = s.replace(/&#39;/g, "\'");
	s = s.replace(/&quot;/g, "\"");
	s = s.replace(/<br>/g, "\n");
	return s;
}
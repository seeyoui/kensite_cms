function mineUpload(uploadObj, ctx , funcName) {
	if(funcName==null || funcName=="") {
		funcName = "afterMineUpload";
	}
	var url = $(uploadObj).data("url");
	if(url==null || url=="") {
		url = "temp\\";
	}
	var buttonText = $(uploadObj).data("buttontext");
	if(buttonText==null || buttonText=="") {
		buttonText = "选择附件";
	}
	var method = $(uploadObj).data("method");
	if(method==null || method=="") {
		method = "POST";
	}
	var multi = $(uploadObj).data("multi");
	if(multi==null || multi=="") {
		multi = true;
	}
	var auto = $(uploadObj).data("auto");
	if(auto==null || auto=="") {
		auto = false;
	}
	var queueID = $(uploadObj).data("queueid");
	if(queueID==null || queueID=="") {
		eval( funcName + "(null, 'queueID 不能为空')" );
		return;
	}
	var fileTypeExts = $(uploadObj).data("filetypeexts");
	if(fileTypeExts==null || fileTypeExts=="") {
		queueID=="*.*";
	}
	var queueSizeLimit = $(uploadObj).data("queuesizelimit");
	if(queueSizeLimit==null || queueSizeLimit=="") {
		queueSizeLimit = 999;
	} else {
		queueSizeLimit = parseInt(queueSizeLimit);
	}
	var fileSizeLimit = $(uploadObj).data("filesizelimit");
	if(fileSizeLimit==null || fileSizeLimit=="") {
		fileSizeLimit = "5M";
	}
	$(uploadObj).uploadify({
	    'swf' : ctx+'/script/uploadify/uploadify.swf',
	    'uploader' : ctx+'/sys/uploadfile/upload?url='+url,
        'debug' : false,//开启调试
	    'buttonText' : buttonText,//按钮显示文本
	    'method' : method,
	    //'scriptData': {'url':'123'},
	    //'formData' : {'abc' : '123'},
	    'cancelImg' : ctx+'/script/uploadify/uploadify-cancel.png',
	    'auto' : auto,//是否自动上传
        'successTimeout' : 99999,//超时时间
	    'queueID' : queueID,//文件选择后的容器ID
	    'multi' : multi,//是否支持多附件上传
	    'fileTypeDesc' : '支持的格式：',//在浏览窗口底部的文件类型下拉菜单中显示的文本
        'fileTypeExts' : fileTypeExts,//允许上传的文件后缀
        'queueSizeLimit' : queueSizeLimit,//允许上传文件数量
	    'fileSizeLimit' : fileSizeLimit,//附件大小
        'onSelect' : function(file) {//选择上传文件后调用
        },
        //返回一个错误，选择文件的时候触发
        'onSelectError' : function(file, errorCode, errorMsg){
            switch(errorCode) {
                case -100:
                    errorMsg = "上传的文件数量已经超出系统限制的"+$(uploadObj).uploadify('settings','queueSizeLimit')+"个文件！";
                    break;
                case -110:
                    errorMsg = "文件 ["+file.name+"] 大小超出系统限制的"+$(uploadObj).uploadify('settings','fileSizeLimit')+"大小！";
                    break;
                case -120:
                    errorMsg = "文件 ["+file.name+"] 大小异常！";
                    break;
                case -130:
                    errorMsg = "文件 ["+file.name+"] 类型不正确！";
                    break;
            }
            eval( funcName + "(null, errorMsg)" );
        },
        //检测FLASH失败调用
        'onFallback' : function(){
            alert("您未安装FLASH控件，无法上传！请安装FLASH控件后再试。");
        },
        //上传到服务器，服务器返回相应信息到data里
	    'onUploadSuccess' : function(file, uf, response) {
	    	uf = eval("("+uf+")");
	    	var res = "上传成功";
	    	eval( funcName + "(file, uf, response)" );
        }
    });
}
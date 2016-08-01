var kensite = {
	//通知类型码
    INFO_N : 1,
    //提醒类型码
    INFO_R : 2,
    //警告类型码
    INFO_W : 3,
    //发送给指定账号
    SEND_U : "U",
    //发送给指定角色
    SEND_R : "R",
    //发送给指定部门
    SEND_D : "D",
    //发送通知
    sendNotice : function (content, receivers, belong) {
    	if(content==null || content=="" || receivers==null || receivers=="") {
    		return "F";
    	}
    	if(belong==null || belong=="") {
    		belong = "U";
    	}
    	var infoData = {content:content,type:this.INFO_N,receivers:receivers,sendType:belong};
    	return this.sendInfo(infoData);
    },
    //发送提醒
    sendRemind : function (content, receivers, belong) {
    	if(content==null || content=="" || receivers==null || receivers=="") {
    		return "F";
    	}
    	if(belong==null || belong=="") {
    		belong = "U";
    	}
    	var infoData = {content:content,type:this.INFO_R,receivers:receivers,sendType:belong};
    	return this.sendInfo(infoData);
    },
    //发送警告
    sendWarning : function (content, receivers, belong) {
    	if(content==null || content=="" || receivers==null || receivers=="") {
    		return "F";
    	}
    	if(belong==null || belong=="") {
    		belong = "U";
    	}
    	var infoData = {content:content,type:this.INFO_W,receivers:receivers,sendType:belong};
    	return this.sendInfo(infoData);
    },
    sendInfo : function (infoData) {
    	$.ajax({
			type: "post",
			url: "/kensite/information/sendInfo.do",
			data: {content:infoData.content,type:infoData.type,receivers:infoData.receivers,sendType:infoData.sendType},
			dataType: 'text',
			beforeSend: function(XMLHttpRequest){
			},
			success: function(data, textStatus){
				return data;
			}
		});
    }
};

function renderErrMsg(errMsg) {
   	for(var item in errMsg){
   		if($("#msg-"+item) != null) {
			//$("#msg-"+item).html(errMsg[item]);
   			var tagName = document.getElementById(item).tagName;
   			console.info(tagName=='SELECT');
   			if(tagName=='SELECT') {
   				console.info($('#'+item).next());
   				layer.tips(errMsg[item], $('#'+item).next(), {
   					tips: [3, '#ea5532'],
   					tipsMore: true
   				});
   			} else {
   				layer.tips(errMsg[item], '#'+item, {
   					tips: [3, '#ea5532'],
   					tipsMore: true
   				});
   			}
		}
	}
}
function cleanErrMsg() {
	$(".err-msg").html("");			
}
function renderFormData(obj) {
	//var obj = eval("("+jsonStr+")");
	var key,value,tagName,type,arr;
	for(x in obj){
		key = x;
		value = obj[x];
		
		$("[name='"+key+"'],[name='"+key+"[]']").each(function(){
			tagName = $(this)[0].tagName;
			type = $(this).attr('type');
			if(tagName=='INPUT'){
				if(type=='radio'){
					if($(this).val()==value){
						$(this).attr('checked',true);
						//$(this).checked = true;
					}
				}else if(type=='checkbox'){
					arr = value.split(',');
					for(var i =0;i<arr.length;i++){
						if($(this).val()==arr[i]){
							$(this).attr('checked',true);
							//$(this).checked = true;
						}else{
							$(this).attr('checked',false);
						}
					}
				}else{
					$(this).val(value);
				}
			}else if(tagName=='SELECT' || tagName=='TEXTAREA'){
				$(this).val(value);
			}
			
		});
	}
}
(function($){
    $.fn.parseForm=function(){
        var serializeObj={};
        var array=this.serializeArray();
        var str=this.serialize();
        $(array).each(function(){
            if(serializeObj[this.name]){
                if($.isArray(serializeObj[this.name])){
                    serializeObj[this.name].push(this.value);
                }else{
                    serializeObj[this.name]=[serializeObj[this.name],this.value];
                }
            }else{
                serializeObj[this.name]=this.value; 
            }
        });
        return serializeObj;
    };
})(jQuery);
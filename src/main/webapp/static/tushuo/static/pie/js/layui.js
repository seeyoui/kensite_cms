define(function(require, exports, module) {

    var $ = require('jquery');
    var layer, form;
    module.exports = {
        init: function(data) {
            layui.use(['layer', 'form'], function() {
				layer = layui.layer
				,form = layui.form();

				form.on('select(md)', function (data) {
					module.exports.loadData(data);
	            })
			});
        },
        loadData: function(data) {
        	var subSel = '';
        	if(data.elem.name == 'legend') {
        		subSel = ['select[name="legend-key"]', 'select[name="legend-value"]'];
    		}
        	if(data.elem.name == 'x') {
        		subSel = ['select[name="x-legend-key"]', 'select[name="x-key"]', 'select[name="x-value"]'];
    		}
        	if(data.elem.name == 'y') {
        		subSel = ['select[name="y-legend-key"]', 'select[name="y-x-key"]', 'select[name="y-value"]'];
    		}
        	$.ajax({
				type: "post",
				url: '/kensite/ks/chartEngine/viewCol',
				data: {viewName: data.value},
				dataType: 'json',
				beforeSend: function(XMLHttpRequest){
				},
				success: function(data, textStatus){
					for(var i=0; i<subSel.length; i++) {
		    			$(subSel[i]+' option').each(function() {
			        		if($(this).val() != '') {
								$(this).remove();
							}
						});
		    			for(var j=0; j<data.length; j++) {
		    				$(subSel[i]).append("<option value='"+data[j].columnName+"'>"+data[j].columnName+"</option>");
		    			}
		    		}
					form.render('select');
				}
			});
        },
        formData: function() {
        	var fd = this.serializeForm($('#setting'));
        	if(!this.validForm(fd)) {
        		return false;
        	}
        	return this.turned(fd);
        },
        serializeForm: function(form) {
            var o = {};
            var a = form.serializeArray();
            $.each(a, function() {
                if (o[this.name]) {
                    if (!o[this.name].push) {
                        o[this.name] = [o[this.name]];
                    }
                    o[this.name].push(this.value || '');
                } else {
                    o[this.name] = this.value || '';
                }
            });
            return o;
        },
        validForm: function(formData) {
        	for(var o in formData) {
        		if(!formData[o]) {
        			layer.tips('不允许为空', '#'+o, {
        				tips: [2, '#ea5532']
        			});
        			return false;
        		}
        	}
        	return true;
        },
        turned: function(fd) {
        	var formData = {};
        	formData['zsource'] = fd['legend'];
        	formData['zkey'] = fd['legend-key'];
        	formData['zvalue'] = fd['legend-value'];
        	formData['xsource'] = fd['x'];
        	formData['xkey'] = fd['x-key'];
        	formData['xzkey'] = fd['x-legend-key'];
        	formData['xvalue'] = fd['x-value'];
        	formData['ysource'] = fd['y'];
        	formData['yzkey'] = fd['y-legend-key'];
        	formData['yxkey'] = fd['y-x-key'];
        	formData['yvalue'] = fd['y-value'];
        	formData['type'] = 'pie';
        	return formData;
        }
    };
});


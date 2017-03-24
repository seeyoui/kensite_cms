<div id="${id}" style="height:100%"></div>
<script type="text/javascript">
	//queryChart_${id}();
	var chart_${id} = echarts.init(document.getElementById('${id}'), 'macarons');
	function queryChart_${id}() {
		renderChart_${id}();
	}
	function renderChart_${id}(xwhere, ywhere, zwhere) {
		if(xwhere == null) {
			xwhere = '';
		}
		if(ywhere == null) {
			ywhere = '';
		}
		if(zwhere == null) {
			zwhere = '';
		}
		$.ajax({
			type: 'post',
			url: '/kensite/ks/chartEngine/config',
			data: {
				id : '${id}',
				type : '${type}',
				zsource : '${zsource}',
				zkey : '${zkey}',
				zvalue : '${zvalue}',
				xsource : '${xsource}',
				xkey : '${xkey}',
				xzkey : '${xzkey}',
				xvalue : '${xvalue}',
				ysource : '${ysource}',
				yzkey : '${yzkey}',
				yxkey : '${yxkey}',
				yvalue : '${yvalue}',
				operation : '${operation}',
				xwhere : xwhere,
				ywhere : ywhere,
				zwhere : zwhere
			},
			dataType: 'json',
			beforeSend: function(XMLHttpRequest){
				loadi = layer.load(2, {shade: layerLoadShade,time: layerLoadMaxTime});
			},
			success: function(data, textStatus){
				layer.close(loadi);
				var chartOpt = {};
				$.extend(true, chartOpt, ${setOption});
				$.extend(true, chartOpt, data);
				chart_${id}.setOption(chartOpt);
			}
		});
	}
</script>
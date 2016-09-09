<div id="${codeNum}" style="height:100%"></div>
<script type="text/javascript">
	${codeNum}ChartData();
	function ${codeNum}ChartData() {
		var ${codeNum}Chart = echarts.init(document.getElementById('${codeNum}'), 'macarons');
		var sqlx = "${sqlx}";
		var sqly = "${sqly}";
		var sqlz = "${sqlz}";
		$.ajax({
			type: "post",
			url: '/kensite/ks/chart/${type}',
			data: {
				sqlx : sqlx,
				sqly : sqly,
				sqlz : sqlz
			},
			dataType: 'json',
			beforeSend: function(XMLHttpRequest){
				loadi = layer.load(2, {shade: layerLoadShade,time: layerLoadMaxTime});
			},
			success: function(data, textStatus){
				layer.close(loadi);
				${chartOption}
				${seriesOption}
				if(seriesOption) {
					for(var i=0; i<seriesOption.length; i++) {
						for(var j=0; j<data.series.length; j++) {
							if(seriesOption[i].zkey == data.series[j].zkey) {
								$.extend(data.series[j], seriesOption[i]);
							}
						}
					}
				}
				$.extend(option, data);
				${codeNum}Chart.setOption(option);
			}
		});
	}
</script>
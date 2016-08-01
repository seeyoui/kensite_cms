<div id="icon_li" class="fitem" style="background:#D2E9FF">
	<img id="/portal/img/menu/icon_01.png" src="${ctx_skins}/portal/img/menu/icon_00.png" style="width:25px;height:25px;"/>
	<img id="/portal/img/menu/icon_01.png" src="${ctx_skins}/portal/img/menu/icon_01.png" style="width:25px;height:25px;"/>
	<img id="/portal/img/menu/icon_01.png" src="${ctx_skins}/portal/img/menu/icon_02.png" style="width:25px;height:25px;"/>
	<img id="/portal/img/menu/icon_01.png" src="${ctx_skins}/portal/img/menu/icon_03.png" style="width:25px;height:25px;"/>
	<img id="/portal/img/menu/icon_01.png" src="${ctx_skins}/portal/img/menu/icon_04.png" style="width:25px;height:25px;"/>
	<img id="/portal/img/menu/icon_01.png" src="${ctx_skins}/portal/img/menu/icon_05.png" style="width:25px;height:25px;"/>
	<img id="/portal/img/menu/icon_01.png" src="${ctx_skins}/portal/img/menu/icon_06.png" style="width:25px;height:25px;"/>
	<img id="/portal/img/menu/icon_01.png" src="${ctx_skins}/portal/img/menu/icon_07.png" style="width:25px;height:25px;"/>
	<img id="/portal/img/menu/icon_01.png" src="${ctx_skins}/portal/img/menu/icon_08.png" style="width:25px;height:25px;"/>
	<img id="/portal/img/menu/icon_01.png" src="${ctx_skins}/portal/img/menu/icon_09.png" style="width:25px;height:25px;"/>
	<img id="/portal/img/menu/icon_01.png" src="${ctx_skins}/portal/img/menu/icon_10.png" style="width:25px;height:25px;"/>
	<img id="/portal/img/menu/icon_01.png" src="${ctx_skins}/portal/img/menu/icon_11.png" style="width:25px;height:25px;"/>
</div>

<script type="text/javascript">
$("#icon_li img").click(function(){
	var obj = $(this);
	$("#icon").textbox('setValue', obj.attr("id"));
});
</script>
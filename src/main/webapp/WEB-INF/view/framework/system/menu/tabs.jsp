<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/taglib/common.jsp"%>
<html>
<head>
<title>Kensite开发平台</title>
<%@ include file="/WEB-INF/view/taglib/header.jsp"%>
<%@ include file="/WEB-INF/view/taglib/easyui.jsp"%>
<%@ include file="/WEB-INF/view/taglib/layer.jsp"%>
<style type="text/css">
</style>
</head>
<body>
<div style="position:absolute;top:0px;left:0px;right:0px;bottom:0px;">
	<div id="ks_tab_win" class="easyui-tabs" data-options="tabPosition:'bottom',plain:true" style="width:100%;height:100%">
		<div id="0" title="基本信息" style="overflow: auto;">
			<c:if test="${empty url}">
			<span style="font-size:32px;color:red">URL都不传闹哪样</span>
			</c:if>
			<c:if test="${not empty url}">
			<iframe style="width:100%;height:100%;border:none" src="${ctx }${url}"></iframe>
			</c:if>
		</div>
	</div>
</div>
</body>
<script type="text/javascript">
	$(document).ready( $(function () {
	}));
	
	function addTab(id, title, url) {
		if(url==null || url=='') {
			var content = '<span style="font-size:32px;color:red">URL都不传闹哪样</span>';
			$('#ks_tab_win').tabs('add',{
    			title: 'I服了YOU',
    			content: content,
    			closable: true
    		});
			return;
		}
		if(title==null && title=='') {
			title=='查看';
		}
		if(id!=null && id!='') {
			var tabIdx = getTabIndex(id);
			if ($('#ks_tab_win').tabs('exists', tabIdx)){
	    		$('#ks_tab_win').tabs('select', tabIdx);
	    	} else {
	    		var content = '<iframe id="ifrm'+id+'" style="width:100%;height:100%;border:none" src="'+url+'"></iframe>';
	    		$('#ks_tab_win').tabs('add', {
	    			id: id,
	    			title: title,
	    			content: content,
	    			closable: true
	    		});
	    	}
		} else {
			if ($('#ks_tab_win').tabs('exists', title)){
	    		$('#ks_tab_win').tabs('select', title);
	    	} else {
	    		var content = '<iframe id="ifrm'+title+'" style="width:100%;height:100%;border:none" src="'+url+'"></iframe>';
	    		$('#ks_tab_win').tabs('add', {
	    			title: title,
	    			content: content,
	    			closable: true
	    		});
	    	}
		}
	}
	
	function closeTab(selector) {
		if(selector!=null && selector!='') {
			var tabIdx = getTabIndex(selector);
			if(tabIdx != -1) {
				$('#ks_tab_win').tabs('close', tabIdx);
			} else {
				$('#ks_tab_win').tabs('close', selector);
			}
		} else {
		}
	}
	
	function refreshTab(selector) {
		if(selector!=null && selector!='') {
			document.getElementById('ifrm'+selector+'').src = src;
		}
	}
	
	function getTabIndex(id) {
		var tabs = $('#ks_tab_win').tabs('tabs');
		for(var i=0; i<tabs.length; i++) {
			if(tabs[i].attr('id') == id) {
				return i;
			}
		}
		return -1;
	}
</script>
</html>

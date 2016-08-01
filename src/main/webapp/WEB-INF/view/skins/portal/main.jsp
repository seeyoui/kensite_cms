<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/taglib/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Kensite</title>
<link rel="stylesheet" type="text/css" href="${ctx_skins}/portal/css/menu-css.css"> 
<link rel="stylesheet" type="text/css" href="${ctx_skins}/portal/css/style.css"> 
<%@ include file="/WEB-INF/view/taglib/header.jsp" %>
<%@ include file="/WEB-INF/view/taglib/easyui.jsp" %>
<%@ include file="/WEB-INF/view/taglib/layer.jsp" %>
<style type="text/css">
a:link {text-decoration:none;}
a:active:{text-decoration:none;}
a:visited {text-decoration:none;}
a:hover {text-decoration:one;}
#body {
	position:absolute;
	top:0px;
	left:0px;
	right:0px;
	bottom:0px;
	min-width:800px;
}
#head{
	position:absolute;
	top:0px;
	left:0px;
	right:0px;
	height:73px;
	background: url('${ctx_skins}/portal/img/top_bg.jpg');
}
#center{
	position:absolute;
	top:73px;
	left:0px;
	right:0px;
	bottom:30px;
}
#logo{
	position:absolute;
	top:0px;
	left:0px;
	width:245px;
	height:73px;
	background:url('${ctx_skins}/portal/img/cold.png');
}
#logo_img{
	position:absolute;
	top:15px;
	left:10px;
}
#close{
	position:absolute;
	top:0px;
	right:0px;
	cursor: pointer;
}
#top_menu_button_div{
	position:absolute;
	top:0px;
	left:240px;
    right:100px;
    bottom:0px;
    cursor: pointer;
}
#top_menu_button{
	text-align: center;
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
	position: absolute;
	display: inline;
	top: 0px;
	left: 40px;
	right: 40px;
	bottom:0px;
}
#top_menu_button ul{
	width:1000px;
	position:relative;
	margin-top:4px;
	margin-left:0px;
}
.top_menu_button_bg{
	background:url('${ctx_skins}/portal/img/menu_button_bg.png');
}
.top_menu_button_click_bg{
	background:url('${ctx_skins}/portal/img/menu_button_bg.png');
}
#top_menu_button ul li{
	width:69px;
	height:64px;
	float:left;
	list-style:none;
	margin-left:5px;
}
#top_menu_button ul li img{
	margin-top:10px;
}
.top_menu_button_des{
	padding-top:3px;
	font-size:11px;
	color:#FFF;
	font-family : 微软雅黑,宋体;
}
#left{
	position:absolute;
	width:185px;
	top:0px;
	bottom:0px;
    overflow-y:scroll;
}
#right{
	position:absolute;
	top:0px;
	bottom:0px;
	right:0px;
	left:165px;
	background-color:#F2E3FF;
}
#bottom{
	position:absolute;
	left:0px;
	right:0px;
	height:30px;
	bottom:0px;
	text-align:center;
	line-height:30px;
	color:#FFF;
	font-size:13px;
	background:url('${ctx_skins}/portal/img/bottom_bg.jpg');
}
#left_button{
	position:absolute;
	top:3px;
	left:15px;
}
#right_button{
	position:absolute;
	top:3px;
	right:15px;
}
#user_info{
	line-height:30px;
	color:#FFFFFF;
	position:absolute;
	top:0px;
	height:33px;
	left:0px;
	right:0px;
	font-size:12px;
	background:url('${ctx_skins}/portal/img/user_bg.jpg');
}
#user_name_info{
	position:absolute;
	top:3px;
	left:4px;
}
#user_dept_info{
	position:absolute;
	top:3px;
	right:10px;
}
#user_menu_button{
	position:absolute;
	top:33px;
	left:0px;
	right:0px;
	bottom:0px;
}
#menu_nav{
	position:absolute;
	top:0px;
	left:0px;
	right:0px;
	height:28px;
	border-bottom:1px solid #66B3FF;
}
#menu_nav img{
	position:absolute;
	top:7px;
	left:15px;
}
#menu_des{
	position: absolute;
	top: 6px;
	left: 40px;
	font-size: 13px;
	font-family : 微软雅黑,宋体;
}
#content {
	position:absolute;
	top:30px;
	left:0px;
	right:0px;
	bottom:0px;
	font-size: 11px;
	margin: 0 auto;
	overflow-x:hidden; 
	background-color:#F1EEF0;
}
.user_menu_button_bg{
	color:#fd5524;
}
.user_menu_button_click_bg{
	color:#fd5524;
}
.user_menu ul {
	margin-top:0px;
}

.user_menu ul li {
	padding-top: 0px;
}
.user_menu ul li a {
	display: block;
	height: 25px;
	font-size: 11px;
	padding-left: 40px;
	line-height:23px;
	background:url('${ctx_skins}/portal/img/user_menu_icon_bg.png');
	background-size:100% 100%
}

#lock{
	position:absolute;
	top:7px;
	right:5px;
	cursor: pointer;
}

</style>
</head>
<body >
	<div id="body">
		<div id="updatePassword" class="easyui-window" title="修改密码" style="width:325px;height:180px;padding:10px;" data-options="modal:true,collapsible:false,minimizable:false,maximizable:false,resizable:false,closable:false,closed:true,iconCls:'icon-lock'">
			<form id="passForm" method="post">
				<div class="fitem">
	                <label>原密码</label>
	                <input id="lPass" name="lPass" type="password" class="easyui-validatebox" data-options="required:true"/>
	                <span id="msg-roomcategoryid" class="err-msg"></span>
	            </div>
				<div class="fitem">
	                <label>新密码</label>
	                <input id="newPass" name="newPass" type="password" class="easyui-validatebox" data-options="required:true"/>
	                <span id="msg-pricestrategyid" class="err-msg"></span>
	            </div>
				<div class="fitem">
	                <label>确认密码</label>
	                <input id="repaPass" name="repaPass" type="password"  class="easyui-validatebox" data-options="required:true"/>
	                <span id="msg-price" class="err-msg"></span>
	            </div>
			</form>
			<div id="dataWin-buttons">
		        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="savePassword()" style="width:90px">提交</a>
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#updatePassword').window('close')" style="width:90px">取消</a>
		    </div>
		</div>
		<div id="head">
			<div id="logo">
				<img id="logo_img" src="${ctx_common}/img/logo.png" style="width:220px;height:47px;"/>
			</div>
			<div id="top_menu_button_div">
				<img id="left_button" onclick="menu_left()" src="${ctx_skins}/portal/img/menu_left.png"/>
				<div id="top_menu_button">
					<ul>
						<!-- 生成菜单导航 -->
						<c:forEach var="menu_file" items="${menuList}" varStatus="status">
							<li onclick="getChildrenMenu('${menu_file.id}','${menu_file.text}')"><img src="${ctx_skins}${menu_file.attributes.icon}"/><br/><span class="top_menu_button_des">${menu_file.text}</span></li>
						</c:forEach>
					</ul>
				</div>
				<img id="right_button" onclick="menu_right()"  src="${ctx_skins}/portal/img/menu_right.png"/>
			</div>
			<img id="close" src="${ctx_skins}/portal/img/close.png" onclick="javascript:exit();"/>
		</div>
		<div id="center">
			<div id="left">
				<div id="user_info">
					<div id="user_name_info">姓名:<span id="username">${currentUser.name}</span></div> 
					<!-- <div id="user_dept_info">部门:<span id="userdept"></span></div> -->
					<img id="lock" src="${ctx_skins}/portal/img/lock.png" onclick="updatePassword()"/>
				</div>
				<div id="user_menu_button">
					<div id="menu_nav">
						<img src="${ctx_skins}/portal/img/menu_icon.png"/>
						<span id="menu_des"></span>
					</div>
					<div id="content">
    					<div class="user_menu">
    					</div>
					</div>
				</div>
			</div>
			<div id="right">
				<div id="tabs" class="easyui-tabs" fit="true">   
				</div>  
				<div id="mm" class="easyui-menu cs-tab-menu">
					<div id="mm-tabupdate">刷新</div>
					<div class="menu-sep"></div>
					<div id="mm-tabclose">关闭</div>
					<div id="mm-tabcloseother">关闭其他</div>
					<div id="mm-tabcloseall">关闭全部</div>
				</div>
			</div>
		</div>
		<div id="bottom">
			Copyright &copy; 2015 kensite快速开发平台
		</div>
	</div>
</body>
<script type="text/javascript">
	var length = 0;
	var jsonstr="[]";
	var jsonarray = eval('('+jsonstr+')');
	//遍历菜单结果集
	<c:forEach var="tree" items="${menuList}" varStatus="status">
		var arr = {"id":"${tree.id}","text":"${tree.text}","pid":"${tree.pid}","url":"${tree.attributes.url}","icon":"${tree.attributes.icon}"};
		jsonarray.push(arr);
			<c:forEach var="tree1" items="${tree.children}" varStatus="status">
				var arr1 = {"id":"${tree1.id}","text":"${tree1.text}","pid":"${tree1.pid}","url":"${tree1.attributes.url}","icon":"${tree1.attributes.icon}"};
				jsonarray.push(arr1);
				<c:forEach var="tree2" items="${tree1.children}" varStatus="status">
					var arr2 = {"id":"${tree2.id}","text":"${tree2.text}","pid":"${tree2.pid}","url":"${tree2.attributes.url}","icon":"${tree2.attributes.icon}"};
					jsonarray.push(arr2);
				</c:forEach>
			</c:forEach>
	</c:forEach>
	$(function(){
		top_menu_button_init();//初始化
		left_button_init();//按钮初始化效果
		right_button_init();//按钮初始化效果
		//user_menu_a_init();//用户菜单初始化效果
		
		initTab('首页', '/login/skinsPage/welcome');//初始化主页
		//初始化左侧菜单
		$("#top_menu_button ul li:first").attr("class","top_menu_button_click_bg");
		$("#top_menu_button ul li:first").click();
	})
	//初始化主页
	function initTab(title, url){
		if(url==null || url=="" || url=="/"){
			return;
		}
		url = "${ctx}"+url;
		if ($('#tabs').tabs('exists', title)){
			$('#tabs').tabs('select', title);//选中并刷新
			var currTab = $('#tabs').tabs('getSelected');
			var url = $(currTab.panel('options').content).attr('src');
			if(url != undefined && currTab.panel('options').title != 'Home') {
				$('#tabs').tabs('update',{
					tab:currTab,
					options:{
						content:createFrame(url)
					}
				});
			}
		} else {
			var content = createFrame(url);
			if(title != "首页") {
				$('#tabs').tabs('add',{
					title:title,
					content:content,
					closable:true
				});
			} else {
				$('#tabs').tabs('add',{
					title:title,
					content:content,
					closable:false
				});
			}
		}
		tabClose();
	}
	function tabClose() {
		/*双击关闭TAB选项卡*/
		$(".tabs-inner").dblclick(function(){
			var subtitle = $(this).children(".tabs-closable").text();
			$('#tabs').tabs('close',subtitle);
		});
	}		
	
	function createFrame(url) {
		var s = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
		return s;
	}
	//顶部菜单效果
	function top_menu_button_init(){
		//鼠标移入效果
		$('#top_menu_button ul li').mouseover(function(){  
			$('#top_menu_button ul li').removeClass("top_menu_button_bg");
		    $(this).addClass("top_menu_button_bg"); //弹出点击的li标签的value值  
		});
		//鼠标移除效果
		$('#top_menu_button ul li').mouseout(function(){  
		    $(this).removeClass("top_menu_button_bg"); //弹出点击的li标签的value值  
		});
		//单击选择菜单栏效果
		$('#top_menu_button ul li').click(function(){  
		    $('#top_menu_button ul li').removeClass("top_menu_button_bg");
		    $('#top_menu_button ul li').removeClass("top_menu_button_click_bg");
		    $(this).addClass("top_menu_button_click_bg"); //弹出点击的li标签的value值  
		});
	}
	//鼠标指向左边按钮效果
	function left_button_init(){
		//鼠标移入效果
		$('#left_button').mouseover(function(){  
			$('#left_button').attr("src","${ctx_skins}/portal/img/menu_left_select.png");
		});
		//鼠标移除效果
		$('#left_button').mouseout(function(){  
		    $('#left_button').attr("src","${ctx_skins}/portal/img/menu_left.png");
		});
	}
	
	//鼠标指向右边按钮效果
	function right_button_init(){
		//鼠标移入效果
		$('#right_button').mouseover(function(){  
			$('#right_button').attr("src","${ctx_skins}/portal/img/menu_right_select.png");
		});
		//鼠标移除效果
		$('#right_button').mouseout(function(){  
		    $('#right_button').attr("src","${ctx_skins}/portal/img/menu_right.png");
		});
	}
	//点击左移按钮
	function menu_left(){
		//1获取ul下所有li的个数
		var li_length = $("#top_menu_button ul li").length;
		//2获取当前ul的实际长度
		var ul_width = $("#top_menu_button").width();
		var ul_length = ul_width/70;
		//3如果实际长度小于总长度
		length = length - 75;
		if((li_length*75) <= Math.abs(length)){
			length = length + 75;
			return false;
		}
		$("#top_menu_button ul").animate({left: length+"px"},300);
	}
	//点击左移按钮
	function menu_right(){
		//1获取ul下所有li的个数
		var li_length = $("#top_menu_button ul li").length;
		//2获取当前ul的实际长度
		var ul_width = $("#top_menu_button").width();
		var ul_length = ul_width/70;
		//3如果实际长度小于总长度
		length = length + 75;
		if(length >= 75){
			length = length - 75;
			return false;
		}		
		$("#top_menu_button ul").animate({left: length+"px"},300);
	}
	//选择顶部菜单时获取子菜单
	function getChildrenMenu(id,text){
		$("#menu_des").html(text);
		//清空div下所有内容
		$(".user_menu").empty();
		//添加新菜单
		var menu = "<ul>";
		for(var i=0;i<jsonarray.length;i++){
			if(jsonarray[i].pid == id){
				menu = menu + "<li>";
				menu = menu + "<a onclick=initTab('"+jsonarray[i].text+"','"+jsonarray[i].url+"') url='"+jsonarray[i].url+"' href='javascript:void(0);'>"+jsonarray[i].text+"</a>"
				//遍历第二层节点
				for(var j=0;j<jsonarray.length;j++){
					if(jsonarray[i].id == jsonarray[j].pid){
						menu = menu + "<ul>";
						//便利第二层的数据
						for(var k=0;k<jsonarray.length;k++){
							if(jsonarray[i].id == jsonarray[k].pid){
								menu = menu + "<li>";
								menu = menu + "<a onclick=initTab('"+jsonarray[k].text+"','"+jsonarray[k].url+"') url='"+jsonarray[k].url+"' href='javascript:void(0);'>"+jsonarray[k].text+"</a>"
								menu = menu + "</li>";
							}
						}
						menu = menu + "</ul>";
						break;
					}
				}
				menu = menu + "</li>";
			}
		}
		menu = menu + "</ul>";
		$(".user_menu").append(menu);	
		user_menu_a_init();
	}
	//左侧菜单效果
	function user_menu_a_init(){
		//绑定事件
		//鼠标移入效果
		$('.user_menu a').bind('mouseover',function(){  
			$('.user_menu a').removeClass("user_menu_button_bg");
		    $(this).addClass("user_menu_button_bg"); //弹出点击的li标签的value值  
		});
		//鼠标移除效果
		$('.user_menu a').bind('mouseout',function(){  
		    $(this).removeClass("user_menu_button_bg"); //弹出点击的li标签的value值  
		});
		//单击选择菜单栏效果
		$('.user_menu a').bind('click',function(){  
			$('.user_menu a').removeClass("user_menu_button_bg");
			$('.user_menu a').removeClass("user_menu_button_click_bg");
			$(this).addClass("user_menu_button_click_bg"); //弹出点击的li标签的value值  
		});
	}
	//退出当前登录
	function exit() {
		layer.confirm('你确定要退出系统么？',function(index){
			window.location.href="${ctx}/login/logout";
		});
	}
	
	function updatePassword(){
		$('#passForm').form('clear');
		$('#updatePassword').window('open');
	}
	
	//修改密码
	function savePassword(){
		var loadi = layer.load('正在修改，请稍后...');
		var lPass = $("#lPass").val();
		var newPass = $("#newPass").val();
		var repaPass = $("#repaPass").val();
		//1判断是否填写完整信息
		if($("#passForm").form('validate') == false) {
			//layer.alert('请填写完整信息!', 0, !1);
			return;
		}
		//2判断旧密码是否正确
		$.ajax({
   			type:'POST',
   			url :'${ctx}/sysUser/validatePassWord?userName=${currentUser.userName}'+"&passWord="+lPass,
   			dataType : "json",
   			success : function(result) {
   				if(result.success){
   				//3判断两次密码是否一致
   					if(newPass != repaPass){
   						layer.alert('两次新密码输入不一致!', 0, !1);
   						return;
   					}
   					//4判断密码长度
   					if(newPass.length < 6){
   						layer.alert('密码长度不得小于6位!', 0, !1);
   						return;
   					}
   					$.ajax({
   			   			type:'POST',
   			   			url :'${ctx}/sysUser/updatePassword?id=${currentUser.id}'+"&password="+newPass+"&userName=${currentUser.userName}",
   			   			dataType : "json",
   			   			success : function(result) {
   			   				layer.close(loadi);
   			   				if(result){
   			   					$.messager.show({
   									title:'提示信息',
   									msg:'密码修改成功，下次登陆时生效！',
   									timeout:500,
   									showType:'fade'
   								});
   			   					$('#updatePassword').window('close');
   			   				}else{
   			   					$.messager.alert('警告','密码修改失败！','warning');
   			   				}
   			   			}
   			   		}); 
   				}else{
   					layer.alert('原密码输入不正确!', 0, !1);
   					layer.close(loadi);
					return;
   				}
   			}
   		}); 
	}
</script>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/taglib/common.jsp" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>虚拟机器人-体验</title>
		<%@ include file="/WEB-INF/view/taglib/header.jsp" %>
		<!-- <link rel="icon" href="./favicon.ico" type="image/x-icon"> -->
		<link href="${ctx_static }/chatRoom/css/reset.css" rel="stylesheet" type="text/css">
		<link href="${ctx_static }/chatRoom/css/experience.css" rel="stylesheet" type="text/css">
	</head>

	<body>
		<div class="bg_ex" style="display: none;">
			<div class="con">
				<div class="close"></div>
				<p class="tit">你可以这样问我</p>
				<p style="display: none;" class="btm1"></p>
				<p style="display: none;" class="btm2"></p>
				<p style="display: none;" class="btm3"></p>
			</div>
		</div>
		<div class="wapper_ex">
			<div class="con">
				<div class="wap clearfix">
					<div class="message">
						<div class="top">
							<span></span>
							<p>小秘</p>
						</div>
						<div class="btm">
							<p>喜欢的事</p>
							<div class="clearfix">
								<span class="col1">爱聊天</span>
								<span class="active col2">睡觉</span>
								<span class="col3">幻想</span>
								<span class="active col4">唱歌</span>
							</div>
							<p>讨厌的事</p>
							<div class="clearfix">
								<span class="col1">被冤枉</span>
								<span class="active col2">无所事事</span>
								<span class="col3">没钱花</span>
								<span class="active col4">被欺骗</span>
							</div>
						</div>
					</div>
					<div class="dialogue">
						<div class="roll"><span class="point"></span></div>
						<div class="displayArea">
							<div class="diswap">
								<div class="rotWord clearfix"> <span></span>
									<p id="member">嗨，最近想我没有？[羞涩]<i></i></p>
								</div>
							</div>
						</div>
						<div class="writeArea clearfix">
							<input maxlength="200" placeholder="你好呀，想我了吗？" type="text">
							<span>发 送</span>
						</div>
						<div id="share">
		                    <!--<em class="sp">分享</em>
		                    <a title="分享到微信" class="sp1" data-cmd="weixin"></a>
		                    <a title="分享到新浪微博" class="sp2" data-cmd="tsina"></a>
		                    <a title="分享到QQ空间" class="sp3" data-cmd="qzone"></a>
		                    <em class="sp4">接入图灵机器人</em>-->
		                </div>
					</div>
					<div class="example">
						<ul class="clearfix">
							<li value1="你好，你是美女么？" value2="挖掘机技术哪家强？">
								<span class="spic1"></span>
								<p>聊天</p>
							</li>
							<li value1="讲个笑话" value2="冷笑话">
								<span class="spic2"></span>
								<p>笑话</p>
							</li>
							<li value1="刘亦菲的图片">
								<span class="spic3"></span>
								<p>图片</p>
							</li>
							<li value1="淄博今天的天气" value2="淄博今天的空气质量">
								<span class="spic4"></span>
								<p>天气</p>
							</li>
							<li value1="地球到月球的距离" value2="感冒应该怎么办" value3="虎皮鹦鹉吃什么">
								<span class="spic5"></span>
								<p>问答</p>
							</li>
							<li value1="百科周杰伦" value2="李连杰的介绍">
								<span class="spic6"></span>
								<p>百科</p>
							</li>
							<li value1="讲个故事" value2="讲个白雪公主的故事">
								<span class="spic7"></span>
								<p>故事</p>
							</li>
							<!-- <li value1="我要看新闻" value2="体育新闻" value3="周杰伦的新闻">
								<span class="spic8"></span>
								<p>新闻</p>
							</li>
							<li value1="红烧肉怎么做" value2="辣子鸡丁的菜谱">
								<span class="spic9"></span>
								<p>菜谱</p>
							</li> -->
							<li value1="双鱼座明天的运势" value2="现在是什么星座" value3="今年属马的运势">
								<span class="spic10"></span>
								<p>星座</p>
							</li>
							<li value1="孙鹏这个名字好不好" value2="10086凶吉">
								<span class="spic11"></span>
								<p>凶吉</p>
							</li>
							<li value1="3乘以5等于多少" value2="25*25等多少">
								<span class="spic12"></span>
								<p>计算</p>
							</li>
							<li value1="顺丰快递">
								<span class="spic13"></span>
								<p>快递</p>
							</li>
							<li value1="明天从淄博到青岛的航班">
								<span class="spic14"></span>
								<p>飞机</p>
							</li>
							<li value1="明天从淄博到石家庄的火车">
								<span class="spic15"></span>
								<p>列车</p>
							</li>
							<li value1="开始成语接龙">
								<span class="spic16"></span>
								<p>成语接龙</p>
							</li>

						</ul>
						<!-- <p class="more">
							<a href="javascript:void(0);">了解更多</a>
						</p> -->
					</div>
				</div>
			</div>
		</div>

		<script>
			if(!window.localStorage.getItem("_userId")) {//HTML5 LocalStorage 本地存储
				window.localStorage.setItem("_userId", '${currentUser.id}');
			}
			var userId = window.localStorage.getItem("_userId");

			function member() {
				var memArr = ['趣味测评上线，对我说[测评游戏]玩玩看吧！', '你好，最近过得怎么样？', '嗨，最近想我没有？[羞涩]', '怎么不说话？', '嗨，有什么想对我聊聊么？', ];
				var num = Math.floor(Math.random() * memArr.length);
				$('#member').html(memArr[num] + '<i></i>')
			}
			member()
			var arrPLace = ['你好呀，想我了吗？', '跟我聊聊吧，你可以问我姚明多高？', '做什么呢？对我说讲笑话，我就会逗你开心哦', '快告诉我天蝎座明天的运势怎么样', '给我查查明天淄博到青岛的航班'];
			var num = Math.floor(Math.random() * 5);
			$('.writeArea input').attr('placeholder', arrPLace[num]);

			var webTuring = {
				point: function() {
					var sum = 0;
					$('.roll span').mousedown(function(ev) {
						var startT = ev.pageY;
						var t = parseFloat($('.point').css('top'));
						add();
						$('.wapper_ex').addClass('stop');
						$(document).mousemove(function(ev) {
							var moveT = ev.pageY;
							var m = t + moveT - startT;
							if(m < 0) {
								m = 0;
							}
							if(m > 326) {
								m = 326;
							}
							$('.point').css('top', m);
							var newM = m / 326 * (sum - 364);
							$('.displayArea').scrollTop(newM);
						})
						$(document).mouseup(function() {
							$('.wapper_ex').removeClass('stop');
							$(document).unbind('mousemove');
						})
					})

					function add() {
						sum = 0;
						$('.diswap>div').each(function() {
							sum += $(this).outerHeight(true);
						})
					}
					$('.displayArea').scroll(function() {
						add();
						$('.point').css('top', $('.displayArea').scrollTop() / (sum - 364) * 326);
					})

					$('.example li').click(function() {
						$('.bg_ex').show();
						$('.btm1').html($(this).attr('value1')).show();
						if($(this).attr('value2')) $('.btm2').html($(this).attr('value2')).show();
						if($(this).attr('value3')) $('.btm3').html($(this).attr('value3')).show();
					})
					$('.close').click(function() {
						$('.bg_ex').hide();
						$('.btm1').hide();
						$('.btm2').hide();
						$('.btm3').hide();
					})
					var timerTenMin = null;
					var memNum = 0;

					function memberth() {
						var memArr = ['你是不是无聊了~？做个测评游戏怎么样？准备好了请对我说[测评游戏]！', '喂，你不爱我了吗？', '楼下的，你怎么看？', '想知道天空为什么那么蓝吗？可以问我呢~', '去睡觉了吧..', '人呢，吃饭去了..?', '亲爱的去哪儿了？', '别人都说我很聪明，我也不是很确定，要不咱俩玩成语接龙试试？请对我说[成语接龙]！', '怎么不说话了~对我说[讲个笑话]，我会逗你开心呢！'];
						var num = Math.floor(Math.random() * memArr.length);
						return memArr[num];
					}

					function timeTenMin() {
						if(memNum >= 3) {
							return;
						}
						clearInterval(timerTenMin);
						timerTenMin = setInterval(function() {
							var yhy = memberth();
							$('.diswap').append('<div class="rotWord clearfix"> <span></span> <p>' + yhy + '<i></i></p></div>');
							memNum++;
							if(memNum >= 3) {
								clearInterval(timerTenMin);
								return;
							}
							scrollBtm();
						}, 60000)
					}
					timeTenMin();
					$('.bg_ex p:not(.tit)').click(function() {
						$('.diswap').append('<div class="mWord clearfix"><span></span><p>' + $(this).html() + '<i></i></p></div>');
						scrollBtm();
						$('.close').click();
						submitvalues($(this).html());
						timeTenMin();
					})
					$('.writeArea span').click(function() {
						timeTenMin();
						var val = $.trim($('.writeArea input').val());
						if(val == '') {
							return;
						}
						if(val == '绑定') {
							$('.diswap').append('<div class="mWord clearfix"><span></span><p>' + val + '<i></i></p></div>');
							$('.diswap').append('<div class="rotWord clearfix"> <span></span> <p>' + userId + '<i></i></p></div>');
							$('.writeArea input').val('');
							scrollBtm();
							return;
						}
						$('.writeArea input').attr('placeholder', '');
						$('.diswap').append('<div class="mWord clearfix"><span></span><p>' + val + '<i></i></p></div>');
						scrollBtm();
						submitvalues(val);
						$('.writeArea input').val('');
					})
					$('.displayArea').mouseover(function() {
						if(sum > 374) $('.roll').show();
					})
					$('.displayArea').mouseout(function() {
						if(sum > 374) $('.roll').hide();
					})
					$('.roll').mouseover(function() {
						$('.roll').show();
					})
					$(window).keydown(function(event) {
						switch(event.keyCode) {
							case 13:
								$('.writeArea span').click();
								break;
						}
					})

					function scrollBtm() {
						add();
						if(sum - 364 < 0) return;
						$('.displayArea').scrollTop(sum - 364);
					}

					function submitvalues(content) {
						content = content.replace('+', "%2B");
						content = content.replace('&', "%26");
						content = content.replace('%', "%25");
						$.ajax({
							url: "${ctx}/sys/chat/hi",
							data: {
								"info": content,
								'userId': userId,
								"_xsrf": ''
							},
							type: "POST",
							dataType: "json",
							cache: false,
							success: function(data) {
								/* var xml = data;
								if($(xml).find('MsgType').text() == 'text') {
									var content = $(xml).find("Content").text();
									if(content.indexOf('#@#') > 0) {
										var contentArr = content.split('#@#');
										$('.diswap').append('<div class="rotWord clearfix"> <span></span> <p>' + contentArr[0] + '<i></i></p></div>');
										setTimeout(function() {
											$('.diswap').append('<div class="rotWord clearfix"> <span></span> <p>' + contentArr[1] + '<i></i></p></div>');
											scrollBtm();
										}, 300)
									} else {
										$('.diswap').append('<div class="rotWord clearfix"> <span></span> <p>' + content + '<i></i></p></div>');
									}
								} else if($(xml).find('MsgType').text() == 'news') {
									if($(xml).find('Articles item Url').eq(1).text() == '') {
										$('.diswap').append('<div class="rotWord1 clearfix"> <span></span> <div class="findMsg"> <i></i> <p>' + $(xml).find("item").eq(0).find("Title").text() + '</p> <div class="main"> <div class="pic"><a href="' + $(xml).find("item").eq(0).find("Url").text() + '"><img src="' + $(xml).find("item").eq(0).find("PicUrl").text() + '" alt=""></a> </div> <div class="info"> <a href="javascript:;"> <div class="make">' + $(xml).find("item").eq(1).find("Title").text() + '</div> </a><a href="javascript:;"><img src="' + $(xml).find("item").eq(1).find("PicUrl").text() + '" alt=""></a> </div> <div class="line"></div> <div class="info"> <a href="javascript:;"> <div class="make">' + $(xml).find("item").eq(2).find("Title").text() + '</div> </a> <a href="javascript:;"><img src="' + $(xml).find("item").eq(2).find("PicUrl").text() + '" alt=""></a> </div></div> </div> </div>')
									} else {
										$('.diswap').append('<div class="rotWord1 clearfix"> <span></span> <div class="findMsg"> <i></i> <p>' + $(xml).find("item").eq(0).find("Title").text() + '</p> <div class="main"> <div class="pic"><a href="' + $(xml).find("item").eq(0).find("Url").text() + '"><img src="' + $(xml).find("item").eq(0).find("PicUrl").text() + '" alt=""></a> </div> <div class="info"> <a href="' + $(xml).find("item").eq(1).find("Url").text() + '"> <div class="make">' + $(xml).find("item").eq(1).find("Title").text() + '</div> </a><a href="' + $(xml).find("item").eq(1).find("Url").text() + '"><img src="' + $(xml).find("item").eq(1).find("PicUrl").text() + '" alt=""></a> </div> <div class="line"></div> <div class="info"> <a href="' + $(xml).find("item").eq(2).find("Url").text() + '"> <div class="make">' + $(xml).find("item").eq(2).find("Title").text() + '</div> </a> <a href="' + $(xml).find("item").eq(2).find("Url").text() + '"><img src="' + $(xml).find("item").eq(2).find("PicUrl").text() + '" alt=""></a> </div></div> </div> </div>')
									}
								} */
								if(data.code == 100000) {//普通聊天
									$('.diswap').append('<div class="rotWord clearfix"> <span></span> <p>' + data.text + '<i></i></p></div>');
								} else if(data.code == 200000) {//图片
									$('.diswap').append('<div class="rotWord clearfix"> <span></span> <p>' + data.text + '<a href="'+data.url+'" target="_blank">点击查看</a><i></i></p></div>');
								} else if(data.code == 302000) {//新闻
									$('.diswap').append('<div class="rotWord clearfix"> <span></span> <p>' + '敬请期待' + '<i></i></p></div>');
								} else if(data.code == 308000) {//菜谱
									$('.diswap').append('<div class="rotWord clearfix"> <span></span> <p>' + '敬请期待' + '<i></i></p></div>');
								} else {
									alert(data.code);
								}
								scrollBtm();
							}
						});
					}
					return this;
				}
			}

			webTuring.point();
		</script>
	</body>

</html>
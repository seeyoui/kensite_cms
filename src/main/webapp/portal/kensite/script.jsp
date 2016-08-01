<%@ page language="java" pageEncoding="UTF-8"%>
<script src="js/jquery-1.10.2.min.js" type="text/javascript"></script>
<script src="js/nprogress.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
	<script type="text/javascript">
		//页面加载
		$('body').show();
		$('.version').text(NProgress.version);
		NProgress.start();
		setTimeout(function() {
			NProgress.done();
			$('.fade').removeClass('out');
		}, 1000);
		//返回顶部按钮
		$(function() {
			$(window).scroll(function() {
				if($(window).scrollTop() > 100) {
					$(".gotop").fadeIn();
				} else {
					$(".gotop").hide();
				}
			});
			$(".gotop").click(function() {
				$('html,body').animate({
					'scrollTop': 0
				}, 500);
			});
		});
		//提示插件启用
		$(function() {
			$('[data-toggle="popover"]').popover();
		});
		$(function() {
			$('[data-toggle="tooltip"]').tooltip();
		});
		//鼠标滑过显示 滑离隐藏
		$(function() {
			$(".carousel").hover(function() {
				$(this).find(".carousel-control").show();
			}, function() {
				$(this).find(".carousel-control").hide();
			});
		});
		$(function() {
			$(".hot-content ul li").hover(function() {
				$(this).find("h3").show();
			}, function() {
				$(this).find("h3").hide();
			});
		});
		//页面元素智能定位
		$.fn.smartFloat = function() {
			var position = function(element) {
				var top = element.position().top; //当前元素对象element距离浏览器上边缘的距离 
				var pos = element.css("position"); //当前元素距离页面document顶部的距离
				$(window).scroll(function() { //侦听滚动时 
					var scrolls = $(this).scrollTop();
					if(scrolls > top) { //如果滚动到页面超出了当前元素element的相对页面顶部的高度 
						if(window.XMLHttpRequest) { //如果不是ie6 
							element.css({ //设置css 
								position: "fixed", //固定定位,即不再跟随滚动 
								top: 0 //距离页面顶部为0 
							}).addClass("shadow"); //加上阴影样式.shadow 
						} else { //如果是ie6 
							element.css({
								top: scrolls //与页面顶部距离 
							});
						}
					} else {
						element.css({ //如果当前元素element未滚动到浏览器上边缘，则使用默认样式 
							position: pos,
							top: top
						}).removeClass("shadow"); //移除阴影样式.shadow 
					}
				});
			};
			return $(this).each(function() {
				position($(this));
			});
		};
		//启用页面元素智能定位
		$(function() {
			$("#search").smartFloat();
		});
		
		function rightSearch() {
			var searchRight = $("#searchRight").val();
			window.location.href = "list.jsp?title="+searchRight;
		}
		
		function headerSearch() {
			var searchHeader = $("#searchHeader").val();
			window.location.href = "list.jsp?title="+searchHeader;
		}
		
		function initLoader(obj) {
			var loaderStr = '<div class="cssload-thecube">'+
				'<div class="cssload-cube cssload-c1"></div>'+
				'<div class="cssload-cube cssload-c2"></div>'+
				'<div class="cssload-cube cssload-c4"></div>'+
				'<div class="cssload-cube cssload-c3"></div>'+
				'</div>';
			$(obj).html(loaderStr);
		}
		
		//Console
		try {
			if(window.console && window.console.log) {
				console.log("\n欢迎访问Kensite技术博客\n");
				console.log("\n请记住我们的网址：%c www.seeyoui.com", "color:red");
			}
		} catch(e) {};
	</script>
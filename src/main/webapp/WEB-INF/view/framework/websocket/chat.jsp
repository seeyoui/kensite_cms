<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/taglib/common.jsp" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>唠嗑</title>
		<%@ include file="/WEB-INF/view/taglib/header.jsp" %>
		<!-- <link rel="icon" href="./favicon.ico" type="image/x-icon"> -->
		<link href="${ctx_static }/chat/css/default.css" rel="stylesheet" type="text/css">
		<link href="${ctx_static }/chat/css/styles.css" rel="stylesheet" type="text/css">
		<link href="${ctx_static }/chat/css/normalize.css" rel="stylesheet" type="text/css">
	</head>

<body>
	<div class="ks-container">
		<div class="ks-content">
			<div id="chatbox">
				<div id="friendslist">
			    	<div id="topmenu">
			        	<span class="friends"></span>
			            <span class="chats"></span>
			            <span class="history"></span>
			        </div>
			        
			        <div id="friends">
			            <!-- <div id="search">
				            <input type="text" id="searchfield" value="Search contacts..." />
			            </div> -->
			        </div>                
			        
			    </div>	
			    
			    <div id="chatview" class="p1">    	
			        <div id="profile">

			            <div id="close">
			                <div class="cy"></div>
			                <div class="cx"></div>
			            </div>
			            
			            <p></p>
			            <span></span>
			        </div>
			        <div id="chat-messages">
			        	<!-- <label></label> -->
			            <div class="message">
			            	<img src="${ctx_static }/chat/img/1_copy.jpg" />
			                <div class="bubble">
			                	Really cool stuff!
			                    <div class="corner"></div>
			                </div>
			            </div>
			            <div class="message right">
			            	<img src="${ctx_static }/chat/img/2_copy.jpg" />
			                <div class="bubble">
			                	Can you share a link for the tutorial?
			                    <div class="corner"></div>
			                </div>
			            </div>
			        </div>
			    	
			        <div id="sendmessage">
			        	<input type="text" value="Send message..." style="width:250px;"/>
			            <!-- <button id="send"></button> -->
			        </div>
			    
			    </div>        
			</div>	
		</div>
		
	</div>
		
<script type="text/javascript">
$(document).ready(function () {
	connect();
	$(document).keydown(function(e) {
		if (e.keyCode === 13) {
			echo();
		}
	});
	
    var preloadbg = document.createElement('img');
    preloadbg.src = '${ctx_static }/chat/img/timeline1.png';
    $('#searchfield').focus(function () {
        if ($(this).val() == 'Search contacts...') {
            $(this).val('');
        }
    });
    $('#searchfield').focusout(function () {
        if ($(this).val() == '') {
            $(this).val('Search contacts...');
        }
    });
    $('#sendmessage input').focus(function () {
        if ($(this).val() == 'Send message...') {
            $(this).val('');
        }
    });
    $('#sendmessage input').focusout(function () {
        if ($(this).val() == '') {
            $(this).val('Send message...');
        }
    });
    
});

function reFriendsList(webUsers) {
	$('#friends').html('');
	for(var i=0; i<webUsers.length; i++) {
		if(webUsers[i].userName == '${currentUserName}') {
			continue;
		}
		var friend = '<div class="friend">'+
    	'<img src="${ctx}/upload/headIcon/'+webUsers[i].headIcon+'" />'+
    	'<p><strong>'+webUsers[i].name+'</strong><br/>'+
        '<span>'+webUsers[i].userName+'</span></p>'+
    	'<div class="status available"></div></div>';
		$('#friends').append(friend);
	}
	bindEvent();
}

function bindEvent() {
	$('.friend').each(function () {
        $(this).click(function () {
            var childOffset = $(this).offset();
            var parentOffset = $(this).parent().parent().offset();
            var childTop = childOffset.top - parentOffset.top;
            var clone = $(this).find('img').eq(0).clone();
            var top = childTop + 12 + 'px';
            $(clone).css({ 'top': top }).addClass('floatingImg').appendTo('#chatbox');
            setTimeout(function () {
                $('#profile p').addClass('animate');
                $('#profile').addClass('animate');
            }, 100);
            setTimeout(function () {
                $('#chat-messages').addClass('animate');
                $('.cx, .cy').addClass('s1');
                setTimeout(function () {
                    $('.cx, .cy').addClass('s2');
                }, 100);
                setTimeout(function () {
                    $('.cx, .cy').addClass('s3');
                }, 200);
            }, 150);
            $('.floatingImg').animate({
                'width': '68px',
                'left': '108px',
                'top': '20px'
            }, 200);
            var name = $(this).find('p strong').html();
            var email = $(this).find('p span').html();
            $('#profile p').html(name);
            $('#profile span').html(email);
            $('.message').not('.right').find('img').attr('src', $(clone).attr('src'));
            $('#friendslist').fadeOut();
            $('#chatview').fadeIn();
            $('#close').unbind('click').click(function () {
                $('#chat-messages, #profile, #profile p').removeClass('animate');
                $('.cx, .cy').removeClass('s1 s2 s3');
                $('.floatingImg').animate({
                    'width': '40px',
                    'top': top,
                    'left': '12px'
                }, 200, function () {
                    $('.floatingImg').remove();
                });
                setTimeout(function () {
                    $('#chatview').fadeOut();
                    $('#friendslist').fadeIn();
                }, 50);
            });
        });
    });
}
</script>
<script src="http://cdn.sockjs.org/sockjs-0.3.min.js"></script>

<script type="text/javascript">
	var ws = null;
	var url = null;
	var transports = [];

	function connect() {
		if(ws) {
			return;
		}
		if (!url) {
			url = '${ctx}/websck';
		}
		if ('WebSocket' in window) {
			ws= new WebSocket("ws://"+window.location.host+"${ctx}/websck");
		} else {
			ws = new SockJS("http://"+window.location.host+"${ctx}/sockjs/websck");
		}
		ws.onopen = function () {
			console.info('open');
			afterOpen();
		};
		ws.onmessage = function (event) {
			log(event.data);
		};
		ws.onclose = function (event) {
			console.info('Info: connection closed.');
			console.info(event);
		};
	}

	function disconnect() {
		if (ws != null) {
			ws.close();
			ws = null;
		}
	}
	
	function afterOpen() {
		var sendMsg = '{"fromUser":{"userName":"${currentUser.userName}",'+
		'"name":"${currentUser.name}",'+
		'"headIcon":"${currentUser.headIcon}"},'+
		'"sendType":"login",'+
		'"sendTo":"foo",'+
		'"message":""}';
		ws.send(sendMsg);
	}

	function echo() {
		if (ws != null) {
			var message = $('#message').val();
			if(message == null || message == '') {
				return;
			}
			var sendMsg = '{"fromUser":{"userName":"${currentUser.userName}",'+
				'"name":"${currentUser.name}",'+
				'"headIcon":"${currentUser.headIcon}"},'+
				'"toUser":{"userName":"${currentUser.userName}",'+
				'"name":"${currentUser.name}",'+
				'"headIcon":"${currentUser.headIcon}"},'+
				'"sendType":"msg",'+
				'"sendTo":"one",'+
				'"message":"'+message+'"}';
			ws.send(sendMsg);
			$('#message').val('');
		} else {
		}
	}
	
	function log(message) {
		//$("#small-chat").removeAttr("class").attr("class","");
		//$("#small-chat").addClass("animated");
		//$("#small-chat").addClass("pulse");
		console.info(message);
		message = JSON.parse(message);
		if(message.msgType == 'userlist') {
			reFriendsList(message.webUsers);
		} else if(message.msgType == '') {
			
		} else if(message.msgType == '') {
			
		} else if(message.msgType == '') {
			
		}
	}
</script>
</body>

</html>
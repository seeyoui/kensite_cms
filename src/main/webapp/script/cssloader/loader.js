var _html = '<div class="cssload-loader">\n\
	<div class="cssload-inner cssload-one"></div>\n\
	<div class="cssload-inner cssload-two"></div>\n\
	<div class="cssload-inner cssload-three"></div>\n\
</div>\n\
<div class="cssload-shadow"></div>';
window.onload = function(){
	hideLoader();
}
document.write(_html);

function showLoader() {
	$('.cssload-loader').show();
	$('.cssload-shadow').show();
}

function hideLoader() {
	$('.cssload-loader').hide();
	$('.cssload-shadow').hide();
}